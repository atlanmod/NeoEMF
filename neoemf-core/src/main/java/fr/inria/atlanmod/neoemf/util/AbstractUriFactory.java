/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.commons.Lazy;
import fr.inria.atlanmod.commons.annotation.VisibleForTesting;
import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkGreaterThanOrEqualTo;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

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
     * Constructs a new default {@code AbstractUriFactory}.
     */
    protected AbstractUriFactory() {
        this.scheme = Lazy.with(() -> Bindings.schemeOf(getClass()));
    }

    /**
     * Constructs a new {@code AbstractUriFactory} with the given {@code scheme}.
     *
     * @param scheme the scheme to identify the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} to use
     */
    protected AbstractUriFactory(String scheme) {
        this.scheme = Lazy.of(checkNotNull(scheme, "Cannot create URI without a valid scheme"));
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
        return new AbstractUriFactory(scheme) {

            @Override
            public boolean supportsLocalUris() {
                return true;
            }

            @Override
            public boolean supportsRemoteUris() {
                return true;
            }
        };
    }

    /**
     * Gets the scheme to identify the {@link BackendFactory} to use.
     *
     * @return the {@link URI} scheme
     */
    @Nonnull
    private String scheme() {
        return scheme.get();
    }

    @Nonnull
    @Override
    public URI createLocalUri(URI uri) {
        if (!supportsLocalUris()) {
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
        return createRemoteUri(host, port, model.segments());
    }

    @Nonnull
    @Override
    public URI createRemoteUri(String host, int port, String... segments) {
        if (!supportsRemoteUris()) {
            throw new UnsupportedOperationException(String.format("%s does not support server-based URIs", getClass().getSimpleName()));
        }

        checkNotNull(host, "host");
        checkNotNull(segments, "segments");
        checkGreaterThanOrEqualTo(port, 0, "port (%d) must not be negative", port);

        checkArgument(BackendFactoryRegistry.getInstance().isRegistered(scheme()), "Unregistered scheme (%s)", scheme());

        return URI.createHierarchicalURI(scheme(),
                host + ':' + port,
                null,
                segments,
                null,
                null);
    }
}
