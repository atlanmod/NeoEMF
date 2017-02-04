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
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link DirectWriteBlueprintsStore} that uses an internal cache to store {@link Vertex} elements that are
 * part of multi-valued {@link EReference}s to speed-up their access.
 * <p>
 * Large multi-valued {@link EReference}s can be an execution time bottleneck in the graph implementation because any
 * element access forces the underlying database engine to load all the {@link Edge}s corresponding to the {@link
 * EReference}. We overcome this limitation by caching all the {@link Vertex} elements involved in multi-valued {@link
 * EReference}s the first time they are traversed, limiting database access.
 * <p>
 * This store can be used as a base store that can be complemented by plugging decorator stores on top of it (see {@link
 * AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 *
 * @see DirectWriteBlueprintsStore
 * @see BlueprintsPersistenceBackend
 * @see AbstractPersistentStoreDecorator
 */
public class DirectWriteBlueprintsCacheManyStore extends DirectWriteBlueprintsStore {

    /**
     * The property key used to define the index of an edge.
     */
    private static final String POSITION = "position";

    /**
     * In-memory cache that holds ???, identified by the associated {@link FeatureKey}.
     */
    // TODO Cache many properties in addition to vertices
    private final Cache<FeatureKey, Object[]> verticesCache = Caffeine.newBuilder()
            .initialCapacity(1_000)
            .maximumSize(10_000)
            .build();

    /**
     * Constructs a new {@code DirectWriteBlueprintsCacheManyStore} between the given {@code resource} and the
     * {@code backend}.
     *
     * @param resource the resource to persist and access
     * @param backend  the persistence back-end used to store the model
     */
    public DirectWriteBlueprintsCacheManyStore(PersistentResource resource, BlueprintsPersistenceBackend backend) {
        super(resource, backend);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Caches all the {@link Vertex}s traversed to retrieve the element at the given {@code index}. This implementation
     * bypasses the graph API limitation that has to traverse all the {@link Edge}s connected to the input vertex to
     * find the one with the given {@code index}.
     *
     * @return a list of {@link EObject} if the given {@link EReference} is multi-valued, an {@link EObject} if it is
     * single-valued, {@code null} if the element doesn't exist
     */
    @Override
    protected PersistentEObject getReference(PersistentEObject object, EReference reference, int index) {
        if (reference.isMany()) {
            FeatureKey key = FeatureKey.from(object, reference);
            Object[] list = verticesCache.getIfPresent(key);
            if (nonNull(list)) {
                Object o = list[index];
                if (isNull(o)) {
                    NeoLogger.warn("Inconsistent content in CachedMany map, null value found for key " + key + " at index " + index);
                    return (PersistentEObject) super.getReference(object, reference, index);
                }
                else {
                    NeoLogger.debug("Found in cache {0} - {1} - idx={2}", key, object.eClass().getName(), index);
                    return eObject(new StringId(((Vertex) o).getId().toString()));
                }
            }
            else {
                Vertex vertex = backend.getVertex(object.id());
                Integer size = size(object, reference);
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
                return eObject(new StringId(((Vertex) vertices[index]).getId().toString()));
            }
        }
        else {
            return (PersistentEObject) super.getReference(object, reference, index);
        }
    }
}
