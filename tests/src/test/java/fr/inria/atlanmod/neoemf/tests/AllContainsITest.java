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
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModel;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.SampleModelContentObject;

import org.eclipse.emf.ecore.resource.Resource;

import java.util.ArrayList;
import java.util.List;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

/**
 * Test class for the contains method, related to performance issue descibed in #30
 * <a href="https://github.com/atlanmod/NeoEMF/issues/30">https://github.com/atlanmod/NeoEMF/issues/30</a>
 */
public class AllContainsITest extends AllITest {

    protected MapSampleFactory factory;

    protected SampleModel m;
    protected List<SampleModelContentObject> addedContent;

    @Override
    public void setUp() throws Exception {
        factory = MapSampleFactory.eINSTANCE;
        ePackage = MapSamplePackage.eINSTANCE;
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        m = null;
        addedContent = null;
        super.tearDown();
    }

    protected void createResourceContent(Resource r, int cCount) {
        addedContent = new ArrayList<>();
        m = factory.createSampleModel();
        m.setName("Model");
        for (int i = 0; i < cCount; i++) {
            SampleModelContentObject c = factory.createSampleModelContentObject();
            c.setName("c" + i);
            addedContent.add(c);
            m.getContentObjects().add(c);
        }
        r.getContents().add(m);
    }

    protected void checkContainsResult(PersistentResource r, int cCount) {
        SampleModel m = (SampleModel) r.getContents().get(0);
        assertThat(m.getContentObjects()).hasSize(cCount);
        assertThat(m.getContentObjects()).containsExactlyElementsOf(addedContent);
    }
}
