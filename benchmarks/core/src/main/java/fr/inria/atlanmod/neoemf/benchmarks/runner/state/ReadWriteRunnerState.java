/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import java.io.File;

import javax.annotation.Nonnull;

/**
 * This state provides a ready-to-use datastore. It is preloaded and unloaded automatically from a temporary copy of the
 * default datastore, to avoid overwriting the original datastore.
 * <p/>
 * It is used for read/write queries.
 */
public class ReadWriteRunnerState extends ReadOnlyRunnerState {

    @Nonnull
    @Override
    protected File storeFile() {
        try {
            return adapter().copy(super.storeFile());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
