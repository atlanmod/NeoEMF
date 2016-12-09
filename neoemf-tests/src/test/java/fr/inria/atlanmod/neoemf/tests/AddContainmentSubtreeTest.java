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

import org.eclipse.emf.ecore.InternalEObject;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

/**
 * Checks that adding a transient containment sub-tree to an existing {@link PersistentResource} add all its elements to
 * the resource.
 */
public class AddContainmentSubtreeTest extends AllContainmentTest {

    @Override
    protected void createResourceContent(PersistentResource resource) {
        p1 = factory.createPack();
        p1.setName("p1");

        resource.getContents().add(p1);

        p2 = factory.createPack();
        p2.setName("p2");
        pc1 = factory.createPackContent();
        p3 = factory.createPack();
        p3.setName("p3");
        p2.getPacks().add(p3);
        pc1.setName("pc1");
        p3.getOwnedContents().add(pc1);

        p1.getPacks().add(p2);
    }

    @Override
    protected void addContainmentSubTree(PersistentResource resource, Class<? extends InternalEObject.EStore> clazz) {
        assertThat(p1.eStore()).isInstanceOf(clazz);
        assertThat(p2.eStore()).isInstanceOf(clazz);
        assertThat(p3.eStore()).isInstanceOf(clazz);
        assertThat(pc1.eStore()).isInstanceOf(clazz);

        assertThat(p1.resource()).isSameAs(resource);
        assertThat(p2.resource()).isSameAs(resource);
        assertThat(p3.resource()).isSameAs(resource);
        assertThat(pc1.resource()).isSameAs(resource);
    }
}
