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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.context.BlueprintsNeo4jContext;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractDataMapperTest;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@code DefaultBlueprintsBackend} with a Neo4j implementation.
 */
@ParametersAreNonnullByDefault
class BlueprintsBackendNeo4jTest extends AbstractDataMapperTest {

    @Nonnull
    @Override
    protected Context context() {
        return BlueprintsNeo4jContext.getDefault();
    }
}
