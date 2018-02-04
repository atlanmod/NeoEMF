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

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a {@link EReference}.
 */
@ParametersAreNonnullByDefault
public class BasicReference extends AbstractBasicFeature<BasicReference, EReference, Id> {

    /**
     * Whether this reference is a containment.
     */
    private boolean isContainment = false;

    /**
     * The string representation of the identifier of the referenced element(s).
     */
    private String rawValue;

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

    /**
     * Returns the string representation of the identifier of the referenced element(s). If the reference is
     * multi-valued, they will be delimited by a space character.
     *
     * @return the string representation of the identifier of the referenced element(s)
     */
    public String rawValue() {
        return rawValue;
    }

    /**
     * Defines the string representation of the identifier of the referenced element(s).
     *
     * @param rawValue the string representation of the identifier of the referenced element(s)
     *
     * @return this instance (for chaining)
     */
    public BasicReference rawValue(@Nullable String rawValue) {
        this.rawValue = rawValue;

        return me();
    }
}
