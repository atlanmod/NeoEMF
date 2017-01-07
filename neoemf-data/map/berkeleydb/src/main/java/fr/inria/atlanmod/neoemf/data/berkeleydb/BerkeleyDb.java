/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import java.io.File;

public class BerkeleyDb {

    private static final String KEY_CONTAINER = "eContainer";
    private static final String KEY_INSTANCE_OF = "neoInstanceOf";
    private static final String KEY_FEATURES = "features";
    private static final String KEY_MULTIVALUED_FEATURES = "multivaluedFeatures";

    /**
     * A persistent map that stores the container of persistent EObjects.
     */
    private Database containers;

    /**
     * A persistent map that stores the EClass for persistent EObjects.
     * The key is the persistent object Id.
     */
    private Database instances;

    /**
     * A persistent map that stores Structural feature values for persistent EObjects.
     * The key is build using the persistent object Id plus the name of the feature.
     */
    private Database features;

    /**
     * A persistent map that store the values of multivalued features for persistent EObjects.
     * The key is build using the persistent object Id plus the name of the feature plus the index of the value.
     */
    private Database multivaluedFeatures;

    private Environment env;
    DatabaseConfig dbconf;

    public BerkeleyDb(File file) {

        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        try {
            env = new Environment(file, envConfig);
            dbconf = new DatabaseConfig();
            dbconf.setAllowCreate(true);
            dbconf.setSortedDuplicates(false);
            dbconf.setDeferredWrite(true);


        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    public void open() {
        try {
            this.containers = env.openDatabase(null, KEY_CONTAINER, dbconf);
            this.instances = env.openDatabase(null, KEY_INSTANCE_OF, dbconf);
            this.features = env.openDatabase(null, KEY_FEATURES, dbconf);
            this.multivaluedFeatures = env.openDatabase(null, KEY_MULTIVALUED_FEATURES, dbconf);
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }

    }

    public void close() {
        try {
            this.containers.close();
            this.instances.close();
            this.features.close();
            this.multivaluedFeatures.close();
            env.close();
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }

    }
}
