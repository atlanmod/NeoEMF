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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.context.ContextProvider;
import fr.inria.atlanmod.neoemf.tests.sample.PrimaryObject;
import fr.inria.atlanmod.neoemf.tests.sample.TargetObject;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of a loaded {@link PersistentResource}.
 */
@ParametersAreNonnullByDefault
public class LoadResourceTest extends AllContextTest {

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testElementsContainer(Context context) throws IOException {
        PersistentResource resource = createResource(context);

        PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
        assertThat(primary.eContainer()).isNull();
        assertThat(primary.eInternalContainer()).isNull();

        TargetObject target = primary.getManyContainmentReferences().get(0);
        assertThat(target.eContainer()).isEqualTo(primary);
        assertThat(target.eInternalContainer()).isEqualTo(primary);
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testAllContentsContainer(Context context) throws IOException {
        PersistentResource resource = createResource(context);

        Iterator<EObject> it = resource.getAllContents();

        PrimaryObject primary = PrimaryObject.class.cast(it.next());
        assertThat(primary.eContainer()).isNull();
        assertThat(primary.eInternalContainer()).isNull();

        TargetObject target = TargetObject.class.cast(it.next());
        assertThat(target.eContainer()).isEqualTo(primary);
        assertThat(target.eInternalContainer()).isEqualTo(primary);
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testElementsResource(Context context) throws IOException {
        PersistentResource resource = createResource(context);

        PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
        assertThat(primary.eResource()).isSameAs(resource);

        TargetObject target = primary.getManyContainmentReferences().get(0);
        assertThat(target.eResource()).isSameAs(resource);
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testAllContentsResource(Context context) throws IOException {
        PersistentResource resource = createResource(context);

        Iterator<EObject> it = resource.getAllContents();

        PrimaryObject primary = PrimaryObject.class.cast(it.next());
        assertThat(primary.eResource()).isSameAs(resource);

        TargetObject target = TargetObject.class.cast(it.next());
        assertThat(target.eResource()).isSameAs(resource);
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testElementsDirectResource(Context context) throws IOException {
        PersistentResource resource = createResource(context);

        PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
        assertThat(primary.eDirectResource()).isNull();

        TargetObject target = primary.getManyContainmentReferences().get(0);
        assertThat(target.eDirectResource()).isNull();
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testAllContentsDirectResource(Context context) throws IOException {
        PersistentResource resource = createResource(context);

        Iterator<EObject> it = resource.getAllContents();

        PrimaryObject primary = PrimaryObject.class.cast(it.next());
        assertThat(primary.eDirectResource()).isNull();

        TargetObject target = TargetObject.class.cast(it.next());
        assertThat(target.eDirectResource()).isNull();
    }

    /**
     * Creates a new persistent resource for the given {@code context}.
     *
     * @return the resulting {@link PersistentResource} after calling {@link PersistentResource#save(Map)} and {@link
     * PersistentResource#load(Map)}
     *
     * @throws IOException if an I/O error occurs during {@link PersistentResource#save(Map)} or {@link
     *                     PersistentResource#load(Map)}
     */
    @Nonnull
    private PersistentResource createResource(Context context) throws IOException {
        PersistentResource resource = newPersistentResource(context);

        PrimaryObject primary = EFACTORY.createPrimaryObject();
        TargetObject target = EFACTORY.createTargetObject();
        primary.getManyContainmentReferences().add(target);
        resource.getContents().add(primary);

        resource.save(context.optionsBuilder().asMap());
        resource.unload();

        PersistentResource newResource = context.loadResource(EPACKAGE, file());
        return closeAtExit(newResource);
    }
}
