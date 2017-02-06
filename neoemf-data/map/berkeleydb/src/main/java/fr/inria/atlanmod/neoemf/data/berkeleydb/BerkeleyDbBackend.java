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

import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EClass;

import java.io.File;

/**
 * ???
 *
 * @param <P> the "self"-type of this {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend}
 */
public abstract class BerkeleyDbBackend extends AbstractPersistenceBackend {

    /**
     * The literal description of this back-end.
     */
    public static final String NAME = "berkeleydb";

    /**
     * ???
     */
    protected final DatabaseConfig databaseConfig;

    /**
     * ???
     */
    private final EnvironmentConfig environmentConfig;

    /**
     * ???
     */
    private final File file;

    /**
     * A persistent map that stores the container of {@link PersistentEObject}, identified by the object {@link Id}.
     */
    protected Database containers;

    /**
     * A persistent map that stores the {@link EClass} for {@link PersistentEObject}, identified by the object {@link
     * Id}.
     */
    protected Database instances;

    /**
     * A persistent map that stores structural features values for {@link PersistentEObject}, identified by the
     * associated {@link FeatureKey}.
     */
    protected Database features;

    /**
     * ???
     */
    protected Environment environment;

    /**
     * ???
     */
    protected boolean isClosed = true;

    /**
     * ???
     *
     * @param file
     * @param envConfig
     * @param dbConfig
     */
    protected BerkeleyDbBackend(File file, EnvironmentConfig envConfig, DatabaseConfig dbConfig) {
        this.file = file;
        this.environmentConfig = envConfig;
        this.databaseConfig = dbConfig;
    }

    /**
     * ???
     */
    public void open() {
        if (!isClosed()) {
            NeoLogger.warn("This backend is already open");
        }

        try {
            environment = new Environment(file, environmentConfig);

            this.containers = environment.openDatabase(null, "eContainer", databaseConfig);
            this.instances = environment.openDatabase(null, "neoInstanceOf", databaseConfig);
            this.features = environment.openDatabase(null, "features", databaseConfig);

            isClosed = false;
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @Override
    public void save() {
        try {
            this.containers.sync();
            this.instances.sync();
            this.features.sync();
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @Override
    public void close() {
        this.save();

        this.containers.close();
        this.instances.close();
        this.features.close();
        this.environment.close();

        isClosed = true;
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    public void create(Id id) {
        // Do nothing
    }

    @Override
    public boolean has(Id id) {
        return false;
    }

    /**
     * Copies all the contents of this {@code PersistenceBackend} to the {@code target} one.
     *
     * @param target the {@code PersistenceBackend} to copy the database contents to
     */
    public <P extends BerkeleyDbBackend> void copyTo(P target) {
        this.copyDatabaseTo(instances, target.instances);
        this.copyDatabaseTo(features, target.features);
        this.copyDatabaseTo(containers, target.containers);
    }

    /**
     * Utility method to copy the contents from one database to another.
     *
     * @param from the database to copy
     * @param to   the database to copy the database contents to
     */
    protected void copyDatabaseTo(Database from, Database to) {
        try (Cursor cursor = from.openCursor(null, null)) {
            DatabaseEntry key = new DatabaseEntry();
            DatabaseEntry value = new DatabaseEntry();
            while (cursor.getNext(key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                to.put(null, key, value);
            }
        }
        to.sync();
    }
}
