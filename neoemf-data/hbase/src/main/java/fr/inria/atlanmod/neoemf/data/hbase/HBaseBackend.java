/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Backend} that is responsible of low-level access to a HBase database.
 */
@ParametersAreNonnullByDefault
public interface HBaseBackend extends Backend {

    @Override
    default boolean isPersistent() {
        return true;
    }

    @Override
    default boolean isDistributed() {
        return true;
    }
}
