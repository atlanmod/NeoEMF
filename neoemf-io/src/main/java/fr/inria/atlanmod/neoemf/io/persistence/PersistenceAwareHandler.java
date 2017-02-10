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
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.Handler;

import java.util.NoSuchElementException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Handler} for {@link PersistenceBackend}s.
 *
 * @note This handler has a key conflicts resolution feature, but it consumes much more memory than a handler without
 * conflicts resolution. Make sure you have enough memory to avoid heap space.
 */
@ParametersAreNonnullByDefault
public class PersistenceAwareHandler extends AbstractPersistenceHandler {

    /**
     * Constructs a new {@code PersistenceAwareHandler} on the given {@code backend}.
     *
     * @param backend the back-end where to store data
     */
    protected PersistenceAwareHandler(PersistenceBackend backend) {
        super(backend);
    }

    @Override
    protected void persist(Id id) {
        if (backend.has(id)) {
            throw new AlreadyExistingIdException("Already existing Id: " + id);
        }
        backend.create(id);
    }

    @Override
    protected void checkExists(Id id) {
        if (!backend.has(id)) {
            throw new NoSuchElementException("Unable to find an element with Id: " + id);
        }
    }
}
