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
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * A {@link StoreAdapter} that caches locally the rebuilt {@link PersistentEObject}s.
 */
@Immutable
@ParametersAreNonnullByDefault
public class LocalStoreAdapter extends AbstractStoreAdapter {

    /**
     * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
     */
    @Nonnull
    private final Cache<Id, PersistentEObject> localCache = CacheBuilder.newBuilder()
            .softValues()
            .maximumSize()
            .build();

    /**
     * Constructs a new {@code LocalStoreAdapter} on the given {@code store}.
     *
     * @param store the inner store
     */
    private LocalStoreAdapter(Store store) {
        super(store);
    }

    /**
     * Adapts the given {@code store} as a {@code LocalStoreAdapter}.
     *
     * @param store the store to adapt
     *
     * @return the adapted {@code store}
     */
    public static StoreAdapter adapt(Store store) {
        if (StoreAdapter.class.isInstance(store) && !LocalStoreAdapter.class.isInstance(store)) {
            throw new IllegalArgumentException(String.format("Unable to adapt another implementation of StoreAdapter, but was %s", store.getClass().getSimpleName()));
        }

        //noinspection ConstantConditions
        return LocalStoreAdapter.class.isInstance(checkNotNull(store))
                ? LocalStoreAdapter.class.cast(store)
                : new LocalStoreAdapter(store);
    }

    @Nonnull
    @Override
    protected Cache<Id, PersistentEObject> cache() {
        return localCache;
    }

    @Override
    public void close() {
        localCache.invalidateAll();
        localCache.cleanUp();

        super.close();
    }
}
