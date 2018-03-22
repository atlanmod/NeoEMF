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

import fr.inria.atlanmod.neoemf.data.AbstractBackendFactory;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.config.BerkeleyDbConfig;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.BackendFactory} that creates {@link BerkeleyDbBackend} instances.
 */
@ParametersAreNonnullByDefault
public class BerkeleyDbBackendFactory extends AbstractBackendFactory<BerkeleyDbConfig> {

    /**
     * The literal description of the factory.
     */
    private static final String NAME = "berkeleydb";

    /**
     * @deprecated Use the default constructor instead.
     */
    @Nonnull
    @Deprecated
    public static BackendFactory getInstance() {
        return new BerkeleyDbBackendFactory();
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

        DatabaseConfig databaseConfig = new DatabaseConfig()
                .setAllowCreate(!isReadOnly)
                .setReadOnly(isReadOnly)
                .setSortedDuplicates(false)
                .setDeferredWrite(true);

        Environment environment = new Environment(directory.toFile(), environmentConfig);

        return createMapper(config.getMapping(), environment, databaseConfig);
    }
}
