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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option;

import fr.inria.atlanmod.neoemf.data.blueprints.option.AbstractBlueprintsOptions;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link AbstractBlueprintsOptions} that creates Blueprints Neo4j specific options.
 * <p>
 * This builder contains methods to set the Neo4j cache type, the low-level buffer sizes, and the use of memory mapped
 * files. The graph type is automatically set to {@link BlueprintsNeo4jResourceOptions#GRAPH_TYPE_NEO4J} in the builder
 * constructor.
 * <p>
 * All features are all optional: options can be created using all or none of them.
 *
 * @see BlueprintsNeo4jResourceOptions
 */
@ParametersAreNonnullByDefault
public class BlueprintsNeo4jOptions extends AbstractBlueprintsOptions<BlueprintsNeo4jOptions> {

    /**
     * Constructs a new {@code BlueprintsNeo4jOptions} and sets the graph type to {@link
     * BlueprintsNeo4jResourceOptions#GRAPH_TYPE_NEO4J}.
     */
    protected BlueprintsNeo4jOptions() {
        withIndices();
        graph(BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    }

    /**
     * Creates a new {@link Map} containing all default settings of {@code BlueprintsNeo4jOptions}.
     *
     * @return an immutable {@link Map}
     */
    @Nonnull
    public static Map<String, Object> noOption() {
        return builder().asMap();
    }

    /**
     * Constructs a new {@code BlueprintsNeo4jOptions} instance with default settings.
     *
     * @return a new builder
     */
    @Nonnull
    public static BlueprintsNeo4jOptions builder() {
        return new BlueprintsNeo4jOptions();
    }

    /**
     * Adds a native Neo4j key/value in the created options. See {@code org.neo4j.graphdb.factory.GraphDatabaseSettings}
     * for more details.
     *
     * @param key   the native key to add
     * @param value the value of the {@code key}
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    public BlueprintsNeo4jOptions withNativeOption(String key, Object value) {
        return withOption(BlueprintsNeo4jResourceOptions.BASE_NAME + key, value);
    }
}
