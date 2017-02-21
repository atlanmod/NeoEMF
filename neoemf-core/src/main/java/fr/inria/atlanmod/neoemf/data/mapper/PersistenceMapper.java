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
 * An object capable of mapping features, containers and metaclasses represented as a set of key/value pair.
 *
 * @see ContainerMapper
 * @see MetaclassMapper
 * @see ValueMapper
 * @see ReferenceMapper
 * @see MultiValueMapper
 * @see MultiReferenceMapper
 */
@ParametersAreNonnullByDefault
public interface PersistenceMapper extends ContainerMapper, MetaclassMapper, ValueMapper, MultiValueMapper, ReferenceMapper, MultiReferenceMapper {

    /**
     * Checks whether the specified {@code id} already exists in this {@code PersistenceMapper}.
     *
     * @param id the identifier to check
     *
     * @return {@code true} if the {@code id} exists, {@code false} otherwise.
     */
    boolean exists(Id id);
}
