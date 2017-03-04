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

package fr.inria.atlanmod.neoemf.resource;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.Closeable;

/**
 * Extends the {@link Resource} interface by providing efficient model-level operations that
 * are not accessible using the standard EMF API. For example, {@code allInstances} is a
 * utility method that computes efficiently all the instances of a given type by delegating the
 * operation to the underlying database, that can benefits of its internal optimizations and indices.
 */
public interface PersistentResource extends Resource, Resource.Internal, Closeable {

    @Override
    void close();

    /**
     * Returns the {@link InternalEObject.EStore} used to store the model.
     *
     * @return the {@link InternalEObject.EStore}
     */
    InternalEObject.EStore store();

    /**
     * Checks whether this {@code PersistentResource} is mapped to a non-transient
     * {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend}.
     *
     * @return {@code true} if the resource is persistent, {@code false} otherwise
     */
    boolean isPersistent();

    /**
     * Checks whether this {@code PersistentResource} is mapped to a distributed
     * {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend}.
     *
     * @return {@code true} if the resource is distributed, {@code false} otherwise.
     */
    boolean isDistributed();

    /**
     * Computes the set of instances of the given {@link EClass} (including its sub-types).
     * <p>
     * This method is similar to {@link #allInstances(EClass, boolean)} with {@code strict = false}.
     *
     * @param eClass the {@link EClass} for which look for instances
     *
     * @return all the instances of the given {@link EClass} from the resource
     */
    default Iterable<EObject> allInstances(EClass eClass) {
        return allInstances(eClass, false);
    }

    /**
     * Computes the set of instances of the given {@link EClass}.
     *
     * @param eClass the {@link EClass} for which look for instances
     * @param strict {@code true} if the lookup searches for strict instances
     *
     * @return if {@code true} then the method returns only the strict instances of the given {@link EClass}, otherwise
     * it also returns the instances of the sub-types of {@code eClass}.
     */
    Iterable<EObject> allInstances(EClass eClass, boolean strict);
}
