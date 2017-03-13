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

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays;

import java.io.File;
import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * ???
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendArrays extends AbstractBerkeleyDbBackend implements ManyValueWithArrays {

    /**
     * Constructs a new {@code BerkeleyDbBackendIndices} on the given {@code file} with the given {@code envConfig}.
     * <p>
     * <b>Note:</b> The detail message associated with cause is not automatically incorporated into this exception's
     * detail message. This constructor is protected. To create a new {@code BerkeleyDbBackendArrays} use {@link
     * BackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     *
     * @param file      ???
     * @param envConfig ???
     * @param dbConfig  ???
     */
    protected BerkeleyDbBackendArrays(File file, EnvironmentConfig envConfig, DatabaseConfig dbConfig) {
        super(file, envConfig, dbConfig);
    }
}
