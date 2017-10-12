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

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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
}
