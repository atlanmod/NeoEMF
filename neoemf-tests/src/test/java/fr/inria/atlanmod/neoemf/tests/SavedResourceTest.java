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
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class SavedResourceTest extends AbstractBackendTest {

    private SampleModel model;
    private SampleModelContentObject modelContentObject;

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEContainer() {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        assertThat(model.eContainer()).isNull();
        assertThat(model.eInternalContainer()).isNull();

        assertThat(modelContentObject.eContainer()).isEqualTo(model);
        assertThat(modelContentObject.eInternalContainer()).isEqualTo(model);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsContainer() {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        Iterator<EObject> it = resource.getAllContents();

        SampleModel model = (SampleModel) it.next();
        assertThat(model.eContainer()).isNull();
        assertThat(model.eInternalContainer()).isNull();

        SampleModelContentObject modelContent = (SampleModelContentObject) it.next();
        assertThat(modelContent.eContainer()).isEqualTo(model);
        assertThat(modelContent.eInternalContainer()).isEqualTo(model);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEResource() {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        assertThat(model.eResource()).isSameAs(resource);
        assertThat(modelContentObject.eResource()).isSameAs(resource);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEResource() {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        Iterator<EObject> it = resource.getAllContents();

        SampleModel model = (SampleModel) it.next();
        assertThat(model.eResource()).isSameAs(resource);

        SampleModelContentObject modelContent = (SampleModelContentObject) it.next();
        assertThat(modelContent.eResource()).isSameAs(resource);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEDirectResource() {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        assertThat(model.eDirectResource()).isSameAs(resource);
        assertThat(modelContentObject.eDirectResource()).isNull();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEDirectResource() {
        PersistentResource resource = createPersistentStore();
        fillResource(resource);

        Iterator<EObject> it = resource.getAllContents();

        SampleModel model = (SampleModel) it.next();
        assertThat(model.eDirectResource()).isSameAs(resource);

        SampleModelContentObject modelContent = (SampleModelContentObject) it.next();
        assertThat(modelContent.eDirectResource()).isNull();
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(PersistentResource resource) {
        model = EFACTORY.createSampleModel();
        modelContentObject = EFACTORY.createSampleModelContentObject();
        model.getContentObjects().add(modelContentObject);
        resource.getContents().add(model);
    }
}
