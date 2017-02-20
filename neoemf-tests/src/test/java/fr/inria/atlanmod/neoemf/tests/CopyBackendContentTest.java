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
import fr.inria.atlanmod.neoemf.option.CommonOptionsBuilder;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test case about the copy from a {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend} to another.
 */
public class CopyBackendContentTest extends AbstractBackendTest {

    /**
     * The name of the {@link SampleModel}.
     */
    private static final String MODEL_NAME = "Model";

    /**
     * The name of the first {@link SampleModelContentObject}.
     */
    private static final String CONTENT1_NAME = "Content1";

    /**
     * The name of the second {@link SampleModelContentObject}.
     */
    private static final String CONTENT2_NAME = "Content2";

    /**
     * Checks the copy from a transient {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend} to a persistent {@link
     * fr.inria.atlanmod.neoemf.data.PersistenceBackend} when calling {@link PersistentResource#save(Map)}.
     *
     * @throws IOException if an I/O error occurs during {@link PersistentResource#save(Map)}
     */
    @Test
    @Category(Tags.TransientTests.class)
    public void testCopyBackend() throws IOException {
        PersistentResource resource = createTransientStore();
        fillResource(resource);

        resource.save(CommonOptionsBuilder.noOption());
        assertThat(resource.getContents()).isNotEmpty();
        assertThat(resource.getContents().get(0)).isInstanceOf(SampleModel.class);

        SampleModel sampleModel = (SampleModel) resource.getContents().get(0);
        assertThat(sampleModel.getName()).isEqualTo(MODEL_NAME);

        EList<SampleModelContentObject> contentObjects = sampleModel.getContentObjects();
        assertThat(contentObjects).isNotEmpty();
        assertThat(contentObjects).hasSize(2);

        assertThat(contentObjects.get(0).getName()).isEqualTo(CONTENT1_NAME);
        assertThat(contentObjects.get(1).getName()).isEqualTo(CONTENT2_NAME);

        assertThat(contentObjects.get(0).eContainer()).isSameAs(sampleModel);
        assertThat(contentObjects.get(1).eContainer()).isSameAs(sampleModel);
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(PersistentResource resource) {
        SampleModel model = EFACTORY.createSampleModel();
        model.setName(MODEL_NAME);

        SampleModelContentObject content1 = EFACTORY.createSampleModelContentObject();
        content1.setName(CONTENT1_NAME);
        model.getContentObjects().add(content1);

        SampleModelContentObject content2 = EFACTORY.createSampleModelContentObject();
        content2.setName(CONTENT2_NAME);
        model.getContentObjects().add(content2);

        resource.getContents().add(model);
    }
}
