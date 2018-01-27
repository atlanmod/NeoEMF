/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;

import java.util.Iterator;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Iterator} that iterates over all content of a {@link PersistentEObject}.
 */
@ParametersAreNonnullByDefault
public class AllContentsIterator<E extends EObject> extends AbstractTreeIterator<E> {

    /**
     * Constructs a new {@code AllContentsIterator}.
     *
     * @param root        the root object of this iterator
     * @param includeRoot the the {@code root} must be included in iteration
     */
    public AllContentsIterator(PersistentEObject root, boolean includeRoot) {
        super(root, includeRoot);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    protected Iterator<? extends E> getChildren(Object object) {
        return (Iterator<? extends E>) PersistentEObject.class.cast(object).eContents().iterator(); // ContentsList#iterator()
    }
}
