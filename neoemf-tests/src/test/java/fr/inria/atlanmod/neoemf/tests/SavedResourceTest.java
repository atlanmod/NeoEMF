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

import fr.inria.atlanmod.neoemf.context.Tags;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class SavedResourceTest extends AllBackendTest {

    private SampleModel model;
    private SampleModelContentObject modelContentObject;

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEContainer() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        assertThat(model.eContainer()).isNull(); // "Top Level EObject has a not null container"
        assertThat(modelContentObject.eContainer()).isSameAs(model); // "Wrong eContainer value"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsContainer() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eContainer()).isNull(); // "Top Level EObject has a not null container"

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eContainer()).isSameAs(sampleModel); // "Wrong eContainer value"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEInternalContainer() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        assertThat(model.eInternalContainer()).isNull(); // "Top Level EObject has a not null internal container"
        assertThat(modelContentObject.eInternalContainer()).isSameAs(model); // "Wrong eInternalContainer value"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEInternalContainer() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eInternalContainer()).isNull(); // "Top Level EObject has a not null container"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eInternalContainer()).isSameAs(sampleModel); // "Wrong eInternalContainer value"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEResource() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        assertThat(model.eResource()).isSameAs(resource); // "Wrong eResource value"
        assertThat(modelContentObject.eResource()).isSameAs(resource); // "Wrong eResource value"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEResource() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eResource()).isSameAs(resource); // "Wrong eResource value"

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eResource()).isSameAs(resource); // "Wrong eResource value"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEDirectResource() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        assertThat(model.eDirectResource()).isSameAs(resource); // "Wrong eDirectResource value"
        assertThat(modelContentObject.eDirectResource()).isNull(); // "Non top level element eDirectResource is not null"
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEDirectResource() {
        PersistentResource resource = createPersistentStore();
        createResourceContent(resource);

        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eDirectResource()).isSameAs(resource); // "Wrong eDirectResource value"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eDirectResource()).isNull(); // "Non top level element eDirectResource is not null"
    }

    private void createResourceContent(final PersistentResource resource) {
        model = EFACTORY.createSampleModel();
        modelContentObject = EFACTORY.createSampleModelContentObject();
        model.getContentObjects().add(modelContentObject);
        resource.getContents().add(model);
    }
}
