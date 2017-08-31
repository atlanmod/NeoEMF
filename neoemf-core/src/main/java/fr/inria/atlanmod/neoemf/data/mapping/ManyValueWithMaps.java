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

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;

/**
 * A {@link ManyValueMapper} that provides a default behavior to represent the "multi-valued" characteristic as {@link
 * SortedMap}s. The implementation used is specified by the {@link #getOrCreateMap(SingleFeatureBean)} method.
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithMaps extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key);

        return this.<SortedMap<Integer, V>>valueOf(key.withoutPosition())
                .filter(m -> key.position() < (m.isEmpty() ? 0 : m.lastKey()) + 1)
                .map(values -> values.get(key.position()));
    }

    @Nonnull
    @Override
    default <V> List<V> allValuesOf(SingleFeatureBean key) {
        return this.<SortedMap<Integer, V>>valueOf(key)
                .map(m -> IntStream.range(0, m.isEmpty() ? 0 : m.lastKey() + 1)
                        .mapToObj(m::get)
                        .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        SortedMap<Integer, V> values = this.<SortedMap<Integer, V>>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values.put(key.position(), value));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    default <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        SortedMap<Integer, V> values = this.<SortedMap<Integer, V>>valueOf(key.withoutPosition())
                .orElseGet(() -> getOrCreateMap(key.withoutPosition()));

        int size = values.isEmpty() ? 0 : values.lastKey() + 1;
        checkPositionIndex(key.position(), size);

        for (int i = size - 1; i >= key.position(); i--) {
            values.put(i + 1, values.get(i));
        }

        values.put(key.position(), value);

        valueFor(key.withoutPosition(), values);
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key);

        Optional<SortedMap<Integer, V>> optionalValues = valueOf(key.withoutPosition());

        if (!optionalValues.isPresent()) {
            return Optional.empty();
        }

        SortedMap<Integer, V> values = optionalValues.get();

        int size = values.isEmpty() ? 0 : values.lastKey() + 1;

        Optional<V> previousValue = Optional.empty();

        if (key.position() < size) {
            previousValue = Optional.of(values.get(key.position()));

            for (int i = key.position(); i < size - 1; i++) {
                values.put(i, values.get(i + 1));
            }

            values.remove(size - 1);

            if (values.isEmpty()) {
                removeAllValues(key.withoutPosition());
            }
            else {
                valueFor(key.withoutPosition(), values);
            }
        }

        return previousValue;
    }

    @Nonnull
    @Nonnegative
    @Override
    default <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        return this.<SortedMap<Integer, V>>valueOf(key)
                .filter(m -> !m.isEmpty())
                .map(m -> m.lastKey() + 1)
                .filter(s -> s != 0);
    }

    /**
     * Gets or creates a new {@link Map} to store the multi-valued features identified by the {@code key}.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of elements in this list
     *
     * @return a new {@link Map}
     */
    default <V> SortedMap<Integer, V> getOrCreateMap(SingleFeatureBean key) {
        return new TreeMap<>();
    }
}
