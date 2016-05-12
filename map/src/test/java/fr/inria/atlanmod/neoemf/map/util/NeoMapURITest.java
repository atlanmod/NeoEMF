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

package fr.inria.atlanmod.neoemf.map.util;

import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.map.AllMapTest;
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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NeoMapURITest extends AllMapTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static final String TEST_FILENAME = "neoMapURITestFile";

    private AbstractPersistenceBackendFactory persistenceBackendFactory = new MapPersistenceBackendFactory();
    private File testFile = null;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.getFactories().clear();
        PersistenceBackendFactoryRegistry.getFactories().put(NeoMapURI.NEO_MAP_SCHEME, persistenceBackendFactory);
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
        NeoMapURI.createNeoMapURI(invalidURI);
    }

    @Test
    public void testCreateNeoGraphURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(NeoMapURI.NEO_MAP_SCHEME + ":/test");
        URI neoURI = NeoMapURI.createNeoMapURI(validURI);
        assertThat(neoURI.scheme(), is(NeoMapURI.NEO_MAP_SCHEME));
    }

    @Test
    public void testCreateNeoGraphURIFromFileURI() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = NeoMapURI.createNeoMapURI(fileURI);
        assertThat(neoURI.scheme(), is(NeoMapURI.NEO_MAP_SCHEME));
    }

    @Test
    public void testCreateNeoURIFromFile() {
        URI neoURI = NeoMapURI.createNeoMapURI(testFile);
        assertThat(neoURI.scheme(), is(NeoMapURI.NEO_MAP_SCHEME));
    }

}
