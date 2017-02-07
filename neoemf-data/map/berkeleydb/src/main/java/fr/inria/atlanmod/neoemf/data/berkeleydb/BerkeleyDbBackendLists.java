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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * ???
 */
@Experimental
class BerkeleyDbBackendLists extends AbstractBerkeleyDbBackend {

    /**
     * Constructs a new {@code BerkeleyDbBackendIndices} on the given {@code file} with the given
     * {@code envConfig}.
     *
     * @param file      ???
     * @param envConfig ???
     * @param dbConfig  ???
     *
     * @note This constructor is protected. To create a new {@code BerkeleyDbBackendLists} use {@link
     * BerkeleyDbBackendFactory#createPersistentBackend(File, Map)}.
     */
    protected BerkeleyDbBackendLists(File file, EnvironmentConfig envConfig, DatabaseConfig dbConfig) {
        super(file, envConfig, dbConfig);
    }

    @Override
    public <V> Optional<V> valueOf(MultivaluedFeatureKey key) {
        return this.<List<V>>valueOf(key.withoutPosition())
                .map(ts -> ts.get(key.position()));
    }

    @Override
    public <V> Optional<V> valueFor(MultivaluedFeatureKey key, V value) {
        List<V> values = this.<List<V>>valueOf(key.withoutPosition()).orElse(newValue());

        Optional<V> previousValue = Optional.of(values.set(key.position(), value));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    public <V> void addValue(MultivaluedFeatureKey key, V value) {
        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .orElse(newValue());

        values.add(key.position(), value);

        valueFor(key.withoutPosition(), values);
    }

    @Override
    public <V> Optional<V> removeValue(MultivaluedFeatureKey key) {
        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .orElse(newValue());

        Optional<V> previousValue = Optional.of(values.remove(key.position()));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    public <V> boolean containsValue(FeatureKey key, V value) {
        return this.<List<V>>valueOf(key)
                .map(ts -> ts.contains(value))
                .orElse(false);
    }

    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        return this.<List<V>>valueOf(key)
                .map(ts -> {
                    int index = ts.indexOf(value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        return this.<List<V>>valueOf(key)
                .map(ts -> {
                    int index = ts.lastIndexOf(value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Override
    public <V> Iterable<V> valuesAsList(FeatureKey key) {
        return this.<List<V>>valueOf(key)
                .orElse(newValue());
    }

    @Override
    public <V> OptionalInt sizeOf(FeatureKey key) {
        return this.<List<V>>valueOf(key)
                .map(ts -> OptionalInt.of(ts.size()))
                .orElse(OptionalInt.empty());
    }

    /**
     * Creates a new multi-value.
     *
     * @param <V> the type of the multi-value
     *
     * @return a new multi-value
     */
    private <V> List<V> newValue() {
        return new ArrayList<>();
    }
}
