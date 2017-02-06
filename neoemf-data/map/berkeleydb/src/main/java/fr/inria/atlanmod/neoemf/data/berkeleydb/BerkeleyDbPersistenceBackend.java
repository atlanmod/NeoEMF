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
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ClassInfoSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ContainerInfoSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.FeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.data.berkeleydb.serializer.ObjectSerializer;
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EClass;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        // Do nothing
    }

    @Override
    public boolean has(Id id) {
        return false;
    }

    @Override
    public Optional<ContainerValue> containerOf(Id id) {
        Optional<ContainerValue> container = Optional.empty();

        try {
            DatabaseEntry dbKey = new DatabaseEntry(new IdSerializer().serialize(id));
            DatabaseEntry dbValue = new DatabaseEntry();

            if (containers.get(null, dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                container = Optional.of(new ContainerInfoSerializer().deserialize(dbValue.getData()));
            }
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }

        return container;
    }

    @Override
    public void containerFor(Id id, ContainerValue container) {
        try {
            DatabaseEntry dbKey = new DatabaseEntry(new IdSerializer().serialize(id));
            DatabaseEntry dbValue = new DatabaseEntry(new ContainerInfoSerializer().serialize(container));

            containers.put(null, dbKey, dbValue);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @Override
    public Optional<MetaclassValue> metaclassOf(Id id) {
        Optional<MetaclassValue> metaclass = Optional.empty();

        try {
            DatabaseEntry dbKey = new DatabaseEntry(new IdSerializer().serialize(id));
            DatabaseEntry dbValue = new DatabaseEntry();

            if (instances.get(null, dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                metaclass = Optional.of(new ClassInfoSerializer().deserialize(dbValue.getData()));
            }
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }

        return metaclass;
    }

    @Override
    public void metaclassFor(Id id, MetaclassValue metaclass) {
        try {
            DatabaseEntry dbKey = new DatabaseEntry(new IdSerializer().serialize(id));
            DatabaseEntry dbValue = new DatabaseEntry(new ClassInfoSerializer().serialize(metaclass));

            instances.put(null, dbKey, dbValue);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @Override
    public Optional<Object> valueOf(FeatureKey key) {
        Optional<Object> previousValue = Optional.empty();

        try {
            DatabaseEntry dbKey = new DatabaseEntry(new FeatureKeySerializer().serialize(key));
            DatabaseEntry dbValue = new DatabaseEntry();

            if (features.get(null, dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                previousValue = Optional.of(new ObjectSerializer().deserialize(dbValue.getData()));
            }
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }

        return previousValue;
    }

    @Override
    public Optional<Object> valueOf(MultivaluedFeatureKey key) {
        Optional<Object> previousValue = Optional.empty();

        try {
            DatabaseEntry dbKey = new DatabaseEntry(new FeatureKeySerializer().serialize(key));
            DatabaseEntry dbValue = new DatabaseEntry();

            if (multivaluedFeatures.get(null, dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                previousValue = Optional.of(new ObjectSerializer().deserialize(dbValue.getData()));
            }
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }

        return previousValue;
    }

    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        return valueOf(key)
                .map(v -> Optional.of(StringId.from(v)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        return valueOf(key)
                .map(v -> Optional.of(StringId.from(v)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Object> valueFor(FeatureKey key, Object obj) {
        Optional<Object> previousValue = valueOf(key);

        try {
            DatabaseEntry dbKey = new DatabaseEntry(new FeatureKeySerializer().serialize(key));
            DatabaseEntry dbValue = new DatabaseEntry(new ObjectSerializer().serialize(obj));

            features.put(null, dbKey, dbValue);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }

        return previousValue;
    }

    @Override
    public Optional<Object> valueFor(MultivaluedFeatureKey key, Object obj) {
        Optional<Object> previousValue = valueOf(key);

        try {
            DatabaseEntry dbKey = new DatabaseEntry(new FeatureKeySerializer().serialize(key));
            DatabaseEntry dbValue = new DatabaseEntry(new ObjectSerializer().serialize(obj));

            multivaluedFeatures.put(null, dbKey, dbValue);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }

        return previousValue;
    }

    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        return valueFor(key, id)
                .map(v -> Optional.of(StringId.from(v)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        return valueFor(key, id)
                .map(v -> Optional.of(StringId.from(v)))
                .orElse(Optional.empty());
    }

    @Override
    public void unsetValue(FeatureKey key) {
        try {
            DatabaseEntry dbKey = new DatabaseEntry(new FeatureKeySerializer().serialize(key));

            features.delete(null, dbKey);
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }
    }

    @Override
    public void unsetAllValues(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public void unsetReference(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public void unsetAllReferences(FeatureKey key) {
        unsetReference(key);
    }

    @Override
    public boolean hasValue(FeatureKey key) {
        boolean has = false;

        try {
            DatabaseEntry dbKey = new DatabaseEntry(new FeatureKeySerializer().serialize(key));
            DatabaseEntry dbValue = new DatabaseEntry();

            has = features.get(null, dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS;
        }
        catch (DatabaseException e) {
            NeoLogger.error(e);
        }

        return has;
    }

    @Override
    public boolean hasAnyValue(FeatureKey key) {
        return hasValue(key);
    }

    @Override
    public boolean hasReference(FeatureKey key) {
        return hasValue(key);
    }

    @Override
    public boolean hasAnyReference(FeatureKey key) {
        return hasReference(key);
    }

    @Override
    public void addValue(MultivaluedFeatureKey key, Object value) {
        int size = sizeOf(key.withoutPosition()).orElse(0);

        // TODO Replace by Stream
        for (int i = size - 1; i >= key.position(); i--) {
            valueFor(key.withPosition(i + 1), valueOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size + 1);

        valueFor(key, value);
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        int size = sizeOf(key.withoutPosition()).orElse(0);

        // TODO Replace by Stream
        for (int i = size - 1; i >= key.position(); i--) {
            referenceFor(key.withPosition(i + 1), referenceOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size + 1);

        referenceFor(key, id);
    }

    @Override
    public Optional<Object> removeValue(MultivaluedFeatureKey key) {
        Optional<Object> previousValue = valueOf(key);

        int size = sizeOf(key.withoutPosition()).orElse(0);

        // Update indexes (element to remove is overwritten)
        // TODO Replace by Stream
        for (int i = key.position() + 1; i < size; i++) {
            valueFor(key.withPosition(i - 1), valueOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size - 1);

        return previousValue;
    }

    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        Optional<Id> previousId = referenceOf(key);

        int size = sizeOf(key.withoutPosition()).orElse(0);

        // Update indexes (element to remove is overwritten)
        // TODO Replace by Stream
        for (int i = key.position() + 1; i < size; i++) {
            referenceFor(key.withPosition(i - 1), referenceOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size - 1);

        return previousId;
    }

    @Override
    public void cleanValues(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public void cleanReferences(FeatureKey key) {
        unsetReference(key);
    }

    @Override
    public boolean containsValue(FeatureKey key, Object value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .anyMatch(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false));
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .anyMatch(i -> referenceOf(key.withPosition(i)).map(v -> Objects.equals(v, id)).orElse(false));
    }

    @Override
    public OptionalInt indexOfValue(FeatureKey key, Object value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .min();
    }

    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> referenceOf(key.withPosition(i)).map(v -> Objects.equals(v, id)).orElse(false))
                .min();
    }

    @Override
    public OptionalInt lastIndexOfValue(FeatureKey key, Object value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .max();
    }

    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> referenceOf(key.withPosition(i)).map(v -> Objects.equals(v, id)).orElse(false))
                .max();
    }

    @Override
    public Iterable<Object> valuesAsList(FeatureKey key) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .mapToObj(i -> valueOf(key.withPosition(i)).orElse(null))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .mapToObj(i -> referenceOf(key.withPosition(i)).orElse(null))
                .collect(Collectors.toList());
    }

    @Override
    public OptionalInt sizeOf(FeatureKey key) {
        return valueOf(key)
                .map(v -> OptionalInt.of((int) v))
                .orElse(OptionalInt.empty());
    }

    protected void sizeFor(FeatureKey key, int size) {
        valueFor(key, size);
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
