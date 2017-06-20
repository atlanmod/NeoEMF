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

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ManyReferenceMapper} that processes the references as values with {@link ManyValueMapper}.
 * <p>
 * All calls are redirected to their equivalent in {@link ManyValueMapper}.
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceAsManyValue extends ManyValueMapper, ManyReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(ManyFeatureKey key) {
        return valueOf(key);
    }

    @Nonnull
    @Override
    default List<Id> allReferencesOf(SingleFeatureKey key) {
        return allValuesOf(key);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return valueFor(key, reference);
    }

    @Override
    default boolean hasAnyReference(SingleFeatureKey key) {
        return hasAnyValue(key);
    }

    @Override
    default void addReference(ManyFeatureKey key, Id reference) {
        addValue(key, reference);
    }

    @Nonnegative
    @Override
    default int appendReference(SingleFeatureKey key, Id reference) {
        return appendValue(key, reference);
    }

    @Nonnegative
    @Override
    default int appendAllReferences(SingleFeatureKey key, List<Id> references) {
        return appendAllValues(key, references);
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(ManyFeatureKey key) {
        return removeValue(key);
    }

    @Override
    default void removeAllReferences(SingleFeatureKey key) {
        removeAllValues(key);
    }

    @Nonnull
    @Override
    default Optional<Id> moveReference(ManyFeatureKey source, ManyFeatureKey target) {
        return moveValue(source, target);
    }

    @Override
    default boolean containsReference(SingleFeatureKey key, @Nullable Id reference) {
        return containsValue(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> indexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        return indexOfValue(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> lastIndexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        return lastIndexOfValue(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> sizeOfReference(SingleFeatureKey key) {
        return sizeOfValue(key);
    }
}
