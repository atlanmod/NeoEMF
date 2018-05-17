/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.InMemoryDefaultContext;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case about {@link UriFactory}.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
        // Test with `@Nonnull`
class UriFactoryFactoryTest extends AbstractUriFactoryTest {

    private static final String SCHEME = "scheme";

    private final UriFactory factory = AbstractUriFactory.withScheme(SCHEME);

    @BeforeAll
    static void registerFactory() {
        BackendFactoryRegistry.getInstance().register(SCHEME, new AbstractBackendFactory<BaseConfig<?>>("fake") {});
    }

    @AfterAll
    static void unregisterFactory() {
        BackendFactoryRegistry.getInstance().unregister(SCHEME);
    }

    @Nonnull
    @Override
    protected Context context() {
        return new InMemoryDefaultContext();
    }

    // region Local

    @Test
    void testCreateLocalUriWithUri() throws IOException {
        final URI uri = factory.createLocalUri(URI.createFileURI(currentTempFile().getAbsolutePath()));
        assertThat(uri.isFile()).isTrue();
        assertThat(uri.scheme()).isEqualTo(SCHEME);
        assertThat(new File(uri.toFileString())).isEqualTo(currentTempFile());
    }

    @Test
    void testCreateLocalUriWithFile() throws IOException {
        final URI uri = factory.createLocalUri(currentTempFile());
        assertThat(uri.isFile()).isTrue();
        assertThat(uri.scheme()).isEqualTo(SCHEME);
        assertThat(new File(uri.toFileString())).isEqualTo(currentTempFile());
    }

    @Test
    void testCreateLocalUriWithString() throws IOException {
        final URI uri = factory.createLocalUri(currentTempFile().getAbsolutePath());
        assertThat(uri.isFile()).isTrue();
        assertThat(uri.scheme()).isEqualTo(SCHEME);
        assertThat(new File(uri.toFileString())).isEqualTo(currentTempFile());
    }

    // endregion

    // region Remote

    @Test
    void testCreateRemoteUriWithStringLocal() {
        final URI uri = factory.createRemoteUri("localhost", 1234, "test");
        assertThat(uri.isHierarchical()).isTrue();
        assertThat(uri.scheme()).isEqualTo(SCHEME);
        assertThat(uri.host()).isEqualTo("127.0.0.1");
        assertThat(uri.port()).isEqualTo(Integer.toString(1234));
        assertThat(uri.segments()).containsExactly("test");
    }

    @Test
    void testCreateRemoteUriWithStringAddr() {
        final URI uri = factory.createRemoteUri("192.168.0.1", 1234, "test");
        assertThat(uri.isHierarchical()).isTrue();
        assertThat(uri.scheme()).isEqualTo(SCHEME);
        assertThat(uri.host()).isEqualTo("192.168.0.1");
        assertThat(uri.port()).isEqualTo(Integer.toString(1234));
        assertThat(uri.segments()).containsExactly("test");
    }

    @Test
    void testCreateRemoteUriWithIPAddr() throws UnknownHostException {
        final URI uri = factory.createRemoteUri(InetAddress.getByName("192.168.0.1"), 1234, "test");
        assertThat(uri.isHierarchical()).isTrue();
        assertThat(uri.scheme()).isEqualTo(SCHEME);
        assertThat(uri.host()).isEqualTo("192.168.0.1");
        assertThat(uri.port()).isEqualTo(Integer.toString(1234));
        assertThat(uri.segments()).containsExactly("test");
    }

    // endregion

    @Test
    void testCreateUriWithoutScheme() {
        assertThat(catchThrowable(() -> AbstractUriFactory.withScheme(null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testCreateUriFromUriWithNull() {
        assertThat(catchThrowable(() -> factory.createLocalUri((URI) null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testCreateUriFromFileWithNull() {
        assertThat(catchThrowable(() -> factory.createLocalUri((File) null)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testCreateUriFromServerWithNull() {
        assertThat(catchThrowable(() -> factory.createRemoteUri((String) null, 0, URI.createURI("uri0"))))
                .isInstanceOf(NullPointerException.class);

        assertThat(catchThrowable(() -> factory.createRemoteUri((InetAddress) null, 0, URI.createURI("uri0"))))
                .isInstanceOf(NullPointerException.class);

        assertThat(catchThrowable(() -> factory.createRemoteUri("localhost", -1, URI.createURI("uri0"))))
                .isInstanceOf(IllegalArgumentException.class);

        assertThat(catchThrowable(() -> factory.createRemoteUri("localhost", 65536, URI.createURI("uri0"))))
                .isInstanceOf(IllegalArgumentException.class);

        assertThat(catchThrowable(() -> factory.createRemoteUri("localhost", 0, (URI) null)))
                .isInstanceOf(NullPointerException.class);
    }
}
