/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link DataMapper} that stores data.
 */
@ParametersAreNonnullByDefault
public interface Backend extends DataMapper {

    /**
     * Returns whether this back-end is transient, i.e., if it is stored in memory.
     *
     * @return {@code true} if the back-end is transient, {@code false} otherwise.
     */
    boolean isTransient();

    /**
     * Returns whether this back-end is persistent, i.e., if it is stored in a local database.
     *
     * @return {@code true} if the back-end is persistent, {@code false} otherwise.
     */
    boolean isPersistent();

    /**
     * Returns whether this back-end is distributed.
     *
     * @return {@code true} if the back-end is distributed, {@code false} otherwise.
     */
    boolean isDistributed();
}
