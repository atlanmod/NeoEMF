/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.benchmarks.datastore;

import java.util.HashMap;
import java.util.Map;

import static fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resource.BlueprintsNeo4jResourceOptions.GRAPH_TYPE;
import static fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resource.BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J;

public class NeoGraphNeo4jBackend extends NeoGraphBackend {

    public static final String NAME = "neo-graph-neo4j";

    private static final String STORE_EXTENSION = "neo4j.resource"; // -> neoemf.neo4j.resource

    public NeoGraphNeo4jBackend() {
        super(NAME, STORE_EXTENSION);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getStoreExtension() {
        return STORE_EXTENSION;
    }

    @Override
    public Map<Object, Object> getOptions() {
        Map<Object, Object> options = new HashMap<>();
        options.put(GRAPH_TYPE, GRAPH_TYPE_NEO4J);
        return options;
    }
}
