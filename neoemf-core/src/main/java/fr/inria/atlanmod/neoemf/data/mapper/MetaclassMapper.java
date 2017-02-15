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
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 * ???
 */
public interface MetaclassMapper {

    /**
     * Retrieves the {@link MetaclassDescriptor} for the given {@code id}.
     *
     * @param id the {@link Id} of the element
     *
     * @return an {@link Optional} containing the {@link MetaclassDescriptor}, or {@link Optional#empty()} if the {@code
     * id} has no defined metaclass.
     */
    @Nonnull
    Optional<MetaclassDescriptor> metaclassOf(Id id);

    /**
     * Stores the {@link MetaclassDescriptor} for the given {@code id}.
     *
     * @param id        the {@link Id} of the element
     * @param metaclass the {@link MetaclassDescriptor} containing element's metaclass information to store
     */
    void metaclassFor(Id id, MetaclassDescriptor metaclass);
}
