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

public class SavedResourceContainerTest extends AllSavedResourceTest {

    @Test
    public void testEContainer() {
        checkEContainer(model, modelContentObject);
    }

    @Test
    public void testGetAllContentsContainer() {
        getAllContentsContainer(resource);
    }

    @Test
    public void testEInternalContainer() {
        checkEInternalContainer(model, modelContentObject);
    }

    @Test
    public void testGetAllContentsEInternalContainer() {
        getAllContentsEInternalContainer(resource);
    }

    private void checkEContainer(SampleModel model, SampleModelContentObject modelContentObject) {
        assertThat(model.eContainer()).isNull(); // "Top Level EObject has a not null container"
        assertThat(modelContentObject.eContainer()).isSameAs(model); // "Wrong eContainer value"
    }

    private void checkEInternalContainer(SampleModel model, SampleModelContentObject modelContentObject) {
        assertThat(model.eInternalContainer()).isNull(); // "Top Level EObject has a not null internal container"
        assertThat(modelContentObject.eInternalContainer()).isSameAs((InternalEObject) model); // "Wrong eInternalContainer value"
    }

    private void getAllContentsEInternalContainer(PersistentResource resource) {
        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eInternalContainer()).isNull(); // "Top Level EObject has a not null container"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eInternalContainer()).isSameAs(sampleModel); // "Wrong eInternalContainer value"
    }
}
