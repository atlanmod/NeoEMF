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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * An object capable of mapping multi-valued references represented as a set of key/value pair.
 * <p>
 * By default, the references are processed as values with {@link ManyValueMapper}, except for composed methods such as
 * {@link #appendReference(FeatureKey, Id)} and {@link #appendAllReferences(FeatureKey, List)}.
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceMapper extends ReferenceMapper, ManyValueMapper {

    /**
     * Retrieves the reference of the specified {@code key} at a defined position.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return an {@link Optional} containing the reference, or {@link Optional#empty()} if the key hasn't any reference
     * or doesn't exist
     *
     * @throws NullPointerException if the {@code key} is {@code null}
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
     * @return an immutable {@link List} containing all references
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    default List<Id> allReferencesOf(FeatureKey key) {
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
     * @throws NoSuchElementException if the {@code key} doesn't exist
     * @throws NullPointerException   if any parameter is {@code null}
     * @see #addReference(ManyFeatureKey, Id)
     * @see #appendReference(FeatureKey, Id)
     */
    @Nonnull
    default Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return valueFor(key, reference);
    }

    /**
     * Checks whether the specified {@code key} has at least one defined reference.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return {@code true} if the {@code key} has a reference, {@code false} otherwise
     *
     * @throws NullPointerException if the {@code key} is {@code null}
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
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    default void addReference(ManyFeatureKey key, Id reference) {
        addValue(key, reference);
    }

    /**
     * Adds the {@code reference} to the specified {@code key} at the last position.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to add
     *
     * @return the position to which the reference was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addReference(ManyFeatureKey, Id)
     */
    @Nonnegative
    default int appendReference(FeatureKey key, Id reference) {
        checkNotNull(key);

        int position = sizeOfReference(key).orElse(0);

        addReference(key.withPosition(position), reference);

        return position;
    }

    /**
     * Adds all {@code references} to the specified {@code key} from the last position.
     *
     * @param key        the key identifying the multi-valued reference
     * @param references the references to add
     *
     * @return the position to which the first reference was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addReference(ManyFeatureKey, Id)
     * @see #appendReference(FeatureKey, Id)
     */
    @Nonnegative
    default int appendAllReferences(FeatureKey key, List<Id> references) {
        checkNotNull(key);
        checkNotNull(references);

        int firstPosition = sizeOfReference(key).orElse(0);

        IntStream.range(0, references.size())
                .forEach(i -> addReference(key.withPosition(firstPosition + i), references.get(i)));

        return firstPosition;
    }

    /**
     * Removes the reference of the specified {@code key} at a defined position.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return an {@link Optional} containing the removed reference, or {@link Optional#empty()} if the key has no
     * reference before
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    default Optional<Id> removeReference(ManyFeatureKey key) {
        return removeValue(key);
    }

    /**
     * Removes all references of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default void removeAllReferences(FeatureKey key) {
        removeAllValues(key);
    }

    /**
     * Moves the reference of the specified {@code source} key to the {@code target} key.
     *
     * @param source the key identifying the multi-valued reference to move
     * @param target the key identifying the multi-valued reference where to move the reference to
     *
     * @return an {@link Optional} containing the moved reference, or {@link Optional#empty()} if no reference has
     * been moved
     */
    @Nonnull
    default Optional<Id> moveReference(ManyFeatureKey source, ManyFeatureKey target) {
        checkNotNull(source);
        checkNotNull(target);

        if (Objects.equals(source, target)) {
            return Optional.empty();
        }

        Optional<Id> movedReference = removeReference(source);
        movedReference.ifPresent(r -> addReference(target, r));
        return movedReference;
    }

    /**
     * Checks whether the specified {@code key} has the given {@code reference}.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to look for
     *
     * @return {@code true} if the {@code key} has the {@code reference}, {@code false} otherwise
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default boolean containsReference(FeatureKey key, @Nullable Id reference) {
        return indexOfReference(key, reference).isPresent();
    }

    /**
     * Retrieves the first position of the {@code reference} of the specified {@code key}.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to look for
     *
     * @return an {@link OptionalInt} containing the first position of the {@code reference}, or {@link
     * OptionalInt#empty()} if the {@code key} hasn't the {@code reference}
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default OptionalInt indexOfReference(FeatureKey key, @Nullable Id reference) {
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
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default OptionalInt lastIndexOfReference(FeatureKey key, @Nullable Id reference) {
        return lastIndexOfValue(key, reference);
    }

    /**
     * Returns the number of reference of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return an {@link OptionalInt} containing the number of reference of the {@code key}, or {@link
     * OptionalInt#empty()} if the {@code key} hasn't any reference
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default OptionalInt sizeOfReference(FeatureKey key) {
        return sizeOfValue(key);
    }
}
