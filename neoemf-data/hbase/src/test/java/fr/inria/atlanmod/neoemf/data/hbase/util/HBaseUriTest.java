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

package fr.inria.atlanmod.neoemf.data.hbase.util;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackendFactory;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

public class HBaseUriTest extends AllTest {

    private static final String SCHEME_INVALID = "invalid";

    private final PersistenceBackendFactory persistenceBackendFactory = HBasePersistenceBackendFactory.getInstance();

    private File testFile;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.register(HBaseURI.SCHEME, persistenceBackendFactory);
        testFile = tempFile("HBase");
    }

    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }

    @Test
    public void testCreateNeoGraphURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(HBaseURI.SCHEME + ":/test");
        URI neoURI = HBaseURI.createURI(validURI);
        assertThat(neoURI).hasScheme(HBaseURI.SCHEME);
    }

    @Test
    public void testCreateNeoGraphURIFromFileURI() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = HBaseURI.createURI(fileURI);
        assertThat(neoURI).hasScheme(HBaseURI.SCHEME);
    }

    @Test
    public void testCreateNeoURIFromFile() {
        URI neoURI = HBaseURI.createFileURI(testFile);
        assertThat(neoURI).hasScheme(HBaseURI.SCHEME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoGraphURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI(SCHEME_INVALID + ":/test");
        HBaseURI.createURI(invalidURI);
    }
}
