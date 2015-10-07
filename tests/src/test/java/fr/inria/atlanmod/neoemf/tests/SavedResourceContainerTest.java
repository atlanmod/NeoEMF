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

public class SavedResourceContainerTest extends AllBackendTest {

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

//    @Test
//    public void testEContainerMapDB() {
//        assert mapSampleModel.eContainer() == null : "Top Level EObject has a not null container";
//        assert mapSampleContentObject.eContainer().equals(mapSampleModel) : "Wrong eContainer value";
//    }
//    
//    @Test
//    public void testEContainerNeo4j() {
//        assert neo4jSampleModel.eContainer() == null : "Top Level EObject has a not null container";
//        assert neo4jSampleContentObject.eContainer().equals(neo4jSampleModel) : "Wrong eContainer value";
//    }
//    
//    @Test
//    public void testEContainerTinker() {
//        assert tinkerSampleModel.eContainer() == null : "Top Level EObject has a not null container";
//        assert tinkerSampleContentObject.eContainer().equals(tinkerSampleModel) : "Wrong eContainer value";
//    }
//    
//    @Test
//    public void testGetAllContentsContainerMapDB() {
//        Iterator<EObject> it = mapResource.getAllContents();
//        EObject sampleModel = it.next();
//        assert sampleModel.eContainer() == null : "Top Level EObject has a not null container";
//        EObject sampleContentObject = it.next();
//        assert sampleContentObject.eContainer().equals(sampleModel) : "Wrong eContainer value";
//    }
//    
//    @Test
//    public void testGetAllContentsContainerNeo4j() {
//        Iterator<EObject> it = neo4jResource.getAllContents();
//        EObject sampleModel = it.next();
//        assert sampleModel.eContainer() == null : "Top Level EObject has a not null container";
//        EObject sampleContentObject = it.next();
//        assert sampleContentObject.eContainer().equals(sampleModel) : "Wrong eContainer value";
//    }
//    
//    @Test
//    public void testGetAllContentsContainerTinker() {
//        Iterator<EObject> it = tinkerResource.getAllContents();
//        EObject sampleModel = it.next();
//        assert sampleModel.eContainer() == null : "Top Level EObject has a not null container";
//        EObject sampleContentObject = it.next();
//        assert sampleContentObject.eContainer().equals(sampleModel) : "Wrong eContainer value";
//    }
//    
//    @Test
//    public void testEInternalContainerMapDB() {
//        InternalEObject internalMapSampleModel = (InternalEObject)mapSampleModel;
//        assert internalMapSampleModel.eInternalContainer() == null : "Top Level EObject has a not null internal container";
//        InternalEObject internalMapSampleContentObject = (InternalEObject)mapSampleContentObject;
//        assert internalMapSampleContentObject.eInternalContainer().equals(internalMapSampleModel) : "Wrong eInternalContainer value";
//    }
//    
//    @Test
//    public void testEInternalContainerNeo4j() {
//        InternalEObject internalNeo4jSampleModel = (InternalEObject)neo4jSampleModel;
//        assert internalNeo4jSampleModel.eInternalContainer() == null : "Top Level EObject has a not null internal container";
//        InternalEObject internalNeo4jSampleContentObject = (InternalEObject)neo4jSampleContentObject;
//        assert internalNeo4jSampleContentObject.eInternalContainer().equals(internalNeo4jSampleModel) : "Wrong eInternalContainer value";
//    }
//    
//    @Test
//    public void testEInternalContainerTinker() {
//        InternalEObject internalTinkerSampleModel = (InternalEObject)tinkerSampleModel;
//        assert internalTinkerSampleModel.eInternalContainer() == null : "Top Level EObject has a not null internal container";
//        InternalEObject internalTinkerSampleContentObject = (InternalEObject)tinkerSampleContentObject;
//        assert internalTinkerSampleContentObject.eInternalContainer().equals(internalTinkerSampleModel) : "Wrong eInternalContainer value";
//    }
    
    @Test
    public void testGetAllContentsEInternalContainerMapDB() {
        Iterator<EObject> it = mapResource.getAllContents();
        InternalEObject sampleModel = (InternalEObject)it.next();
        assert sampleModel.eInternalContainer() == null : "Top Level EObject has a not null container";
        InternalEObject sampleContentObject = (InternalEObject)it.next();
        assert sampleContentObject.eInternalContainer().equals(sampleModel) : "Wrong eInternalContainer value";
    }
    
//    @Test
//    public void testGetAllContentsEInternalContainerNeo4j() {
//        Iterator<EObject> it = neo4jResource.getAllContents();
//        InternalEObject sampleModel = (InternalEObject)it.next();
//        assert sampleModel.eInternalContainer() == null : "Top Level EObject has a not null container";
//        InternalEObject sampleContentObject = (InternalEObject)it.next();
//        assert sampleContentObject.eInternalContainer().equals(sampleModel) : "Wrong eInternalContainer value";
//    }
//    
//    @Test
//    public void testGetAllContentsEInternalContainerTinker() {
//        Iterator<EObject> it = tinkerResource.getAllContents();
//        InternalEObject sampleModel = (InternalEObject)it.next();
//        assert sampleModel.eInternalContainer() == null : "Top Level EObject has a not null container";
//        InternalEObject sampleContentObject = (InternalEObject)it.next();
//        assert sampleContentObject.eInternalContainer().equals(sampleModel) : "Wrong eInternalContainer value";
//    }

}
