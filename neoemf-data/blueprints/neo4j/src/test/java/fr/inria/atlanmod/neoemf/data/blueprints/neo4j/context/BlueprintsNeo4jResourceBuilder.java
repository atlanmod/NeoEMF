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

import fr.inria.atlanmod.neoemf.context.ResourceBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.AbstractBlueprintsResourceBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * A specific {@link ResourceBuilder} for the Blueprints Neo4j implementation.
 */
public class BlueprintsNeo4jResourceBuilder extends AbstractBlueprintsResourceBuilder<BlueprintsNeo4jResourceBuilder> {

    /**
     * Constructs a new {@code BlueprintsNeo4jResourceBuilder} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public BlueprintsNeo4jResourceBuilder(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected void registerFactory() {
        resourceOptions = BlueprintsNeo4jOptionsBuilder.newBuilder().asMap();
    }
}
