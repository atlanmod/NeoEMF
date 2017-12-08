/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.Copiable;

import java.io.Closeable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;

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
public interface DataMapper extends Closeable, Copiable<DataMapper>, ContainerMapper, ClassMapper, ValueMapper, ManyValueMapper, ReferenceMapper, ManyReferenceMapper {

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
     *
     * @return the deferred computation
     */
    @Nonnull
    Completable save();
}
