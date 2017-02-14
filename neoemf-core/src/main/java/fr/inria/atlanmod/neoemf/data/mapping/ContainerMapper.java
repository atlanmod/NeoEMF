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
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 */
public interface ContainerMapper {

    /**
     * Retrieves the {@link ContainerDescriptor} for a given {@code id}.
     *
     * @param id the {@link Id} of the contained element
     *
     * @return an {@link Optional} containing the {@link ContainerDescriptor}, or {@link Optional#empty()} if the {@code
     * id} has no defined container.
     */
    @Nonnull
    Optional<ContainerDescriptor> containerOf(Id id);

    /**
     * Stores the {@link ContainerDescriptor} for a given {@code id}.
     *
     * @param id        the {@link Id} of the contained element
     * @param container the {@link ContainerDescriptor} containing element's container information to store
     */
    void containerFor(Id id, ContainerDescriptor container);
}
