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

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import java.io.File;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;
import static fr.inria.atlanmod.neoemf.NeoAssertions.catchThrowable;
import static org.mockito.Mockito.mock;

public class PersistenceUriTest extends AllUriTest {

    @Override
    protected String name() {
        return "Core";
    }

    @Override
    protected String uriScheme() {
        return "mock";
    }

    @Override
    protected PersistenceBackendFactory persistenceBackendFactory() {
        return mock(PersistenceBackendFactory.class);
    }

    @Override
    protected URI createURI(URI uri) {
        return PersistenceURI.createURI(uri);
    }

    @Override
    protected URI createURI(File file) {
        return PersistenceURI.createFileURI(file, uriScheme());
    }

    protected URI createURI(URI uri, String scheme) {
        return PersistenceURI.createFileURI(uri, scheme);
    }

    protected URI createURI(File file, String scheme) {
        return PersistenceURI.createFileURI(file, scheme);
    }

    @Test
    @Override
    public void testCreateUriFromFileUri() {
        Throwable thrown = catchThrowable(super::testCreateUriFromFileUri);
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateUriFromFileInvalidScheme() {
        Throwable thrown = catchThrowable(() -> createURI(file(), SCHEME_INVALID));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateUriFromFileNullScheme() {
        Throwable thrown = catchThrowable(() -> createURI(file(), null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateUriFromFileUriInvalidScheme() {
        URI fileUri = URI.createFileURI(file().getAbsolutePath());

        Throwable thrown = catchThrowable(() -> createURI(fileUri, SCHEME_INVALID));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateUriFromFileUriNullScheme() {
        URI fileURI = URI.createFileURI(file().getAbsolutePath());

        Throwable thrown = catchThrowable(() -> createURI(fileURI, null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
