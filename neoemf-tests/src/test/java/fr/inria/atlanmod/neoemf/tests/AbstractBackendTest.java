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
import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.ContextualTest;
import fr.inria.atlanmod.neoemf.data.berkeleydb.context.BerkeleyDbContext;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.context.BlueprintsNeo4jContext;
import fr.inria.atlanmod.neoemf.data.hbase.context.HBaseContext;
import fr.inria.atlanmod.neoemf.data.mapdb.context.MapDbContext;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.sample.SampleFactory;
import fr.inria.atlanmod.neoemf.tests.sample.SamplePackage;

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

import javax.annotation.Nonnull;

import static org.junit.Assume.assumeTrue;

/**
 * The base class for testing on different {@link Context}s.
 */
@RunWith(Parameterized.class)
public abstract class AbstractBackendTest extends AbstractTest implements ContextualTest {

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
    private final List<PersistentResource> loadedResources = new ArrayList<>();

    /**
     * The current context.
     */
    @VisibleForReflection
    @Parameterized.Parameter()
    public Context context;

    /**
     * The name of the current context.
     */
    @VisibleForReflection
    @Parameterized.Parameter(1)
    public String name;

    /**
     * The current temporary file.
     */
    private File file;

    /**
     * Initializes the parameters of tests.
     *
     * @return a collection
     */
    @Nonnull
    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> allContexts() {
        return Arrays.asList(
                // Blueprints
                buildContext(BlueprintsContext.getWithIndices(), "Tinker"),
                buildContext(BlueprintsNeo4jContext.getWithIndices(), "Neo4j"),

                // MapDB
                buildContext(MapDbContext.getWithIndices(), "MapDb - Indices"),
                buildContext(MapDbContext.getWithArrays(), "MapDb - Arrays"),
                buildContext(MapDbContext.getWithLists(), "MapDb - Lists"),

                // BerkeleyDB
                buildContext(BerkeleyDbContext.getWithIndices(), "BerkeleyDB - Indices"),
                buildContext(BerkeleyDbContext.getWithArrays(), "BerkeleyDB - Arrays"),
                buildContext(BerkeleyDbContext.getWithLists(), "BerkeleyDB - Lists"),

                // HBase
                buildContext(HBaseContext.getWithArraysAndStrings(), "HBase")
        );
    }

    /**
     * Build a new {@link Context} entry.
     *
     * @param context the context
     * @param name    the name of the context
     *
     * @return a new entry
     *
     * @see #allContexts()
     */
    @Nonnull
    private static Object[] buildContext(Context context, String name) {
        return new Object[]{context, name};
    }

    @Nonnull
    @Override
    public Context context() {
        return context;
    }

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
     * Create a new persistent store according to the current {@link #context() Context}.
     *
     * @return the created resource
     */
    @Nonnull
    public PersistentResource newPersistentStore() {
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
    @Nonnull
    public PersistentResource newTransientStore() {
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
    @Before
    public final void createWorkspace() throws IOException {
        context.init();
        assumeTrue("The context has not been initialized", context.isInitialized());

        loadedResources.clear();
        file = workspace.newFile(context.name());
    }

    /**
     * Cleans the workspace by closing all loaded resources.
     */
    @After
    public final void cleanWorkspace() {
        if (context.isInitialized()) {
            loadedResources.forEach(PersistentResource::close);
            loadedResources.clear();
        }
    }
}
