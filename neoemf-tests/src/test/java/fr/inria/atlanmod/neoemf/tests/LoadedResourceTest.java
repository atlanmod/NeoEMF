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

import fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class LoadedResourceTest extends AllBackendTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createPersistentStore();

        SampleModel mapSampleModel = factory.createSampleModel();
        SampleModelContentObject mapSampleContentObject = factory.createSampleModelContentObject();
        mapSampleModel.getContentObjects().add(mapSampleContentObject);
        resource.getContents().add(mapSampleModel);

        resource.save(PersistenceOptionsBuilder.noOption());

        resource.close();

        ResourceSet rSet = new ResourceSetImpl();
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(helper.uriScheme(), PersistentResourceFactory.getInstance());
        resource = (PersistentResource) rSet.getResource(helper.createFileUri(resourceFile), true);

        resource.load(PersistenceOptionsBuilder.noOption());
    }

    @Test
    public void testGetElementsContainer() {
        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.eContainer()).isNull();

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eContainer()).isSameAs(model);
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
    @Ignore("Performance issues")
    public void testGetElementsEInternalContainer() {
        // TODO Check if we have to correct it or not (performance issues)
        InternalEObject model = (InternalEObject) resource.getContents().get(0);
        assertThat(model.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"
    }

    @Test
    @Ignore("Performance issues")
    public void testGetAllContentsEInternalContainer() {
        // TODO Check if we have to correct it or not (performance issues)
        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eInternalContainer()).isNull(); // "eInternalContainer must return null if eContainer has not been called"
    }

    @Test
    public void testGetElementsEResource() {
        SampleModel model = (SampleModel) resource.getContents().get(0);
        assertThat(model.eResource()).isSameAs(resource); // "Wrong eResource value"

        SampleModelContentObject modelContent = model.getContentObjects().get(0);
        assertThat(modelContent.eResource()).isSameAs(resource); // "Wrong eResource value"
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
    public void testGetElementsEDirectResource() {
        InternalEObject model = (InternalEObject) resource.getContents().get(0);
        assertThat(model.eDirectResource()).isNull(); // "eDirectResource must return null"

        InternalEObject modelContent = ((SampleModel) model).getContentObjects().get(0);
        assertThat(modelContent.eDirectResource()).isNull(); // "eDirectResource must return null"
    }

    @Test
    public void testGetAllContentsEDirectResource() {
        Iterator<EObject> it = resource.getAllContents();

        InternalEObject sampleModel = (InternalEObject) it.next();
        assertThat(sampleModel.eDirectResource()).isNull(); // "eDirectResource must return null"

        InternalEObject sampleContentObject = (InternalEObject) it.next();
        assertThat(sampleContentObject.eDirectResource()).isNull(); // "eDirectResource must return null"
    }
}
