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

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link AbstractUriBuilder}.
 */
public class UriBuilderTest extends AbstractUriTest implements CoreTest {

    private static final String SCHEME = "scheme";

    @Test
    public void testCreateUriWithoutScheme() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() -> AbstractUriBuilder.builder(null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCreateUriFromUriWithNull() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() -> AbstractUriBuilder.builder(SCHEME).fromUri(null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCreateUriFromFileWithNull() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() -> AbstractUriBuilder.builder(SCHEME).fromFile((File) null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCreateUriFromServerWithNull() {
        //noinspection ConstantConditions
        assertThat(catchThrowable(() -> AbstractUriBuilder.builder(SCHEME).fromServer(null, 0, URI.createURI("uri0"))))
                .isInstanceOf(NullPointerException.class);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() -> AbstractUriBuilder.builder(SCHEME).fromServer("localhost", -1, URI.createURI("uri0"))))
                .isInstanceOf(IllegalArgumentException.class);

        //noinspection ConstantConditions
        assertThat(catchThrowable(() -> AbstractUriBuilder.builder(SCHEME).fromServer("localhost", 0, (URI) null)))
                .isInstanceOf(NullPointerException.class);
    }
}
