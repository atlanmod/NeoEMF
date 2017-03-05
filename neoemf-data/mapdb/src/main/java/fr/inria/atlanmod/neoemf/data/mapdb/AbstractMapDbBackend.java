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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;
import fr.inria.atlanmod.neoemf.util.logging.Log;

import org.mapdb.DB;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend} that is responsible of low-level access to a
 * MapDB database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractMapDbBackend extends AbstractPersistenceBackend implements MapDbBackend {

    /**
     * A persistent map that stores the container of {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    @Nonnull
    private final HTreeMap<Id, ContainerDescriptor> containersMap;

    /**
     * A persistent map that stores the EClass for {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    @Nonnull
    private final HTreeMap<Id, MetaclassDescriptor> instanceOfMap;

    /**
     * A persistent map that stores Structural feature values for {@link PersistentEObject}s, identified by the
     * associated {@link FeatureKey}.
     */
    @Nonnull
    private final HTreeMap<FeatureKey, Object> features;

    /**
     * The MapDB database.
     */
    @Nonnull
    private final DB db;

    /**
     * Constructs a new {@code AbstractMapDbBackend} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link Map}s from the MapDB engine and set their respective
     * {@link Serializer}s.
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@code AbstractMapDbBackend} use {@link
     * PersistenceBackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     *
     * @param db the {@link DB} used to creates the used {@link Map}s and manage the database
     *
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected AbstractMapDbBackend(DB db) {
        this.db = checkNotNull(db);

        containersMap = db.hashMap("eContainer")
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        instanceOfMap = db.hashMap("neoInstanceOf")
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        features = db.hashMap("features")
                .keySerializer(new FeatureKeySerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
    }

    @Override
    public void save() {
        db.commit();
    }

    @Override
    public void close() {
        try {
            db.close();
        }
        catch (Exception e) {
            Log.warn(e);
        }
    }

    @Override
    public boolean isClosed() {
        return db.isClosed();
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void copyTo(PersistenceBackend target) {
        checkArgument(target instanceof AbstractMapDbBackend);
        AbstractMapDbBackend to = (AbstractMapDbBackend) target;

        for (Map.Entry<String, Object> entry : db.getAll().entrySet()) {
            Object collection = entry.getValue();
            if (collection instanceof Map) {
                Map fromMap = (Map) collection;
                Map toMap = to.db.hashMap(entry.getKey()).createOrOpen();

                toMap.putAll(fromMap);
            }
            else {
                throw new UnsupportedOperationException("Cannot copy MapDB backend: store type " + collection.getClass().getSimpleName() + " is not supported");
            }
        }
    }

    @Override
    public boolean exists(Id id) {
        return metaclassOf(id).isPresent();
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        checkNotNull(id);

        return get(containersMap, id);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        checkNotNull(id);
        checkNotNull(container);

        put(containersMap, id, container);
    }

    @Nonnull
    @Override
    public Optional<MetaclassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        return get(instanceOfMap, id);
    }

    @Override
    public void metaclassFor(Id id, MetaclassDescriptor metaclass) {
        checkNotNull(id);
        checkNotNull(metaclass);

        put(instanceOfMap, id, metaclass);
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

        return put(features, key, value);
    }

    @Override
    public void unsetValue(FeatureKey key) {
        checkNotNull(key);

        delete(features, key);
    }

    @Override
    public boolean hasValue(FeatureKey key) {
        checkNotNull(key);

        return features.containsKey(key);
    }

    /**
     * Retrieves a value from the {@code database} according to the given {@code key}.
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
     * A {@link Serializer} implementation for {@link Id}s.
     * <p>
     * <b>Note:</b> For now, this serializer only works with {@link StringId}.
     *
     * @see Id
     * @see StringId
     */
    @ParametersAreNonnullByDefault
    protected static class IdSerializer implements Serializer<Id> {

        /**
         * An embedded {@link String} {@link Serializer} used to handle {@link StringId}s.
         */
        private final Serializer<String> serializer = STRING;

        @Override
        public void serialize(DataOutput2 out, Id id) throws IOException {
            serializer.serialize(out, id.toString());
        }

        @Override
        public int hashCode() {
            return Objects.hash(serializer);
        }

        @Override
        public boolean equals(@Nullable Object o) {
            if (this == o) {
                return true;
            }
            if (isNull(o) || !(o instanceof IdSerializer)) {
                return false;
            }

            IdSerializer that = (IdSerializer) o;

            return Objects.equals(serializer, that.serializer);
        }

        @Nonnull
        @Override
        public Id deserialize(DataInput2 in, int i) throws IOException {
            return StringId.of(serializer.deserialize(in, i));
        }
    }

    /**
     * A {@link Serializer} implementation for {@link FeatureKey}.
     *
     * @see FeatureKey
     */
    @ParametersAreNonnullByDefault
    protected static class FeatureKeySerializer implements Serializer<FeatureKey> {

        /**
         * The {@link Serializer} that manages {@link String}s.
         */
        private final Serializer<String> stringSerializer = STRING;

        /**
         * The {@link Serializer} the manages {@link Id}s.
         */
        private final Serializer<Id> idSerializer = new IdSerializer();

        @Override
        public void serialize(DataOutput2 out, FeatureKey key) throws IOException {
            idSerializer.serialize(out, key.id());
            stringSerializer.serialize(out, key.name());
        }

        @Nonnull
        @Override
        public FeatureKey deserialize(DataInput2 in, int i) throws IOException {
            return FeatureKey.of(
                    idSerializer.deserialize(in, -1),
                    stringSerializer.deserialize(in, -1)
            );
        }
    }
}
