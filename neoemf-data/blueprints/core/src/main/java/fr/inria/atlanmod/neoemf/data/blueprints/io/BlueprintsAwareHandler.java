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

package fr.inria.atlanmod.neoemf.data.blueprints.io;

import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;

import java.util.NoSuchElementException;

/**
 * A {@link PersistenceHandler} for {@link BlueprintsPersistenceBackend}s.
 * <p>
 * This handler has a key conflicts resolution feature, but it consumes much more memory than a handler
 * without conflicts resolution. Make sure you have enough memory to avoid heap space.
 */
public class BlueprintsAwareHandler extends AbstractBlueprintsHandler {

    /**
     * Constructs a new {@code BlueprintsAwareHandler} on the given {@code backend}.
     *
     * @param backend the back-end where to store data
     */
    protected BlueprintsAwareHandler(BlueprintsPersistenceBackend backend) {
        super(backend);
    }

    @Override
    protected Vertex getVertex(final Id id) {
        try {
            return verticesCache.get(id, key -> getPersistenceBackend().getVertex(key));
        }
        catch (Exception e) {
            throw new NoSuchElementException("Unable to find an element with Id '" + id.toString() + "'");
        }
    }

    @Override
    protected Vertex createVertex(final Id id) {
        try {
            return verticesCache.get(id, key -> getPersistenceBackend().addVertex(key));
        }
        catch (Exception e) {
            throw new AlreadyExistingIdException("Already existing Id '" + id.toString() + "'");
        }
    }
}
