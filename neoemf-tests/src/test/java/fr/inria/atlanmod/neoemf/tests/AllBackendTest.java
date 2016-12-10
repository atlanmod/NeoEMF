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
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.tests.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.tests.util.BackendHelper;
import fr.inria.atlanmod.neoemf.tests.util.BlueprintsBackendHelper;
import fr.inria.atlanmod.neoemf.tests.util.BlueprintsNeo4jBackendHelper;
import fr.inria.atlanmod.neoemf.tests.util.MapDbBackendHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static java.util.Objects.nonNull;

@RunWith(Parameterized.class)
public abstract class AllBackendTest extends AllTest {

    @Parameterized.Parameter
    public BackendHelper helper;

    @Parameterized.Parameter(1)
    public String name;

    protected PersistentResource resource;

    protected File resourceFile;

    protected MapSampleFactory factory = MapSampleFactory.eINSTANCE;
    protected MapSamplePackage ePackage = MapSamplePackage.eINSTANCE;

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{new MapDbBackendHelper(), MapDbBackendHelper.NAME},
                new Object[]{new BlueprintsBackendHelper(), BlueprintsBackendHelper.NAME},
                new Object[]{new BlueprintsNeo4jBackendHelper(), BlueprintsNeo4jBackendHelper.NAME}
        );
    }

    @Before
    public void setUp() throws Exception {
        resourceFile = tempFile(helper.name());
    }

    public void createPersistentStore() {
        try {
            resource = helper.createPersistentResource(ePackage, resourceFile);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTransientStore() {
        try {
            resource = helper.createTransientResource(ePackage, resourceFile);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() throws Exception {
        //printMemoryUsage();

        if (nonNull(resource)) {
            resource.close();
        }
    }
}
