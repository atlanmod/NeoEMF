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
import fr.inria.atlanmod.neoemf.tests.sample.PrimaryObject;
import fr.inria.atlanmod.neoemf.tests.sample.TargetObject;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about the copy from a {@link fr.inria.atlanmod.neoemf.data.Backend} to another.
 */
public class CopyContentTest extends AbstractBackendTest {

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

    /**
     * Checks the copy from a transient {@link fr.inria.atlanmod.neoemf.data.Backend} to a persistent {@link
     * fr.inria.atlanmod.neoemf.data.Backend} when calling {@link PersistentResource#save(Map)}.
     *
     * @throws IOException if an I/O error occurs during {@link PersistentResource#save(Map)}
     */
    @Test
    @Category(Tags.TransientTests.class)
    public void testCopyBackend() throws IOException {
        PersistentResource resource = newTransientStore();
        fillResource(resource);

        resource.save(context.optionsBuilder().asMap());
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
