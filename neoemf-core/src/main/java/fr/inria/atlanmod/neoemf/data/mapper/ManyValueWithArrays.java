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

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An object capable of mapping multi-valued attributes represented as a set of key/value pair.
 * <p>
 * It provides a default behavior to represent the "multi-valued" characteristic as arrays.
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithArrays extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Optional<V> valueOf(ManyFeatureKey key) {
        checkNotNull(key);

        return this.<V[]>valueOf(key.withoutPosition())
                .filter(values -> key.position() < values.length)
                .map(values -> values[key.position()]);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    default <V> Iterable<V> allValuesOf(FeatureKey key) {
        V[] values = this.<V[]>valueOf(key)
                .orElse((V[]) new Object[0]);

        return Arrays.asList(values);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = value;

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    default <V> void addValue(ManyFeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .orElse((V[]) new Object[0]);

        while (key.position() > values.length) {
            values = ArrayUtils.add(values, values.length, null);
        }

        if (key.position() < values.length && isNull(values[key.position()])) {
            values[key.position()] = value;
        }
        else {
            values = ArrayUtils.add(values, key.position(), value);
        }

        valueFor(key.withoutPosition(), values);
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureKey key) {
        checkNotNull(key);

        Optional<V[]> optionalValues = valueOf(key.withoutPosition());

        if (!optionalValues.isPresent()) {
            return Optional.empty();
        }

        V[] values = optionalValues.get();

        Optional<V> previousValue;
        if (key.position() < values.length) {
            previousValue = Optional.of(values[key.position()]);

            values = ArrayUtils.remove(values, key.position());

            if (values.length == 0) {
                removeAllValues(key.withoutPosition());
            }
            else {
                valueFor(key.withoutPosition(), values);
            }
        }
        else {
            previousValue = Optional.empty();
        }

        return previousValue;
    }

    @Override
    default <V> boolean containsValue(FeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return false;
        }

        return this.<V[]>valueOf(key)
                .map(values -> ArrayUtils.contains(values, value))
                .orElse(false);
    }

    @Nonnull
    @Override
    default <V> OptionalInt indexOfValue(FeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return OptionalInt.empty();
        }

        return this.<V[]>valueOf(key)
                .map(values -> {
                    int index = ArrayUtils.indexOf(values, value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default <V> OptionalInt lastIndexOfValue(FeatureKey key, @Nullable V value) {
        if (isNull(value)) {
            return OptionalInt.empty();
        }

        return this.<V[]>valueOf(key)
                .map(values -> {
                    int index = ArrayUtils.lastIndexOf(values, value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default <V> OptionalInt sizeOfValue(FeatureKey key) {
        return this.<V[]>valueOf(key)
                .map(values -> OptionalInt.of(values.length))
                .orElse(OptionalInt.empty());
    }
}
