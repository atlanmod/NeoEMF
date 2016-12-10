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
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.Pack;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.PackContent;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.PackContent2;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SpecializedPackContent;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.stream.IntStream;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

public class AllInstancesTest extends AllBackendTest {

    // These variables should be updated if createResourceContent is changed
    private static final int PACK_COUNT = 6;
    private static final int ABSTRACT_PACK_CONTENT_COUNT = 150;
    private static final int PACK_CONTENT_COUNT = 100;
    private static final int SPECIALIZED_PACK_CONTENT_COUNT = 50;
    private static final int PACK_CONTENT_2_COUNT = 50;
    private static final int ABSTRACT_PACK_CONTENT_STRICT_COUNT = 0;
    private static final int PACK_CONTENT_STRICT_COUNT = 50;

    @Test
    @Category(PersistentTests.class)
    public void testAllInstancesPersistent() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        assertAllInstancesPersistentTranscient(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    @Test
    @Category(PersistentTests.class)
    public void testAllInstancesStricPersistent() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        assertAllInstancesPersistentTranscient(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }

    @Test
    @Category(PersistentTests.class)
    public void testAllInstancesPersistentLoaded() throws IOException {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        resource.save(PersistenceOptionsBuilder.noOption());
        resource.close();
        resource.load(PersistenceOptionsBuilder.noOption());

        assertAllInstancesPersistentTranscient(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    @Test
    @Category(PersistentTests.class)
    public void testAllInstancesStrictPersistentLoaded() throws IOException {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        resource.save(PersistenceOptionsBuilder.noOption());
        resource.close();
        resource.load(PersistenceOptionsBuilder.noOption());

        assertAllInstancesPersistentTranscient(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }

    @Test
    @Category(TransientTests.class)
    public void testAllInstancesTransient() {
        PersistentResource resource = createTransientStore();
        createResourceContent(resource);

        assertAllInstancesPersistentTranscient(resource, false, ABSTRACT_PACK_CONTENT_COUNT, PACK_CONTENT_COUNT);
    }

    @Test
    @Category(TransientTests.class)
    public void testAllInstancesStrictTransient() {
        PersistentResource resource = createTransientStore();
        createResourceContent(resource);

        assertAllInstancesPersistentTranscient(resource, true, ABSTRACT_PACK_CONTENT_STRICT_COUNT, PACK_CONTENT_STRICT_COUNT);
    }

    private void createResourceContent(final PersistentResource resource) {
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

    private void assertAllInstancesPersistentTranscient(final PersistentResource resource, final boolean strict, final int abstractPackContentCount, final int packContentCount) {
        EList<EObject> allPacks = resource.getAllInstances(MapSamplePackage.eINSTANCE.getPack(), strict);
        assertThat(allPacks).hasSize(PACK_COUNT); // "Invalid count"

        EList<EObject> allAbstractPackContents = resource.getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent(), strict);
        assertThat(allAbstractPackContents).hasSize(abstractPackContentCount); // "Invalid count"

        EList<EObject> allPackContents = resource.getAllInstances(MapSamplePackage.eINSTANCE.getPackContent(), strict);
        assertThat(allPackContents).hasSize(packContentCount); // "Invalid count"

        EList<EObject> allSpecializedPackContents = resource.getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent(), strict);
        assertThat(allSpecializedPackContents).hasSize(SPECIALIZED_PACK_CONTENT_COUNT); // "Invalid count"

        EList<EObject> allPackContents2 = resource.getAllInstances(MapSamplePackage.eINSTANCE.getPackContent2(), strict);
        assertThat(allPackContents2).hasSize(PACK_CONTENT_2_COUNT); // "Invalid count"
    }
}
