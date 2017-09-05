/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.sample.PrimaryObject;
import fr.inria.atlanmod.neoemf.tests.sample.TargetObject;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case for the contains method, related to performance issue described in the issue <a
 * href="https://github.com/atlanmod/NeoEMF/issues/30">#30</a>.
 */
public class ContainsTest extends AbstractBackendTest {

    @Test
    @Category(Tags.PersistentTests.class)
    public void testContainsPersistent3Elements() {
        PersistentResource resource = newPersistentStore();
        List<TargetObject> content = fillResource(resource, 3);

        assertContainsExactly(resource, content);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testContainsPersistent4Elements() {
        PersistentResource resource = newPersistentStore();
        List<TargetObject> content = fillResource(resource, 4);

        assertContainsExactly(resource, content);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testContainsPersistent5Elements() {
        PersistentResource resource = newPersistentStore();
        List<TargetObject> content = fillResource(resource, 5);

        assertContainsExactly(resource, content);
    }

    @Test
    @Category(Tags.TransientTests.class)
    public void testContainsTransient3Elements() {
        PersistentResource resource = newTransientStore();
        List<TargetObject> content = fillResource(resource, 3);

        assertContainsExactly(resource, content);
    }

    @Test
    @Category(Tags.TransientTests.class)
    public void testContainsTransient4Elements() {
        PersistentResource resource = newTransientStore();
        List<TargetObject> content = fillResource(resource, 4);

        assertContainsExactly(resource, content);
    }

    @Test
    @Category(Tags.TransientTests.class)
    public void testContainsTransient5Elements() {
        PersistentResource resource = newTransientStore();
        List<TargetObject> content = fillResource(resource, 5);

        assertContainsExactly(resource, content);
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     * @param count    the number of {@link TargetObject} to add
     *
     * @return a list of all created {@link TargetObject}s
     */
    private List<TargetObject> fillResource(PersistentResource resource, int count) {
        List<TargetObject> targets = new ArrayList<>();

        PrimaryObject primary = EFACTORY.createPrimaryObject();
        primary.setName("Model");

        IntStream.rangeClosed(0, count).forEach(i -> {
            TargetObject target = EFACTORY.createTargetObject();
            target.setName("target" + i);
            targets.add(target);
            primary.getManyContainmentReferences().add(target);
        });

        resource.getContents().add(primary);

        return targets;
    }

    private void assertContainsExactly(PersistentResource resource, List<TargetObject> addedContent) {
        PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
        assertThat(primary.getManyContainmentReferences()).hasSize(addedContent.size());
        assertThat(primary.getManyContainmentReferences()).containsExactlyElementsOf(addedContent);
    }
}
