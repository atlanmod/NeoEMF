/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 */

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BaseBlueprintsConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link BaseBlueprintsConfig} that creates Blueprints Neo4j specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@FactoryBinding(factory = BlueprintsBackendFactory.class, variant = "neo4j")
@ParametersAreNonnullByDefault
public class BlueprintsNeo4jConfig extends BaseBlueprintsConfig<BlueprintsNeo4jConfig> {

    /**
     * The base prefix for all natives options related to Neo4j under Blueprints.
     */
    static final String NEO4J_PREFIX = createKey(BLUEPRINTS_PREFIX, "neo4j");

    /**
     * The option value to define Neo4j as the graph implementation to use.
     */
    static final String BLUEPRINTS_GRAPH_NEO4J = "com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph";

    /**
     * Constructs a new {@code BlueprintsNeo4jConfig}.
     */
    protected BlueprintsNeo4jConfig() {
        setGraph(BLUEPRINTS_GRAPH_NEO4J);
    }

    /**
     * Constructs a new {@code BlueprintsNeo4jConfig} instance with default settings.
     *
     * @return a new configuration
     */
    @Nonnull
    public static BlueprintsNeo4jConfig newConfig() {
        return new BlueprintsNeo4jConfig();
    }

    /**
     * Adds a native Neo4j key/value in this configuration.
     * <p>
     * See {@code org.neo4j.graphdb.factory.GraphDatabaseSettings} for more details.
     *
     * @param key   the native key to add
     * @param value the value of the {@code key}
     *
     * @return this configuration (for chaining)
     */
    @Nonnull
    public BlueprintsNeo4jConfig addNativeOption(String key, Object value) {
        return addOption(createKey(NEO4J_PREFIX, "conf", key), value);
    }
}
