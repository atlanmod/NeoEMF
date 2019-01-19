/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.neoemf.core.internal.PersistenceAdapter;
import fr.inria.atlanmod.neoemf.data.store.Storable;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Iterator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link EObject} identified by a unique {@link Id}, able to persist in {@link
 * fr.inria.atlanmod.neoemf.data.Backend}.
 */
@ParametersAreNonnullByDefault
public interface PersistentEObject extends InternalEObject, Storable, Iterable<PersistentEObject> {

    /**
     * Returns the given {@code object} as a {@code PersistentEObject}.
     *
     * @param object the object to adapt
     *
     * @return an adapted object as a {@code PersistentEObject}
     *
     * @throws NullPointerException     if the {@code object} is {@code null}
     * @throws IllegalArgumentException if the {@code object} cannot be adapted as a {@code PersistentEObject}
     */
    @Nonnull
    static PersistentEObject from(Object object) {
        return PersistenceAdapter.getInstance().adapt(object);
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
     * @param newId the identifier
     */
    void id(Id newId);

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
     * @param newResource the containing resource
     */
    void resource(@Nullable Resource.Internal newResource);

    @Override
    PersistentEObject eInternalContainer();

    /**
     * Returns an iterator on the direct content of this object.
     *
     * @return an iterator
     *
     * @see #eContents()
     */
    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    default Iterator<PersistentEObject> iterator() {
        return (Iterator) eContents().iterator();
    }
}
