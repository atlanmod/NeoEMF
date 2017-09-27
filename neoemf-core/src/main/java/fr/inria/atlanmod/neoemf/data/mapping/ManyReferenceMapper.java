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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

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
    Optional<Id> referenceOf(ManyFeatureBean key);

    /**
     * Retrieves all references of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @return an immutable ordered {@link Stream} containing all references
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    @Nonnull
    Stream<Id> allReferencesOf(SingleFeatureBean key);

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
     * @see #addReference(ManyFeatureBean, Id)
     * @see #appendReference(SingleFeatureBean, Id)
     */
    @Nonnull
    Optional<Id> referenceFor(ManyFeatureBean key, Id reference);

    /**
     * Adds the {@code reference} to the specified {@code key} at a defined position.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to add
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code key#position() > size}
     */
    void addReference(ManyFeatureBean key, Id reference);

    /**
     * Adds all the {@code collection} to the specified {@code key} from the position of the {@code key}.
     *
     * @param key        the key identifying the multi-valued attribute
     * @param collection the values to add
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code key#position() > size}
     */
    void addAllReferences(ManyFeatureBean key, List<Id> collection);

    /**
     * Adds the {@code reference} to the specified {@code key} at the last position.
     *
     * @param key       the key identifying the multi-valued reference
     * @param reference the reference to add
     *
     * @return the position to which the reference was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addReference(ManyFeatureBean, Id)
     */
    @Nonnegative
    default int appendReference(SingleFeatureBean key, Id reference) {
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
     * @see #addReference(ManyFeatureBean, Id)
     * @see #appendReference(SingleFeatureBean, Id)
     */
    @Nonnegative
    default int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
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
    Optional<Id> removeReference(ManyFeatureBean key);

    /**
     * Removes all references of the specified {@code key}.
     *
     * @param key the key identifying the multi-valued reference
     *
     * @throws NullPointerException if the {@code key} is {@code null}
     */
    void removeAllReferences(SingleFeatureBean key);

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
    Optional<Integer> sizeOfReference(SingleFeatureBean key);
}
