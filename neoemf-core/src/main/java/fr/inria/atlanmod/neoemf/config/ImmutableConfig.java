/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.config;

import fr.inria.atlanmod.neoemf.data.store.Store;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An immutable configuration that defines the behavior of {@link fr.inria.atlanmod.neoemf.data.BackendFactory
 * BackendFactories} and {@link fr.inria.atlanmod.neoemf.data.store.StoreFactory}.
 */
@ParametersAreNonnullByDefault
public interface ImmutableConfig {

    /**
     * Saves this configuration in the {@code directory}.
     *
     * @param directory the directory where to store the configuration
     *
     * @throws IOException if an I/O error occurs during the saving
     */
    void save(Path directory) throws IOException;

    /**
     * Returns a immutable map view of this configuration.
     *
     * @return an immutable map
     *
     * @see fr.inria.atlanmod.neoemf.resource.PersistentResource#load(Map)
     * @see fr.inria.atlanmod.neoemf.resource.PersistentResource#save(Map)
     */
    @Nonnull
    Map<String, ?> toMap();

    /**
     * Returns the name of the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} associated with this configuration.
     *
     * @return the name
     *
     * @see fr.inria.atlanmod.neoemf.data.BackendFactory#name()
     */
    @Nonnull
    String getName();

    /**
     * Returns the defined mapping.
     *
     * @return the class name of the mapping
     *
     * @throws InvalidConfigException if the mapping is not defined
     */
    @Nonnull
    String getMapping();

    /**
     * Returns {@code true} if the {@code key} is defined.
     *
     * @param key the key to check the declaration
     *
     * @return {@code true} if the {@code key} is defined
     */
    default boolean hasOption(String key) {
        return getOptions().containsKey(key);
    }

    /**
     * Returns the value of the {@code key}.
     *
     * @param key the key of the value
     *
     * @return a {@link Optional} containing the value, or {@link Optional#empty()} if the {@code key} is not defined
     */
    @SuppressWarnings("unchecked")
    default <T> Optional<T> getOption(String key) {
        return Optional.ofNullable((T) getOptions().get(key));
    }

    /**
     * Returns a sub-map of all defined options filtered with the given {@code keyFilter}.
     *
     * @param keyFilter the predicate to filter the options
     *
     * @return an immutable map of the options that match the {@code keyFilter}
     */
    @Nonnull
    default Map<String, ?> getOptions(Predicate<String> keyFilter) {
        Map<String, Object> subMap = getOptions().entrySet().stream()
                .filter(e -> keyFilter.test(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return Collections.unmodifiableMap(subMap);
    }

    /**
     * Returns all defined options.
     *
     * @return an immutable map
     */
    @Nonnull
    Map<String, ?> getOptions();

    /**
     * Checks that the {@code storeType} is defined in this configuration.
     *
     * @param storeType the store declaration to check the presence
     *
     * @return {@code true} if the {@code storeType} is defined
     */
    default boolean hasStoreType(ConfigType<? extends Store> storeType) {
        return getStoreTypes().contains(storeType);
    }

    /**
     * Returns a set of all defined {@link ConfigType}.
     *
     * @return an immutable collection of stores declaration
     */
    @Nonnull
    Set<ConfigType<? extends Store>> getStoreTypes();

    /**
     * Returns {@code true} if the "read-only" mode is defined.
     *
     * @return {@code true} if the "read-only" mode is defined
     */
    default boolean isReadOnly() {
        return hasStoreType(DefaultStoreTypes.READONLY);
    }
}
