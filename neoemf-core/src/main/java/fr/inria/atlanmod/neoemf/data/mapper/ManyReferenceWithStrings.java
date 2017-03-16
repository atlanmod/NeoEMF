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
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A {@link ManyReferenceMapper} that provides a default behavior to use {@link String} instead of {@link Id[]} for
 * multi-valued references. This behavior is specified by the {@link #arrayToString(Id[])} and
 * {@link #arrayFromString(String)} methods.
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceWithStrings extends ManyReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(ManyFeatureKey key) {
        return this.<String>valueOf(key.withoutPosition())
                .map(this::arrayFromString)
                .filter(ids -> key.position() < ids.length)
                .map(ids -> ids[key.position()]);
    }

    @Nonnull
    @Override
    default Iterable<Id> allReferencesOf(FeatureKey key) {
        Optional<Id[]> ids = this.<String>valueOf(key)
                .map(this::arrayFromString);

        return ids
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Id[] ids = this.<String>valueOf(key.withoutPosition())
                .map(this::arrayFromString)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<Id> previousId = Optional.of(ids[key.position()]);

        ids[key.position()] = reference;

        valueFor(key.withoutPosition(), arrayToString(ids));

        return previousId;
    }

    @Override
    default void addReference(ManyFeatureKey key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Id[] ids = this.<String>valueOf(key.withoutPosition())
                .map(this::arrayFromString)
                .orElse(new Id[0]);

        while (key.position() > ids.length) {
            ids = ArrayUtils.add(ids, ids.length, null);
        }

        if (key.position() < ids.length && isNull(ids[key.position()])) {
            ids[key.position()] = reference;
        }
        else {
            ids = ArrayUtils.add(ids, key.position(), reference);
        }

        valueFor(key.withoutPosition(), arrayToString(ids));
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(ManyFeatureKey key) {
        checkNotNull(key);

        Optional<String> optionalIds = valueOf(key.withoutPosition());

        if (!optionalIds.isPresent()) {
            return Optional.empty();
        }

        Id[] ids = arrayFromString(optionalIds.get());

        Optional<Id> previousId;
        if (key.position() < ids.length) {
            previousId = Optional.of(ids[key.position()]);

            ids = ArrayUtils.remove(ids, key.position());

            if (ids.length == 0) {
                removeAllReferences(key.withoutPosition());
            }
            else {
                valueFor(key.withoutPosition(), arrayToString(ids));
            }
        }
        else {
            previousId = Optional.empty();
        }

        return previousId;
    }

    @Override
    default boolean containsReference(FeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return false;
        }

        return this.<String>valueOf(key)
                .map(s -> ArrayUtils.contains(arrayFromString(s), reference))
                .orElse(false);
    }

    @Nonnull
    @Override
    default OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return OptionalInt.empty();
        }

        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.indexOf(arrayFromString(s), reference);
                    return index == NO_INDEX ? OptionalInt.empty() : OptionalInt.of(index);
                })
                .orElse(OptionalInt.empty());
    }

    @Nonnull
    @Override
    default OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return OptionalInt.empty();
        }

        return this.<String>valueOf(key)
                .map(s -> {
                    int index = ArrayUtils.lastIndexOf(arrayFromString(s), reference);
                    return index == NO_INDEX ? OptionalInt.empty() : OptionalInt.of(index);
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
                .map(id -> isNull(id) ? "" : id.toString())
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
                .map(r -> r.isEmpty() ? null : StringId.of(r))
                .toArray(Id[]::new);
    }
}
