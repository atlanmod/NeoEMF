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

import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.junit.Before;

public abstract class AllSavedResourceTest extends AllSavedLoadedResourceTest {

    protected SampleModel model;
    protected SampleModelContentObject modelContentObject;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createPersistentStore();

        model = factory.createSampleModel();
        modelContentObject = factory.createSampleModelContentObject();
        model.getContentObjects().add(modelContentObject);
        resource.getContents().add(model);
    }
}
