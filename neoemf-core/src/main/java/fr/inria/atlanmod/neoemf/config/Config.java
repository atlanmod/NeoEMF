/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.config;

import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.listener.StoreListener;
import fr.inria.atlanmod.neoemf.data.store.listener.StoreStats;

import org.atlanmod.commons.log.Level;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A configuration that builds the behavior of {@link fr.inria.atlanmod.neoemf.data.BackendFactory BackendFactories} and
 * {@link fr.inria.atlanmod.neoemf.data.store.StoreFactory}.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 *
 * @see org.eclipse.emf.ecore.resource.Resource#load(Map)
 * @see org.eclipse.emf.ecore.resource.Resource#save(Map)
 */
@ParametersAreNonnullByDefault
public interface Config extends ImmutableConfig {

    /**
     * Retrieves the instance of {@code Config} that is associated to a {@link fr.inria.atlanmod.neoemf.data.BackendFactory}
     * wearing the specified {@code name}.
     *
     * @param name    the name of the factory
     * @param variant the variant of the binding
     * @param <C>     the type of the configuration
     *
     * @return a new instance of {@code Config}
     */
    @Nonnull
    static <C extends Config> C forName(String name, @Nullable String variant) {
        return Bindings.find(Config.class, Bindings::nameOf, name, variant);
    }

    /**
     * Retrieves the instance of {@code Config} that is associated to a {@link fr.inria.atlanmod.neoemf.util.UriFactory}
     * which use the specified {@code scheme}.
     *
     * @param scheme the scheme of the factory
     * @param <C>    the type of the configuration
     *
     * @return a new instance of {@code Config}
     */
    @Nonnull
    static <C extends Config> C forScheme(String scheme) {
        return Bindings.find(Config.class, Bindings::schemeOf, scheme, null);
    }

    /**
     * Loads a configuration from the {@code directory}.
     *
     * @param directory the directory of the configuration to load
     * @param <C>       the type of the configuration
     *
     * @return an {@link Optional} containing the configuration, or {@link Optional#empty()} if the {@code directory}
     * does not contains any configuration
     *
     * @throws IOException if an I/O error occurs during the saving
     * @see ConfigFile#exists(Path)
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    static <C extends Config> Optional<C> load(Path directory) throws IOException {
        if (ConfigFile.exists(directory)) {
            ConfigFile configFile = ConfigFile.load(directory);
            C config = (C) forName(configFile.get(BaseConfig.BACKEND_TYPE), configFile.get(BaseConfig.BACKEND_VARIANT)).merge(configFile.toMap());
            return Optional.of(config);
        }

        return Optional.empty();
    }

    /**
     * Checks that a configuration file exists in the {@code directory}.
     *
     * @param directory the directory where to look for a configuration
     *
     * @return {@code true} if the {@code directory} contains a configuration file
     */
    static boolean exists(Path directory) {
        return ConfigFile.exists(directory);
    }

    /**
     * Merges the given {@code map} in this configuration. All options defined in the {@code map} will be added to this
     * configuration, with conflict checking.
     *
     * @param map the map representation of this configuration
     *
     * @return this configuration (for chaining)
     *
     * @see #toMap()
     */
    @Nonnull
    Config merge(Map<String, ?> map);

    /**
     * Merges the given {@code config} in this configuration. All options defined in the {@code config} will be added to
     * this configuration, with conflict checking.
     *
     * @param config the configuration to merge
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config merge(ImmutableConfig config);

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.Backend}.
     *
     * @param mapping the class name of the mapping to use
     *
     * @return this configuration (for chaining)
     *
     * @throws NullPointerException   if the {@code mapping} is {@code null}
     * @throws InvalidConfigException if the {@code mapping} does not represent a {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper}
     *                                instance, if the associated class cannot be found or if the mapping is already
     *                                defined and different from {@code mapping}
     */
    @Nonnull
    Config setMapping(String mapping);

    /**
     * Adds a key/value in this configuration. A custom configuration, which is not part of NeoEMF, can be added.
     *
     * @param key   the key to add
     * @param value the value of the {@code key}
     * @param <V>   the type of the value
     *
     * @return this configuration (for chaining)
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    <V> Config addOption(String key, V value);

    // region Stores

    /**
     * Adds a feature defined by the given {@code store} in this configuration.
     *
     * @param store the store declaration to add
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config addStore(Store store);

    /**
     * Adds the {@code cache-features} feature in this configuration.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config cacheFeatures();

    /**
     * Adds the {@code cache-containers} feature in this configuration.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config cacheContainers();

    /**
     * Adds the {@code cache-metaclasses} feature in this configuration.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config cacheMetaClasses();

    /**
     * Adds the {@code cache-sizes} feature in this configuration.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config cacheSizes();

    /**
     * Adds the {@code read-only} feature in this configuration.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config readOnly();

    /**
     * Adds the {@code autoSave} feature in this configuration.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config autoSave();

    /**
     * Adds the {@code autoSave} feature, with a defined {@code chunk}, in this configuration.
     * <p>
     * <b>WARNING:</b> When {@code chunk} is zero, the store will be saved at each call.
     *
     * @param chunk the number of database operations between each save
     *
     * @return this configuration (for chaining)
     *
     * @throws InvalidConfigException if the {@code chunk} is {@code &lt; 0}
     */
    @Nonnull
    Config autoSave(@Nonnegative long chunk);

    // endregion

    // region Listeners

    /**
     * Adds a store listener in this configuration.
     *
     * @param listener the sotre listener to add
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config addListener(StoreListener listener);

    /**
     * Adds the {@code log} feature in this configuration.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config log();

    /**
     * Adds the {@code log} feature, with a defined {@code level}, in this configuration.
     *
     * @param level the logging {@link org.atlanmod.commons.log.Level} to use
     *
     * @return this configuration (for chaining)
     *
     * @throws NullPointerException if the {@code level} is {@code null}
     */
    @Nonnull
    Config log(Level level);

    /**
     * Adds the {@code stats} feature in this configuration.
     *
     * @param stats the stats that will be updated
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    Config recordStats(StoreStats stats);

    // endregion
}
