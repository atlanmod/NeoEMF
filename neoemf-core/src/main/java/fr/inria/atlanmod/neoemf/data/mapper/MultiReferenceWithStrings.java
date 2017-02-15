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
 * ???
 */
public interface MultiReferenceWithStrings extends MultiReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(MultiFeatureKey key) {
        return this.<String>valueOf(key.withoutPosition())
                .map(s -> arrayFromString(s)[key.position()]);
    }

    @Nonnull
    @Override
    default Iterable<Id> allReferencesOf(FeatureKey key) {
        Id[] values = this.<String>valueOf(key)
                .map(this::arrayFromString)
                .orElseThrow(NoSuchElementException::new);

        return Arrays.asList(values);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(MultiFeatureKey key, Id id) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::arrayFromString)
                .orElseThrow(NoSuchElementException::new);

        Optional<Id> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = id;

        valueFor(key.withoutPosition(), arrayToString(values));

        return previousValue;
    }

    @Override
    default void addReference(MultiFeatureKey key, Id id) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::arrayFromString)
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

        valueFor(key.withoutPosition(), arrayToString(values));
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(MultiFeatureKey key) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::arrayFromString)
                .orElseThrow(NoSuchElementException::new);

        Optional<Id> previousValue = Optional.of(values[key.position()]);

        valueFor(key.withoutPosition(), arrayToString(ArrayUtils.remove(values, key.position())));

        return previousValue;
    }

    @Override
    default boolean containsReference(FeatureKey key, Id id) {
        return this.<String>valueOf(key)
                .map(s -> ArrayUtils.contains(arrayFromString(s), id))
                .orElse(false);
    }

    @Nonnull
    @Override
    default OptionalInt indexOfReference(FeatureKey key, Id id) {
        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.indexOf(arrayFromString(s), id);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default OptionalInt lastIndexOfReference(FeatureKey key, Id id) {
        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.lastIndexOf(arrayFromString(s), id);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default OptionalInt sizeOfReference(FeatureKey key) {
        return this.<String>valueOf(key)
                .map(s -> OptionalInt.of(arrayFromString(s).length))
                .orElse(OptionalInt.empty());
    }

    /**
     * ???
     *
     * @param values ???
     *
     * @return ???
     */
    default String arrayToString(Id[] values) {
        return Stream.of(checkNotNull(values))
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
    default Id[] arrayFromString(String value) {
        return Stream.of(checkNotNull(value).split(","))
                .map(StringId::of)
                .toArray(Id[]::new);
    }
}
