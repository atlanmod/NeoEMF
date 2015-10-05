/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.test.commons;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resources.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceFactoryImpl;

public class BlueprintsResourceBuilder extends AbstractResourceBuilder {
    
    public BlueprintsResourceBuilder(EPackage ePackage) {
        super(ePackage);
        if(!PersistenceBackendFactoryRegistry.getFactories().containsKey(NeoBlueprintsURI.NEO_GRAPH_SCHEME)) {
            PersistenceBackendFactoryRegistry.getFactories().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, new BlueprintsPersistenceBackendFactory());
        }
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, new PersistentResourceFactoryImpl());
    }
    
    @Override
    public BlueprintsResourceBuilder uri(URI uri) {
        this.uri = NeoBlueprintsURI.createNeoGraphURI(uri);
        return this;
    }
    
    @Override
    public BlueprintsResourceBuilder file(File file) {
        this.uri = NeoBlueprintsURI.createNeoGraphURI(file);
        return this;
    }
    
    public BlueprintsResourceBuilder neo4j() {
        resourceOptions.put(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE, BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_TYPE_NEO4J);
        return this;
    }
    
    public BlueprintsResourceBuilder tinkerGraph() {
        resourceOptions.put(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE, BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE_DEFAULT);
        return this;
    }
    
}
