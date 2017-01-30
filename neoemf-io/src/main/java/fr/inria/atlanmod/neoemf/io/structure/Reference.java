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
 * A {@link Feature} representing a reference, which links several {@link Element}s.
 */
public class Reference extends Feature {

    /**
     * The identifier of the referenced element.
     */
    private Identifier idReference;

    /**
     * Whether this reference is a containment.
     */
    private boolean containment;

    /**
     * Constructs a new {@code Reference} with the given {@code name}.
     *
     * @param name the name of this reference
     */
    public Reference(String name) {
        super(name);
        this.containment = false;
    }

    /**
     * Converts an {@link Attribute} to a {@code Reference}.
     *
     * @param attribute the attribute to convert
     *
     * @return a new reference
     */
    public static Reference from(Attribute attribute) {
        Reference reference = new Reference(attribute.name());
        reference.id(attribute.id());
        reference.index(attribute.index());
        reference.idReference(Identifier.original(attribute.value().toString()));
        return reference;
    }

    /**
     * Returns the identifier of the referenced element.
     *
     * @return the identifier
     */
    public Identifier idReference() {
        return idReference;
    }

    /**
     * Defines the identifier of the referenced element.
     *
     * @param idReference the identifier
     */
    public void idReference(Identifier idReference) {
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
    public boolean containment() {
        return containment;
    }

    /**
     * Defines whether this reference is a containment.
     *
     * @param containment {@code true} if this reference is a containment
     */
    public void containment(boolean containment) {
        this.containment = containment;
    }
}
