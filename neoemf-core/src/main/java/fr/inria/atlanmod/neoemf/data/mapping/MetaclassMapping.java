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
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 */
public interface MetaclassMapping {

    /**
     * Retrieves the {@link MetaclassDescriptor} for the given {@code id}.
     *
     * @param id the {@link Id} of the element
     *
     * @return a {@link MetaclassDescriptor} containing element's metaclass information
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
