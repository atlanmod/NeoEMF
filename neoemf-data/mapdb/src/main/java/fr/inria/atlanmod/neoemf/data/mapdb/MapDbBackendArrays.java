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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithArrays;

import org.mapdb.DB;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link MapDbBackend} that use a {@link ManyValueWithArrays} mapping for storing features.
 *
 * @see MapDbBackendFactory
 */
@ParametersAreNonnullByDefault
class MapDbBackendArrays extends AbstractMapDbBackend implements ManyValueWithArrays {

    /**
     * Constructs a new {@code MapDbBackendArrays} wrapping the provided {@code db}.
     *
     * @param db the {@link DB} used to creates the used {@link java.util.concurrent.ConcurrentMap}s and manage the
     *           database
     *
     * @see MapDbBackendFactory
     */
    protected MapDbBackendArrays(DB db) {
        super(db);
    }
}
