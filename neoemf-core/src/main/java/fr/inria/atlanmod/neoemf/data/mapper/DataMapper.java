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

import java.io.Closeable;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object capable of mapping features, containers and metaclasses represented as a set of key/value pair.
 *
 * @see ContainerMapper
 * @see ClassMapper
 * @see ValueMapper
 * @see ReferenceMapper
 * @see ManyValueMapper
 * @see ManyReferenceMapper
 */
@ParametersAreNonnullByDefault
public interface DataMapper extends Closeable, ContainerMapper, ClassMapper, ValueMapper, ManyValueMapper, ReferenceMapper, ManyReferenceMapper {

    /**
     * Cleanly closes this mapper, clear all data in-memory and releases any system resources associated with it. All
     * modifications are saved before closing.
     * <p>
     * If the mapper is already closed, then invoking this method has no effect.
     */
    @Override
    void close();

    /**
     * Saves all changes made on this mapper since the last call.
     */
    void save();

    /**
     * Copies all the contents from this mapper to the {@code target}.
     *
     * @param target the mapper to copy the mapper contents to
     */
    void copyTo(DataMapper target);

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
