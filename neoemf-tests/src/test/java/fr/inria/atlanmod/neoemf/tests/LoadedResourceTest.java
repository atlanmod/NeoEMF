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
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LoadedResourceTest extends AbstractBackendTest {

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetElementsContainer() throws IOException {
        PersistentResource resource = fillResource(createPersistentStore());

        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.eContainer()).isNull();

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eContainer()).isSameAs(model);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsContainer() throws IOException {
        PersistentResource resource = fillResource(createPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eContainer()).isNull();

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eContainer()).isSameAs(sampleModel);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    @Ignore("Performance issues") // TODO Check if we have to correct it or not (performance issues)
    public void testGetElementsEInternalContainer() throws IOException {
        PersistentResource resource = fillResource(createPersistentStore());

        InternalEObject model = (InternalEObject) resource.getContents().get(0);
        assertThat(model.eInternalContainer()).isNull();

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eInternalContainer()).isNull();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    @Ignore("Performance issues") // TODO Check if we have to correct it or not (performance issues)
    public void testGetAllContentsEInternalContainer() throws IOException {
        PersistentResource resource = fillResource(createPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eInternalContainer()).isNull();

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eInternalContainer()).isNull();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetElementsEResource() throws IOException {
        PersistentResource resource = fillResource(createPersistentStore());

        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.eResource()).isSameAs(resource);

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eResource()).isSameAs(resource);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEResource() throws IOException {
        PersistentResource resource = fillResource(createPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eResource()).isSameAs(resource);

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eResource()).isSameAs(resource);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetElementsEDirectResource() throws IOException {
        PersistentResource resource = fillResource(createPersistentStore());

        InternalEObject model = (InternalEObject) resource.getContents().get(0);
        assertThat(model.eDirectResource()).isNull();

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eDirectResource()).isNull();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEDirectResource() throws IOException {
        PersistentResource resource = fillResource(createPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eDirectResource()).isNull();

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eDirectResource()).isNull();
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     *
     * @return the resulting {@link PersistentResource} after calling {@link PersistentResource#save(Map)} and {@link
     * PersistentResource#load(Map)}
     *
     * @throws IOException if an I/O error occurs during {@link PersistentResource#save(Map)} or {@link
     *                     PersistentResource#load(Map)}
     */
    private PersistentResource fillResource(PersistentResource resource) throws IOException {
        SampleModel mapSampleModel = EFACTORY.createSampleModel();
        SampleModelContentObject mapSampleContentObject = EFACTORY.createSampleModelContentObject();
        mapSampleModel.getContentObjects().add(mapSampleContentObject);
        resource.getContents().add(mapSampleModel);

        resource.save(context().optionsBuilder().asMap());
        resource.close();

        PersistentResource newResource = context().loadResource(EPACKAGE, file());
        return closeAtExit(newResource);
    }
}
