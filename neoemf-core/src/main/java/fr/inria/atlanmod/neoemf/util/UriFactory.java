/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.bind.BindingEngineProvider;
import fr.inria.atlanmod.neoemf.bind.Bindings;

import org.eclipse.emf.common.util.URI;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A factory that creates {@link org.eclipse.emf.common.util.URI} instances.
 * <p>
 * Created URIs are used to locate a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} and select the
 * {@link fr.inria.atlanmod.neoemf.data.BackendFactory} to use for persistence.
 */
@ParametersAreNonnullByDefault
public interface UriFactory {

    /**
     * Creates a URI scheme from the specified {@code baseName}.
     *
     * @param baseName the base name of the scheme
     *
     * @return the URI scheme
     */
    @Nonnull
    static String createScheme(String baseName) {
        return String.format("neo-%s", baseName.toLowerCase());
    }

    /**
     * Retrieves the instance of {@code UriFactory} that is associated to a {@link fr.inria.atlanmod.neoemf.data.BackendFactory}
     * wearing the given {@code name}.
     *
     * @param name the name of the factory
     *
     * @return a new instance of {@code UriFactory}
     */
    @Nonnull
    static UriFactory forName(String name) {
        return BindingEngineProvider.getInstance().getEngine().find(UriFactory.class, Bindings::nameOf, name, null);
    }

    /**
     * Retrieves the instance of {@code UriFactory} that uses the given {@code scheme}.
     *
     * @param scheme the scheme of the builder
     *
     * @return a new instance of {@code UriFactory}
     */
    @Nonnull
    static UriFactory forScheme(String scheme) {
        return BindingEngineProvider.getInstance().getEngine().find(UriFactory.class, Bindings::schemeOf, scheme, null);
    }

    /**
     * Checks that the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} associated to the created {@link
     * org.eclipse.emf.common.util.URI} supports file-based storage.
     *
     * @return {@code true} if file-based URIs are supported
     */
    boolean supportsLocalUris();

    /**
     * Checks that the {@link fr.inria.atlanmod.neoemf.data.BackendFactory} associated to the created {@link
     * org.eclipse.emf.common.util.URI} supports server-based storage.
     *
     * @return {@code true} if server-based URIs are supported
     */
    boolean supportsRemoteUris();

    /**
     * Creates a new URI from the given {@code uri}.
     * <p>
     * This method checks that the scheme of the provided {@code uri} can be used to create a new {@link
     * fr.inria.atlanmod.neoemf.util.UriFactory}. Its scheme must be registered in the {@link
     * fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry}.
     *
     * @param uri the base file-based URI
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if the {@code uri} is {@code null}
     * @throws IllegalArgumentException      if the scheme of the provided {@code uri} is not registered in the {@link
     *                                       fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry}
     */
    @Nonnull
    URI createLocalUri(URI uri);

    /**
     * Creates a new URI from the given {@code file} descriptor.
     *
     * @param file the {@link File} to build a URI from
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if the {@code file} is {@code null}
     */
    @Nonnull
    URI createLocalUri(File file);

    /**
     * Creates a new URI from the given {@code file} descriptor.
     *
     * @param filePath the path of the {@link File} to build a URI from
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if the {@code filePath} is {@code null}
     */
    @Nonnull
    URI createLocalUri(String filePath);

    /**
     * Creates a new URI from the {@code host}, {@code port}, and {@code model} by creating a hierarchical URI that
     * references the distant model resource.
     *
     * @param host  the address of the server (use {@code "localhost"} if the server is running locally)
     * @param port  the port of the server
     * @param model a URI identifying the model in the database
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if any of the parameters is {@code null}
     * @throws IllegalArgumentException      if {@code port < 0}
     */
    @Nonnull
    URI createRemoteUri(String host, int port, URI model);

    /**
     * Creates a new URI from the {@code host}, {@code port}, and {@code model} by creating a hierarchical URI that
     * references the distant model resource.
     *
     * @param host     the address of the server (use {@code "localhost"} if the server is running locally)
     * @param port     the port of the server
     * @param segments a string identifying the model in the database
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if any of the parameters is {@code null}
     * @throws IllegalArgumentException      if {@code port < 0}
     */
    @Nonnull
    URI createRemoteUri(String host, int port, String... segments);
}
