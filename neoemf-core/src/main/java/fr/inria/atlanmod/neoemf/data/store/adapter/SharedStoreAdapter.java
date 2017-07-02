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
import fr.inria.atlanmod.neoemf.data.TransientBackend;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.store.Store;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * A {@link StoreAdapter} that caches statically the rebuilt {@link PersistentEObject}s. Its content is shared among
 * all the dependencies that use it.
 * <p>
 * This adapter is used in a fully-transient context, when {@link PersistentEObject}s are not attached to a
 * {@link fr.inria.atlanmod.neoemf.resource.PersistentResource}.
 *
 * @see PersistentEObject
 */
@Immutable
@ParametersAreNonnullByDefault
// TODO Share a store chain (share caching,...)
public class SharedStoreAdapter extends AbstractStoreAdapter {

    /**
     * The cache holder associated with the resource of the underlying store.
     */
    @Nonnull
    private SharedHolder holder;

    /**
     * Constructs a new {@code SharedStoreAdapter} on the given {@code store}.
     *
     * @param store the inner store
     */
    protected SharedStoreAdapter(Store store, @Nullable Resource.Internal resource) {
        super(store, resource);

        this.holder = SharedHolder.forResource(resource);
    }

    /**
     * Adapts the given {@code backend} into a {@code SharedStoreAdapter}.
     *
     * @param backend the backend to adapt
     *
     * @return the adapted {@code backend}
     */
    public static StoreAdapter adapt(TransientBackend backend, @Nullable Resource.Internal resource) {
        return new SharedStoreAdapter(new DirectWriteStore(backend), resource);
    }

    @Override
    public void close() {
        holder.close();

        super.close();
    }

    @Override
    public void resource(@Nullable Resource.Internal resource) {
        this.holder.close();

        this.holder = SharedHolder.forResource(resource);
        super.resource(resource);
    }

    @Nonnull
    @Override
    protected Cache<Id, PersistentEObject> cache() {
        return holder.cache();
    }

    /**
     * A shared holder of {@link Cache}.
     */
    @Immutable
    @ParametersAreNonnullByDefault
    private static final class SharedHolder implements Closeable {

        /**
         * A map that holds all created instances of {@link SharedHolder}, identified by their associated resource.
         */
        @Nonnull
        private static final Map<Resource, SharedHolder> REGISTRY = new HashMap<>();

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
        private final Cache<Id, PersistentEObject> cache = CacheBuilder.builder()
                .softValues()
                .build();

        /**
         * The resource associated to this holder.
         */
        @Nullable
        private final Resource resource;

        /**
         * Constructs a new {@code SharedHolder} for the given {@code resource}.
         */
        private SharedHolder(@Nullable Resource resource) {
            this.resource = resource;
        }

        /**
         * Gets of creates the holder associated to the specified {@code resource}.
         *
         * @param resource the resource identifying the holder to use
         */
        @Nonnull
        public static SharedHolder forResource(@Nullable Resource resource) {
            SharedHolder holder = REGISTRY.computeIfAbsent(resource, SharedHolder::new);
            holder.dependencies.incrementAndGet();
            return holder;
        }

        /**
         * Returns the cache of this holder.
         *
         * @return the cache
         */
        public Cache<Id, PersistentEObject> cache() {
            return cache;
        }

        @Override
        public void close() {
            if (dependencies.decrementAndGet() == 0L) {
                REGISTRY.remove(resource);

                cache.invalidateAll();
                cache.cleanUp();
            }
        }
    }
}
