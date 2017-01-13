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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

/**
 * The abstract implementation of a {@link PersistentStore}.
 */
public abstract class AbstractPersistentStore implements PersistentStore {

    /**
     * Instantiates a new {@code AbstractPersistentStore}.
     */
    public AbstractPersistentStore() {
        NeoLogger.info("{0} created", getClass().getSimpleName());
    }
}
