/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Backend} that is responsible of low-level access to a Blueprints database.
 * <p>
 * It wraps an existing Blueprints database and provides facilities to create and retrieve elements, map {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}s to {@code Vertex} elements in order to speed up attribute access,
 * and manage a set of lightweight caches to improve access time of {@code Vertex} from their corresponding {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}.
 */
@ParametersAreNonnullByDefault
public interface BlueprintsBackend extends Backend {

    @Override
    default boolean isPersistent() {
        return true;
    }

    @Override
    default boolean isDistributed() {
        return false;
    }
}
