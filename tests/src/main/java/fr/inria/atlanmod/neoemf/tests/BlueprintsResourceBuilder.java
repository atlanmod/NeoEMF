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

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resource.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;

public class BlueprintsResourceBuilder extends AbstractResourceBuilder {

    public BlueprintsResourceBuilder(EPackage ePackage) {
        super(ePackage);
        initBlueprintsBuilder();
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();
        initBlueprintsBuilder();
    }

    @Override
    public BlueprintsResourceBuilder uri(URI uri) {
        this.uri = NeoBlueprintsURI.createURI(uri);
        return this;
    }

    @Override
    public BlueprintsResourceBuilder file(File file) {
        this.uri = NeoBlueprintsURI.createFileURI(file);
        return this;
    }

    private void initBlueprintsBuilder() {
        if (!PersistenceBackendFactoryRegistry.isRegistered(NeoBlueprintsURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
        }
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    public BlueprintsResourceBuilder neo4j() {
        resourceOptions.put(BlueprintsNeo4jResourceOptions.GRAPH_TYPE, BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
        return this;
    }

    public BlueprintsResourceBuilder tinkerGraph() {
        resourceOptions.put(BlueprintsNeo4jResourceOptions.GRAPH_TYPE, BlueprintsNeo4jResourceOptions.GRAPH_TYPE_DEFAULT);
        return this;
    }
}
