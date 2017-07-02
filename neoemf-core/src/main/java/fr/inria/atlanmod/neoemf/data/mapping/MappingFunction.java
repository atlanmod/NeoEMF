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

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object that converts primitives values.
 *
 * @param <I> the type of the input value
 * @param <O> the type of the ouput value
 *
 * @see ReferenceWith
 * @see ManyValueWith
 * @see ManyReferenceWith
 */
@ParametersAreNonnullByDefault
public interface MappingFunction<I, O> {

    /**
     * Maps the input value.
     *
     * @param i the input value to map
     *
     * @return the mapped value
     */
    @Nonnull
    O map(I i);

    /**
     * Unmaps the output value.
     *
     * @param o the output value to unmap
     *
     * @return the unmapped value
     */
    @Nonnull
    I unmap(O o);
}
