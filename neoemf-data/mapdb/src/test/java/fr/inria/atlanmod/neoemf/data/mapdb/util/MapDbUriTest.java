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

package fr.inria.atlanmod.neoemf.data.mapdb.util;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

public class MapDbUriTest extends AllTest {

    private static final String SCHEME_INVALID = "invalid";

    private final PersistenceBackendFactory persistenceBackendFactory = MapDbPersistenceBackendFactory.getInstance();

    private File testFile;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.register(MapDbURI.SCHEME, persistenceBackendFactory);
        testFile = tempFile("MapDb");
    }

    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }

    @Test
    public void testCreateNeoGraphURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(MapDbURI.SCHEME + ":/test");
        URI neoURI = MapDbURI.createURI(validURI);
        assertThat(neoURI).hasScheme(MapDbURI.SCHEME);
    }

    @Test
    public void testCreateNeoGraphURIFromFileURI() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = MapDbURI.createURI(fileURI);
        assertThat(neoURI).hasScheme(MapDbURI.SCHEME);
    }

    @Test
    public void testCreateNeoURIFromFile() {
        URI neoURI = MapDbURI.createFileURI(testFile);
        assertThat(neoURI).hasScheme(MapDbURI.SCHEME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNeoGraphURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI(SCHEME_INVALID + ":/test");
        MapDbURI.createURI(invalidURI);
    }
}
