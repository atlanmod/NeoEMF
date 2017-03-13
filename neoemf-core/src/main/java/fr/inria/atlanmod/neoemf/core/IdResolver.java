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

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object able to retrieve a {@link PersistentEObject} from an {@link Id}.
 */
@ParametersAreNonnullByDefault
public interface IdResolver {

    /**
     * Retrieves the {@link PersistentEObject} associated to the given {@code id}.
     *
     * @param id the identifier to resolve
     *
     * @return the {@link PersistentEObject} associated to the given {@code id}, or {@code null} if no object can be
     * retrieved with the {@code id}
     */
    @Nullable
    PersistentEObject resolve(Id id);
}
