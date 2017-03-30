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
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A {@link StoreAdapter} that caches statically the rebuilt {@link PersistentEObject}s. Its content is shared among all
 * the dependencies that use it.
 * <p>
 * This adapter is used in a fully-transient context, when {@link PersistentEObject}s are not attached to a
 * {@link fr.inria.atlanmod.neoemf.resource.PersistentResource}.
 *
 * @see PersistentEObject
 */
@Immutable
@ParametersAreNonnullByDefault
public class SharedStoreAdapter extends AbstractStoreAdapter {

    /**
     * A map that holds all created instances of {@link CacheHolder}, identified by a resource hashcode.
     */
    private static final Map<Integer, CacheHolder> HOLDERS = new HashMap<>();

    /**
     * The cache holder associated with the resource of the underlying store.
     */
    private CacheHolder holder;

    /**
     * Constructs a new {@code SharedStoreAdapter} on the given {@code store}.
     *
     * @param store the inner store
     */
    protected SharedStoreAdapter(Store store) {
        super(store);

        refreshHolder(store.resource());
        holder.dependencies.getAndIncrement();
    }

    /**
     * Adapts the given {@code store} as a {@code SharedStoreAdapter}.
     *
     * @param store the store to adapt
     *
     * @return the adapted {@code store}
     */
    public static StoreAdapter adapt(Store store) {
        if (store instanceof StoreAdapter && !(store instanceof SharedStoreAdapter)) {
            throw new IllegalArgumentException(String.format("Unable to adapt another implementation of StoreAdapter, but was %s", store.getClass().getSimpleName()));
        }

        //noinspection ConstantConditions
        return checkNotNull(store) instanceof SharedStoreAdapter
                ? (SharedStoreAdapter) store
                : new SharedStoreAdapter(store);
    }

    @Override
    public void resource(@Nullable Resource.Internal resource) {
        refreshHolder(resource);
        super.resource(resource);
    }

    /**
     * Refreshes the holder of this store.
     *
     * @param resource the resource identifying the holder to use
     */
    private void refreshHolder(@Nullable Resource resource) {
        holder = HOLDERS.computeIfAbsent(Objects.hashCode(resource), CacheHolder::new);
    }

    @Nonnull
    @Override
    protected Cache<Id, PersistentEObject> cache() {
        return holder.sharedCache;
    }

    @Override
    public void close() {
        // Cleans the shared cache: it will no longer be used
        if (holder.dependencies.decrementAndGet() <= 0) {
            HOLDERS.remove(holder.id).close();
        }

        super.close();
    }

    /**
     * A shared holder of {@link Cache}.
     */
    @Immutable
    @ParametersAreNonnullByDefault
    private static final class CacheHolder implements Closeable {

        /**
         * The name of the resource associated with the {@link #sharedCache} to avoid conflicts.
         */
        private final int id;

        /**
         * The number of dependencies that use this store.
         * <p>
         * This count is used to clean caches when they become useless.
         */
        @Nonnegative
        private final AtomicLong dependencies = new AtomicLong();

        /**
         * In-memory cache that holds recently loaded {@link PersistentEObject}s, identified by their {@link Id}.
         */
        @Nonnull
        private final Cache<Id, PersistentEObject> sharedCache = CacheBuilder.newBuilder()
                .softValues()
                .maximumSize()
                .build();

        /**
         * Constructs a new {@code CacheHolder} for the given {@code resource}.
         *
         * @param id the hashcode of the associated resource
         */
        private CacheHolder(int id) {
            this.id = id;
        }

        @Override
        public void close() {
            Log.debug("Cleaning SharedStoreAdapter {0}", id);

            sharedCache.invalidateAll();
            sharedCache.cleanUp();
        }
    }
}
