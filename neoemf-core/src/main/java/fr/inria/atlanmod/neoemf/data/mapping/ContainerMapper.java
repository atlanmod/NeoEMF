/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
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

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * An object capable of mapping containers.
 *
 * @see SingleFeatureBean
 */
@ParametersAreNonnullByDefault
public interface ContainerMapper {

    /**
     * Retrieves the container for the specified {@code id}.
     *
     * @param id the {@link Id} of the contained element
     *
     * @return the deferred computation to execute, that may contains the container
     */
    @Nonnull
    Maybe<SingleFeatureBean> containerOf(Id id);

    /**
     * Stores the {@code container} for the specified {@code id}.
     *
     * @param id        the {@link Id} of the contained element
     * @param container the containing element's container information to store
     *
     * @return the deferred computation to execute
     */
    @Nonnull
    Completable containerFor(Id id, SingleFeatureBean container);

    /**
     * Removes the container of the specified {@code id}.
     * <p>
     * The container must be completely removed, so that a call to {@link #containerOf(Id)} returns {@link
     * Optional#empty()}.
     *
     * @param id the {@link Id} of the contained element
     *
     * @return the deferred computation to execute
     */
    @Nonnull
    Completable removeContainer(Id id);
}
