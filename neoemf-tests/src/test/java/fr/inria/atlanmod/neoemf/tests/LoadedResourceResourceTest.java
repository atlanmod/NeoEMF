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

public class LoadedResourceResourceTest extends AllLoadedResourceTest {

    @Test
    public void testGetElementsEResource() {
        getElementsEResource(resource);
    }

    @Test
    public void testGetAllContentsEResource() {
        getAllContentsEResource(resource);
    }

    @Test
    public void testGetElementsEDirectResource() {
        getElementsEDirectResource(resource);
    }

    @Test
    public void testGetAllContentsEDirectResource() {
        getAllContentsEDirectResource(resource);
    }

    private void getElementsEResource(PersistentResource resource) {
        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.eResource()).isSameAs(resource); // "Wrong eResource value"

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eResource()).isSameAs(resource); // "Wrong eResource value"
    }

    private void getElementsEDirectResource(PersistentResource resource) {
        InternalEObject model = (InternalEObject) resource.getContents().get(0);
        assertThat(model.eDirectResource()).isNull(); // "eDirectResource must return null"

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eDirectResource()).isNull(); // "eDirectResource must return null"
    }

    private void getAllContentsEDirectResource(PersistentResource resource) {
        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eDirectResource()).isNull(); // "eDirectResource must return null"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eDirectResource()).isNull(); // "eDirectResource must return null"
    }
}
