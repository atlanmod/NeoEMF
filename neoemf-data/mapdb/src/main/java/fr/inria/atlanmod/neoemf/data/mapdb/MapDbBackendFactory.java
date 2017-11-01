/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
import fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig;

import org.eclipse.emf.common.util.URI;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

/**
 * A factory that creates {@link MapDbBackend} instances.
 * <p>
 * As other implementations of {@link BackendFactory}, this class can create transient and persistent databases.
 * Persistent back-end creation can be configured using {@link fr.inria.atlanmod.neoemf.resource.PersistentResource#save(Map)}
 * and {@link fr.inria.atlanmod.neoemf.resource.PersistentResource#load(Map)} option maps.
 *
 * @see MapDbBackend
 * @see fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResource
 */
@ParametersAreNonnullByDefault
public class MapDbBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = "mapdb";

    /**
     * Constructs a new {@code MapDbBackendFactory}.
     */
    protected MapDbBackendFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static BackendFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Nonnull
    @Override
    public Backend createBackend(URI uri, ImmutableConfig baseConfig) {
        MapDbBackend backend;

        checkArgument(uri.isFile(), "%s only supports file-based URIs", getClass().getSimpleName());

        try {
            Path baseDirectory = Paths.get(uri.toFileString());

            // Merge and check conflicts between the two configurations
            ImmutableConfig mergedConfig = Config.load(baseDirectory)
                    .orElseGet(MapDbConfig::newConfig)
                    .merge(baseConfig);

            String mapping = mergedConfig.getMapping();
            boolean isReadOnly = mergedConfig.isReadOnly();

            if (Files.notExists(baseDirectory)) {
                Files.createDirectories(baseDirectory);
            }

            DBMaker.Maker dbBuilder = DBMaker
                    .fileDB(baseDirectory.resolve("data").toFile())
                    .fileMmapEnableIfSupported();

            if (isReadOnly) {
                dbBuilder.readOnly();
            }

            DB db = dbBuilder.make();

            backend = createMapper(mapping, db);

            mergedConfig.save(baseDirectory);
        }
        catch (Exception e) {
            throw new InvalidBackendException("Unable to open the MapDB database", e);
        }

        return backend;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final BackendFactory INSTANCE = new MapDbBackendFactory();
    }
}
