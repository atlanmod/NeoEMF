/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.config;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.commons.annotation.VisibleForTesting;
import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.InvalidConfigException;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;

import java.nio.file.Path;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The base {@link fr.inria.atlanmod.neoemf.config.Config} that provides utility methods to create generic Blueprints
 * configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 *
 * @param <C> the "self"-type of this {@link fr.inria.atlanmod.neoemf.config.Config}
 */
@FactoryBinding(BlueprintsBackendFactory.class)
@ParametersAreNonnullByDefault
public class BaseBlueprintsConfig<C extends BaseBlueprintsConfig<C>> extends BaseConfig<C> {

    /**
     * The base prefix for all options related to Blueprints, whatever the implementation.
     */
    public static final String BLUEPRINTS_PREFIX = "blueprints";

    /**
     * The Blueprints option key to define the graph implementation to use.
     */
    @VisibleForTesting
    public static final String BLUEPRINTS_GRAPH = createKey(BLUEPRINTS_PREFIX, "graph");

    /**
     * Constructs a new {@code BaseBlueprintsConfig}.
     */
    protected BaseBlueprintsConfig() {
        withDefault();
    }

    /**
     * Constructs a new {@code BaseBlueprintsConfig} instance with default settings.
     * <p>
     * This method is used to create a Blueprints configuration when calling Config{@link #forName(String)} or
     * Config{@link #forScheme(String)}: a specific implementation is not necessary because all the default values
     * ​​have already been defined when they have been created.
     *
     * @return a new configuration
     */
    @Nonnull
    @VisibleForReflection
    @SuppressWarnings("unused") // Called dynamically
    public static BaseBlueprintsConfig<?> newConfig() {
        return new BaseBlueprintsConfig<>();
    }

    /**
     * Defines the given Graph implementation in this configuration.
     *
     * @param type the fully qualified name of the class of the Blueprints graph
     *
     * @return this configuration (for chaining)
     *
     * @throws InvalidConfigException if the graph is already defined and its value is different from the given {@code
     *                                type}
     */
    @Nonnull
    protected C setGraph(String type) {
        return addOption(BLUEPRINTS_GRAPH, type);
    }

    /**
     * Defines the directory of the Blueprints database.
     * <p>
     * <b>NOTE:</b> This options is intended to be used internally.
     *
     * @param directory the directory of the database
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    @VisibleForReflection
    public C setDirectory(Path directory) {
        String graph = this.<String>getOption(BLUEPRINTS_GRAPH)
                .<InvalidConfigException>orElseThrow(() -> new InvalidConfigException("The graph implementation is not defined"));

        String[] segments = graph.split("\\.");
        String prefix = segments[segments.length - 2];
        if (prefix.equals("neo4j2")) { // Remove the tailing '2'
            prefix = "neo4j";
        }

        return addOption(createKey(BLUEPRINTS_PREFIX, prefix, "directory"), directory.toString());
    }

    /**
     * Defines the mapping to use for the created {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackend}.
     * <p>
     * This mapping corresponds to a simple representation of multi-valued features, by using the {@link
     * fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean#position()}.
     * <p>
     * <b>NOTE:</b> This is the default mapping.
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    protected C withDefault() {
        return setMappingWithCheck("fr.inria.atlanmod.neoemf.data.blueprints.DefaultBlueprintsBackend", false);
    }

    @Nonnull
    @Override
    protected Predicate<String> isPersistentKey() {
        return super.isPersistentKey()
                .or(s -> s.startsWith(BLUEPRINTS_PREFIX));
    }

    @Nonnull
    @Override
    protected Predicate<String> isReadOnlyKey() {
        return super.isReadOnlyKey()
                .or(s -> s.equals(BLUEPRINTS_GRAPH));
    }
}
