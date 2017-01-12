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
 * An adapter on top of a data store that provides specific methods for communicating with a data store implementation.
 * <p>
 * Each {@code PersistenceBackend} manage one single instance of a data store.
 *
 * @future an abstraction of {@code PersistenceBackend} will be implemented to define a global behaviour
 */
public interface PersistenceBackend extends Closeable {

    /**
     * Returns whether the underlying data store has been started or not.
     *
     * @return {@code true} if the data store is closed, otherwise {@code false}
     */
    boolean isClosed();

    /**
     * {@inheritDoc}
     *
     * In our case, it cleanly stops the underlying data store.
     */
    @Override
    void close();

    /**
     * Saves the modifications of the owned {@link EObject}s in the underlying data store.
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
        throw new UnsupportedOperationException("This backend does not support custom all instances computation");
    }
}

