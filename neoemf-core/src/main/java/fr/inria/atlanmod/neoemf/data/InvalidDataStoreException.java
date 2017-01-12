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

import java.io.IOException;

/**
 * Exception thrown when an error occurred when creating {@link PersistenceBackend back-end} or
 * {@link fr.inria.atlanmod.neoemf.data.store.PersistentStore store} in {@link PersistenceBackendFactory}.
 */
public class InvalidDataStoreException extends IOException {

    private static final long serialVersionUID = 1L;

    public InvalidDataStoreException() {
    }

    public InvalidDataStoreException(String message) {
        super(message);
    }

    public InvalidDataStoreException(Throwable t) {
        super(t);
    }

    public InvalidDataStoreException(String message, Throwable t) {
        super(message, t);
    }
}

