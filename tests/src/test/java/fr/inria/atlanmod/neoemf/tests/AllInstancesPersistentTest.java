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
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;

public class AllInstancesPersistentTest extends AllInstancesTest {
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        super.createPersistentStores();
        createResourceContent(mapResource);
        createResourceContent(neo4jResource);
        createResourceContent(tinkerResource);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testAllInstancesPersistentMapDB() {
        EList<EObject> allPacks = mapResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack());
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = mapResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent());
        assert allAbstractPackContents.size() == abstractPackContentCount : "Invalid count : expected "
                + abstractPackContentCount + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = mapResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent());
        assert allPackContents.size() == packContentCount : "Invalid count : expected "
                + packContentCount + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = mapResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent());
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = mapResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2());
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesStricPersistentMapDB() {
        EList<EObject> allPacks = mapResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack(),
                true);
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = mapResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent(), true);
        assert allAbstractPackContents.size() == 0 : "Invalid count : expected "
                + 0 + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = mapResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent(), true);
        assert allPackContents.size() == 50 : "Invalid count : expected "
                + 50 + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = mapResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent(), true);
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = mapResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2(), true);
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesPersistentNeo4j() {
        EList<EObject> allPacks = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack());
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = neo4jResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent());
        assert allAbstractPackContents.size() == abstractPackContentCount : "Invalid count : expected "
                + abstractPackContentCount + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent());
        assert allPackContents.size() == packContentCount : "Invalid count : expected "
                + packContentCount + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = neo4jResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent());
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2());
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesStrictPersistentNeo4j() {
        EList<EObject> allPacks = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack(),
                true);
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = neo4jResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent(), true);
        assert allAbstractPackContents.size() == 0 : "Invalid count : expected "
                + 0 + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent(), true);
        assert allPackContents.size() == 50 : "Invalid count : expected "
                + 50 + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = neo4jResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent(), true);
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2(), true);
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesPersistentTinker() {
        EList<EObject> allPacks = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack());
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = tinkerResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent());
        assert allAbstractPackContents.size() == abstractPackContentCount : "Invalid count : expected "
                + abstractPackContentCount + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent());
        assert allPackContents.size() == packContentCount : "Invalid count : expected "
                + packContentCount + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = tinkerResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent());
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2());
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesStrictPersistentTinker() {
        EList<EObject> allPacks = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack(),
                true);
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = tinkerResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent(), true);
        assert allAbstractPackContents.size() == 0 : "Invalid count : expected "
                + 0 + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent(), true);
        assert allPackContents.size() == 50 : "Invalid count : expected "
                + 50 + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = tinkerResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent(), true);
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2(), true);
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesPersistentLoadedMapDB() throws IOException {
        mapResource.save(Collections.EMPTY_MAP);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)mapResource);
        mapResource.load(Collections.EMPTY_MAP);
        EList<EObject> allPacks = mapResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack());
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = mapResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent());
        assert allAbstractPackContents.size() == abstractPackContentCount : "Invalid count : expected "
                + abstractPackContentCount + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = mapResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent());
        assert allPackContents.size() == packContentCount : "Invalid count : expected "
                + packContentCount + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = mapResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent());
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = mapResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2());
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesStrictPersistentLoadedMapDB() throws IOException {
        mapResource.save(Collections.EMPTY_MAP);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)mapResource);
        mapResource.load(Collections.EMPTY_MAP);
        EList<EObject> allPacks = mapResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack(),
                true);
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = mapResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent(), true);
        assert allAbstractPackContents.size() == 0 : "Invalid count : expected "
                + 0 + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = mapResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent(), true);
        assert allPackContents.size() == 50 : "Invalid count : expected "
                + 50 + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = mapResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent(), true);
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = mapResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2(), true);
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesPersistentLoadedNeo4j() throws IOException {
        neo4jResource.save(Collections.EMPTY_MAP);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)neo4jResource);
        neo4jResource.load(Collections.EMPTY_MAP);
        EList<EObject> allPacks = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack());
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = neo4jResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent());
        assert allAbstractPackContents.size() == abstractPackContentCount : "Invalid count : expected "
                + abstractPackContentCount + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent());
        assert allPackContents.size() == packContentCount : "Invalid count : expected "
                + packContentCount + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = neo4jResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent());
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2());
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesStrictPersistentLoadedNeo4j() throws IOException {
        neo4jResource.save(Collections.EMPTY_MAP);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)neo4jResource);
        neo4jResource.load(Collections.EMPTY_MAP);
        EList<EObject> allPacks = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack(),
                true);
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = neo4jResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent(), true);
        assert allAbstractPackContents.size() == 0 : "Invalid count : expected "
                + 0 + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent(), true);
        assert allPackContents.size() == 50 : "Invalid count : expected "
                + 50 + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = neo4jResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent(), true);
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2(), true);
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    @Test
    public void testAllInstancesPersistentLoadedTinker() throws IOException {
        tinkerResource.save(Collections.EMPTY_MAP);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)tinkerResource);
        tinkerResource.load(Collections.EMPTY_MAP);
        EList<EObject> allPacks = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack());
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = tinkerResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent());
        assert allAbstractPackContents.size() == abstractPackContentCount : "Invalid count : expected "
                + abstractPackContentCount + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent());
        assert allPackContents.size() == packContentCount : "Invalid count : expected "
                + packContentCount + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = tinkerResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent());
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2());
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }
    
    public void testAllInstancesStrictPersistentLoadedTinker() throws IOException {
        tinkerResource.save(Collections.EMPTY_MAP);
        PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)tinkerResource);
        tinkerResource.load(Collections.EMPTY_MAP);
        EList<EObject> allPacks = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack(),
                true);
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allAbstractPackContents = tinkerResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent(), true);
        assert allAbstractPackContents.size() == 0 : "Invalid count : expected "
                + 0 + ", found " + allAbstractPackContents.size();
        EList<EObject> allPackContents = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent(), true);
        assert allPackContents.size() == 50 : "Invalid count : expected "
                + 50 + ", found " + allPackContents.size();
        EList<EObject> allSpecializedPackContents = tinkerResource
                .getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent(), true);
        assert allSpecializedPackContents.size() == specializedPackContentCount : "Invalid count : expected "
                + specializedPackContentCount + ", found " + allSpecializedPackContents.size();
        EList<EObject> allPackContents2 = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent2(), true);
        assert allPackContents2.size() == packContent2Count : "Invalid count : expected "
                + packContent2Count + ", found " + allPackContents2.size();
    }

}
