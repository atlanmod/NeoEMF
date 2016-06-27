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
 * A {link StructuralFeature structural feature} of a link between 2 {@link Classifier classifiers}.
 */
public class Reference extends StructuralFeature {

    private boolean containment;

    @Override
    public boolean isReference() {
        return true;
    }

    public boolean isContainment() {
        return containment;
    }

    public void setContainment(boolean containment) {
        this.containment = containment;
    }

    public static Reference from(Attribute attribute) {
        Reference reference = new Reference();
        reference.setNamespace(attribute.getNamespace());
        reference.setIndex(attribute.getIndex());
        reference.setLocalName(attribute.getLocalName());
        reference.setId(attribute.getId());
        reference.setValue(attribute.getValue());
        return reference;
    }
}
