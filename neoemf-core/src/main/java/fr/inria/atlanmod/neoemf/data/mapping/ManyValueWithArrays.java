/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.collect.MoreArrays;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link ManyValueMapper} that provides a default behavior to represent the "multi-valued" characteristic as {@link
 * Object}[].
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithArrays extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return this.<V[]>valueOf(key.withoutPosition())
                .filter(values -> key.position() < values.length)
                .map(values -> values[key.position()]);
    }

    @Nonnull
    @Override
    default <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        return this.<V[]>valueOf(key)
                .map(Arrays::stream)
                .orElseGet(Stream::empty);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = value;

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    default <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .orElseGet(() -> MoreArrays.newArray(Object.class, 0));

        checkPositionIndex(key.position(), values.length);

        values = MoreArrays.add(values, key.position(), value);

        valueFor(key.withoutPosition(), values);
    }

    @Override
    default <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .orElseGet(() -> MoreArrays.newArray(Object.class, 0));

        int firstPosition = key.position();
        checkPositionIndex(firstPosition, values.length);

        values = MoreArrays.addAll(values, firstPosition, collection);

        valueFor(key.withoutPosition(), values);
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        V[] values = this.<V[]>valueOf(key.withoutPosition())
                .orElse(null);

        if (isNull(values)) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.empty();

        if (key.position() < values.length) {
            previousValue = Optional.of(values[key.position()]);

            values = MoreArrays.remove(values, key.position());

            if (values.length == 0) {
                removeAllValues(key.withoutPosition());
            }
            else {
                valueFor(key.withoutPosition(), values);
            }
        }

        return previousValue;
    }

    @Override
    default void removeAllValues(SingleFeatureBean key) {
        this.removeValue(key);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        return this.<Object[]>valueOf(key)
                .map(a -> a.length)
                .filter(s -> s > 0);
    }
}
