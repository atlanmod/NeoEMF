/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object capable of mapping single-valued attributes represented as a set of key/value pair.
 */
@ParametersAreNonnullByDefault
public interface ValueMapper {

    /**
     * Retrieves the value of the specified {@code key}.
     *
     * @param key the key identifying the value
     * @param <V> the type of value
     *
     * @return an {@link Optional} containing the value, or {@link Optional#empty()} if the key hasn't any value or
     * doesn't exist
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    <V> Optional<V> valueOf(SingleFeatureBean key);

    /**
     * Defines the {@code value} of the specified {@code key}.
     *
     * @param key   the key identifying the value
     * @param value the value to set
     * @param <V>   the type of value
     *
     * @return an {@link Optional} containing the previous value of the {@code key}, or {@link Optional#empty()} if the
     * key has no value before
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    <V> Optional<V> valueFor(SingleFeatureBean key, V value);

    /**
     * Removes the value of the specified {@code key}.
     *
     * @param key the key identifying the value
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    void removeValue(SingleFeatureBean key);
}
