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
import fr.inria.atlanmod.neoemf.bind.annotation.FactoryName;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidBackendException;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

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
 * @see fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptions
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResource
 */
@ParametersAreNonnullByDefault
public class BerkeleyDbBackendFactory extends AbstractBackendFactory {

    /**
     * The literal description of the factory.
     */
    @FactoryName
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
    public PersistentBackend createPersistentBackend(URI uri, Map<String, Object> options) {
        BerkeleyDbBackend backend;

        checkArgument(uri.isFile(), "BerkeleyDbBackendFactory only supports file-based URIs");

        boolean isReadOnly = StoreFactory.isDefined(options, PersistentStoreOptions.READ_ONLY);

        try {
            Path baseDirectory = Paths.get(uri.toFileString());

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

            String mapping = mappingFrom(options);
            backend = newInstanceOf(mapping,
                    new ConstructorParameter(environment, Environment.class),
                    new ConstructorParameter(databaseConfig, DatabaseConfig.class));

            processGlobalConfiguration(baseDirectory, mapping);
        }
        catch (Exception e) {
            throw new InvalidBackendException(e);
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
        private static final BackendFactory INSTANCE = new BerkeleyDbBackendFactory();
    }
}
