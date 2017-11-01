/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.AbstractUnitTest;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

/**
 * An abstract test-case about {@link AbstractUriBuilder} and its implementations.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractUriTest extends AbstractUnitTest {

    /**
     * Checks the creation of a {@link URI} from another.
     */
    @Test
    public void testCreateUriFromStandardUri() {
        URI validUri = URI.createURI(context().uriScheme() + ":/test");
        URI persistenceUri = context().createUri(validUri);
        assertThat(persistenceUri.scheme()).isEqualTo(context().uriScheme());
    }

    /**
     * Checks the creation of a {@link URI} from another file {@link URI}.
     */
    @Test
    public void testCreateUriFromFileUri() throws IOException {
        URI fileUri = URI.createFileURI(currentTempFile().getAbsolutePath());
        URI persistenceUri = context().createUri(fileUri);
        assertThat(persistenceUri.scheme()).isEqualTo(context().uriScheme());
    }

    /**
     * Checks the creation of a {@link URI} from a {@link java.io.File}.
     */
    @Test
    public void testCreateFileUriFromFile() throws IOException {
        URI persistenceUri = context().createUri(currentTempFile());
        assertThat(persistenceUri.scheme()).isEqualTo(context().uriScheme());
    }

    /**
     * Checks the creation of a {@link URI} with an invalid scheme.
     */
    @Test
    public void testCreateUriFromStandardUriInvalidScheme() {
        URI invalidURI = URI.createURI("invalid:/test");

        assertThat(catchThrowable(() -> context().createUri(invalidURI)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks the creation of a file-based {@link URI} from another {@link URI}.
     */
    @Test
    public void testCreateUriFromUriIfNotSupported() {
        AbstractUriBuilder uriBuilder = AbstractUriBuilder.class.cast(UriBuilder.forScheme(context().uriScheme()));

        assumeFalse(uriBuilder.supportsFile());

        assertThat(catchThrowable(() -> uriBuilder.fromUri(URI.createURI("uri0"))))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    /**
     * Checks the creation of a file-based {@link URI} from a {@link File}.
     */
    @Test
    public void testCreateUriFromFileIfNotSupported() {
        AbstractUriBuilder uriBuilder = AbstractUriBuilder.class.cast(UriBuilder.forScheme(context().uriScheme()));

        assumeFalse(uriBuilder.supportsFile());

        assertThat(catchThrowable(() -> uriBuilder.fromFile("file0")))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    /**
     * Checks the creation of a server-based {@link URI}.
     */
    @Test
    public void testCreateUriFromServerIfNotSupported() {
        AbstractUriBuilder uriBuilder = AbstractUriBuilder.class.cast(UriBuilder.forScheme(context().uriScheme()));

        assumeFalse(uriBuilder.supportsServer());

        assertThat(catchThrowable(() -> uriBuilder.fromServer("host", 0, "segments")))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }
}
