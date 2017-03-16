/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.context.CoreTest;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the specific behavior of {@link PersistenceURI}.
 */
public class PersistenceUriTest extends AbstractUriTest implements CoreTest {

    @Test
    @Override
    public void testCreateUriFromFileUri() {
        Throwable thrown = catchThrowable(super::testCreateUriFromFileUri);
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks the creation of a file {@link URI} from another.
     */
    @Test
    public void testCreateFileUriFromFileUri() {
        URI fileURI = URI.createFileURI(file().getAbsolutePath());
        URI neoURI = PersistenceURI.createFileURI(fileURI, context().uriScheme());
        assertThat(neoURI.scheme()).isEqualTo(context().uriScheme());
    }

    /**
     * Checks the creation of a file {@link URI} from a {@link java.io.File}, with an invalid scheme.
     */
    @Test
    public void testCreateFileUriFromFileInvalidScheme() {
        Throwable thrown = catchThrowable(() -> PersistenceURI.createFileURI(file(), SCHEME_INVALID));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks the creation of a file {@link URI} from a {@link java.io.File}, with a {@code null} scheme.
     */
    @Test
    public void testCreateFileUriFromFileNullScheme() {
        Throwable thrown = catchThrowable(() -> PersistenceURI.createFileURI(file(), null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks the creation of a file {@link URI} from another, with an invalid scheme.
     */
    @Test
    public void testCreateFileUriFromFileUriInvalidScheme() {
        URI fileUri = URI.createFileURI(file().getAbsolutePath());

        Throwable thrown = catchThrowable(() -> PersistenceURI.createFileURI(fileUri, SCHEME_INVALID));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks the creation of a file {@link URI} from another, with a {@code null} scheme.
     */
    @Test
    public void testCreateFileUriFromFileUriNullScheme() {
        URI fileURI = URI.createFileURI(file().getAbsolutePath());

        Throwable thrown = catchThrowable(() -> PersistenceURI.createFileURI(fileURI, null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
