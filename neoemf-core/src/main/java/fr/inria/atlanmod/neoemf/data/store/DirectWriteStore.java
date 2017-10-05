/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractMapperDecorator;

import java.util.Collections;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} that translates model-level operations into datastore calls.
 * <p>
 * This store corresponds to the tail of a {@link Store} chain and sets the default result for each method. These
 * methods are handled if the {@link Store} that is supposed to handle them has not been configured during the store
 * chain configuration.
 */
@ParametersAreNonnullByDefault
public class DirectWriteStore extends AbstractMapperDecorator<Backend> implements Store {

    /**
     * Constructs a new {@code DirectWriteStore} between the given {@code resource} and the {@code backend}.
     *
     * @param backend the back-end used to store the model
     */
    public DirectWriteStore(Backend backend) {
        super(backend);
    }

    @Nonnull
    @Override
    public Backend backend() {
        return next();
    }

    @Nonnull
    @Override
    public StoreStats stats() {
        return new StoreStats(Collections.emptyMap());
    }
}