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
     * @return an {@link Optional} containing the container, or {@link Optional#empty()} if the {@code id} has no
     * defined container.
     *
     * @throws NullPointerException if any parameter is {@code null}
     */
    @Nonnull
    Optional<SingleFeatureBean> containerOf(Id id);

    /**
     * Stores the {@code container} for the specified {@code id}.
     *
     * @param id        the {@link Id} of the contained element
     * @param container the containing element's container information to store
     *
     * @throws NullPointerException if the {@code id} is {@code null}
     */
    void containerFor(Id id, SingleFeatureBean container);

    /**
     * Removes the container of the specified {@code id}.
     * <p>
     * The container must be completely removed, so that a call to {@link #containerOf(Id)} returns {@link
     * Optional#empty()}.
     *
     * @param id the {@link Id} of the contained element
     *
     * @throws NullPointerException if the {@code id} is {@code null}
     */
    void removeContainer(Id id);
}
