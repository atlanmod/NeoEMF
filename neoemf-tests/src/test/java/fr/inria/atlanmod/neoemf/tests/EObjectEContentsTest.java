/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.Tags;
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
 * Test class for the contains method, related to performance issue descibed in #30 <a
 * href="https://github.com/atlanmod/NeoEMF/issues/30">https://github.com/atlanmod/NeoEMF/issues/30</a>
 */
public class EObjectEContentsTest extends AbstractBackendTest {

    /**
     * The expected number of {@link Pack} contained in {@link #pack}.
     */
    private static final int SUB_PACK_COUNT = 5;

    /**
     * The expected number of
     */
    private static final int PACK_CONTENT_COUNT = 3;

    /**
     * The expected number of
     */
    private static final int ECONTENTS_COUNT = SUB_PACK_COUNT + PACK_CONTENT_COUNT;

    /**
     * The root {@link Pack}.
     */
    private Pack pack;

    /**
     * The {@link Pack}s contained in {@link #pack}.
     */
    private List<Pack> subPacks;

    /**
     * The owned content of {@link #pack}.
     */
    private List<AbstractPackContent> packContents;

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEObjectEContents() {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        EList<EObject> eContents = pack.eContents();
        assertThat(eContents).hasSize(ECONTENTS_COUNT);

        IntStream.range(0, SUB_PACK_COUNT)
                .forEach(i -> assertThat(eContents.get(i)).isEqualTo(subPacks.get(i)));

        IntStream.range(0, PACK_CONTENT_COUNT)
                .forEach(i -> assertThat(eContents.get(i + SUB_PACK_COUNT)).isEqualTo(packContents.get(i)));
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEObjectEmptyEContentsSize() {
        PersistentResource resource = createPersistentStore();
        fillResourceWithEmpty(resource);

        EList<EObject> eContents = pack.eContents();
        assertThat(eContents).isEmpty();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEObjectEmptyEContentsGet() {
        PersistentResource resource = createPersistentStore();
        fillResourceWithEmpty(resource);

        assertThat(catchThrowable(() -> pack.eContents().get(0)))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(PersistentResource resource) {
        subPacks = new ArrayList<>();
        packContents = new ArrayList<>();

        Pack parentPack = EFACTORY.createPack();
        parentPack.setName("ParentPack");

        pack = EFACTORY.createPack();
        pack.setName("Pack");
        pack.setParentPack(parentPack);

        IntStream.range(0, SUB_PACK_COUNT).forEach(i -> {
            Pack childPack = EFACTORY.createPack();
            childPack.setName("SubPack" + i);
            pack.getPacks().add(childPack);
            subPacks.add(childPack);
        });

        IntStream.range(0, PACK_CONTENT_COUNT).forEach(i -> {
            AbstractPackContent content = EFACTORY.createPackContent();
            content.setName("PackContent" + i);
            pack.getOwnedContents().add(content);
            packContents.add(content);
        });

        resource.getContents().add(pack);
    }

    /**
     * Fills the {@code resource} with an empty {@link Pack}.
     *
     * @param resource the resource to fill
     */
    private void fillResourceWithEmpty(PersistentResource resource) {
        pack = EFACTORY.createPack();
        pack.setName("Empty Pack");
        resource.getContents().add(pack);
    }
}
