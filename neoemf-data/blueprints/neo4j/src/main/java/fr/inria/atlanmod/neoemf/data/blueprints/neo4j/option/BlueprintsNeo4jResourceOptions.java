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

import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * {@link fr.inria.atlanmod.neoemf.option.PersistentResourceOptions} that hold Blueprints Neo4j related resource-level
 * features, such as cache type, usage of memory mapped files, or internal buffer sizes.
 */
@ParametersAreNonnullByDefault
public interface BlueprintsNeo4jResourceOptions extends BlueprintsResourceOptions {

    /**
     * The option value to define Neo4j as the graph implementation to use.
     */
    String GRAPH_TYPE_NEO4J = "com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph";

    /**
     * The base name of all native configuration keys for Neo4j.
     */
    String BASE_NAME = "blueprints.neo4j.conf.";
}
