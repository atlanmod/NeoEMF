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
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object capable of mapping single-valued references represented as a set of key/value pair.
 * <p>
 * By default, the references are processed as values with {@link ValueMapper}.
 */
@ParametersAreNonnullByDefault
public interface ReferenceMapper extends ValueMapper {

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
    default Optional<Id> referenceOf(SingleFeatureKey key) {
        return valueOf(key);
    }

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
    default Optional<Id> referenceFor(SingleFeatureKey key, Id reference) {
        return valueFor(key, reference);
    }

    /**
     * Unsets the reference of the specified {@code key}.
     *
     * @param key the key identifying the reference
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    default void unsetReference(SingleFeatureKey key) {
        unsetValue(key);
    }

    /**
     * Checks whether the specified {@code key} has a defined reference.
     *
     * @param key the key identifying the reference
     *
     * @return {@code true} if the {@code key} has a reference, {@code false} otherwise
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    default boolean hasReference(SingleFeatureKey key) {
        return hasValue(key);
    }
}
