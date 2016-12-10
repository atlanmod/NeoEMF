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
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;
import static fr.inria.atlanmod.neoemf.NeoAssertions.catchThrowable;
import static org.mockito.Mockito.mock;

public class PersistenceUriTest extends AllTest {

    private static final String URI_SCHEME = "mock";

    private static final String SCHEME_INVALID = "invalid";

    private final PersistenceBackendFactory persistenceBackendFactory = mock(PersistenceBackendFactory.class);

    private File testFile;

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.register(URI_SCHEME, persistenceBackendFactory);
        testFile = tempFile("Core");
    }

    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }

    @Test
    public void testCreateNeoURIFromStandardURIValidScheme() {
        URI validURI = URI.createURI(URI_SCHEME + "://test");
        URI neoURI = PersistenceURI.createURI(validURI);
        assertThat(neoURI).hasScheme(URI_SCHEME);
    }

    @Test
    public void testCreateNeoURIFromFileValidScheme() {
        URI neoURI = PersistenceURI.createFileURI(testFile, URI_SCHEME);
        assertThat(neoURI).hasScheme(URI_SCHEME);
    }

    @Test
    public void testCreateNeoURIFromFileURIValidScheme() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
        URI neoURI = PersistenceURI.createFileURI(fileURI, URI_SCHEME);
        assertThat(neoURI).hasScheme(URI_SCHEME);
    }

    @Test
    public void testCreateNeoURIFromStandardURIInvalidScheme() {
        URI invalidURI = URI.createURI(SCHEME_INVALID + "://test");

        Throwable thrown = catchThrowable(() -> PersistenceURI.createURI(invalidURI));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateNeoURIFromFileURINoScheme() {
        URI invalidFileURI = URI.createFileURI(testFile.getAbsolutePath());

        Throwable thrown = catchThrowable(() -> PersistenceURI.createURI(invalidFileURI));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateNeoURIFromFileInvalidScheme() {
        Throwable thrown = catchThrowable(() -> PersistenceURI.createFileURI(testFile, SCHEME_INVALID));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateNeoURIFromFileNullScheme() {
        Throwable thrown = catchThrowable(() -> PersistenceURI.createFileURI(testFile, null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateNeoURIFromFileURIInvalidScheme() {
        URI fileUri = URI.createFileURI(testFile.getAbsolutePath());

        Throwable thrown = catchThrowable(() -> PersistenceURI.createFileURI(fileUri, SCHEME_INVALID));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateNeoURIFromFileURINullScheme() {
        URI fileURI = URI.createFileURI(testFile.getAbsolutePath());

        Throwable thrown = catchThrowable(() -> PersistenceURI.createFileURI(fileURI, null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
