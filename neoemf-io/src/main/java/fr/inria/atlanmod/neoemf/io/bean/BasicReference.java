/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import fr.inria.atlanmod.neoemf.core.Id;

import org.eclipse.emf.ecore.EReference;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a {@link org.eclipse.emf.ecore.EReference}.
 */
@ParametersAreNonnullByDefault
public class BasicReference extends AbstractBasicFeature<BasicReference, EReference, Id> {

    /**
     * Whether this reference is a containment.
     */
    private boolean isContainment = false;

    /**
     * Returns {@code true} if this reference is a containment.
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
     *
     * @return this instance (for chaining)
     */
    public BasicReference isContainment(boolean isContainment) {
        this.isContainment = isContainment;

        return me();
    }

    @Override
    public BasicReference eFeature(EReference eFeature) {
        return super.eFeature(eFeature)
                .isContainment(eFeature.isContainment());
    }
}
