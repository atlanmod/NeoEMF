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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class LoadedResourceTest extends AbstractBackendTest {

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetElementsContainer() throws IOException {
        PersistentResource resource = createResourceContent(createPersistentStore());

        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.eContainer()).isNull();

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eContainer()).isSameAs(model);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsContainer() throws IOException {
        PersistentResource resource = createResourceContent(createPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eContainer()).isNull(); // "Top Level EObject has a not null container"

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eContainer()).isSameAs(sampleModel); // "Wrong eContainer value"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    @Ignore("Performance issues") // TODO Check if we have to correct it or not (performance issues)
    public void testGetElementsEInternalContainer() throws IOException {
        PersistentResource resource = createResourceContent(createPersistentStore());

        InternalEObject model = (InternalEObject) resource.getContents().get(0);
        assertThat(model.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    @Ignore("Performance issues") // TODO Check if we have to correct it or not (performance issues)
    public void testGetAllContentsEInternalContainer() throws IOException {
        PersistentResource resource = createResourceContent(createPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetElementsEResource() throws IOException {
        PersistentResource resource = createResourceContent(createPersistentStore());

        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.eResource()).isSameAs(resource); // "Wrong eResource value"

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eResource()).isSameAs(resource); // "Wrong eResource value"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEResource() throws IOException {
        PersistentResource resource = createResourceContent(createPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eResource()).isSameAs(resource); // "Wrong eResource value"

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eResource()).isSameAs(resource); // "Wrong eResource value"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetElementsEDirectResource() throws IOException {
        PersistentResource resource = createResourceContent(createPersistentStore());

        InternalEObject model = (InternalEObject) resource.getContents().get(0);
        assertThat(model.eDirectResource()).isNull(); // "eDirectResource must return null"

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eDirectResource()).isNull(); // "eDirectResource must return null"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEDirectResource() throws IOException {
        PersistentResource resource = createResourceContent(createPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eDirectResource()).isNull(); // "eDirectResource must return null"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eDirectResource()).isNull(); // "eDirectResource must return null"
    }

    private PersistentResource createResourceContent(final PersistentResource resource) throws IOException {
        SampleModel mapSampleModel = EFACTORY.createSampleModel();
        SampleModelContentObject mapSampleContentObject = EFACTORY.createSampleModelContentObject();
        mapSampleModel.getContentObjects().add(mapSampleContentObject);
        resource.getContents().add(mapSampleModel);

        resource.save(CommonOptionsBuilder.noOption());
        resource.close();

        PersistentResource newResource = context().loadResource(EPACKAGE, file());
        return closeAtExit(newResource);
    }
}
