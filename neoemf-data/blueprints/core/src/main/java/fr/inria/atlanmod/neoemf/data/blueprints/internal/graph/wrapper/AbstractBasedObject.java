/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.wrapper;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * An object based on another.
 *
 * @param <T> the type of the base element
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
