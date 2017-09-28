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

import fr.inria.atlanmod.neoemf.data.BackendConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptionsTest;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;

import org.junit.jupiter.api.Test;

import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link BlueprintsNeo4jOptions}.
 */
@ParametersAreNonnullByDefault
public class BlueprintsNeo4jOptionsTest extends BlueprintsOptionsTest {

    /**
     * Checks the definition of the {@link BlueprintsResourceOptions#GRAPH_TYPE} option, with the Neo4j type.
     */
    @Test
    public void testNeo4jGraphTypeOption() throws Exception {
        Map<String, Object> options = BlueprintsNeo4jOptions.noOption();

        resource.save(options);

        BackendConfig config = getConfig();
        assertConfigurationHasEntry(config, BlueprintsNeo4jResourceOptions.GRAPH_TYPE, BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
        assertConfigurationHasSize(config, 3);
    }
}
