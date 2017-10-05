/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Backend} that stores all elements in a database and provides specific methods for communicating with the
 * database that it uses. Each {@code PersistentBackend} manage one single instance of a database.
 */
@ParametersAreNonnullByDefault
public interface PersistentBackend extends Backend {

    @Override
    default boolean isTransient() {
        return false;
    }

    @Override
    default boolean isPersistent() {
        return true;
    }
}

