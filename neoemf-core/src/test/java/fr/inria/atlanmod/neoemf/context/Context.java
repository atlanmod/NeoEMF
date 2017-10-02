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

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A utility class representing a test-case context.
 * <p>
 * All methods are a proxy to their associated method; they do not provide new functionalities.
 */
@ParametersAreNonnullByDefault
public interface Context {

    /**
     * Checks whether this context is ready for testing.
     *
     * @return {@code true} if the context is initialized, {@code false} otherwise.
     */
    default boolean isInitialized() {
        // By default: a context is always ready
        return true;
    }

    /**
     * Initializes this context if necessary.
     */
    default Context init() {
        return this;
    }

    /**
     * Returns the name of this context.
     *
     * @return the name
     */
    @Nonnull
    String name();

    /**
     * Returns the {@link BackendFactory} used by this context.
     *
     * @return the factory
     */
    @Nonnull
    BackendFactory factory();

    /**
     * Returns the base configuration used by this context.
     *
     * @return a new configuration
     */
    @Nonnull
    Config config();

    /**
     * Returns the {@link URI} scheme used by this context.
     *
     * @return the {@link URI} scheme
     */
    @Nonnull
    String uriScheme();

    /**
     * Creates a new {@link URI} from the given {@code uri}, according to this context.
     *
     * @param uri the base {@link URI}
     *
     * @return the created {@link URI}
     */
    @Nonnull
    URI createUri(URI uri);

    /**
     * Creates a new {@link URI} from the given {@code file}, according to this context.
     *
     * @param file the {@link File} to build a {@link URI} from
     *
     * @return the created {@link URI}
     */
    @Nonnull
    URI createUri(File file);

    /**
     * Creates a new persistent resource from the given {@code ePackage} on the given {@code file}.
     *
     * @param file the file from which to load/save data
     *
     * @return a new {@link PersistentResource}
     *
     * @see ContextualResourceBuilder
     */
    @Nonnull
    PersistentResource createPersistentResource(File file) throws IOException;

    /**
     * Creates a new transient resource from the given {@code ePackage} on the given {@code file}.
     *
     * @param file the file from which to load/save data
     *
     * @return a new {@link PersistentResource}
     *
     * @see ContextualResourceBuilder
     */
    @Nonnull
    PersistentResource createTransientResource(File file) throws IOException;

    /**
     * Loads an existing resource from the given {@code file}.
     *
     * @param file the file from which to load/save data
     *
     * @return a new {@link PersistentResource}
     *
     * @see ContextualResourceBuilder
     */
    @Nonnull
    PersistentResource loadResource(File file) throws IOException;

    /**
     * Creates a new {@link DataMapper} on the given {@code uri}.
     *
     * @param file the URI of the backend
     *
     * @return a new {@link DataMapper}
     *
     * @see ContextualResourceBuilder
     */
    @Nonnull
    DataMapper createMapper(File file);
}
