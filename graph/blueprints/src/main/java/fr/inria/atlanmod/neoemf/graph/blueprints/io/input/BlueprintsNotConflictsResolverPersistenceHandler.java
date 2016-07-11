/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.io.input;

import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;

import java.util.concurrent.Callable;

/**
 * A {@link BlueprintsConflictsResolverPersistenceHandler} with <b>no key conflict resolution</b>.
 * <p/>
 * <b>NOTE :</b> Unlike the {@link BlueprintsConflictsResolverPersistenceHandler}, this handler does not solve key
 * conflicts. However, they are detected and an exception is raised to avoid the creation of an unusable backend.
 */
class BlueprintsNotConflictsResolverPersistenceHandler extends BlueprintsConflictsResolverPersistenceHandler {

    public BlueprintsNotConflictsResolverPersistenceHandler(BlueprintsPersistenceBackend persistenceBackend) {
        super(persistenceBackend);
    }

    @Override
    protected Vertex getVertex(final Id id) throws Exception {
        return loadedVertices.get(id, new Callable<Vertex>() {
            @Override
            public Vertex call() throws Exception {
                Vertex vertex = getPersistenceBackend().getVertex(id.toString());

                if (vertex == null) {
                    vertex = getPersistenceBackend().addVertex(id.toString());
                }

                return vertex;
            }
        });
    }

    @Override
    protected Vertex createVertex(final Id id) throws Exception {
        return getVertex(id);
    }
}
