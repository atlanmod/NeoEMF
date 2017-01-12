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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A registry that keeps in memory links of registered {@link org.eclipse.emf.common.util.URI} schemes to their
 * associated {@link PersistenceBackendFactory factory}.
 * <p>
 * This {@code PersistenceBackendFactoryRegistry} is used for dynamically create
 * {@link fr.inria.atlanmod.neoemf.data.store.PersistentStore} and {@link PersistenceBackend} when loading and saving a
 * {@link fr.inria.atlanmod.neoemf.resource.PersistentResource}. For this reason, a
 * {@link PersistenceBackendFactory factory} must be registered before using these operations, with the
 * {@link #register(String, PersistenceBackendFactory)} method.
 *
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResource#load(Map)
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResource#save(Map)
 */
public class PersistenceBackendFactoryRegistry {

    /**
     * A map containing all registered {@link PersistenceBackendFactory factory} identified by a URI scheme.
     */
    private static final Map<String, PersistenceBackendFactory> FACTORIES = new HashMap<>();

    private PersistenceBackendFactoryRegistry() {
    }

    /**
     * Returns all registered URI schemes with their {@link PersistenceBackendFactory back-end factory}.
     *
     * @return an immutable {@link Map}
     */
    @Nonnull
    public static Map<String, PersistenceBackendFactory> getFactories() {
        return Collections.unmodifiableMap(FACTORIES);
    }

    /**
     * Returns a specific {@link PersistenceBackendFactory factory} identified by the given URI {@code scheme}.
     *
     * @param scheme the URI scheme identifying the back-end factory
     *
     * @return the back-end factory
     *
     * @throws NullPointerException if no back-end factory is registered for the given {@code scheme}
     */
    @Nonnull
    public static PersistenceBackendFactory getFactoryProvider(String scheme) {
        return checkNotNull(FACTORIES.get(scheme),
                "No factory is registered to process the URI scheme %s. Use the %s.register() method first",
                scheme,
                PersistenceBackendFactoryRegistry.class.getName());
    }

    /**
     * Defines if a {@link PersistenceBackendFactory factory} is registered for the given URI {@code scheme}.
     *
     * @param scheme the URI scheme identifying the back-end factory
     *
     * @return {@code true} if a back-end factory is registered for the given {@code scheme}
     */
    public static boolean isRegistered(@Nullable String scheme) {
        return !isNull(scheme) && FACTORIES.containsKey(scheme);
    }

    /**
     * Registers a {@link PersistenceBackendFactory factory} identified by the given URI {@code scheme}.
     * <p>
     * If the given {@code scheme} is already registered, its value will be overriden by the given {@code factory}.
     *
     * @param scheme the URI scheme identifying the back-end factory
     */
    public static void register(@Nonnull String scheme, @Nonnull PersistenceBackendFactory factory) {
        checkNotNull(scheme);
        checkNotNull(factory);
        FACTORIES.put(scheme, factory);
    }

    /**
     * Unregisters a {@link PersistenceBackendFactory factory} identified by the given URI {@code scheme}.
     *
     * @param scheme the URI scheme identifying the back-end factory
     */
    public static void unregister(@Nullable String scheme) {
        if (nonNull(scheme)) {
            FACTORIES.remove(scheme);
        }
    }

    /**
     * Unregisters all back-end factories.
     */
    public static void unregisterAll() {
        FACTORIES.clear();
    }
}
