/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.datastore.graphs;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jboss.util.collection.SoftValueHashMap;
import org.jboss.util.collection.WeakValueHashMap;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.wrappers.id.IdEdge;
import com.tinkerpop.blueprints.util.wrappers.id.IdGraph;
import com.tinkerpop.blueprints.util.wrappers.id.IdVertex;

import fr.inria.atlanmod.kyanos.core.KyanosEObject;
import fr.inria.atlanmod.kyanos.core.KyanosInternalEObject;
import fr.inria.atlanmod.kyanos.core.impl.KyanosEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.kyanos.logger.Logger;


public class KyanosGraph extends IdGraph<KeyIndexableGraph> {
	

	protected class KyanosEdge extends IdEdge {
		public KyanosEdge(Edge edge) {
			super(edge, KyanosGraph.this);
		}
		
		/**
		 * {@inheritDoc} <br>
		 * If the {@link Edge} references a {@link Vertex} with no more incoming
		 * {@link Edge}, the referenced {@link Vertex} is removed as well
		 */
		@Override
		public void remove() {
			Vertex referencedVertex = this.getVertex(Direction.IN);
			super.remove();
			if (!referencedVertex.getEdges(Direction.IN).iterator().hasNext()) {
				// If the Vertex has no more incoming edges remove it from the DB
				referencedVertex.remove();
			}
		}
	}
	
	protected static final String ECLASS__NAME = EcorePackage.eINSTANCE.getENamedElement_Name().getName();
	protected static final String EPACKAGE__NSURI = EcorePackage.eINSTANCE.getEPackage_NsURI().getName();

	protected static final String INSTANCE_OF = "kyanosInstanceOf";

	/**
	 * This {@link Map}&lt;objectID, {@link EObject}> is necessary to maintain a
	 * registry of the already loaded {@link Vertex}es, to avoid duplicated
	 * {@link EObject}s in memory.
	 * 
	 * We use a {@link WeakValueHashMap} for saving memory. When the value
	 * {@link EObject} is no longer referenced and can be garbage collected it
	 * is removed from the {@link Map}.
	 */
	@SuppressWarnings("unchecked")
	protected Map<Object, KyanosInternalEObject> loadedEObjects = new SoftValueHashMap();
	
	public KyanosGraph(KeyIndexableGraph baseGraph) {
		super(baseGraph);
	}
	
	/**
	 * Create a new vertex, add it to the graph, and return the newly created
	 * vertex. The issued {@link EObject} is used to calculate the
	 * {@link Vertex} <code>id</code>.
	 * 
	 * @param eObject
	 *            The corresponding {@link EObject}
	 * @return the newly created vertex
	 */
	protected Vertex addVertex(EObject eObject) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(eObject, KyanosEObject.class);
		return addVertex(kyanosEObject.kyanosId());
	}

	/**
	 * Create a new vertex, add it to the graph, and return the newly created
	 * vertex. The issued {@link EClass} is used to calculate the
	 * {@link Vertex} <code>id</code>.
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
	 * @param id
	 * @return the vertex referenced by the provided {@link EObject} or null
	 *         when no such vertex exists
	 */
	public Vertex getVertex(EObject eObject) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(eObject, KyanosEObject.class);
		return getVertex(kyanosEObject.kyanosId());
	}

	/**
	 * Return the vertex corresponding to the provided {@link EObject}. If no
	 * vertex corresponds to that {@link EObject}, then the corresponding
	 * {@link Vertex} together with its <code>INSTANCE_OF</code> relationship is
	 * created.
	 * 
	 * @param id
	 * @return the vertex referenced by the provided {@link EObject} or null
	 *         when no such vertex exists
	 */
	public Vertex getOrCreateVertex(EObject eObject) {
		KyanosInternalEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(eObject, KyanosInternalEObject.class);
		Vertex vertex = getVertex(kyanosEObject.kyanosId());
		if (vertex == null) {
			vertex = addVertex(kyanosEObject);
			EClass eClass = kyanosEObject.eClass();
			Vertex eClassVertex = getVertex(eClass);
			if (eClassVertex == null) {
				eClassVertex = addVertex(eClass);
			}
			vertex.addEdge(INSTANCE_OF, eClassVertex);
			loadedEObjects.put(kyanosEObject.kyanosId(), kyanosEObject);
		}
		return getVertex(eObject);
	}
	
	/**
	 * Returns the vertex corresponding to the provided {@link EClass}. If no
	 * vertex corresponds to that {@link EClass}, then return null.
	 * 
	 * @param id
	 * @return the vertex referenced by the provided {@link EClass} or null when
	 *         no such vertex exists
	 */
	protected Vertex getVertex(EClass eClass) {
		return getVertex(buildEClassId(eClass));
	}

	

    @Override
	public Edge addEdge(final Object id, final Vertex outVertex, final Vertex inVertex, final String label) {
        return new KyanosEdge(getBaseGraph().addEdge(id, ((IdVertex) outVertex).getBaseVertex(), ((IdVertex) inVertex).getBaseVertex(), label));
    }

    @Override
	public Edge getEdge(final Object id) {
        final Edge edge = getBaseGraph().getEdge(id);
        if (null == edge)
            return null;
        else
            return new KyanosEdge(edge);
    }
    
    

	public EClass resolveInstanceOf(Vertex vertex) {
		Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, INSTANCE_OF).iterator();
		if (iterator.hasNext()) {
			Vertex eClassVertex = iterator.next();
			String name = eClassVertex.getProperty(ECLASS__NAME);
			String nsUri = eClassVertex.getProperty(EPACKAGE__NSURI);
			EClass eClass = (EClass) Registry.INSTANCE.getEPackage(nsUri).getEClassifier(name);
			return eClass;
		}
		return null;
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
	public KyanosInternalEObject reifyVertex(Vertex vertex) {
		Object id = vertex.getId();
		KyanosInternalEObject kyanosEObject = loadedEObjects.get(id);
		if (kyanosEObject == null) {
			EClass eClass = resolveInstanceOf(vertex);
			if (eClass != null) {
				EObject eObject = EcoreUtil.create(eClass);
				if (eObject instanceof KyanosInternalEObject) {
					kyanosEObject = (KyanosInternalEObject) eObject;
				} else {
					kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(eObject, KyanosInternalEObject.class);
				}
				kyanosEObject.kyanosSetId(id.toString());
			} else {
				Logger.log(Logger.SEVERITY_ERROR, 
						MessageFormat.format("Vertex {0} does not have an associated EClass Vertex", id));
			}
			loadedEObjects.put(id, kyanosEObject);
		}
		return kyanosEObject;
	}
	
	/**
	 * Builds the <code>id</code> used to identify {@link EClass} {@link Vertex}
	 * es.
	 * 
	 * @param eClass
	 * @return
	 */
	protected static String buildEClassId(EClass eClass) {
		if (eClass != null) {
			StringBuilder builder = new StringBuilder();
			builder.append(eClass.getName());
			builder.append("@");
			builder.append(eClass.getEPackage().getNsURI());
			return builder.toString();
		} else {
			return null;
		}
	}
}
