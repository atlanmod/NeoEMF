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

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

/**
 * Test class for the contains method, related to performance issue descibed in #30
 * <a href="https://github.com/atlanmod/NeoEMF/issues/30">https://github.com/atlanmod/NeoEMF/issues/30</a>
 */
public class ContainsTest extends AllBackendTest {

    @Test
    public void testContainsPersistent3Elements() {
        PersistentResource resource = createPersistentStore();
        List<SampleModelContentObject> content = createResourceContent(resource, 3);

        assertContainsExactly(resource, content);
    }

    @Test
    public void testContainsPersistent4Elements() {
        PersistentResource resource = createPersistentStore();
        List<SampleModelContentObject> content = createResourceContent(resource, 4);

        assertContainsExactly(resource, content);
    }

    @Test
    public void testContainsPersistent5Elements() {
        PersistentResource resource = createPersistentStore();
        List<SampleModelContentObject> content = createResourceContent(resource, 5);

        assertContainsExactly(resource, content);
    }

    @Test
    public void testContainsTransient3Elements() {
        PersistentResource resource = createTransientStore();
        List<SampleModelContentObject> content = createResourceContent(resource, 3);

        assertContainsExactly(resource, content);
    }

    @Test
    public void testContainsTransient4Elements() {
        PersistentResource resource = createTransientStore();
        List<SampleModelContentObject> content = createResourceContent(resource, 4);

        assertContainsExactly(resource, content);
    }

    @Test
    public void testContainsTransient5Elements() {
        PersistentResource resource = createTransientStore();
        List<SampleModelContentObject> content = createResourceContent(resource, 5);

        assertContainsExactly(resource, content);
    }

    private List<SampleModelContentObject> createResourceContent(final PersistentResource resource, final int count) {
        List<SampleModelContentObject> addedContent = new ArrayList<>();

        SampleModel model = EFACTORY.createSampleModel();
        model.setName("Model");

        IntStream.rangeClosed(0, count).forEach(i -> {
            SampleModelContentObject c = EFACTORY.createSampleModelContentObject();
            c.setName("c" + i);
            addedContent.add(c);
            model.getContentObjects().add(c);
        });

        resource.getContents().add(model);

        return addedContent;
    }

    private void assertContainsExactly(final PersistentResource resource, final List<SampleModelContentObject> addedContent) {
        SampleModel m = (SampleModel) resource.getContents().get(0);
        assertThat(m.getContentObjects()).hasSize(addedContent.size());
        assertThat(m.getContentObjects()).containsExactlyElementsOf(addedContent);
    }
}
