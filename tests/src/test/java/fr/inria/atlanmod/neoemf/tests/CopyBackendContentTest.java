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

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject;

public class CopyBackendContentTest extends AllBackendTest {

    protected MapSampleFactory factory;
    
    @Before
    public void setUp() throws Exception {
        factory = MapSampleFactory.eINSTANCE;
        this.ePackage = MapSamplePackage.eINSTANCE;
        super.setUp();
        
        super.createTransientStores();
        
        createResourceContent(mapResource);
        createResourceContent(neo4jResource);
        createResourceContent(tinkerResource);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    private void createResourceContent(PersistentResource r) {
        SampleModel model = factory.createSampleModel();
        model.setName("Model");
        SampleModelContentObject content1 = factory.createSampleModelContentObject();
        content1.setName("Content1");
        SampleModelContentObject content2 = factory.createSampleModelContentObject();
        content2.setName("Content2");
        model.getContentObjects().add(content1);
        model.getContentObjects().add(content2);
        r.getContents().add(model);
    }

    @Test
    public void testCopyBackendMapDB() throws IOException {
        mapResource.save(Collections.EMPTY_MAP);
        assert !mapResource.getContents().isEmpty() : "Map resource content is empty";
        assert mapResource.getContents().get(0) instanceof SampleModel : "Top-level element is not a SampleModel";
        SampleModel sampleModel = (SampleModel)mapResource.getContents().get(0);
        assert sampleModel.getName().equals("Model") : "SampleModel has an invalid name attribute";
        EList<SampleModelContentObject> contentObjects = sampleModel.getContentObjects();
        assert !contentObjects.isEmpty() : "SampleModel contentObjects collection is empty";
        assert contentObjects.size() == 2 : "SampleModel contentObjects collection has an invalid size";
        assert contentObjects.get(0).getName().equals("Content1") : "First element in contentObjects collection has an invalid name";
        assert contentObjects.get(1).getName().equals("Content2") : "Second element in contentObjects collection has an invalid name";
        assert contentObjects.get(0).eContainer().equals(sampleModel) : "First element in contentObjects collection has an invalid container";
        assert contentObjects.get(1).eContainer().equals(sampleModel) : "Second element in contentObjects collection has an invalid container";
    }

}
