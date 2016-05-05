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
