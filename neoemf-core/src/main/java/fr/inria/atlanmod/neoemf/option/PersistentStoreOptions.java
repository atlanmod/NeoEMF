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

import fr.inria.atlanmod.neoemf.data.store.StatsRecordStore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * Represents options related to database access, managed by {@link fr.inria.atlanmod.neoemf.data.BackendFactory}.
 */
@ParametersAreNonnullByDefault
public enum PersistentStoreOptions {

    /**
     * Caches {@link org.eclipse.emf.ecore.EStructuralFeature}s.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.FeatureCacheStore
     */
    CACHE_FEATURE("fr.inria.atlanmod.neoemf.data.store.FeatureCacheStore"),

    /**
     * Caches the size data.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.SizeCacheStore
     */
    CACHE_SIZE("fr.inria.atlanmod.neoemf.data.store.SizeCacheStore"),

    /**
     * Caches containers.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.ContainerCacheStore
     */
    CACHE_CONTAINER("fr.inria.atlanmod.neoemf.data.store.ContainerCacheStore"),

    /**
     * Caches meta-classes.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.ClassCacheStore
     */
    CACHE_METACLASS("fr.inria.atlanmod.neoemf.data.store.ClassCacheStore"),

    /**
     * Automatically saves modifications as calls are made.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.AutoSaveStore
     */
    AUTO_SAVE("fr.inria.atlanmod.neoemf.data.store.AutoSaveStore", PersistentResourceOptions.AUTO_SAVE_CHUNK),

    /**
     * Only allows read operations.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.ReadOnlyStore
     */
    READ_ONLY("fr.inria.atlanmod.neoemf.data.store.ReadOnlyStore"),

    /**
     * Logs every call to a methods.
     *
     * @see fr.inria.atlanmod.neoemf.data.store.LogStore
     */
    LOG("fr.inria.atlanmod.neoemf.data.store.LogStore", PersistentResourceOptions.LOG_LEVEL),

    /**
     * Records several stats.
     *
     * @see StatsRecordStore
     */
    STATS("fr.inria.atlanmod.neoemf.data.store.StatsRecordStore");

    /**
     * The type of the represented {@link fr.inria.atlanmod.neoemf.data.store.Store}.
     */
    @Nonnull
    private final String className;

    /**
     * The optional parameters of this option.
     */
    @Nonnull
    private final List<String> parameters;

    /**
     * Constructs a new {@code PersistentStoreOptions} with the given {@code type}.
     *
     * @param className  the type of the represented {@link fr.inria.atlanmod.neoemf.data.store.Store}
     * @param parameters the optional parameters of this option
     */
    PersistentStoreOptions(String className, String... parameters) {
        this.className = checkNotNull(className);
        this.parameters = Arrays.stream(parameters).collect(Collectors.toList());
    }

    /**
     * Returns the class name of the represented {@link fr.inria.atlanmod.neoemf.data.store.Store}.
     *
     * @return the name of the class
     */
    @Nonnull
    public String className() {
        return className;
    }

    /**
     * Returns the optional parameters of this option.
     *
     * @return an immutable list
     */
    @Nonnull
    public List<String> parameters() {
        return parameters;
    }
}
