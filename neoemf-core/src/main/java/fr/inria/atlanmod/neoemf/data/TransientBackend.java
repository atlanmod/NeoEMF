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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Backend} that stores all elements in an in-memory key/value store.
 */
@ParametersAreNonnullByDefault
public interface TransientBackend extends Backend {

    @Override
    default void save() {
        // No need to save anything
    }

    @Override
    default void copyTo(DataMapper target) {
        // TODO Implement this method
    }

    @Override
    default boolean isPersistent() {
        return false;
    }

    @Override
    default boolean isDistributed() {
        // A transient backend cannot be distributed
        return false;
    }
}
