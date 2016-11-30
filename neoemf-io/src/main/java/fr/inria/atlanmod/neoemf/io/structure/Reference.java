/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
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
 * A {@link StructuralFeature} of a link between two {@link Classifier}.
 */
public class Reference extends StructuralFeature {

    private Identifier idReference;
    private boolean containment;

    public Reference(String localName) {
        super(localName);
        this.containment = false;
    }

    public static Reference from(Attribute attribute) {
        Reference reference = new Reference(attribute.getLocalName());
        reference.setId(attribute.getId());
        reference.setIndex(attribute.getIndex());
        reference.setIdReference(Identifier.original(attribute.getValue().toString()));
        return reference;
    }

    public Identifier getIdReference() {
        return idReference;
    }

    public void setIdReference(Identifier idReference) {
        this.idReference = idReference;
    }

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
}
