/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import org.eclipse.emf.ecore.EObject;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of an {@link EObject} of type {@code R}.
 *
 * @param <T> the "self"-type of this object
 * @param <R> the type of the represented object
 */
@ParametersAreNonnullByDefault
// FIXME Find better name for methods!
public interface Basic<T extends Basic<T, R>, R extends EObject> {

    /**
     * Returns the object represented by this object.
     *
     * @return the represented object
     */
    R getReal();

    /**
     * Defines the object represented by this object.
     *
     * @param r the represented object
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    T setReal(R r);
}
