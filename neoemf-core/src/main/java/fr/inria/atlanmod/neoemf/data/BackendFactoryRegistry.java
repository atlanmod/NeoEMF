/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.atlanmod.commons.annotation.Singleton;
import org.atlanmod.commons.annotation.Static;
import org.atlanmod.commons.annotation.VisibleForTesting;
import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.service.ServiceDefinition;
import org.atlanmod.commons.service.ServiceProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The registry that holds registered {@link org.eclipse.emf.common.util.URI} schemes with their associated {@link
 * BackendFactory}.
 * <p>
 * This {@code BackendFactoryRegistry} is used for dynamically create {@link fr.inria.atlanmod.neoemf.data.store.Store}
 * and {@link fr.inria.atlanmod.neoemf.data.Backend} when loading and saving a {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResource}. For this reason, a {@link BackendFactory} must be registered
 * before using these operations, with the {@link #register(String, BackendFactory)} method.
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
     * Constructs a new {@code BackendFactoryRegistry}.
     */
    private BackendFactoryRegistry() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static BackendFactoryRegistry getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Returns all registered URI schemes with their {@link BackendFactory}.
     *
     * @return an immutable map
     */
    @Nonnull
    @VisibleForTesting
    Map<String, BackendFactory> getFactories() {
        return Collections.unmodifiableMap(factories);
    }

    /**
     * Returns the {@link BackendFactory} identified by the given URI {@code scheme}.
     *
     * @param scheme the URI scheme identifying the factory
     *
     * @return the factory
     *
     * @throws IllegalArgumentException if {@code scheme} is {@code null}, or if no factory is registered for the {@code
     *                                  scheme}
     */
    @Nonnull
    public BackendFactory getFactoryFor(String scheme) {
        checkNotNull(scheme, "scheme");

        if (isRegistered(scheme)) {
            return factories.get(scheme);
        }

        throw new IllegalArgumentException(String.format("No factory is registered to process the URI scheme '%s'", scheme));
    }

    /**
     * Checks that a {@link BackendFactory} is registered for the given URI {@code scheme}.
     *
     * @param scheme the URI scheme identifying the factory
     *
     * @return {@code true} if a factory is registered for the {@code scheme}
     */
    public boolean isRegistered(@Nullable String scheme) {
        if (isNull(scheme)) {
            return false;
        }

        if (!factories.containsKey(scheme)) {
            registerAll();
        }

        return factories.containsKey(scheme);
    }

    /**
     * Registers a {@link BackendFactory} identified by the given URI {@code scheme}.
     * <p>
     * If the URI {@code scheme} is already registered, its value will be overridden by the {@code factory}.
     *
     * @param scheme  the URI scheme identifying the factory
     * @param factory the factory
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    public void register(String scheme, BackendFactory factory) {
        checkNotNull(scheme, "scheme");
        checkNotNull(factory, "factory");

        if (isNull(factories.put(scheme, factory))) {
            Log.info("Registered scheme: \"{0}\" = {1}", scheme, factory.getClass().getName());

            Resource.Factory.Registry.INSTANCE
                    .getProtocolToFactoryMap()
                    .putIfAbsent(scheme, PersistentResourceFactory.getInstance());
        }
    }

    /**
     * Dynamically registers all {@link BackendFactory} instances with their URI scheme.
     */
    private void registerAll() {
        ServiceProvider.getInstance()
                .load(BackendFactory.class)
                .map(ServiceDefinition::get)
                .forEach(b -> register(Bindings.schemeOf(b), b));
    }

    /**
     * Unregisters a {@link BackendFactory} identified by the specified URI {@code scheme}.
     *
     * @param scheme the URI scheme identifying the factory
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    public void unregister(String scheme) {
        checkNotNull(scheme, "scheme");

        if (nonNull(factories.remove(scheme))) {
            Resource.Factory.Registry.INSTANCE
                    .getProtocolToFactoryMap()
                    .remove(scheme);
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final BackendFactoryRegistry INSTANCE = new BackendFactoryRegistry();
    }
}
