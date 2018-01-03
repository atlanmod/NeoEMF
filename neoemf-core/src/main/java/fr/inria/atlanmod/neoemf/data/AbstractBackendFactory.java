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

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

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

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public Backend createBackend(URI uri, ImmutableConfig baseConfig) {
        Backend newBackend;

        try {
            if (uri.isFile()) {
                Path baseDirectory = Paths.get(uri.toFileString());

                // Merge and check conflicts between the two configurations, or create a new default configuration
                C mergedConfig = (C) Config.load(baseDirectory)
                        .orElseGet(() -> Config.forName(name()))
                        .merge(baseConfig);

                newBackend = createLocalBackend(baseDirectory, mergedConfig);

                // Save the configuration next to the created database
                mergedConfig.save(baseDirectory);
            }
            else if (uri.isHierarchical()) {
                final String targetDelimiter = "_";
                String path = uri.segmentsList().stream()
                        .map(s -> s.replaceAll("-", targetDelimiter))
                        .collect(Collectors.joining(targetDelimiter));

                URL url = new URL("http", uri.host(), isNull(uri.port()) ? -1 : Integer.parseInt(uri.port()), path);

                newBackend = createRemoteBackend(url, (C) baseConfig);
            }
            else {
                throw new IllegalArgumentException(String.format("URI '%s' must be either file-based or hierarchical", uri.toString()));
            }
        }
        catch (InvalidBackendException e) {
            throw e;
        }
        catch (ClassCastException e) {
            throw new IllegalArgumentException(String.format("Unknown configuration of type %s", baseConfig.getClass().getName()), e);
        }
        catch (Exception e) {
            throw new InvalidBackendException("Unable to open the database", e);
        }

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
     * @see #createBackend(URI, ImmutableConfig)
     */
    @Nonnull
    protected Backend createLocalBackend(Path directory, C config) throws Exception {
        throw new UnsupportedOperationException(String.format("%s only supports file-based URIs", getClass().getSimpleName()));
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
     * @see #createBackend(URI, ImmutableConfig)
     */
    @Nonnull
    protected Backend createRemoteBackend(URL url, C config) throws Exception {
        throw new UnsupportedOperationException(String.format("%s only supports hierarchical URIs", getClass().getSimpleName()));
    }
}
