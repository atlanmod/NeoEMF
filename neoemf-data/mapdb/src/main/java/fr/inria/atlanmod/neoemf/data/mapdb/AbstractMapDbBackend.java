/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.serializer.BeanSerializerFactory;
import fr.inria.atlanmod.neoemf.data.mapping.AllReferenceAs;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import org.mapdb.DB;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.functions.Action;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link MapDbBackend} that provides overall behavior for the management of a MapDB database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractMapDbBackend extends AbstractBackend implements MapDbBackend, AllReferenceAs<Long> {

    /**
     * The {@link BeanSerializerFactory} to use for creating the {@link fr.inria.atlanmod.commons.io.serializer.Serializer}
     * instances.
     */
    @Nonnull
    protected final BeanSerializerFactory serializers = BeanSerializerFactory.getInstance();

    /**
     * The MapDB database.
     */
    @Nonnull
    private final DB database;

    /**
     * A persistent map that stores the container of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private final HTreeMap<Id, SingleFeatureBean> containers;

    /**
     * A persistent map that stores the meta-class for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private final HTreeMap<Id, ClassBean> instances;

    /**
     * A persistent map that stores single-feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link SingleFeatureBean}. Depending on the mapping used, it can also contain
     * many-feature values grouped in collections.
     */
    @Nonnull
    private final HTreeMap<SingleFeatureBean, Object> singleFeatures;

    /**
     * Constructs a new {@code AbstractMapDbBackend} wrapping the provided {@code database}.
     *
     * @param database the {@link DB} used to create and manage {@link HTreeMap}s
     *
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected AbstractMapDbBackend(DB database) {
        checkNotNull(database, "database");

        this.database = database;

        this.containers = database.hashMap("containers")
                .keySerializer(new SerializerDecorator<>(serializers.forId()))
                .valueSerializer(new SerializerDecorator<>(serializers.forSingleFeature()))
                .createOrOpen();

        this.instances = database.hashMap("instances")
                .keySerializer(new SerializerDecorator<>(serializers.forId()))
                .valueSerializer(new SerializerDecorator<>(serializers.forClass()))
                .createOrOpen();

        this.singleFeatures = database.hashMap("features/single")
                .keySerializer(new SerializerDecorator<>(serializers.forSingleFeature()))
                .valueSerializer(Serializer.ELSA)
                .createOrOpen();
    }

    @Nonnull
    @Override
    protected Action blockingClose() {
        return database::close;
    }

    @Nonnull
    @Override
    protected Action blockingSave() {
        return database.isClosed()
                ? Functions.EMPTY_ACTION
                : database::commit;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void innerCopyTo(DataMapper target) {
        AbstractMapDbBackend to = AbstractMapDbBackend.class.cast(target);

        for (Map.Entry<String, Object> entry : database.getAll().entrySet()) {
            Object collection = entry.getValue();
            if (Map.class.isInstance(collection)) {
                Map fromMap = Map.class.cast(collection);
                Map toMap = to.database.hashMap(entry.getKey()).open();

                toMap.putAll(fromMap);
            }
            else {
                throw new UnsupportedOperationException(String.format("Cannot copy MapDB backend: store type %s is not supported", collection.getClass().getSimpleName()));
            }
        }
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        // Retrieve the container
        Callable<SingleFeatureBean> getFunc = () -> get(containers, id);

        // The composed query to execute on the database
        Maybe<SingleFeatureBean> databaseQuery = Maybe.fromCallable(getFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        // Define the container
        Action setFunc = () -> put(containers, id, container);

        // The composed query to execute on the database
        Completable databaseQuery = Completable.fromAction(setFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        checkNotNull(id, "id");

        // Remove the container
        Action removeFunc = () -> delete(containers, id);

        // The composed query to execute on the database
        Completable databaseQuery = Completable.fromAction(removeFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        // Retrieve the meta-class
        Callable<ClassBean> getFunc = () -> get(instances, id);

        // The composed query to execute on the database
        Maybe<ClassBean> databaseQuery = Maybe.fromCallable(getFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        // Define the meta-class, if it does not already exist
        Callable<ClassBean> setFunc = () -> putIfAbsent(instances, id, metaClass);

        // The composed query to execute on the database
        Completable databaseQuery = Maybe.fromCallable(setFunc)
                .isEmpty()
                .filter(Functions.equalsWith(false))
                .doOnSuccess(CommonQueries.classAlreadyExists())
                .ignoreElement();

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Flowable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        // The composed query to execute on the database
        Flowable<Id> databaseQuery = Maybe.fromCallable(instances::getEntries)
                .flattenAsFlowable(Functions.identity())
                .filter(e -> metaClasses.contains(e.getValue()))
                .map(Map.Entry::getKey);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Callable<V> getFunc = () -> get(singleFeatures, key);

        Maybe<V> databaseQuery = Maybe.fromCallable(getFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Callable<V> setFunc = () -> put(singleFeatures, key, value);

        Maybe<V> databaseQuery = Maybe.fromCallable(setFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Action removeFunc = () -> delete(singleFeatures, key);

        Completable databaseQuery = Completable.fromAction(removeFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Converter<Id, Long> referenceConverter() {
        return IdConverters.withLong();
    }

    /**
     * Retrieves the value of the {@code key} from the {@code database}.
     *
     * @param database the database where to looking for
     * @param key      the key of the element to retrieve
     * @param <K>      the type of the key
     * @param <V>      the type of the value
     *
     * @return an {@link Optional} containing the element, or an empty {@link Optional} if the element has not been
     * found
     */
    @Nullable
    @SuppressWarnings("unchecked")
    protected <K, V> V get(HTreeMap<K, ? super V> database, K key) {
        return (V) database.get(key);
    }

    /**
     * Saves a {@code value} identified by the {@code key} in the {@code database}.
     *
     * @param database the database where to save the value
     * @param key      the key of the element to save
     * @param value    the value to save
     * @param <K>      the type of the key
     * @param <V>      the type of the value
     *
     * @return an {@link Optional} containing the previous element, or an empty {@link Optional} if there is not any
     * previous element
     */
    @Nullable
    @SuppressWarnings("unchecked")
    protected <K, V> V put(HTreeMap<K, ? super V> database, K key, V value) {
        return (V) database.put(key, value);
    }

    /**
     * Saves a {@code value} identified by the {@code key} in the {@code database}, only if the {@code key} is not
     * already defined
     *
     * @param database the database where to save the value
     * @param key      the key of the element to save
     * @param value    the value to save
     * @param <K>      the type of the key
     * @param <V>      the type of the value
     *
     * @return {@code true} if the {@code key} has been saved
     */
    @Nullable
    @SuppressWarnings("unchecked")
    protected <K, V> V putIfAbsent(HTreeMap<K, ? super V> database, K key, V value) {
        return (V) database.putIfAbsent(key, value);
    }

    /**
     * Removes a value from the {@code database} according to its {@code key}.
     *
     * @param database the database where to remove the value
     * @param key      the key of the element to remove
     * @param <K>      the type of the key
     */
    protected <K> void delete(HTreeMap<K, ?> database, K key) {
        database.remove(key);
    }

    /**
     * A MapDB serializer that delegates its processing to an internal {@link fr.inria.atlanmod.commons.io.serializer.Serializer}.
     *
     * @param <T> the type of the (de)serialized value
     */
    @Immutable
    @ParametersAreNonnullByDefault
    static final class SerializerDecorator<T> implements Serializer<T> {

        /**
         * The serializer where to delegate the serialization process.
         */
        @Nonnull
        private final fr.inria.atlanmod.commons.io.serializer.Serializer<T> delegate;

        /**
         * Constructs a new {@code SerializerDecorator} on the specified {@code delegate}.
         *
         * @param delegate the serializer where to delegate the serialization process
         */
        public SerializerDecorator(fr.inria.atlanmod.commons.io.serializer.Serializer<T> delegate) {
            checkNotNull(delegate, "delegate");

            this.delegate = delegate;
        }

        @Override
        public void serialize(DataOutput2 out, T value) throws IOException {
            delegate.serialize(value, DataOutput.class.cast(out));
        }

        @Nonnull
        @Override
        public T deserialize(DataInput2 in, int available) throws IOException {
            return delegate.deserialize(in);
        }
    }
}
