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
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ClassInfoSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ContainerInfoSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.FeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ObjectSerializer;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import java.io.File;
import java.util.Map;

public class BerkeleyDbPersistenceBackend extends AbstractPersistenceBackend {

    /**
     * The literal description of this backend.
     */
    public static final String NAME = "berkeleydb";

    private static final String KEY_CONTAINER = "eContainer";
    private static final String KEY_INSTANCE_OF = "neoInstanceOf";
    private static final String KEY_FEATURES = "features";
    private static final String KEY_MULTIVALUED_FEATURES = "multivaluedFeatures";
    private boolean isClosed = true;

    private static final IdSerializer idSerializer = new IdSerializer();
    private static final FeatureKeySerializer fkSerializer = new FeatureKeySerializer();
    private static final ClassInfoSerializer classSerializer = new ClassInfoSerializer();
    private static final ContainerInfoSerializer containerSerializer = new ContainerInfoSerializer();
    private static final ObjectSerializer objSerializer = new ObjectSerializer();

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


    BerkeleyDbPersistenceBackend(File f, EnvironmentConfig ec) {
        file = f;
        environmentConfig = ec;
        databaseConfig = new DatabaseConfig();
        databaseConfig.setAllowCreate(true);
        databaseConfig.setSortedDuplicates(false);
        databaseConfig.setDeferredWrite(true);
    }

    public void open() {
        NeoLogger.debug("BerkeleyDBPersistentBackend::open()");
        //NeoLogger.debug(environmentConfig.getConfigParam(EnvironmentConfig.LOG_MEM_ONLY));

        if (!isClosed()) {
            NeoLogger.warn("This backend is already open");
        }
        try {
            environment = new Environment(file, environmentConfig);

            this.containers = environment.openDatabase(null, KEY_CONTAINER, databaseConfig);
            this.instances = environment.openDatabase(null, KEY_INSTANCE_OF, databaseConfig);
            this.features = environment.openDatabase(null, KEY_FEATURES, databaseConfig);
            this.multivaluedFeatures = environment.openDatabase(null, KEY_MULTIVALUED_FEATURES, databaseConfig);
            isClosed = false;

            NeoLogger.debug("BerkeleyDB Environment info: {0}", environment.getConfig());
            NeoLogger.debug("Databases: {0}", environment.getDatabaseNames());
            NeoLogger.debug("Database containers open, size: {0}", this.containers.count());
            NeoLogger.debug("Database instances open, size: {0}", this.instances.count());
            NeoLogger.debug("Database features open, size: {0}", this.features.count());
            NeoLogger.debug("Database multivaluedFeatures open, size: {0}", this.multivaluedFeatures.count());
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    public void close() {
        NeoLogger.debug("Closing databases");

        this.save();

        try {
            this.containers.close();
            this.instances.close();
            this.features.close();
            this.multivaluedFeatures.close();
            this.environment.close();
            isClosed = true;
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
//            env.sync();
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    /**
     * Retrieves container for a given object id.
     */
    public ContainerInfo containerFor(Id id) {
        ContainerInfo containerInfo = null;
        DatabaseEntry key = new DatabaseEntry(idSerializer.serialize(id));
        DatabaseEntry value = new DatabaseEntry();
        try {
            if (containers.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                byte[] data = value.getData();
                containerInfo = containerSerializer.deserialize(data);
            }
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return containerInfo;
    }

    /**
     * Stores containter information for an object id
     */
    public void storeContainer(Id id, ContainerInfo container) {
        DatabaseEntry key = new DatabaseEntry(idSerializer.serialize(id));
        DatabaseEntry value = new DatabaseEntry(containerSerializer.serialize(container));
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
        ClassInfo classInfo = null;
        DatabaseEntry key = new DatabaseEntry(idSerializer.serialize(id));
        DatabaseEntry value = new DatabaseEntry();
        try {
            if (instances.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                byte[] data = value.getData();
                classInfo = classSerializer.deserialize(data);
            }
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return classInfo;
    }

    /**
     * Stores metaclass (EClass) information for an object id.
     */
    public void storeMetaclass(Id id, ClassInfo metaclass) {
        DatabaseEntry key = new DatabaseEntry(idSerializer.serialize(id));
        DatabaseEntry value = new DatabaseEntry(classSerializer.serialize(metaclass));
        try {
            instances.put(null, key, value);
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    /**
     * Store the value of a given feature.
     */
    public Object storeValue(FeatureKey featureKey, Object obj) {
        Object old = null;
        try {
            DatabaseEntry key = new DatabaseEntry(fkSerializer.serialize(featureKey));
            DatabaseEntry value = new DatabaseEntry(objSerializer.serialize(obj));
            features.put(null, key, value);
            old = obj;
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return old;
    }

    /**
     * Retrieves the value of a given feature.
     */
    public Object valueOf(FeatureKey featureKey) {
        DatabaseEntry key = new DatabaseEntry(fkSerializer.serialize(featureKey));
        DatabaseEntry value = new DatabaseEntry();
        Object old = null;
        try {
            if (features.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                old = objSerializer.deserialize(value.getData());
            }
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return old;
    }

    /**
     * Removes the value of a given feature. The feature becomes unset.
     */
    public Object removeFeature(FeatureKey featureKey) {
        DatabaseEntry key = new DatabaseEntry(fkSerializer.serialize(featureKey));
        DatabaseEntry value = new DatabaseEntry();
        Object old = null;
        try {
            features.get(null, key, value, LockMode.DEFAULT);
            features.delete(null, key);
            old = objSerializer.deserialize(value.getData());
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return old;
    }

    /**
     * Return true if the feature was set, false otherwise.
     */
    public boolean isFeatureSet(FeatureKey featureKey) {
        boolean isSet = false;
        DatabaseEntry key = new DatabaseEntry(fkSerializer.serialize(featureKey));
        DatabaseEntry value = new DatabaseEntry();
        try {
            isSet = (features.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS);
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return isSet;
    }

    /**
     * Stores the single value of a given multivalued feature at the given index.
     */
    public Object storeValueAtIndex(MultivaluedFeatureKey featureKey, Object obj) {
        try {
            DatabaseEntry key = new DatabaseEntry(fkSerializer.serialize(featureKey));
            DatabaseEntry value = new DatabaseEntry(objSerializer.serialize(obj));
            multivaluedFeatures.put(null, key, value);
            return obj;
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return null;
    }

    /**
     * Retrieves the value of a given multivalued feature at a given index.
     */
    public Object valueAtIndex(MultivaluedFeatureKey featureKey) {
        DatabaseEntry key = new DatabaseEntry(fkSerializer.serialize(featureKey));
        DatabaseEntry value = new DatabaseEntry();
        Object old = null;
        try {
            if (multivaluedFeatures.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                old = objSerializer.deserialize(value.getData());
            }
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return old;
    }

    /**
     * Copies all the contents of this backend to the target one.
     */
    public void copyTo(BerkeleyDbPersistenceBackend target) {
        NeoLogger.debug("Copying " + this.toString() + "to: " + target.toString());
        NeoLogger.debug("This is closed: " + this.isClosed);
        NeoLogger.debug("Target is closed: " + target.isClosed);

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
     */
    private void copyDatabaseTo(Database from, Database to) throws DatabaseException {
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
