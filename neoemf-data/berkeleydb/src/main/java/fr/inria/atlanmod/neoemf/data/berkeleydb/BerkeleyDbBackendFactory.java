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

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptions;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;

/**
 * A factory that creates {@link BerkeleyDbBackend} instances.
 * <p>
 * As other implementations of {@link BackendFactory}, this class can create transient and persistent databases.
 * Persistent back-end creation can be configured using {@link PersistentResource#save(Map)} and {@link
 * PersistentResource#load(Map)} option maps.
 *
 * @see PersistentResource
 * @see BerkeleyDbBackend
 * @see BerkeleyDbOptions
 */
@ParametersAreNonnullByDefault
public class BerkeleyDbBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    public static final String NAME = BerkeleyDbBackend.NAME.toLowerCase();

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
    public Backend createPersistentBackend(URI uri, Map<String, Object> options) {
        BerkeleyDbBackend backend;

        checkArgument(uri.isFile(),
                "%s only supports file-based URIs",
                BerkeleyDbBackendFactory.class.getSimpleName());
        try {
            File file = new File(uri.toFileString());

            File directory = new File(uri.toFileString());
            if (!directory.exists()) {
                Files.createDirectories(directory.toPath());
            }

            EnvironmentConfig environmentConfig = new EnvironmentConfig()
                    .setAllowCreate(true);

            Environment environment = new Environment(directory, environmentConfig);

            DatabaseConfig databaseConfig = new DatabaseConfig()
                    .setAllowCreate(true)
                    .setSortedDuplicates(false)
                    .setDeferredWrite(true);

            backend = newInstanceOf(mappingFrom(options),
                    new ConstructorParameter(environment, Environment.class),
                    new ConstructorParameter(databaseConfig, DatabaseConfig.class));

            processGlobalConfiguration(file);
        }
        catch (Exception e) {
            throw new InvalidDataStoreException(e);
        }

        return backend;
    }

    @Nonnull
    @Override
    public Backend createTransientBackend() {
        BerkeleyDbBackend backend;

        try {
            File directory = new File(BerkeleyDbURI.createFileURI(Files.createTempDirectory("neoemf").toFile()).toFileString());

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
            throw new InvalidDataStoreException(e);
        }

        return backend;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final BackendFactory INSTANCE = new BerkeleyDbBackendFactory();
    }
}
