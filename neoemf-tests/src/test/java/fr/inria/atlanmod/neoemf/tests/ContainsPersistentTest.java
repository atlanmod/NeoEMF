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

import org.junit.Test;

/**
 * Test class for the contains method, related to performance issue descibed in #30
 * <a href="https://github.com/atlanmod/NeoEMF/issues/30">https://github.com/atlanmod/NeoEMF/issues/30</a>
 */
public class ContainsPersistentTest extends AllContainsTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createPersistentStore();
    }

    @Test
    public void testContainsPersistent3Elements() {
        createResourceContent(resource, 3);
        checkContainsResult(resource, 3);
    }

    @Test
    public void testContainsPersistent4Elements() {
        createResourceContent(resource, 4);
        checkContainsResult(resource, 4);
    }

    @Test
    public void testContainsPersistent5Elements() {
        createResourceContent(resource, 5);
        checkContainsResult(resource, 5);
    }
}
