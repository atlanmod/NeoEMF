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

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

public class BerkeleyDbOptionsBuilder extends AbstractPersistenceOptionsBuilder<BerkeleyDbOptionsBuilder, BerkeleyDbOptions> {

    protected BerkeleyDbOptionsBuilder() {
    }

    public static BerkeleyDbOptionsBuilder newBuilder() {
        return new BerkeleyDbOptionsBuilder();
    }

    public BerkeleyDbOptionsBuilder autocommit() {
        return storeOption(BerkeleyDbStoreOptions.AUTOCOMMIT);
    }

    public BerkeleyDbOptionsBuilder directWrite() {
        return storeOption(BerkeleyDbStoreOptions.DIRECT_WRITE);
    }

    public BerkeleyDbOptionsBuilder directWriteLists() {
        return storeOption(BerkeleyDbStoreOptions.DIRECT_WRITE_LISTS);
    }

    public BerkeleyDbOptionsBuilder directWriteIndices() {
        return storeOption(BerkeleyDbStoreOptions.DIRECT_WRITE_INDICES);
    }

    public BerkeleyDbOptionsBuilder directWriteCacheMany() {
        return storeOption(BerkeleyDbStoreOptions.CACHE_MANY);
    }
}
