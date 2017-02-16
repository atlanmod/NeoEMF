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

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object capable of mapping features represented as a set of key/value pair.
 */
@ParametersAreNonnullByDefault
public interface PersistenceMapper extends ContainerMapper, MetaclassMapper, ValueMapper, MultiValueMapper, ReferenceMapper, MultiReferenceMapper {

    /**
     * Creates the specified {@code id}.
     *
     * @param id the id to create
     *
     * @throws IllegalArgumentException if the {@code id} already exists. Use {@link #has(Id)} to check the presence
     *                                  first
     */
    void create(Id id);

    /**
     * Checks whether the specified {@code id} is already present.
     *
     * @param id the id to check to presence
     *
     * @return {@code true} if the {@code id} is present, {@code false} otherwise.
     */
    boolean has(Id id);
}
