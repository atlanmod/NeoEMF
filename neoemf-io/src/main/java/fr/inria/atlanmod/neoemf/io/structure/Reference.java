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
 * A {@link StructuralFeature} representing a reference, which links several {@link Classifier}s.
 */
public class Reference extends StructuralFeature {

    /**
     * The identifier of the referenced element.
     */
    private Identifier idReference;

    /**
     * Whether this reference is a containment.
     */
    private boolean containment;

    /**
     * Constructs a new {@code Reference} with the given {@code localName}.
     *
     * @param localName the name of this reference
     */
    public Reference(String localName) {
        super(localName);
        this.containment = false;
    }

    /**
     * Converts an {@link Attribute} to a {@link Reference}.
     *
     * @param attribute the attribute to convert
     *
     * @return a new reference
     */
    public static Reference from(Attribute attribute) {
        Reference reference = new Reference(attribute.getLocalName());
        reference.setId(attribute.getId());
        reference.setIndex(attribute.getIndex());
        reference.setIdReference(Identifier.original(attribute.getValue().toString()));
        return reference;
    }

    /**
     * Returns the identifier of the referenced element.
     *
     * @return the identifier
     */
    public Identifier getIdReference() {
        return idReference;
    }

    /**
     * Defines the identifier of the referenced element.
     *
     * @param idReference the identifier
     */
    public void setIdReference(Identifier idReference) {
        this.idReference = idReference;
    }

    @Override
    public boolean isReference() {
        return true;
    }

    /**
     * Returns whether this reference is a containment.
     *
     * @return {@code true} if this reference is a containment
     */
    public boolean isContainment() {
        return containment;
    }

    /**
     * Defines whether this reference is a containment.
     *
     * @param containment {@code true} if this reference is a containment
     */
    public void setContainment(boolean containment) {
        this.containment = containment;
    }
}
