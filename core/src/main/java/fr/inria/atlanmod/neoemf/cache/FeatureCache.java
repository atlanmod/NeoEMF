/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.cache;

/**
 * A cache for {@link FeatureKey}.
 *
 * @param <V> the type of cached values
 */
public class FeatureCache<V> extends NeoCache<FeatureKey, V> {

    public FeatureCache() {
        super();
    }

    public FeatureCache(long cacheSize) {
        super(cacheSize);
    }
}
