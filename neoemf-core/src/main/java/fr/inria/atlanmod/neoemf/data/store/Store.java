/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper} that adds pre-processing and post-processing capabilities
 * to data persistence.
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
}
