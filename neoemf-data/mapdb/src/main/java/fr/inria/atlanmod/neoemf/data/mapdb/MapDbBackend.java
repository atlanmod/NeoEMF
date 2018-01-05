/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Backend} that is responsible of low-level access to a MapDB database.
 * <p>
 * It wraps an existing MapDB database and provides facilities to create and retrieve elements.
 * <p>
 * <b>NOTE:</b> Instances of {@code MapDbBackend} are created by {@link MapDbBackendFactory} that provides an usable
 * database that can be manipulated by this wrapper.
 *
 * @see MapDbBackendFactory
 */
@ParametersAreNonnullByDefault
public interface MapDbBackend extends Backend {

    @Override
    default boolean isPersistent() {
        return true;
    }

    @Override
    default boolean isDistributed() {
        return false;
    }
}
