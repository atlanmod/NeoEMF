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

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link AbstractBasicFeature} representing a reference, which links several {@link BasicElement}s.
 */
@ParametersAreNonnullByDefault
public class BasicReference extends AbstractBasicFeature {

    /**
     * The identifier of the referenced element.
     */
    private BasicId idReference;

    /**
     * The meta-class of the referenced element.
     */
    private BasicMetaclass metaClassReference;

    /**
     * Whether this reference is a containment.
     */
    private boolean isContainment = false;

    /**
     * Converts an {@link BasicAttribute} to a {@code BasicReference}.
     *
     * @param attribute the attribute to convert
     *
     * @return a new reference
     */
    public static BasicReference from(BasicAttribute attribute) {
        BasicReference reference = new BasicReference();
        reference.name(attribute.name());
        reference.owner(attribute.owner());
        reference.index(attribute.index());
        reference.idReference(BasicId.original(attribute.value()));
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
     * Returns the meta-class of the referenced element.
     *
     * @return the meta-class
     */
    public BasicMetaclass metaClassReference() {
        return metaClassReference;
    }

    /**
     * Defines the meta-class of the referenced element.
     *
     * @param metaClassReference the meta-class
     */
    public void metaClassReference(BasicMetaclass metaClassReference) {
        this.metaClassReference = metaClassReference;
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
