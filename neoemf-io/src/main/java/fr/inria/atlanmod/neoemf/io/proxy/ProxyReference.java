/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.proxy;

import fr.inria.atlanmod.neoemf.core.Id;

import org.eclipse.emf.ecore.EReference;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a {@link org.eclipse.emf.ecore.EReference}.
 */
@ParametersAreNonnullByDefault
public class ProxyReference extends AbstractProxyFeature<ProxyReference, EReference, Id> {

    /**
     * Whether this reference is a containment.
     */
    private boolean containment;

    /**
     * Creates a new {@code ProxyReference} from another, without its values.
     *
     * @param base the reference to copy
     *
     * @return a new {@code ProxyReference}
     */
    @Nonnull
    public static ProxyReference copy(ProxyReference base) {
        return new ProxyReference().setOwner(base.getOwner()).setId(base.getId()).setOrigin(base.getOrigin());
    }

    @Nonnull
    @Override
    public ProxyReference setOrigin(EReference eReference) {
        return super.setOrigin(eReference).isContainment(eReference.isContainment());
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
    public ProxyReference isContainment(boolean isContainment) {
        this.containment = isContainment;

        return me();
    }
}
