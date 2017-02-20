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

import fr.inria.atlanmod.neoemf.AbstractTest;
import fr.inria.atlanmod.neoemf.Context;
import fr.inria.atlanmod.neoemf.ContextualTest;
import fr.inria.atlanmod.neoemf.data.berkeleydb.BerkeleyDbContext;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.BlueprintsNeo4jContext;
import fr.inria.atlanmod.neoemf.data.hbase.HBaseContext;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbContext;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A utility class for testing on different {@link Context}s.
 */
@RunWith(Parameterized.class)
public abstract class AbstractBackendTest extends AbstractTest implements ContextualTest {

    /**
     * The {@link org.eclipse.emf.ecore.EFactory} of the test model.
     */
    protected static final MapSampleFactory EFACTORY = MapSampleFactory.eINSTANCE;

    /**
     * The {@link org.eclipse.emf.ecore.EPackage} of the test model.
     */
    protected static final MapSamplePackage EPACKAGE = MapSamplePackage.eINSTANCE;

    /**
     * The current context.
     */
    @Parameterized.Parameter()
    public Context context;

    /**
     * The name of the current context.
     */
    @Parameterized.Parameter(1)
    public String name;

    /**
     * A list that holds the resources that should be closed at the end of the tests.
     */
    private List<PersistentResource> loadedResources;

    /**
     * The current temporary file.
     */
    private File file;

    /**
     * Initializes the parameters of tests.
     *
     * @return a collection
     */
    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{BlueprintsContext.get(), BlueprintsContext.NAME},
                new Object[]{BlueprintsNeo4jContext.get(), BlueprintsNeo4jContext.NAME},
                new Object[]{MapDbContext.get(), MapDbContext.NAME},
                new Object[]{BerkeleyDbContext.get(), BerkeleyDbContext.NAME},
                new Object[]{HBaseContext.get(), HBaseContext.NAME}
        );
    }

    @Override
    public Context context() {
        return context;
    }

    /**
     * Returns the temporary file for this test.
     *
     * @return a file.
     */
    public File file() {
        return file;
    }

    /**
     * Create a new persistent store according to the current {@link #context() Context}.
     *
     * @return the created resource
     */
    public PersistentResource createPersistentStore() {
        try {
            return closeAtExit(context.createPersistentResource(EPACKAGE, file));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a new transient store according to the current {@link #context() Context}.
     *
     * @return the created resource
     */
    public PersistentResource createTransientStore() {
        try {
            return closeAtExit(context.createTransientResource(EPACKAGE, file));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds the {@code resource} among the resources that should be closed at the end of the tests.
     *
     * @param resource the resource to close
     *
     * @return the {@code resource}
     */
    public PersistentResource closeAtExit(PersistentResource resource) {
        loadedResources.add(resource);
        return resource;
    }

    /**
     * Initializes the workspace by creating a new temporary file.
     *
     * @throws IOException if an I/O error occurs during the file creation
     */
    @Before
    public final void createWorkspace() throws IOException {
        loadedResources = new ArrayList<>();
        file = workspace.newFile(context.name());
    }

    /**
     * Cleans the workspace by closing all loaded resources.
     */
    @After
    public final void cleanWorkspace() {
        for (PersistentResource resource : loadedResources) {
            resource.close();
        }
        loadedResources.clear();
    }
}
