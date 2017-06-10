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
