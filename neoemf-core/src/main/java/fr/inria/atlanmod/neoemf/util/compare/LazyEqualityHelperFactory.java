/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util.compare;

import com.google.common.cache.CacheBuilder;

import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;

import javax.annotation.Nonnull;

/**
 * A {@link DefaultEqualityHelperFactory} that creates instances of {@link LazyEqualityHelper}.
 *
 * @see LazyEqualityHelper
 */
public class LazyEqualityHelperFactory extends DefaultEqualityHelperFactory {

    /**
     * Constructs a new {@code LazyEqualityHelperFactory} with a default cache.
     */
    public LazyEqualityHelperFactory() {
        super();
    }

    /**
     * Constructs a new {@code LazyEqualityHelperFactory} using the given {@code cacheBuilder}.
     *
     * @param cacheBuilder the {@link CacheBuilder} to use
     */
    public LazyEqualityHelperFactory(CacheBuilder<Object, Object> cacheBuilder) {
        super(cacheBuilder);
    }

    @Nonnull
    @Override
    public LazyEqualityHelper createEqualityHelper() {
        return new LazyEqualityHelper(LazyEqualityHelper.createDefaultCache(getCacheBuilder()));
    }
}
