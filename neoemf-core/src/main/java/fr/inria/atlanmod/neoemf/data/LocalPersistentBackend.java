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
 * A {@link PersistentBackend} that stores data locally, in a embbeded database or a {@link java.io.File}. Concurrent
 * modifications are not supported.
 */
@ParametersAreNonnullByDefault
public interface LocalPersistentBackend extends PersistentBackend {

    @Override
    default boolean isDistributed() {
        return false;
    }
}
