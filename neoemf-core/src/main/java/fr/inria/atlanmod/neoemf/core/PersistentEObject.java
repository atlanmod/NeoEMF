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

package fr.inria.atlanmod.neoemf.core;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An {@link org.eclipse.emf.ecore.EObject} able to persist in a data store.
 */
public interface PersistentEObject extends InternalEObject {

    /**
     * Returns the given {@code object} as a {@code PersistentEObject}.
     *
     * @param object the object to adapt
     *
     * @return an adapted object as a {@code PersistentEObject}, or {@code null} if the {@code object} cannot be
     * assigned as a {@code PersistentEObject}
     */
    @Nullable
    static PersistentEObject from(@Nullable Object object) {
        return PersistentEObjectAdapter.getAdapter(object);
    }

    /**
     * Returns the identifier of this {@code PersistentEObject}.
     *
     * @return the identifier
     */
    @Nonnull
    Id id();

    /**
     * Defines the new identifier of this {@code PersistentEObject}.
     *
     * @param id the new identifier
     */
    void id(@Nonnull Id id);

    /**
     * Returns {@code true} if this {@code PersistentEObject} is mapped to an entity stored in a database.
     *
     * @return {@code true} if this {@code PersistentEObject} is mapped to an entity stored in a database
     */
    boolean isMapped();

    /**
     * Defines whether this {@code PersistentEObject} is mapped to an entity stored in a database.
     *
     * @param mapped {@code true} if this {@code PersistentEObject} is mapped, otherwise {@code false}
     */
    void setMapped(boolean mapped);

    /**
     * Returns the resource that contains this {@code PersistentEObject}.
     *
     * @return the containing resource
     */
    @Nullable
    Resource.Internal resource();

    /**
     * Defines the resource that containsthis {@code PersistentEObject}.
     *
     * @param resource the containing resource
     */
    void resource(@Nullable Resource.Internal resource);
}
