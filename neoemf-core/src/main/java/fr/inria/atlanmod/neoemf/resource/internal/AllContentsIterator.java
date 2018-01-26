/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.resource.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;

import java.util.Iterator;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Iterator} that iterates over all content of a {@link PersistentResource}.
 */
@ParametersAreNonnullByDefault
public class AllContentsIterator extends AbstractTreeIterator<EObject> {

    /**
     * Constructs a new {@code AllContentsIterator}.
     *
     * @param resource    the root resource of this iterator
     * @param includeRoot the the {@code root} must be included in iteration
     */
    public AllContentsIterator(PersistentResource resource, boolean includeRoot) {
        super(resource, includeRoot);
    }

    @Nonnull
    @Override
    protected Iterator<? extends EObject> getChildren(Object object) {
        return object == this.object
                ? PersistentResource.class.cast(object).getContents().iterator()
                : PersistentEObject.class.cast(object).eContents().iterator();
    }
}
