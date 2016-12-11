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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

public class PersistenceUriTest extends AbstractUriTest {

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
    protected URI createFileURI(File file) {
        return PersistenceURI.createFileURI(file, uriScheme());
    }

    @Test
    @Override
    public void testCreateUriFromFileUri() {
        Throwable thrown = catchThrowable(super::testCreateUriFromFileUri);
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    protected URI createFileURI(URI uri, String scheme) {
        return PersistenceURI.createFileURI(uri, scheme);
    }

    protected URI createFileURI(File file, String scheme) {
        return PersistenceURI.createFileURI(file, scheme);
    }

    @Test
    public void testCreateFileUriFromFileUri() {
        URI fileURI = URI.createFileURI(file().getAbsolutePath());
        URI neoURI = createFileURI(fileURI, uriScheme());
        assertThat(neoURI.scheme()).isEqualTo(uriScheme());
    }

    @Test
    public void testCreateFileUriFromFileInvalidScheme() {
        Throwable thrown = catchThrowable(() -> createFileURI(file(), SCHEME_INVALID));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateFileUriFromFileNullScheme() {
        Throwable thrown = catchThrowable(() -> createFileURI(file(), null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateFileUriFromFileUriInvalidScheme() {
        URI fileUri = URI.createFileURI(file().getAbsolutePath());

        Throwable thrown = catchThrowable(() -> createFileURI(fileUri, SCHEME_INVALID));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateFileUriFromFileUriNullScheme() {
        URI fileURI = URI.createFileURI(file().getAbsolutePath());

        Throwable thrown = catchThrowable(() -> createFileURI(fileURI, null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
