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

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;

import static java.util.Objects.isNull;

/**
 *
 */
public interface MultiValueMapperWithLists extends SingleValueMapper, MultiValueMapper {

    @Nonnull
    @Override
    default <V> Optional<V> valueOf(MultiFeatureKey key) {
        return this.<List<V>>valueOf(key.withoutPosition())
                .map(ts -> ts.get(key.position()));
    }

    @Nonnull
    @Override
    default <V> Iterable<V> allValuesOf(FeatureKey key) {
        return this.<List<V>>valueOf(key)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);
    }

    @Nonnull
    @Override
    default <V> Optional<V> valueFor(MultiFeatureKey key, V value) {
        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values.set(key.position(), value));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    default <V> void addValue(MultiFeatureKey key, V value) {
        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .orElse(new ArrayList<>());

        while (key.position() > values.size()) {
            values.add(null);
        }

        if (key.position() < values.size() && isNull(values.get(key.position()))) {
            values.set(key.position(), value);
        }
        else {
            values.add(key.position(), value);
        }

        valueFor(key.withoutPosition(), values);
    }

    @Nonnull
    @Override
    default <V> Optional<V> removeValue(MultiFeatureKey key) {
        List<V> values = this.<List<V>>valueOf(key.withoutPosition())
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<V> previousValue = Optional.of(values.remove(key.position()));

        valueFor(key.withoutPosition(), values);

        return previousValue;
    }

    @Override
    default <V> boolean containsValue(FeatureKey key, V value) {
        return this.<List<V>>valueOf(key)
                .map(ts -> ts.contains(value))
                .orElse(false);
    }

    @Nonnull
    @Override
    default <V> OptionalInt indexOfValue(FeatureKey key, V value) {
        return this.<List<V>>valueOf(key)
                .map(ts -> {
                    int index = ts.indexOf(value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default <V> OptionalInt lastIndexOfValue(FeatureKey key, V value) {
        return this.<List<V>>valueOf(key)
                .map(ts -> {
                    int index = ts.lastIndexOf(value);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default <V> OptionalInt sizeOfValue(FeatureKey key) {
        return this.<List<V>>valueOf(key)
                .map(ts -> OptionalInt.of(ts.size()))
                .orElse(OptionalInt.empty());
    }
}
