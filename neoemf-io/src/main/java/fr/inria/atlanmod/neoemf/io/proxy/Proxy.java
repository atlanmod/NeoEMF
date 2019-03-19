/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.proxy;

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
public interface Proxy<T extends Proxy<T, R>, R extends EObject> {

    /**
     * Returns the origin of this proxy.
     *
     * @return the represented object
     */
    R getOrigin();

    /**
     * Defines the origin of this proxy.
     *
     * @param r the represented object
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    T setOrigin(R r);
}
