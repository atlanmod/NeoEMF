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
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.EPackage;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AllBackendTest extends AllTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    protected PersistentResource mapResource;
    protected PersistentResource neo4jResource;
    protected PersistentResource tinkerResource;

    protected MapResourceBuilder mapBuilder;
    protected BlueprintsResourceBuilder blueprintsBuilder;

    protected File mapFile;
    protected File neo4jFile;
    protected File tinkerFile;

    protected EPackage ePackage;

    @Before
    public void setUp() throws Exception {
        assertThat(ePackage).isNotNull(); // "EPackage not set"
        String className = getClass().getSimpleName();
        String timestamp = String.valueOf(new Date().getTime());
        mapFile = temporaryFolder.getRoot().toPath().resolve(className + "MapDB" + timestamp).toFile();
        neo4jFile = temporaryFolder.getRoot().toPath().resolve(className + "Neo4j" + timestamp).toFile();
        tinkerFile = temporaryFolder.getRoot().toPath().resolve(className + "Tinker" + timestamp).toFile();
        mapBuilder = new MapResourceBuilder(ePackage);
        blueprintsBuilder = new BlueprintsResourceBuilder(ePackage);
    }

    public void createPersistentStores() throws IOException {
        mapResource = mapBuilder.persistent().file(mapFile).build();
        neo4jResource = blueprintsBuilder.neo4j().persistent().file(neo4jFile).build();
        tinkerResource = blueprintsBuilder.tinkerGraph().persistent().file(tinkerFile).build();
    }

    public void createTransientStores() throws IOException {
        mapResource = mapBuilder.file(mapFile).build();
        neo4jResource = blueprintsBuilder.neo4j().file(neo4jFile).build();
        tinkerResource = blueprintsBuilder.file(tinkerFile).build();
    }

    @After
    public void tearDown() throws Exception {
        //printMemoryUsage();

        mapResource.close();
        neo4jResource.close();
        tinkerResource.close();

        //temporaryFolder.delete();

        if (temporaryFolder.getRoot().exists()) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder.getRoot());
            }
            catch (IOException e) {
                NeoLogger.warn(e);
            }
        }
    }
}
