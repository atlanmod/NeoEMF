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

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject;
import org.junit.Before;

public class AllSavedResourceTest extends AllSavedLoadedResourceTest {

    protected MapSampleFactory factory;

    protected SampleModel mapSampleModel;
    protected SampleModelContentObject mapSampleContentObject;

    protected SampleModel neo4jSampleModel;
    protected SampleModelContentObject neo4jSampleContentObject;

    protected SampleModel tinkerSampleModel;
    protected SampleModelContentObject tinkerSampleContentObject;

    @Before
    public void setUp() throws Exception {
        this.factory = MapSampleFactory.eINSTANCE;
        this.ePackage = MapSamplePackage.eINSTANCE;
        super.setUp();
        super.createPersistentStores();

        mapSampleModel = factory.createSampleModel();
        mapSampleContentObject = factory.createSampleModelContentObject();
        mapSampleModel.getContentObjects().add(mapSampleContentObject);
        mapResource.getContents().add(mapSampleModel);

        neo4jSampleModel = factory.createSampleModel();
        neo4jSampleContentObject = factory.createSampleModelContentObject();
        neo4jSampleModel.getContentObjects().add(neo4jSampleContentObject);
        neo4jResource.getContents().add(neo4jSampleModel);

        tinkerSampleModel = factory.createSampleModel();
        tinkerSampleContentObject = factory.createSampleModelContentObject();
        tinkerSampleModel.getContentObjects().add(tinkerSampleContentObject);
        tinkerResource.getContents().add(tinkerSampleModel);
    }

}
