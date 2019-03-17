/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.proxy;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple element that has a name.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractNamedElement<T extends AbstractNamedElement<T>> {

    /**
     * The name of the element.
     */
    private String name;

    /**
     * Returns this instance, casted as a {@code T}.
     *
     * @return this instance
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected final T me() {
        return (T) this;
    }

    /**
     * Returns the name of this element.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Defines the name of this element.
     *
     * @param name the name
     *
     * @return this instance (for chaining)
     */
    public T setName(String name) {
        this.name = name;

        return me();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractNamedElement<?> that = (AbstractNamedElement) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
