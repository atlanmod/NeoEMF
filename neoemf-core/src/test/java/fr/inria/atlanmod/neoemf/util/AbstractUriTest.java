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

import fr.inria.atlanmod.neoemf.AbstractUnitTest;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * An abstract test-case that checks the behavior of {@link PersistenceURI}s.
 */
public abstract class AbstractUriTest extends AbstractUnitTest {

    /**
     * The scheme of an invalid {@link URI}.
     */
    protected static final String SCHEME_INVALID = "invalid";

    /**
     * Checks the creation of a {@link URI} from another.
     */
    @Test
    public void testCreateUriFromStandardUri() {
        URI validURI = URI.createURI(context().uriScheme() + ":/test");
        URI neoURI = context().createUri(validURI);
        assertThat(neoURI.scheme()).isEqualTo(context().uriScheme());
    }

    /**
     * Checks the creation of a {@link URI} from another file {@link URI}.
     */
    @Test
    public void testCreateUriFromFileUri() {
        URI fileURI = URI.createFileURI(file().getAbsolutePath());
        URI neoURI = context().createUri(fileURI);
        assertThat(neoURI.scheme()).isEqualTo(context().uriScheme());
    }

    /**
     * Checks the creation of a {@link URI} from a {@link java.io.File}.
     */
    @Test
    public void testCreateFileUriFromFile() {
        URI neoURI = context().createUri(file());
        assertThat(neoURI.scheme()).isEqualTo(context().uriScheme());
    }

    /**
     * Checks the creation of a {@link URI} with an invalid scheme.
     */
    @Test
    public void testCreateUriFromStandardUriInvalidScheme() {
        URI invalidURI = URI.createURI(SCHEME_INVALID + ":/test");

        Throwable thrown = catchThrowable(() -> context().createUri(invalidURI));
        Assertions.assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
