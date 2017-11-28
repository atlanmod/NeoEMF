/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;

/**
 * Exception thrown when an {@link fr.inria.atlanmod.neoemf.core.Id} is already bound to a {@link
 * fr.inria.atlanmod.neoemf.data.bean.ClassBean}.
 *
 * @see ClassMapper#metaClassFor(Id, ClassBean)
 */
public class ClassAlreadyExistsException extends RuntimeException {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -2787372409937902858L;

    /**
     * Constructs an {@code ClassAlreadyExistsException} with {@code null} as its error detail message.
     */
    public ClassAlreadyExistsException() {
    }

    /**
     * Constructs an {@code ClassAlreadyExistsException} with the specified detail {@code message}.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public ClassAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code ClassAlreadyExistsException} with the specified {@code cause} and its detail message.
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null}
     *              value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ClassAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an {@code ClassAlreadyExistsException} with the specified detail {@code message} and {@code cause}.
     * <p>
     * The detail message associated with cause is not automatically incorporated into this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null}
     *                value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ClassAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
