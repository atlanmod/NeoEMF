/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * This interface extends the {@link EStore} interface and allows to establish a mapping between {@link Resource}s and
 * {@link EStore}s.
 */
public interface PersistentStore extends EStore {

    int NO_INDEX = EStore.NO_INDEX;

    /**
     * Returns the {@link Resource} to which this {@link EStore} is associated.
     */
    Resource resource();

    /**
     * Returns the resolved {@link EObject} identified by the given {@code id} or {@code null} if no {@link EObject} can
     * be resolved.
     */
    EObject eObject(Id id);

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
     * Utility method used for tests. Returns the decorated {@link PersistentStore}, if it exists.
     */
    PersistentStore getEStore();

    /**
     * Saves the modifications of the owned {@link org.eclipse.emf.ecore.EObject}s in the persistence back-end.
     */
    void save();
}
