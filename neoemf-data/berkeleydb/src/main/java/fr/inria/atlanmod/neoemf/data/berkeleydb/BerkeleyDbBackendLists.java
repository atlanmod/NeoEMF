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

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * ???
 */
@ParametersAreNonnullByDefault
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
     * PersistenceBackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, Map)}.
     */
    protected BerkeleyDbBackendLists(File file, EnvironmentConfig envConfig, DatabaseConfig dbConfig) {
        super(file, envConfig, dbConfig);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultiFeatureKey key) {
        return this.<List<V>>valueOf(key.withoutPosition())
                .map(ts -> ts.get(key.position()));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultiFeatureKey key, V value) {
        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values.set(key.position(), value));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    public <V> void addValue(MultiFeatureKey key, V value) {
        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .orElse(newValue());

        while (key.position() > values.size()) {
            values.add(null);
        }

        if (key.position() < values.size() && isNull(values.get(key.position()))) {
            values.set(key.position(), value);
        }
        else {
            values.add(key.position(), value);
        }

        valueFor(key.withoutPosition(), values);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultiFeatureKey key) {
        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values.remove(key.position()));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    public <V> boolean containsValue(SingleFeatureKey key, V value) {
        return this.<List<V>>valueOf(key)
                .map(ts -> ts.contains(value))
                .orElse(false);
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(SingleFeatureKey key, V value) {
        return this.<List<V>>valueOf(key)
                .map(ts -> {
                    int index = ts.indexOf(value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(SingleFeatureKey key, V value) {
        return this.<List<V>>valueOf(key)
                .map(ts -> {
                    int index = ts.lastIndexOf(value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public <V> Iterable<V> valuesAsList(SingleFeatureKey key) {
        return this.<List<V>>valueOf(key)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);
    }

    @Nonnull
    @Override
    public <V> OptionalInt sizeOfValue(SingleFeatureKey key) {
        return this.<List<V>>valueOf(key)
                .map(ts -> OptionalInt.of(ts.size()))
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    public OptionalInt sizeOfReference(SingleFeatureKey key) {
        return sizeOfValue(key);
    }

    /**
     * Creates a new multi-value.
     *
     * @param <V> the type of the multi-value
     *
     * @return a new multi-value
     */
    @Nonnull
    private <V> List<V> newValue() {
        return new ArrayList<>();
    }
}
