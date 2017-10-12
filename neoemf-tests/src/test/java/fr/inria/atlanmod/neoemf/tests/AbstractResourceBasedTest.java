/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.commons.AbstractFileBasedTest;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.sample.SampleFactory;
import fr.inria.atlanmod.neoemf.tests.sample.SamplePackage;

import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The base class for testing on different {@link Context}s.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractResourceBasedTest extends AbstractFileBasedTest {

    /**
     * The {@link org.eclipse.emf.ecore.EFactory} of the test model.
     */
    @Nonnull
    protected static final SampleFactory EFACTORY = SampleFactory.eINSTANCE;

    /**
     * The {@link org.eclipse.emf.ecore.EPackage} of the test model.
     */
    @Nonnull
    protected static final SamplePackage EPACKAGE = SamplePackage.eINSTANCE;

    /**
     * Registers all {@link fr.inria.atlanmod.neoemf.data.BackendFactory}.
     */
    @BeforeAll
    protected static void registerBackendFactories() {
        BackendFactoryRegistry.getInstance().registerAll(); // Optional
    }

    /**
     * Creates a new {@link PersistentResource} for the {@code context}.
     *
     * @param context      the current context
     * @param isPersistent {@code true} if the resource must be persistent; i.e. saved
     *
     * @return a new {@link PersistentResource}
     */
    @Nonnull
    protected PersistentResource createResource(Context context, boolean isPersistent) throws IOException {
        return isPersistent
                ? context.createPersistentResource(currentTempFile())
                : context.createTransientResource(currentTempFile());
    }

    /**
     * Creates a new {@link PersistentResource} for the {@code context}. The resource will be persistent.
     *
     * @param context the current context
     *
     * @return a new {@link PersistentResource}
     *
     * @see #createResource(Context, boolean)
     */
    @Nonnull
    protected PersistentResource createPersistentResource(Context context) throws IOException {
        return createResource(context, true);
    }

    /**
     * Creates a new {@link PersistentResource} for the {@code context}. The resource will be transient.
     *
     * @param context the current context
     *
     * @return a new {@link PersistentResource}
     *
     * @see #createResource(Context, boolean)
     */
    @Nonnull
    protected PersistentResource createTransientResource(Context context) throws IOException {
        return createResource(context, false);
    }
}
