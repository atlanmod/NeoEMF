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
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A {@link ManyReferenceMapper} that provides a default behavior to use {@link M} instead of a set of {@link Id} for
 * multi-valued references. This behavior is specified by the {@link #manyReferencesMapping()} method.
 *
 * @param <M> the type of the multi-valued reference after mapping
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceWith<M> extends ManyValueMapper, ManyReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(ManyFeatureKey key) {
        MappingFunction<List<Id>, M> func = manyReferencesMapping();

        return this.<M>valueOf(key.withoutPosition())
                .map(func::unmap)
                .filter(ids -> key.position() < ids.size())
                .map(ids -> ids.get(key.position()));
    }

    @Nonnull
    @Override
    default List<Id> allReferencesOf(SingleFeatureKey key) {
        MappingFunction<List<Id>, M> func = manyReferencesMapping();

        return this.<M>valueOf(key)
                .map(func::unmap)
                .orElseGet(Collections::emptyList);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        MappingFunction<List<Id>, M> func = manyReferencesMapping();

        List<Id> ids = this.<M>valueOf(key.withoutPosition())
                .map(func::unmap)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<Id> previousId = Optional.of(ids.get(key.position()));

        ids.set(key.position(), reference);

        valueFor(key.withoutPosition(), func.map(ids));

        return previousId;
    }

    @Override
    default void addReference(ManyFeatureKey key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        MappingFunction<List<Id>, M> func = manyReferencesMapping();

        List<Id> ids = this.<M>valueOf(key.withoutPosition())
                .map(func::unmap)
                .orElseGet(ArrayList::new);

        if (key.position() > ids.size()) {
            ids.addAll(IntStream.range(ids.size(), key.position() + 1)
                    .mapToObj(i -> (Id) null)
                    .collect(Collectors.toList()));
        }

        if (key.position() < ids.size() && isNull(ids.get(key.position()))) {
            ids.set(key.position(), reference);
        }
        else {
            ids.add(key.position(), reference);
        }

        valueFor(key.withoutPosition(), func.map(ids));
    }

    @Override
    default void addAllReferences(ManyFeatureKey key, List<Id> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        int firstPosition = key.position();

        MappingFunction<List<Id>, M> func = manyReferencesMapping();

        List<Id> ids = this.<M>valueOf(key.withoutPosition())
                .map(func::unmap)
                .orElseGet(ArrayList::new);

        if (firstPosition > ids.size()) {
            ids.addAll(IntStream.range(ids.size(), firstPosition + 1)
                    .mapToObj(i -> (Id) null)
                    .collect(Collectors.toList()));
        }

        ids.addAll(firstPosition, collection);

        valueFor(key.withoutPosition(), func.map(ids));
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(ManyFeatureKey key) {
        checkNotNull(key);

        MappingFunction<List<Id>, M> func = manyReferencesMapping();

        List<Id> ids = this.<M>valueOf(key.withoutPosition())
                .map(func::unmap)
                .orElse(null);

        if (isNull(ids)) {
            return Optional.empty();
        }

        Optional<Id> previousId = Optional.empty();

        if (key.position() < ids.size()) {
            previousId = Optional.of(ids.remove(key.position()));

            if (ids.isEmpty()) {
                removeAllReferences(key.withoutPosition());
            }
            else {
                valueFor(key.withoutPosition(), func.map(ids));
            }
        }

        return previousId;
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> indexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        MappingFunction<List<Id>, M> func = manyReferencesMapping();

        return this.<M>valueOf(key)
                .map(func::unmap)
                .map(ids -> ids.indexOf(reference))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> lastIndexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        MappingFunction<List<Id>, M> func = manyReferencesMapping();

        return this.<M>valueOf(key)
                .map(func::unmap)
                .map(ids -> ids.lastIndexOf(reference))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> sizeOfReference(SingleFeatureKey key) {
        MappingFunction<List<Id>, M> func = manyReferencesMapping();

        return this.<M>valueOf(key)
                .map(func::unmap)
                .map(List::size)
                .filter(s -> s != 0);
    }

    @Nonnull
    MappingFunction<List<Id>, M> manyReferencesMapping();
}
