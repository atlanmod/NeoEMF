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

import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;

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

        mapResource.save(PersistenceOptionsBuilder.newBuilder().asMap());
        neo4jResource.save(PersistenceOptionsBuilder.newBuilder().asMap());
        tinkerResource.save(PersistenceOptionsBuilder.newBuilder().asMap());

        mapResource.close();
        neo4jResource.close();
        tinkerResource.close();

        mapResource = null;
        neo4jResource = null;
        tinkerResource = null;

        ResourceSet rSet = new ResourceSetImpl();
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.SCHEME, PersistentResourceFactory.getInstance());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
        mapResource = (PersistentResource) rSet.getResource(NeoMapURI.createFileURI(mapFile), true);
        neo4jResource = (PersistentResource) rSet.getResource(NeoBlueprintsURI.createFileURI(neo4jFile), true);
        tinkerResource = (PersistentResource) rSet.getResource(NeoBlueprintsURI.createFileURI(tinkerFile), true);

        mapResource.load(PersistenceOptionsBuilder.newBuilder().asMap());
        neo4jResource.load(PersistenceOptionsBuilder.newBuilder().asMap());
        tinkerResource.load(PersistenceOptionsBuilder.newBuilder().asMap());
    }
}
