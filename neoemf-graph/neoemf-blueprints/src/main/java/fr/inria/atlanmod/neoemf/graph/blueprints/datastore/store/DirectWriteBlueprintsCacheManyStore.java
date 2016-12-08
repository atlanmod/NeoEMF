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

package fr.inria.atlanmod.neoemf.graph.blueprints.datastore.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource.Internal;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class DirectWriteBlueprintsCacheManyStore extends DirectWriteBlueprintsStore {

    // TODO Cache many properties in addition to vertices
    private final Cache<FeatureKey, Object[]> verticesCache;

    public DirectWriteBlueprintsCacheManyStore(Internal resource, BlueprintsPersistenceBackend graph) {
        super(resource, graph);
        this.verticesCache = Caffeine.newBuilder().maximumSize(10000).build();
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference eReference, int index) {
        if (eReference.isMany()) {
            FeatureKey key = FeatureKey.from(object, eReference);
            Object[] list = verticesCache.getIfPresent(key);
            if (nonNull(list)) {
                Object o = list[index];
                if (isNull(o)) {
                    NeoLogger.warn("Inconsistent content in CachedMany map, null value found for key " + key + " at index " + index);
                    return super.get(object, eReference, index);
                }
                else {
                    NeoLogger.debug("Found in cache {0} - {1} - idx={2}", key, object.eClass().getName(), index);
                    return reifyVertex((Vertex) o);
                }
            }
            else {
                Vertex vertex = persistenceBackend.getVertex(object.id());
                Integer size = getSize(vertex, eReference);
                Object[] vertices = new Object[size];
                verticesCache.put(key, vertices);
                if (index < 0 || index >= size) {
                    NeoLogger.error("Invalid get index {0}", index);
                    throw new IndexOutOfBoundsException("Invalid get index " + index);
                }
                for (Edge edge : vertex.getEdges(Direction.OUT, eReference.getName())) {
                    if (isNull(edge.getProperty(POSITION))) {
                        NeoLogger.error("An edge corresponding to the many EReference {0} does not have a position property", eReference.getName());
                        throw new RuntimeException("An edge corresponding to the many EReference " + eReference.getName() + " does not have a position property");
                    }
                    else {
                        Integer position = edge.getProperty(POSITION);
                        Vertex otherEnd = edge.getVertex(Direction.IN);
                        NeoLogger.debug("Putting in cache {0} - {1} - idx={2}", key, object.eClass().getName(), position);
                        vertices[position] = otherEnd;
                    }
                }
                return reifyVertex((Vertex) vertices[index]);
            }
        }
        else {
            return super.getReference(object, eReference, index);
        }
    }
}
