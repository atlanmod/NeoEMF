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

import fr.inria.atlanmod.commons.Converter;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link ManyValueMapper} that provides a default behavior to use {@link M} instead of a set of {@link Object} for
 * multi-valued attributes. This behavior is specified by the {@link #manyValuesConverter()} method.
 *
 * @param <M> the type of the multi-valued attribute after mapping
 */
@ParametersAreNonnullByDefault
public interface ManyValueWith<M> extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Optional<V> valueOf(ManyFeatureBean key) {
        Converter<List<V>, M> func = manyValuesConverter();

        return this.<M>valueOf(key.withoutPosition())
                .map(func::<V>doBackward)
                .filter(values -> key.position() < values.size())
                .map(values -> values.get(key.position()));
    }

    @Nonnull
    @Override
    default <V> List<V> allValuesOf(SingleFeatureBean key) {
        Converter<List<V>, M> func = manyValuesConverter();

        return this.<M>valueOf(key)
                .map(func::<V>doBackward)
                .orElseGet(Collections::<V>emptyList);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Converter<List<V>, M> func = manyValuesConverter();

        List<V> values = this.<M>valueOf(key.withoutPosition())
                .map(func::<V>doBackward)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values.set(key.position(), value));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    default <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        Converter<List<V>, M> func = manyValuesConverter();

        List<V> values = this.<M>valueOf(key.withoutPosition())
                .map(func::<V>doBackward)
                .orElseGet(ArrayList::new);

        values.add(key.position(), value);

        valueFor(key.withoutPosition(), func.doForward(values));
    }

    @Override
    default <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        Converter<List<V>, M> func = manyValuesConverter();

        List<V> values = this.<M>valueOf(key.withoutPosition())
                .map(func::doBackward)
                .orElseGet(ArrayList::new);

        int firstPosition = key.position();
        checkPositionIndex(firstPosition, values.size());

        values.addAll(firstPosition, collection);

        valueFor(key.withoutPosition(), func.doForward(values));
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key);

        Converter<List<V>, M> func = manyValuesConverter();

        List<V> values = this.<M>valueOf(key.withoutPosition())
                .map(func::<V>doBackward)
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
    default <V> Optional<Integer> indexOfValue(SingleFeatureBean key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        Converter<List<V>, M> func = manyValuesConverter();

        return this.<M>valueOf(key)
                .map(func::doBackward)
                .map(rs -> rs.indexOf(value))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default <V> Optional<Integer> lastIndexOfValue(SingleFeatureBean key, @Nullable V value) {
        if (isNull(value)) {
            return Optional.empty();
        }

        Converter<List<V>, M> func = manyValuesConverter();

        return this.<M>valueOf(key)
                .map(func::<V>doBackward)
                .map(rs -> rs.lastIndexOf(value))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default <V> Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        Converter<List<V>, M> func = manyValuesConverter();

        return this.<M>valueOf(key)
                .map(func::doBackward)
                .map(List::size)
                .filter(s -> s != 0);
    }

    <V> Converter<List<V>, M> manyValuesConverter();
}
