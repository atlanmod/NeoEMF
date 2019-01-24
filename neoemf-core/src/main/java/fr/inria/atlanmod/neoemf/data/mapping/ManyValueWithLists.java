/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static org.atlanmod.commons.Preconditions.checkNotContainsNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;
import static org.atlanmod.commons.Preconditions.checkPositionIndex;

/**
 * A {@link ManyValueMapper} that provides a default behavior to represent the "multi-valued" characteristic as {@link
 * List}s. The implementation used is specified by the {@link #getOrCreateList(SingleFeatureBean)} method.
 * <p>
 * Using a {@link List}-based implementation allows to benefit from the rich Java {@link java.util.Collection} API, with
 * the cost of a small memory overhead compared to raw arrays.
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithLists extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Optional<V> valueOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        return this.<List<V>>valueOf(feature.withoutPosition())
                .filter(values -> feature.position() < values.size())
                .map(values -> values.get(feature.position()));
    }

    @Nonnull
    @Override
    default <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        return this.<List<V>>valueOf(feature)
                .map(List::stream)
                .orElseGet(Stream::empty);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        List<V> values = this.<List<V>>valueOf(feature.withoutPosition()).orElse(null);

        if (isNull(values) || feature.position() >= values.size()) {
            throw new IndexOutOfBoundsException();
        }

        Optional<V> previousValue = Optional.of(values.set(feature.position(), value));

        valueFor(feature.withoutPosition(), values);

        return previousValue;
    }

    @Override
    default <V> void addValue(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        List<V> values = this.<List<V>>valueOf(feature.withoutPosition())
                .orElseGet(() -> getOrCreateList(feature.withoutPosition()));

        checkPositionIndex(feature.position(), values.size());

        values.add(feature.position(), value);

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

        List<V> valuesList = this.<List<V>>valueOf(feature.withoutPosition())
                .orElseGet(() -> getOrCreateList(feature.withoutPosition()));

        int firstPosition = feature.position();
        checkPositionIndex(firstPosition, valuesList.size());

        valuesList.addAll(firstPosition, collection);

        valueFor(feature.withoutPosition(), valuesList);
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        List<V> values = this.<List<V>>valueOf(feature.withoutPosition())
                .orElse(null);

        if (isNull(values)) {
            return Optional.empty();
        }

        Optional<V> previousValue = Optional.empty();

        if (feature.position() < values.size()) {
            previousValue = Optional.of(values.remove(feature.position()));

            if (values.isEmpty()) {
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
        removeValue(feature);
    }

    @Nonnull
    @Override
    default Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        return this.<List<Object>>valueOf(feature)
                .map(List::size)
                .filter(s -> s > 0);
    }

    /**
     * Gets or creates a new {@link List} to store the multi-valued features identified by the {@code feature}.
     * <p>
     * By default, this method creates an {@link ArrayList} which favor random read access method, to the detriment of
     * insertions and deletions.
     *
     * @param feature the bean identifying the multi-valued attribute
     * @param <V>     the type of elements in this list
     *
     * @return a new {@link List}
     */
    default <V> List<V> getOrCreateList(SingleFeatureBean feature) {
        return new ArrayList<>();
    }
}
