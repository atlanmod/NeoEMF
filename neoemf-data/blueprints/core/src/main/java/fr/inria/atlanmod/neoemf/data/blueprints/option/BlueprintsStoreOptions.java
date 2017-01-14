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

import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsCacheManyStore;
import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsStore;
import fr.inria.atlanmod.neoemf.data.store.AutocommitStoreDecorator;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

/**
 * An implementation of {@link PersistentStoreOptions} holding Blueprints related database access features, such as
 * autocommit or direct write behavior.
 *
 * @see AutocommitStoreDecorator
 * @see DirectWriteBlueprintsStore
 * @see DirectWriteBlueprintsCacheManyStore
 */
public enum BlueprintsStoreOptions implements PersistentStoreOptions {
    AUTOCOMMIT,
    DIRECT_WRITE,
    CACHE_MANY
}
