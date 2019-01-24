/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractDataMapperChain;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} that simply notifies its associated {@link fr.inria.atlanmod.neoemf.data.Backend}, without any
 * additional operation.
 * <p>
 * It corresponds to the tail of a {@link Store} chain and sets the default result for each method. These methods are
 * handled if the {@link Store} that is supposed to handle them has not been configured during the store chain
 * configuration.
 */
@ParametersAreNonnullByDefault
public class NoopStore extends AbstractDataMapperChain<Backend> implements Store {

    /**
     * Constructs a new {@code NoopStore} between the given {@code resource} and the {@code backend}.
     *
     * @param backend the back-end used to store the model
     */
    public NoopStore(Backend backend) {
        next(backend);
    }

    @Nonnull
    @Override
    public Backend backend() {
        return next();
    }
}
