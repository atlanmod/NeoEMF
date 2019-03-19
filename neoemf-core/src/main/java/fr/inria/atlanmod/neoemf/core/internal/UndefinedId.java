/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An undefined {@link Id}.
 * <p>
 * This implementation is used when a {@link PersistentEObject} is initialized without any identifier.
 */
@ParametersAreNonnullByDefault
public final class UndefinedId implements Id {

    /**
     * The message of the exception thrown when accessing an unsupported method.
     */
    @Nonnull
    private static final String MSG = "Undefined identifier";

    @Override
    public long toLong() {
        throw new IllegalStateException(MSG);
    }

    @Nonnull
    @Override
    public String toHexString() {
        throw new IllegalStateException(MSG);
    }

    @Override
    public int compareTo(Id o) {
        throw new IllegalStateException(MSG);
    }

    @Override
    public String toString() {
        return "Id {UNDEFINED}";
    }
}
