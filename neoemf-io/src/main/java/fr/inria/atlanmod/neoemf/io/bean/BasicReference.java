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

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a {@link org.eclipse.emf.ecore.EReference}.
 */
@ParametersAreNonnullByDefault
public class BasicReference extends AbstractBasicFeature<BasicReference, EReference, Id> {

    /**
     * Whether this reference is a containment.
     */
    private boolean containment;

    /**
     * Creates a new {@code BasicReference} from another, without its values.
     *
     * @param base the reference to copy
     *
     * @return a new {@code BasicReference}
     */
    @Nonnull
    public static BasicReference copy(BasicReference base) {
        return new BasicReference().setOwner(base.getOwner()).setId(base.getId()).setReal(base.getReal());
    }

    @Nonnull
    @Override
    public BasicReference setReal(EReference eReference) {
        return super.setReal(eReference).isContainment(eReference.isContainment());
    }

    /**
     * Returns {@code true} if this reference is a containment.
     *
     * @return {@code true} if this reference is a containment
     */
    public boolean isContainment() {
        return containment;
    }

    /**
     * Defines whether this reference is a containment.
     *
     * @param isContainment {@code true} if this reference is a containment
     *
     * @return this instance (for chaining)
     */
    @Nonnull
    public BasicReference isContainment(boolean isContainment) {
        this.containment = isContainment;

        return me();
    }
}
