/*
 * Copyright (c) 2013 Atlanmod.
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

import com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfigTest;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link BlueprintsNeo4jConfig}.
 */
@ParametersAreNonnullByDefault
class BlueprintsNeo4jConfigTest extends BlueprintsTinkerConfigTest {

    /**
     * Checks the definition of the Graph implementation, with the default type.
     */
    @Override
    public void testGraphOption() throws IOException {
        resource.save(new BlueprintsNeo4jConfig());

        ImmutableConfig config = loadConfig();
        assertConfigurationHasEntry(config, "blueprints.graph", Neo4j2Graph.class.getName());
        assertConfigurationHasSize(config, 4);
    }
}
