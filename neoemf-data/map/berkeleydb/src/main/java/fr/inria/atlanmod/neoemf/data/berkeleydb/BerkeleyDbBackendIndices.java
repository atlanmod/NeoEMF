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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * ???
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendIndices extends AbstractBerkeleyDbBackend {

    /**
     * A persistent map that store the values of multi-valued features for {@link Id}, identified by the associated
     * {@link MultivaluedFeatureKey}.
     */
    @Nonnull
    private final Database multivaluedFeatures;

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

        this.multivaluedFeatures = environment.openDatabase(null, "multivaluedFeatures", dbConfig);
    }

    @Override
    public void copyTo(BerkeleyDbBackend target) {
        BerkeleyDbBackendIndices backend = (BerkeleyDbBackendIndices) target;

        super.copyTo(backend);
        this.copyDatabaseTo(multivaluedFeatures, backend.multivaluedFeatures);
    }

    @Nonnull
    @Override
    protected List<Database> allDatabases() {
        List<Database> databases = super.allDatabases();
        databases.add(multivaluedFeatures);
        return databases;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(MultivaluedFeatureKey key) {
        return fromDatabase(multivaluedFeatures, key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(MultivaluedFeatureKey key, V value) {
        Optional<V> previousValue = valueOf(key);

        toDatabase(multivaluedFeatures, key, value);

        return previousValue;
    }

    @Override
    public <V> void addValue(MultivaluedFeatureKey key, V value) {
        int size = sizeOf(key.withoutPosition()).orElse(0);

        // TODO Replace by Stream
        for (int i = size - 1; i >= key.position(); i--) {
            valueFor(key.withPosition(i + 1), valueOf(key.withPosition(i)).orElse(null));
        }
        sizeFor(key.withoutPosition(), size + 1);

        valueFor(key, value);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(MultivaluedFeatureKey key) {
        Optional<V> previousValue = valueOf(key);

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
    public <V> boolean containsValue(FeatureKey key, V value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .anyMatch(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false));
    }

    @Nonnull
    @Override
    public <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .min();
    }

    @Nonnull
    @Override
    public <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .max();
    }

    @Nonnull
    @Override
    public <V> Iterable<V> valuesAsList(FeatureKey key) {
        return IntStream.range(0, sizeOf(key).orElse(0))
                .mapToObj(i -> this.<V>valueOf(key.withPosition(i)).orElse(null))
                .collect(Collectors.toList());
    }

    @Nonnull
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
    protected void sizeFor(FeatureKey key, @Nonnegative int size) {
        checkArgument(size >= 0);

        valueFor(key, size);
    }
}
