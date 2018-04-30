/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind.internal;

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.concurrent.MoreExecutors;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.reflect.MoreReflection;
import fr.inria.atlanmod.neoemf.bind.BindingEngine;
import fr.inria.atlanmod.neoemf.bind.BindingException;
import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.util.UriFactory;

import org.osgi.framework.BundleContext;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link BindingEngine} that uses reflection to retrieve bound objects.
 */
@Singleton
@ParametersAreNonnullByDefault
public class ReflectiveBindingEngine implements BindingEngine {

    /**
     * The shared concurrent pool for the scanning of elements in the classpath.
     *
     * @see ConfigurationBuilder#setExecutorService(ExecutorService)
     */
    @Nonnull
    private final ExecutorService bindingPool = MoreExecutors.newFixedThreadPool("binding-scanner");

    /**
     *
     */
    @Nonnull
    private final ClasspathCollector collector;

    /**
     * Constructs a new {@code ReflectiveBindingEngine}.
     */
    public ReflectiveBindingEngine(BundleContext context) {
        Log.info("Using a ReflectiveBindingEngine");

        this.collector = new ClasspathCollector(bindingPool)
                .register(new SimpleCollector(ClasspathHelper::forJavaClassPath))
                .register(new SimpleCollector(ClasspathHelper::forManifest))
                .register(new BundleContextCollector(context));
    }

    /**
     * Adds a {@link org.osgi.framework.BundleContext} to be scanned for binding.
     *
     * <b>NOTE:</b> This method is intended for internal use and should not be call in standard use.
     *
     * @param context the OSGi context to add for scanning
     *
     * @throws NullPointerException if the {@code context} is {@code null}
     * @see fr.inria.atlanmod.neoemf.util.Activator#start(BundleContext)
     */
    public void setContext(BundleContext context) {
        collector.register(new BundleContextCollector(context));
    }

    // region Annotation processing

    @Nonnull
    @Override
    public Set<BackendFactory> findFactories() {
        return typesAnnotatedWith(FactoryBinding.class, UriFactory.class)
                .stream()
                .map(t -> t.getAnnotation(FactoryBinding.class).factory())
                .distinct()
                .map(MoreReflection::newInstance)
                .collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public <T> T find(Class<? super T> type, Function<Class<? extends BackendFactory>, String> valueMapping, String value, @Nullable String variant) {
        final String variantOrDefault = Optional.ofNullable(variant).orElse(FactoryBinding.DEFAULT_VARIANT);

        // Find all types that match the value and variant
        List<Class<? extends T>> relevantTypes = this.<T>typesAnnotatedWith(FactoryBinding.class, type).stream()
                .filter(t -> {
                    final FactoryBinding a = t.getDeclaredAnnotation(FactoryBinding.class);

                    return nonNull(a)
                            && Objects.equals(value, valueMapping.apply(a.factory()))
                            && Objects.equals(variantOrDefault, a.variant());
                })
                .collect(Collectors.toList());

        // Ensure that only one type is relevant
        if (relevantTypes.isEmpty()) {
            throw new BindingException(String.format("Unable to find a %s instance for value '%s' and variant '%s'; No relevant type found in %s", type.getName(), value, variantOrDefault, collector.get()));
        }
        else if (relevantTypes.size() > 1) {
            throw new BindingException(String.format("Unable to find a %s instance for value '%s' and variant '%s'; Several relevant types found : %s", type.getName(), value, variantOrDefault, relevantTypes));
        }

        return MoreReflection.newInstance(relevantTypes.get(0));
    }

    // endregion

    // region Reflection

    /**
     * Retrieves all types annotated with the specified {@code annotation}.
     *
     * @param annotation the expected annotation
     *
     * @return a set of annotated instances
     *
     * @see Reflections#getTypesAnnotatedWith(Class)
     */
    @Nonnull
    private Set<Class<?>> typesAnnotatedWith(Class<? extends Annotation> annotation) {
        Configuration conf = createConfiguration().setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());
        return new Reflections(conf).getTypesAnnotatedWith(annotation);
    }

    /**
     * Retrieves all types annotated with the specified {@code annotation}, which are also assignable from the given
     * {@code type}.
     *
     * @param annotation the expected annotation
     * @param type       the type of the expected classes
     * @param <T>        the type of the instances to look for
     *
     * @return a set of annotated instances of {@code type}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private <T> Set<Class<? extends T>> typesAnnotatedWith(Class<? extends Annotation> annotation, Class<? super T> type) {
        return typesAnnotatedWith(annotation).stream()
                .filter(type::isAssignableFrom)
                .map(c -> (Class<? extends T>) c)
                .collect(Collectors.toSet());
    }

    /**
     * Creates a new default configuration for classpath analysis.
     *
     * @return a new configuration
     */
    @Nonnull
    private ConfigurationBuilder createConfiguration() {
        return new ConfigurationBuilder()
                .setExecutorService(bindingPool)
                .setUrls(collector.get());
    }

    // endregion
}
