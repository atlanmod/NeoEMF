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
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.mapdb.DB;
import org.mapdb.Serializer;

import java.util.Arrays;
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
 * @note This class is used in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} and its subclasses to access
 * and manipulate the database.
 * @note Instances of {@link MapDbBackend} are created by {@link MapDbBackendFactory} that
 * provides an usable {@link DB} that can be manipulated by this wrapper.
 * @see MapDbBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
 */
class MapDbBackendArrays extends AbstractMapDbBackend {

    /**
     * Constructs a new {@code MapDbBackendArrays} wrapping the provided {@code db}.
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
    protected MapDbBackendArrays(DB db) {
        super(db);
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
        return Optional.of(asMany(features.get(key.withoutPosition()))[key.position()]);
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
        Object[] values = asMany(features.get(key.withoutPosition()));

        Optional<Object> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = value;

        features.put(key.withoutPosition(), values);

        return previousValue;
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
        Object[] values = Optional.ofNullable(asMany(features.get(key.withoutPosition()))).orElse(new Object[0]);
        values = ArrayUtils.add(values, key.position(), value);

        features.put(key.withoutPosition(), values);
    }

    @Override
    public void addReference(MultivaluedFeatureKey key, Id id) {
        addValue(key, id);
    }

    @Override
    public Optional<Object> removeValue(MultivaluedFeatureKey key) {
        Object[] values = asMany(features.get(key.withoutPosition()));

        Optional<Object> previousValue = Optional.of(values[key.position()]);

        values = ArrayUtils.remove(values, key.position());

        features.put(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    public Optional<Id> removeReference(MultivaluedFeatureKey key) {
        return removeValue(key)
                .map(v -> Optional.of(StringId.from(v)))
                .orElse(Optional.empty());
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
        return ArrayUtils.contains(asMany(features.get(key)), value);
    }

    @Override
    public boolean containsReference(FeatureKey key, Id id) {
        return containsValue(key, id);
    }

    @Override
    public OptionalInt indexOfValue(FeatureKey key, Object value) {
        int index = ArrayUtils.indexOf(asMany(features.get(key)), value);
        return index == ArrayUtils.INDEX_NOT_FOUND ? OptionalInt.empty() : OptionalInt.of(index);
    }

    @Override
    public OptionalInt indexOfReference(FeatureKey key, Id id) {
        return indexOfValue(key, id);
    }

    @Override
    public OptionalInt lastIndexOfValue(FeatureKey key, Object value) {
        int index = ArrayUtils.lastIndexOf(asMany(features.get(key)), value);
        return index == ArrayUtils.INDEX_NOT_FOUND ? OptionalInt.empty() : OptionalInt.of(index);
    }

    @Override
    public OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return lastIndexOfValue(key, id);
    }

    @Override
    public Iterable<Object> valuesAsList(FeatureKey key) {
        return Arrays.asList(asMany(features.get(key)));
    }

    @Override
    public Iterable<Id> referencesAsList(FeatureKey key) {
        return Arrays.asList(asMany(features.get(key)));
    }

    @Override
    public OptionalInt sizeOf(FeatureKey key) {
        return Optional.ofNullable(asMany(features.get(key)))
                .map(v -> OptionalInt.of(v.length))
                .orElse(OptionalInt.empty());
    }

    /**
     * Returns the given {@code value} as a multi-value.
     *
     * @param value the value to cast
     * @param <T>   the type of the multi-value
     *
     * @return a multi-value
     */
    @SuppressWarnings("unchecked")
    private <T> T[] asMany(Object value) {
        return (T[]) value;
    }
}
