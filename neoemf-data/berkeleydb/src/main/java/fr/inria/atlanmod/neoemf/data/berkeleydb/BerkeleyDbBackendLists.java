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

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithLists;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BerkeleyDbBackend} that use a {@link ManyValueWithLists} mapping for storing features.
 *
 * @see BerkeleyDbBackendFactory
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendLists extends AbstractBerkeleyDbBackend implements ManyValueWithLists {

    /**
     * Constructs a new {@code BerkeleyDbBackendLists} wrapping the provided {@code environment}.
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@link BerkeleyDbBackend} use {@link
     * BackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, java.util.Map)}.
     *
     * @param environment    the database environment
     * @param databaseConfig the database configuration
     *
     * @see BerkeleyDbBackendFactory
     */
    protected BerkeleyDbBackendLists(Environment environment, DatabaseConfig databaseConfig) {
        super(environment, databaseConfig);
    }
}
