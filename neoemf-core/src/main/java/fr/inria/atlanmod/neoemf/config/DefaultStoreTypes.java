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

package fr.inria.atlanmod.neoemf.config;

import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.neoemf.data.store.Store;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * Constants definitions for the default {@link ConfigType} representing {@link fr.inria.atlanmod.neoemf.data.store.Store}s.
 */
@Immutable
@ParametersAreNonnullByDefault
interface DefaultStoreTypes {

    /**
     * Caches {@link org.eclipse.emf.ecore.EStructuralFeature}s.
     */
    ConfigType<Store> CACHE_FEATURE = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.FeatureCacheStore", 60);

    /**
     * Caches the size data.
     */
    ConfigType<Store> CACHE_SIZE = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.SizeCacheStore", 60);

    /**
     * Caches containers.
     */
    ConfigType<Store> CACHE_CONTAINER = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.ContainerCacheStore", 70);

    /**
     * Caches meta-classes.
     */
    ConfigType<Store> CACHE_METACLASS = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.ClassCacheStore", 70);

    /**
     * Automatically saves modifications as calls are made.
     */
    ConfigType<Store> AUTOSAVE = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.AutoSaveStore", 80,
            new ConfigParameter<>(BaseConfig.STORE_AUTOSAVE_CHUNK, Long.class));

    /**
     * Only allows read operations.
     */
    ConfigType<Store> READONLY = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.ReadOnlyStore", 90);

    /**
     * Logs every call to a methods.
     */
    ConfigType<Store> LOG = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.LogStore", 100,
            new ConfigParameter<>(BaseConfig.STORE_LOG_LEVEL, Level.class));

    /**
     * Records several stats.
     */
    ConfigType<Store> STATS = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.StatsRecordStore", 100);
}
