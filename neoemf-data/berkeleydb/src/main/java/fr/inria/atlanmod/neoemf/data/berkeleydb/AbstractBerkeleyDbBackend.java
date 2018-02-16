/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.serializer.BeanSerializerFactory;
import fr.inria.atlanmod.neoemf.data.mapping.AllReferenceAs;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link BerkeleyDbBackend} that provides overall behavior for the management of a BerkeleyDB database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractBerkeleyDbBackend extends AbstractBackend implements BerkeleyDbBackend, AllReferenceAs<Long> {

    /**
     * The {@link BeanSerializerFactory} to use for creating the {@link Serializer} instances.
     */
    @Nonnull
    protected static final BeanSerializerFactory SERIALIZER_FACTORY = BeanSerializerFactory.getInstance();

    /**
     * The BerkeleyDB environment.
     */
    @Nonnull
    protected final Environment environment;

    /**
     * A persistent map that stores the container of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private final Database containers;

    /**
     * A persistent map that stores the meta-class for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private final Database instances;

    /**
     * A persistent map that stores single-feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link SingleFeatureBean}. Depending on the mapping used, it can also contain
     * many-feature values grouped in collections.
     */
    @Nonnull
    private final Database features;

    /**
     * Creates a new {@code AbstractBerkeleyDbBackend} with the configuration of the databases.
     *
     * @param environment    the database environment used to create and manage {@link com.sleepycat.je.Database}s
     * @param databaseConfig the configuration of the created {@link com.sleepycat.je.Database}s
     */
    protected AbstractBerkeleyDbBackend(Environment environment, DatabaseConfig databaseConfig) {
        checkNotNull(environment, "environment");
        checkNotNull(databaseConfig, "databaseConfig");

        this.environment = environment;

        this.containers = environment.openDatabase(null, "containers", databaseConfig);
        this.instances = environment.openDatabase(null, "instances", databaseConfig);
        this.features = environment.openDatabase(null, "features/single", databaseConfig);
    }

    @Override
    protected void internalClose() {
        containers.close();
        instances.close();
        features.close();

        environment.close();
    }

    @Override
    public void internalSave() {
        // Do nothing: data are automatically saved
    }

    @Override
    protected void internalCopyTo(DataMapper target) {
        AbstractBerkeleyDbBackend to = AbstractBerkeleyDbBackend.class.cast(target);

        copy(containers, to.containers);
        copy(instances, to.instances);
        copy(features, to.features);
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        return get(containers, id, SERIALIZER_FACTORY.forId(), SERIALIZER_FACTORY.forSingleFeature());
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        put(containers, id, container, SERIALIZER_FACTORY.forId(), SERIALIZER_FACTORY.forSingleFeature());
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        delete(containers, id, SERIALIZER_FACTORY.forId());
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        return get(instances, id, SERIALIZER_FACTORY.forId(), SERIALIZER_FACTORY.forClass());
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        return putIfAbsent(instances, id, metaClass, SERIALIZER_FACTORY.forId(), SERIALIZER_FACTORY.forClass());
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        try (Cursor cursor = instances.openCursor(null, null)) {
            DatabaseEntry dbKey = new DatabaseEntry();
            DatabaseEntry dbValue = new DatabaseEntry();

            Set<Id> instancesOf = new HashSet<>();

            while (cursor.getNext(dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                if (metaClasses.contains(SERIALIZER_FACTORY.forClass().deserialize(dbValue.getData()))) {
                    instancesOf.add(SERIALIZER_FACTORY.forId().deserialize(dbKey.getData()));
                }
            }

            return instancesOf;
        }
        catch (IOException e) {
            handleException(e);
            return Collections.emptySet();
        }
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        return get(features, feature, SERIALIZER_FACTORY.forSingleFeature(), SERIALIZER_FACTORY.forAny());
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        Optional<V> previousValue = valueOf(feature);
        put(features, feature, value, SERIALIZER_FACTORY.forSingleFeature(), SERIALIZER_FACTORY.forAny());
        return previousValue;
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        delete(features, feature, SERIALIZER_FACTORY.forSingleFeature());
    }

    @Nonnull
    @Override
    public Converter<Id, Long> referenceConverter() {
        return IdConverters.withLong();
    }

    /**
     * Retrieves the value of the {@code key} from the {@code database}.
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
            handleException(e);
            return Optional.empty();
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
            handleException(e);
        }
    }

    /**
     * Saves a {@code value} identified by the {@code key} in the database, only if the {@code key} is not defined.
     *
     * @param database        the database where to save the value
     * @param key             the key of the element to save
     * @param value           the value to save
     * @param keySerializer   the serializer to serialize the {@code key}
     * @param valueSerializer the serializer to serialize the {@code value}
     * @param <K>             the type of the key
     * @param <V>             the type of the value
     *
     * @return {@code true} if the {@code value} has been saved
     */
    protected <K, V> boolean putIfAbsent(Database database, K key, V value, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        try {
            DatabaseEntry dbKey = new DatabaseEntry(keySerializer.serialize(key));
            DatabaseEntry dbValue = new DatabaseEntry(valueSerializer.serialize(value));

            return database.putNoOverwrite(null, dbKey, dbValue) != OperationStatus.KEYEXIST;
        }
        catch (IOException e) {
            handleException(e);
            return false;
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
            handleException(e);
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

    /**
     * TODO
     *
     * @param e the exception to handle
     */
    private void handleException(IOException e) {
        throw Throwables.wrap(e, RuntimeException.class);
    }
}
