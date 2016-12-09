/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.data.blueprints.store.DirectWriteBlueprintsStore;
import fr.inria.atlanmod.neoemf.data.mapdb.store.DirectWriteMapDbStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.AbstractPackContentComment;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.Pack;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.PackContent;

import org.eclipse.emf.ecore.InternalEObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AllContainmentTest extends AllBackendTest {

    protected Pack p1;
    protected Pack p2;
    protected Pack p3;

    protected PackContent pc1;
    protected AbstractPackContentComment com1;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createPersistentStores();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        p1 = null;
        p2 = null;
        p3 = null;
        pc1 = null;
        com1 = null;
    }

    protected abstract void createResourceContent(PersistentResource resource);

    protected abstract void addContainmentSubTree(PersistentResource resource, Class<? extends InternalEObject.EStore> clazz);

    @Test
    public void testAddContainmentSubtreeToPersistentResourceMapDB() {
        createResourceContent(mapResource);
        addContainmentSubTree(mapResource, DirectWriteMapDbStore.class);
    }

    @Test
    public void testAddContainmentSubtreeToPersistentResourceNeo4j() {
        createResourceContent(neo4jResource);
        addContainmentSubTree(neo4jResource, DirectWriteBlueprintsStore.class);
    }

    @Test
    public void testAddContainmentSubtreeToPersistentResourceTinker() {
        createResourceContent(tinkerResource);
        addContainmentSubTree(tinkerResource, DirectWriteBlueprintsStore.class);
    }
}
