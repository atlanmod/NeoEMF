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

import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AllInstancesPersistentTest extends AllInstancesTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createPersistentStore();
        createResourceContent(resource);
    }

    @Test
    public void testAllInstancesPersistent() {
        assertAllInstancesPersistentTranscient(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    @Test
    public void testAllInstancesStricPersistent() {
        assertAllInstancesPersistentTranscient(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }

    @Test
    public void testAllInstancesPersistentLoaded() throws IOException {
        resource.save(PersistenceOptionsBuilder.noOption());
        resource.close();
        resource.load(PersistenceOptionsBuilder.noOption());

        assertAllInstancesPersistentTranscient(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    @Test
    public void testAllInstancesStrictPersistentLoaded() throws IOException {
        resource.save(PersistenceOptionsBuilder.noOption());
        resource.close();
        resource.load(PersistenceOptionsBuilder.noOption());

        assertAllInstancesPersistentTranscient(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }
}
