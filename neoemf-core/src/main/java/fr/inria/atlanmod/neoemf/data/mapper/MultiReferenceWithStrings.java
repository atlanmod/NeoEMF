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
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An object capable of mapping multi-valued references represented as a set of key/value pair.
 * <p>
 * It provides a default behavior to use {@link String} instead of {@link Id[]} for multi-valued references. This
 * behavior is specified by the {@link #arrayToString(Id[])} and {@link #arrayFromString(String)} methods.
 */
@ParametersAreNonnullByDefault
public interface MultiReferenceWithStrings extends MultiReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(ManyFeatureKey key) {
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
    default Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::arrayFromString)
                .orElseThrow(NoSuchElementException::new);

        Optional<Id> previousValue = Optional.of(values[key.position()]);

        values[key.position()] = reference;

        valueFor(key.withoutPosition(), arrayToString(values));

        return previousValue;
    }

    @Override
    default void addReference(ManyFeatureKey key, Id reference) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::arrayFromString)
                .orElse(new Id[0]);

        while (key.position() > values.length) {
            values = ArrayUtils.add(values, values.length, null);
        }

        if (key.position() < values.length && isNull(values[key.position()])) {
            values[key.position()] = reference;
        }
        else {
            values = ArrayUtils.add(values, key.position(), reference);
        }

        valueFor(key.withoutPosition(), arrayToString(values));
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(ManyFeatureKey key) {
        Id[] values = this.<String>valueOf(key.withoutPosition())
                .map(this::arrayFromString)
                .orElseThrow(NoSuchElementException::new);

        Optional<Id> previousValue = Optional.of(values[key.position()]);

        values = ArrayUtils.remove(values, key.position());

        if (values.length == 0) {
            removeAllReferences(key.withoutPosition());
        }
        else {
            valueFor(key.withoutPosition(), arrayToString(values));
        }

        return previousValue;
    }

    @Override
    default boolean containsReference(FeatureKey key, Id reference) {
        return this.<String>valueOf(key)
                .map(s -> ArrayUtils.contains(arrayFromString(s), reference))
                .orElse(false);
    }

    @Nonnull
    @Override
    default OptionalInt indexOfReference(FeatureKey key, Id reference) {
        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.indexOf(arrayFromString(s), reference);
                    return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default OptionalInt lastIndexOfReference(FeatureKey key, Id reference) {
        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.lastIndexOf(arrayFromString(s), reference);
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
     * Converts a multi-valued reference as a {@link String}.
     *
     * @param references the array containing all references of convert
     *
     * @return the converted multi-valued reference
     */
    default String arrayToString(Id[] references) {
        return Stream.of(checkNotNull(references))
                .map(Id::toString)
                .collect(Collectors.joining(","));
    }

    /**
     * Converts a multi-valued reference as an {@link Id[]}.
     *
     * @param references the multi-valued reference to convert
     *
     * @return an array containing all references
     */
    default Id[] arrayFromString(String references) {
        return Stream.of(checkNotNull(references).split(","))
                .map(StringId::of)
                .toArray(Id[]::new);
    }
}
