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
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.mapdb.serializer.MultivaluedFeatureKeySerializer;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
 * @note This class is used in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} and its subclasses to access
 * and manipulate the database.
 * @note Instances of {@link MapDbBackendIndices} are created by {@link MapDbBackendFactory} that
 * provides an usable {@link DB} that can be manipulated by this wrapper.
 * @see MapDbBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
 * @see fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithLists
 * @see fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteMapStoreWithArrays
 * @see fr.inria.atlanmod.neoemf.data.map.core.store.DirectWriteCachedMapStore
 */
class MapDbBackendIndices extends AbstractMapDbBackend {

    /**
     * A persistent map that store the values of multi-valued features for {@link PersistentEObject}s, identified by the
     * associated {@link MultivaluedFeatureKey}.
     */
    private final HTreeMap<MultivaluedFeatureKey, Object> multivaluedFeatures;

    /**
     * Constructs a new {@code MapDbBackendIndices} wrapping the provided {@code db}.
     * <p>
     * This constructor initialize the different {@link Map}s from the MapDB engine and set their respective
     * {@link Serializer}s.
     *
     * @param db the {@link DB} used to creates the used {@link Map}s and manage the database
     *
     * @note This constructor is protected. To create a new {@code MapDbBackendIndices} use {@link
     * MapDbBackendFactory#createPersistentBackend(java.io.File, Map)}.
     * @see MapDbBackendFactory
     */
    @SuppressWarnings("unchecked")
    protected MapDbBackendIndices(DB db) {
        super(db);

        multivaluedFeatures = db.hashMap("multivaluedFeatures")
                .keySerializer(new MultivaluedFeatureKeySerializer())
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();
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
                .map(v -> Optional.of(StringId.from(v)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Id> referenceOf(MultivaluedFeatureKey key) {
        return valueOf(key)
                .map(v -> Optional.of(StringId.from(v)))
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
                .map(v -> Optional.of(StringId.from(v)))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id) {
        return valueFor(key, id)
                .map(v -> Optional.of(StringId.from(v)))
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
        int size = sizeOf(key.withoutPosition()).orElse(0);

        // TODO Replace by Stream
        for (int i = size - 1; i >= key.position(); i--) {
            valueFor(key.withPosition(i + 1), valueOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size + 1);

        valueFor(key, value);
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        int size = sizeOf(key.withoutPosition()).orElse(0);

        // TODO Replace by Stream
        for (int i = size - 1; i >= key.position(); i--) {
            referenceFor(key.withPosition(i + 1), referenceOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size + 1);

        referenceFor(key, id);
    }

    @Override
    public Optional<Object> removeValue(MultivaluedFeatureKey key) {
        Optional<Object> previousValue = valueOf(key);

        int size = sizeOf(key.withoutPosition()).orElse(0);

        // Update indexes (element to remove is overwritten)
        // TODO Replace by Stream
        for (int i = key.position() + 1; i < size; i++) {
            valueFor(key.withPosition(i - 1), valueOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size - 1);

        return previousValue;
    }

    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        Optional<Id> previousId = referenceOf(key);

        int size = sizeOf(key.withoutPosition()).orElse(0);

        // Update indexes (element to remove is overwritten)
        // TODO Replace by Stream
        for (int i = key.position() + 1; i < size; i++) {
            referenceFor(key.withPosition(i - 1), referenceOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size - 1);

        return previousId;
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
    public boolean containsValue(FeatureKey key, Object value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .anyMatch(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false));
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .anyMatch(i -> referenceOf(key.withPosition(i)).map(v -> Objects.equals(v, id)).orElse(false));
    }

    @Override
    public OptionalInt indexOfValue(FeatureKey key, Object value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .min();
    }

    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> referenceOf(key.withPosition(i)).map(v -> Objects.equals(v, id)).orElse(false))
                .min();
    }

    @Override
    public OptionalInt lastIndexOfValue(FeatureKey key, Object value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .max();
    }

    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> referenceOf(key.withPosition(i)).map(v -> Objects.equals(v, id)).orElse(false))
                .max();
    }

    @Override
    public Iterable<Object> valuesAsList(FeatureKey key) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .mapToObj(i -> valueOf(key.withPosition(i)).orElse(null))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .mapToObj(i -> referenceOf(key.withPosition(i)).orElse(null))
                .collect(Collectors.toList());
    }

    @Override
    public OptionalInt sizeOf(FeatureKey key) {
        return valueOf(key)
                .map(v -> OptionalInt.of((int) v))
                .orElse(OptionalInt.empty());
    }

    protected void sizeFor(FeatureKey key, int size) {
        valueFor(key, size);
    }
}
