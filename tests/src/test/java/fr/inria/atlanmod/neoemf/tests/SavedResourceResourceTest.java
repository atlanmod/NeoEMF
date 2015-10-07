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

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject;

public class SavedResourceResourceTest extends AllBackendTest {

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

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testEResourceMapDB() {
        assert mapSampleModel.eResource().equals(mapResource) : "Wrong eResource value";
        assert mapSampleContentObject.eResource().equals(mapResource) : "Wrong eResource value";
    }
    
    @Test
    public void testEResourceNeo4j() {
        assert neo4jSampleModel.eResource().equals(neo4jResource) : "Wrong eResource value";
        assert neo4jSampleContentObject.eResource().equals(neo4jResource) : "Wrong eResource value";
    }
    
    @Test
    public void testEResourceTinker() {
        assert tinkerSampleModel.eResource().equals(tinkerResource) : "Wrong eResource value";
        assert tinkerSampleContentObject.eResource().equals(tinkerResource) : "Wrong eResource value";
    }
    
    @Test
    public void testGetAllContentsEResourceMapDB() {
        Iterator<EObject> it = mapResource.getAllContents();
        EObject sampleModel = it.next();
        assert sampleModel.eResource().equals(mapResource) : "Wrong eResource value";
        EObject sampleContentObject = it.next();
        assert sampleContentObject.eResource().equals(mapResource) : "Wrong eResource value";
    }
    
    @Test
    public void testGetAllContentsEResourceNeo4j() {
        Iterator<EObject> it = neo4jResource.getAllContents();
        EObject sampleModel = it.next();
        assert sampleModel.eResource().equals(neo4jResource) : "Wrong eResource value";
        EObject sampleContentObject = it.next();
        assert sampleContentObject.eResource().equals(neo4jResource) : "Wrong eResource value";
    }
    
    @Test
    public void testGetAllContentsEResourceTinker() {
        Iterator<EObject> it = tinkerResource.getAllContents();
        EObject sampleModel = it.next();
        assert sampleModel.eResource().equals(tinkerResource) : "Wrong eResource value";
        EObject sampleContentObject = it.next();
        assert sampleContentObject.eResource().equals(tinkerResource) : "Wrong eResource value";
    }
    
    @Test
    public void testEDirectResourceMapDB() {
        InternalEObject internalMapSampleModel = (InternalEObject)mapSampleModel;
        assert internalMapSampleModel.eDirectResource().equals(mapResource) : "Wrong eDirectResource value";
        InternalEObject internalMapSampleContentObject = (InternalEObject)mapSampleContentObject;
        assert internalMapSampleContentObject.eDirectResource() == null : "Non top level element eDirectResource is not null";
    }
    
    @Test
    public void testEDirectResourceNeo4j() {
        InternalEObject internalMapSampleModel = (InternalEObject)neo4jSampleModel;
        assert internalMapSampleModel.eDirectResource().equals(neo4jResource) : "Wrong eDirectResource value";
        InternalEObject internalMapSampleContentObject = (InternalEObject)neo4jSampleContentObject;
        assert internalMapSampleContentObject.eDirectResource() == null : "Non top level element eDirectResource is not null";
    }
    
    @Test
    public void testEDirectResourceTinker() {
        InternalEObject internalMapSampleModel = (InternalEObject)tinkerSampleModel;
        assert internalMapSampleModel.eDirectResource().equals(tinkerResource) : "Wrong eDirectResource value";
        InternalEObject internalMapSampleContentObject = (InternalEObject)tinkerSampleContentObject;
        assert internalMapSampleContentObject.eDirectResource() == null : "Non top level element eDirectResource is not null";
    }
    
    @Test
    public void testGetAllContentsEDirectResourceMapDB() {
        Iterator<EObject> it = mapResource.getAllContents();
        InternalEObject sampleModel = (InternalEObject)it.next();
        assert sampleModel.eDirectResource().equals(mapResource) : "Wrong eDirectResource value";
        InternalEObject sampleContentObject = (InternalEObject)it.next();
        assert sampleContentObject.eDirectResource() == null : "Non top level element eDirectResource is not null";
    }
    
    @Test
    public void testGetAllContentsEDirectResourceNeo4j() {
        Iterator<EObject> it = neo4jResource.getAllContents();
        InternalEObject sampleModel = (InternalEObject)it.next();
        assert sampleModel.eDirectResource().equals(neo4jResource) : "Wrong eDirectResource value";
        InternalEObject sampleContentObject = (InternalEObject)it.next();
        assert sampleContentObject.eDirectResource() == null : "Non top level element eDirectResource is not null";
    }
    
    @Test
    public void testGetAllContentsEDirectResourceTinker() {
        Iterator<EObject> it = tinkerResource.getAllContents();
        InternalEObject sampleModel = (InternalEObject)it.next();
        assert sampleModel.eDirectResource().equals(tinkerResource) : "Wrong eDirectResource value";
        InternalEObject sampleContentObject = (InternalEObject)it.next();
        assert sampleContentObject.eDirectResource() == null : "Non top level element eDirectResource is not null";
    }

}
