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
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An object capable of mapping multi-valued attributes represented as a set of key/value pair.
 * <p>
 * It provides a default behavior to represent the "multi-valued" directly with their position.
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithIndices extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Iterable<V> allValuesOf(FeatureKey key) {
        return IntStream.range(0, sizeOfValue(key).orElse(0))
                .mapToObj(i -> this.<V>valueOf(key.withPosition(i)).orElse(null))
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Optional<V> previousValue = valueOf(key);
        if (!previousValue.isPresent()) {
            throw new NoSuchElementException();
        }

        safeValueFor(key, value);

        return previousValue;
    }

    @Override
    default <V> void addValue(ManyFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        int size = sizeOfValue(key.withoutPosition()).orElse(0);

        if (key.position() >= size || valueOf(key).isPresent()) {
            for (int i = size; i > key.position(); i--) {
                Optional<V> movingValue = valueOf(key.withPosition(i - 1));
                if (movingValue.isPresent()) {
                    safeValueFor(key.withPosition(i), movingValue.get());
                }
            }
        }

        sizeForValue(key.withoutPosition(), size + 1);

        safeValueFor(key, value);
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureKey key) {
        checkNotNull(key);

        int size = sizeOfValue(key.withoutPosition()).orElse(0);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = valueOf(key);

        for (int i = key.position(); i < size - 1; i++) {
            Optional<V> movingValue = valueOf(key.withPosition(i + 1));
            if (movingValue.isPresent()) {
                safeValueFor(key.withPosition(i), movingValue.get());
            }
        }

        safeValueFor(key.withPosition(size - 1), null);

        sizeForValue(key.withoutPosition(), size - 1);

        return previousValue;
    }

    @Override
    default <V> void removeAllValues(FeatureKey key) {
        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> safeValueFor(key.withPosition(i), null));

        unsetValue(key);
    }

    @Override
    default <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        return nonNull(value) && IntStream.range(0, sizeOfValue(key).orElse(0))
                .anyMatch(i -> valueOf(key.withPosition(i))
                        .map(v -> Objects.equals(v, value))
                        .orElse(false));
    }

    @Nonnull
    @Override
    default <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return OptionalInt.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        for (int i = 0; i < size; i++) {
            if (valueOf(key.withPosition(i)).filter(v -> Objects.equals(v, value)).isPresent()) {
                return OptionalInt.of(i);
            }
        }

        return OptionalInt.empty();
    }

    @Nonnull
    @Override
    default <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return OptionalInt.empty();
        }

        int size = sizeOfValue(key).orElse(0);
        for (int i = size - 1; i > 0; i--) {
            if (valueOf(key.withPosition(i)).filter(v -> Objects.equals(v, value)).isPresent()) {
                return OptionalInt.of(i);
            }
        }

        return OptionalInt.empty();
    }

    @Nonnull
    @Override
    default <V> OptionalInt sizeOfValue(FeatureKey key) {
        checkNotNull(key);

        return valueOf(key)
                .map(v -> OptionalInt.of((int) v))
                .orElse(OptionalInt.empty());
    }

    /**
     * Defines the number of values of the specified {@code key}.
     *
     * @param key  the key identifying the multi-valued attribute
     * @param size the number of values
     *
     * @throws NullPointerException     if the {@code key} is {@code null}
     * @throws IllegalArgumentException if {@code size < 0}
     */
    default <V> void sizeForValue(FeatureKey key, @Nonnegative int size) {
        checkNotNull(key);
        checkArgument(size >= 0);

        if (size > 0) {
            valueFor(key, size);
        }
        else {
            unsetValue(key);
        }
    }

    /**
     * Defines the {@code value} of the specified {@code key} at a defined position.
     * <p>
     * This method acts as {@link #valueFor(ManyFeatureKey, Object)}, without checking whether the multi-valued feature
     * already exists, in order to replace it. If {@code value == null}, the key is removed.
     * <p>
     * <b>Note:</b> This method is used by the default {@link #valueFor(FeatureKey, Object)},
     * {@link #addValue(ManyFeatureKey, Object)} and {@link #removeValue(ManyFeatureKey)} methods. If you intend to use
     * them, you have to override it.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to set
     * @param <V>   the type of value
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default <V> void safeValueFor(ManyFeatureKey key, @Nullable V value) {
        throw new IllegalStateException("This method should be overwritten.");
    }
}
