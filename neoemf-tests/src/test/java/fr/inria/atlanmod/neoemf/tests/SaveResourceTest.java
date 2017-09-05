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

import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of a saved {@link org.eclipse.emf.ecore.resource.Resource}.
 */
public class SaveResourceTest extends AbstractBackendTest {

    private PrimaryObject primary;
    private TargetObject target;

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEContainer() {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        assertThat(primary.eContainer()).isNull();
        assertThat(primary.eInternalContainer()).isNull();

        assertThat(target.eContainer()).isEqualTo(primary);
        assertThat(target.eInternalContainer()).isEqualTo(primary);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsContainer() {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        Iterator<EObject> it = resource.getAllContents();

        PrimaryObject primary = PrimaryObject.class.cast(it.next());
        assertThat(primary.eContainer()).isNull();
        assertThat(primary.eInternalContainer()).isNull();

        TargetObject target = TargetObject.class.cast(it.next());
        assertThat(target.eContainer()).isEqualTo(primary);
        assertThat(target.eInternalContainer()).isEqualTo(primary);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEResource() {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        assertThat(primary.eResource()).isSameAs(resource);
        assertThat(target.eResource()).isSameAs(resource);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEResource() {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        Iterator<EObject> it = resource.getAllContents();

        PrimaryObject primary = PrimaryObject.class.cast(it.next());
        assertThat(primary.eResource()).isSameAs(resource);

        TargetObject target = TargetObject.class.cast(it.next());
        assertThat(target.eResource()).isSameAs(resource);
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEDirectResource() {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        assertThat(primary.eDirectResource()).isSameAs(resource);
        assertThat(target.eDirectResource()).isNull();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testGetAllContentsEDirectResource() {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        Iterator<EObject> it = resource.getAllContents();

        PrimaryObject primary = PrimaryObject.class.cast(it.next());
        assertThat(primary.eDirectResource()).isSameAs(resource);

        TargetObject target = TargetObject.class.cast(it.next());
        assertThat(target.eDirectResource()).isNull();
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(PersistentResource resource) {
        primary = EFACTORY.createPrimaryObject();
        target = EFACTORY.createTargetObject();
        primary.getManyContainmentReferences().add(target);
        resource.getContents().add(primary);
    }
}
