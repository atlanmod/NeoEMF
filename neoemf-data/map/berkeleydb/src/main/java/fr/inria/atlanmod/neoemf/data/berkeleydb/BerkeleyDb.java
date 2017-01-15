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

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.io.File;

// TODO What does this class ?
public class BerkeleyDb {

    private static final String KEY_CONTAINER = "eContainer";
    private static final String KEY_INSTANCE_OF = "neoInstanceOf";
    private static final String KEY_FEATURES = "features";
    private static final String KEY_MULTIVALUED_FEATURES = "multivaluedFeatures";

    DatabaseConfig dbconf;

    /**
     * A persistent map that stores the container of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s.
     */
    private Database containers;

    /**
     * A persistent map that stores the {@link org.eclipse.emf.ecore.EClass} for {@link
     * fr.inria.atlanmod.neoemf.core.PersistentEObject}s. The key is the object {@link
     * fr.inria.atlanmod.neoemf.core.Id}.
     */
    private Database instances;

    /**
     * A persistent map that stores Structural feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s.
     * The key is build using the object {@link fr.inria.atlanmod.neoemf.core.Id} plus the name of the feature.
     */
    private Database features;

    /**
     * A persistent map that store the values of multi-valued features for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s.
     * The key is build using the object {@link fr.inria.atlanmod.neoemf.core.Id} plus the name of the feature plus the
     * index of the value.
     */
    private Database multivaluedFeatures;

    private Environment env;

    public BerkeleyDb(File file) {

        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        try {
            env = new Environment(file, envConfig);
            dbconf = new DatabaseConfig();
            dbconf.setAllowCreate(true);
            dbconf.setSortedDuplicates(false);
            dbconf.setDeferredWrite(true);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    public void open() {
        try {
            this.containers = env.openDatabase(null, KEY_CONTAINER, dbconf);
            this.instances = env.openDatabase(null, KEY_INSTANCE_OF, dbconf);
            this.features = env.openDatabase(null, KEY_FEATURES, dbconf);
            this.multivaluedFeatures = env.openDatabase(null, KEY_MULTIVALUED_FEATURES, dbconf);
        }
        catch (DatabaseException e) {
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
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }
}
