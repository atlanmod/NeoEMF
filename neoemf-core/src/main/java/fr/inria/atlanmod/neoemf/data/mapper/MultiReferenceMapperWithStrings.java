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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 *
 */
public interface MultiReferenceMapperWithStrings extends MultiValueMapperWithArrays, MultiReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(MultiFeatureKey key) {
        return this.<String>valueOf(key.withoutPosition())
                .map(s -> parseMulti(s)[key.position()]);
    }

    @Nonnull
    @Override
    default Iterable<Id> allReferencesOf(FeatureKey key) {
        Id[] values = this.<String>valueOf(key)
                .map(this::parseMulti)
                .orElseThrow(NoSuchElementException::new);

        return Arrays.asList(values);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(MultiFeatureKey key, Id id) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::parseMulti)
                .orElseThrow(NoSuchElementException::new);

        Optional<Id> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = id;

        valueFor(key.withoutPosition(), formatMulti(values));

        return previousValue;
    }

    @Override
    default void addReference(MultiFeatureKey key, Id id) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::parseMulti)
                .orElse(new Id[0]);

        while (key.position() > values.length) {
            values = ArrayUtils.add(values, values.length, null);
        }

        if (key.position() < values.length && isNull(values[key.position()])) {
            values[key.position()] = id;
        }
        else {
            values = ArrayUtils.add(values, key.position(), id);
        }

        valueFor(key.withoutPosition(), formatMulti(values));
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(MultiFeatureKey key) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::parseMulti)
                .orElseThrow(NoSuchElementException::new);

        Optional<Id> previousValue = Optional.of(values[key.position()]);

        valueFor(key.withoutPosition(), formatMulti(ArrayUtils.remove(values, key.position())));

        return previousValue;
    }

    @Override
    default boolean containsReference(FeatureKey key, Id id) {
        return this.<String>valueOf(key)
                .map(s -> ArrayUtils.contains(parseMulti(s), id))
                .orElse(false);
    }

    @Nonnull
    @Override
    default OptionalInt indexOfReference(FeatureKey key, Id id) {
        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.indexOf(parseMulti(s), id);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.lastIndexOf(parseMulti(s), id);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default OptionalInt sizeOfReference(FeatureKey key) {
        return this.<String>valueOf(key)
                .map(s -> OptionalInt.of(parseMulti(s).length))
                .orElse(OptionalInt.empty());
    }

    /**
     * ???
     *
     * @param values ???
     *
     * @return ???
     */
    default String formatMulti(Id[] values) {
        checkNotNull(values);

        return Stream.of(values)
                .map(Id::toString)
                .collect(Collectors.joining(","));
    }

    /**
     * ???
     *
     * @param value ???
     *
     * @return ???
     */
    default Id[] parseMulti(String value) {
        checkNotNull(value);

        return Stream.of(value.split(","))
                .map(StringId::of)
                .toArray(Id[]::new);
    }
}
