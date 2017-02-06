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
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.map.core.MapBackend;
import fr.inria.atlanmod.neoemf.data.mapdb.serializer.FeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.mapdb.serializer.IdSerializer;
import fr.inria.atlanmod.neoemf.data.mapdb.serializer.MultivaluedFeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * {@link PersistenceBackend} that is responsible of low-level access to a MapDB database.
 * <p>
 * It wraps an existing {@link DB} and provides facilities to create and retrieve elements. This class manages a set of
 * {@link Map}s used to represent model elements: <ul> <li><b>Containers Map: </b> holds containment and container links
 * between elements</li> <li><b>InstanceOf Map: </b> holds metaclass information for each element</li> <li><b>Features
 * Map: </b> holds non-containment {@link EStructuralFeature} links between elements </li> <li><b>Multi-valued Map: </b>
 * optional Map used in {@link fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithIndices} that stores
 * {@link Collection} indices instead of a serialized version of the collection itself</li> </ul>
 *
 * @note This class is used in {@link fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStore} and its
 * subclasses to access and manipulate the database.
 * @note Instances of {@link MapDbPersistenceBackend} are created by {@link MapDbPersistenceBackendFactory} that
 * provides an usable {@link DB} that can be manipulated by this wrapper.
 * @see MapDbPersistenceBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStore
 * @see fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithLists
 * @see fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithArrays
 * @see fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteCachedMapStore
 */
public class MapDbPersistenceBackend extends AbstractPersistenceBackend implements MapBackend {

    /**
     * The literal description of this back-end.
     */
    public static final String NAME = "mapdb";

    /**
     * ???
     */
    private static final String KEY_CONTAINER = "eContainer";

    /**
     * ???
     */
    private static final String KEY_INSTANCE_OF = "neoInstanceOf";

    /**
     * ???
     */
    private static final String KEY_FEATURES = "features";

    /**
     * ???
     */
    private static final String KEY_MULTIVALUED_FEATURES = "multivaluedFeatures";

    /**
     * The MapDB database.
     */
    private final DB db;

    /**
     * A persistent map that stores the container of {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    private final HTreeMap<Id, ContainerValue> containersMap;

    /**
     * A persistent map that stores the EClass for {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    private final HTreeMap<Id, MetaclassValue> instanceOfMap;

    /**
     * A persistent map that stores Structural feature values for {@link PersistentEObject}s, identified by the
     * associated {@link FeatureKey}.
     */
    private final HTreeMap<FeatureKey, Object> features;

    /**
     * A persistent map that store the values of multi-valued features for {@link PersistentEObject}s, identified by the
     * associated {@link MultivaluedFeatureKey}.
     */
    private final HTreeMap<MultivaluedFeatureKey, Object> multivaluedFeatures;

    /**
     * Constructs a new {@code MapDbPersistenceBackend} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link Map}s from the MapDB engine and set their respective
     * {@link Serializer}s.
     *
     * @param db the {@link DB} used to creates the used {@link Map}s and manage the database
     *
     * @note This constructor is protected. To create a new {@code MapDbPersistenceBackend} use {@link
     * MapDbPersistenceBackendFactory#createPersistentBackend(java.io.File, Map)}.
     * @see MapDbPersistenceBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected MapDbPersistenceBackend(DB db) {

        this.db = db;

        containersMap = this.db.hashMap(KEY_CONTAINER)
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        instanceOfMap = this.db.hashMap(KEY_INSTANCE_OF)
                .keySerializer(new IdSerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        features = this.db.hashMap(KEY_FEATURES)
                .keySerializer(new FeatureKeySerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        multivaluedFeatures = this.db.hashMap(KEY_MULTIVALUED_FEATURES)
                .keySerializer(new MultivaluedFeatureKeySerializer())
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
        return Optional.ofNullable(containersMap.get(id));
    }

    @Override
    public void containerFor(Id id, ContainerValue container) {
        containersMap.put(id, container);
    }

    @Override
    public Optional<MetaclassValue> metaclassOf(Id id) {
        return Optional.ofNullable(instanceOfMap.get(id));
    }

    @Override
    public void metaclassFor(Id id, MetaclassValue metaclass) {
        instanceOfMap.put(id, metaclass);
    }

    @Override
    public Optional<Object> valueOf(FeatureKey key) {
        return Optional.ofNullable(features.get(key));
    }

    @Override
    public Optional<Object> valueOf(MultivaluedFeatureKey key) {
        return Optional.ofNullable(multivaluedFeatures.get(key));
    }

    @Override
    public Optional<Id> referenceOf(FeatureKey key) {
        return valueOf(key)
                .map(i -> Optional.of(StringId.from(i)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        return valueOf(key)
                .map(i -> Optional.of(StringId.from(i)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Object> valueFor(FeatureKey key, Object value) {
        return Optional.ofNullable(features.put(key, value));
    }

    @Override
    public Optional<Object> valueFor(MultivaluedFeatureKey key, Object value) {
        return Optional.ofNullable(multivaluedFeatures.put(key, value));
    }

    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id id) {
        return valueFor(key, id)
                .map(i -> Optional.of(StringId.from(i)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        return valueFor(key, id)
                .map(i -> Optional.of(StringId.from(i)))
                .orElse(Optional.empty());
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
    public void addValue(MultivaluedFeatureKey key, Object value) {
        // Make space for the new element
        int size = sizeOf(key).orElse(0);

        // TODO Replace by Stream
        for (int i = size - 1; i >= key.position(); i--) {
            valueFor(key.withPosition(i + 1), valueOf(key.withPosition(i)).orElse(null));
        }
        sizeOf(key, size + 1);

        // Add element
        valueFor(key, value);
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        // Make space for the new element
        int size = sizeOf(key).orElse(0);

        // TODO Replace by Stream
        for (int i = size - 1; i >= key.position(); i--) {
            referenceFor(key.withPosition(i + 1), referenceOf(key.withPosition(i)).orElse(null));
        }
        sizeOf(key, size + 1);

        // Add reference
        referenceFor(key, id);
    }

    @Override
    public Optional<Object> removeValue(MultivaluedFeatureKey key) {
        int size = sizeOf(key).orElse(0);

        // Get element to remove
        Optional<Object> previousValue = valueOf(key);

        // Update indexes (element to remove is overwritten)
        // TODO Replace by Stream
        for (int i = key.position() + 1; i < size; i++) {
            valueFor(key.withPosition(i - 1), valueOf(key.withPosition(i)).orElse(null));
        }
        sizeOf(key, size - 1);

        return previousValue;
    }

    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        int size = sizeOf(key).orElse(0);

        // Get element to remove
        Optional<Id> previousId = referenceOf(key);

        // Update indexes (element to remove is overwritten)
        // TODO Replace by Stream
        for (int i = key.position() + 1; i < size; i++) {
            referenceFor(key.withPosition(i - 1), referenceOf(key.withPosition(i)).orElse(null));
        }
        sizeOf(key, size - 1);

        return previousId;
    }

    @Override
    public void cleanValues(FeatureKey key) {
        unsetValue(key);
    }

    @Override
    public void cleanReferences(FeatureKey key) {
        cleanValues(key);
    }

    @Override
    public boolean containsValue(FeatureKey key, Object value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public OptionalInt indexOfValue(FeatureKey key, Object value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public OptionalInt lastIndexOfValue(FeatureKey key, Object value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Iterable<Object> valuesAsList(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public OptionalInt sizeOf(FeatureKey key) {
        return valueOf(key).map(v -> OptionalInt.of((int) v)).orElse(OptionalInt.empty());
    }

    protected void sizeOf(FeatureKey key, int size) {
        valueFor(key, size);
    }

    @VisibleForTesting
    @Override
    public Map<String, Object> getAll() {
        return db.getAll();
    }

    @VisibleForTesting
    @Override
    public <E> E get(String name) {
        return db.get(name);
    }

    /**
     * Copies all the contents of this {@code PersistenceBackend} to the {@code target} one.
     *
     * @param target the {@code PersistenceBackend} to copy the database contents to
     */
    @SuppressWarnings("rawtypes")
    public void copyTo(MapDbPersistenceBackend target) {
        for (Map.Entry<String, Object> entry : db.getAll().entrySet()) {
            Object collection = entry.getValue();
            if (collection instanceof Map) {
                Map fromMap = (Map) collection;
                Map toMap = target.db.hashMap(entry.getKey()).createOrOpen();

                toMap.putAll(fromMap);
            }
            else {
                throw new UnsupportedOperationException("Cannot copy MapDB backend: store type " + collection.getClass().getSimpleName() + " is not supported");
            }
        }
    }
}
