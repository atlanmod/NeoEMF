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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsTest;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.context.BlueprintsNeo4jContext;

public interface BlueprintsNeo4jTest extends BlueprintsTest {

    @Override
    default Context context() {
        return BlueprintsNeo4jContext.get();
    }
}
