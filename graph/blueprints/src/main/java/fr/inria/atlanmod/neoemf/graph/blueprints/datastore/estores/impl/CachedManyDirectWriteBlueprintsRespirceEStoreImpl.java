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
package fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource.Internal;

import static java.util.Objects.isNull;

public class CachedManyDirectWriteBlueprintsRespirceEStoreImpl extends
        DirectWriteBlueprintsResourceEStoreImpl {

    // Cache Object[] instead of Vertex[]
    // because TODO cache many properties in addition to vertices
    private final Cache<CacheKey, Object[]> cache;

    public CachedManyDirectWriteBlueprintsRespirceEStoreImpl(Internal resource,
                                                             BlueprintsPersistenceBackend graph) {
        super(resource, graph);
        cache = CacheBuilder.newBuilder().softValues().build();
        NeoLogger.info("CachedManyBlueprintsResourceEStore Created");
    }

    @Override
    protected Object getReference(PersistentEObject object, EReference eReference, int index) {
        if (eReference.isMany()) {
            CacheKey key = new CacheKey(object.id(), eReference);
            Object[] list = cache.getIfPresent(key);
            if (!isNull(list)) {
                Object o = list[index];
                if (isNull(o)) {
                    NeoLogger.warn("Inconsistent content in CachedMany map, null value found for key " + key.toString() + " at index " + index);
                    return super.get(object, eReference, index);
                } else {
                    NeoLogger.debug("Found in cache " + key.toString() + "-" + object.eClass().getName() + "- idx=" + index);
                    return reifyVertex((Vertex) o);
                }
            } else {
                Vertex vertex = persistenceBackend.getVertex(object);
                Integer size = getSize(vertex, eReference);
                Object[] vertices = new Object[size];
                cache.put(key, vertices);
                if (index < 0 || index >= size) {
                    NeoLogger.error("Invalid get index " + index);
                    throw new IndexOutOfBoundsException("Invalid get index " + index);
                }
                for (Edge edge : vertex.getEdges(Direction.OUT, eReference.getName())) {
                    if (isNull(edge.getProperty(POSITION))) {
                        NeoLogger.error("An edge corresponding to the many EReference " + eReference.getName() + " does not have a position property");
                        throw new RuntimeException("An edge corresponding to the many EReference " + eReference.getName() + " does not have a position property");
                    } else {
                        Integer position = edge.getProperty(POSITION);
                        Vertex otherEnd = edge.getVertex(Direction.IN);
                        NeoLogger.debug("Putting in cache " + key.toString() + "-" + object.eClass().getName() + "- idx=" + position);
                        vertices[position] = otherEnd;
                    }
                }
                return reifyVertex((Vertex) vertices[index]);
            }
        } else {
            return super.getReference(object, eReference, index);
        }
    }

    private static class CacheKey {

        public final Id id;
        public final EStructuralFeature feature;

        public CacheKey(Id id, EStructuralFeature feature) {
            this.id = id;
            this.feature = feature;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof CacheKey) {
                return id.equals(((CacheKey) obj).id) && feature.equals(((CacheKey) obj).feature);
            }
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return id.hashCode() + feature.hashCode();
        }

        @Override
        public String toString() {
            return "(" + id.toString() + "," + feature.getName() + ")";
        }

    }

}
