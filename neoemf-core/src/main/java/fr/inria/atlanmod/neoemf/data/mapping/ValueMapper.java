/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;

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
     * @return the deferred computation that may contains the value
     */
    @Nonnull
    <V> Maybe<V> valueOf(SingleFeatureBean key);

    /**
     * Defines the {@code value} of the specified {@code key}.
     *
     * @param key   the key identifying the value
     * @param value the value to set
     * @param <V>   the type of value
     *
     * @return the deferred computation
     */
    @Nonnull
    <V> Completable valueFor(SingleFeatureBean key, V value);

    /**
     * Removes the value of the specified {@code key}.
     *
     * @param key the key identifying the value
     *
     * @return the deferred computation
     */
    @Nonnull
    Completable removeValue(SingleFeatureBean key);
}
