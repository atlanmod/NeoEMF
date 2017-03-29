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

package fr.inria.atlanmod.neoemf.core;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object able to retrieve an object from another.
 *
 * @param <T> the type of object to be resolved
 * @param <R> the type of resolved object
 */
@FunctionalInterface
@ParametersAreNonnullByDefault
public interface Resolver<T, R> {

    /**
     * Retrieves the object associated to the given argument.
     *
     * @param t the argument to be resolved
     *
     * @return the resolved object
     *
     * @throws java.util.NoSuchElementException if no object can be retrieved
     */
    @Nonnull
    R resolve(T t);
}
