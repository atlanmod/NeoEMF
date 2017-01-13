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
 * A {@link StructuralFeature} representing an attribute, with a value.
 */
public class Attribute extends StructuralFeature {

    /**
     * The value of this attribute.
     */
    private Object value;

    /**
     * Constructs a new {@code Attribute} with the given {@code localName}.
     *
     * @param localName the name of this attribute
     */
    public Attribute(String localName) {
        super(localName);
    }

    /**
     * Converts a {@link Reference} to an {@link Attribute}.
     *
     * @param reference the reference to convert
     *
     * @return a new attribute
     */
    public static Attribute from(Reference reference) {
        Attribute attribute = new Attribute(reference.getLocalName());
        attribute.setId(reference.getId());
        attribute.setIndex(reference.getIndex());
        attribute.setValue(reference.getIdReference().getValue());
        return attribute;
    }

    /**
     * Returns the value of this attribute.
     *
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Defines the value of this attribute.
     *
     * @param value the value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean isAttribute() {
        return true;
    }
}
