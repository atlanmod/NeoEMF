/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.InvalidDataMapper;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A closed {@link Store}.
 * <p>
 * All methods throws a {@link IllegalStateException}.
 */
@ParametersAreNonnullByDefault
public final class ClosedStore extends InvalidDataMapper implements Store {

    /**
     * Constructs a new {@code ClosedStore}.
     */
    public ClosedStore() {
        super(() -> new IllegalStateException("Unable to execute a query on a closed store"));
    }

    @Nonnull
    @Override
    public Backend backend() {
        throw e.get();
    }
}
