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
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.mapdb.DB;
import org.mapdb.Serializer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

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
@ParametersAreNonnullByDefault
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
     * PersistenceBackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     * @see MapDbBackendFactory
     */
    protected MapDbBackendArrays(DB db) {
        super(db);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultivaluedFeatureKey key) {
        return this.<V[]>valueOf(key.withoutPosition())
                .map(ts -> ts[key.position()]);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultivaluedFeatureKey key, V value) {
        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = value;

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    public <V> void addValue(MultivaluedFeatureKey key, V value) {
        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .orElse(newValue());

        while (key.position() > values.length) {
            values = ArrayUtils.add(values, values.length, null);
        }

        if (key.position() < values.length && isNull(values[key.position()])) {
            values[key.position()] = value;
        }
        else {
            values = ArrayUtils.add(values, key.position(), value);
        }

        valueFor(key.withoutPosition(), values);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultivaluedFeatureKey key) {
        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values[key.position()]);

        valueFor(key.withoutPosition(), ArrayUtils.remove(values, key.position()));

        return previousValue;
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, V value) {
        return this.<V[]>valueOf(key)
                .map(ts -> ArrayUtils.contains(ts, value))
                .orElse(false);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        return this.<V[]>valueOf(key)
                .map(ts -> {
                    int index = ArrayUtils.indexOf(ts, value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        return this.<V[]>valueOf(key)
                .map(ts -> {
                    int index = ArrayUtils.lastIndexOf(ts, value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public <V> Iterable<V> valuesAsList(FeatureKey key) {
        V[] values = this.<V[]>valueOf(key)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        return Arrays.asList(values);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOf(FeatureKey key) {
        return this.<V[]>valueOf(key)
                .map(ts -> OptionalInt.of(ts.length))
                .orElse(OptionalInt.empty());
    }

    /**
     * Creates a new multi-value.
     *
     * @param <V> the type of the multi-value
     *
     * @return a new multi-value
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private <V> V[] newValue() {
        return (V[]) new Object[0];
    }
}
