/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
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

import fr.inria.atlanmod.commons.LazyObject;
import fr.inria.atlanmod.commons.collect.CloseableIterator;
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
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.internal.functions.Functions;

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
    protected final BeanSerializerFactory serializers = BeanSerializerFactory.getInstance();

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
     * @param environment    the database environment used to create and manage {@link Database}s
     * @param databaseConfig the configuration of the created {@link Database}s
     */
    protected AbstractBerkeleyDbBackend(Environment environment, DatabaseConfig databaseConfig) {
        checkNotNull(environment, "environment");
        checkNotNull(databaseConfig, "databaseConfig");

        this.environment = environment;

        this.containers = environment.openDatabase(null, "containers", databaseConfig);
        this.instances = environment.openDatabase(null, "instances", databaseConfig);
        this.features = environment.openDatabase(null, "features/single", databaseConfig);
    }

    @Nonnull
    @Override
    protected Action blockingClose() {
        return () -> {
            containers.close();
            instances.close();
            features.close();

            environment.close();
        };
    }

    @Nonnull
    @Override
    protected Action blockingSave() {
        // Do nothing: data are automatically saved
        return Functions.EMPTY_ACTION;
    }

    @Override
    protected void innerCopyTo(DataMapper target) {
        AbstractBerkeleyDbBackend to = AbstractBerkeleyDbBackend.class.cast(target);

        copy(containers, to.containers);
        copy(instances, to.instances);
        copy(features, to.features);
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        Maybe<SingleFeatureBean> query = get(containers, id, serializers.forId(), serializers.forSingleFeature());

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        Completable query = put(containers, id, container, serializers.forId(), serializers.forSingleFeature());

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        checkNotNull(id, "id");

        Completable query = delete(containers, id, serializers.forId());

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        Maybe<ClassBean> query = get(instances, id, serializers.forId(), serializers.forClass());

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        Completable query = putIfAbsent(instances, id, metaClass, serializers.forId(), serializers.forClass())
                .filter(Functions.equalsWith(false))
                .doOnSuccess(CommonQueries.classAlreadyExists())
                .ignoreElement();

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Flowable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        CloseableIterator<Id> iter = new AllInstancesIterator(metaClasses);

        Flowable<Id> query = Flowable.fromIterable(() -> iter)
                .doAfterTerminate(iter::close);

        return query.compose(dispatcher().dispatchFlowable());
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Maybe<V> query = get(features, key, serializers.forSingleFeature(), serializers.forAny());

        return query.compose(dispatcher().dispatchMaybe());
    }

    @Nonnull
    @Override
    public <V> Completable valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Completable query = put(features, key, value, serializers.forSingleFeature(), serializers.forAny());

        return query.compose(dispatcher().dispatchCompletable());
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Completable query = delete(features, key, serializers.forSingleFeature());

        return query.compose(dispatcher().dispatchCompletable());
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
    protected <K, V> Maybe<V> get(Database database, K key, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        DatabaseEntry dbValue = new DatabaseEntry();

        Callable<OperationStatus> getFunc = () -> database.get(
                null,
                new DatabaseEntry(keySerializer.serialize(key)),
                dbValue,
                LockMode.DEFAULT);

        return Single.fromCallable(getFunc)
                .filter(s -> s == OperationStatus.SUCCESS)
                .map(r -> valueSerializer.deserialize(dbValue.getData()));
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
    @Nonnull
    protected <K, V> Completable put(Database database, K key, V value, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        Callable<OperationStatus> setFunc = () -> database.put(
                null,
                new DatabaseEntry(keySerializer.serialize(key)),
                new DatabaseEntry(valueSerializer.serialize(value)));

        return Completable.fromCallable(setFunc);
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
    @Nonnull
    protected <K, V> Single<Boolean> putIfAbsent(Database database, K key, V value, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        Callable<OperationStatus> setFunc = () -> database.putNoOverwrite(
                null,
                new DatabaseEntry(keySerializer.serialize(key)),
                new DatabaseEntry(valueSerializer.serialize(value)));

        return Single.fromCallable(setFunc)
                .contains(OperationStatus.KEYEXIST)
                .map(b -> !b);
    }

    /**
     * Removes a value from the {@code database} according to its {@code key}.
     *
     * @param database      the database where to remove the value
     * @param key           the key of the element to remove
     * @param keySerializer the serializer to serialize the {@code key}
     * @param <K>           the type of the key
     */
    @Nonnull
    protected <K> Completable delete(Database database, K key, Serializer<K> keySerializer) {
        Callable<OperationStatus> removeFunc = () -> database.delete(
                null,
                new DatabaseEntry(keySerializer.serialize(key)));

        return Completable.fromCallable(removeFunc);
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
     * A {@link CloseableIterator} that iterates over meta-classes and returns {@link Id}s related to a set of defined
     * meta-classes. An {@link Id} is related to a meta-class if its associated element is instance of it.
     */
    @ParametersAreNonnullByDefault
    private class AllInstancesIterator implements CloseableIterator<Id> {

        /**
         * The database cursor, embedded on an on-demand loader.
         */
        @Nonnull
        private final LazyObject<Cursor> cursor = LazyObject.with(() -> instances.openCursor(null, null));

        /**
         * The set of meta-classes whose instances must be retrieved.
         */
        @Nonnull
        private final Set<ClassBean> metaClasses;

        /**
         * The database key, used to deserialize an entry.
         */
        @Nonnull
        private final DatabaseEntry dbKey = new DatabaseEntry();

        /**
         * The database value, used to deserialize an entry.
         */
        @Nonnull
        private final DatabaseEntry dbValue = new DatabaseEntry();

        /**
         * The prepared next element.
         */
        private Id nextElement;

        /**
         * {@code true} if the next element exists and has been prepared.
         */
        private boolean hasNext = false;

        /**
         * {@code true} if the next element is not prepared yet.
         */
        private boolean requirePreparation = true;

        /**
         * {@code true} if this iterator has been closed.
         *
         * @see #close()
         */
        private boolean isClosed = false;

        /**
         * Constructs a new {@code AllInstancesIterator} that matches the given {@code metaClasses}.
         *
         * @param metaClasses the set of meta-classes whose instances must be retrieved
         */
        public AllInstancesIterator(Set<ClassBean> metaClasses) {
            this.metaClasses = checkNotNull(metaClasses);
        }

        /**
         * Prepares the next element. Iterates over the cursor to the next matching element, or to the end of the cursor
         * if no element matches.
         */
        private void prepareNext() {
            if (isClosed) {
                throw new IllegalStateException("This iterator is closed");
            }

            if (!requirePreparation) {
                // No need to prepare anything
                return;
            }

            requirePreparation = false;

            try {
                while (cursor.get().getNext(dbKey, dbValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                    if (metaClasses.contains(serializers.forClass().deserialize(dbValue.getData()))) {
                        nextElement = serializers.forId().deserialize(dbKey.getData());
                        hasNext = true;
                        return;
                    }
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean hasNext() {
            prepareNext();
            return hasNext;
        }

        @Override
        public Id next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            requirePreparation = true;
            hasNext = false;

            return nextElement;
        }

        @Override
        public void close() {
            if (isClosed) {
                return;
            }

            try {
                if (cursor.isLoaded()) {
                    cursor.get().close();
                }
            }
            finally {
                isClosed = true;
            }
        }
    }
}
