/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;

import javax.annotation.Nonnull;

/**
 * An object that can be stored in a {@link Store}.
 */
public interface Storable {

    /**
     * Returns the store used to store the model.
     *
     * @return the store
     */
    @Nonnull
    StoreAdapter eStore();
}
