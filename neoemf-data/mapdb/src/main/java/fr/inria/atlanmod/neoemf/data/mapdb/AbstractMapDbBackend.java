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
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.mapdb.DB;
import org.mapdb.Serializer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * An abstract {@link MapDbBackend} that provides overall behavior for the management of a MapDB database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractMapDbBackend implements MapDbBackend {

    /**
     * A persistent map that stores the container of {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    @Nonnull
    private final ConcurrentMap<Id, ContainerDescriptor> containersMap;

    /**
     * A persistent map that stores the EClass for {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    @Nonnull
    private final ConcurrentMap<Id, ClassDescriptor> instanceOfMap;

    /**
     * A persistent map that stores Structural feature values for {@link PersistentEObject}s, identified by the
     * associated {@link FeatureKey}.
     */
    @Nonnull
    private final ConcurrentMap<FeatureKey, Object> features;

    /**
     * The MapDB database.
     */
    @Nonnull
    private final DB db;

    /**
     * Constructs a new {@code AbstractMapDbBackend} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link ConcurrentMap}s from the MapDB engine and set their respective
     * {@link Serializer}s.
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@code AbstractMapDbBackend} use {@link
     * BackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     *
     * @param db the {@link DB} used to creates the used {@link ConcurrentMap}s and manage the database
     *
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected AbstractMapDbBackend(DB db) {
        this.db = checkNotNull(db);

        containersMap = db.hashMap("eContainer")
                .keySerializer(Serializer.JAVA)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        instanceOfMap = db.hashMap("neoInstanceOf")
                .keySerializer(Serializer.JAVA)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        features = db.hashMap("features")
                .keySerializer(Serializer.JAVA)
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
        catch (Exception ignored) {
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void copyTo(DataMapper target) {
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
                throw new UnsupportedOperationException(String.format("Cannot copy MapDB backend: store type %s is not supported", collection.getClass().getSimpleName()));
            }
        }
    }

    @Override
    public boolean isDistributed() {
        return false;
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
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        return get(instanceOfMap, id);
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
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
    protected <K, V> Optional<V> get(Map<K, ? super V> database, K key) {
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
    protected <K, V> Optional<V> put(Map<K, ? super V> database, K key, V value) {
        return Optional.ofNullable((V) database.put(key, value));
    }

    /**
     * Removes a value from the {@code database} according to its {@code key}.
     *
     * @param database the database where to remove the value
     * @param key      the key of the element to remove
     * @param <K>      the type of the key
     */
    protected <K> void delete(Map<K, ?> database, K key) {
        database.remove(key);
    }
}
