/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.provider.ContextProvider;
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
class LoadResourceTest extends AbstractResourceBasedTest {

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    void testElementsContainer(Context context) throws IOException {
        try (PersistentResource resource = createPersistentLoadedResource(context)) {

            PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
            assertThat(primary.eContainer()).isNull();
            assertThat(primary.eInternalContainer()).isNull();

            TargetObject target = primary.getManyContainmentReferences().get(0);
            assertThat(target.eContainer()).isEqualTo(primary);
            assertThat(target.eInternalContainer()).isEqualTo(primary);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    void testAllContentsContainer(Context context) throws IOException {
        try (PersistentResource resource = createPersistentLoadedResource(context)) {

            Iterator<EObject> it = resource.getAllContents();

            PrimaryObject primary = PrimaryObject.class.cast(it.next());
            assertThat(primary.eContainer()).isNull();
            assertThat(primary.eInternalContainer()).isNull();

            TargetObject target = TargetObject.class.cast(it.next());
            assertThat(target.eContainer()).isEqualTo(primary);
            assertThat(target.eInternalContainer()).isEqualTo(primary);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    void testElementsResource(Context context) throws IOException {
        try (PersistentResource resource = createPersistentLoadedResource(context)) {

            PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
            assertThat(primary.eResource()).isSameAs(resource);

            TargetObject target = primary.getManyContainmentReferences().get(0);
            assertThat(target.eResource()).isSameAs(resource);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    void testAllContentsResource(Context context) throws IOException {
        try (PersistentResource resource = createPersistentLoadedResource(context)) {

            Iterator<EObject> it = resource.getAllContents();

            PrimaryObject primary = PrimaryObject.class.cast(it.next());
            assertThat(primary.eResource()).isSameAs(resource);

            TargetObject target = TargetObject.class.cast(it.next());
            assertThat(target.eResource()).isSameAs(resource);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    void testElementsDirectResource(Context context) throws IOException {
        try (PersistentResource resource = createPersistentLoadedResource(context)) {

            PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));
            assertThat(primary.eDirectResource()).isNull();

            TargetObject target = primary.getManyContainmentReferences().get(0);
            assertThat(target.eDirectResource()).isNull();
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    void testAllContentsDirectResource(Context context) throws IOException {
        try (PersistentResource resource = createPersistentLoadedResource(context)) {

            Iterator<EObject> it = resource.getAllContents();

            PrimaryObject primary = PrimaryObject.class.cast(it.next());
            assertThat(primary.eDirectResource()).isNull();

            TargetObject target = TargetObject.class.cast(it.next());
            assertThat(target.eDirectResource()).isNull();
        }
    }

    /**
     * Creates a new persistent resource for the given {@code context}.
     *
     * @return the resulting {@link PersistentResource} after calling {@link PersistentResource#save(Map)} and {@link
     * PersistentResource#load(Map)}
     */
    @Nonnull
    private PersistentResource createPersistentLoadedResource(Context context) throws IOException {
        try (PersistentResource resource = createPersistentResource(context)) {
            PrimaryObject primary = EFACTORY.createPrimaryObject();
            TargetObject target = EFACTORY.createTargetObject();
            primary.getManyContainmentReferences().add(target);

            resource.getContents().add(primary);

            resource.save(context.config());
        }

        return context.loadResource(currentTempFile());
    }
}
