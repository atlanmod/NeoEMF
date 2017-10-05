/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Backend} that stores all elements in an in-memory store.
 */
@ParametersAreNonnullByDefault
public interface TransientBackend extends Backend {

    @Override
    default void save() {
        // No need to save anything
    }

    @Override
    default boolean isTransient() {
        return true;
    }

    @Override
    default boolean isPersistent() {
        return false;
    }

    @Override
    default boolean isDistributed() {
        return false;
    }
}
