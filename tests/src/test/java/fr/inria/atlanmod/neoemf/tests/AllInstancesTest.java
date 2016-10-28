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

import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.Pack;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.PackContent;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.PackContent2;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SpecializedPackContent;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AllInstancesTest extends AllBackendTest {

    // These variables should be updated if createResourceContent is changed
    protected static final int PACK_COUNT = 6;
    protected static final int ABSTRACT_PACK_CONTENT_COUNT = 150;
    protected static final int PACK_CONTENT_COUNT = 100;
    protected static final int SPECIALIZED_PACK_CONTENT_COUNT = 50;
    protected static final int PACK_CONTENT_2_COUNT = 50;
    protected static final int ABSTRACT_PACK_CONTENT_STRICT_COUNT = 0;
    protected static final int PACK_CONTENT_STRICT_COUNT = 50;

    protected MapSampleFactory factory;


    @Override
    @Before
    public void setUp() throws Exception {
        factory = MapSampleFactory.eINSTANCE;
        this.ePackage = MapSamplePackage.eINSTANCE;
        super.setUp();
    }

    /**
     * Creates a Pack hierarchy containing 1 root, 5 sub-pack elements, 10
     * PackContent, and 10 SpecializedPackContent elements in each sub-pack
     *
     * @param r the PersistentResource to fill with the created model
     */
    protected void createResourceContent(PersistentResource r) {
        Pack rootPack = factory.createPack();
        rootPack.setName("root");
        for (int i = 0; i < 5; i++) {
            Pack newPack = factory.createPack();
            newPack.setName("pack" + i);
            rootPack.getPacks().add(newPack);
            for (int j = 0; j < 10; j++) {
                PackContent newPackContent = factory.createPackContent();
                newPackContent.setName("pContent" + i + '-' + j);
                newPack.getOwnedContents().add(newPackContent);
                SpecializedPackContent newSpecializedPackContent = factory.createSpecializedPackContent();
                newSpecializedPackContent.setName("spContent" + i + '-' + j);
                newPack.getOwnedContents().add(newSpecializedPackContent);
                PackContent2 newPackContent2 = factory.createPackContent2();
                newPackContent2.setName("pContent2" + i + '-' + j);
                newPack.getOwnedContents().add(newPackContent2);
            }
        }
        r.getContents().add(rootPack);
    }

    protected void allInstancesPersistentTranscient(PersistentResource persistentResource, boolean strict, int abstractPackContentCount, int packContentCount) {
        EList<EObject> allPacks = persistentResource.getAllInstances(MapSamplePackage.eINSTANCE.getPack(), strict);
        assertThat(allPacks).hasSize(PACK_COUNT); // "Invalid count"

        EList<EObject> allAbstractPackContents = persistentResource.getAllInstances(MapSamplePackage.eINSTANCE.getAbstractPackContent(), strict);
        assertThat(allAbstractPackContents).hasSize(abstractPackContentCount); // "Invalid count"

        EList<EObject> allPackContents = persistentResource.getAllInstances(MapSamplePackage.eINSTANCE.getPackContent(), strict);
        assertThat(allPackContents).hasSize(packContentCount); // "Invalid count"

        EList<EObject> allSpecializedPackContents = persistentResource.getAllInstances(MapSamplePackage.eINSTANCE.getSpecializedPackContent(), strict);
        assertThat(allSpecializedPackContents).hasSize(SPECIALIZED_PACK_CONTENT_COUNT); // "Invalid count"

        EList<EObject> allPackContents2 = persistentResource.getAllInstances(MapSamplePackage.eINSTANCE.getPackContent2(), strict);
        assertThat(allPackContents2).hasSize(PACK_CONTENT_2_COUNT); // "Invalid count"
    }
}
