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
 * A {@link BasicFeature} representing a reference, which links several {@link BasicElement}s.
 */
public class BasicReference extends BasicFeature {

    /**
     * The identifier of the referenced element.
     */
    private BasicId idReference;

    /**
     * The metaclass of the referenced element.
     */
    private BasicMetaclass metaclassReference;

    /**
     * Whether this reference is a containment.
     */
    private boolean isContainment;

    /**
     * Constructs a new {@code BasicReference} with the given {@code name}.
     *
     * @param name the name of this reference
     */
    public BasicReference(String name) {
        super(name);
        this.isContainment = false;
    }

    /**
     * Converts an {@link BasicAttribute} to a {@code BasicReference}.
     *
     * @param attribute the attribute to convert
     *
     * @return a new reference
     */
    public static BasicReference from(BasicAttribute attribute) {
        BasicReference reference = new BasicReference(attribute.name());
        reference.id(attribute.id());
        reference.index(attribute.index());
        reference.idReference(BasicId.original(attribute.value().toString()));
        return reference;
    }

    /**
     * Returns the identifier of the referenced element.
     *
     * @return the identifier
     */
    public BasicId idReference() {
        return idReference;
    }

    /**
     * Defines the identifier of the referenced element.
     *
     * @param idReference the identifier
     */
    public void idReference(BasicId idReference) {
        this.idReference = idReference;
    }

    @Override
    public boolean isReference() {
        return true;
    }

    /**
     * Returns the metaclass of the referenced element.
     *
     * @return the metaclass
     */
    public BasicMetaclass metaclassReference() {
        return metaclassReference;
    }

    /**
     * Defines the metaclass of the referenced element.
     *
     * @param metaclassReference the metaclass
     */
    public void metaclassReference(BasicMetaclass metaclassReference) {
        this.metaclassReference = metaclassReference;
    }

    /**
     * Returns whether this reference is a containment.
     *
     * @return {@code true} if this reference is a containment
     */
    public boolean isContainment() {
        return isContainment;
    }

    /**
     * Defines whether this reference is a containment.
     *
     * @param isContainment {@code true} if this reference is a containment
     */
    public void isContainment(boolean isContainment) {
        this.isContainment = isContainment;
    }
}
