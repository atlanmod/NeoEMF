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

import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class LoadedResourceContainerTest extends AllLoadedResourceTest {

    @Test
    public void testGetElementsContainerMapDB() {
        getElementsContainer(mapResource);
    }

    @Test
    public void testGetElementsContainerNeo4j() {
        getElementsContainer(neo4jResource);
    }

    @Test
    public void testGetElementsContainerTinker() {
        getElementsContainer(tinkerResource);
    }

    @Test
    public void testGetAllContentsContainerMapDB() {
        getAllContentsContainer(mapResource);
    }

    @Test
    public void testGetAllContentsContainerNeo4j() {
        getAllContentsContainer(neo4jResource);
    }

    @Test
    public void testGetAllContentsContainerTinker() {
        getAllContentsContainer(tinkerResource);
    }

    @Test
    @Ignore("Performance issues")
    public void testGetElementsEInternalContainerMapDB() {
        getElementsEInternalContainer(mapResource);
    }

    @Test
    @Ignore("Performance issues")
    public void testGetElementsEInternalContainerNeo4j() {
        getElementsEInternalContainer(neo4jResource);
    }

    @Test
    @Ignore("Performance issues")
    public void testGetElementsEInternalContainerTinker() {
        getElementsEInternalContainer(tinkerResource);
    }

    @Test
    @Ignore("Performance issues")
    public void testGetAllContentsEInternalContainerMapDB() {
        getAllContentsEInternalContainer(mapResource);
    }

    @Test
    @Ignore("Performance issues")
    public void testGetAllContentsEInternalContainerNeo4j() {
        getAllContentsEInternalContainer(neo4jResource);
    }

    @Test
    @Ignore("Performance issues")
    public void testGetAllContentsEInternalContainerTinker() {
        getAllContentsEInternalContainer(tinkerResource);
    }

    private void getElementsContainer(PersistentResource persistentResource) {
        SampleModel model = (SampleModel) persistentResource.getContents().get(0);
        assertThat(model.eContainer()).isNull();

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eContainer()).isSameAs(model);
    }

    private void getElementsEInternalContainer(PersistentResource persistentResource) {
        // TODO Check if we have to correct it or not (performance issues)
        InternalEObject model = (InternalEObject) persistentResource.getContents().get(0);
        assertThat(model.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"
    }

    private void getAllContentsEInternalContainer(PersistentResource persistentResource) {
        // TODO Check if we have to correct it or not (performance issues)
        Iterator<EObject> it = persistentResource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"
    }
}
