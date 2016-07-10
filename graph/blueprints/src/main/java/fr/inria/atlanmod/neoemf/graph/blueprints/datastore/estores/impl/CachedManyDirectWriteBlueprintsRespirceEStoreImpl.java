/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.jboss.util.collection.SoftValueHashMap;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

public class CachedManyDirectWriteBlueprintsRespirceEStoreImpl extends
        DirectWriteBlueprintsResourceEStoreImpl {

    private Map<MapKey,Object[]> manyCache;
    
    public CachedManyDirectWriteBlueprintsRespirceEStoreImpl(Internal resource,
            BlueprintsPersistenceBackend graph) {
        super(resource, graph);
        manyCache = new SoftValueHashMap<MapKey, Object[]>();
        NeoLogger.log(NeoLogger.SEVERITY_INFO, "CachedManyBlueprintsResourceEStore Created");
    }
    
    @Override
    protected Object get(InternalEObject object, EReference eReference, int index) {
        if(eReference.isMany()) {
            MapKey key = new MapKey(((InternalPersistentEObject)object).id(), eReference);
            if(manyCache.containsKey(key)) {
                Object o = manyCache.get(key)[index];
                if(o == null) {
                    NeoLogger.log(NeoLogger.SEVERITY_WARNING, "Inconsistent content in CachedMany map, null value found for key " + key.toString() + " at index " + index);
                    return super.get(object, eReference, index);
                }
                else {
                    NeoLogger.log(NeoLogger.SEVERITY_DEBUG, "Found in cache " + key.toString() + "-" + object.eClass().getName() + "- idx=" + index);
                    return reifyVertex((Vertex)o);
                }
            }
            else {
                Vertex vertex = graph.getVertex(object);
                Integer size = getSize(vertex, eReference);
                Object[] vertices = new Object[size];
                manyCache.put(key, vertices);
                if(index < 0 || index >= size) {
                    NeoLogger.log(NeoLogger.SEVERITY_ERROR, "Invalid get index " + index);
                    throw new IndexOutOfBoundsException("Invalid get index " + index);
                }
                Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, eReference.getName()).iterator();
                while(iterator.hasNext()) {
                    Edge edge = iterator.next();
                    if(edge.getProperty(POSITION) == null) {
                        NeoLogger.log(NeoLogger.SEVERITY_ERROR, "An edge corresponding to the many EReference " + eReference.getName() + " does not have a position property");
                        throw new RuntimeException("An edge corresponding to the many EReference " + eReference.getName() + " does not have a position property");
                    }
                    else {
                        Integer position = edge.getProperty(POSITION);
                        Vertex otherEnd = edge.getVertex(Direction.IN);
                        NeoLogger.log(NeoLogger.SEVERITY_DEBUG, "Putting in cache " + key.toString() + "-" + object.eClass().getName() + "- idx=" + position);
                        vertices[position] = otherEnd;
                    }
                }
                return reifyVertex((Vertex)vertices[index]);
            }
        }
        else {
            return super.get(object, eReference, index);
        }
    }
    
    private class MapKey {
        
        public Id id;
        public EStructuralFeature feature;
        
        public MapKey(Id id, EStructuralFeature feature) {
            this.id = id;
            this.feature = feature;
        }
        
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof MapKey) {
                return id.equals(((MapKey) obj).id) && feature.equals(((MapKey) obj).feature);
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
