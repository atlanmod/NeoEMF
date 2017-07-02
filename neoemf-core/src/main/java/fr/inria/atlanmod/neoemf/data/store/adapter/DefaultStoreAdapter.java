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

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.common.cache.Cache;
import fr.inria.atlanmod.common.cache.CacheBuilder;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Store;

import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * A {@link StoreAdapter} that caches locally the rebuilt {@link PersistentEObject}s.
 */
@Immutable
@ParametersAreNonnullByDefault
public class DefaultStoreAdapter extends AbstractStoreAdapter {

    /**
     * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
     */
    @Nonnull
    private final Cache<Id, PersistentEObject> cache = CacheBuilder.builder()
            .softValues()
            .build();

    /**
     * Constructs a new {@code DefaultStoreAdapter} on the given {@code store}.
     *
     * @param store the inner store
     */
    private DefaultStoreAdapter(Store store, @Nullable Resource.Internal resource) {
        super(store, resource);
    }

    /**
     * Adapts the given {@code store} as a {@code DefaultStoreAdapter}.
     *
     * @param store the store to adapt
     *
     * @return the adapted {@code store}
     */
    public static StoreAdapter adapt(Store store, @Nullable Resource.Internal resource) {
        checkArgument(DefaultStoreAdapter.class.isInstance(store) || !StoreAdapter.class.isInstance(store),
                "Unable to adapt another implementation of StoreAdapter, but was %s", store.getClass().getName());

        return DefaultStoreAdapter.class.isInstance(checkNotNull(store))
                ? DefaultStoreAdapter.class.cast(store)
                : new DefaultStoreAdapter(store, resource);
    }

    @Override
    public void close() {
        cache.invalidateAll();
        cache.cleanUp();

        super.close();
    }

    @Nonnull
    @Override
    protected Cache<Id, PersistentEObject> cache() {
        return cache;
    }
}
