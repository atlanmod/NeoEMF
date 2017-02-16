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

package fr.inria.atlanmod.neoemf.data.mapper;

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;

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
 * An object capable of mapping multi-valued attributes represented as a set of key/value pair.
 * <p>
 * It provides a default behavior to represent the "multi-valued" directly with their position.
 */
@ParametersAreNonnullByDefault
public interface MultiValueWithIndices extends MultiValueMapper {

    @Nonnull
    @Override
    default <V> Iterable<V> allValuesOf(FeatureKey key) {
        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .mapToObj(i -> this.<V>valueOf(key.withPosition(i)).orElse(null))
                .collect(Collectors.toList());
    }

    @Override
    default <V> void addValue(MultiFeatureKey key, V value) {
        int size = sizeOfValue(key.withoutPosition()).orElse(0);

        // TODO Replace by Stream
        for (int i = size - 1; i >= key.position(); i--) {
            Optional<V> movingValue = valueOf(key.withPosition(i));
            if (movingValue.isPresent()) {
                valueFor(key.withPosition(i + 1), movingValue.get());
            }
        }
        sizeFor(key.withoutPosition(), size + 1);

        valueFor(key, value);
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(MultiFeatureKey key) {
        Optional<V> previousValue = valueOf(key);

        int size = sizeOfValue(key.withoutPosition()).orElse(0);

        // Update indexes (element to remove is overwritten)
        // TODO Replace by Stream
        for (int i = key.position() + 1; i < size; i++) {
            Optional<V> movingValue = valueOf(key.withPosition(i));
            if (movingValue.isPresent()) {
                valueFor(key.withPosition(i - 1), movingValue.get());
            }
        }
        sizeFor(key.withoutPosition(), size - 1);

        return previousValue;
    }

    @Override
    default <V> boolean containsValue(FeatureKey key, V value) {
        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .anyMatch(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false));
    }

    @Nonnull
    @Override
    default <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .min();
    }

    @Nonnull
    @Override
    default <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .filter(i -> valueOf(key.withPosition(i)).map(v -> Objects.equals(v, value)).orElse(false))
                .max();
    }

    @Nonnull
    @Override
    default OptionalInt sizeOfValue(FeatureKey key) {
        return valueOf(key)
                .map(v -> OptionalInt.of((int) v))
                .orElse(OptionalInt.empty());
    }

    /**
     * Defines the number of values of the specified {@code key}.
     *
     * @param key  the key identifying the multi-valued attribute
     * @param size the number of values
     */
    default void sizeFor(FeatureKey key, @Nonnegative int size) {
        checkArgument(size >= 0);

        valueFor(key, size);
    }
}
