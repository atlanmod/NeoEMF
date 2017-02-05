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

package fr.inria.atlanmod.neoemf.data.berkeleydb.option;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

/**
 * {@link PersistentStoreOptions} that hold BerkeleyDB related database access features.
 */
@Experimental
public enum BerkeleyDbStoreOptions implements PersistentStoreOptions {

    /**
     * ???
     */
    DIRECT_WRITE,

    /**
     * ???
     */
    DIRECT_WRITE_LISTS,

    /**
     * ???
     */
    DIRECT_WRITE_ARRAYS,

    /**
     * ???
     */
    CACHE_MANY
}
