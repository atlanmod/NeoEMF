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

package fr.inria.atlanmod.neoemf.core;


import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * An {@link org.eclipse.emf.ecore.EObject object} able to persist in a database.
 */
public interface PersistentEObject extends InternalEObject {

    /**
     * Returns the given {@code object} as a {@link PersistentEObject}.
     *
     * @param object the object to adapt
     *
     * @return an adapted object as a {@link PersistentEObject}, or {@code null} if the {@code object} cannot be
     *         assigned as a {@link PersistentEObject}
     */
    static PersistentEObject from(Object object) {
        return PersistentEObjectAdapter.getAdapter(object);
    }

    /**
     * Returns the unique identifier of this persistent object.
     *
     * @return the unique identifier
     */
    Id id();

    /**
     * Defines the unique identifier of this persistent object.
     *
     * @param id a unique identifier
     */
    void id(Id id);

    /**
     * Returns {@code true} if this object is mapped to an entity (table, node, column, etc.) stored in a database.
     */
    boolean isMapped();

    /**
     * Sets the mapped attribute.
     */
    void setMapped(boolean mapped);

    /**
     * Returns the resource containing this persistent object.
     *
     * @return the containing resource.
     */
    Resource.Internal resource();

    /**
     * Sets the resource containing this persistent object.
     */
    void resource(Resource.Internal resource);
}
