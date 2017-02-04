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

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.annotations.VisibleForTesting;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ClassInfoSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ContainerInfoSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.FeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ObjectSerializer;
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EClass;

import java.io.File;
import java.util.Map;

/**
 * ???
 */
@Experimental
public class BerkeleyDbPersistenceBackend extends AbstractPersistenceBackend implements MapBackend {

    /**
     * The literal description of this back-end.
     */
    public static final String NAME = "berkeleydb";

    /**
     * ???
     */
    private static final String KEY_CONTAINER = "eContainer";

    /**
     * ???
     */
    private static final String KEY_INSTANCE_OF = "neoInstanceOf";

    /**
     * ???
     */
    private static final String KEY_FEATURES = "features";

    /**
     * ???
     */
    private static final String KEY_MULTIVALUED_FEATURES = "multivaluedFeatures";

    /**
     * ???
     */
    private final DatabaseConfig databaseConfig;

    /**
     * ???
     */
    private final EnvironmentConfig environmentConfig;

    /**
     * ???
     */
    private final File file;

    /**
     * ???
     */
    private boolean isClosed = true;

    /**
     * A persistent map that stores the container of {@link PersistentEObject}, identified by the object {@link Id}.
     */
    private Database containers;

    /**
     * A persistent map that stores the {@link EClass} for {@link PersistentEObject}, identified by the object {@link
     * Id}.
     */
    private Database instances;

    /**
     * A persistent map that stores structural features values for {@link PersistentEObject}, identified by the
     * associated {@link FeatureKey}.
     */
    private Database features;

    /**
     * A persistent map that store the values of multi-valued features for {@link PersistentEObject}, identified by the
     * associated {@link MultivaluedFeatureKey}.
     */
    private Database multivaluedFeatures;

    /**
     * ???
     */
    private Environment environment;

    /**
     * Constructs a new {@code BerkeleyDbPersistenceBackend} on the given {@code file} with the given
     * {@code environmentConfig}.
     *
     * @param file              ???
     * @param environmentConfig ???
     *
     * @note This constructor is protected. To create a new {@code BerkeleyDbPersistenceBackend} use {@link
     * BerkeleyDbPersistenceBackendFactory#createPersistentBackend(java.io.File, Map)}.
     */
    protected BerkeleyDbPersistenceBackend(File file, EnvironmentConfig environmentConfig) {
        this.file = file;
        this.environmentConfig = environmentConfig;
        this.databaseConfig = new DatabaseConfig()
                .setAllowCreate(true)
                .setSortedDuplicates(false)
                .setDeferredWrite(true);
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

            this.containers = environment.openDatabase(null, KEY_CONTAINER, databaseConfig);
            this.instances = environment.openDatabase(null, KEY_INSTANCE_OF, databaseConfig);
            this.features = environment.openDatabase(null, KEY_FEATURES, databaseConfig);
            this.multivaluedFeatures = environment.openDatabase(null, KEY_MULTIVALUED_FEATURES, databaseConfig);
            isClosed = false;
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @VisibleForTesting
    @Override
    public Map<String, Object> getAll() {
        throw new UnsupportedOperationException();
    }

    @VisibleForTesting
    @Override
    public <E> E get(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save() {
        try {
            this.containers.sync();
            this.instances.sync();
            this.features.sync();
            this.multivaluedFeatures.sync();
//            env.sync();
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @Override
    public void close() {
        this.save();

        try {
            this.containers.close();
            this.instances.close();
            this.features.close();
            this.multivaluedFeatures.close();
            this.environment.close();
            isClosed = true;
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean has(Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ContainerValue containerFor(Id id) {
        ContainerValue container = null;
        DatabaseEntry key = new DatabaseEntry(new IdSerializer().serialize(id));
        DatabaseEntry value = new DatabaseEntry();
        try {
            if (containers.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                byte[] data = value.getData();
                container = new ContainerInfoSerializer().deserialize(data);
            }
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return container;
    }

    @Override
    public void storeContainer(Id id, ContainerValue container) {
        DatabaseEntry key = new DatabaseEntry(new IdSerializer().serialize(id));
        DatabaseEntry value = new DatabaseEntry(new ContainerInfoSerializer().serialize(container));
        try {
            containers.put(null, key, value);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @Override
    public MetaclassValue metaclassFor(Id id) {
        MetaclassValue metaclass = null;
        DatabaseEntry key = new DatabaseEntry(new IdSerializer().serialize(id));
        DatabaseEntry value = new DatabaseEntry();
        try {
            if (instances.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                byte[] data = value.getData();
                metaclass = new ClassInfoSerializer().deserialize(data);
            }
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return metaclass;
    }

    @Override
    public void storeMetaclass(Id id, MetaclassValue metaclass) {
        DatabaseEntry key = new DatabaseEntry(new IdSerializer().serialize(id));
        DatabaseEntry value = new DatabaseEntry(new ClassInfoSerializer().serialize(metaclass));
        try {
            instances.put(null, key, value);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @Override
    public Object getValue(FeatureKey featureKey) {
        DatabaseEntry key = new DatabaseEntry(new FeatureKeySerializer().serialize(featureKey));
        DatabaseEntry value = new DatabaseEntry();
        Object old = null;
        try {
            if (features.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                old = new ObjectSerializer().deserialize(value.getData());
            }
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return old;
    }

    @Override
    public Object setValue(FeatureKey featureKey, Object obj) {
        Object old = null;
        try {
            DatabaseEntry key = new DatabaseEntry(new FeatureKeySerializer().serialize(featureKey));
            DatabaseEntry value = new DatabaseEntry(new ObjectSerializer().serialize(obj));
            features.put(null, key, value);
            old = obj;
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return old;
    }

    @Override
    public void unsetValue(FeatureKey featureKey) {
        DatabaseEntry key = new DatabaseEntry(new FeatureKeySerializer().serialize(featureKey));
        DatabaseEntry value = new DatabaseEntry();
        Object old = null;
        try {
            if (features.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                features.delete(null, key);
            }
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @Override
    public boolean hasValue(FeatureKey featureKey) {
        boolean isSet = false;
        DatabaseEntry key = new DatabaseEntry(new FeatureKeySerializer().serialize(featureKey));
        DatabaseEntry value = new DatabaseEntry();
        try {
            isSet = (features.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return isSet;
    }

    @Override
    public void addValue(MultivaluedFeatureKey key, Object value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object removeValue(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cleanValue(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Iterable<Object> valueAsList(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean containsValue(FeatureKey key, Object value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int indexOfValue(FeatureKey key, Object value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int lastIndexOfValue(FeatureKey key, Object value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Id getReference(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Id setReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void unsetReference(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Id removeReference(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cleanReference(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Iterable<Id> referenceAsList(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int indexOfReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int lastIndexOfReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object getValueAtIndex(MultivaluedFeatureKey featureKey) {
        DatabaseEntry key = new DatabaseEntry(new FeatureKeySerializer().serialize(featureKey));
        DatabaseEntry value = new DatabaseEntry();
        Object old = null;
        try {
            if (multivaluedFeatures.get(null, key, value, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                old = new ObjectSerializer().deserialize(value.getData());
            }
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return old;
    }

    @Override
    public Object setValueAtIndex(MultivaluedFeatureKey featureKey, Object obj) {
        try {
            DatabaseEntry key = new DatabaseEntry(new FeatureKeySerializer().serialize(featureKey));
            DatabaseEntry value = new DatabaseEntry(new ObjectSerializer().serialize(obj));
            multivaluedFeatures.put(null, key, value);
            return obj;
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
        return null;
    }

    @Override
    public void unsetValueAtIndex(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public boolean hasValueAtIndex(FeatureKey key) {
        return hasValue(key);
    }

    @Override
    public Iterable<Object> valueAtIndexAsList(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Id getReferenceAtIndex(MultivaluedFeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Id setReferenceAtIndex(MultivaluedFeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void unsetReferenceAtIndex(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean hasReferenceAtIndex(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Iterable<Id> referenceAtIndexAsList(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int sizeOf(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Copies all the contents of this {@code PersistenceBackend} to the {@code target} one.
     *
     * @param target the {@code PersistenceBackend} to copy the database contents to
     */
    public void copyTo(BerkeleyDbPersistenceBackend target) {
        try {
            this.copyDatabaseTo(instances, target.instances);
            this.copyDatabaseTo(features, target.features);
            this.copyDatabaseTo(containers, target.containers);
            this.copyDatabaseTo(multivaluedFeatures, target.multivaluedFeatures);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    /**
     * Utility method to copy the contents from one database to another.
     *
     * @param from the database to copy
     * @param to   the database to copy the database contents to
     */
    private void copyDatabaseTo(Database from, Database to) {
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
