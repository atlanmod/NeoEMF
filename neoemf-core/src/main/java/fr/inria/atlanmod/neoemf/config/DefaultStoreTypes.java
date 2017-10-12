/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.config;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.neoemf.data.store.Store;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Constants definitions for the default {@link ConfigType} representing {@link fr.inria.atlanmod.neoemf.data.store.Store}s.
 */
@Static
@ParametersAreNonnullByDefault
final class DefaultStoreTypes {

    /**
     * Caches {@link org.eclipse.emf.ecore.EStructuralFeature}s.
     */
    static final ConfigType<Store> CACHE_FEATURE = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.FeatureCacheStore", 60);

    /**
     * Caches the size data.
     */
    static final ConfigType<Store> CACHE_SIZE = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.SizeCacheStore", 60);

    /**
     * Caches containers.
     */
    static final ConfigType<Store> CACHE_CONTAINER = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.ContainerCacheStore", 70);

    /**
     * Caches meta-classes.
     */
    static final ConfigType<Store> CACHE_METACLASS = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.ClassCacheStore", 70);

    /**
     * Logs every call to a methods.
     */
    static final ConfigType<Store> LOG = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.LogStore", 90,
            new ConfigParameter<>(BaseConfig.STORE_LOG_LEVEL, Level.class));

    /**
     * Records several stats.
     */
    static final ConfigType<Store> STATS = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.StatsRecordStore", 90);

    /**
     * Automatically saves modifications as calls are made.
     */
    static final ConfigType<Store> AUTOSAVE = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.AutoSaveStore", 100,
            new ConfigParameter<>(BaseConfig.STORE_AUTOSAVE_CHUNK, Long.class));

    /**
     * Only allows read operations.
     */
    static final ConfigType<Store> READONLY = new ConfigType<>("fr.inria.atlanmod.neoemf.data.store.ReadOnlyStore", 100);

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private DefaultStoreTypes() {
        throw new IllegalStateException("This class should not be instantiated");
    }
}
