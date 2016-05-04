/*
 * Copyright 2016 Yoann Vernageau
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceFactoryImpl;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;

import java.util.Collections;

public class AllLoadedResourceTest extends AllSavedLoadedResourceTest {

    MapSampleFactory factory;

    @Before
    public void setUp() throws Exception {
        this.factory = MapSampleFactory.eINSTANCE;
        this.ePackage = MapSamplePackage.eINSTANCE;

        super.setUp();
        super.createPersistentStores();

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

        mapResource.save(Collections.EMPTY_MAP);
        neo4jResource.save(Collections.EMPTY_MAP);
        tinkerResource.save(Collections.EMPTY_MAP);

        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) mapResource);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) neo4jResource);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) tinkerResource);

        mapResource = null;
        neo4jResource = null;
        tinkerResource = null;

        ResourceSet rSet = new ResourceSetImpl();
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, new PersistentResourceFactoryImpl());
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, new PersistentResourceFactoryImpl());
        mapResource = (PersistentResource) rSet.getResource(NeoMapURI.createNeoMapURI(mapFile), true);
        neo4jResource = (PersistentResource) rSet.getResource(NeoBlueprintsURI.createNeoGraphURI(neo4jFile), true);
        tinkerResource = (PersistentResource) rSet.getResource(NeoBlueprintsURI.createNeoGraphURI(tinkerFile), true);

        mapResource.load(Collections.EMPTY_MAP);
        neo4jResource.load(Collections.EMPTY_MAP);
        tinkerResource.load(Collections.EMPTY_MAP);
    }

}
