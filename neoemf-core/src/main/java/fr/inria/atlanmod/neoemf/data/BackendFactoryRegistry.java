/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data;

import com.google.common.annotations.VisibleForTesting;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * The registry that holds registered {@link URI} schemes with their associated {@link BackendFactory}.
 * <p>
 * This {@code BackendFactoryRegistry} is used for dynamically create {@link Store} and {@link Backend} when loading and
 * saving a {@link PersistentResource}. For this reason, a {@link BackendFactory} must be registered before using these
 * operations, with the {@link #register(String, BackendFactory)} method.
 *
 * @see PersistentResource#load(Map)
 * @see PersistentResource#save(Map)
 */
@ParametersAreNonnullByDefault
public final class BackendFactoryRegistry {

    /**
     * A map containing all registered {@link BackendFactory} identified by a {@link URI} scheme.
     */
    @Nonnull
    private final Map<String, BackendFactory> factories = new ConcurrentHashMap<>();

    /**
     * Constructs a new {@code BackendFactoryRegistry} and initializes it with {@link #registerAll()}.
     */
    private BackendFactoryRegistry() {
        registerAll();
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    public static BackendFactoryRegistry getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Retrieves the {@link BackendFactory} associated to the given {@code cls}.
     *
     * @param cls the class from which to extract the factory
     *
     * @return the instance of the factory
     */
    @Nonnull
    private static BackendFactory factoryFrom(Class<?> cls) {
        Class<? extends BackendFactory> factoryCls = cls.getAnnotation(FactoryBinding.class).value();

        BackendFactory factory;

        try {
            factory = (BackendFactory) factoryCls.getMethod("getInstance").invoke(null);
        }
        catch (NoSuchMethodException e) {
            Log.error("{0} must have a \"{1}\" static method", factoryCls.getName(), "getInstance");
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            Log.error(e);
            throw new RuntimeException(e);
        }

        return checkNotNull(factory);
    }

    /**
     * Retrieves the URI scheme from the given {@code cls}.
     *
     * @param cls the class from which to extract the URI scheme
     *
     * @return the scheme
     */
    @Nonnull
    private static String schemeFrom(Class<?> cls) {
        checkArgument(UriBuilder.class.isAssignableFrom(cls));

        String scheme;

        try {
            Field schemeField = cls.getField("SCHEME");
            scheme = (String) schemeField.get(cls);
        }
        catch (NoSuchFieldException e) {
            Log.error("{0} must have a \"{1}\" static field representing its scheme", cls.getName(), "SCHEME");
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return checkNotNull(scheme);
    }

    /**
     * Retrieves all classes annotated with {@link FactoryBinding} which are also assignable from the given
     * {@code instance}.
     *
     * @param instance the instance of the expected classes
     *
     * @return a set of bounded classes
     */
    @Nonnull
    private static Set<Class<?>> getBoundedClasses(Class<?> instance) {
        return new Reflections(new ConfigurationBuilder()
                .addUrls(ClasspathHelper.forJavaClassPath())
                .addUrls(ClasspathHelper.forManifest())
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()))
                .getTypesAnnotatedWith(FactoryBinding.class).stream()
                .filter(instance::isAssignableFrom)
                .collect(Collectors.toSet());
    }

    /**
     * Returns all registered {@link URI} schemes with their {@link BackendFactory}.
     *
     * @return an immutable map
     */
    @Nonnull
    @VisibleForTesting
    public Map<String, BackendFactory> getFactories() {
        return Collections.unmodifiableMap(factories);
    }

    /**
     * Returns a specific {@link BackendFactory} identified by the given {@link URI} {@code scheme}.
     *
     * @param scheme the {@link URI} scheme identifying the factory
     *
     * @return the factory
     *
     * @throws NullPointerException if {@code scheme} is {@code null}, or ig no factory is registered for the given
     *                              {@code scheme}
     */
    @Nonnull
    public BackendFactory getFactoryProvider(String scheme) {
        checkNotNull(scheme);

        // Refresh the registry if necessary
        if (!factories.containsKey(scheme)) {
            registerAll();
        }

        return checkNotNull(factories.get(scheme),
                "No factory is registered to process the URI scheme %s. Use the %s#register() method first",
                scheme,
                BackendFactoryRegistry.class.getName());
    }

    /**
     * Defines if a {@link BackendFactory} is registered for the given {@link URI} {@code scheme}.
     *
     * @param scheme the {@link URI} scheme identifying the factory
     *
     * @return {@code true} if a factory is registered for the given {@code scheme}
     */
    public boolean isRegistered(@Nullable String scheme) {
        if (isNull(scheme)) {
            return false;
        }

        // Refresh the registry if necessary
        if (!factories.containsKey(scheme)) {
            registerAll();
        }

        return factories.containsKey(scheme);
    }

    /**
     * Registers a {@link BackendFactory} identified by the given {@link URI} {@code scheme}.
     * <p>
     * If the given {@link URI} {@code scheme} is already registered, its value will be overridden by the given {@code
     * factory}.
     *
     * @param scheme  the {@link URI} scheme identifying the factory
     * @param factory the factory
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    public void register(String scheme, BackendFactory factory) {
        checkNotNull(scheme);
        checkNotNull(factory);

        factories.putIfAbsent(scheme, factory);

        Resource.Factory.Registry.INSTANCE
                .getProtocolToFactoryMap()
                .putIfAbsent(scheme, PersistentResourceFactory.getInstance());
    }

    /**
     * Registers all {@link BackendFactory} with their {@link URI} scheme by using the {@link FactoryBinding}
     * annotation.
     * <p>
     * This method scan the full Java classpath to retrieve the annotated element.
     */
    public void registerAll() {
        Log.debug("Registering all factories");

        Set<Class<?>> boundedClasses = getBoundedClasses(UriBuilder.class);

        if (boundedClasses.isEmpty()) {
            Log.warn("No factory has been found in the classpath");
            return;
        }

        for (Class<?> cls : boundedClasses) {
            BackendFactory factory = factoryFrom(cls);
            String scheme = schemeFrom(cls);

            Log.info("Registering \"{0}\" with {1}", scheme, factory.getClass().getName());
            register(scheme, factory);
        }
    }

    /**
     * Unregisters a {@link BackendFactory} identified by the given {@link URI} {@code scheme}.
     *
     * @param scheme the {@link URI} scheme identifying the factory
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    public void unregister(String scheme) {
        checkNotNull(scheme);

        factories.remove(scheme);

        Resource.Factory.Registry.INSTANCE
                .getProtocolToFactoryMap()
                .remove(scheme);
    }

    /**
     * Unregisters all registered factories.
     */
    @VisibleForTesting
    public void unregisterAll() {
        Log.debug("Unregistering all factories");

        factories.keySet().forEach(this::unregister);
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final BackendFactoryRegistry INSTANCE = new BackendFactoryRegistry();
    }
}
