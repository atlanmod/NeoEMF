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
 * A {@link PersistentBackend} that stores data locally, in a embbeded database or a {@link java.io.File}. Concurrent
 * modifications are not supported.
 */
@ParametersAreNonnullByDefault
public interface LocalPersistentBackend extends PersistentBackend {

    @Override
    default boolean isDistributed() {
        return false;
    }
}
