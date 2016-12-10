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
import fr.inria.atlanmod.neoemf.tests.models.mapSample.AbstractPackContent;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.Pack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;
import static fr.inria.atlanmod.neoemf.NeoAssertions.catchThrowable;

/**
 * Test class for the contains method, related to performance issue descibed in #30
 * <a href="https://github.com/atlanmod/NeoEMF/issues/30">https://github.com/atlanmod/NeoEMF/issues/30</a>
 */
public class EObjectEContentsTest extends AllBackendTest {

    private static final int SUB_PACK_COUNT = 5;
    private static final int PACK_CONTENT_COUNT = 3;
    private static final int ECONTENTS_COUNT = SUB_PACK_COUNT + PACK_CONTENT_COUNT;

    protected Pack p;
    protected List<EObject> subPacks;
    protected List<EObject> packContents;

    @Override
    public void setUp() throws Exception {
        subPacks = new ArrayList<>();
        packContents = new ArrayList<>();
        super.setUp();
        createPersistentStore();
    }

    @Override
    public void tearDown() throws Exception {
        p = null;
        subPacks = null;
        packContents = null;
        super.tearDown();
    }

    @Test
    public void testEObjectEContents() {
        createResourceContent(resource);
        checkEContents();
    }

    @Test
    public void testEObjectEmptyEContentsSize() {
        createEmptyPackResourceContent(resource);
        checkEmptyEContentsSize();
    }

    @Test
    public void testEObjectEmptyEContentsGet() {
        createEmptyPackResourceContent(resource);

        Throwable thrown = catchThrowable(this::checkEmptyEContentsGet);
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    protected void createResourceContent(PersistentResource resource) {
        Pack parentPack = factory.createPack();
        parentPack.setName("ParentPack");

        p = factory.createPack();
        p.setName("Pack");
        p.setParentPack(parentPack);

        for (int i = 0; i < SUB_PACK_COUNT; i++) {
            Pack subPack = factory.createPack();
            subPack.setName("SubPack" + i);
            p.getPacks().add(subPack);
            subPacks.add(subPack);
        }

        for (int i = 0; i < PACK_CONTENT_COUNT; i++) {
            AbstractPackContent pContent = factory.createPackContent();
            pContent.setName("PackContent" + i);
            p.getOwnedContents().add(pContent);
            packContents.add(pContent);
        }
        resource.getContents().add(p);
    }

    protected void createEmptyPackResourceContent(PersistentResource resource) {
        p = factory.createPack();
        p.setName("Empty Pack");
        resource.getContents().add(p);
    }

    private void checkEContents() {
        EList<EObject> eContents = p.eContents();
        assertThat(eContents).hasSize(ECONTENTS_COUNT);
        for (int i = 0; i < SUB_PACK_COUNT; i++) {
            assertThat(eContents.get(i)).isEqualTo(subPacks.get(i)); // "p.eContents().get(i) != subPacks.get(i)"
        }
        for (int i = 0; i < PACK_CONTENT_COUNT; i++) {
            assertThat(eContents.get(i + SUB_PACK_COUNT)).isEqualTo(packContents.get(i)); // "p.eContents().get(i + SUB_PACK_COUNT) != packContents.get(i)"
        }
    }

    private void checkEmptyEContentsSize() {
        EList<EObject> eContents = p.eContents();
        assertThat(eContents).isEmpty();
    }

    private void checkEmptyEContentsGet() {
        p.eContents().get(0);
    }
}
