/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
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

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfigTest;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link BlueprintsNeo4jConfig}.
 */
@ParametersAreNonnullByDefault
class BlueprintsNeo4jConfigTest extends BlueprintsTinkerConfigTest {

    /**
     * Checks the definition of the {@link BlueprintsNeo4jConfig#GRAPH} option, with the Neo4j type.
     */
    @Test
    void testNeo4jGraphTypeOption() throws IOException {
        resource.save(BlueprintsNeo4jConfig.newConfig().toMap());

        ImmutableConfig config = loadConfig();
        assertConfigurationHasEntry(config, BlueprintsNeo4jConfig.GRAPH, BlueprintsNeo4jConfig.GRAPH_NEO4J);
        assertConfigurationHasSize(config, 4);
    }
}
