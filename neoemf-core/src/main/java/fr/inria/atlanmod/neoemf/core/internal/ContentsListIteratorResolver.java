/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ContentsListIterator} that resolves proxies.
 *
 * @param <E> the type of elements in this iterator
 */
@ParametersAreNonnullByDefault
class ContentsListIteratorResolver<E extends EObject> extends ContentsListIterator<E> {

    /**
     * Constructs a new {@code ContentsListIteratorResolver} for the given {@code owner}.
     *
     * @param owner    the owner of this iterator
     * @param features the containment features that are handled by this iterator
     */
    public ContentsListIteratorResolver(PersistentEObject owner, EStructuralFeature[] features) {
        super(owner, features);
    }

    @Override
    protected boolean resolve() {
        return true;
    }
}
