/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
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
     * @return {@code true} if the context is initialized, {@code false} if the {@link
     * fr.inria.atlanmod.neoemf.data.Backend} failed to initialize.
     */
    default boolean isInitialized() {
        // By default: a context is always ready
        return true;
    }

    /**
     * Initializes this context if necessary.
     *
     * @implNote Should never throws an exception
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
    ImmutableConfig config();

    /**
     * Returns the {@link URI} scheme used by this context.
     *
     * @return the {@link URI} scheme
     */
    @Nonnull
    String uriScheme();

    /**
     * Returns {@code true} if this context is persistent.
     *
     * @return {@code true} if this context is persistent
     */
    default boolean isPersistent() {
        return true;
    }

    /**
     * Creates a new {@link URI} from the given {@code uri}, according to this context.
     *
     * @param uri the base URI
     *
     * @return the created URI
     */
    @Nonnull
    URI createUri(URI uri);

    /**
     * Creates a new {@link URI} from the given {@code file}, according to this context.
     *
     * @param file the file to build a URI from
     *
     * @return the created URI
     */
    @Nonnull
    URI createUri(File file);

    /**
     * Creates a new persistent resource in the given {@code file}.
     *
     * @param file the file from which to save data
     *
     * @return a new persistent resource
     *
     * @see ContextualResourceBuilder
     */
    @Nonnull
    PersistentResource createPersistentResource(File file) throws IOException;

    /**
     * Loads an existing persistent resource from the given {@code file}.
     *
     * @param file the file from which to load data
     *
     * @return a new persistent resource
     *
     * @see ContextualResourceBuilder
     */
    @Nonnull
    PersistentResource loadPersistentResource(File file) throws IOException;

    /**
     * Creates a new transient resource on the given {@code file}.
     *
     * @param file the file where to save data when the resource will persist
     *
     * @return a new transient resource
     *
     * @see ContextualResourceBuilder
     */
    @Nonnull
    PersistentResource createTransientResource(File file) throws IOException;

    /**
     * Creates a new data mapper on the given {@code uri}.
     *
     * @param file the file to build a URI from
     *
     * @return a new data mapper
     *
     * @see ContextualResourceBuilder
     */
    @Nonnull
    DataMapper createMapper(File file);
}
