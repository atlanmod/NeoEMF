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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.mapdb.DB;
import org.mapdb.Serializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
 * @note Instances of {@link MapDbBackendLists} are created by {@link MapDbBackendFactory} that provides an usable
 * {@link DB} that can be manipulated by this wrapper.
 * @see MapDbBackendFactory
 * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
 */
class MapDbBackendLists extends AbstractMapDbBackend {

    /**
     * Constructs a new {@code MapDbBackendLists} wrapping the provided {@code db}.
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
    protected MapDbBackendLists(DB db) {
        super(db);
    }

    @Override
    public <T> Optional<T> valueOf(MultivaluedFeatureKey key) {
        return this.<List<T>>valueOf(key.withoutPosition())
                .map(ts -> ts.get(key.position()));
    }

    @Override
    public <T> Optional<T> valueFor(MultivaluedFeatureKey key, T value) {
        List<T> values = this.<List<T>>valueOf(key.withoutPosition()).orElse(newValue());

        Optional<T> previousValue = Optional.of(values.set(key.position(), value));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    public <T> void addValue(MultivaluedFeatureKey key, T value) {
        List<T> values = this.<List<T>>valueOf(key.withoutPosition())
                .orElse(newValue());

        values.add(key.position(), value);

        valueFor(key.withoutPosition(), values);
    }

    @Override
    public <T> Optional<T> removeValue(MultivaluedFeatureKey key) {
        List<T> values = this.<List<T>>valueOf(key.withoutPosition())
                .orElse(newValue());

        Optional<T> previousValue = Optional.of(values.remove(key.position()));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    public <T> boolean containsValue(FeatureKey key, T value) {
        return this.<List<T>>valueOf(key)
                .map(ts -> ts.contains(value))
                .orElse(false);
    }

    @Override
    public <T> OptionalInt indexOfValue(FeatureKey key, T value) {
        return this.<List<T>>valueOf(key)
                .map(ts -> {
                    int index = ts.indexOf(value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Override
    public <T> OptionalInt lastIndexOfValue(FeatureKey key, T value) {
        return this.<List<T>>valueOf(key)
                .map(ts -> {
                    int index = ts.lastIndexOf(value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Override
    public <T> Iterable<T> valuesAsList(FeatureKey key) {
        return this.<List<T>>valueOf(key)
                .orElse(newValue());
    }

    @Override
    public <T> OptionalInt sizeOf(FeatureKey key) {
        return this.<List<T>>valueOf(key)
                .map(ts -> OptionalInt.of(ts.size()))
                .orElse(OptionalInt.empty());
    }

    /**
     * Creates a new multi-value.
     *
     * @param <T> the type of the multi-value
     *
     * @return a new multi-value
     */
    private <T> List<T> newValue() {
        return new ArrayList<>();
    }
}
