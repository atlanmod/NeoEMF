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

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Backend} that stores all elements in a database and provides specific methods for communicating with the
 * database that it uses. Each {@code PersistentBackend} manage one single instance of a database.
 */
@ParametersAreNonnullByDefault
public interface PersistentBackend extends Backend {

    @Override
    default boolean isPersistent() {
        return true;
    }
}

