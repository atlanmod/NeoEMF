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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j;

import fr.inria.atlanmod.neoemf.TestHelper;
import fr.inria.atlanmod.neoemf.data.blueprints.AbstractBlueprintsTestHelper;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * A specific {@link TestHelper} for the Blueprints Neo4j implementation.
 */
public class BlueprintsNeo4jTestHelper extends AbstractBlueprintsTestHelper<BlueprintsNeo4jTestHelper> {

    /**
     * Constructs a new {@code BlueprintsNeo4jTestHelper} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public BlueprintsNeo4jTestHelper(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected BlueprintsNeo4jOptionsBuilder optionsBuilder() {
        return BlueprintsNeo4jOptions.newBuilder();
    }
}