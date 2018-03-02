/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
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
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link MapDbBackend} that provides overall behavior for the management of a MapDB database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractMapDbBackend extends AbstractBackend implements MapDbBackend, AllReferenceAs<Long> {

    /**
     * The {@link BeanSerializerFactory} to use for creating the {@link fr.inria.atlanmod.commons.io.serializer.BinarySerializer}
     * instances.
     */
    @Nonnull
    protected static final BeanSerializerFactory SERIALIZER_FACTORY = BeanSerializerFactory.getInstance();

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
     * @param database the {@link org.mapdb.DB} used to create and manage {@link org.mapdb.HTreeMap}s
     *
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected AbstractMapDbBackend(DB database) {
        checkNotNull(database, "database");

        this.database = database;

        this.containers = database.hashMap("containers")
                .keySerializer(new SerializerDecorator<>(SERIALIZER_FACTORY.forId()))
                .valueSerializer(new SerializerDecorator<>(SERIALIZER_FACTORY.forSingleFeature()))
                .createOrOpen();

        this.instances = database.hashMap("instances")
                .keySerializer(new SerializerDecorator<>(SERIALIZER_FACTORY.forId()))
                .valueSerializer(new SerializerDecorator<>(SERIALIZER_FACTORY.forClass()))
                .createOrOpen();

        this.singleFeatures = database.hashMap("features/single")
                .keySerializer(new SerializerDecorator<>(SERIALIZER_FACTORY.forSingleFeature()))
                .valueSerializer(Serializer.ELSA)
                .createOrOpen();
    }

    @Override
    protected void internalClose() {
        database.close();
    }

    @Override
    public void internalSave() {
        if (!database.isClosed()) {
            database.commit();
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void internalCopyTo(DataMapper target) {
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
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        return get(containers, id);
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        put(containers, id, container);
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        delete(containers, id);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        return get(instances, id);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        return putIfAbsent(instances, id, metaClass);
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return instances.getEntries().stream()
                .filter(e -> metaClasses.contains(e.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        return get(singleFeatures, feature);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        return put(singleFeatures, feature, value);
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        delete(singleFeatures, feature);
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
    @Nonnull
    @SuppressWarnings("unchecked")
    protected <K, V> Optional<V> get(HTreeMap<K, ? super V> database, K key) {
        return Optional.ofNullable((V) database.get(key));
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
    @Nonnull
    @SuppressWarnings("unchecked")
    protected <K, V> Optional<V> put(HTreeMap<K, ? super V> database, K key, V value) {
        return Optional.ofNullable((V) database.put(key, value));
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
    protected <K, V> boolean putIfAbsent(HTreeMap<K, ? super V> database, K key, V value) {
        return isNull(database.putIfAbsent(key, value));
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
     * A MapDB serializer that delegates its processing to an internal {@link fr.inria.atlanmod.commons.io.serializer.BinarySerializer}.
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
        private final fr.inria.atlanmod.commons.io.serializer.BinarySerializer<T> delegate;

        /**
         * Constructs a new {@code SerializerDecorator} on the specified {@code delegate}.
         *
         * @param delegate the serializer where to delegate the serialization process
         */
        public SerializerDecorator(fr.inria.atlanmod.commons.io.serializer.BinarySerializer<T> delegate) {
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
