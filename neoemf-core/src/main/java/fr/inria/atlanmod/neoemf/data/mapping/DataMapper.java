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

import fr.inria.atlanmod.commons.Copiable;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.DataManager;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object capable of mapping features, containers and meta-classes represented as a set of key/value pair.
 *
 * @see ContainerMapper
 * @see ClassMapper
 * @see ValueMapper
 * @see ReferenceMapper
 * @see ManyValueMapper
 * @see ManyReferenceMapper
 */
@ParametersAreNonnullByDefault
public interface DataMapper extends DataManager, Copiable<DataMapper>, ContainerMapper, ClassMapper, ValueMapper, ManyValueMapper, ReferenceMapper, ManyReferenceMapper {

    /**
     * Checks whether the specified {@code id} already exists in this {@code DataMapper}.
     *
     * @param id the identifier to check
     *
     * @return {@code true} if the {@code id} exists, {@code false} otherwise.
     */
    default boolean exists(Id id) {
        return hasMetaclass(id);
    }
}
