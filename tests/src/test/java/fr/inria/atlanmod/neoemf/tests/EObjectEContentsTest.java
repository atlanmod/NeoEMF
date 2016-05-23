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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContent;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack;

/**
 * Test class for the contains method, related to performance issue descibed in #30
 * {@link https://github.com/atlanmod/NeoEMF/issues/30}
 */
public class EObjectEContentsTest extends AllBackendTest {

    private static int SUB_PACK_COUNT = 5;
    private static int PACK_CONTENT_COUNT = 3;
    private static int ECONTENTS_COUNT = SUB_PACK_COUNT + PACK_CONTENT_COUNT;
    
    protected MapSampleFactory factory;
    protected Pack p;
    protected List<EObject> subPacks;
    protected List<EObject> packContents;
    
    
    @Override
    public void setUp() throws Exception {
        factory = MapSampleFactory.eINSTANCE;
        ePackage = MapSamplePackage.eINSTANCE;
        subPacks = new ArrayList<EObject>();
        packContents = new ArrayList<EObject>();
        super.setUp();
        createPersistentStores();
    }
    
    @Override
    public void tearDown() throws Exception {
        p = null;
        subPacks = null;
        packContents = null;
        super.tearDown();
    }
    
    @Test
    public void testEObjectEContentsMapDB() {
        createResourceContent(mapResource);
        checkEContents();
    }
    
    @Test
    public void testEObjectEContentsNeo4j() {
        createResourceContent(neo4jResource);
        checkEContents();
    }
    
    @Test
    public void testEObjectEContentsTinker() {
        createResourceContent(tinkerResource);
        checkEContents();
    }
    
    @Test
    public void testEObjectEmptyEContentsSizeMapDB() {
        createEmptyPackResourceContent(mapResource);
        checkEmptyEContentsSize();
    }
    
    @Test
    public void testEObjectEmptyEContentsSizeNeo4j() {
        createEmptyPackResourceContent(neo4jResource);
        checkEmptyEContentsSize();
    }
    
    @Test
    public void testEObjectEmptyEContentsSizeTinker() {
        createEmptyPackResourceContent(tinkerResource);
        checkEmptyEContentsSize();
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testEObjectEmptyEContentsGetMapDB() {
        createEmptyPackResourceContent(mapResource);
        checkEmptyEContentsGet();
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testEObjectEmptyEContentsGetNeo4j() {
        createEmptyPackResourceContent(neo4jResource);
        checkEmptyEContentsGet();
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testEObjectEmptyEContentsGetTinker() {
        createEmptyPackResourceContent(tinkerResource);
        checkEmptyEContentsGet();
    }
    
    @Test
    public void test() {
        createResourceContent(neo4jResource);
        p.eContents().iterator().next();
    }
    
    protected void createResourceContent(Resource r) {
        Pack parentPack = factory.createPack();
        parentPack.setName("ParentPack");
        
        p = factory.createPack();
        p.setName("Pack");
        p.setParentPack(parentPack);
        
        for(int i = 0; i < SUB_PACK_COUNT; i++) {
            Pack subPack = factory.createPack();
            subPack.setName("SubPack"+i);
            p.getPacks().add(subPack);
            subPacks.add(subPack);
        }
        
        for(int i = 0; i < PACK_CONTENT_COUNT; i++) {
            AbstractPackContent pContent = factory.createPackContent();
            pContent.setName("PackContent"+i );
            p.getOwnedContents().add(pContent);
            packContents.add(pContent);
        }
        r.getContents().add(p);
    }
    
    protected void createEmptyPackResourceContent(Resource r) {
        p = factory.createPack();
        p.setName("Empty Pack");
        r.getContents().add(p);
    }
    
    private void checkEContents() {
        EList<EObject> eContents = p.eContents();
        assertThat("p.eContents().size() returns an invalid value", eContents.size(), equalTo(ECONTENTS_COUNT));
        for(int i = 0; i < SUB_PACK_COUNT; i++) {
            assertThat("p.eContents().get("+i+") != subPacks.get("+i+")", eContents.get(i), equalTo(subPacks.get(i)));
        }
        for(int i = 0; i < PACK_CONTENT_COUNT; i++) {
            assertThat("p.eContents().get(" + (i + SUB_PACK_COUNT) + ") != packContents.get(" + i
                    + ")", eContents.get(i + SUB_PACK_COUNT), equalTo(packContents.get(i)));
        }
    }
    
    private void checkEmptyEContentsSize() {
        EList<EObject> eContents = p.eContents();
        assertThat("p.eContents().size() != 0", eContents.size(), equalTo(0));
    }
    
    private void checkEmptyEContentsGet() {
        p.eContents().get(0);
    }
}
