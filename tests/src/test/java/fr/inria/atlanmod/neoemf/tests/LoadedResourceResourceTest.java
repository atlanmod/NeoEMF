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

import static org.assertj.core.api.Assertions.assertThat;

public class LoadedResourceResourceTest extends AllLoadedResourceTest {

    @Test
    public void testGetElementsEResourceMapDB() {
        getElementsEResource(mapResource);
    }

    @Test
    public void testGetElementsEResourceNeo4j() {
        getElementsEResource(neo4jResource);
    }

    @Test
    public void testGetElementsEResourceTinker() {
        getElementsEResource(tinkerResource);
    }

    @Test
    public void testGetAllContentsEResourceMapDB() {
        getAllContentsEResource(mapResource);
    }

    @Test
    public void testGetAllContentsEResourceNeo4j() {
        getAllContentsEResource(neo4jResource);
    }

    @Test
    public void testGetAllContentsEResourceTinker() {
        getAllContentsEResource(tinkerResource);
    }

    @Test
    public void testGetElementsEDirectResourceMapDB() {
        getElementsEDirectResource(mapResource);
    }

    @Test
    public void testGetElementsEDirectResourceNeo4j() {
        getElementsEDirectResource(neo4jResource);
    }

    @Test
    public void testGetElementsEDirectResourceTinker() {
        getElementsEDirectResource(tinkerResource);
    }

    @Test
    public void testGetAllContentsEDirectResourceMapDB() {
        getAllContentsEDirectResource(mapResource);
    }

    @Test
    public void testGetAllContentsEDirectResourceNeo4j() {
        getAllContentsEDirectResource(neo4jResource);
    }

    @Test
    public void testGetAllContentsEDirectResourceTinker() {
        getAllContentsEDirectResource(tinkerResource);
    }

    private void getElementsEResource(PersistentResource persistentResource) {
        SampleModel model = (SampleModel) persistentResource.getContents().get(0);
        assertThat(model.eResource()).isSameAs(persistentResource); // "Wrong eResource value"

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eResource()).isSameAs(persistentResource); // "Wrong eResource value"
    }

    private void getElementsEDirectResource(PersistentResource persistentResource) {
        InternalEObject model = (InternalEObject) persistentResource.getContents().get(0);
        assertThat(model.eDirectResource()).isNull(); // "eDirectResource must return null"

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eDirectResource()).isNull(); // "eDirectResource must return null"
    }

    private void getAllContentsEDirectResource(PersistentResource persistentResource) {
        Iterator<EObject> it = persistentResource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eDirectResource()).isNull(); // "eDirectResource must return null"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eDirectResource()).isNull(); // "eDirectResource must return null"
    }
}
