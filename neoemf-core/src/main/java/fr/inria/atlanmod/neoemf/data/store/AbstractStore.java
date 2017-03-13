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

import fr.inria.atlanmod.neoemf.util.log.Log;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The abstract implementation of a {@link Store}.
 */
@ParametersAreNonnullByDefault
// TODO Remove inheritance from StoreAdapter
public abstract class AbstractStore extends StoreAdapter implements Store {

    /**
     * Constructs a new {@code AbstractStore}.
     */
    public AbstractStore() {
        Log.debug("{0} created", getClass().getSimpleName());
    }
}
