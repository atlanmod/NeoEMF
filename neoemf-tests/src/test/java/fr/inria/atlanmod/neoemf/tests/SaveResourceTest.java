/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.context.ContextProvider;
import fr.inria.atlanmod.neoemf.tests.sample.PrimaryObject;
import fr.inria.atlanmod.neoemf.tests.sample.TargetObject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Iterator;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of a saved {@link PersistentResource}.
 */
@ParametersAreNonnullByDefault
public class SaveResourceTest extends AbstractResourceBasedTest {

    private PrimaryObject primary;
    private TargetObject target;

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testContainer(Context context) throws Exception {
        try (PersistentResource resource = createPersistentResource(context)) {
            fillResource(resource);

            assertThat(primary.eContainer()).isNull();
            assertThat(primary.eInternalContainer()).isNull();

            assertThat(target.eContainer()).isEqualTo(primary);
            assertThat(target.eInternalContainer()).isEqualTo(primary);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testAllContentsContainer(Context context) throws Exception {
        try (PersistentResource resource = createPersistentResource(context)) {
            fillResource(resource);

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
    public void testResource(Context context) throws Exception {
        try (PersistentResource resource = createPersistentResource(context)) {
            fillResource(resource);

            assertThat(primary.eResource()).isSameAs(resource);
            assertThat(target.eResource()).isSameAs(resource);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testAllContentsResource(Context context) throws Exception {
        try (PersistentResource resource = createPersistentResource(context)) {
            fillResource(resource);

            Iterator<EObject> it = resource.getAllContents();

            PrimaryObject primary = PrimaryObject.class.cast(it.next());
            assertThat(primary.eResource()).isSameAs(resource);

            TargetObject target = TargetObject.class.cast(it.next());
            assertThat(target.eResource()).isSameAs(resource);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testDirectResource(Context context) throws Exception {
        try (PersistentResource resource = createPersistentResource(context)) {
            fillResource(resource);

            assertThat(primary.eDirectResource()).isSameAs(resource);
            assertThat(target.eDirectResource()).isNull();
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testAllContentsDirectResource(Context context) throws Exception {
        try (PersistentResource resource = createPersistentResource(context)) {
            fillResource(resource);

            Iterator<EObject> it = resource.getAllContents();

            PrimaryObject primary = PrimaryObject.class.cast(it.next());
            assertThat(primary.eDirectResource()).isSameAs(resource);

            TargetObject target = TargetObject.class.cast(it.next());
            assertThat(target.eDirectResource()).isNull();
        }
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(Resource resource) {
        primary = EFACTORY.createPrimaryObject();
        target = EFACTORY.createTargetObject();
        primary.getManyContainmentReferences().add(target);

        resource.getContents().add(primary);
    }
}
