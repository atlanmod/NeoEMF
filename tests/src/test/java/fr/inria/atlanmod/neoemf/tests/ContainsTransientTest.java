/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */
package fr.inria.atlanmod.neoemf.tests;

import org.junit.Test;


/**
 * Test class for the contains method, related to performance issue descibed in #30
 * <a href="https://github.com/atlanmod/NeoEMF/issues/30">https://github.com/atlanmod/NeoEMF/issues/30</a>
 */
public class ContainsTransientTest extends AllContainsTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createTransientStores();
    }
    
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testContainsTransient3ElementsMapDB() {
        createResourceContent(mapResource,3);
        checkContainsResult(mapResource,3);
    }
    
    @Test
    public void testContainsTransient3ElementsNeo4j() {
        createResourceContent(neo4jResource,3);
        checkContainsResult(neo4jResource,3);
    }
    
    @Test
    public void testContainsTransient3ElementsTinker() {
        createResourceContent(tinkerResource,3);
        checkContainsResult(tinkerResource,3);
    }
    
    @Test
    public void testContainsTransient4ElementsMapDB() {
        createResourceContent(mapResource,4);
        checkContainsResult(mapResource, 4);
    }
    
    @Test
    public void testContainsTransient4ElementsNeo4j() {
        createResourceContent(neo4jResource, 4);
        checkContainsResult(neo4jResource, 4);
    }
    
    @Test
    public void testContainsTransient4ElementsTinker() {
        createResourceContent(tinkerResource, 4);
        checkContainsResult(tinkerResource, 4);
    }
    
    
    @Test
    public void testContainsTransient5ElementsMapDB() {
        createResourceContent(mapResource, 5);
        checkContainsResult(mapResource, 5);
    }
    
    @Test
    public void testContainsTransient5ElementsNeo4j() {
        createResourceContent(neo4jResource, 5);
        checkContainsResult(neo4jResource, 5);
    }
    
    @Test
    public void testContainsTransient5ElementsTinker() {
        createResourceContent(tinkerResource, 5);
        checkContainsResult(tinkerResource, 5);
    }
}
