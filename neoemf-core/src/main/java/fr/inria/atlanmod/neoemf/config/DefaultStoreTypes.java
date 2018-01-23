/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.config;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.data.store.Store;

import javax.annotation.Nonnull;
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
    @Nonnull
    public static final ConfigType<Store> CACHE_FEATURE = new ConfigType<>(
            "fr.inria.atlanmod.neoemf.data.store.FeatureCacheStore",
            60
    );

    /**
     * Caches the size data.
     */
    @Nonnull
    public static final ConfigType<Store> CACHE_SIZE = new ConfigType<>(
            "fr.inria.atlanmod.neoemf.data.store.SizeCacheStore",
            60
    );

    /**
     * Caches containers.
     */
    @Nonnull
    public static final ConfigType<Store> CACHE_CONTAINER = new ConfigType<>(
            "fr.inria.atlanmod.neoemf.data.store.ContainerCacheStore",
            70
    );

    /**
     * Caches meta-classes.
     */
    @Nonnull
    public static final ConfigType<Store> CACHE_METACLASS = new ConfigType<>(
            "fr.inria.atlanmod.neoemf.data.store.ClassCacheStore",
            70
    );

    /**
     * Logs every call to a methods.
     */
    @Nonnull
    public static final ConfigType<Store> LOG = new ConfigType<>(
            "fr.inria.atlanmod.neoemf.data.store.LogStore",
            90,
            BaseConfig.STORE_LOG_LEVEL
    );

    /**
     * Records several stats.
     */
    @Nonnull
    public static final ConfigType<Store> STATS = new ConfigType<>(
            "fr.inria.atlanmod.neoemf.data.store.StatsRecordStore",
            90
    );

    /**
     * Automatically saves modifications as calls are made.
     */
    @Nonnull
    public static final ConfigType<Store> AUTOSAVE = new ConfigType<>(
            "fr.inria.atlanmod.neoemf.data.store.AutoSaveStore",
            100,
            BaseConfig.STORE_AUTOSAVE_CHUNK
    );

    /**
     * Only allows read operations.
     */
    @Nonnull
    public static final ConfigType<Store> READONLY = new ConfigType<>(
            "fr.inria.atlanmod.neoemf.data.store.ReadOnlyStore",
            100
    );

    private DefaultStoreTypes() {
        throw Throwables.notInstantiableClass(getClass());
    }
}
