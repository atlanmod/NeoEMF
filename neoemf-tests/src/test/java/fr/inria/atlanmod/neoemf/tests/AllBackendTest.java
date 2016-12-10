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

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.context.BlueprintsNeo4jContext;
import fr.inria.atlanmod.neoemf.data.mapdb.context.MapDbContext;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.context.Context;
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

@RunWith(Parameterized.class)
public abstract class AllBackendTest extends AllTest {

    protected static final MapSampleFactory EFACTORY = MapSampleFactory.eINSTANCE;

    private static final MapSamplePackage EPACKAGE = MapSamplePackage.eINSTANCE;

    @Parameterized.Parameter
    public Context context;

    @Parameterized.Parameter(1)
    public String name;

    private List<PersistentResource> loadedResources;

    private File resourceFile;

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{new MapDbContext(), MapDbContext.NAME},
                new Object[]{new BlueprintsContext(), BlueprintsContext.NAME},
                new Object[]{new BlueprintsNeo4jContext(), BlueprintsNeo4jContext.NAME}
        );
    }

    public File resourceFile() {
        return resourceFile;
    }

    public PersistentResource createPersistentStore() {
        try {
            return closeAtExit(context.createPersistentResource(EPACKAGE, resourceFile));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PersistentResource createTransientStore() {
        try {
            return closeAtExit(context.createTransientResource(EPACKAGE, resourceFile));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PersistentResource closeAtExit(final PersistentResource resource) {
        loadedResources.add(resource);
        return resource;
    }

    @Before
    public void setUp() throws Exception {
        loadedResources = new ArrayList<>();
        resourceFile = tempFile(context.name());
    }

    @After
    public void tearDown() throws Exception {
        //printMemoryUsage();

        for (PersistentResource resource : loadedResources) {
            resource.close();
        }

        loadedResources.clear();
    }
}
