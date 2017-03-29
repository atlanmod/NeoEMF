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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.util.cache.Cache;
import fr.inria.atlanmod.neoemf.util.cache.CacheBuilder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A {@link StoreAdapter} that caches statically the rebuilt {@link PersistentEObject}s.
 * <p>
 * This adapter is used in a fully-transient context, when {@link PersistentEObject}s are not attached to a
 * {@link fr.inria.atlanmod.neoemf.resource.PersistentResource}.
 *
 * @see PersistentEObject
 */
@ParametersAreNonnullByDefault
public class StaticStoreAdapter extends AbstractStoreAdapter {

    /**
     * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
     */
    @Nonnull
    private static final Cache<Id, PersistentEObject> GLOBAL_CACHE = CacheBuilder.newBuilder()
            .softValues()
            .maximumSize()
            .build();

    /**
     * Constructs a new {@code StaticStoreAdapter} on the given {@code store}.
     *
     * @param store the inner store
     */
    protected StaticStoreAdapter(Store store) {
        super(store);
    }

    /**
     * Adapts the given {@code store} as a {@code StaticStoreAdapter}.
     *
     * @param store the store to adapt
     *
     * @return the adapted {@code store}
     */
    public static StoreAdapter adapt(Store store) {
        if (store instanceof StoreAdapter && !(store instanceof StaticStoreAdapter)) {
            throw new IllegalArgumentException(String.format("Unable to adapt another implementation of StoreAdapter, but was %s", store.getClass().getSimpleName()));
        }

        //noinspection ConstantConditions
        return checkNotNull(store) instanceof StaticStoreAdapter
                ? (StaticStoreAdapter) store
                : new StaticStoreAdapter(store);
    }

    @Nonnull
    @Override
    protected Cache<Id, PersistentEObject> cache() {
        return GLOBAL_CACHE;
    }

    @Override
    public void close() {
        // TODO Clear the cache at the right time

        super.close();
    }
}
