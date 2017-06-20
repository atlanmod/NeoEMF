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

import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A {@link ManyValueMapper} that provides a default behavior to use {@link M} instead of a set of {@link Object} for
 * multi-valued attributes. This behavior is specified by the {@link #manyValuesMapping()} method.
 *
 * @param <M> the type of the multi-valued attribute after mapping
 */
@ParametersAreNonnullByDefault
public interface ManyValueWith<M> extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Optional<V> valueOf(ManyFeatureKey key) {
        MappingFunction<List<V>, M> func = manyValuesMapping();

        return this.<M>valueOf(key.withoutPosition())
                .map(func::<V>unmap)
                .filter(values -> key.position() < values.size())
                .map(values -> values.get(key.position()));
    }

    @Nonnull
    @Override
    default <V> List<V> allValuesOf(SingleFeatureKey key) {
        MappingFunction<List<V>, M> func = manyValuesMapping();

        return this.<M>valueOf(key)
                .map(func::<V>unmap)
                .orElseGet(Collections::<V>emptyList);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        MappingFunction<List<V>, M> func = manyValuesMapping();

        List<V> values = this.<M>valueOf(key.withoutPosition())
                .map(func::<V>unmap)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values.set(key.position(), value));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    default <V> void addValue(ManyFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        MappingFunction<List<V>, M> func = manyValuesMapping();

        List<V> values = this.<M>valueOf(key.withoutPosition())
                .map(func::<V>unmap)
                .orElseGet(ArrayList::new);

        if (key.position() > values.size()) {
            values.addAll(IntStream.range(values.size(), key.position() + 1)
                    .mapToObj(i -> (V) null)
                    .collect(Collectors.toList()));
        }

        if (key.position() < values.size() && isNull(values.get(key.position()))) {
            values.set(key.position(), value);
        }
        else {
            values.add(key.position(), value);
        }

        valueFor(key.withoutPosition(), func.map(values));
    }

    @Override
    default <V> int appendAllValues(SingleFeatureKey key, List<? extends V> values) {
        checkNotNull(key);
        checkNotNull(values);

        int firstPosition = sizeOfValue(key).orElse(0);

        MappingFunction<List<V>, M> func = manyValuesMapping();

        List<V> vs = this.<M>valueOf(key)
                .map(func::unmap)
                .orElseGet(ArrayList::new);

        vs.addAll(values);

        valueFor(key, func.map(vs));

        return firstPosition;
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureKey key) {
        checkNotNull(key);

        MappingFunction<List<V>, M> func = manyValuesMapping();

        List<V> values = this.<M>valueOf(key.withoutPosition())
                .map(func::<V>unmap)
                .orElse(null);

        if (isNull(values)) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.empty();

        if (key.position() < values.size()) {
            previousValue = Optional.of(values.remove(key.position()));

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
    default <V> Optional<Integer> indexOfValue(SingleFeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        MappingFunction<List<V>, M> func = manyValuesMapping();

        return this.<M>valueOf(key)
                .map(func::unmap)
                .map(rs -> rs.indexOf(value))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default <V> Optional<Integer> lastIndexOfValue(SingleFeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        MappingFunction<List<V>, M> func = manyValuesMapping();

        return this.<M>valueOf(key)
                .map(func::<V>unmap)
                .map(rs -> rs.lastIndexOf(value))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default <V> Optional<Integer> sizeOfValue(SingleFeatureKey key) {
        MappingFunction<List<V>, M> func = manyValuesMapping();

        return this.<M>valueOf(key)
                .map(func::unmap)
                .map(List::size)
                .filter(s -> s != 0);
    }

    <V> MappingFunction<List<V>, M> manyValuesMapping();
}
