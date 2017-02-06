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

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ???
 */
@Experimental
class BerkeleyDbBackendIndices extends AbstractBerkeleyDbBackend {

    /**
     * A persistent map that store the values of multi-valued features for {@link Id}, identified by the associated
     * {@link MultivaluedFeatureKey}.
     */
    private Database multivaluedFeatures;

    /**
     * Constructs a new {@code BerkeleyDbBackendIndices} on the given {@code file} with the given
     * {@code envConfig}.
     *
     * @param file      ???
     * @param envConfig ???
     * @param dbConfig  ???
     *
     * @note This constructor is protected. To create a new {@code BerkeleyDbBackendIndices} use {@link
     * BerkeleyDbBackendFactory#createPersistentBackend(java.io.File, Map)}.
     */
    protected BerkeleyDbBackendIndices(File file, EnvironmentConfig envConfig, DatabaseConfig dbConfig) {
        super(file, envConfig, dbConfig);
    }

    @Override
    public void open() {
        super.open();

        this.multivaluedFeatures = environment.openDatabase(null, "multivaluedFeatures", databaseConfig);
    }

    @Override
    public void copyTo(BerkeleyDbBackend target) {
        BerkeleyDbBackendIndices backend = (BerkeleyDbBackendIndices) target;

        super.copyTo(backend);
        this.copyDatabaseTo(multivaluedFeatures, backend.multivaluedFeatures);
    }

    @Override
    protected List<Database> allDatabases() {
        List<Database> databases = super.allDatabases();
        databases.add(multivaluedFeatures);
        return databases;
    }

    @Override
    public <T> Optional<T> valueOf(MultivaluedFeatureKey key) {
        return fromDatabase(multivaluedFeatures, key);
    }

    @Override
    public <T> Optional<T> valueFor(MultivaluedFeatureKey key, T value) {
        Optional<T> previousValue = valueOf(key);

        toDatabase(multivaluedFeatures, key, value);

        return previousValue;
    }

    @Override
    public <T> void addValue(MultivaluedFeatureKey key, T value) {
        int size = sizeOf(key.withoutPosition()).orElse(0);

        // TODO Replace by Stream
        for (int i = size - 1; i >= key.position(); i--) {
            valueFor(key.withPosition(i + 1), valueOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size + 1);

        valueFor(key, value);
    }

    @Override
    public <T> Optional<T> removeValue(MultivaluedFeatureKey key) {
        Optional<T> previousValue = valueOf(key);

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
    public <T> boolean containsValue(FeatureKey key, T value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .anyMatch(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false));
    }

    @Override
    public <T> OptionalInt indexOfValue(FeatureKey key, T value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .min();
    }

    @Override
    public <T> OptionalInt lastIndexOfValue(FeatureKey key, T value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .max();
    }

    @Override
    public <T> Iterable<T> valuesAsList(FeatureKey key) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .mapToObj(i -> this.<T>valueOf(key.withPosition(i)).orElse(null))
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

    /**
     * Defines the {@code size} of the given {@code key}.
     *
     * @param key  the key
     * @param size the new size
     */
    protected void sizeFor(FeatureKey key, int size) {
        valueFor(key, size);
    }
}
