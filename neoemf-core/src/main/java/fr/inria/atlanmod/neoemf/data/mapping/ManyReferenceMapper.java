/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
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
     * Retrieves the reference of the specified {@code feature} at a defined position.
     *
     * @param feature the bean identifying the multi-valued reference
     *
     * @return an {@link Optional} containing the reference, or {@link Optional#empty()} if the feature hasn't any
     * reference or doesn't exist
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    @Nonnull
    Optional<Id> referenceOf(ManyFeatureBean feature);

    /**
     * Retrieves all references of the specified {@code feature}.
     *
     * @param feature the bean identifying the multi-valued reference
     *
     * @return an immutable ordered {@link Stream} over all references
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    @Nonnull
    Stream<Id> allReferencesOf(SingleFeatureBean feature);

    /**
     * Defines the {@code reference} of the specified {@code feature} at a defined position.
     *
     * @param feature   the bean identifying the multi-valued reference
     * @param reference the reference to set
     *
     * @return an {@link Optional} containing the previous reference of the {@code feature}, or {@link Optional#empty()}
     * if the feature has no reference before
     *
     * @throws NoSuchElementException if the {@code feature} doesn't exist
     * @throws NullPointerException   if any parameter is {@code null}
     * @implSpec This method is intended to modify an existing value. If the {@code feature} is not defined,
     * implementations should not add the value, but throw a {@link NoSuchElementException}.
     * @see #addReference(ManyFeatureBean, Id)
     * @see #appendReference(SingleFeatureBean, Id)
     */
    @Nonnull
    Optional<Id> referenceFor(ManyFeatureBean feature, Id reference);

    /**
     * Adds the {@code reference} to the specified {@code feature} at a defined position.
     *
     * @param feature   the bean identifying the multi-valued reference
     * @param reference the reference to add
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code feature#position() > size}
     */
    void addReference(ManyFeatureBean feature, Id reference);

    /**
     * Adds all the {@code references} to the specified {@code feature} from the position of the {@code feature}.
     *
     * @param feature    the bean identifying the multi-valued attribute
     * @param references the values to add
     *
     * @throws NullPointerException      if any parameter is {@code null}
     * @throws IndexOutOfBoundsException if {@code feature#position() > size}
     */
    void addAllReferences(ManyFeatureBean feature, List<Id> references);

    /**
     * Adds the {@code reference} to the specified {@code feature} at the last position.
     *
     * @param feature   the bean identifying the multi-valued reference
     * @param reference the reference to add
     *
     * @return the position to which the reference was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addReference(ManyFeatureBean, Id)
     */
    @Nonnegative
    default int appendReference(SingleFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");

        int position = sizeOfReference(feature).orElse(0);

        addReference(feature.withPosition(position), reference);

        return position;
    }

    /**
     * Adds all the {@code references} to the specified {@code feature} from the last position.
     *
     * @param feature    the bean identifying the multi-valued reference
     * @param references the references to add
     *
     * @return the position to which the first reference was added
     *
     * @throws NullPointerException if any parameter is {@code null}
     * @see #addReference(ManyFeatureBean, Id)
     * @see #appendReference(SingleFeatureBean, Id)
     */
    @Nonnegative
    default int appendAllReferences(SingleFeatureBean feature, List<Id> references) {
        checkNotNull(feature, "feature");
        checkNotNull(references, "references");

        int firstPosition = sizeOfReference(feature).orElse(0);

        addAllReferences(feature.withPosition(firstPosition), references);

        return firstPosition;
    }

    /**
     * Removes the reference of the specified {@code feature} at a defined position.
     *
     * @param feature the bean identifying the multi-valued reference
     *
     * @return an {@link Optional} containing the removed reference, or {@link Optional#empty()} if the feature has no reference
     *before
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    @Nonnull
    Optional<Id> removeReference(ManyFeatureBean feature);

    /**
     * Removes all references of the specified {@code feature}.
     *
     * @param feature the bean identifying the multi-valued reference
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    void removeAllReferences(SingleFeatureBean feature);

    /**
     * Returns the number of reference of the specified {@code feature}.
     *
     * @param feature the bean identifying the multi-valued reference
     *
     * @return an {@link Optional} containing the number of reference of the {@code feature}, or {@link
     * Optional#empty()} if the {@code feature} hasn't any reference
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     */
    @Nonnull
    @Nonnegative
    Optional<Integer> sizeOfReference(SingleFeatureBean feature);
}
