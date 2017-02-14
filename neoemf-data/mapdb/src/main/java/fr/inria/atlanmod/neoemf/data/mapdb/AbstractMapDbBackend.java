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
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.util.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.data.mapdb.util.serializer.SingleFeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
     * associated {@link SingleFeatureKey}.
     */
    @Nonnull
    private final HTreeMap<SingleFeatureKey, Object> features;

    /**
     * The MapDB database.
     */
    @Nonnull
    private final DB db;

    /**
     * {@link Id} representing the {@link Id} concerned by the last call of {{@link #create(Id)}}.
     * MapDB doesn't support {@link Id} creation.
     */
    private Id lastId;

    /**
     * Constructs a new {@code AbstractMapDbBackend} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link Map}s from the MapDB engine and set their respective
     * {@link Serializer}s.
     *
     * @param db the {@link DB} used to creates the used {@link Map}s and manage the database
     *
     * @note This constructor is protected. To create a new {@code AbstractMapDbBackend} use {@link
     * PersistenceBackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
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
                .keySerializer(new SingleFeatureKeySerializer())
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
            NeoLogger.warn(e);
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
    public void create(Id id) {
        lastId = id;
    }

    @Override
    public boolean has(Id id) {
        return (Objects.equals(id, lastId)) || metaclassOf(id).isPresent();
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        return fromDatabase(containersMap, id);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        toDatabase(containersMap, id, container);
    }

    @Nonnull
    @Override
    public Optional<MetaclassDescriptor> metaclassOf(Id id) {
        return fromDatabase(instanceOfMap, id);
    }

    @Override
    public void metaclassFor(Id id, MetaclassDescriptor metaclass) {
        toDatabase(instanceOfMap, id, metaclass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        return fromDatabase(features, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        return toDatabase(features, key, value);
    }

    @Override
    public void unsetValue(SingleFeatureKey key) {
        features.remove(key);
    }

    @Override
    public boolean hasValue(SingleFeatureKey key) {
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
    protected <K, V> Optional<V> fromDatabase(HTreeMap<K, ? super V> database, K key) {
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
    protected <K, V> Optional<V> toDatabase(HTreeMap<K, ? super V> database, K key, V value) {
        return Optional.ofNullable((V) database.put(key, value));
    }
}
