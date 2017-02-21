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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

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
    default <V> void unsetAllValues(FeatureKey key) throws NullPointerException {
        IntStream.range(0, sizeOfValue(key).orElse(0))
                .forEach(i -> safeValueFor(key.withPosition(i), null));

        unsetValue(key);
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
                    valueFor(key.withPosition(i), movingValue.get());
                }
            }
        }

        sizeFor(key.withoutPosition(), size + 1);

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
                valueFor(key.withPosition(i), movingValue.get());
            }
        }

        safeValueFor(key.withPosition(size - 1), null);

        sizeFor(key.withoutPosition(), size - 1);

        return previousValue;
    }

    @Override
    default <V> void removeAllValues(FeatureKey key) {
        IntStream.range(0, sizeOfValue(key).orElse(0)).forEach(i -> removeValue(key.withPosition(i)));
    }

    @Override
    default <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return false;
        }

        return IntStream.range(0, sizeOfValue(key).orElse(0))
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
    default OptionalInt sizeOfValue(FeatureKey key) {
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
    default void sizeFor(FeatureKey key, @Nonnegative int size) {
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
     * already exists, in order to replace it.
     * <p>
     * If {@code value == null}, the key must be removed.
     *
     * @param key   the key identifying the multi-valued attribute
     * @param value the value to set
     * @param <V>   the type of value
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default <V> void safeValueFor(ManyFeatureKey key, @Nullable V value) throws NullPointerException {
        throw new IllegalStateException("This method should be overwritten if you use the default valueFor(ManyFeatureKey, V) and/or add(ManyFeatureKey, V) methods");
    }
}
