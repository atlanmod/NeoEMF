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

import fr.inria.atlanmod.neoemf.data.store.PersistentStore;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link EObject} identified by a unique {@link Id}, able to persist in {@link
 * fr.inria.atlanmod.neoemf.data.PersistenceBackend}.
 */
@ParametersAreNonnullByDefault
public interface PersistentEObject extends InternalEObject {

    /**
     * Returns the given {@code object} as a {@code PersistentEObject}.
     *
     * @param object the object to adapt
     *
     * @return an adapted object as a {@code PersistentEObject}, or {@code null} if the {@code object} cannot be
     * assigned as a {@code PersistentEObject}
     */
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
     * Defines the identifier of this {@code PersistentEObject}.
     *
     * @param id the identifier
     */
    void id(Id id);

    /**
     * Returns whether this {@code PersistentEObject} is mapped to an entity stored in a {@link
     * fr.inria.atlanmod.neoemf.data.PersistenceBackend}.
     *
     * @return {@code true} if this {@code PersistentEObject} is mapped, otherwise {@code false}
     */
    boolean isPersistent();

    /**
     * Defines whether this {@code PersistentEObject} is mapped to an entity stored in a {@link
     * fr.inria.atlanmod.neoemf.data.PersistenceBackend}.
     *
     * @param isPersistent {@code true} if this {@code PersistentEObject} is mapped, otherwise {@code false}
     *
     * @return this object (for chaining)
     */
    PersistentEObject isPersistent(boolean isPersistent);

    /**
     * Returns the resource that contains this {@code PersistentEObject}.
     *
     * @return the containing resource
     */
    @Nullable
    Resource.Internal resource();

    /**
     * Defines the resource that contains this {@code PersistentEObject}.
     *
     * @param resource the containing resource
     */
    void resource(@Nullable Resource.Internal resource);

    @Override
    PersistentStore eStore();
}
