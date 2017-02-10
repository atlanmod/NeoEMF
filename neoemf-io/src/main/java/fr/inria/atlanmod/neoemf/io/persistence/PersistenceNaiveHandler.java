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

package fr.inria.atlanmod.neoemf.io.persistence;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.io.Handler;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Handler} for {@link PersistenceBackend}s, <b>without</b> key conflict resolution.
 *
 * @note Unlike the {@link PersistenceAwareHandler}, this handler does not solve key conflicts. However, they are
 * detected and an exception is raised to avoid the creation of an unusable back-end.
 */
@ParametersAreNonnullByDefault
public class PersistenceNaiveHandler extends AbstractPersistenceHandler {

    /**
     * Constructs a new {@code PersistenceNaiveHandler} on the given {@code backend}.
     *
     * @param backend the back-end where to store data
     */
    protected PersistenceNaiveHandler(PersistenceBackend backend) {
        super(backend);
    }

    @Override
    protected void persist(Id id) {
        checkExists(id);
    }

    @Override
    protected void checkExists(Id id) {
        if (!backend.has(id)) {
            backend.create(id);
        }
    }
}
