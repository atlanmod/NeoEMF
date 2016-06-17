/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;

import java.util.Collections;

public abstract class AllLoadedResourceTest extends AllSavedLoadedResourceTest {

    MapSampleFactory factory;

    @Override
    @Before
    public void setUp() throws Exception {
        this.factory = MapSampleFactory.eINSTANCE;
        this.ePackage = MapSamplePackage.eINSTANCE;

        super.setUp();
        createPersistentStores();

        SampleModel mapSampleModel = factory.createSampleModel();
        SampleModelContentObject mapSampleContentObject = factory.createSampleModelContentObject();
        mapSampleModel.getContentObjects().add(mapSampleContentObject);
        mapResource.getContents().add(mapSampleModel);

        SampleModel neo4jSampleModel = factory.createSampleModel();
        SampleModelContentObject neo4jSampleContentObject = factory.createSampleModelContentObject();
        neo4jSampleModel.getContentObjects().add(neo4jSampleContentObject);
        neo4jResource.getContents().add(neo4jSampleModel);

        SampleModel tinkerSampleModel = factory.createSampleModel();
        SampleModelContentObject tinkerSampleContentObject = factory.createSampleModelContentObject();
        tinkerSampleModel.getContentObjects().add(tinkerSampleContentObject);
        tinkerResource.getContents().add(tinkerSampleModel);

        mapResource.save(Collections.emptyMap());
        neo4jResource.save(Collections.emptyMap());
        tinkerResource.save(Collections.emptyMap());

        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) mapResource);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) neo4jResource);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) tinkerResource);

        mapResource = null;
        neo4jResource = null;
        tinkerResource = null;

        ResourceSet rSet = new ResourceSetImpl();
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.eINSTANCE);
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.eINSTANCE);
        mapResource = (PersistentResource) rSet.getResource(NeoMapURI.createNeoMapURI(mapFile), true);
        neo4jResource = (PersistentResource) rSet.getResource(NeoBlueprintsURI.createNeoGraphURI(neo4jFile), true);
        tinkerResource = (PersistentResource) rSet.getResource(NeoBlueprintsURI.createNeoGraphURI(tinkerFile), true);

        mapResource.load(Collections.emptyMap());
        neo4jResource.load(Collections.emptyMap());
        tinkerResource.load(Collections.emptyMap());
    }

}
