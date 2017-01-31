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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * An {@link EStore} to establish a mapping between {@link Resource}s and {@link
 * fr.inria.atlanmod.neoemf.data.PersistenceBackend}s.
 */
public interface PersistentStore extends EStore {

    /**
     * A value indicating that no index is specified.
     */
    int NO_INDEX = EStore.NO_INDEX;

    /**
     * Returns the {@link Resource} to persist and access.
     *
     * @return the resource to persist and access
     */
    Resource resource();

    /**
     * Returns the resolved {@link PersistentEObject} identified by the given {@code id} or {@code null}.
     *
     * @param id the identifier of the {@link PersistentEObject} to resolve
     *
     * @return the resolved {@link PersistentEObject}, or {@code null} if no {@link PersistentEObject} can be resolved
     */
    PersistentEObject eObject(Id id);

    /**
     * Back-end specific computation of {@link Resource#getAllContents()}.
     *
     * @param eClass the {@link EClass} to compute the instances of
     * @param strict {@code true} if the lookup searches for strict instances
     *
     * @return an {@link EList} containing all the {@link EObject}s that are instances of the given {@link EClass}
     *
     * @throws UnsupportedOperationException if the back-end does not support custom all instances computation
     */
    default EList<EObject> getAllInstances(EClass eClass, boolean strict) {
        throw new UnsupportedOperationException();
    }

    /**
     * Saves the modifications of the owned {@link EObject}s in the persistence back-end.
     */
    void save();

    @Override
    default EObject create(EClass eClass) {
        throw new IllegalStateException("This method should not be called");
    }
}
