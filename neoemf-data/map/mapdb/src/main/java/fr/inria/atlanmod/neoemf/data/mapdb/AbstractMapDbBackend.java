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

import fr.inria.atlanmod.neoemf.annotations.VisibleForTesting;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.mapdb.util.serializer.FeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.mapdb.util.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * An abstract {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend} that is responsible of low-level access to a
 * MapDB database.
 */
public abstract class AbstractMapDbBackend extends AbstractPersistenceBackend implements MapDbBackend {

    /**
     * A persistent map that stores the container of {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    protected final HTreeMap<Id, ContainerValue> containersMap;

    /**
     * A persistent map that stores the EClass for {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    protected final HTreeMap<Id, MetaclassValue> instanceOfMap;

    /**
     * A persistent map that stores Structural feature values for {@link PersistentEObject}s, identified by the
     * associated {@link FeatureKey}.
     */
    protected final HTreeMap<FeatureKey, Object> features;

    /**
     * The MapDB database.
     */
    private final DB db;

    /**
     * Constructs a new {@code AbstractMapDbBackend} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link Map}s from the MapDB engine and set their respective
     * {@link Serializer}s.
     *
     * @param db the {@link DB} used to creates the used {@link Map}s and manage the database
     *
     * @note This constructor is protected. To create a new {@code AbstractMapDbBackend} use {@link
     * MapDbBackendFactory#createPersistentBackend(java.io.File, Map)}.
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected AbstractMapDbBackend(DB db) {
        this.db = db;

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
    public void create(Id id) {
        // Do nothing
    }

    @Override
    public boolean has(Id id) {
        return false;
    }

    @Override
    public Optional<ContainerValue> containerOf(Id id) {
        return fromDatabase(containersMap, id);
    }

    @Override
    public void containerFor(Id id, ContainerValue container) {
        toDatabase(containersMap, id, container);
    }

    @Override
    public Optional<MetaclassValue> metaclassOf(Id id) {
        return fromDatabase(instanceOfMap, id);
    }

    @Override
    public void metaclassFor(Id id, MetaclassValue metaclass) {
        toDatabase(instanceOfMap, id, metaclass);
    }

    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        return fromDatabase(features, key);
    }

    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        return valueOf(key);
    }

    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        return valueOf(key);
    }

    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        return toDatabase(features, key, value);
    }

    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        return valueFor(key, id);
    }

    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        return valueFor(key, id);
    }

    @Override
    public void unsetValue(FeatureKey key) {
        features.remove(key);
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
        return features.containsKey(key);
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
    public void addReference(MultivaluedFeatureKey key, Id id) {
        addValue(key, id);
    }

    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        return removeValue(key);
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
    public boolean containsReference(FeatureKey key, Id id) {
        return containsValue(key, id);
    }

    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        return indexOfValue(key, id);
    }

    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return lastIndexOfValue(key, id);
    }

    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        return valuesAsList(key);
    }

    @VisibleForTesting
    public Map<String, Object> getAll() {
        return db.getAll();
    }

    @VisibleForTesting
    public <V> V get(String name) {
        return db.get(name);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void copyTo(MapDbBackend target) {
        AbstractMapDbBackend backend = (AbstractMapDbBackend) target;

        for (Map.Entry<String, Object> entry : db.getAll().entrySet()) {
            Object collection = entry.getValue();
            if (collection instanceof Map) {
                Map fromMap = (Map) collection;
                Map toMap = backend.db.hashMap(entry.getKey()).createOrOpen();

                toMap.putAll(fromMap);
            }
            else {
                throw new UnsupportedOperationException("Cannot copy MapDB backend: store type " + collection.getClass().getSimpleName() + " is not supported");
            }
        }
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
    @SuppressWarnings("unchecked")
    protected <K, V> Optional<V> toDatabase(HTreeMap<K, ? super V> database, K key, V value) {
        return Optional.ofNullable((V) database.put(key, value));
    }
}
