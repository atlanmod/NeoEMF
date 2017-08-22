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

package fr.inria.atlanmod.neoemf.io.bean;

import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple element that has a name.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractNamedElement {

    /**
     * The name of the element.
     */
    private String name;

    /**
     * Returns the name of this element.
     *
     * @return the name
     */
    public String name() {
        return name;
    }

    /**
     * Defines the name of this element.
     *
     * @param name the name
     */
    public void name(String name) {
        this.name = name;
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
        if (!AbstractNamedElement.class.isInstance(o)) {
            return false;
        }

        AbstractNamedElement that = AbstractNamedElement.class.cast(o);
        return Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
