/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.beans;

/**
 * A {link StructuralFeature structural feature} of a simple key/value pair.
 */
public class Attribute extends StructuralFeature {

    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Attribute(String localName) {
        super(localName);
    }

    @Override
    public boolean isAttribute() {
        return true;
    }

    public static Attribute from(Reference reference) {
        Attribute attribute = new Attribute(reference.getLocalName());
        attribute.setId(reference.getId());
        attribute.setIndex(reference.getIndex());
        attribute.setValue(reference.getIdReference().getValue());
        return attribute;
    }
}