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

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import java.io.File;

/**
 * This state provided a ready-to-use datastore. It is preloaded and unloaded automatically from a temporary copy of the
 * default datastore, to avoid overwriting the original datastore.
 * <p/>
 * It is used for read/write queries.
 */
// TODO: Dynamically save in a temporary datastore
public class ReadWriteRunnerState extends ReadOnlyRunnerState {

    @Override
    protected File getStoreLocation() {
        try {
            return getBackend().copy(super.getStoreLocation());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
