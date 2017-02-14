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

import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

import java.util.Collection;
import java.util.List;

/**
 * {@link PersistentStoreOptions} that hold MapDB related database access features, such as direct write, usage of raw
 * arrays or {@link List}s.
 */
public enum MapDbStoreOptions implements PersistentStoreOptions {

    /**
     * Translates model-level operations to MapDB calls <i>(default {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore})</i>.
     */
    @Deprecated
    DIRECT_WRITE,

    /**
     * Translates model-level operations to MapDB calls, and uses {@link List}s instead of arrays to persist
     * multi-valued {@link EAttribute}s and {@link EReference}s.
     */
    @Deprecated
    DIRECT_WRITE_LISTS,

    /**
     * Translates model-level operations to MapDB calls, and persists {@link Collection} indices instead of serialized
     * arrays.
     */
    @Deprecated
    DIRECT_WRITE_ARRAYS,

    /**
     * Translates model-level operations to Blueprints calls, and uses an internal cache to store elements that are
     * part of multi-valued {@link EReference}s to speed-up their access.
     */
    @Deprecated
    CACHE_MANY
}
