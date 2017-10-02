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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ConfigValue;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;
import fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

/**
 * A factory that creates {@link BerkeleyDbBackend} instances.
 * <p>
 * As other implementations of {@link BackendFactory}, this class can create transient and persistent databases.
 * Persistent back-end creation can be configured using {@link fr.inria.atlanmod.neoemf.resource.PersistentResource#save(Map)}
 * and {@link fr.inria.atlanmod.neoemf.resource.PersistentResource#load(Map)} option maps.
 *
 * @see BerkeleyDbBackend
 * @see fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResource
 */
@ParametersAreNonnullByDefault
public class BerkeleyDbBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = "berkeleydb";

    /**
     * Constructs a new {@code BerkeleyDbBackendFactory}.
     */
    protected BerkeleyDbBackendFactory() {
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
    public PersistentBackend createPersistentBackend(URI uri, ImmutableConfig baseConfig) {
        BerkeleyDbBackend backend;

        checkArgument(uri.isFile(), "%s only supports file-based URIs", getClass().getSimpleName());

        try {
            Path baseDirectory = Paths.get(uri.toFileString());

            // Merge and check conflicts between the two configurations
            ImmutableConfig mergedConfig = Config.load(baseDirectory)
                    .orElseGet(BerkeleyDbConfig::newConfig)
                    .merge(baseConfig);

            String mapping = mergedConfig.getMapping();
            boolean isReadOnly = mergedConfig.isReadOnly();

            if (Files.notExists(baseDirectory)) {
                Files.createDirectories(baseDirectory);
            }

            EnvironmentConfig environmentConfig = new EnvironmentConfig()
                    .setAllowCreate(!isReadOnly)
                    .setReadOnly(isReadOnly);

            Environment environment = new Environment(baseDirectory.toFile(), environmentConfig);

            DatabaseConfig databaseConfig = new DatabaseConfig()
                    .setAllowCreate(!isReadOnly)
                    .setReadOnly(isReadOnly)
                    .setSortedDuplicates(false)
                    .setDeferredWrite(true);

            backend = createMapper(mapping,
                    new ConfigValue<>(environment, Environment.class),
                    new ConfigValue<>(databaseConfig, DatabaseConfig.class));

            mergedConfig.save(baseDirectory);
        }
        catch (Exception e) {
            throw new InvalidBackendException("Unable to open the BerkeleyDB database", e);
        }

        return backend;
    }

    @Nonnull
    @Override
    // TODO Remove this implementation
    public Backend createTransientBackend() {
        BerkeleyDbBackend backend;

        try {
            File directory = Files.createTempDirectory("neoemf").toFile();

            EnvironmentConfig environmentConfig = new EnvironmentConfig()
                    .setAllowCreate(true)
                    .setConfigParam(EnvironmentConfig.LOG_MEM_ONLY, Boolean.TRUE.toString());

            Environment environment = new Environment(directory, environmentConfig);

            DatabaseConfig databaseConfig = new DatabaseConfig()
                    .setAllowCreate(true)
                    .setSortedDuplicates(false)
                    .setDeferredWrite(true);

            backend = new BerkeleyDbBackendIndices(environment, databaseConfig);
        }
        catch (Exception e) {
            throw new InvalidBackendException(e);
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
        static final BackendFactory INSTANCE = new BerkeleyDbBackendFactory();
    }
}
