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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.Closeable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An adapter on top of a database that provides specific methods for communicating with the database that it uses.
 * Each {@code PersistenceBackend} manage one single instance of a database.
 * <p>
 * It does not provide model-level translation; these functions are handled by
 * {@link fr.inria.atlanmod.neoemf.data.store.DirectWriteStore}s.
 *
 * @future an abstraction of {@code PersistenceBackend}s will be implemented to define a global behaviour. For now, it
 * provides only basic methods for closing or saving a model, but later, it will provide generic methods to add, delete
 * or get a value.
 * @see fr.inria.atlanmod.neoemf.data.store.DirectWriteStore
 */
@ParametersAreNonnullByDefault
public interface PersistenceBackend extends PersistenceMapper, Closeable {

    /**
     * Saves the modifications of the owned {@link EObject}s in the underlying database.
     */
    void save();

    /**
     * {@inheritDoc}
     * <p>
     * In our case, it cleanly stops the underlying database.
     */
    @Override
    void close();

    /**
     * Returns whether the underlying database is closed.
     *
     * @return {@code true} if the database is closed, otherwise {@code false}
     */
    boolean isClosed();

    /**
     * Returns {@code true} if this {@code PersistenceBackend} is distributed.
     *
     * @return {@code true} if the back-end is distributed, {@code false} otherwise.
     */
    boolean isDistributed();

    /**
     * Copies all the contents of this {@code PersistenceBackend} to the {@code target} one.
     *
     * @param target the {@code PersistenceBackend} to copy the database contents to
     */
    void copyTo(PersistenceBackend target);

    /**
     * Back-end specific computation of {@link Resource#getAllContents()}.
     *
     * @param eClass the class to compute the instances of
     * @param strict {@code true} if the lookup searches for strict instances
     *
     * @return an {@link Object} containing the back-end specific objects corresponding to the instances of the {@link
     * EClass}
     *
     * @throws UnsupportedOperationException if the back-end doesn't support the lookup of all instances
     */
    @Nonnull
    default Iterable<Id> allInstances(EClass eClass, boolean strict) {
        throw new UnsupportedOperationException("This back-end doesn't support the lookup of all instances");
    }
}

