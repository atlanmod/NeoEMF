/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithLists;

import org.mapdb.DB;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link MapDbBackend} that use a {@link ManyValueWithLists} mapping for storing features.
 *
 * @see MapDbBackendFactory
 */
@ParametersAreNonnullByDefault
class MapDbBackendLists extends AbstractMapDbBackend implements ManyValueWithLists {

    /**
     * Constructs a new {@code MapDbBackendLists} wrapping the provided {@code db}.
     *
     * @param db the {@link DB} used to creates the used {@link java.util.concurrent.ConcurrentMap}s and manage the
     *           database
     *
     * @see MapDbBackendFactory
     */
    protected MapDbBackendLists(DB db) {
        super(db);
    }
}
