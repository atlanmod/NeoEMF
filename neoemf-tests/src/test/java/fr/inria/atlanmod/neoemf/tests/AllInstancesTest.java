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
import fr.inria.atlanmod.neoemf.option.CommonOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.Pack;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.PackContent;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.PackContent2;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SpecializedPackContent;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test case about the {@link PersistentResource#allInstances(EClass, boolean)} method.
 */
public class AllInstancesTest extends AbstractBackendTest {

    /**
     * The expected number of {@link Pack} in the resulting list.
     */
    private static final int PACK_COUNT = 6;

    /**
     * The expected number of {@link fr.inria.atlanmod.neoemf.tests.models.mapSample.AbstractPackContent} in the
     * resulting list.
     */
    private static final int ABSTRACT_PACK_CONTENT_COUNT = 150;

    /**
     * The expected number of {@link PackContent} in the resulting list.
     */
    private static final int PACK_CONTENT_COUNT = 100;

    /**
     * The expected number of {@link SpecializedPackContent} in the resulting list.
     */
    private static final int SPECIALIZED_PACK_CONTENT_COUNT = 50;

    /**
     * The expected number of {@link PackContent2} in the resulting list.
     */
    private static final int PACK_CONTENT_2_COUNT = 50;

    /**
     * The expected number of {@link fr.inria.atlanmod.neoemf.tests.models.mapSample.AbstractPackContent} in the
     * resulting list in a {@code strict} mode.
     */
    private static final int ABSTRACT_PACK_CONTENT_STRICT_COUNT = 0;

    /**
     * The expected number of {@link PackContent} in the resulting list in a {@code strict} mode.
     */
    private static final int PACK_CONTENT_STRICT_COUNT = 50;

    /**
     * Checks the content with a persistent store, and {@code strict = false}.
     */
    @Test
    @Category(Tags.PersistentTests.class)
    public void testAllInstancesPersistent() {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        assertAllInstancesHas(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    /**
     * Checks the content with a persistent store, and {@code strict = true}.
     */
    @Test
    @Category(Tags.PersistentTests.class)
    public void testAllInstancesStrictPersistent() {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        assertAllInstancesHas(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }

    /**
     * Checks the content after calling {@link PersistentResource#save(Map)} and {@link PersistentResource#load(Map)},
     * with {@code strict = false}.
     *
     * @throws IOException if a I/O error occurs during {@link PersistentResource#save(Map)} or {@link
     *                     PersistentResource#load(Map)}
     */
    @Test
    @Category(Tags.PersistentTests.class)
    public void testAllInstancesPersistentLoaded() throws IOException {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        resource.save(CommonOptions.noOption());
        resource.close();
        resource.load(CommonOptions.noOption());

        assertAllInstancesHas(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    /**
     * Checks the content after calling {@link PersistentResource#save(Map)} and {@link PersistentResource#load(Map)},
     * with {@code strict = true}.
     *
     * @throws IOException if a I/O error occurs during {@link PersistentResource#save(Map)} or {@link
     *                     PersistentResource#load(Map)}
     */
    @Test
    @Category(Tags.PersistentTests.class)
    public void testAllInstancesStrictPersistentLoaded() throws IOException {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        resource.save(CommonOptions.noOption());
        resource.close();
        resource.load(CommonOptions.noOption());

        assertAllInstancesHas(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }

    /**
     * Checks the content with a transient store, and {@code strict = false}.
     */
    @Test
    @Category(Tags.TransientTests.class)
    public void testAllInstancesTransient() {
        PersistentResource resource = createTransientStore();
        fillResource(resource);

        assertAllInstancesHas(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    /**
     * Checks the content with a transient store, and {@code strict = true}.
     */
    @Test
    @Category(Tags.TransientTests.class)
    public void testAllInstancesStrictTransient() {
        PersistentResource resource = createTransientStore();
        fillResource(resource);

        assertAllInstancesHas(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(PersistentResource resource) {
        Pack rootPack = EFACTORY.createPack();
        rootPack.setName("root");

        IntStream.range(0, 5).forEach(i -> {
            Pack newPack = EFACTORY.createPack();
            newPack.setName("pack" + i);
            rootPack.getPacks().add(newPack);

            IntStream.range(0, 10).forEach(j -> {
                PackContent newPackContent = EFACTORY.createPackContent();
                newPackContent.setName("pContent" + i + '-' + j);
                newPack.getOwnedContents().add(newPackContent);

                SpecializedPackContent newSpecializedPackContent = EFACTORY.createSpecializedPackContent();
                newSpecializedPackContent.setName("spContent" + i + '-' + j);
                newPack.getOwnedContents().add(newSpecializedPackContent);

                PackContent2 newPackContent2 = EFACTORY.createPackContent2();
                newPackContent2.setName("pContent2" + i + '-' + j);
                newPack.getOwnedContents().add(newPackContent2);
            });
        });
        resource.getContents().add(rootPack);
    }

    /**
     * Assert that the {@code resource} has the expected number of each element after calling {@link
     * PersistentResource#allInstances(EClass, boolean)}.
     *
     * @param resource                 the resource to test
     * @param strict                   {@code true} if the lookup searches for strict instances
     * @param abstractPackContentCount the expected number of {@link fr.inria.atlanmod.neoemf.tests.models.mapSample.AbstractPackContent}
     * @param packContentCount         the expected number of {@link PackContent}
     */
    private void assertAllInstancesHas(PersistentResource resource, boolean strict, int abstractPackContentCount, int packContentCount) {
        Iterable<EObject> allPacks = resource.allInstances(MapSamplePackage.eINSTANCE.getPack(), strict);
        assertThat(allPacks).hasSize(PACK_COUNT);

        Iterable<EObject> allAbstractPackContents = resource.allInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent(), strict);
        assertThat(allAbstractPackContents).hasSize(abstractPackContentCount);

        Iterable<EObject> allPackContents = resource.allInstances(MapSamplePackage.eINSTANCE.getPackContent(), strict);
        assertThat(allPackContents).hasSize(packContentCount);

        Iterable<EObject> allSpecializedPackContents = resource.allInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent(), strict);
        assertThat(allSpecializedPackContents).hasSize(SPECIALIZED_PACK_CONTENT_COUNT);

        Iterable<EObject> allPackContents2 = resource.allInstances(MapSamplePackage.eINSTANCE.getPackContent2(), strict);
        assertThat(allPackContents2).hasSize(PACK_CONTENT_2_COUNT);
    }
}
