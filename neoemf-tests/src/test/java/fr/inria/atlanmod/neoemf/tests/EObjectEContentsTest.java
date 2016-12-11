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

import fr.inria.atlanmod.neoemf.context.Tags;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.AbstractPackContent;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.Pack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * Test class for the contains method, related to performance issue descibed in #30
 * <a href="https://github.com/atlanmod/NeoEMF/issues/30">https://github.com/atlanmod/NeoEMF/issues/30</a>
 */
public class EObjectEContentsTest extends AbstractBackendTest {

    private static final int SUB_PACK_COUNT = 5;
    private static final int PACK_CONTENT_COUNT = 3;
    private static final int ECONTENTS_COUNT = SUB_PACK_COUNT + PACK_CONTENT_COUNT;

    private Pack pack;
    private List<EObject> subPacks;
    private List<EObject> packContents;

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEObjectEContents() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        EList<EObject> eContents = pack.eContents();
        assertThat(eContents).hasSize(ECONTENTS_COUNT);

        IntStream.range(0, SUB_PACK_COUNT).forEach(index -> {
            assertThat(eContents.get(index)).isEqualTo(subPacks.get(index)); // "p.eContents().get(i) != subPacks.get(i)"
        });

        IntStream.range(0, PACK_CONTENT_COUNT).forEach(index -> {
            assertThat(eContents.get(index + SUB_PACK_COUNT)).isEqualTo(packContents.get(index)); // "p.eContents().get(i + SUB_PACK_COUNT) != packContents.get(i)"
        });
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEObjectEmptyEContentsSize() {
        PersistentResource resource = createPersistentStore();
        createEmptyPackResourceContent(resource);

        EList<EObject> eContents = pack.eContents();
        assertThat(eContents).isEmpty();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEObjectEmptyEContentsGet() {
        PersistentResource resource = createPersistentStore();
        createEmptyPackResourceContent(resource);

        Throwable thrown = catchThrowable(() -> pack.eContents().get(0));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    private void createResourceContent(final PersistentResource resource) {
        subPacks = new ArrayList<>();
        packContents = new ArrayList<>();

        Pack parentPack = EFACTORY.createPack();
        parentPack.setName("ParentPack");

        pack = EFACTORY.createPack();
        pack.setName("Pack");
        pack.setParentPack(parentPack);

        IntStream.range(0, SUB_PACK_COUNT).forEach(i -> {
            Pack subPack = EFACTORY.createPack();
            subPack.setName("SubPack" + i);
            pack.getPacks().add(subPack);
            subPacks.add(subPack);
        });

        IntStream.range(0, PACK_CONTENT_COUNT).forEach(i -> {
            AbstractPackContent pContent = EFACTORY.createPackContent();
            pContent.setName("PackContent" + i);
            pack.getOwnedContents().add(pContent);
            packContents.add(pContent);
        });

        resource.getContents().add(pack);
    }

    private void createEmptyPackResourceContent(final PersistentResource resource) {
        pack = EFACTORY.createPack();
        pack.setName("Empty Pack");
        resource.getContents().add(pack);
    }
}
