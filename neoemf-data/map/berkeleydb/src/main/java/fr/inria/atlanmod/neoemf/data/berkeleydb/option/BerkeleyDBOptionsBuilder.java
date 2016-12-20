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

package fr.inria.atlanmod.neoemf.data.berkeleydb.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

public class BerkeleyDBOptionsBuilder extends AbstractPersistenceOptionsBuilder<BerkeleyDBOptionsBuilder, BerkeleyDBOptions> {

    protected BerkeleyDBOptionsBuilder() {
    }

    public static BerkeleyDBOptionsBuilder newBuilder() {
        return new BerkeleyDBOptionsBuilder();
    }

    public BerkeleyDBOptionsBuilder autocommit() {
        return storeOption(BerkeleyDBStoreOptions.AUTOCOMMIT);
    }

    public BerkeleyDBOptionsBuilder directWrite() {
        return storeOption(BerkeleyDBStoreOptions.DIRECT_WRITE);
    }

    public BerkeleyDBOptionsBuilder directWriteLists() {
        return storeOption(BerkeleyDBStoreOptions.DIRECT_WRITE_LISTS);
    }

    public BerkeleyDBOptionsBuilder directWriteIndices() {
        return storeOption(BerkeleyDBStoreOptions.DIRECT_WRITE_INDICES);
    }

    public BerkeleyDBOptionsBuilder directWriteCacheMany() {
        return storeOption(BerkeleyDBStoreOptions.CACHE_MANY);
    }
}
