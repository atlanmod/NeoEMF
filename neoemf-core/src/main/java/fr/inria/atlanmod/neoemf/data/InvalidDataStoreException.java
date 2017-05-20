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

import fr.inria.atlanmod.neoemf.data.store.Store;

/**
 * Exception thrown when an error occurred when creating {@link Backend} or {@link Store} in {@link BackendFactory}.
 */
public class InvalidDataStoreException extends RuntimeException {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 7803703997361736845L;

    /**
     * Constructs an {@code InvalidDataStoreException} with {@code null} as its error detail message.
     */
    public InvalidDataStoreException() {
    }

    /**
     * Constructs an {@code InvalidDataStoreException} with the specified detail {@code message}.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public InvalidDataStoreException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code InvalidDataStoreException} with the specified {@code cause} and its detail message.
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null}
     *              value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidDataStoreException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an {@code InvalidDataStoreException} with the specified detail {@code message} and {@code cause}.
     * <p>
     * The detail message associated with cause is not automatically incorporated into this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null}
     *                value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidDataStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}

