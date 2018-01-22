/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package ${package};

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Backend} that is responsible of low-level access to a ${databaseName} database.
 * <p>
 * It wraps an existing ${databaseName} database and provides facilities to create and retrieve elements.
 * <p>
 * <b>Note:</b> Instances of {@code ${databaseName}Backend} are created by {@link ${databaseName}BackendFactory} that
 * provides an usable database that can be manipulated by this wrapper.
 *
 * @see ${databaseName}BackendFactory
 */
@ParametersAreNonnullByDefault
public interface ${databaseName}Backend extends Backend {

    @Override
    default boolean isPersistent() {
        // TODO Implement this method
        throw Throwables.notImplementedYet("isPersistent");
    }

    @Override
    default boolean isDistributed() {
        // TODO Implement this method
        throw Throwables.notImplementedYet("isDistributed");
    }
}
