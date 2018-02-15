/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.collect.SizedIterator;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkGreaterThanOrEqualTo;
import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;

/**
 * A {@link ManyValueMapper} that provides a default behavior to represent the "multi-valued" directly with their
 * position.
 * <p>
 * Indices are persisted with dedicated {@link SingleFeatureBean}s containing the index of the element to store. Using
 * this approach avoid to deserialize entire {@link java.util.Collection}s to retrieve a single element, which can be an
 * important bottleneck in terms of execution time and memory consumption if the underlying model contains very large
 * {@link java.util.Collection}s.
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithIndices extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        final int size = sizeOfValue(feature).orElse(0);
        final Iterator<V> iter = new SizedIterator<>(size, i -> this.<V>valueOf(feature.withPosition(i)).orElse(null));

        return MoreIterables.stream(() -> iter);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        Optional<V> previousValue = valueOf(feature);
        if (!previousValue.isPresent()) {
            throw new NoSuchElementException();
        }

        valueForNullable(feature, value);

        return previousValue;
    }

    @Override
    default <V> void addValue(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        int size = sizeOfValue(feature.withoutPosition()).orElse(0);
        checkPositionIndex(feature.position(), size);

        for (int i = size - 1; i >= feature.position(); i--) {
            valueForNullable(feature.withPosition(i + 1), valueOf(feature.withPosition(i)).<IllegalStateException>orElseThrow(IllegalStateException::new));
        }

        sizeForValue(feature.withoutPosition(), size + 1);

        valueForNullable(feature, value);
    }

    @Override
    default <V> void addAllValues(ManyFeatureBean feature, List<? extends V> values) {
        checkNotNull(feature, "feature");
        checkNotNull(values, "values");
        checkNotContainsNull(values);

        int firstPosition = feature.position();

        IntStream.range(0, values.size())
                .forEachOrdered(i -> addValue(feature.withPosition(firstPosition + i), values.get(i)));
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        int size = sizeOfValue(feature.withoutPosition()).orElse(0);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = valueOf(feature);

        for (int i = feature.position(); i < size - 1; i++) {
            valueForNullable(feature.withPosition(i), valueOf(feature.withPosition(i + 1)).<IllegalStateException>orElseThrow(IllegalStateException::new));
        }

        valueForNullable(feature.withPosition(size - 1), null);

        sizeForValue(feature.withoutPosition(), size - 1);

        return previousValue;
    }

    @Override
    default void removeAllValues(SingleFeatureBean feature) {
        IntStream.range(0, sizeOfValue(feature).orElse(0))
                .forEachOrdered(i -> valueForNullable(feature.withPosition(i), null));

        removeValue(feature);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        return this.<Integer>valueOf(feature)
                .filter(s -> s > 0);
    }

    /**
     * Defines the number of values of the specified {@code feature}.
     *
     * @param feature the bean identifying the multi-valued attribute
     * @param size    the number of values
     *
     * @throws NullPointerException     if the {@code feature} is {@code null}
     * @throws IllegalArgumentException if {@code size < 0}
     */
    default void sizeForValue(SingleFeatureBean feature, @Nonnegative int size) {
        checkNotNull(feature, "feature");
        checkGreaterThanOrEqualTo(size, 0, "size (%d) must not be negative", size);

        if (size > 0) {
            valueFor(feature, size);
        }
        else {
            removeValue(feature);
        }
    }

    /**
     * Defines the {@code value} of the specified {@code feature} at a defined position.
     * <p>
     * This method behaves like {@link #valueFor(ManyFeatureBean, Object)}, without checking whether the multi-valued
     * feature already exists, in order to replace it.
     * If {@code value == null}, the feature is removed.
     *
     * @param feature the bean identifying the multi-valued attribute
     * @param value   the value to set
     * @param <V>     the type of value
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    <V> void valueForNullable(ManyFeatureBean feature, @Nullable V value);
}
