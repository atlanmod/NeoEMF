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

import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

/**
 * {@link PersistentStoreOptions} that hold Blueprints related database access features, such as direct write behavior.
 */
public enum BlueprintsStoreOptions implements PersistentStoreOptions {

    /**
     * Translates model-level operations to Blueprints calls <i>(default {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore})</i>.
     */
    @Deprecated
    DIRECT_WRITE,

    /**
     * Translates model-level operations to Blueprints calls, and uses an internal cache to store elements that are
     * part of multi-valued {@link org.eclipse.emf.ecore.EReference}s to speed-up their access.
     */
    @Deprecated
    CACHE_MANY
}
