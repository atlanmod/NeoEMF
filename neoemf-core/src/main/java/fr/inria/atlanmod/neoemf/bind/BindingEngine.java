/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.concurrent.MoreExecutors;
import fr.inria.atlanmod.commons.reflect.MoreReflection;
import fr.inria.atlanmod.commons.reflect.ReflectionException;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

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
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A static utility class for binding and reflection.
 *
 * @see ClasspathCollector
 */
@Static
@ParametersAreNonnullByDefault
public final class BindingEngine {

    /**
     * The shared concurrent pool for the scanning of elements in the classpath.
     *
     * @see ConfigurationBuilder#setExecutorService(ExecutorService)
     */
    @Nonnull
    private static final ExecutorService BINDING_POOL = MoreExecutors.newFixedThreadPool("binding-scanner");

    static {
        // Add the default URLs for scanning
        ClasspathCollector.getInstance()
                .register(new SimpleCollector(ClasspathHelper::forJavaClassPath))
                .register(new SimpleCollector(ClasspathHelper::forManifest));
    }

    private BindingEngine() {
        throw Throwables.notInstantiableClass(getClass());
    }

    /**
     * Returns the shared {@link ExecutorService} for asynchronous tasks related to binding.
     *
     * @return an immutable {@link ExecutorService}
     */
    @Nonnull
    static ExecutorService getBindingPool() {
        return BINDING_POOL;
    }

    /**
     * Adds a {@link org.osgi.framework.BundleContext} to be scanned for binding.
     * <p>
     * <b>NOTE:</b> This method is intended for internal use and should not be call in standard use.
     *
     * @param context the OSGi context to add for scanning
     *
     * @throws NullPointerException if the {@code context} is {@code null}
     * @see fr.inria.atlanmod.neoemf.util.Activator#start(BundleContext)
     */
    public static void withContext(BundleContext context) {
        ClasspathCollector.getInstance().register(new BundleContextCollector(context));
    }

    /**
     * Creates a new default configuration for classpath analysis.
     *
     * @return a new configuration
     */
    @Nonnull
    private static ConfigurationBuilder createConfiguration() {
        return new ConfigurationBuilder()
                .setExecutorService(getBindingPool())
                .setUrls(ClasspathCollector.getInstance().get());
    }

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
    private static Set<Class<?>> typesAnnotatedWith(Class<? extends Annotation> annotation) {
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
    private static <T> Set<Class<? extends T>> typesAnnotatedWith(Class<? extends Annotation> annotation, Class<? super T> type) {
        return typesAnnotatedWith(annotation).stream()
                .filter(type::isAssignableFrom)
                .map(c -> (Class<? extends T>) c)
                .collect(Collectors.toSet());
    }

    // endregion

    // region Annotation processing

    /**
     * Retrieves the URI scheme for the speficied {@code type}.
     * <p>
     * The {@code type} must be a sub-class of {@link fr.inria.atlanmod.neoemf.data.BackendFactory}, or must be
     * annotated with {@link FactoryBinding}.
     *
     * @param type the type
     *
     * @return the URI scheme
     *
     * @throws BindingException if no scheme is defined for this {@code type}
     */
    @Nonnull
    public static String schemeOf(Class<?> type) {
        return UriBuilder.createScheme(nameOf(type));
    }

    /**
     * Retrieves the URI scheme for the specified {@code factory}.
     *
     * @param factory the factory
     *
     * @return the URI scheme
     *
     * @throws BindingException if no scheme is defined for this {@code type}
     */
    @Nonnull
    public static String schemeOf(BackendFactory factory) {
        return UriBuilder.createScheme(factory.name());
    }

    /**
     * Retrieves the name for the specified {@code type}.
     * <p>
     * The {@code type} must be a sub-class of {@link fr.inria.atlanmod.neoemf.data.BackendFactory}, or must be
     * annotated with {@link FactoryBinding}.
     *
     * @param type the type
     *
     * @return the name
     *
     * @throws BindingException if no name is defined for this {@code type}
     */
    @Nonnull
    public static String nameOf(Class<?> type) {
        return factoryFor(type).name();
    }

    /**
     * Retrieves the variant for the specified {@code type}.
     * <p>
     * The {@code type} must be annotated with {@link FactoryBinding}.
     *
     * @param type the type
     *
     * @return the variant
     */
    @Nonnull
    public static String variantOf(Class<?> type) {
        checkNotNull(type, "type");

        return Optional.of(type)
                .filter(t -> t.isAnnotationPresent(FactoryBinding.class))
                .map(t -> t.getAnnotation(FactoryBinding.class))
                .map(FactoryBinding::variant)
                .orElseThrow(() -> new BindingException(
                        String.format("%s is not annotated with %s: Unable to retrieve the variant", type.getName(), FactoryBinding.class.getName())));
    }

    /**
     * Retrieves the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} associated to the {@code type}.
     * <p>
     * The {@code type} <b>must</b> be annotated with {@link FactoryBinding}, or implements {@link
     * fr.inria.atlanmod.neoemf.data.BackendFactory}.
     *
     * @param type the type of the instance to look for
     *
     * @return the instance of the {@link fr.inria.atlanmod.neoemf.data.BackendFactory}
     *
     * @throws BindingException    if no instance of {@link fr.inria.atlanmod.neoemf.data.BackendFactory} is found for
     *                             the {@code type}
     * @throws ReflectionException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static BackendFactory factoryFor(Class<?> type) {
        checkNotNull(type, "type");

        Class<? extends BackendFactory> factoryType = null;
        if (BackendFactory.class.isAssignableFrom(type)) {
            factoryType = (Class<? extends BackendFactory>) type;
        }
        else if (type.isAnnotationPresent(FactoryBinding.class)) {
            factoryType = type.getAnnotation(FactoryBinding.class).factory();
        }

        return Optional.ofNullable(factoryType)
                .map(MoreReflection::newInstance)
                .orElseThrow(() -> new BindingException(
                        String.format("%s is not annotated with %s: Unable to retrieve the associated factory", type.getName(), FactoryBinding.class.getName())));
    }

    /**
     * Returns all {@link fr.inria.atlanmod.neoemf.data.BackendFactory} instances that are visible in the classpath.
     *
     * @return a set of initialized factories
     *
     * @throws ReflectionException if an error occurs during the instantiation of any factory
     */
    @Nonnull
    public static Set<BackendFactory> allFactories() {
        return typesAnnotatedWith(FactoryBinding.class, UriBuilder.class)
                .stream()
                .map(t -> t.getAnnotation(FactoryBinding.class).factory())
                .distinct()
                .map(MoreReflection::newInstance)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves the instance of the {@code type} that is bound to a {@link fr.inria.atlanmod.neoemf.data.BackendFactory}
     * with the given {@code value}, by using the speficied {@code valueMapping}.
     * <p>
     * The {@code type} <b>must</b> be annotated with {@link FactoryBinding}.
     *
     * @param type         the type of the instance to look for
     * @param valueMapping the mapping function to retrieve the value from the factory bound by the annotation
     * @param value        the value to look for
     * @param variant      the variant to look for
     * @param <T>          the type of the instance
     *
     * @return a new instance of the {@code type}
     *
     * @throws BindingException    if no instance of {@code type} is found for the {@code value} by using the {@code
     *                             valueMapping}
     * @throws ReflectionException if an error occurs during the instantiation
     */
    @Nonnull
    public static <T> T findBy(Class<? super T> type, Function<Class<? extends BackendFactory>, String> valueMapping, String value, @Nullable String variant) {
        final String variantOrDefault = Optional.ofNullable(variant).orElse(FactoryBinding.DEFAULT_VARIANT);

        // Predicate to filter types according to their annotation
        final Predicate<Class<? extends T>> isRelevant = t -> {
            FactoryBinding a = t.getDeclaredAnnotation(FactoryBinding.class);

            return nonNull(a)
                    && Objects.equals(value, valueMapping.apply(a.factory()))
                    && Objects.equals(variantOrDefault, a.variant());
        };

        // Find all types that match the value and variant
        List<Class<? extends T>> relevantTypes = BindingEngine.<T>typesAnnotatedWith(FactoryBinding.class, type)
                .stream()
                .filter(isRelevant)
                .collect(Collectors.toList());

        // Ensure that only one type is relevant
        if (relevantTypes.isEmpty()) {
            throw new BindingException(String.format("Unable to find a %s instance for value '%s' and variant '%s'; No relevant type found in %s", type.getName(), value, variantOrDefault, ClasspathCollector.getInstance().get()));
        }
        else if (relevantTypes.size() > 1) {
            throw new BindingException(String.format("Unable to find a %s instance for value '%s' and variant '%s'; Several relevant types found : %s", type.getName(), value, variantOrDefault, relevantTypes));
        }

        return MoreReflection.newInstance(relevantTypes.get(0));
    }

    // endregion
}
