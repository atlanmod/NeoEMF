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

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
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
 * optional Map used in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} that stores {@link Collection}
 * indices instead of a serialized version of the collection itself</li> </ul>
 *
 * @note This class is used in {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore} and its subclasses to access
 * and manipulate the database.
 * @note Instances of {@link MapDbBackend} are created by {@link MapDbBackendFactory} that provides an usable {@link DB}
 * that can be manipulated by this wrapper.
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
    public <T> Optional<T> valueOf(MultivaluedFeatureKey key) {
        return this.<T[]>valueOf(key.withoutPosition())
                .map(ts -> ts[key.position()]);
    }

    @Override
    public <T> Optional<T> valueFor(MultivaluedFeatureKey key, T value) {
        T[] values = this.<T[]>valueOf(key.withoutPosition())
                .orElse(newValue());

        Optional<T> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = value;

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    public <T> void addValue(MultivaluedFeatureKey key, T value) {
        T[] values = this.<T[]>valueOf(key.withoutPosition())
                .orElse(newValue());

        valueFor(key.withoutPosition(), ArrayUtils.add(values, key.position(), value));
    }

    @Override
    public <T> Optional<T> removeValue(MultivaluedFeatureKey key) {
        T[] values = this.<T[]>valueOf(key.withoutPosition())
                .orElse(newValue());

        Optional<T> previousValue = Optional.of(values[key.position()]);

        valueFor(key.withoutPosition(), ArrayUtils.remove(values, key.position()));

        return previousValue;
    }

    @Override
    public <T> boolean containsValue(FeatureKey key, T value) {
        return this.<T[]>valueOf(key)
                .map(ts -> ArrayUtils.contains(ts, value))
                .orElse(false);
    }

    @Override
    public <T> OptionalInt indexOfValue(FeatureKey key, T value) {
        return this.<T[]>valueOf(key)
                .map(ts -> {
                    int index = ArrayUtils.indexOf(ts, value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Override
    public <T> OptionalInt lastIndexOfValue(FeatureKey key, T value) {
        return this.<T[]>valueOf(key)
                .map(ts -> {
                    int index = ArrayUtils.lastIndexOf(ts, value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Override
    public <T> Iterable<T> valuesAsList(FeatureKey key) {
        T[] values = this.<T[]>valueOf(key)
                .orElse(newValue());

        return Arrays.asList(values);
    }

    @Override
    public <T> OptionalInt sizeOf(FeatureKey key) {
        return this.<T[]>valueOf(key)
                .map(ts -> OptionalInt.of(ts.length))
                .orElse(OptionalInt.empty());
    }

    /**
     * Creates a new multi-value.
     *
     * @param <T> the type of the multi-value
     *
     * @return a new multi-value
     */
    @SuppressWarnings("unchecked")
    private <T> T[] newValue() {
        return (T[]) new Object[0];
    }
}
