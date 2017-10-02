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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link DataMapper} that adds pre-processing and post-processing capabilities to data persistence.
 */
@ParametersAreNonnullByDefault
public interface Store extends DataMapper {

    /**
     * Returns the back-end where data are stored.
     *
     * @return the back-end
     */
    @Nonnull
    Backend backend();

    /**
     * Returns a current snapshot of this store chain's cumulative statistics. All statistics are initialized to zero,
     * and are monotonically increasing over the lifetime of the store chain.
     *
     * @return the current snapshot of the statistics of this store chain
     *
     * @see Config#recordStats()
     */
    @Nonnull
    StoreStats stats();
}
