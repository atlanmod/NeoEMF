/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An object based on another, owned by an {@link IdGraph}.
 *
 * @param <T>
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBasedObject<T> {

    /**
     * The base element.
     */
    @Nonnull
    protected final T base;

    /**
     * Constructs a new {@code AbstractBasedObject}.
     *
     * @param base the base element
     */
    protected AbstractBasedObject(T base) {
        this.base = checkNotNull(base, "base");
    }

    /**
     * Returns the base element of this element.
     *
     * @return the base element
     */
    @Nonnull
    public T getBaseElement() {
        return base;
    }
}
