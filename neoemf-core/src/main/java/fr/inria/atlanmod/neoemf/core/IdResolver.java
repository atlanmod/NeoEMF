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

package fr.inria.atlanmod.neoemf.core;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object able to retrieve a {@link PersistentEObject} from an {@link Id}.
 */
@FunctionalInterface
@ParametersAreNonnullByDefault
public interface IdResolver {

    /**
     * Retrieves the {@link PersistentEObject} associated to the given {@code id}.
     *
     * @param id the identifier to resolve
     *
     * @return the object associated to the given {@code id}
     *
     * @throws java.util.NoSuchElementException if no object can be retrieved with the {@code id}
     */
    @Nonnull
    PersistentEObject resolve(Id id);
}
