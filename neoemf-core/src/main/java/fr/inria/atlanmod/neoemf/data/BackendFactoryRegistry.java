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

import fr.inria.atlanmod.common.annotation.Singleton;
import fr.inria.atlanmod.common.annotation.Static;
import fr.inria.atlanmod.common.annotation.VisibleForTesting;
import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

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
@Singleton
@ParametersAreNonnullByDefault
public final class BackendFactoryRegistry {

    /**
     * A map containing all registered {@link BackendFactory} identified by a {@link URI} scheme.
     */
    @Nonnull
    private final Map<String, BackendFactory> factories = new ConcurrentHashMap<>();

    /**
     * Whether this registry has been initialized at least once.
     */
    boolean initialized;

    /**
     * Constructs a new {@code BackendFactoryRegistry} and initializes it with {@link #registerAll()}.
     */
    private BackendFactoryRegistry() {
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
     * Returns all registered {@link URI} schemes with their {@link BackendFactory}.
     *
     * @return an immutable map
     */
    @Nonnull
    @VisibleForTesting
    public Map<String, BackendFactory> getFactories() {
        if (!initialized) {
            registerAll();
        }

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

        if (!initialized || !factories.containsKey(scheme)) {
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

        if (!initialized || !factories.containsKey(scheme)) {
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

        Set<Class<? extends UriBuilder>> boundClasses = Bindings.typesBoundWith(FactoryBinding.class, UriBuilder.class);

        if (boundClasses.isEmpty()) {
            Log.warn("No factory has been found in the classpath");
            return;
        }

        for (Class<? extends UriBuilder> cls : boundClasses) {
            Class<? extends BackendFactory> factoryType = cls.getAnnotation(FactoryBinding.class).value();

            BackendFactory factory = Bindings.newInstance(factoryType);
            String scheme = Bindings.schemeOf(factoryType);

            register(scheme, factory);
            Log.info("{0} registered with scheme \"{1}\"", factory.getClass().getName(), scheme);
        }

        initialized = true;
    }

    /**
     * Unregisters a {@link BackendFactory} identified by the given {@link URI} {@code scheme}.
     *
     * @param scheme the {@link URI} scheme identifying the factory
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @VisibleForTesting
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

        initialized = false;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final BackendFactoryRegistry INSTANCE = new BackendFactoryRegistry();
    }
}
