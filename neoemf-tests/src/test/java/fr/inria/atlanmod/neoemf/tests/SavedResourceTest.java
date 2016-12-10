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

import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class SavedResourceTest extends AllBackendTest {

    protected SampleModel model;
    protected SampleModelContentObject modelContentObject;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createPersistentStore();

        model = factory.createSampleModel();
        modelContentObject = factory.createSampleModelContentObject();
        model.getContentObjects().add(modelContentObject);
        resource.getContents().add(model);
    }

    @Test
    public void testEContainer() {
        assertThat(model.eContainer()).isNull(); // "Top Level EObject has a not null container"
        assertThat(modelContentObject.eContainer()).isSameAs(model); // "Wrong eContainer value"
    }

    @Test
    public void testGetAllContentsContainer() {
        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eContainer()).isNull(); // "Top Level EObject has a not null container"

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eContainer()).isSameAs(sampleModel); // "Wrong eContainer value"
    }

    @Test
    public void testEInternalContainer() {
        assertThat(model.eInternalContainer()).isNull(); // "Top Level EObject has a not null internal container"
        assertThat(modelContentObject.eInternalContainer()).isSameAs((InternalEObject) model); // "Wrong eInternalContainer value"
    }

    @Test
    public void testGetAllContentsEInternalContainer() {
        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eInternalContainer()).isNull(); // "Top Level EObject has a not null container"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eInternalContainer()).isSameAs(sampleModel); // "Wrong eInternalContainer value"
    }

    @Test
    public void testEResource() {
        assertThat(model.eResource()).isSameAs(resource); // "Wrong eResource value"
        assertThat(modelContentObject.eResource()).isSameAs(resource); // "Wrong eResource value"
    }

    @Test
    public void testGetAllContentsEResource() {
        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eResource()).isSameAs(resource); // "Wrong eResource value"

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eResource()).isSameAs(resource); // "Wrong eResource value"
    }

    @Test
    public void testEDirectResource() {
        assertThat(model.eDirectResource()).isSameAs(resource); // "Wrong eDirectResource value"
        assertThat(modelContentObject.eDirectResource()).isNull(); // "Non top level element eDirectResource is not null"
    }

    @Test
    public void testGetAllContentsEDirectResource() {
        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eDirectResource()).isSameAs(resource); // "Wrong eDirectResource value"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eDirectResource()).isNull(); // "Non top level element eDirectResource is not null"
    }
}
