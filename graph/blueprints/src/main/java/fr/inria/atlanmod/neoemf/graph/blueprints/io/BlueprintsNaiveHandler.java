/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.graph.blueprints.io;

import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;

import static java.util.Objects.isNull;

/**
 * A {@link PersistenceHandler} for a {@link BlueprintsPersistenceBackend}, <b>without</b> key conflict resolution.
 * <p/>
 * <b>NOTE :</b> Unlike the {@link BlueprintsAwareHandler}, this handler does not solve key conflicts. However, they are
 * detected and an exception is raised to avoid the creation of an unusable backend.
 */
public class BlueprintsNaiveHandler extends AbstractBlueprintsHandler {

    public BlueprintsNaiveHandler(BlueprintsPersistenceBackend persistenceBackend) {
        super(persistenceBackend);
    }

    @Override
    protected Vertex getVertex(final Id id) {
        return loadedVertices.get(id, key -> {
            Vertex vertex = getPersistenceBackend().getVertex(key);

            if (isNull(vertex)) {
                vertex = getPersistenceBackend().addVertex(key);
            }

            return vertex;
        });
    }

    @Override
    protected Vertex createVertex(final Id id) {
        return getVertex(id);
    }
}
