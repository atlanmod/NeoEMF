/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The default {@link Backend} that stores all elements in an in-memory store.
 */
@ParametersAreNonnullByDefault
public interface InMemoryBackend extends Backend {

    @Override
    default boolean isPersistent() {
        return false;
    }

    @Override
    default boolean isDistributed() {
        return false;
    }
}
