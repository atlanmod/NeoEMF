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

import java.util.ArrayList;
import java.util.List;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

/**
 * Test class for the contains method, related to performance issue descibed in #30
 * <a href="https://github.com/atlanmod/NeoEMF/issues/30">https://github.com/atlanmod/NeoEMF/issues/30</a>
 */
public abstract class AllContainsTest extends AllBackendTest {

    protected SampleModel model;
    protected List<SampleModelContentObject> addedContent;

    @Override
    public void tearDown() throws Exception {
        model = null;
        addedContent = null;
        super.tearDown();
    }

    protected void createResourceContent(PersistentResource resource, int count) {
        addedContent = new ArrayList<>();
        model = factory.createSampleModel();
        model.setName("Model");
        for (int i = 0; i < count; i++) {
            SampleModelContentObject c = factory.createSampleModelContentObject();
            c.setName("c" + i);
            addedContent.add(c);
            model.getContentObjects().add(c);
        }
        resource.getContents().add(model);
    }

    protected void assertContainsExactly(PersistentResource resource, int count) {
        SampleModel m = (SampleModel) resource.getContents().get(0);
        assertThat(m.getContentObjects()).hasSize(count);
        assertThat(m.getContentObjects()).containsExactlyElementsOf(addedContent);
    }
}
