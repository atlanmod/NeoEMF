/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.atlanmod.commons.collect.MoreArrays;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static org.atlanmod.commons.Preconditions.checkNotContainsNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;
import static org.atlanmod.commons.Preconditions.checkPositionIndex;

/**
 * A {@link ManyValueMapper} that provides a default behavior to represent the "multi-valued" characteristic as {@link
 * Object}[].
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithArrays extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Optional<V> valueOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        return this.<V[]>valueOf(feature.withoutPosition())
                .filter(values -> feature.position() < values.length)
                .map(values -> values[feature.position()]);
    }

    @Nonnull
    @Override
    default <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        return this.<V[]>valueOf(feature)
                .map(Arrays::stream)
                .orElseGet(Stream::empty);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        V[] values = this.<V[]>valueOf(feature.withoutPosition()).orElse(null);

        if (isNull(values) || feature.position() >= values.length) {
            throw new IndexOutOfBoundsException();
        }

        Optional<V> previousValue = Optional.of(values[feature.position()]);

        values[feature.position()] = value;

        valueFor(feature.withoutPosition(), values);

        return previousValue;
    }

    @Override
    default <V> void addValue(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        V[] values = this.<V[]>valueOf(feature.withoutPosition())
                .orElseGet(() -> MoreArrays.newArray(Object.class, 0));

        checkPositionIndex(feature.position(), values.length);

        values = MoreArrays.add(values, feature.position(), value);

        valueFor(feature.withoutPosition(), values);
    }

    @Override
    default <V> void addAllValues(ManyFeatureBean feature, List<? extends V> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection, "collection");

        if (collection.isEmpty()) {
            return;
        }

        V[] valuesArray = this.<V[]>valueOf(feature.withoutPosition())
                .orElseGet(() -> MoreArrays.newArray(Object.class, 0));

        int firstPosition = feature.position();
        checkPositionIndex(firstPosition, valuesArray.length);

        valuesArray = MoreArrays.addAll(valuesArray, firstPosition, collection);

        valueFor(feature.withoutPosition(), valuesArray);
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        V[] values = this.<V[]>valueOf(feature.withoutPosition())
                .orElse(null);

        if (isNull(values)) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.empty();

        if (feature.position() < values.length) {
            previousValue = Optional.of(values[feature.position()]);

            values = MoreArrays.remove(values, feature.position());

            if (values.length == 0) {
                removeAllValues(feature.withoutPosition());
            }
            else {
                valueFor(feature.withoutPosition(), values);
            }
        }

        return previousValue;
    }

    @Override
    default void removeAllValues(SingleFeatureBean feature) {
        this.removeValue(feature);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        return this.<Object[]>valueOf(feature)
                .map(a -> a.length)
                .filter(s -> s > 0);
    }
}
