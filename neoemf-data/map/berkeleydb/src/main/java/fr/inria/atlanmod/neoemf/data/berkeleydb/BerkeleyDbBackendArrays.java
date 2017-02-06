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
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.apache.commons.lang.ArrayUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * ???
 */
@Experimental
class BerkeleyDbBackendArrays extends AbstractBerkeleyDbBackend {

    /**
     * Constructs a new {@code BerkeleyDbBackendIndices} on the given {@code file} with the given
     * {@code envConfig}.
     *
     * @param file      ???
     * @param envConfig ???
     * @param dbConfig  ???
     *
     * @note This constructor is protected. To create a new {@code BerkeleyDbBackendArrays} use {@link
     * BerkeleyDbBackendFactory#createPersistentBackend(File, Map)}.
     */
    protected BerkeleyDbBackendArrays(File file, EnvironmentConfig envConfig, DatabaseConfig dbConfig) {
        super(file, envConfig, dbConfig);
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
                    return index == ArrayUtils.INDEX_NOT_FOUND ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Override
    public <T> OptionalInt lastIndexOfValue(FeatureKey key, T value) {
        return this.<T[]>valueOf(key)
                .map(ts -> {
                    int index = ArrayUtils.lastIndexOf(ts, value);
                    return index == ArrayUtils.INDEX_NOT_FOUND ? OptionalInt.empty() : OptionalInt.of(index);
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
    public Iterable<Id> referencesAsList(FeatureKey key) {
        Id[] references = this.<Id[]>valueOf(key)
                .orElse(newValue());

        return Arrays.asList(references);
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
