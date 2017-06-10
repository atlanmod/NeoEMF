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

import fr.inria.atlanmod.common.collect.MoreArrays;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A {@link ManyReferenceMapper} that provides a default behavior to use {@link String} instead of {@link Id}[] for
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
    default List<Id> allReferencesOf(FeatureKey key) {
        return this.<String>valueOf(key)
                .map(this::arrayFromString)
                .map(Arrays::asList)
                .orElseGet(Collections::emptyList);
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
                .orElseGet(() -> new Id[0]);

        if (key.position() > ids.length) {
            ids = MoreArrays.resize(ids, key.position() + 1);
        }

        if (key.position() < ids.length && isNull(ids[key.position()])) {
            ids[key.position()] = reference;
        }
        else {
            ids = MoreArrays.add(ids, key.position(), reference);
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

        Optional<Id> previousId = Optional.empty();

        if (key.position() < ids.length) {
            previousId = Optional.of(ids[key.position()]);

            ids = MoreArrays.remove(ids, key.position());

            if (ids.length == 0) {
                removeAllReferences(key.withoutPosition());
            }
            else {
                valueFor(key.withoutPosition(), arrayToString(ids));
            }
        }

        return previousId;
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> indexOfReference(FeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        return this.<String>valueOf(key)
                .map(this::arrayFromString)
                .map(rs -> MoreArrays.indexOf(rs, reference))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        return this.<String>valueOf(key)
                .map(this::arrayFromString)
                .map(rs -> MoreArrays.lastIndexOf(rs, reference))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> sizeOfReference(FeatureKey key) {
        return this.<String>valueOf(key)
                .map(this::arrayFromString)
                .map(rs -> rs.length)
                .filter(s -> s != 0);
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
     * Converts a multi-valued reference as an {@link Id}[].
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
