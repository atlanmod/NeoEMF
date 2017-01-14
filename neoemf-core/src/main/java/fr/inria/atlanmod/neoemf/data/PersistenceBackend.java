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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.Closeable;

/**
 * An adapter on top of a database that provides specific methods for communicating with a database implementation.
 * <p>
 * Each {@code PersistenceBackend} manage one single instance of a database.
 *
 * @future an abstraction of {@code PersistenceBackend} will be implemented to define a global behaviour
 */
public interface PersistenceBackend extends Closeable {

    /**
     * Returns whether the underlying database has been started or not.
     *
     * @return {@code true} if the database is closed, otherwise {@code false}
     */
    boolean isClosed();

    /**
     * {@inheritDoc}
     * <p>
     * In our case, it cleanly stops the underlying database.
     */
    @Override
    void close();

    /**
     * Saves the modifications of the owned {@link EObject}s in the underlying database.
     */
    void save();

    /**
     * Back-end specific computation of {@link Resource#getAllContents()}.
     *
     * @param eClass the class to compute the instances of
     * @param strict {@code true} if the lookup searches for strict instances
     *
     * @return an {@link Object} containing the back-end specific objects corresponding to the instances of the {@link
     * EClass}
     *
     * @throws UnsupportedOperationException if the back-end does not support all instances lookup
     */
    default Object getAllInstances(EClass eClass, boolean strict) {
        throw new UnsupportedOperationException("This back-end does not support custom all instances computation");
    }
}

