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

package fr.inria.atlanmod.neoemf.map.util;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;

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

import static org.assertj.core.api.Assertions.assertThat;

public class MapURITest extends AllTest {

    private static final String TEST_FILENAME = "neoMapURITestFile";

    private final PersistenceBackendFactory persistenceBackendFactory = MapPersistenceBackendFactory.getInstance();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File testFile;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.unregisterAll();
        PersistenceBackendFactoryRegistry.register(MapURI.SCHEME, persistenceBackendFactory);
        testFile = temporaryFolder.getRoot().toPath().resolve(TEST_FILENAME + new Date().getTime()).toFile();
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();

        if (temporaryFolder.getRoot().exists()) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder.getRoot());
            }
            catch (IOException e) {
                NeoLogger.warn(e);
            }
        }

        testFile = null;
    }

    @Test
    public void testCreateNeoGraphURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(MapURI.SCHEME + ":/test");
        URI neoURI = MapURI.createURI(validURI);
        assertThat(neoURI.scheme()).isEqualTo(MapURI.SCHEME);
    }

    @Test
    public void testCreateNeoGraphURIFromFileURI() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = MapURI.createURI(fileURI);
        assertThat(neoURI.scheme()).isEqualTo(MapURI.SCHEME);
    }

    @Test
    public void testCreateNeoURIFromFile() {
        URI neoURI = MapURI.createFileURI(testFile);
        assertThat(neoURI.scheme()).isEqualTo(MapURI.SCHEME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoGraphURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI("invalid:/test");
        MapURI.createURI(invalidURI);
    }
}
