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

import fr.inria.atlanmod.commons.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link ManyReferenceMapper} that provides a default behavior to use {@link M} instead of a set of {@link Id} for
 * multi-valued references.
 * <p>
 * This mapper merges the multi-valued references into a single value.
 *
 * @param <M> the type of the multi-valued reference after mapping
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceMergedAs<M> extends ManyValueMapper, ManyReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(ManyFeatureBean key) {
        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .filter(ids -> key.position() < ids.size())
                .map(ids -> ids.get(key.position()));
    }

    @Nonnull
    @Override
    default List<Id> allReferencesOf(SingleFeatureBean key) {
        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key)
                .map(converter::revert)
                .orElseGet(Collections::emptyList);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Converter<List<Id>, M> converter = manyReferenceMerger();

        List<Id> ids = this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        Optional<Id> previousId = Optional.of(ids.get(key.position()));

        ids.set(key.position(), reference);

        valueFor(key.withoutPosition(), converter.convert(ids));

        return previousId;
    }

    @Override
    default void addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key);
        checkNotNull(reference);

        Converter<List<Id>, M> converter = manyReferenceMerger();

        List<Id> ids = this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .orElseGet(ArrayList::new);

        checkPositionIndex(key.position(), ids.size());

        ids.add(key.position(), reference);

        valueFor(key.withoutPosition(), converter.convert(ids));
    }

    @Override
    default void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        if (collection.contains(null)) {
            throw new NullPointerException();
        }

        Converter<List<Id>, M> converter = manyReferenceMerger();

        List<Id> ids = this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .orElseGet(ArrayList::new);

        int firstPosition = key.position();
        checkPositionIndex(firstPosition, ids.size());

        ids.addAll(firstPosition, collection);

        valueFor(key.withoutPosition(), converter.convert(ids));
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(ManyFeatureBean key) {
        checkNotNull(key);

        Converter<List<Id>, M> converter = manyReferenceMerger();

        List<Id> ids = this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
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
                valueFor(key.withoutPosition(), converter.convert(ids));
            }
        }

        return previousId;
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key)
                .map(converter::revert)
                .map(ids -> ids.indexOf(reference))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key)
                .map(converter::revert)
                .map(ids -> ids.lastIndexOf(reference))
                .filter(i -> i >= 0);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key)
                .map(converter::revert)
                .map(List::size)
                .filter(s -> s != 0);
    }

    /**
     * Returns the converter used to transform a ordered list of references to the desired type.
     *
     * @return the conveter
     */
    @Nonnull
    Converter<List<Id>, M> manyReferenceMerger();
}
