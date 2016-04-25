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

import static org.junit.Assert.fail;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;

public class AllInstancesTransientTest extends AllInstancesTest {
    
    @Before
    public void setUp() throws Exception {
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

    @Test
    public void testAllInstancesTransientMapDB() {
        EList<EObject> allPacks = mapResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack());
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allPackContents = mapResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent());
        assert allPackContents.size() == packContentCount : "Invalid count : expected "
                + packContentCount + ", found " + allPackContents.size();
    }
    
    @Test
    public void testAllInstancesTransientNeo4j() {
        EList<EObject> allPacks = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack());
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allPackContents = neo4jResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent());
        assert allPackContents.size() == packContentCount : "Invalid count : expected "
                + packContentCount + ", found " + allPackContents.size();
    }
    
    @Test
    public void testAllInstancesTransientTinker() {
        EList<EObject> allPacks = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack());
        assert allPacks.size() == packCount : "Invalid count : expected " + packCount + ", found "
                + allPacks.size();
        EList<EObject> allPackContents = tinkerResource.getAllInstances(MapSamplePackage.eINSTANCE
                .getPackContent());
        assert allPackContents.size() == packContentCount : "Invalid count : expected "
                + packContentCount + ", found " + allPackContents.size();
    }

}
