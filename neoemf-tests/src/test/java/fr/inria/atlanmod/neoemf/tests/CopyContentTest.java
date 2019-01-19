/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.provider.ContextProvider;
import fr.inria.atlanmod.neoemf.tests.sample.PrimaryObject;
import fr.inria.atlanmod.neoemf.tests.sample.TargetObject;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about the copy from a {@link fr.inria.atlanmod.neoemf.data.Backend} to another.
 */
@ParametersAreNonnullByDefault
class CopyContentTest extends AbstractResourceBasedTest {

    /**
     * The name of the {@link PrimaryObject}.
     */
    private static final String PRIMARY_NAME = "Model";

    /**
     * The name of the first {@link TargetObject}.
     */
    private static final String TARGET0_NAME = "Content1";

    /**
     * The name of the second {@link TargetObject}.
     */
    private static final String TARGET1_NAME = "Content2";

    @BeforeAll
    static void registerPackages() {
        ResourceManager.registerAllPackages();
    }

    /**
     * Checks the copy from a transient {@link fr.inria.atlanmod.neoemf.data.Backend} to a persistent {@link
     * fr.inria.atlanmod.neoemf.data.Backend} when calling {@link PersistentResource#save(Map)}.
     */
    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    void testCopyTransientToPersistentResource(Context context) throws IOException {
        try (PersistentResource resource = createTransientResource(context)) {
            fillResource(resource);

            resource.save(context.config());
            assertThat(resource.getContents()).isNotEmpty();
            assertThat(resource.getContents().get(0)).isInstanceOf(PrimaryObject.class);

            PrimaryObject primary = (PrimaryObject) resource.getContents().get(0);
            assertThat(primary.getName()).isEqualTo(PRIMARY_NAME);

            List<TargetObject> targets = primary.getManyContainmentReferences();
            assertThat(targets).isNotEmpty();
            assertThat(targets).hasSize(2);

            assertThat(targets.get(0).getName()).isEqualTo(TARGET0_NAME);
            assertThat(targets.get(1).getName()).isEqualTo(TARGET1_NAME);

            assertThat(targets.get(0).eContainer()).isEqualTo(primary);
            assertThat(targets.get(1).eContainer()).isEqualTo(primary);
        }
    }

    /**
     * Checks the transfer from a fully-transient {@link fr.inria.atlanmod.neoemf.data.Backend} to a persistent {@link
     * fr.inria.atlanmod.neoemf.data.Backend}.
     */
    @Tag("slower")
    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    // FIXME May failed with HBase when using no caching store
    void testMoveStandardToPersistentResource(Context context) throws IOException {
        try (PersistentResource resource = createPersistentResource(context)) {
            EObject expected = ResourceManager.load(ResourceManager.xmiStandard());

            // Move the content and check the state of `expected` after transfer
            resource.getContents().add(expected);

            assertThat(expected.eResource()).isEqualTo(resource);

            // Save the persistent resource
            resource.save(context.config());

            // Load objects and compare them
            EObject actual = resource.getContents().get(0);
            expected = PersistentEObject.from(ResourceManager.load(ResourceManager.xmiStandard()));

            ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

            expected.eResource().unload();
        }
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(Resource resource) {
        PrimaryObject primary = EFACTORY.createPrimaryObject();
        primary.setName(PRIMARY_NAME);

        TargetObject target0 = EFACTORY.createTargetObject();
        target0.setName(TARGET0_NAME);
        primary.getManyContainmentReferences().add(target0);

        TargetObject target1 = EFACTORY.createTargetObject();
        target1.setName(TARGET1_NAME);
        primary.getManyContainmentReferences().add(target1);

        resource.getContents().add(primary);
    }
}
