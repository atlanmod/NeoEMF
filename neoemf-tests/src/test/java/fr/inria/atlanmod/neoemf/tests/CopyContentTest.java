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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.io.util.IOTestUtils;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.context.ContextProvider;
import fr.inria.atlanmod.neoemf.tests.sample.PrimaryObject;
import fr.inria.atlanmod.neoemf.tests.sample.TargetObject;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeAll;
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
public class CopyContentTest extends AllContextTest {

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
    public static void registerPackages() {
        IOResourceManager.registerAllPackages();
    }

    /**
     * Checks the copy from a transient {@link fr.inria.atlanmod.neoemf.data.Backend} to a persistent {@link
     * fr.inria.atlanmod.neoemf.data.Backend} when calling {@link PersistentResource#save(Map)}.
     *
     * @throws IOException if an I/O error occurs during {@link PersistentResource#save(Map)}
     */
    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testCopyTransientToPersistentResource(Context sourceContext) throws IOException {
        PersistentResource resource = newTransientResource(sourceContext);
        fillResource(resource);

        resource.save(sourceContext.optionsBuilder().asMap());
        assertThat(resource.getContents()).isNotEmpty();
        assertThat(resource.getContents().get(0)).isInstanceOf(PrimaryObject.class);

        PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
        assertThat(primary.getName()).isEqualTo(PRIMARY_NAME);

        List<TargetObject> targets = primary.getManyContainmentReferences();
        assertThat(targets).isNotEmpty();
        assertThat(targets).hasSize(2);

        assertThat(targets.get(0).getName()).isEqualTo(TARGET0_NAME);
        assertThat(targets.get(1).getName()).isEqualTo(TARGET1_NAME);

        assertThat(targets.get(0).eContainer()).isEqualTo(primary);
        assertThat(targets.get(1).eContainer()).isEqualTo(primary);
    }

    @ParameterizedTest(name = "[{index}] {0}: isPersistent = {1}")
    @ArgumentsSource(ContextProvider.WithBooleans.class)
    public void testCopyStandardToPersistentResource(Context context, Boolean isPersistent) throws IOException {
        EObject sourceObject = IOTestUtils.loadWithEMF(IOResourceManager.xmiStandard());

        PersistentResource resource = isPersistent
                ? newPersistentResource(context)
                : newTransientResource(context);

        resource.getContents().add(sourceObject);
        resource.save(context.optionsBuilder().asMap());

        EObject targetObject = resource.getContents().get(0);

        ModelComparisonUtils.assertEObjectAreEqual(targetObject, sourceObject);

        sourceObject.eResource().unload();
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(PersistentResource resource) {
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
