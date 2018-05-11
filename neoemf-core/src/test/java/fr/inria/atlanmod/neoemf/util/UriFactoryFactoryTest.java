/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.InMemoryDefaultContext;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case about {@link UriFactory}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"}) // Test with `@Nonnull`
class UriFactoryFactoryTest extends AbstractUriFactoryTest {

    private static final String SCHEME = "scheme";

    @Nonnull
    @Override
    protected Context context() {
        return new InMemoryDefaultContext();
    }

    @Test
    void testCreateUriWithoutScheme() {
        assertThat(catchThrowable(() -> AbstractUriFactory.withScheme(null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testCreateUriFromUriWithNull() {
        assertThat(catchThrowable(() -> AbstractUriFactory.withScheme(SCHEME).createLocalUri((URI) null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testCreateUriFromFileWithNull() {
        assertThat(catchThrowable(() -> AbstractUriFactory.withScheme(SCHEME).createLocalUri((File) null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testCreateUriFromServerWithNull() {
        assertThat(catchThrowable(() -> AbstractUriFactory.withScheme(SCHEME).createRemoteUri(null, 0, URI.createURI("uri0"))))
                .isInstanceOf(NullPointerException.class);

        assertThat(catchThrowable(() -> AbstractUriFactory.withScheme(SCHEME).createRemoteUri("localhost", -1, URI.createURI("uri0"))))
                .isInstanceOf(IllegalArgumentException.class);

        assertThat(catchThrowable(() -> AbstractUriFactory.withScheme(SCHEME).createRemoteUri("localhost", 0, (URI) null)))
                .isInstanceOf(NullPointerException.class);
    }
}
