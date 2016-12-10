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

import org.eclipse.emf.ecore.EObject;

import java.util.Iterator;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

public abstract class AllSavedLoadedResourceTest extends AllBackendTest {

    protected void getAllContentsContainer(PersistentResource resource) {
        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eContainer()).isNull(); // "Top Level EObject has a not null container"

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eContainer()).isSameAs(sampleModel); // "Wrong eContainer value"
    }

    protected void getAllContentsEResource(PersistentResource resource) {
        Iterator<EObject> it = resource.getAllContents();

        EObject sampleModel = it.next();
        assertThat(sampleModel.eResource()).isSameAs(resource); // "Wrong eResource value"

        EObject sampleContentObject = it.next();
        assertThat(sampleContentObject.eResource()).isSameAs(resource); // "Wrong eResource value"
    }
}
