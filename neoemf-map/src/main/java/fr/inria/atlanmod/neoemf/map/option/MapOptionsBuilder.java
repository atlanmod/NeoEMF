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

package fr.inria.atlanmod.neoemf.map.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

public class MapOptionsBuilder extends AbstractPersistenceOptionsBuilder<MapOptionsBuilder> {

    protected MapOptionsBuilder() {
    }

    public static MapOptionsBuilder newBuilder() {
        return new MapOptionsBuilder();
    }

    public MapOptionsBuilder autocommit() {
        return storeOption(MapStoreOptions.AUTOCOMMIT);
    }

    public MapOptionsBuilder directWrite() {
        return storeOption(MapStoreOptions.DIRECT_WRITE);
    }

    public MapOptionsBuilder directWriteLists() {
        return storeOption(MapStoreOptions.DIRECT_WRITE_LISTS);
    }

    public MapOptionsBuilder directWriteIndices() {
        return storeOption(MapStoreOptions.DIRECT_WRITE_INDICES);
    }

    public MapOptionsBuilder directWriteCacheMany() {
        return storeOption(MapStoreOptions.CACHE_MANY);
    }
}
