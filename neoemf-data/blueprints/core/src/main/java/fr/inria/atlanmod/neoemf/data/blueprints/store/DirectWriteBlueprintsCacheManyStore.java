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

package fr.inria.atlanmod.neoemf.data.blueprints.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource.Internal;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link DirectWriteBlueprintsStore} subclass that uses an internal cache to store {@link Vertex} elements
 * that are part of multi-valued {@link EReference}s to speed-up their access.
 * <p>
 * Large multi-valued {@link EReference}s can be an execution time bottleneck in the graph implementation because any
 * element access forces the underlying database engine to load all the {@link Edge}s corresponding to the {@link EReference}.
 * We overcome this limitation by caching all the {@link Vertex} elements involved in multi-valued {@link EReference}s the first time they are
 * traversed, limiting database access. Note that the cache can contain up to {@code 10000} elements, limiting memory consumption. 
 * is accessed.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it
 * (see {@link AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 *
 * @see DirectWriteBlueprintsStore
 * @see BlueprintsPersistenceBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteBlueprintsCacheManyStore extends DirectWriteBlueprintsStore {

    // TODO Cache many properties in addition to vertices
    private final Cache<FeatureKey, Object[]> verticesCache;

    /**
     * Constructs a new {@code DirectWriteBlueprintsCacheManyStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend the persistence backend used to store the model
     */
    public DirectWriteBlueprintsCacheManyStore(Internal resource, BlueprintsPersistenceBackend backend) {
        super(resource, backend);
        this.verticesCache = Caffeine.newBuilder().maximumSize(10000).build();
    }

    /**
     * Custom implementation of {@link DirectWriteBlueprintsStore#getReference(PersistentEObject, EReference, int)} that
     * caches all the vertices traversed to retrieve the element at the given {@code index}. This implementation bypasses 
     * the graph API limitation that has to traverse all the {@link Edge}s connected to the input vertex to 
     * find the one with the given {@code index}.
     * @param object the input model element
     * @param reference the {@link EReference} to retrieve
     * @param index the index of the element to retrieve
     * @return a list of {@link EObject} if the given {@link EReference} is multi-valued, an {@link EObject} if 
     * it is single-valued, {@code null} if the element doesn't exist
     */
    @Override
    protected Object getReference(PersistentEObject object, EReference reference, int index) {
        if (reference.isMany()) {
            FeatureKey key = FeatureKey.from(object, reference);
            Object[] list = verticesCache.getIfPresent(key);
            if (nonNull(list)) {
                Object o = list[index];
                if (isNull(o)) {
                    NeoLogger.warn("Inconsistent content in CachedMany map, null value found for key " + key + " at index " + index);
                    return super.get(object, reference, index);
                }
                else {
                    NeoLogger.debug("Found in cache {0} - {1} - idx={2}", key, object.eClass().getName(), index);
                    return reifyVertex((Vertex) o);
                }
            }
            else {
                Vertex vertex = backend.getVertex(object.id());
                Integer size = getSize(vertex, reference);
                Object[] vertices = new Object[size];
                verticesCache.put(key, vertices);
                if (index < 0 || index >= size) {
                    NeoLogger.error("Invalid get index {0}", index);
                    throw new IndexOutOfBoundsException("Invalid get index " + index);
                }
                for (Edge edge : vertex.getEdges(Direction.OUT, reference.getName())) {
                    if (isNull(edge.getProperty(POSITION))) {
                        NeoLogger.error("An edge corresponding to the many EReference {0} does not have a position property", reference.getName());
                        throw new RuntimeException("An edge corresponding to the many EReference " + reference.getName() + " does not have a position property");
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
            return super.getReference(object, reference, index);
        }
    }
}
