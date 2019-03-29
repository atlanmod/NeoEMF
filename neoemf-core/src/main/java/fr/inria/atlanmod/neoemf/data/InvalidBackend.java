/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.mapping.InvalidDataMapper;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An invalid {@link Backend} that throws an {@link UnsupportedOperationException} at each call.
 */
@ParametersAreNonnullByDefault
public final class InvalidBackend extends InvalidDataMapper implements Backend {

    /**
     * Constructs a new {@code InvalidBackend} with the reason that explain why it is invalid.
     *
     * @param reason the reason that explain why this backend is invalid
     */
    public InvalidBackend(String reason) {
        super(() -> new UnsupportedOperationException(reason));
    }

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    public void copyTo(DataMapper target) {
        // Do nothing instead of throwing an exception (see PersistentResource#save())
    }
}
