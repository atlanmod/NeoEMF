/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object capable of mapping single-valued references represented as a set of key/value pair.
 */
@ParametersAreNonnullByDefault
public interface ReferenceMapper {

    /**
     * Retrieves the reference of the specified {@code feature}.
     *
     * @param feature the bean identifying the reference
     *
     * @return an {@link Optional} containing the reference, or {@link Optional#empty()} if the feature hasn't any
     * reference or doesn't exist
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    Optional<Id> referenceOf(SingleFeatureBean feature);

    /**
     * Defines the reference of the specified {@code feature}.
     *
     * @param feature   the bean identifying the reference
     * @param reference the reference to set
     *
     * @return an {@link Optional} containing the previous reference of the {@code feature}, or {@link Optional#empty()}
     * if the feature has no reference before
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    Optional<Id> referenceFor(SingleFeatureBean feature, Id reference);

    /**
     * Removes the reference of the specified {@code feature}.
     *
     * @param feature the bean identifying the reference
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    void removeReference(SingleFeatureBean feature);
}
