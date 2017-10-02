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

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfigTest;

import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link BlueprintsNeo4jConfig}.
 */
@ParametersAreNonnullByDefault
public class BlueprintsNeo4jConfigTest extends BlueprintsTinkerConfigTest {

    /**
     * Checks the definition of the {@link BlueprintsNeo4jConfig#GRAPH} option, with the Neo4j type.
     */
    @Test
    public void testNeo4jGraphTypeOption() throws Exception {
        resource.save(BlueprintsNeo4jConfig.newConfig().toMap());

        ImmutableConfig config = loadConfig();
        assertConfigurationHasEntry(config, BlueprintsNeo4jConfig.GRAPH, BlueprintsNeo4jConfig.GRAPH_NEO4J);
        assertConfigurationHasSize(config, 4);
    }
}
