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
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

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

import static org.assertj.core.api.Assertions.assertThat;

public class NeoURITest extends AllTest {

    private static final String TEST_FILENAME = "neoURITestFile";

    private static final String MOCK = "mock";
    private static final String INVALID = "invalid";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final PersistenceBackendFactory persistenceBackendFactory = Mockito.mock(PersistenceBackendFactory.class);
    private File testFile;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.unregisterAll();
        PersistenceBackendFactoryRegistry.register(MOCK, persistenceBackendFactory);
        testFile = temporaryFolder.getRoot().toPath().resolve(TEST_FILENAME + new Date().getTime()).toFile();
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();

        if (temporaryFolder.getRoot().exists()) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder.getRoot());
            } catch (IOException e) {
                NeoLogger.warn(e);
            }
        }

        testFile = null;
    }

    @Test
    public void testCreateNeoURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(MOCK + "://test");
        URI neoURI = NeoURI.createNeoURI(validURI);
        assertThat(neoURI.scheme()).isEqualTo(MOCK);
    }

    @Test
    public void testCreateNeoURIFromFileValidScheme() {
        URI neoURI = NeoURI.createNeoURI(testFile, MOCK);
        assertThat(neoURI.scheme()).isEqualTo(MOCK);
    }

    @Test
    public void testCreateNeoURIFromFileURIValidScheme() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = NeoURI.createNeoURI(fileURI, MOCK);
        assertThat(neoURI.scheme()).isEqualTo(MOCK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI(INVALID + "://test");
        NeoURI.createNeoURI(invalidURI);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileURINoScheme() {
        URI invalidFileURI = URI.createFileURI(testFile.getAbsolutePath());
        NeoURI.createNeoURI(invalidFileURI);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileInvalidScheme() {
        NeoURI.createNeoURI(testFile, INVALID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileNullScheme() {
        NeoURI.createNeoURI(testFile, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileURIInvalidScheme() {
        URI fileUri = URI.createFileURI(testFile.getAbsolutePath());
        NeoURI.createNeoURI(fileUri, INVALID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileURINullScheme() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        NeoURI.createNeoURI(fileURI, null);
    }
}
