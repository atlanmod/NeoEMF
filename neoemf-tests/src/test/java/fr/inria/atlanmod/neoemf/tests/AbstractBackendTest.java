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
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

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

@RunWith(Parameterized.class)
public abstract class AbstractBackendTest extends AbstractTest implements ContextualTest {

    protected static final MapSampleFactory EFACTORY = MapSampleFactory.eINSTANCE;

    private static final MapSamplePackage EPACKAGE = MapSamplePackage.eINSTANCE;

    @Parameterized.Parameter()
    public Context context;

    @Parameterized.Parameter(1)
    public String name;

    private List<PersistentResource> loadedResources;

    private File file;

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{BlueprintsContext.get(), BlueprintsContext.NAME},
                new Object[]{BlueprintsNeo4jContext.get(), BlueprintsNeo4jContext.NAME},
                new Object[]{MapDbContext.get(), MapDbContext.NAME},
                new Object[]{BerkeleyDbContext.get(), BerkeleyDbContext.NAME}
//                new Object[]{HBaseContext.get(), HBaseContext.NAME}
        );
    }

    @Override
    public Context context() {
        return context;
    }

    public File file() {
        return file;
    }

    public PersistentResource createPersistentStore() {
        try {
            return closeAtExit(context.createPersistentResource(EPACKAGE, file));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PersistentResource createTransientStore() {
        try {
            return closeAtExit(context.createTransientResource(EPACKAGE, file));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PersistentResource closeAtExit(final PersistentResource resource) {
        loadedResources.add(resource);
        return resource;
    }

    @Before
    public final void createWorkspace() throws Exception {
        loadedResources = new ArrayList<>();
        file = workspace.newFile(context.name());
    }

    @After
    public final void cleanWorkspace() throws Exception {
        for (PersistentResource resource : loadedResources) {
            resource.close();
        }
        loadedResources.clear();
    }
}
