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

/**
 * {@link PersistentStoreOptions} that are available for all modules.
 */
public enum CommonStoreOptions implements PersistentStoreOptions {
    CACHE_IS_SET,
    CACHE_SIZE,
    CACHE_STRUCTURAL_FEATURE,
    LOG,
    COUNT_LOADED_OBJECT
}
