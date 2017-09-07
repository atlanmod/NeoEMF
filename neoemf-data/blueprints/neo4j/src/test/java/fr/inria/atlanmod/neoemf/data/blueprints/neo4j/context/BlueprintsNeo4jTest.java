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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j.context;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsTest;

import javax.annotation.Nonnull;

/**
 * A test-case that uses a {@link BlueprintsNeo4jContext}.
 */
public interface BlueprintsNeo4jTest extends BlueprintsTest {

    @Nonnull
    @Override
    default Context context() {
        return BlueprintsNeo4jContext.getWithIndices();
    }
}
