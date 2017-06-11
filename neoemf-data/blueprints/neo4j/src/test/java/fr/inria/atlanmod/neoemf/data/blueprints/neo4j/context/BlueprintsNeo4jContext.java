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
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptions;

/**
 * A specific {@link Context} for the Blueprints Neo4j implementation.
 */
public interface BlueprintsNeo4jContext extends BlueprintsContext {

    /**
     * Creates a new {@code BlueprintsContext} with a mapping with indices.
     *
     * @return a new context.
     */
    static Context getWithIndices() {
        return (BlueprintsNeo4jContext) () -> BlueprintsNeo4jOptions.builder().withIndices();
    }

    @Override
    default String name() {
        return "Neo4j";
    }
}
