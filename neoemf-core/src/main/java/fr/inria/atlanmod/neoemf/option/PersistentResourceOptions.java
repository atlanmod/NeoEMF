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

package fr.inria.atlanmod.neoemf.option;

import org.eclipse.emf.ecore.resource.Resource;

import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Represents options related to resource-level features.
 *
 * @see Resource#load(Map)
 * @see Resource#save(Map)
 */
@ParametersAreNonnullByDefault
public interface PersistentResourceOptions {

    /**
     * The key identifying the {@link PersistentStoreOptions} list.
     */
    String STORES = "neoemf.stores";

    /**
     * The key identifying the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.Backend}.
     */
    String MAPPING = "neoemf.mapping";

    /**
     * The option key to define the number of operations between each save in auto-save mode.
     *
     * @see PersistentStoreOptions#AUTO_SAVE
     */
    String AUTO_SAVE_CHUNK = "neoemf.store.autosave.chunk";

    /**
     * The option key to define the logging level in log mode.
     *
     * @see PersistentStoreOptions#LOG
     */
    String LOG_LEVEL = "neoemf.store.log.level";
}
