/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An adapter on top of a {@code Resource} manager.
 */
@ParametersAreNonnullByDefault
public interface Adapter {

    /**
     * Creates a resource from the given {@code name} and returns its path.
     *
     * @return the resource file
     */
    @Nonnull
    File getOrCreateResource(String name) throws IOException;

    /**
     * Creates a datastore from the given {@code file} in the default location.
     *
     * @return the datastore location
     */
    @Nonnull
    File getOrCreateStore(File file, ImmutableConfig config, boolean useDirectImport) throws IOException;

    /**
     * Creates a datastore from the given {@code file} in a temporary location.
     *
     * @return the datastore location
     */
    @Nonnull
    @SuppressWarnings("UnusedReturnValue")
    File createTempStore(File file, ImmutableConfig config, boolean useDirectImport) throws IOException;

    /**
     * Loads a resource file from the given {@code file}.
     *
     * @return the loaded resource
     */
    @Nonnull
    Resource load(File file, ImmutableConfig config) throws IOException;

    /**
     * Saves the given {@code resource}.
     */
    void save(Resource resource, ImmutableConfig config) throws IOException;

    /**
     * Unloads the given {@code resource}.
     */
    void unload(Resource resource);

    /**
     * Copies a datastore from the {@code file} to a temporary location.
     *
     * @return the location of the new datastore
     */
    @Nonnull
    File copy(File file) throws IOException;

    /**
     * An {@link Adapter} used to create {@link Resource}s.
     */
    @ParametersAreNonnullByDefault
    interface Internal extends Adapter {

        /**
         * Checks that this {@code Adapter} supports the creation of {@link DataMapper}s.
         *
         * @return {@code true} if this {@code Adapter} supports the creation of {@link DataMapper}s
         */
        default boolean supportsMapper() {
            return false;
        }

        /**
         * Creates a new {@link DataMapper} in the given {@code file}.
         *
         * @return a new {@link DataMapper}
         *
         * @throws UnsupportedOperationException if this {@code Adapter} does not support {@link DataMapper} creation
         * @see #supportsMapper()
         */
        default DataMapper createMapper(File file, ImmutableConfig config) {
            throw new UnsupportedOperationException("This adapter does not support DataMapper creation");
        }

        /**
         * Returns the extension of the adapted {@link Resource}, used to create the stores.
         *
         * @return the extension
         */
        @Nonnull
        String getResourceExtension();

        /**
         * Returns the extension of the {@link Resource}, used for benchmarks.
         *
         * @return the extension
         */
        @Nonnull
        String getStoreExtension();

        /**
         * Retrieves and initializes the {@link EPackage} used by this adapter.
         *
         * @return the package
         */
        @Nonnull
        EPackage initAndGetEPackage();

        /**
         * Creates a new {@link Resource} in the given {@code file}, by using the given {@code resourceSet}.
         *
         * @param file        the file to create the resource
         * @param resourceSet the resource set used to created the resource
         *
         * @return a new resource
         */
        @Nonnull
        Resource createResource(File file, ResourceSet resourceSet);

        /**
         * Returns the default {@link Map} options of this adapter
         *
         * @return the {@link Map} options
         */
        @Nonnull
        Map<String, ?> getOptions();
    }
}
