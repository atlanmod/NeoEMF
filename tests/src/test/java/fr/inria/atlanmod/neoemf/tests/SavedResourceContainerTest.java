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
import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class SavedResourceContainerTest extends AllSavedResourceTest {

    @Test
    public void testEContainerMapDB() {
        checkEContainer(mapSampleModel, mapSampleContentObject);
    }

    @Test
    public void testEContainerNeo4j() {
        checkEContainer(neo4jSampleModel, neo4jSampleContentObject);
    }

    @Test
    public void testEContainerTinker() {
        checkEContainer(tinkerSampleModel, tinkerSampleContentObject);
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
    public void testEInternalContainerMapDB() {
        checkEInternalContainer(mapSampleModel, mapSampleContentObject);
    }

    @Test
    public void testEInternalContainerNeo4j() {
        checkEInternalContainer(neo4jSampleModel, neo4jSampleContentObject);
    }

    @Test
    public void testEInternalContainerTinker() {
        checkEInternalContainer(tinkerSampleModel, tinkerSampleContentObject);
    }

    @Test
    public void testGetAllContentsEInternalContainerMapDB() {
        getAllContentsEInternalContainer(mapResource);
    }

    @Test
    public void testGetAllContentsEInternalContainerNeo4j() {
        getAllContentsEInternalContainer(neo4jResource);
    }

    @Test
    public void testGetAllContentsEInternalContainerTinker() {
        getAllContentsEInternalContainer(tinkerResource);
    }

    private void checkEContainer(SampleModel sampleModel, SampleModelContentObject sampleModelContentObject) {
        assertThat(sampleModel.eContainer()).isNull(); // "Top Level EObject has a not null container"
        assertThat(sampleModelContentObject.eContainer()).isSameAs(sampleModel); // "Wrong eContainer value"
    }

    private void checkEInternalContainer(SampleModel sampleModel, SampleModelContentObject sampleModelContentObject) {
        InternalEObject internalMapSampleModel = (InternalEObject) sampleModel;
        assertThat(internalMapSampleModel.eInternalContainer()).isNull(); // "Top Level EObject has a not null internal container"

        InternalEObject internalMapSampleContentObject = (InternalEObject) sampleModelContentObject;
        assertThat(internalMapSampleContentObject.eInternalContainer()).isSameAs(internalMapSampleModel); // "Wrong eInternalContainer value"
    }

    private void getAllContentsEInternalContainer(PersistentResource persistentResource) {
        Iterator<EObject> it = persistentResource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eInternalContainer()).isNull(); // "Top Level EObject has a not null container"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eInternalContainer()).isSameAs(sampleModel); // "Wrong eInternalContainer value"
    }
}
