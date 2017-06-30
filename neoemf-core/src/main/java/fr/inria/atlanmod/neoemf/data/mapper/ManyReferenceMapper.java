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
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An object capable of mapping multi-valued references represented as a set of key/value pair.
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceMapper extends ReferenceMapper {

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
    Optional<Id> referenceOf(ManyFeatureKey key);

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
    List<Id> allReferencesOf(SingleFeatureKey key);

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
     * @see #appendReference(SingleFeatureKey, Id)
     */
    @Nonnull
    Optional<Id> referenceFor(ManyFeatureKey key, Id reference);

    /**
     * Checks whether the specified {@code key} has at least one defined reference.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return {@code true} if the {@code key} has a reference, {@code false} otherwise
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default boolean hasAnyReference(SingleFeatureKey key) {
        return sizeOfReference(key).isPresent();
    }

    /**
     * Adds the {@code reference} to the specified {@code key} at a defined position.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to add
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code key#position() > size}
     */
    void addReference(ManyFeatureKey key, Id reference);

    /**
     * Adds all the {@code collection} to the specified {@code key} from the position of the {@code key}.
     *
     * @param key        the key identifying the multi-valued attribute
     * @param collection the values to add
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code key#position() > size}
     */
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

        IntStream.range(0, collection.size())
                .forEach(i -> addReference(key.withPosition(firstPosition + i), collection.get(i)));
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
    default int appendReference(SingleFeatureKey key, Id reference) {
        checkNotNull(key);

        int position = sizeOfReference(key).orElse(0);

        addReference(key.withPosition(position), reference);

        return position;
    }

    /**
     * Adds all the {@code collection} to the specified {@code key} from the last position.
     *
     * @param key        the key identifying the multi-valued reference
     * @param collection the references to add
     *
     * @return the position to which the first reference was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addReference(ManyFeatureKey, Id)
     * @see #appendReference(SingleFeatureKey, Id)
     */
    @Nonnegative
    default int appendAllReferences(SingleFeatureKey key, List<Id> collection) {
        checkNotNull(key);
        checkNotNull(collection);

        int firstPosition = sizeOfReference(key).orElse(0);

        addAllReferences(key.withPosition(firstPosition), collection);

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
    Optional<Id> removeReference(ManyFeatureKey key);

    /**
     * Removes all references of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    default void removeAllReferences(SingleFeatureKey key) {
        unsetReference(key);
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

        Optional<Id> movedValue = removeReference(source);
        movedValue.ifPresent(v -> addReference(target, v));
        return movedValue;
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
    default boolean containsReference(SingleFeatureKey key, @Nullable Id reference) {
        return indexOfReference(key, reference).isPresent();
    }

    /**
     * Retrieves the first position of the {@code reference} of the specified {@code key}.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to look for
     *
     * @return an {@link Optional} containing the first position of the {@code reference}, or {@link Optional#empty()}
     * if the {@code key} hasn't the {@code reference}
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default Optional<Integer> indexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        return Optional.of(allReferencesOf(key).indexOf(reference))
                .filter(i -> i >= 0);
    }

    /**
     * Retrieves the last position of the {@code reference} of the specified {@code key}.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to look for
     *
     * @return an {@link Optional} containing the last position of the {@code reference}, or {@link Optional#empty()} if
     * the {@code key} hasn't the {@code reference}
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default Optional<Integer> lastIndexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        if (isNull(reference)) {
            return Optional.empty();
        }

        return Optional.of(allReferencesOf(key).lastIndexOf(reference))
                .filter(i -> i >= 0);
    }

    /**
     * Returns the number of reference of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return an {@link Optional} containing the number of reference of the {@code key}, or {@link Optional#empty()} if
     * the {@code key} hasn't any reference
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    @Nonnegative
    default Optional<Integer> sizeOfReference(SingleFeatureKey key) {
        return Optional.of(allReferencesOf(key).size())
                .filter(s -> s != 0);
    }
}
