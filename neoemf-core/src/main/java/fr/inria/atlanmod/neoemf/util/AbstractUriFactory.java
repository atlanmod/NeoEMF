/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.atlanmod.commons.Lazy;
import org.atlanmod.commons.annotation.VisibleForTesting;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Guards.checkArgument;
import static org.atlanmod.commons.Guards.checkGreaterThanOrEqualTo;
import static org.atlanmod.commons.Guards.checkLessThanOrEqualTo;
import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * An abstract {@link fr.inria.atlanmod.neoemf.util.UriFactory} that manages the assembly and the construction of {@link
 * org.eclipse.emf.common.util.URI}s.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractUriFactory implements UriFactory {

    /**
     * The scheme to identify the {@link BackendFactory} to use.
     */
    @Nonnull
    private final Lazy<String> scheme;

    /**
     * {@code true} if file-based URIs are supported.
     */
    private final boolean supportsLocal;

    /**
     * {@code true} if server-based URIs are supported.
     */
    private final boolean supportsRemote;

    /**
     * Constructs a new default {@code AbstractUriFactory}.
     *
     * @param supportsLocal  {@code true} if file-based URIs are supported
     * @param supportsRemote {@code true} if server-based URIs are supported
     */
    protected AbstractUriFactory(boolean supportsLocal, boolean supportsRemote) {
        this.scheme = Lazy.with(() -> Bindings.schemeOf(getClass()));
        this.supportsLocal = supportsLocal;
        this.supportsRemote = supportsRemote;
    }

    /**
     * Constructs a new {@code AbstractUriFactory} with the given {@code scheme}.
     *
     * @param scheme         the scheme to identify the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} to use
     * @param supportsLocal  {@code true} if file-based URIs are supported
     * @param supportsRemote {@code true} if server-based URIs are supported
     */
    protected AbstractUriFactory(String scheme, boolean supportsLocal, boolean supportsRemote) {
        this.scheme = Lazy.of(checkNotNull(scheme, "Cannot create URI without a valid scheme"));
        this.supportsLocal = supportsLocal;
        this.supportsRemote = supportsRemote;
    }

    /**
     * Creates a new {@link fr.inria.atlanmod.neoemf.util.UriFactory} with the given {@code scheme}.
     *
     * @param scheme the scheme of the created URI
     *
     * @return a new builder
     */
    @Nonnull
    @VisibleForTesting
    static UriFactory withScheme(String scheme) {
        return new AbstractUriFactory(scheme, true, true) {};
    }

    /**
     * Gets the scheme to identify the {@link BackendFactory} to use.
     *
     * @return the {@link URI} scheme
     */
    @Nonnull
    protected final String scheme() {
        return scheme.get();
    }

    /**
     * Checks that the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} associated to the created {@link
     * org.eclipse.emf.common.util.URI} supports file-based storage.
     *
     * @return {@code true} if file-based URIs are supported
     */
    protected boolean supportsLocalUris() {
        return supportsLocal;
    }

    /**
     * Checks that the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} associated to the created {@link
     * org.eclipse.emf.common.util.URI} supports server-based storage.
     *
     * @return {@code true} if server-based URIs are supported
     */
    protected boolean supportsRemoteUris() {
        return supportsRemote;
    }

    @Nonnull
    @Override
    public URI createLocalUri(URI uri) {
        if (!supportsLocal) {
            throw new UnsupportedOperationException(String.format("%s does not support file-based URI", getClass().getSimpleName()));
        }

        checkNotNull(uri, "uri");

        if (Objects.equals(scheme(), uri.scheme())) {
            checkArgument(BackendFactoryRegistry.getInstance().isRegistered(uri.scheme()), "Unregistered scheme (%s)", uri.scheme());

            return new FileBasedUri(uri);
        }

        if (Objects.equals(FileBasedUri.SCHEME, uri.scheme())) {
            return createLocalUri(new File(uri.toFileString()));
        }

        throw new IllegalArgumentException(String.format("Cannot create URI with the scheme %s", uri.scheme()));
    }

    @Nonnull
    @Override
    public URI createLocalUri(File file) {
        checkNotNull(file, "file");

        final URI fileUri = URI.createFileURI(file.getAbsolutePath());

        final URI uri = URI.createHierarchicalURI(scheme(),
                fileUri.authority(),
                fileUri.device(),
                fileUri.segments(),
                fileUri.query(),
                fileUri.fragment());

        return createLocalUri(uri);
    }

    @Nonnull
    @Override
    public URI createLocalUri(String filePath) {
        checkNotNull(filePath, "filePath");

        return createLocalUri(new File(filePath));
    }

    @Nonnull
    @Override
    public URI createRemoteUri(String host, int port, URI model) {
        checkNotNull(model, "model");
        checkNotNull(model.segments(), "model.segments");
        checkArgument(model.segments().length == 1, "model.segments must contains only one occurence");

        return createRemoteUri(host, port, model.segments()[0]);
    }

    @Nonnull
    @Override
    public URI createRemoteUri(String host, int port, String model) {
        checkNotNull(host, "host");

        if (!supportsRemoteUris()) {
            throw new UnsupportedOperationException(String.format("%s does not support server-based URIs", getClass().getSimpleName()));
        }

        try {
            return createRemoteUri(InetAddress.getByName(host), port, model);
        }
        catch (UnknownHostException e) {
            throw new IllegalArgumentException(String.format("Unknown host: %s", host), e);
        }
    }

    @Nonnull
    @Override
    public URI createRemoteUri(InetAddress host, int port, URI model) {
        checkNotNull(model, "model");
        checkNotNull(model.segments(), "model.segments");
        checkArgument(model.segments().length == 1, "model.segments must contains only one occurence");

        return createRemoteUri(host, port, model.segments()[0]);
    }

    @Nonnull
    @Override
    public URI createRemoteUri(InetAddress host, int port, String model) {
        if (!supportsRemoteUris()) {
            throw new UnsupportedOperationException(String.format("%s does not support server-based URIs", getClass().getSimpleName()));
        }

        checkNotNull(host, "host");
        checkGreaterThanOrEqualTo(port, 0, "port (%d) must be between 0-65535", port);
        checkLessThanOrEqualTo(port, 65535, "port (%d) must be between 0-65535", port);
        checkNotNull(model, "model");

        checkArgument(BackendFactoryRegistry.getInstance().isRegistered(scheme()), "Unregistered scheme (%s)", scheme());

        return URI.createHierarchicalURI(scheme(),
                host.getHostAddress() + ':' + port,
                null,
                new String[]{model},
                null,
                null);
    }
}
