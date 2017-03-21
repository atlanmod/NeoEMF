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
    public void testCreateUriWithoutScheme() {
        //noinspection ConstantConditions
        Throwable thrown0 = catchThrowable(() -> PersistenceURI.newBuilder(null));
        assertThat(thrown0).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateUriFromUriWithNull() {
        //noinspection ConstantConditions
        Throwable thrown0 = catchThrowable(() -> PersistenceURI.newBuilder("scheme").fromUri(null));
        assertThat(thrown0).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCreateUriFromFileWithNull() {
        //noinspection ConstantConditions
        Throwable thrown0 = catchThrowable(() -> PersistenceURI.newBuilder("scheme").fromFile(null));
        assertThat(thrown0).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCreateUriFromServerWithNull() {
        //noinspection ConstantConditions
        Throwable thrown0 = catchThrowable(() -> PersistenceURI.newBuilder("scheme").fromServer(null, 0, URI.createURI("uri0")));
        assertThat(thrown0).isInstanceOf(NullPointerException.class);

        //noinspection ConstantConditions
        Throwable thrown1 = catchThrowable(() -> PersistenceURI.newBuilder("scheme").fromServer("localhost", -1, URI.createURI("uri0")));
        assertThat(thrown1).isInstanceOf(IllegalArgumentException.class);

        //noinspection ConstantConditions
        Throwable thrown2 = catchThrowable(() -> PersistenceURI.newBuilder("scheme").fromServer("localhost", 0, null));
        assertThat(thrown2).isInstanceOf(NullPointerException.class);
    }
}
