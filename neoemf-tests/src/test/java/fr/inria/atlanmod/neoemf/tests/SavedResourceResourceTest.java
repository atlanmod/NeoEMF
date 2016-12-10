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

import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Test;

import java.util.Iterator;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

public class SavedResourceResourceTest extends AllSavedResourceTest {

    @Test
    public void testEResource() {
        checkEResource(resource, model, modelContentObject);
    }

    @Test
    public void testGetAllContentsEResource() {
        getAllContentsEResource(resource);
    }

    @Test
    public void testEDirectResource() {
        checkEDirectResource(resource, model, modelContentObject);
    }

    @Test
    public void testGetAllContentsEDirectResource() {
        getAllContentsEDirectResource(resource);
    }

    private void checkEResource(PersistentResource resource, SampleModel model, SampleModelContentObject modelContentObject) {
        assertThat(model.eResource()).isSameAs(resource); // "Wrong eResource value"
        assertThat(modelContentObject.eResource()).isSameAs(resource); // "Wrong eResource value"
    }

    private void checkEDirectResource(PersistentResource resource, SampleModel model, SampleModelContentObject modelContentObject) {
        assertThat(model.eDirectResource()).isSameAs(resource); // "Wrong eDirectResource value"
        assertThat(modelContentObject.eDirectResource()).isNull(); // "Non top level element eDirectResource is not null"
    }

    private void getAllContentsEDirectResource(PersistentResource resource) {
        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eDirectResource()).isSameAs(resource); // "Wrong eDirectResource value"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eDirectResource()).isNull(); // "Non top level element eDirectResource is not null"
    }
}
