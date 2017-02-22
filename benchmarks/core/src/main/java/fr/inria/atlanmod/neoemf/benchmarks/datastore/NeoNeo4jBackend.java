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

package fr.inria.atlanmod.neoemf.benchmarks.datastore;

import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptions;

import java.util.Map;

public class NeoNeo4jBackend extends NeoTinkerBackend {

    public static final String NAME = "neo-neo4j";

    private static final String STORE_EXTENSION = "neo4j.resource"; // -> neoemf.neo4j.resource

    public NeoNeo4jBackend() {
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
    public Map<String, Object> getOptions() {
        return BlueprintsNeo4jOptions.newBuilder()
                .autocommit()
                .asMap();
    }
}
