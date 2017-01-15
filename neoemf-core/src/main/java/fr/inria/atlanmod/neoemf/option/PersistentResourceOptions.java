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

/**
 * Represents options related to resource-level features.
 */
public interface PersistentResourceOptions {

    /**
     * The key identifying the {@link PersistentStoreOptions} list in the options {@link Map}.
     *
     * @see Resource#load(Map)
     * @see Resource#save(Map)
     */
    String STORE_OPTIONS = "stores";
}
