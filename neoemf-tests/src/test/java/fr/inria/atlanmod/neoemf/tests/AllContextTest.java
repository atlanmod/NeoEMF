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

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.sample.SampleFactory;
import fr.inria.atlanmod.neoemf.tests.sample.SamplePackage;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The base class for testing on different {@link Context}s.
 */
@ParametersAreNonnullByDefault
public abstract class AllContextTest extends AbstractTest {

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
     * A list that holds the resources that should be closed at the end of the tests.
     */
    @Nonnull
    private final List<Resource> loadedResources = new ArrayList<>();

    /**
     * The current temporary file.
     */
    private File file;

    /**
     * Returns the temporary file for this test.
     *
     * @return a file.
     */
    @Nonnull
    public File file() {
        return file;
    }

    /**
     * Create a new persistent store according to the current {@code context}.
     *
     * @return the created resource
     */
    @Nonnull
    public PersistentResource newPersistentResource(Context context) {
        try {
            return closeAtExit(context.createPersistentResource(EPACKAGE, file));
        }
        catch (IOException e) {
            throw new IllegalStateException(e); // Should never happen
        }
    }

    /**
     * Creates a new transient store according to the current {@code context}.
     *
     * @return the created resource
     */
    @Nonnull
    public PersistentResource newTransientResource(Context context) {
        try {
            return closeAtExit(context.createTransientResource(EPACKAGE, file));
        }
        catch (IOException e) {
            throw new IllegalStateException(e); // Should never happen
        }
    }

    /**
     * Adds the {@code resource} among the resources that should be closed at the end of the tests.
     *
     * @param resource the resource to close
     *
     * @return the {@code resource}
     */
    @Nonnull
    public PersistentResource closeAtExit(PersistentResource resource) {
        loadedResources.add(resource);
        return resource;
    }

    /**
     * Initializes the workspace by creating a new temporary file.
     *
     * @throws IOException if an I/O error occurs during the file creation
     */
    @BeforeEach
    public final void createWorkspace() throws IOException {
        loadedResources.clear();
        file = newFile("tests");
    }

    /**
     * Cleans the workspace by closing all loaded resources.
     */
    @AfterEach
    public final void cleanWorkspace() {
        loadedResources.forEach(Resource::unload);
        loadedResources.clear();
    }
}
