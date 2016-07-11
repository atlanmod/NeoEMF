/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.map.resources;

import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

public interface MapResourceOptions extends PersistentResourceOptions {

    enum EStoreMapOption implements StoreOption {
        AUTOCOMMIT,
        CACHED_MANY,
        DIRECT_WRITE,
        DIRECT_WRITE_WITH_LISTS,
        DIRECT_WRITE_WITH_INDEXES
    }
}
