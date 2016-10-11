/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
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
 * An {@code EObject object} able to persist in a database.
 */
public interface PersistentEObject extends InternalEObject {

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
     *
     * returns true if this object is mapped to an entity (table, node, column, etc.) stored in a database.
     *
     * @return
     */
    boolean isMapped();

    /**
     *
     * Sets the mapped attribute.
     *
     * @param mapped
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
     * @param resource
     */
    void resource(Resource.Internal resource);
}
