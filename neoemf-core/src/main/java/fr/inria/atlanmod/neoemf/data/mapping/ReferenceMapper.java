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
     * Retrieves the reference of the specified {@code key}.
     *
     * @param key the key identifying the reference
     *
     * @return an {@link Optional} containing the reference, or {@link Optional#empty()} if the key hasn't any reference
     * or doesn't exist
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    Optional<Id> referenceOf(SingleFeatureBean key);

    /**
     * Defines the reference of the specified {@code key}.
     *
     * @param key       the key identifying the reference
     * @param reference the reference to set
     *
     * @return an {@link Optional} containing the previous reference of the {@code key}, or {@link Optional#empty()} if
     * the key has no reference before
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    Optional<Id> referenceFor(SingleFeatureBean key, Id reference);

    /**
     * Unsets the reference of the specified {@code key}.
     *
     * @param key the key identifying the reference
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    void unsetReference(SingleFeatureBean key);

    /**
     * Checks whether the specified {@code key} has a defined reference.
     *
     * @param key the key identifying the reference
     *
     * @return {@code true} if the {@code key} has a reference, {@code false} otherwise
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    default boolean hasReference(SingleFeatureBean key) {
        return referenceOf(key).isPresent();
    }
}
