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

import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

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
// TODO Can be renamed as "MapperFactoryRegistry"
@ParametersAreNonnullByDefault
public final class BackendFactoryRegistry {

    /**
     * A map containing all registered {@link BackendFactory} identified by a {@link URI} scheme.
     */
    @Nonnull
    private static final Map<String, BackendFactory> FACTORIES = new ConcurrentHashMap<>();

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private BackendFactoryRegistry() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns all registered {@link URI} schemes with their {@link BackendFactory}.
     *
     * @return an immutable map
     */
    @Nonnull
    public static Map<String, BackendFactory> getFactories() {
        return Collections.unmodifiableMap(FACTORIES);
    }

    /**
     * Returns a specific {@link BackendFactory} identified by the given {@link URI} {@code scheme}.
     *
     * @param scheme the {@link URI} scheme identifying the factory
     *
     * @return the factory
     *
     * @throws NullPointerException if no factory is registered for the given {@code scheme}
     */
    @Nonnull
    public static BackendFactory getFactoryProvider(String scheme) {
        return checkNotNull(FACTORIES.get(scheme),
                "No factory is registered to process the URI scheme %s. Use the %s.register() method first",
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
    public static boolean isRegistered(@Nullable String scheme) {
        return nonNull(scheme) && FACTORIES.containsKey(scheme);
    }

    /**
     * Registers a {@link BackendFactory} identified by the given {@link URI} {@code scheme}.
     * <p>
     * If the given {@link URI} {@code scheme} is already registered, its value will be overridden by the given {@code
     * factory}.
     *
     * @param scheme  the {@link URI} scheme identifying the factory
     * @param factory the factory
     */
    public static void register(String scheme, BackendFactory factory) {
        checkNotNull(scheme);
        checkNotNull(factory);

        FACTORIES.put(scheme, factory);
    }

    /**
     * Unregisters a {@link BackendFactory} identified by the given {@link URI} {@code scheme}.
     *
     * @param scheme the {@link URI} scheme identifying the factory
     */
    public static void unregister(@Nullable String scheme) {
        if (nonNull(scheme)) {
            FACTORIES.remove(scheme);
        }
    }

    /**
     * Unregisters all factories.
     */
    public static void unregisterAll() {
        FACTORIES.clear();
    }
}
