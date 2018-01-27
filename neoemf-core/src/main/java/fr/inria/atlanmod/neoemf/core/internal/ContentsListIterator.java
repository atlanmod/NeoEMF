/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;

import java.util.ListIterator;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ListIterator} that iterates over all the content of a {@link PersistentEObject}.
 *
 * @param <E> the type of elements in this iterator
 *
 * @see PersistentEObject#eContents()
 * @see PersistentEObject#eAllContents()
 */
@ParametersAreNonnullByDefault
class ContentsListIterator<E> extends EContentsEList.FeatureIteratorImpl<E> {

    /**
     * Constructs a new {@code ContentsListIterator} for the given {@code owner}.
     *
     * @param owner    the owner of this iterator
     * @param features the containment features that are handled by this iterator
     */
    public ContentsListIterator(PersistentEObject owner, EStructuralFeature[] features) {
        super(owner, features);
    }

    @Override
    protected final boolean useIsSet() {
        return false;
    }
}
