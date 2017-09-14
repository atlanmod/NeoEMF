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

import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.sample.PrimaryObject;
import fr.inria.atlanmod.neoemf.tests.sample.TargetObject;

import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of a loaded {@link org.eclipse.emf.ecore.resource.Resource}.
 */
public class LoadResourceTest extends AbstractBackendTest {

    @Test
    public void testGetElementsContainer() throws IOException {
        PersistentResource resource = fillResource(newPersistentStore());

        PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
        assertThat(primary.eContainer()).isNull();
        assertThat(primary.eInternalContainer()).isNull();

        TargetObject target = primary.getManyContainmentReferences().get(0);
        assertThat(target.eContainer()).isEqualTo(primary);
        assertThat(target.eInternalContainer()).isEqualTo(primary);
    }

    @Test
    public void testGetAllContentsContainer() throws IOException {
        PersistentResource resource = fillResource(newPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        PrimaryObject primary = PrimaryObject.class.cast(it.next());
        assertThat(primary.eContainer()).isNull();
        assertThat(primary.eInternalContainer()).isNull();

        TargetObject target = TargetObject.class.cast(it.next());
        assertThat(target.eContainer()).isEqualTo(primary);
        assertThat(target.eInternalContainer()).isEqualTo(primary);
    }

    @Test
    public void testGetElementsEResource() throws IOException {
        PersistentResource resource = fillResource(newPersistentStore());

        PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
        assertThat(primary.eResource()).isSameAs(resource);

        TargetObject target = primary.getManyContainmentReferences().get(0);
        assertThat(target.eResource()).isSameAs(resource);
    }

    @Test
    public void testGetAllContentsEResource() throws IOException {
        PersistentResource resource = fillResource(newPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        PrimaryObject primary = PrimaryObject.class.cast(it.next());
        assertThat(primary.eResource()).isSameAs(resource);

        TargetObject target = TargetObject.class.cast(it.next());
        assertThat(target.eResource()).isSameAs(resource);
    }

    @Test
    public void testGetElementsEDirectResource() throws IOException {
        PersistentResource resource = fillResource(newPersistentStore());

        PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
        assertThat(primary.eDirectResource()).isNull();

        TargetObject target = primary.getManyContainmentReferences().get(0);
        assertThat(target.eDirectResource()).isNull();
    }

    @Test
    public void testGetAllContentsEDirectResource() throws IOException {
        PersistentResource resource = fillResource(newPersistentStore());

        Iterator<EObject> it = resource.getAllContents();

        PrimaryObject primary = PrimaryObject.class.cast(it.next());
        assertThat(primary.eDirectResource()).isNull();

        TargetObject target = TargetObject.class.cast(it.next());
        assertThat(target.eDirectResource()).isNull();
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     *
     * @return the resulting {@link PersistentResource} after calling {@link PersistentResource#save(Map)} and {@link
     * PersistentResource#load(Map)}
     *
     * @throws IOException if an I/O error occurs during {@link PersistentResource#save(Map)} or {@link
     *                     PersistentResource#load(Map)}
     */
    private PersistentResource fillResource(PersistentResource resource) throws IOException {
        PrimaryObject primary = EFACTORY.createPrimaryObject();
        TargetObject target = EFACTORY.createTargetObject();
        primary.getManyContainmentReferences().add(target);
        resource.getContents().add(primary);

        resource.save(context().optionsBuilder().asMap());
        resource.close();

        PersistentResource newResource = context().loadResource(EPACKAGE, file());
        return closeAtExit(newResource);
    }
}
