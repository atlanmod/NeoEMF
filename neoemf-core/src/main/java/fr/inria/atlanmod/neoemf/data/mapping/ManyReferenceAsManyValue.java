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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

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
    default Optional<Id> referenceOf(ManyFeatureBean key) {
        return valueOf(key);
    }

    @Nonnull
    @Override
    default List<Id> allReferencesOf(SingleFeatureBean key) {
        return allValuesOf(key);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        return valueFor(key, reference);
    }

    @Override
    default boolean hasAnyReference(SingleFeatureBean key) {
        return hasAnyValue(key);
    }

    @Override
    default void addReference(ManyFeatureBean key, Id reference) {
        addValue(key, reference);
    }

    @Override
    default void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        addAllValues(key, collection);
    }

    @Nonnegative
    @Override
    default int appendReference(SingleFeatureBean key, Id reference) {
        return appendValue(key, reference);
    }

    @Nonnegative
    @Override
    default int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        return appendAllValues(key, collection);
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(ManyFeatureBean key) {
        return removeValue(key);
    }

    @Override
    default void removeAllReferences(SingleFeatureBean key) {
        removeAllValues(key);
    }

    @Nonnull
    @Override
    default Optional<Id> moveReference(ManyFeatureBean source, ManyFeatureBean target) {
        return moveValue(source, target);
    }

    @Override
    default boolean containsReference(SingleFeatureBean key, @Nullable Id reference) {
        return containsValue(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        return indexOfValue(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        return lastIndexOfValue(key, reference);
    }

    @Nonnull
    @Nonnegative
    @Override
    default Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return sizeOfValue(key);
    }
}
