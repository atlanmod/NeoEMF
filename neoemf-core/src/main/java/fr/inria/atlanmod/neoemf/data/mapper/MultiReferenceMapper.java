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
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object capable of mapping multi-valued references represented as a set of key/value pair.
 * <p>
 * By default, the references are processed as values with {@link MultiValueMapper}.
 */
@ParametersAreNonnullByDefault
public interface MultiReferenceMapper extends ReferenceMapper, MultiValueMapper {

    /**
     * Retrieves the reference of the specified {@code key} at a defined position.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return an {@link Optional} containing the reference, or {@link Optional#empty()} if the key hasn't any reference
     * or doesn't exist
     */
    @Nonnull
    default Optional<Id> referenceOf(ManyFeatureKey key) {
        return valueOf(key);
    }

    /**
     * Retrieves all references of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return an {@link Iterable} containing all references
     */
    @Nonnull
    default Iterable<Id> allReferencesOf(FeatureKey key) {
        return allValuesOf(key);
    }

    /**
     * Defines the {@code reference} of the specified {@code key} at a defined position.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to set
     *
     * @return an {@link Optional} containing the previous reference of the {@code key}, or {@link Optional#empty()} if
     * the key has no reference before
     *
     * @throws java.util.NoSuchElementException if the {@code key} doesn't exist
     * @see #addReference(ManyFeatureKey, Id)
     * @see #appendReference(FeatureKey, Id)
     */
    @Nonnull
    default Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return valueFor(key, reference);
    }

    /**
     * Unsets all references of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     */
    default void unsetAllReferences(FeatureKey key) {
        unsetAllValues(key);
    }

    /**
     * Checks whether the specified {@code key} has at least one defined reference.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return {@code true} if the {@code key} has a reference, {@code false} otherwise
     */
    default boolean hasAnyReference(FeatureKey key) {
        return hasAnyValue(key);
    }

    /**
     * Adds the {@code reference} to the specified {@code key} at a defined position. If {@code key#position > size}
     * then it creates {@code null} elements to respect the position.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to add
     */
    default void addReference(ManyFeatureKey key, Id reference) {
        addValue(key, reference);
    }

    /**
     * Adds the {@code reference} to the specified {@code key} at the last position.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to add
     */
    default void appendReference(FeatureKey key, Id reference) {
        addReference(key.withPosition(sizeOfReference(key).orElse(0)), reference);
    }

    /**
     * Removes the reference of the specified {@code key} at a defined position.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return an {@link Optional} containing the removed reference, or {@link Optional#empty()} if the key has no
     * reference before
     */
    @Nonnull
    default Optional<Id> removeReference(ManyFeatureKey key) {
        return removeValue(key);
    }

    /**
     * Removes all references of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     */
    default void removeAllReferences(FeatureKey key) {
        removeAllValues(key);
    }

    /**
     * Checks whether the specified {@code key} has the given {@code reference}.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to look for
     *
     * @return {@code true} if the {@code key} has the {@code reference}, {@code false} otherwise
     */
    default boolean containsReference(FeatureKey key, Id reference) {
        return containsValue(key, reference);
    }

    /**
     * Retrieves the first position of the {@code reference} of the specified {@code key}.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to look for
     *
     * @return an {@link OptionalInt} containing the first position of the {@code reference}, or {@link
     * OptionalInt#empty()} if the {@code key} hasn't the {@code reference}
     */
    @Nonnull
    default OptionalInt indexOfReference(FeatureKey key, Id reference) {
        return indexOfValue(key, reference);
    }

    /**
     * Retrieves the last position of the {@code reference} of the specified {@code key}.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to look for
     *
     * @return an {@link OptionalInt} containing the last position of the {@code reference}, or {@link
     * OptionalInt#empty()} if the {@code key} hasn't the {@code reference}
     */
    @Nonnull
    default OptionalInt lastIndexOfReference(FeatureKey key, Id reference) {
        return lastIndexOfValue(key, reference);
    }

    /**
     * Returns the number of reference of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return an {@link OptionalInt} containing the number of reference of the {@code key}, or {@link
     * OptionalInt#empty()} if the {@code key} hasn't any reference
     */
    @Nonnull
    default OptionalInt sizeOfReference(FeatureKey key) {
        return sizeOfValue(key);
    }
}
