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

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.AllCoreTest;
import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class NeoURITest extends AllCoreTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static final String TEST_FILENAME = "neoURITestFile";

    private AbstractPersistenceBackendFactory persistenceBackendFactory = Mockito.mock(AbstractPersistenceBackendFactory.class);
    private File testFile;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.getFactories().clear();
        PersistenceBackendFactoryRegistry.getFactories().put("mock", persistenceBackendFactory);
        testFile = temporaryFolder.getRoot().toPath().resolve(TEST_FILENAME + new Date().getTime()).toFile();
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();

        if (temporaryFolder.getRoot().exists()) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder.getRoot());
            } catch (IOException e) {
                NeoLogger.log(NeoLogger.SEVERITY_WARNING, e);
            }
        }

        testFile = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI("invalid://test");
        NeoURI.createNeoURI(invalidURI);
    }

    @Test
    public void testCreateNeoURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI("mock://test");
        URI neoURI = NeoURI.createNeoURI(validURI);
        assertThat(neoURI.scheme(), equalTo("mock"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileURINoScheme() {
        URI invalidFileURI = URI.createFileURI(testFile.getAbsolutePath());
        NeoURI.createNeoURI(invalidFileURI);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileInvalidScheme() {
        NeoURI.createNeoURI(testFile, "invalid");
    }

    @Test
    public void testCreateNeoURIFromFileValidScheme() {
        URI neoURI = NeoURI.createNeoURI(testFile, "mock");
        assertThat(neoURI.scheme(), equalTo("mock"));
        System.out.println(neoURI.devicePath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileNullScheme() {
        NeoURI.createNeoURI(testFile, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileURIInvalidScheme() {
        URI fileUri = URI.createFileURI(testFile.getAbsolutePath());
        NeoURI.createNeoURI(fileUri, "invalid");
    }

    @Test
    public void testCreateNeoURIFromFileURIValidScheme() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = NeoURI.createNeoURI(fileURI, "mock");
        assertThat(neoURI.scheme(), equalTo("mock"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoURIFromFileURINullScheme() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        NeoURI.createNeoURI(fileURI, null);
    }
}
