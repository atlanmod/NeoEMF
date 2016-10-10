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

package fr.inria.atlanmod.neoemf.graph.blueprints.datastore;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Iterables;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.wrappers.id.IdEdge;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;
import com.tinkerpop.blueprints.util.wrappers.id.IdVertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class BlueprintsPersistenceBackend extends IdGraph<KeyIndexableGraph> implements PersistenceBackend {

	public static final String ECLASS_NAME = EcorePackage.eINSTANCE.getENamedElement_Name().getName();
	public static final String EPACKAGE_NSURI = EcorePackage.eINSTANCE.getEPackage_NsURI().getName();
	public static final String INSTANCE_OF = "kyanosInstanceOf";
	public static final String METACLASSES = "metaclasses";
	public static final String NAME = "name";

	private Index<Vertex> metaclassIndex;
	private boolean isStarted;

	/**
	 * This {@link Cache}&lt;objectID, {@link EObject}&gt; is necessary to maintain a
	 * registry of the already loaded {@link Vertex}es, to avoid duplicated
	 * {@link EObject}s in memory.
	 * <p/>
	 * We use a weak key cache for saving memory. When the value
	 * {@link EObject} is no longer referenced and can be garbage collected it
	 * is removed from the {@link Cache}.
	 */
	private final Cache<Object, PersistentEObject> loadedEObjectsCache;
	private final Cache<Object, Vertex> loadedVerticesCache;
	private final List<EClass> indexedEClasses;
	
	public BlueprintsPersistenceBackend(KeyIndexableGraph baseGraph) {
		super(baseGraph);
		this.loadedEObjectsCache = CacheBuilder.newBuilder().softValues().build();
		this.loadedVerticesCache = CacheBuilder.newBuilder().softValues().build();
		this.indexedEClasses = new ArrayList<>();
		this.metaclassIndex = getIndex(METACLASSES, Vertex.class);
		if(metaclassIndex == null) {
			metaclassIndex = createIndex(METACLASSES,Vertex.class);
		}
		this.isStarted = true;
	}
	
	@Override
	public void start(Map<?, ?> options) throws InvalidDataStoreException {

	}
	
	@Override
	public boolean isStarted() {
		return isStarted;
	}
	
	@Override
	public void stop() {
		shutdown();
		isStarted = false;

	}
	
	@Override
	public void save() {
	    if(getFeatures().supportsTransactions) {
			commit();
	    } else {
			shutdown();
	    }
	}

	public void initMetaClassesIndex(List<EClass> eClassList) {
	    for(EClass eClass : eClassList) {
	        checkArgument(Iterables.isEmpty(metaclassIndex.get(NAME, eClass.getName())), "Index is not consistent");
	        metaclassIndex.put(NAME, eClass.getName(), getVertex(eClass));
	    }
	}
	
	/**
	 * Create a new vertex, add it to the graph, and return the newly created
	 * vertex. The issued {@link EObject} is used to calculate the {@link Vertex} {@code id}.
	 * 
	 * @param eObject
	 *            The corresponding {@link EObject}
	 * @return the newly created vertex
	 */
	protected Vertex addVertex(EObject eObject) {
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(eObject, PersistentEObject.class));
		return addVertex(persistentEObject.id().toString());
	}

	/**
	 * Create a new vertex, add it to the graph, and return the newly created
	 * vertex. The issued {@link EClass} is used to calculate the {@link Vertex} {@code id}.
	 * 
	 * @param eClass
	 *            The corresponding {@link EClass}
	 * @return the newly created vertex
	 */
	protected Vertex addVertex(EClass eClass) {
		Vertex vertex = addVertex(buildEClassId(eClass));
		vertex.setProperty(ECLASS_NAME, eClass.getName());
		vertex.setProperty(EPACKAGE_NSURI, eClass.getEPackage().getNsURI());
		return vertex;
	}

	/**
	 * Return the vertex corresponding to the provided {@link EObject}. If no
	 * vertex corresponds to that {@link EObject}, then return {@code null}.
	 * 
	 * @return the vertex referenced by the provided {@link EObject} or {@code null}
	 *         when no such vertex exists
	 */
	public Vertex getVertex(EObject eObject) {
		Vertex vertex = null;
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(eObject, PersistentEObject.class));
		if(persistentEObject.isMapped()) {
			vertex = getMappedVertex(persistentEObject.id());
		}
		else {
//		    NeoLogger.warn("Trying to access a non-mapped PersistentEObject");
		}
		return vertex;
	}

	/**
	 * Returns the vertex corresponding to the provided {@link EClass}. If no
	 * vertex corresponds to that {@link EClass}, then return {@code null}.
	 *
	 * @return the vertex referenced by the provided {@link EClass} or {@code null} when
	 *         no such vertex exists
	 */
	protected Vertex getVertex(EClass eClass) {
		return getVertex(buildEClassId(eClass));
	}

	/**
	 * Return the vertex corresponding to the provided {@link EObject}. If no
	 * vertex corresponds to that {@link EObject}, then the corresponding
	 * {@link Vertex} together with its {@code INSTANCE_OF} relationship is created.
	 * 
	 * @return the vertex referenced by the provided {@link EObject} or {@code null}
	 *         when no such vertex exists
	 */
	public Vertex getOrCreateVertex(EObject eObject) {
		Vertex vertex;
		PersistentEObject persistentEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(eObject, PersistentEObject.class));
		if(persistentEObject.isMapped()) {
			vertex = getMappedVertex(persistentEObject.id());
		}
		else {
			vertex = createVertex(persistentEObject);
		}
		return vertex;
	}

	private Vertex createVertex(final PersistentEObject persistentEObject) {
		Vertex vertex = addVertex(persistentEObject);
		EClass eClass = persistentEObject.eClass();

		Vertex eClassVertex = Iterables.getOnlyElement(metaclassIndex.get(NAME, eClass.getName()), null);
		if(eClassVertex == null) {
			eClassVertex = addVertex(eClass);
			metaclassIndex.put(NAME, eClass.getName(), eClassVertex);
			indexedEClasses.add(eClass);
		}
		vertex.addEdge(INSTANCE_OF, eClassVertex);
		persistentEObject.setMapped(true);
		loadedEObjectsCache.put(persistentEObject.id().toString(), persistentEObject);
		loadedVerticesCache.put(persistentEObject.id(), vertex);
		return vertex;
	}

	private Vertex getMappedVertex(Id id) {
		Vertex vertex = null;
		try {
			vertex = loadedVerticesCache.get(id, new VertexCacheLoader(id));
		} catch (ExecutionException e) {
			NeoLogger.error(e.getCause());
		}
		return vertex;
	}



    @Override
	// FIXME Return of instance of non-static inner class 'NeoEdge'
	public Edge addEdge(final Object id, final Vertex outVertex, final Vertex inVertex, final String label) {
        return new NeoEdge(getBaseGraph().addEdge(id, ((IdVertex) outVertex).getBaseVertex(), ((IdVertex) inVertex).getBaseVertex(), label));
    }

    @Override
	// FIXME Return of instance of non-static inner class 'NeoEdge'
	public Edge getEdge(final Object id) {
        final Edge edge = getBaseGraph().getEdge(id);
		return null != edge ? new NeoEdge(edge) : null;
    }

	public EClass resolveInstanceOf(Vertex vertex) {
		EClass returnValue = null;
		Vertex eClassVertex = Iterables.getOnlyElement(vertex.getVertices(Direction.OUT, INSTANCE_OF), null);
		if (eClassVertex != null) {
			String name = eClassVertex.getProperty(ECLASS_NAME);
			String nsUri = eClassVertex.getProperty(EPACKAGE_NSURI);
			returnValue = (EClass) Registry.INSTANCE.getEPackage(nsUri).getEClassifier(name);
		}
		return returnValue;
	}

	public PersistentEObject reifyVertex(Vertex vertex, EClass eClass) {
		PersistentEObject persistentEObject = null;
		Object id = vertex.getId();
		if (eClass == null) {
			eClass = resolveInstanceOf(vertex);
		}
		try {
			persistentEObject = loadedEObjectsCache.get(id, new PersistantEObjectCacheLoader(id, eClass));
		} catch (ExecutionException e) {
			NeoLogger.error(e.getCause());
		}
		return persistentEObject;
	}
	
	/**
	 * Reifies the given {@link Vertex} as an {@link EObject}.
	 * <p/>
	 * The method guarantees that the same {@link EObject} is returned for a given
	 * {@link Vertex} in subsequent calls, unless the {@link EObject} returned
	 * in previous calls has been already garbage collected.
	 */
	public PersistentEObject reifyVertex(Vertex vertex) {
		return reifyVertex(vertex, null);
	}
	
	/**
	 * Builds the {@code id} used to identify {@link EClass} {@link Vertex}es.
	 */
	protected String buildEClassId(EClass eClass) {
		return eClass != null ? eClass.getName() + '@' + eClass.getEPackage().getNsURI() : null;
	}
	
	/**
	 * Returns the list of {@link EClass}es that have been indexed.
	 * <p/>
	 * This list is needed to support index copy in {@link BlueprintsPersistenceBackendFactory#copyBackend(PersistenceBackend, PersistenceBackend)}
	 */
	public List<EClass> getIndexedEClasses() {
	    return indexedEClasses;
	}
	
	@Override
	public Map<EClass, Iterable<Vertex>> getAllInstances(EClass eClass, boolean strict) {
		Map<EClass, Iterable<Vertex>> indexHits;
		if(eClass.isAbstract() && strict) {
			// There is no strict instance of an abstract class
			indexHits = Collections.emptyMap();
		} else {
			indexHits = new HashMap<>();
			Set<EClass> eClassToFind = new HashSet<>();
			eClassToFind.add(eClass);
			if (!strict) {
				/*
				 * Find all the concrete subclasses of the given EClass
				 * (the metaclass index only stores the concrete EClasses)
				 */
				EPackage ePackage = eClass.getEPackage();
				for (EClassifier eClassifier : ePackage.getEClassifiers()) {
					if (eClassifier instanceof EClass) {
						EClass packageEClass = (EClass) eClassifier;
						if (eClass.isSuperTypeOf(packageEClass) && !packageEClass.isAbstract()) {
							eClassToFind.add(packageEClass);
						}
					}
				}
			}
			// Get all the vertices that are indexed with one of the EClass
			for(EClass ec : eClassToFind) {
				Vertex metaClassVertex = Iterables.getOnlyElement(metaclassIndex.get(NAME, ec.getName()), null);
				if(metaClassVertex != null) {
					Iterable<Vertex> instanceVertexIterable = metaClassVertex.getVertices(Direction.IN, INSTANCE_OF);
					indexHits.put(ec, instanceVertexIterable);
			    } else {
					NeoLogger.warn("MetaClass {0} not found in index", ec.getName());
				}
			}
		}
		return indexHits;
	}

	private class NeoEdge extends IdEdge {
		public NeoEdge(Edge edge) {
			super(edge, BlueprintsPersistenceBackend.this);
		}

		/**
		 * {@inheritDoc}
		 * <p/>
		 * If the {@link Edge} references a {@link Vertex} with no more incoming
		 * {@link Edge}, the referenced {@link Vertex} is removed as well.
		 */
		@Override
		public void remove() {
			Vertex referencedVertex = getVertex(Direction.IN);
			super.remove();
			if (Iterables.isEmpty(referencedVertex.getEdges(Direction.IN))) {
				// If the Vertex has no more incoming edges remove it from the DB
				referencedVertex.remove();
			}
		}
	}

	private class VertexCacheLoader implements Callable<Vertex> {

		private final Id id;

		public VertexCacheLoader(Id id) {
			this.id = id;
		}

		@Override
        public Vertex call() throws Exception {
            return getVertex(id.toString());
        }
	}

	private static class PersistantEObjectCacheLoader implements Callable<PersistentEObject> {

		private final Object id;
		private final EClass eClass;

		public PersistantEObjectCacheLoader(Object id, EClass eClass) {
			this.id = id;
			this.eClass = eClass;
		}

		@Override
        public PersistentEObject call() throws Exception {
            PersistentEObject persistentEObject;
            if (eClass != null) {
                EObject eObject;
                if(eClass.getEPackage().getClass().equals(EPackageImpl.class)) {
                    // Dynamic EMF
                    eObject = PersistenceFactory.eINSTANCE.create(eClass);
                } else {
                    // EObject eObject = EcoreUtil.create(eClass);
                    eObject = EcoreUtil.create(eClass);
                }
                if (eObject instanceof PersistentEObject) {
                    persistentEObject = (PersistentEObject) eObject;
                } else {
                    persistentEObject = checkNotNull(
                            NeoEObjectAdapterFactoryImpl.getAdapter(eObject, PersistentEObject.class));
                }
                persistentEObject.id(new StringId(id.toString()));
                persistentEObject.setMapped(true);
            } else {
                // TODO Find a better exception to thrown
                throw new Exception("Vertex " + id + " does not have an associated EClass Vertex");
            }

			return persistentEObject;
        }
	}
}
