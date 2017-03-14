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
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;

/**
 * ???
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

        File file = new File(uri.toFileString());

        try {
            File dir = new File(uri.toFileString());
            if (!dir.exists()) {
                Files.createDirectories(dir.toPath());
            }

            EnvironmentConfig envConfig = new EnvironmentConfig()
                    .setAllowCreate(true);

            DatabaseConfig dbConfig = new DatabaseConfig()
                    .setAllowCreate(true)
                    .setSortedDuplicates(false)
                    .setDeferredWrite(true);

            backend = new BerkeleyDbBackendIndices(dir, envConfig, dbConfig);

            processGlobalConfiguration(file);
        }
        catch (IOException e) {
            throw new InvalidDataStoreException(e);
        }

        return backend;
    }

    @Nonnull
    @Override
    public Backend createTransientBackend() {
        BerkeleyDbBackend backend;

        try {
            File dir = new File(BerkeleyDbURI.createFileURI(Files.createTempDirectory("neoemf").toFile()).toFileString());

            EnvironmentConfig envConfig = new EnvironmentConfig()
                    .setAllowCreate(true)
                    .setConfigParam(EnvironmentConfig.LOG_MEM_ONLY, "true");

            DatabaseConfig dbConfig = new DatabaseConfig()
                    .setAllowCreate(true)
                    .setSortedDuplicates(false)
                    .setDeferredWrite(true);

            backend = new BerkeleyDbBackendIndices(dir, envConfig, dbConfig);
        }
        catch (IOException e) {
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
