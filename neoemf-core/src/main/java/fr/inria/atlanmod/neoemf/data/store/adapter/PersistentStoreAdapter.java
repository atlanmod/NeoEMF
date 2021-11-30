/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.atlanmod.commons.cache.Cache;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter} that caches the rebuilt {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}s.
 * <p>
 * This adapter can be used either in a transient context or in a persistent context, and is bound to a unique {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResource}.
 *
 * @see PersistentResource
 */
@Immutable
@ParametersAreNonnullByDefault
public class PersistentStoreAdapter extends AbstractStoreAdapter {

    /**
     * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
     */
    @Nonnull
    private final Cache<Id, PersistentEObject> cache = createCache();

    /**
     * Constructs a new {@code PersistentStoreAdapter} on the given {@code store}.
     *
     * @param store    the inner store
     * @param resource the resource to store and access
     */
    public PersistentStoreAdapter(Store store, PersistentResource resource) {
        super(store, checkNotNull(resource, "resource"));
    }

    @Nonnull
    @Override
    protected Cache<Id, PersistentEObject> getCache() {
        return cache;
    }

    @Override
    public void close() {
        cache.invalidateAll();
        cache.cleanUp();

        super.close();
    }
}
