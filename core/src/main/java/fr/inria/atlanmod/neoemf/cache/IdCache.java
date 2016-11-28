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

import fr.inria.atlanmod.neoemf.core.Id;

/**
 * A cache for {@link Id}.
 *
 * @param <V> the type of cached values
 */
public class IdCache<V> extends NeoCache<Id, V> {

    public IdCache() {
        super();
    }

    public IdCache(long cacheSize) {
        super(cacheSize);
    }
}
