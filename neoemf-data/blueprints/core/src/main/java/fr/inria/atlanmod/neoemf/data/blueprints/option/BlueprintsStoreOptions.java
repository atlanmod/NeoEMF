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

package fr.inria.atlanmod.neoemf.data.blueprints.option;

import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsCacheManyStore;
import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsStore;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

import org.eclipse.emf.ecore.EReference;

/**
 * {@link PersistentStoreOptions} that hold Blueprints related database access features, such as autocommit or direct
 * write behavior.
 *
 * @see AutocommitStoreDecorator
 * @see DirectWriteBlueprintsStore
 * @see DirectWriteBlueprintsCacheManyStore
 */
public enum BlueprintsStoreOptions implements PersistentStoreOptions {

    /**
     * Automatically saves modifications as calls are made.
     *
     * @see AutocommitStoreDecorator
     */
    AUTOCOMMIT,

    /**
     * Translates model-level operations to Blueprints calls <i>(default {@link DirectWriteStore})</i>.
     *
     * @see DirectWriteBlueprintsStore
     */
    DIRECT_WRITE,

    /**
     * Translates model-level operations to Blueprints calls, and uses an internal cache to store elements that are
     * part of multi-valued {@link EReference}s to speed-up their access.
     *
     * @see DirectWriteBlueprintsCacheManyStore
     */
    CACHE_MANY
}
