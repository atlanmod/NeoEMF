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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config;

import fr.inria.atlanmod.neoemf.data.blueprints.config.BaseBlueprintsConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link BaseBlueprintsConfig} that creates Blueprints Neo4j specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@ParametersAreNonnullByDefault
public class BlueprintsNeo4jConfig extends BaseBlueprintsConfig<BlueprintsNeo4jConfig> {

    /**
     * The base prefix for all natives options related to Neo4j under Blueprints.
     */
    static final String NEO4J_PREFIX = createKey(BLUEPRINTS_PREFIX, "neo4j");

    /**
     * The option value to define Neo4j as the graph implementation to use.
     */
    static final String GRAPH_NEO4J = "com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph";

    /**
     * Constructs a new {@code BlueprintsNeo4jConfig}.
     */
    protected BlueprintsNeo4jConfig() {
        setGraph(GRAPH_NEO4J);
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
     * Adds a native Neo4j key/value in this configuration. See {@code org.neo4j.graphdb.factory.GraphDatabaseSettings}
     * for more details.
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
