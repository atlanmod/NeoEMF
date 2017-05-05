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
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.io.serializer.Serializers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * An abstract {@link BerkeleyDbBackend} that provides overall behavior for the management of a BerkeleyDB database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractBerkeleyDbBackend implements BerkeleyDbBackend {

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
     * associated {@link FeatureKey}.
     */
    @Nonnull
    private final Database features;

    /**
     * Whether the databases are closed.
     */
    private boolean isClosed;

    /**
     * Creates a new {@code AbstractBerkeleyDbBackend} with the configuration of the databases.
     *
     * @param environment    the database environment
     * @param databaseConfig the database configuration
     */
    protected AbstractBerkeleyDbBackend(Environment environment, DatabaseConfig databaseConfig) {
        checkNotNull(environment);
        checkNotNull(databaseConfig);

        this.environment = environment;

        containers = environment.openDatabase(null, "eContainer", databaseConfig);
        instances = environment.openDatabase(null, "neoInstanceOf", databaseConfig);
        features = environment.openDatabase(null, "features", databaseConfig);

        isClosed = false;
    }

    @Override
    public void save() {
//        allDatabases().stream()
//                .filter(db -> db.getConfig().getDeferredWrite())
//                .forEach(Database::sync);
    }

    @Override
    public void close() {
        if (isClosed) {
            return;
        }

        isClosed = true;

        try {
            allDatabases().forEach(Database::close);
            environment.close();
        }
        catch (Exception ignored) {
        }
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

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        checkNotNull(id);

        return get(containers, id);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        checkNotNull(id);
        checkNotNull(container);

        put(containers, id, container);
    }

    @Override
    public void unsetContainer(Id id) {
        checkNotNull(id);

        delete(containers, id);
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        return get(instances, id);
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        checkNotNull(id);
        checkNotNull(metaclass);

        put(instances, id, metaclass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        checkNotNull(key);

        return get(features, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Optional<V> previousValue = valueOf(key);
        put(features, key, value);
        return previousValue;
    }

    @Override
    public void unsetValue(FeatureKey key) {
        checkNotNull(key);

        delete(features, key);
    }

    /**
     * Returns all loaded databases.
     *
     * @return a list of {@link Database}
     *
     * @see #close()
     * @see #save()
     */
    @Nonnull
    protected List<Database> allDatabases() {
        List<Database> databases = new ArrayList<>();
        databases.add(containers);
        databases.add(instances);
        databases.add(features);
        return databases;
    }

    /**
     * Retrieves a value from the {@code database} according to the given {@code key}.
     *
     * @param database the database where to looking for
     * @param key      the key of the element to retrieve
     * @param <K>      the type of the key
     * @param <V>      the type of the value
     *
     * @return on {@link Optional} containing the element, or an empty {@link Optional} if the element has not been
     * found
     */
    @Nonnull
    protected <K, V> Optional<V> get(Database database, K key) {
        DatabaseEntry dbKey = new DatabaseEntry(Serializers.<K>forGenerics().serialize(key));
        DatabaseEntry dbValue = new DatabaseEntry();

        Optional<V> value;
        if (database.get(null, dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
            value = Optional.of(Serializers.<V>forGenerics().deserialize(dbValue.getData()));
        }
        else {
            value = Optional.empty();
        }
        return value;
    }

    /**
     * Saves a {@code value} identified by the {@code key} in the {@code database}.
     *
     * @param database the database where to save the value
     * @param key      the key of the element to save
     * @param value    the value to save
     * @param <K>      the type of the key
     * @param <V>      the type of the value
     */
    protected <K, V> void put(Database database, K key, V value) {
        DatabaseEntry dbKey = new DatabaseEntry(Serializers.<K>forGenerics().serialize(key));
        DatabaseEntry dbValue = new DatabaseEntry(Serializers.<V>forGenerics().serialize(value));

        database.put(null, dbKey, dbValue);
    }

    /**
     * Removes a value from the {@code database} according to its {@code key}.
     *
     * @param database the database where to remove the value
     * @param key      the key of the element to remove
     * @param <K>      the type of the key
     */
    protected <K> void delete(Database database, K key) {
        DatabaseEntry dbKey = new DatabaseEntry(Serializers.<K>forGenerics().serialize(key));

        database.delete(null, dbKey);
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
