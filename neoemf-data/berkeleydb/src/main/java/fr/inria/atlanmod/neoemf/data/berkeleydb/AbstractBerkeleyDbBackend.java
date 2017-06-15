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
import com.sleepycat.je.Environment;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;
import fr.inria.atlanmod.neoemf.io.serializer.JavaSerializerFactory;
import fr.inria.atlanmod.neoemf.io.serializer.Serializer;
import fr.inria.atlanmod.neoemf.io.serializer.SerializerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * An abstract {@link BerkeleyDbBackend} that provides overall behavior for the management of a BerkeleyDB database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractBerkeleyDbBackend extends AbstractBackend implements BerkeleyDbBackend {

    /**
     * The {@link SerializerFactory} to use for creating the {@link Serializer} instances.
     */
    protected final SerializerFactory serializerFactory;

    /**
     * The databases environment.
     */
    @Nonnull
    protected final Environment environment;

    /**
     * A persistent map that stores the container of {@link PersistentEObject}, identified by the object {@link Id}.
     */
    @Nonnull
    private final Database containers;

    /**
     * A persistent map that stores the metaclass for {@link PersistentEObject}, identified by the object {@link Id}.
     */
    @Nonnull
    private final Database instances;

    /**
     * A persistent map that stores structural features values for {@link PersistentEObject}, identified by the
     * associated {@link SingleFeatureKey}.
     */
    @Nonnull
    private final Database features;

    /**
     * Creates a new {@code AbstractBerkeleyDbBackend} with the configuration of the databases.
     *
     * @param environment    the database environment
     * @param databaseConfig the database configuration
     */
    protected AbstractBerkeleyDbBackend(Environment environment, DatabaseConfig databaseConfig) {
        checkNotNull(environment);
        checkNotNull(databaseConfig);

        this.serializerFactory = JavaSerializerFactory.getInstance();

        this.environment = environment;

        this.containers = environment.openDatabase(null, "containers", databaseConfig);
        this.instances = environment.openDatabase(null, "instances", databaseConfig);
        this.features = environment.openDatabase(null, "features/single", databaseConfig);
    }

    @Override
    public void save() {
//        activeDatabases().stream()
//                .filter(db -> db.getConfig().getDeferredWrite())
//                .forEach(Database::sync);
    }

    @Override
    public void copyTo(DataMapper target) {
        checkArgument(AbstractBerkeleyDbBackend.class.isInstance(target));
        AbstractBerkeleyDbBackend to = AbstractBerkeleyDbBackend.class.cast(target);

        copy(instances, to.instances);
        copy(features, to.features);
        copy(containers, to.containers);
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    protected void safeClose() {
        activeDatabases().forEach(Database::close);
        environment.close();
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        checkNotNull(id);

        return get(containers, id, serializerFactory.forId(), serializerFactory.forContainer());
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        checkNotNull(id);
        checkNotNull(container);

        put(containers, id, container, serializerFactory.forId(), serializerFactory.forContainer());
    }

    @Override
    public void unsetContainer(Id id) {
        checkNotNull(id);

        delete(containers, id, serializerFactory.forId());
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        return get(instances, id, serializerFactory.forId(), serializerFactory.forClass());
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        checkNotNull(id);
        checkNotNull(metaclass);

        put(instances, id, metaclass, serializerFactory.forId(), serializerFactory.forClass());
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        checkNotNull(key);

        return get(features, key, serializerFactory.forSingleFeatureKey(), serializerFactory.forAny());
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Optional<V> previousValue = valueOf(key);
        put(features, key, value, serializerFactory.forSingleFeatureKey(), serializerFactory.forAny());
        return previousValue;
    }

    @Override
    public void unsetValue(SingleFeatureKey key) {
        checkNotNull(key);

        delete(features, key, serializerFactory.forSingleFeatureKey());
    }

    /**
     * Returns all actives databases.
     *
     * @return a list of {@link Database}
     *
     * @see #close()
     * @see #save()
     */
    @Nonnull
    protected List<Database> activeDatabases() {
        List<Database> databases = new ArrayList<>();
        databases.add(containers);
        databases.add(instances);
        databases.add(features);
        return databases;
    }

    /**
     * Retrieves a value from the {@code database} according to the given {@code key}.
     *
     * @param database        the database where to looking for
     * @param key             the key of the element to retrieve
     * @param keySerializer   the serializer to serialize the {@code key}
     * @param valueSerializer the serializer to deserialize the read value
     * @param <K>             the type of the key
     * @param <V>             the type of the value
     *
     * @return on {@link Optional} containing the element, or an empty {@link Optional} if the element has not been
     * found
     */
    @Nonnull
    protected <K, V> Optional<V> get(Database database, K key, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        try {
            DatabaseEntry dbKey = new DatabaseEntry(keySerializer.serialize(key));
            DatabaseEntry dbValue = new DatabaseEntry();

            Optional<V> value = Optional.empty();

            if (database.get(null, dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                value = Optional.of(valueSerializer.deserialize(dbValue.getData()));
            }

            return value;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves a {@code value} identified by the {@code key} in the {@code database}.
     *
     * @param database        the database where to save the value
     * @param key             the key of the element to save
     * @param value           the value to save
     * @param keySerializer   the serializer to serialize the {@code key}
     * @param valueSerializer the serializer to serialize the {@code value}
     * @param <K>             the type of the key
     * @param <V>             the type of the value
     */
    protected <K, V> void put(Database database, K key, V value, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        try {
            DatabaseEntry dbKey = new DatabaseEntry(keySerializer.serialize(key));
            DatabaseEntry dbValue = new DatabaseEntry(valueSerializer.serialize(value));

            database.put(null, dbKey, dbValue);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes a value from the {@code database} according to its {@code key}.
     *
     * @param database      the database where to remove the value
     * @param key           the key of the element to remove
     * @param keySerializer the serializer to serialize the {@code key}
     * @param <K>           the type of the key
     */
    protected <K> void delete(Database database, K key, Serializer<K> keySerializer) {
        try {
            DatabaseEntry dbKey = new DatabaseEntry(keySerializer.serialize(key));

            database.delete(null, dbKey);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Utility method to copy the contents from one database to another.
     *
     * @param from the database to copy
     * @param to   the database to copy the database contents to
     */
    protected void copy(Database from, Database to) {
        try (Cursor cursor = from.openCursor(null, null)) {
            DatabaseEntry dbKey = new DatabaseEntry();
            DatabaseEntry dbValue = new DatabaseEntry();
            while (cursor.getNext(dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                to.put(null, dbKey, dbValue);
            }
        }
        to.sync();
    }
}
