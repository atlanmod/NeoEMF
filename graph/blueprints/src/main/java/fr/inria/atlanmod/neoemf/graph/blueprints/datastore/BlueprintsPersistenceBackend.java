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

import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
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
import java.util.Objects;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public class BlueprintsPersistenceBackend extends IdGraph<KeyIndexableGraph> implements PersistenceBackend {

	private static final String ECLASS__NAME = EcorePackage.eINSTANCE.getENamedElement_Name().getName();
	private static final String EPACKAGE__NSURI = EcorePackage.eINSTANCE.getEPackage_NsURI().getName();

	private static final String INSTANCE_OF = "kyanosInstanceOf";

	private Index<Vertex> metaclassIndex;
	private boolean isStarted;

	/**
	 * This {@link Cache}&lt;objectID, {@link EObject}&gt; is necessary to maintain a
	 * registry of the already loaded {@link Vertex}es, to avoid duplicated
	 * {@link EObject}s in memory.
	 * 
	 * We use a weak key cache for saving memory. When the value
	 * {@link EObject} is no longer referenced and can be garbage collected it
	 * is removed from the {@link Cache}.
	 */
	private final Cache<Object, InternalPersistentEObject> loadedEObjectsCache;
	private final Cache<Object, Vertex> loadedVerticesCache;
	private List<EClass> indexedEClasses;
	
	public BlueprintsPersistenceBackend(KeyIndexableGraph baseGraph) {
		super(baseGraph);
		this.loadedEObjectsCache = CacheBuilder.newBuilder().softValues().build();
		this.loadedVerticesCache = CacheBuilder.newBuilder().softValues().build();
		this.indexedEClasses = new ArrayList<>();
		this.metaclassIndex = getIndex("metaclasses", Vertex.class);
		if(metaclassIndex == null) {
			metaclassIndex = createIndex("metaclasses",Vertex.class);
		}
		this.isStarted = true;
	}
	
	@Override
	public void start(Map<?, ?> options) throws InvalidDataStoreException {
		// TODO Auto-generated method stub	
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
	        checkArgument(Iterables.isEmpty(metaclassIndex.get("name", eClass.getName())), "Index is not consistent");
	        metaclassIndex.put("name", eClass.getName(), getVertex(eClass));
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
		PersistentEObject neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(eObject, PersistentEObject.class));
		return addVertex(neoEObject.id().toString());
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
		vertex.setProperty(ECLASS__NAME, eClass.getName());
		vertex.setProperty(EPACKAGE__NSURI, eClass.getEPackage().getNsURI());
		return vertex;
	}

	/**
	 * Return the vertex corresponding to the provided {@link EObject}. If no
	 * vertex corresponds to that {@link EObject}, then return null.
	 * 
	 * @param eObject
	 * @return the vertex referenced by the provided {@link EObject} or null
	 *         when no such vertex exists
	 */
	public Vertex getVertex(EObject eObject) {
		Vertex returnValue = null;
		InternalPersistentEObject neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(eObject, InternalPersistentEObject.class));
		if(neoEObject.isMapped()) {
			returnValue = loadedVerticesCache.getIfPresent(neoEObject.id());
    		if(returnValue == null) {
				returnValue = getVertex(neoEObject.id().toString());
    		}
	    }
		else {
		    NeoLogger.warn("Trying to access a non-mapped PersistentEObject");
		}
		return returnValue;
	}

	/**
	 * Return the vertex corresponding to the provided {@link EObject}. If no
	 * vertex corresponds to that {@link EObject}, then the corresponding
	 * {@link Vertex} together with its {@code INSTANCE_OF} relationship is created.
	 * 
	 * @param eObject
	 * @return the vertex referenced by the provided {@link EObject} or null
	 *         when no such vertex exists
	 */
	public Vertex getOrCreateVertex(EObject eObject) {
		InternalPersistentEObject neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(eObject, InternalPersistentEObject.class));
		Vertex vertex;
		if(neoEObject.isMapped()) {
    		vertex = loadedVerticesCache.getIfPresent(neoEObject.id());
    		if(vertex == null) {
    		    vertex = getVertex(neoEObject.id().toString());
    		    loadedVerticesCache.put(neoEObject.id(), vertex);
    		}
    	}
		else {
			vertex = addVertex(neoEObject);
			EClass eClass = neoEObject.eClass();
			Vertex eClassVertex = Iterables.getOnlyElement(metaclassIndex.get("name", eClass.getName()), null);
			if(eClassVertex == null) {
				eClassVertex = addVertex(eClass);
				metaclassIndex.put("name", eClass.getName(), eClassVertex);
				indexedEClasses.add(eClass);
			}
			vertex.addEdge(INSTANCE_OF, eClassVertex);
			neoEObject.setMapped(true);
			loadedEObjectsCache.put(neoEObject.id().toString(), neoEObject);
			loadedVerticesCache.put(neoEObject.id(), vertex);
		}
		return vertex;
	}
	
	/**
	 * Returns the vertex corresponding to the provided {@link EClass}. If no
	 * vertex corresponds to that {@link EClass}, then return null.
	 * 
	 * @param eClass
	 * @return the vertex referenced by the provided {@link EClass} or null when
	 *         no such vertex exists
	 */
	protected Vertex getVertex(EClass eClass) {
		return getVertex(buildEClassId(eClass));
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
			String name = eClassVertex.getProperty(ECLASS__NAME);
			String nsUri = eClassVertex.getProperty(EPACKAGE__NSURI);
			returnValue = (EClass) Registry.INSTANCE.getEPackage(nsUri).getEClassifier(name);
		}
		return returnValue;
	}

	public InternalPersistentEObject reifyVertex(Vertex vertex, EClass eClass) {
		Object id = vertex.getId();
		InternalPersistentEObject neoEObject;
		neoEObject = loadedEObjectsCache.getIfPresent(id);
		if (neoEObject == null) {
			if (eClass == null) {
				eClass = resolveInstanceOf(vertex);
			}
			if (eClass != null) {
			    EObject eObject;
			    if(eClass.getEPackage().getClass().equals(EPackageImpl.class)) {
			        // Dynamic EMF
			        eObject = PersistenceFactory.eINSTANCE.create(eClass);
			    } else {
			        // EObject eObject = EcoreUtil.create(eClass);
			        eObject = EcoreUtil.create(eClass);
			    }
				if (eObject instanceof InternalPersistentEObject) {
					neoEObject = (InternalPersistentEObject) eObject;
				} else {
					neoEObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(eObject, InternalPersistentEObject.class));
				}
				neoEObject.id(new StringId(id.toString()));
				neoEObject.setMapped(true);
			} else {
				NeoLogger.error("Vertex {0} does not have an associated EClass Vertex", id);
			}
			loadedEObjectsCache.put(id, neoEObject);
		}
		return neoEObject;
	}
	
	/**
	 * Reifies the given {@link Vertex} as an {@link EObject}. The method
	 * guarantees that the same {@link EObject} is returned for a given
	 * {@link Vertex} in subsequent calls, unless the {@link EObject} returned
	 * in previous calls has been already garbage collected.
	 * 
	 * @param vertex
	 * @return
	 */
	public InternalPersistentEObject reifyVertex(Vertex vertex) {
		return reifyVertex(vertex, null);
	}
	
	/**
	 * Builds the {@code id} used to identify {@link EClass} {@link Vertex}es.
	 * 
	 * @param eClass
	 * @return
	 */
	protected static String buildEClassId(EClass eClass) {
		return eClass != null ? eClass.getName() + '@' + eClass.getEPackage().getNsURI() : null;
	}
	
	/**
	 * 
	 * @return the list of EClasses that have been indexed.
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
				// Find all the concrete subclasses of the given EClass
				// (the metaclass index only stores the concrete EClasses)
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
				Vertex metaClassVertex = Iterables.getOnlyElement(metaclassIndex.get("name", ec.getName()), null);
				if(metaClassVertex != null) {
					Iterable<Vertex> instanceVertexIterable = metaClassVertex.getVertices(Direction.IN, INSTANCE_OF);
					indexHits.put(ec, instanceVertexIterable);
			    } else {
					NeoLogger.warn("MetaClass '{}' not found in index", ec.getName());
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
		 * {@inheritDoc} <br>
		 * If the {@link Edge} references a {@link Vertex} with no more incoming
		 * {@link Edge}, the referenced {@link Vertex} is removed as well
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
}
