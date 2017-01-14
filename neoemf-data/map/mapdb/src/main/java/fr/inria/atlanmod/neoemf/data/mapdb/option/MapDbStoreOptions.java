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

package fr.inria.atlanmod.neoemf.data.mapdb.option;

import java.util.List;

import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbCacheManyStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbIndicesStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbListsStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbStore;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

/**
 * An implementation of {@link PersistentStoreOptions} holding MapDB related database access
 * features, such as autocommit, direct write, usage of raw arrays or {@link List}s.
 * 
 * @see AutocommitStoreDecorator
 * @see DirectWriteMapDbStore
 * @see DirectWriteMapDbListsStore
 * @see DirectWriteMapDbIndicesStore
 * @see DirectWriteMapDbCacheManyStore
 */
public enum MapDbStoreOptions implements PersistentStoreOptions {
    AUTOCOMMIT,
    DIRECT_WRITE,
    DIRECT_WRITE_LISTS,
    DIRECT_WRITE_INDICES,
    CACHE_MANY
}
