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
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.CISerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ContainerInfoSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.FKSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.Serializer;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import java.io.*;
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
    private DatabaseConfig dbconf;



    BerkeleyDBPersistenceBackend(File file) {
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
            //env.close();
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
        throw new UnsupportedOperationException();
    }



    @Override
    public void save() {
        try {
            this.containers.sync();
            this.instances.sync();
            this.features.sync();
            this.multivaluedFeatures.sync();
            env.sync();
        } catch (DatabaseException e) {
            NeoLogger.error(e);
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
            if (instances.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
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
            instances.put(null, key, value);
        } catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    /**
     * Retrieves metaclass (EClass) for a given object id
     */
    public ClassInfo metaclassFor(Id id) {
        ClassInfo result = null;
        DatabaseEntry key = new DatabaseEntry(id.toString().getBytes());
        DatabaseEntry value = new DatabaseEntry();

        try {
            if (instances.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                byte[] data = value.getData();
                result = CISerializer.deserialize(data);
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
        DatabaseEntry value = new DatabaseEntry(CISerializer.serialize(metaclass));
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
        try {
            DatabaseEntry key = new DatabaseEntry(FKSerializer.serialize(fk));
            DatabaseEntry value = new DatabaseEntry(Serializer.serialize(obj));
            features.put(null, key, value);
            return obj;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
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
            result = (features.get(null,key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS);
        } catch (DatabaseException e) {
            NeoLogger.error(e);
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
        }
        return result;
    }

    /**
     * Copies all the contents of this backend to the target one.
     */
    public void copyTo(BerkeleyDBPersistenceBackend target) {
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
    }
}
