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

package fr.inria.atlanmod.neoemf.io.structure;

/**
 * A simple element that has a name.
 */
public abstract class NamedElement {

    /**
     * The name of the element.
     */
    private final String name;

    /**
     * Constructs a new {@code NamedElement} with the given {@code name}.
     *
     * @param name the name of this element
     */
    public NamedElement(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this element.
     *
     * @return the name
     */
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
