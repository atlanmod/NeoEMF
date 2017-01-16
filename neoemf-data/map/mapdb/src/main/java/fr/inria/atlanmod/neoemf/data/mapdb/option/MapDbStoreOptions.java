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

import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbCacheManyStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbIndicesStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbListsStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbStore;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

import java.util.Collection;
import java.util.List;

/**
 * {@link PersistentStoreOptions} that hold MapDB related database access features, such as autocommit, direct write,
 * usage of raw arrays or {@link List}s.
 *
 * @see AutocommitStoreDecorator
 * @see DirectWriteMapDbStore
 * @see DirectWriteMapDbListsStore
 * @see DirectWriteMapDbIndicesStore
 * @see DirectWriteMapDbCacheManyStore
 */
public enum MapDbStoreOptions implements PersistentStoreOptions {

    /**
     * Automatically saves modifications as calls are made.
     *
     * @see AutocommitStoreDecorator
     */
    AUTOCOMMIT,

    /**
     * Translates model-level operations to MapDB calls <i>(default {@link DirectWriteStore})</i>.
     *
     * @see DirectWriteMapDbStore
     */
    DIRECT_WRITE,

    /**
     * Translates model-level operations to MapDB calls, and uses {@link List}s instead of arrays to persist
     * multi-valued {@link EAttribute}s and {@link EReference}s.
     *
     * @see DirectWriteMapDbListsStore
     */
    DIRECT_WRITE_LISTS,

    /**
     * Translates model-level operations to MapDB calls, and persists {@link Collection} indices instead of serialized
     * arrays.
     *
     * @see DirectWriteMapDbIndicesStore
     */
    DIRECT_WRITE_INDICES,

    /**
     * Translates model-level operations to Blueprints calls, and uses an internal cache to store elements that are
     * part of multi-valued {@link EReference}s to speed-up their access.
     *
     * @see DirectWriteMapDbCacheManyStore
     */
    CACHE_MANY
}
