/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.graph.blueprints.util;

import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.AllGraphTest;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NeoBlueprintsURITest extends AllGraphTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static final String TEST_FILENAME = "neoGraphURITestFile";

    private AbstractPersistenceBackendFactory persistenceBackendFactory = new BlueprintsPersistenceBackendFactory();
    private File testFile = null;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.getFactories().clear();
        PersistenceBackendFactoryRegistry.getFactories().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, persistenceBackendFactory);
        testFile = temporaryFolder.getRoot().toPath().resolve(TEST_FILENAME + String.valueOf(new Date().getTime())).toFile();
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();

        if (temporaryFolder.getRoot().exists()) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder.getRoot());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        testFile = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoGraphURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI("invalid:/test");
        NeoBlueprintsURI.createNeoGraphURI(invalidURI);
    }

    @Test
    public void testCreateNeoGraphURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(NeoBlueprintsURI.NEO_GRAPH_SCHEME + ":/test");
        URI neoURI = NeoBlueprintsURI.createNeoGraphURI(validURI);
        assertThat(neoURI.scheme(), is(NeoBlueprintsURI.NEO_GRAPH_SCHEME));
    }

    @Test
    public void testCreateNeoGraphURIFromFileURI() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = NeoBlueprintsURI.createNeoGraphURI(fileURI);
        assertThat(neoURI.scheme(), is(NeoBlueprintsURI.NEO_GRAPH_SCHEME));
    }

    @Test
    public void testCreateNeoURIFromFile() {
        URI neoURI = NeoBlueprintsURI.createNeoGraphURI(testFile);
        assertThat(neoURI.scheme(), is(NeoBlueprintsURI.NEO_GRAPH_SCHEME));
    }

}
