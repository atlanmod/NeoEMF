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

import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsResourceBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jResourceOptions;

import org.eclipse.emf.ecore.EPackage;

public class BlueprintsNeo4jResourceBuilder extends BlueprintsResourceBuilder {

    public BlueprintsNeo4jResourceBuilder(EPackage ePackage) {
        super(ePackage);
    }

    public BlueprintsNeo4jResourceBuilder neo4j() {
        resourceOptions.put(BlueprintsNeo4jResourceOptions.GRAPH_TYPE, BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
        return this;
    }
}
