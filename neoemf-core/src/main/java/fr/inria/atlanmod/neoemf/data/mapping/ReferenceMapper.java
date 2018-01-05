/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;

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
     * @return the deferred computation that may contains the reference
     */
    @Nonnull
    Maybe<Id> referenceOf(SingleFeatureBean key);

    /**
     * Defines the reference of the specified {@code key}.
     *
     * @param key       the key identifying the reference
     * @param reference the reference to set
     *
     * @return the deferred computation
     */
    @Nonnull
    Completable referenceFor(SingleFeatureBean key, Id reference);

    /**
     * Removes the reference of the specified {@code key}.
     *
     * @param key the key identifying the reference
     *
     * @return the deferred computation
     */
    @Nonnull
    Completable removeReference(SingleFeatureBean key);
}
