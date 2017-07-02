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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static fr.inria.atlanmod.common.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link ManyValueMapper} that provides a default behavior to represent the "multi-valued" characteristic as
 * {@link List}s. The implementation used is specified by the {@link #getOrCreateList(SingleFeatureBean)} method.
 * <p>
 * Using a {@link List}-based implementation allows to benefit from the rich Java {@link java.util.Collection} API, with
 * the cost of a small memory overhead compared to raw arrays.
 */
@ParametersAreNonnullByDefault
public interface ManyValueWithLists extends ManyValueMapper {

    @Nonnull
    @Override
    default <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key);

        return this.<List<V>>valueOf(key.withoutPosition())
                .filter(values -> key.position() < values.size())
                .map(values -> values.get(key.position()));
    }

    @Nonnull
    @Override
    default <V> List<V> allValuesOf(SingleFeatureBean key) {
        return this.<List<V>>valueOf(key)
                .orElseGet(Collections::<V>emptyList);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values.set(key.position(), value));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    default <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .orElseGet(() -> getOrCreateList(key.withoutPosition()));

        checkPositionIndex(key.position(), values.size());

        values.add(key.position(), value);

        valueFor(key.withoutPosition(), values);
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

        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .orElseGet(() -> getOrCreateList(key.withoutPosition()));

        int firstPosition = key.position();
        checkPositionIndex(firstPosition, values.size());

        values.addAll(firstPosition, collection);

        valueFor(key.withoutPosition(), values);
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key);

        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
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

    /**
     * Gets or creates a new {@link List} to store the multi-valued features identified by the {@code key}.
     * <p>
     * By default, this method creates an {@link ArrayList} which favor random read access method, to the detriment of
     * insertions and deletions.
     *
     * @param key the key identifying the multi-valued attribute
     * @param <V> the type of elements in this list
     *
     * @return a new {@link List}
     */
    default <V> List<V> getOrCreateList(SingleFeatureBean key) {
        return new ArrayList<>();
    }
}
