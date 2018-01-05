/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BackendFactory} that creates {@link BerkeleyDbBackend} instances.
 */
@ParametersAreNonnullByDefault
public class BerkeleyDbBackendFactory extends AbstractBackendFactory<BerkeleyDbConfig> {

    /**
     * The literal description of the factory.
     */
    private static final String NAME = "berkeleydb";

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
    protected Backend createLocalBackend(Path directory, BerkeleyDbConfig config) throws Exception {
        if (!directory.toFile().exists()) {
            Files.createDirectories(directory);
        }

        final boolean isReadOnly = config.isReadOnly();

        EnvironmentConfig environmentConfig = new EnvironmentConfig()
                .setAllowCreate(!isReadOnly)
                .setReadOnly(isReadOnly);

        Environment environment = new Environment(directory.toFile(), environmentConfig);

        DatabaseConfig databaseConfig = new DatabaseConfig()
                .setAllowCreate(!isReadOnly)
                .setReadOnly(isReadOnly)
                .setSortedDuplicates(false)
                .setDeferredWrite(true);

        return createMapper(config.getMapping(), environment, databaseConfig);
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
