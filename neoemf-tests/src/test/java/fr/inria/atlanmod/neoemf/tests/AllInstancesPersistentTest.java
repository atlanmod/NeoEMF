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
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

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
        allInstancesPersistent(resource);
    }

    @Test
    public void testAllInstancesStricPersistent() {
        allInstancesStrictPersistent(resource);
    }

    @Test
    public void testAllInstancesPersistentLoaded() throws IOException {
        allInstancesPersistentLoaded(resource);
    }

    @Test
    public void testAllInstancesStrictPersistentLoaded() throws IOException {
        allInstancesStrictPersistentLoaded(resource);
    }

    private void allInstancesPersistentLoaded(PersistentResource resource) throws IOException {
        resource.save(PersistenceOptionsBuilder.noOption());
        resource.close();
        resource.load(PersistenceOptionsBuilder.noOption());

        allInstancesPersistent(resource);
    }

    private void allInstancesStrictPersistentLoaded(PersistentResource resource) throws IOException {
        resource.save(PersistenceOptionsBuilder.noOption());
        resource.close();
        resource.load(PersistenceOptionsBuilder.noOption());

        allInstancesStrictPersistent(resource);
    }

    private void allInstancesPersistent(PersistentResource resource) {
        allInstancesPersistentTranscient(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    private void allInstancesStrictPersistent(PersistentResource resource) {
        allInstancesPersistentTranscient(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }
}
