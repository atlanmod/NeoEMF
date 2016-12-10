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
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

public class LoadedResourceContainerTest extends AllLoadedResourceTest {

    @Test
    public void testGetElementsContainer() {
        getElementsContainer(resource);
    }

    @Test
    public void testGetAllContentsContainer() {
        getAllContentsContainer(resource);
    }

    @Test
    @Ignore("Performance issues")
    public void testGetElementsEInternalContainer() {
        getElementsEInternalContainer(resource);
    }

    @Test
    @Ignore("Performance issues")
    public void testGetAllContentsEInternalContainer() {
        getAllContentsEInternalContainer(resource);
    }

    private void getElementsContainer(PersistentResource resource) {
        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.eContainer()).isNull();

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eContainer()).isSameAs(model);
    }

    private void getElementsEInternalContainer(PersistentResource resource) {
        // TODO Check if we have to correct it or not (performance issues)
        InternalEObject model = (InternalEObject) resource.getContents().get(0);
        assertThat(model.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"
    }

    private void getAllContentsEInternalContainer(PersistentResource resource) {
        // TODO Check if we have to correct it or not (performance issues)
        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"
    }
}
