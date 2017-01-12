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

package fr.inria.atlanmod.neoemf.io;

/**
 * Exception thrown when an {@link fr.inria.atlanmod.neoemf.core.Id} is already defined in a data store.
 */
public class AlreadyExistingIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new {@code AlreadyExistingIdException} with a detail message.
     *
     * @param message the detail message
     */
    public AlreadyExistingIdException(String message) {
        super(message);
    }
}
