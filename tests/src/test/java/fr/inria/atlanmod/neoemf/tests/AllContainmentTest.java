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

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.*;
import org.junit.After;
import org.junit.Before;

public abstract class AllContainmentTest extends AllBackendTest {

    protected MapSampleFactory factory;

    protected Pack p1;
    protected Pack p2;
    protected Pack p3;
    protected PackContent pc1;
    protected AbstractPackContentComment com1;

    @Before
    public void setUp() throws Exception {
        factory = MapSampleFactory.eINSTANCE;
        ePackage = MapSamplePackage.eINSTANCE;
        super.setUp();
        super.createPersistentStores();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        p1 = null;
        p2 = null;
        p3 = null;
        pc1 = null;
        com1 = null;
    }

}
