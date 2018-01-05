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

import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithArrays;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BerkeleyDbBackend} that use a {@link ManyValueWithArrays} mapping for storing features.
 *
 * @see BerkeleyDbBackendFactory
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendArrays extends AbstractBerkeleyDbBackend implements ManyValueWithArrays {

    /**
     * Constructs a new {@code BerkeleyDbBackendArrays} wrapping the provided {@code environment}.
     *
     * @param environment    the database environment
     * @param databaseConfig the database configuration
     *
     * @see BerkeleyDbBackendFactory
     */
    protected BerkeleyDbBackendArrays(Environment environment, DatabaseConfig databaseConfig) {
        super(environment, databaseConfig);
    }
}
