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
 * Represents common options related to database access, managed by
 * {@link fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory}.
 */
public enum CommonStoreOptions implements PersistentStoreOptions {
    /**
     * Caches the presence of a value.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.IsSetCachingStoreDecorator
     */
    CACHE_IS_SET,
    /**
     * Caches the size data.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.SizeCachingStoreDecorator
     */
    CACHE_SIZE,
    /**
     * Caches {@link org.eclipse.emf.ecore.EStructuralFeature}.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.FeatureCachingStoreDecorator
     */
    CACHE_STRUCTURAL_FEATURE,
    /**
     * Logs every call to a methods.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.LoggingStoreDecorator
     */
    LOG,
    /**
     * @see fr.inria.atlanmod.neoemf.data.store.LoadedObjectCounterStoreDecorator
     */
    COUNT_LOADED_OBJECT
}
