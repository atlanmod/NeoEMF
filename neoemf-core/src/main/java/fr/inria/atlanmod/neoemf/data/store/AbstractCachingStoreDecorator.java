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

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.util.cache.Cache;
import fr.inria.atlanmod.neoemf.util.cache.CacheBuilder;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link AbstractStoreDecorator} that provides a pre-loaded {@link Cache}.
 *
 * @param <V> the type of cached values
 */
@ParametersAreNonnullByDefault
public abstract class AbstractCachingStoreDecorator<V> extends AbstractStoreDecorator {

    /**
     * In-memory cache that holds loaded values, identified by their {@link FeatureKey}.
     */
    protected final Cache<FeatureKey, V> cache = CacheBuilder.newBuilder()
            .maximumSize()
            .build();

    /**
     * Constructs a new {@code AbstractCachingStoreDecorator} on the given {@code store}.
     *
     * @param store the inner store
     */
    protected AbstractCachingStoreDecorator(Store store) {
        super(store);
    }

    @Override
    public void close() {
        cache.invalidateAll();
        cache.cleanUp();

        super.close();
    }
}
