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

import com.sleepycat.je.*;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.*;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import java.io.File;
import java.util.Map;

public class BerkeleyDBPersistenceBackend extends AbstractPersistenceBackend {

    /**
     * The literal description of this backend.
     */
    public static final String NAME = "berkeleydb";

    private static final String KEY_CONTAINER = "eContainer";
    private static final String KEY_INSTANCE_OF = "neoInstanceOf";
    private static final String KEY_FEATURES = "features";
    private static final String KEY_MULTIVALUED_FEATURES = "multivaluedFeatures";
    private boolean isClosed = true;
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

    private Environment environment;
    private DatabaseConfig databaseConfig;
    private File file;
    private EnvironmentConfig environmentConfig;


    BerkeleyDBPersistenceBackend(File f, EnvironmentConfig ec) {
        file = f;
        environmentConfig = ec;
        databaseConfig = new DatabaseConfig();
        databaseConfig.setAllowCreate(true);
        databaseConfig.setSortedDuplicates(false);
        databaseConfig.setDeferredWrite(true);
    }

    public void open() {

        NeoLogger.info("BerkeleyDBPersistentBackend::open()");
        //NeoLogger.info(environmentConfig.getConfigParam(EnvironmentConfig.LOG_MEM_ONLY));
        if (!isClosed()) {
            NeoLogger.warn("Opening an open backend");
        }
        try {
            environment = new Environment(file, environmentConfig);

            this.containers = environment.openDatabase(null, KEY_CONTAINER, databaseConfig);
            this.instances = environment.openDatabase(null, KEY_INSTANCE_OF, databaseConfig);
            this.features = environment.openDatabase(null, KEY_FEATURES, databaseConfig);
            this.multivaluedFeatures = environment.openDatabase(null, KEY_MULTIVALUED_FEATURES, databaseConfig);
            isClosed = false;

            //NeoLogger.info("BerkeleyDB Environment info: {0}", environment.getConfig());
            NeoLogger.info("Databases: {0}", environment.getDatabaseNames());
            NeoLogger.info("Database containers open, size: {0}", this.containers.count());
            NeoLogger.info("Database instances open, size: {0}", this.instances.count());
            NeoLogger.info("Database features open, size: {0}", this.features.count());
            NeoLogger.info("Database multivaluedFeatures open, size: {0}", this.multivaluedFeatures.count());
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    public void close() {
        NeoLogger.info("Closing databases");

        this.save();
        try {
            isClosed = true;
            this.containers.close();
            this.instances.close();
            this.features.close();
            this.multivaluedFeatures.close();
            this.environment.close();
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    public Map<String, Object> getAll() {
        throw new UnsupportedOperationException();
    }

    public <E> E get(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }


    @Override
    public void save() {
        try {
            this.containers.sync();
            this.instances.sync();
            this.features.sync();
            this.multivaluedFeatures.sync();
            //env.sync();
        } catch (DatabaseException e) {
            NeoLogger.error(e);
            e.printStackTrace();
        }
    }

    /**
     * Retrieves container for a given object id.
     */
    public ContainerInfo containerFor(Id id) {
        ContainerInfo result = null;
        DatabaseEntry key = new DatabaseEntry(IdSerializer.serialize(id));
        DatabaseEntry value = new DatabaseEntry();

        try {
            if (containers.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                byte[] data = value.getData();
                result = ContainerInfoSerializer.deserialize(data);
            }
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return result;
    }

    /**
     * Stores containter information for an object id
     */
    public void storeContainer(Id id, ContainerInfo container) {
        DatabaseEntry key = new DatabaseEntry(IdSerializer.serialize(id));
        DatabaseEntry value = new DatabaseEntry(ContainerInfoSerializer.serialize(container));
        try {
            containers.put(null, key, value);
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    /**
     * Retrieves metaclass (EClass) for a given object id
     */
    public ClassInfo metaclassFor(Id id) {
        ClassInfo result = null;
        DatabaseEntry key = new DatabaseEntry(IdSerializer.serialize(id));
        DatabaseEntry value = new DatabaseEntry();

        try {
            if (instances.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                byte[] data = value.getData();
                result = ClassInfoSerializer.deserialize(data);
            }
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return result;
    }

    /**
     * Stores metaclass (EClass) information for an object id.
     */
    public void storeMetaclass(Id id, ClassInfo metaclass) {
        DatabaseEntry key = new DatabaseEntry(IdSerializer.serialize(id));
        DatabaseEntry value = new DatabaseEntry(ClassInfoSerializer.serialize(metaclass));
        try {
            instances.put(null, key, value);
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    /**
     * Store the value of a given feature.
     */
    public Object storeValue(FeatureKey fk, Object obj) {
        Object result = null;
        try {
            DatabaseEntry key = new DatabaseEntry(FKSerializer.serialize(fk));
            DatabaseEntry value = new DatabaseEntry(Serializer.serialize(obj));
            features.put(null, key, value);
            result = obj;
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return result;
    }

    /**
     * Retrieves the value of a given feature.
     */
    public Object valueOf(FeatureKey fk) {
        DatabaseEntry key = new DatabaseEntry(FKSerializer.serialize(fk));
        DatabaseEntry value = new DatabaseEntry();
        Object result = null;
        try {
            if (features.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                result = Serializer.deserialize(value.getData());
            }
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return result;
    }

    /**
     * Removes the value of a given feature. The feature becomes unset.
     */
    public Object removeFeature(FeatureKey fk) {
        DatabaseEntry key = new DatabaseEntry(FKSerializer.serialize(fk));
        DatabaseEntry value = new DatabaseEntry();
        Object result = null;
        try {
            features.get(null, key, value, LockMode.DEFAULT);
            features.delete(null, key);
            result = Serializer.deserialize(value.getData());
        } catch (DatabaseException e) {
            NeoLogger.error(e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Return true if the feature was set, false otherwise.
     */
    public boolean isFeatureSet(FeatureKey fk) {
        boolean result = false;
        DatabaseEntry key = new DatabaseEntry(FKSerializer.serialize(fk));
        DatabaseEntry value = new DatabaseEntry();
        try {
            result = (features.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS);
        } catch (DatabaseException e) {
            NeoLogger.error(e);
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Stores the single value of a given multivalued feature at the given index.
     */
    public Object storeValueAtIndex(MultivaluedFeatureKey fk, Object val) {
        try {
            DatabaseEntry key = new DatabaseEntry(FKSerializer.serialize(fk));
            DatabaseEntry value = new DatabaseEntry(Serializer.serialize(val));
            multivaluedFeatures.put(null, key, value);
            return val;
        } catch (DatabaseException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Retrieves the value of a given multivalued feature at a given index.
     */
    public Object valueAtIndex(MultivaluedFeatureKey fk) {
        DatabaseEntry key = new DatabaseEntry(FKSerializer.serialize(fk));
        DatabaseEntry value = new DatabaseEntry();
        Object result = null;
        try {
            if (multivaluedFeatures.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                result = Serializer.deserialize(value.getData());
            }
        } catch (DatabaseException e) {
            NeoLogger.error(e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Copies all the contents of this backend to the target one.
     */
    public void copyTo(BerkeleyDBPersistenceBackend target) {
        NeoLogger.info("Copying " + this.toString() + "to: " + target.toString());
        NeoLogger.info("This is closed: " + this.isClosed);
        NeoLogger.info("Target is closed: " + target.isClosed);

        try {
            this.copyDatabaseTo(instances, target.instances);
            this.copyDatabaseTo(features, target.features);
            this.copyDatabaseTo(containers, target.containers);
            this.copyDatabaseTo(multivaluedFeatures, target.multivaluedFeatures);
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    /**
     * Utility method to copy the contents from one database to another.
     *
     * @param from
     * @param to
     * @throws DatabaseException
     */
    private void copyDatabaseTo(Database from, Database to) throws DatabaseException {

        Cursor cursor = from.openCursor(null, null);
        DatabaseEntry key = new DatabaseEntry();
        DatabaseEntry value = new DatabaseEntry();

        while (cursor.getNext(key, value, LockMode.DEFAULT) ==
                OperationStatus.SUCCESS) {
            to.put(null, key, value);
        }
        cursor.close();
        to.sync();
    }
}
