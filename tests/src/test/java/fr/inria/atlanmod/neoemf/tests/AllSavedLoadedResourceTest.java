/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import org.eclipse.emf.ecore.EObject;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public abstract class AllSavedLoadedResourceTest extends AllBackendTest {

    protected void getAllContentsContainer(PersistentResource persistentResource) {
        Iterator<EObject> it = persistentResource.getAllContents();

        EObject sampleModel = it.next();
        assertThat("Top Level EObject has a not null container", sampleModel.eContainer(), nullValue());

        EObject sampleContentObject = it.next();
        assertThat("Wrong eContainer value", sampleContentObject.eContainer(), is(sampleModel));
    }

    protected void getAllContentsEResource(PersistentResource persistentResource) {
        Iterator<EObject> it = persistentResource.getAllContents();

        EObject sampleModel = it.next();
        assertThat("Wrong eResource value", sampleModel.eResource().equals(persistentResource), is(true));

        EObject sampleContentObject = it.next();
        assertThat("Wrong eResource value", sampleContentObject.eResource().equals(persistentResource), is(true));
    }

}
