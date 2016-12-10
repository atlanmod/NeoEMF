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

import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.junit.Before;
import org.junit.Test;

public class AllInstancesTransientTest extends AllInstancesTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createTransientStore();
        createResourceContent(resource);
    }

    @Test
    public void testAllInstancesTransient() {
        allInstancesTransient(resource);
    }

    @Test
    public void testAllInstancesStrictTransient() {
        allInstancesStrictTransient(resource);
    }

    private void allInstancesTransient(PersistentResource resource) {
        allInstancesPersistentTranscient(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    private void allInstancesStrictTransient(PersistentResource resource) {
        allInstancesPersistentTranscient(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }
}
