/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.commons.LazyObject;
import fr.inria.atlanmod.commons.annotation.Builder;
import fr.inria.atlanmod.commons.annotation.VisibleForTesting;
import fr.inria.atlanmod.neoemf.bind.BindingEngine;
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
 * An abstract {@link UriBuilder} that manages the assembly and the construction of {@link URI}s.
 */
@Builder("builder")
@ParametersAreNonnullByDefault
public abstract class AbstractUriBuilder implements UriBuilder {

    /**
     * The scheme to identify the {@link BackendFactory} to use.
     */
    @Nonnull
    private final LazyObject<String> scheme;

    /**
     * Constructs a new default {@code AbstractUriBuilder}.
     */
    protected AbstractUriBuilder() {
        this.scheme = LazyObject.with(() -> BindingEngine.schemeOf(getClass()));
    }

    /**
     * Constructs a new {@code AbstractUriBuilder} with the given {@code scheme}.
     *
     * @param scheme the scheme to identify the {@link BackendFactory} to use
     */
    protected AbstractUriBuilder(String scheme) {
        this.scheme = LazyObject.of(checkNotNull(scheme, "Cannot create URI without a valid scheme"));
    }

    /**
     * Creates a new {@link UriBuilder} with the given {@code scheme}.
     *
     * @param scheme the scheme of the created URI
     *
     * @return a new builder
     */
    @Nonnull
    @VisibleForTesting
    public static UriBuilder builder(String scheme) {
        return new AbstractUriBuilder(scheme) {

            @Override
            protected boolean supportsFile() {
                return true;
            }

            @Override
            protected boolean supportsServer() {
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

    /**
     * Checks that the {@link BackendFactory} associated to the created {@link URI} supports file-based storage.
     *
     * @return {@code true} if file-based {@link URI}s are supported
     */
    protected abstract boolean supportsFile();

    /**
     * Checks that the {@link BackendFactory} associated to the created {@link URI} supports server-based storage.
     *
     * @return {@code true} if server-based {@link URI}s are supported
     */
    protected abstract boolean supportsServer();

    @Nonnull
    @Override
    public URI fromUri(URI uri) {
        checkNotNull(uri, "uri");

        if (!supportsFile()) {
            throw new UnsupportedOperationException(String.format("%s does not support file-based URI", getClass().getSimpleName()));
        }

        if (Objects.equals(scheme(), uri.scheme())) {
            checkArgument(BackendFactoryRegistry.getInstance().isRegistered(uri.scheme()),
                    "Unregistered scheme (%s)", uri.scheme());

            return new FileBasedUri(uri);
        }

        if (Objects.equals(FileBasedUri.SCHEME, uri.scheme())) {
            return fromFile(new File(uri.toFileString()));
        }

        throw new IllegalArgumentException(String.format("Cannot create URI with the scheme %s", uri.scheme()));
    }

    @Nonnull
    @Override
    public URI fromFile(String filePath) {
        checkNotNull(filePath, "filePath");

        return fromFile(new File(filePath));
    }

    @Nonnull
    @Override
    public URI fromFile(File file) {
        checkNotNull(file, "file");

        final URI baseUri = URI.createFileURI(file.getAbsolutePath());

        return fromFile(baseUri);
    }

    @Nonnull
    @Override
    public URI fromServer(String host, int port, URI model) {
        return fromServer(host, port, model.segments());
    }

    @Nonnull
    @Override
    public URI fromServer(String host, int port, String... segments) {
        checkNotNull(host, "host");
        checkNotNull(segments, "segments");
        checkGreaterThanOrEqualTo(port, 0, "port (%d) must not be negative", port);

        if (!supportsServer()) {
            throw new UnsupportedOperationException(String.format("%s does not support server-based URIs", getClass().getSimpleName()));
        }

        return URI.createHierarchicalURI(scheme(),
                host + ':' + port,
                null,
                segments,
                null,
                null);
    }

    /**
     * Creates a new {@code URI} from the given {@code uri} by checking the referenced file exists on the file system.
     *
     * @param uri the base filed-based {@link URI}
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if the {@code uri} is {@code null}
     */
    @Nonnull
    private URI fromFile(URI uri) {
        checkArgument(uri.isFile(), "Expecting a file-based URI but was '%s'", uri.toString());

        final URI baseUri = URI.createHierarchicalURI(scheme(),
                uri.authority(),
                uri.device(),
                uri.segments(),
                uri.query(),
                uri.fragment());

        return fromUri(baseUri);
    }
}
