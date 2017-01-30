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
 * A {@link Feature} representing an attribute, with a value.
 */
public class Attribute extends Feature {

    /**
     * The value of this attribute.
     */
    private Object value;

    /**
     * Constructs a new {@code Attribute} with the given {@code name}.
     *
     * @param name the name of this attribute
     */
    public Attribute(String name) {
        super(name);
    }

    /**
     * Converts a {@link Reference} to an {@code Attribute}.
     *
     * @param reference the reference to convert
     *
     * @return a new attribute
     */
    public static Attribute from(Reference reference) {
        Attribute attribute = new Attribute(reference.name());
        attribute.id(reference.id());
        attribute.index(reference.index());
        attribute.value(reference.idReference().value());
        return attribute;
    }

    /**
     * Returns the value of this attribute.
     *
     * @return the value
     */
    public Object value() {
        return value;
    }

    /**
     * Defines the value of this attribute.
     *
     * @param value the value
     */
    public void value(Object value) {
        this.value = value;
    }

    @Override
    public boolean isAttribute() {
        return true;
    }
}
