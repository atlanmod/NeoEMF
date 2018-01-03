/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractMapperFactory;

import org.eclipse.emf.common.util.URI;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link BackendFactory}.
 */
@Singleton
@ParametersAreNonnullByDefault
public abstract class AbstractBackendFactory<C extends Config> extends AbstractMapperFactory implements BackendFactory {

    /**
     * Constructs a new {@code AbstractBackendFactory}.
     */
    protected AbstractBackendFactory() {
    }

    /**
     * Creates a {@link Path} from the given {@code uri}.
     *
     * @param uri the URI to convert
     *
     * @return a new file path
     */
    @Nonnull
    private static Path uriToPath(URI uri) {
        return Paths.get(uri.toFileString());
    }

    /**
     * Creates a {@link URL} from the given {@code uri}.
     *
     * @param uri the URI to convert
     *
     * @return a new URL
     *
     * @throws MalformedURLException if an error occurs during the {@link URL} creation
     */
    @Nonnull
    private static URL uriToUrl(URI uri) throws MalformedURLException {
        final String protocol = "http";
        final String delimiter = "_";

        String path = uri.segmentsList().stream()
                .map(s -> s.replaceAll("-", delimiter))
                .collect(Collectors.joining(delimiter));

        return new URL(protocol, uri.host(), isNull(uri.port()) ? -1 : Integer.parseInt(uri.port()), path);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public Backend createBackend(URI uri, ImmutableConfig baseConfig) {
        checkNotNull(uri, "uri");
        checkNotNull(baseConfig, "baseConfig");
        checkArgument(uri.isFile() || uri.isHierarchical(), "URI '%s' must be either file-based or hierarchical", uri.toString());

        try {
            return uri.isFile()
                    ? createLocalBackend(uri, baseConfig)
                    : createRemoteBackend(uri, baseConfig);
        }
        catch (Exception e) {
            throw new InvalidBackendException("Unable to create/open the database", e);
        }
    }

    /**
     * Creates a new file-based {@link Backend}.
     *
     * @param uri        the {@link URI} where to store the back-end
     * @param baseConfig the base configuration that defines the behaviour of the back-end
     *
     * @return a new back-end
     *
     * @throws Exception if an error occurs during the back-end initialization
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private Backend createLocalBackend(URI uri, ImmutableConfig baseConfig) throws Exception {
        Path directory = uriToPath(uri);

        // Merge and check conflicts between the two configurations, or create a new default mutable configuration
        C mergedConfig = (C) Config.load(directory).orElseGet(() -> Config.forName(name())).merge(baseConfig);

        Backend newBackend = createLocalBackend(directory, mergedConfig);

        // Save the configuration next to the created database
        mergedConfig.save(directory);

        return newBackend;
    }

    /**
     * Creates a new file-based {@link Backend}.
     * <p>
     * By default, this method throws an {@link UnsupportedOperationException}.
     *
     * @param directory the file path where to store the database
     * @param config    the configuration that defines the behaviour of the back-end
     *
     * @return a new back-end
     *
     * @throws Exception if an error occurs during the back-end initialization
     */
    @Nonnull
    protected Backend createLocalBackend(Path directory, C config) throws Exception {
        throw new UnsupportedOperationException(String.format("%s only supports file-based URIs", getClass().getSimpleName()));
    }

    /**
     * Creates a new server-based {@link Backend}.
     *
     * @param uri        the {@link URI} where to store the back-end
     * @param baseConfig the base configuration that defines the behaviour of the back-end
     *
     * @return a new back-end
     *
     * @throws Exception if an error occurs during the back-end initialization
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    // TODO Save the configuration in a default NeoEMF directory ('${user.home}/.neoemf/conf/' for example)
    private Backend createRemoteBackend(URI uri, ImmutableConfig baseConfig) throws Exception {
        URL url = uriToUrl(uri);

        // Create a new default mutable configuration
        C mergedConfig = (C) Config.forName(name()).merge(baseConfig);

        return createRemoteBackend(url, mergedConfig);
    }

    /**
     * Creates a new server-based {@link Backend}.
     * <p>
     * By default, this method throws an {@link UnsupportedOperationException}.
     *
     * @param url    the {@link URL} locating the database
     * @param config the configuration that defines the behaviour of the back-end
     *
     * @return a new back-end
     *
     * @throws Exception if an error occurs during the back-end initialization
     */
    @Nonnull
    protected Backend createRemoteBackend(URL url, C config) throws Exception {
        throw new UnsupportedOperationException(String.format("%s only supports hierarchical URIs", getClass().getSimpleName()));
    }
}
