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

package fr.inria.atlanmod.neoemf.util.emf.compare;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.compare.utils.IEqualityHelper;
import org.eclipse.emf.ecore.EObject;

/**
 * Overrides {@link DefaultEqualityHelperFactory} methods to create
 * {@link LazyEqualityHelper} instances instead of default
 * {@link EqualityHelper} ones.
 *
 * @see LazyEqualityHelper
 */
public class LazyEqualityHelperFactory extends DefaultEqualityHelperFactory {

    /**
     * Constructs a new {@link LazyEqualityHelperFactory} with a default cache.
     */
    public LazyEqualityHelperFactory() {
        super();
    }

    /**
     * Constructs a new {@link LazyEqualityHelperFactory} using the given
     * {@code cacheBuilder}.
     *
     * @param cacheBuilder the {@link CacheBuilder} to use
     */
    public LazyEqualityHelperFactory(CacheBuilder<Object, Object> cacheBuilder) {
        super(cacheBuilder);
    }

    /**
     * Returns a new lazy {@link IEqualityHelper}.
     *
     * @return a new lazy {@link IEqualityHelper}
     *
     * @see LazyEqualityHelper
     */
    @Override
    public IEqualityHelper createEqualityHelper() {
        LoadingCache<EObject, URI> cache = LazyEqualityHelper.createDefaultCache(getCacheBuilder());
        IEqualityHelper equalityHelper = new LazyEqualityHelper(cache);
        return equalityHelper;
    }
}
