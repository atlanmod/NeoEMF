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

import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.commons.cache.CacheBuilder;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link AbstractStore} that provides a pre-loaded {@link Cache}.
 *
 * @param <K> the type of keys maintained by the cache
 * @param <V> the type of cached values
 */
@ParametersAreNonnullByDefault
public abstract class AbstractCachingStore<K, V> extends AbstractStore {

    /**
     * In-memory cache that holds loaded values, identified by their key.
     */
    protected final Cache<K, V> cache = CacheBuilder.builder()
            .softValues()
            .build();

    /**
     * Constructs a new {@code AbstractCachingStore} on the given {@code store}.
     *
     * @param store the inner store
     */
    protected AbstractCachingStore(Store store) {
        super(store);
    }

    @Override
    public void close() {
        cache.invalidateAll();
        cache.cleanUp();

        super.close();
    }
}
