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

/**
 * The abstract implementation of a {@link PersistenceBackend}.
 *
 * @future an abstraction of {@code PersistenceBackend} will be implemented to define a global behaviour
 */
public abstract class AbstractPersistenceBackend implements PersistenceBackend {

    /**
     * Instantiates a new {@code AbstractPersistenceBackend}.
     */
    protected AbstractPersistenceBackend() {
    }
}
