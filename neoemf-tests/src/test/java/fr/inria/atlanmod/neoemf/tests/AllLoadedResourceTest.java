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

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;

public abstract class AllLoadedResourceTest extends AllSavedLoadedResourceTest {

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
}
