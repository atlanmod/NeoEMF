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

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

public class PersistenceURITest extends AllTest {

    private static final String TEST_FILENAME = "neoURITestFile";

    private static final String SCHEME = "mock";
    private static final String SCHEME_INVALID = "invalid";

    private final PersistenceBackendFactory persistenceBackendFactory = Mockito.mock(PersistenceBackendFactory.class);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File testFile;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.unregisterAll();
        PersistenceBackendFactoryRegistry.register(SCHEME, persistenceBackendFactory);
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
    public void testCreateNeoURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(SCHEME + "://test");
        URI neoURI = PersistenceURI.createURI(validURI);
        assertThat(neoURI).hasScheme(SCHEME);
    }

    @Test
    public void testCreateNeoURIFromFileValidScheme() {
        URI neoURI = PersistenceURI.createFileURI(testFile, SCHEME);
        assertThat(neoURI).hasScheme(SCHEME);
    }

    @Test
    public void testCreateNeoURIFromFileURIValidScheme() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = PersistenceURI.createFileURI(fileURI, SCHEME);
        assertThat(neoURI).hasScheme(SCHEME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI(SCHEME_INVALID + "://test");
        PersistenceURI.createURI(invalidURI);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileURINoScheme() {
        URI invalidFileURI = URI.createFileURI(testFile.getAbsolutePath());
        PersistenceURI.createURI(invalidFileURI);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileInvalidScheme() {
        PersistenceURI.createFileURI(testFile, SCHEME_INVALID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileNullScheme() {
        PersistenceURI.createFileURI(testFile, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileURIInvalidScheme() {
        URI fileUri = URI.createFileURI(testFile.getAbsolutePath());
        PersistenceURI.createFileURI(fileUri, SCHEME_INVALID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileURINullScheme() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        PersistenceURI.createFileURI(fileURI, null);
    }
}
