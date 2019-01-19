/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
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
 * A {@link StoreAdapter}, bound to a {@link org.eclipse.emf.ecore.resource.Resource.Internal}, that caches the rebuilt
 * {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s. Its content is shared among all the dependencies that use
 * it, i.e. each {@link org.eclipse.emf.ecore.resource.Resource.Internal} has its own cache.
 * <p>
 * This adapter is used in a transient context, when {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s are not
 * attached to a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource}.
 *
 * @see PersistentEObject
 */
@Immutable
@ParametersAreNonnullByDefault
public class TransientStoreAdapter extends AbstractStoreAdapter {

    /**
     * The cache holder associated with the resource of the underlying store.
     */
    @Nonnull
    private SharedHolder holder;

    /**
     * Constructs a new {@code TransientStoreAdapter} on the given {@code store}.
     *
     * @param store    the inner store
     * @param resource the resource to store and access
     */
    public TransientStoreAdapter(Store store, @Nullable Resource.Internal resource) {
        super(store, resource);

        this.holder = SharedHolder.forResource(resource);
    }

    @Nonnull
    @Override
    protected Cache<Id, PersistentEObject> getCache() {
        return holder.cache;
    }

    @Override
    public void close() {
        holder.close();

        super.close();
    }

    @Override
    public void resource(@Nullable Resource.Internal resource) {
        if (holder.resource == resource || resource instanceof PersistentResource) {
            // A transient store cannot be attached to a persistent resource
            return;
        }

        this.holder.close();

        this.holder = SharedHolder.forResource(resource);
        super.resource(resource);
    }

    /**
     * An object that holds {@link Cache} for a specific {@link Resource.Internal}.
     */
    @Immutable
    @ParametersAreNonnullByDefault
    private static final class SharedHolder implements Closeable {

        /**
         * A map that holds all created instances of {@link SharedHolder}, identified by their associated resource.
         */
        @Nonnull
        private static final Map<Resource.Internal, SharedHolder> REGISTRY = new HashMap<>();

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
        private final Cache<Id, PersistentEObject> cache = createCache();

        /**
         * The resource associated to this holder.
         */
        @Nullable
        private final Resource.Internal resource;

        /**
         * Constructs a new {@code SharedHolder} for the given {@code resource}.
         */
        private SharedHolder(@Nullable Resource.Internal resource) {
            this.resource = resource;
        }

        /**
         * Gets of creates the holder associated to the specified {@code resource}.
         *
         * @param resource the resource identifying the holder to use
         */
        @Nonnull
        public static SharedHolder forResource(@Nullable Resource.Internal resource) {
            SharedHolder holder = REGISTRY.computeIfAbsent(resource, SharedHolder::new);
            holder.dependencies.incrementAndGet();
            return holder;
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
