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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.prefetch.cache.NeoEMFCacheKey;
import fr.inria.atlanmod.prefetch.cache.NeoEMFIndexedCacheKey;
import fr.inria.atlanmod.prefetch.core.PrefetchCore;
import fr.inria.atlanmod.prefetch.event.EventAPI;
import fr.inria.atlanmod.prefetch.processor.neoemf.NeoEMFRuleProcessor2.VertexWrapper;
import fr.inria.atlanmod.prefetch.processor.neoemf.NeoEMFRuleProcessorFactory;
import fr.inria.atlanmod.prefetch.util.PrefetchLogger;

public class PrefetchingDirectWriteBlueprintsResourceEStoreImpl extends DirectWriteBlueprintsResourceEStoreImpl{

	protected PrefetchCore pCore;
    
    public PrefetchingDirectWriteBlueprintsResourceEStoreImpl(Resource.Internal resource,
            BlueprintsPersistenceBackend graph) {
        super(resource, graph);
        NeoLogger.log(NeoLogger.SEVERITY_INFO, "Creating a Prefetching EStore");
        pCore = new PrefetchCore(graph,new NeoEMFRuleProcessorFactory());
    }
    
    public PrefetchCore getPrefetcher() {
    	return pCore;
    }
    
    public static int missCount = 0;
    public static int hitCount = 0;
    
    @Override
    public int size(InternalEObject object, EStructuralFeature feature) {
    	if(feature instanceof EReference) {
    		EReference eReference = (EReference)feature;
	    	final Map<Object,Object> cache = pCore.getActiveCache();
	    	EventAPI eventAPI = pCore.getEventAPI();
	    	if(!(eReference.getEType() instanceof EClass)) {
	            return super.size(object, feature);
	        }
            PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
//	            NeoEMFCacheKey key = new NeoEMFCacheKey(neoEObject.id().toString(), eReference);
//	            List<VertexWrapper> cacheHit = (List<VertexWrapper>)cache.get(key);
            NeoEMFIndexedCacheKey key = new NeoEMFIndexedCacheKey(neoEObject.id().toString(), eReference, -2);
            if(cache.containsKey(key)) {
            	hitCount++;
            	int cachedSize = (int)cache.get(key);
            	if(cachedSize == -1) {
            		return 0;
            	}
            	else {
            		return cachedSize;
            	}
            }
            else {
            	missCount++;
            	return super.size(object, feature);
            }
//	            if(cacheHit != null) {
//	            	hitCount++;
//	            	return cacheHit.size();
	//            	NeoLogger.log(NeoLogger.SEVERITY_DEBUG, "hit !");
	//            	System.out.println(index + "/" + cacheHit.size());
//	            	if(cacheHit.get(lIndex) == null) {
	//            		System.out.println("Prefetcher too slow");
//	            }
//	            System.out.println(super.size(object, feature));
//	            	else {
	//            		return cacheHit.get(index);
	            		
	//            		System.out.println("hit");
	//            		System.out.println(e);
//	            		return e;
	//            System.out.println(missCount);
    	}
    	return super.size(object, feature);
    }
    
    @Override
    protected boolean isSet(InternalEObject object, EReference eReference) {
    	PrefetchLogger.info("Accessing eSet");
    	final Map<Object,Object> cache = pCore.getActiveCache();
    	EventAPI eventAPI = pCore.getEventAPI();
    	if(!(eReference.getEType() instanceof EClass)) {
            return super.isSet(object, eReference);
        }
        if(!cache.isEmpty()) {
            PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
            NeoEMFIndexedCacheKey key = new NeoEMFIndexedCacheKey(neoEObject.id().toString(), eReference, -2);
            if(cache.containsKey(key)) {
            	hitCount++;
            	int theSize = (int)cache.get(key);
            	return theSize > 0;
            }
//            NeoEMFCacheKey key = new NeoEMFCacheKey(neoEObject.id().toString(), eReference);
//            List<VertexWrapper> cacheHit = (List<VertexWrapper>)cache.get(key);
//            if(cacheHit != null) {
//            	hitCount++;
//            	return cacheHit.size() > 0;
//            	NeoLogger.log(NeoLogger.SEVERITY_DEBUG, "hit !");
//            	System.out.println(index + "/" + cacheHit.size());
//	            	if(cacheHit.get(lIndex) == null) {
//            		System.out.println("Prefetcher too slow");
//            }
//	            System.out.println(super.size(object, feature));
//	            	else {
//            		return cacheHit.get(index);
            		
//            		System.out.println("hit");
//            		System.out.println(e);
//	            		return e;
        }
        missCount++;
//            System.out.println(missCount);
    	return super.isSet(object, eReference);
    }
    
    
    @Override
    protected Object get(InternalEObject object, EReference eReference, int index) {
//    	if(1==1) {
//        	return super.get(object, eReference, index);
//        }
    	Map<Object,Object> cache = pCore.getActiveCache();
    	EventAPI eventAPI = pCore.getEventAPI();
        if(!(eReference.getEType() instanceof EClass)) {
            Object r = super.get(object, eReference, index);
            if(r instanceof PersistentEObject) {
            	Vertex v = graph.getVertex(((PersistentEObject)r).id());
            	eventAPI.accessEvent(v,((EObject)r).eClass());
            }
            return r;
        }
        
        PersistentEObject neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(object, PersistentEObject.class);
//        System.out.println(cache.toString());
        if(eReference.isMany()) {
        	NeoEMFIndexedCacheKey key = new NeoEMFIndexedCacheKey(neoEObject.id().toString(), eReference, index);
        	if(cache.containsKey(key)) {
        		hitCount++;
        		VertexWrapper wrapper = (VertexWrapper)cache.get(key);
        		eventAPI.accessEvent(wrapper.getV(),wrapper.getEClass());
        		return reifyVertex(wrapper);
        	}
        	else {
        		missCount++;
        		Vertex vertex = graph.getVertex(object);
        		Iterator<Vertex> iterator = vertex.query().labels(eReference.getName()).direction(Direction.OUT).has(POSITION, index).vertices().iterator();
                if (iterator.hasNext()) {
                    Vertex referencedVertex = iterator.next();
                    InternalEObject reifiedObject = reifyVertex(referencedVertex);
                    eventAPI.accessEvent(referencedVertex,graph.resolveInstanceOf(referencedVertex));
                    return reifiedObject;
                } else {
                    return null;
                }
        	}
        }
        else {
//        	PrefetchLogger.info("accessing single ref : " + eReference.getName());
        	NeoEMFIndexedCacheKey key = new NeoEMFIndexedCacheKey(neoEObject.id().toString(), eReference, -1);
        	if(cache.containsKey(key)) {
        		hitCount++;
        		VertexWrapper wrapper = (VertexWrapper)cache.get(key);
        		eventAPI.accessEvent(wrapper.getV(),wrapper.getEClass());
        		return reifyVertex(wrapper);
        	}
        	else {
        		missCount++;
        		Vertex vertex = graph.getVertex(object);
            	Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, eReference.getName()).iterator();
                if (iterator.hasNext()) {
                    Vertex referencedVertex = iterator.next();
                    eventAPI.accessEvent(referencedVertex,graph.resolveInstanceOf(referencedVertex));
                    InternalEObject reifiedObject = reifyVertex(referencedVertex);
                    return reifiedObject;
                } else {
                    return null;
                }
        	}
        }
//            System.out.println("?????");
////            NeoEMFCacheKey key = new NeoEMFCacheKey(neoEObject.id().toString(), eReference);
////            List<VertexWrapper> cacheHit = (List<VertexWrapper>)cache.get(key);
//            if(cacheHit != null) {
////            	NeoLogger.log(NeoLogger.SEVERITY_DEBUG, "hit !");
////            	System.out.println(index + "/" + cacheHit.size());
//            	int lIndex = (index == -1 ? 0 : index);
//            	try{
//            	if(cacheHit.get(lIndex) == null) {
////            		System.out.println("Prefetcher too slow");
//            	}
//            	else {
////            		return cacheHit.get(index)
//            		eventAPI.accessEvent(cacheHit.get(lIndex).getV(),cacheHit.get(lIndex).getEClass());
//            		InternalEObject e = reifyVertex(cacheHit.get(lIndex));
//            		hitCount++;
////            		System.out.println("hit");
////            		System.out.println(e);
//            		return e;
//            	}
//            	}catch(ArrayIndexOutOfBoundsException e) {
//            		if(cacheHit.size()==0 && lIndex == 0) {
//            			return null;
//            		}
//            	}
//            }
//            missCount++;
////            System.out.println(missCount);
//        }
//        Vertex vertex = graph.getVertex(object);
//        
//        if (!eReference.isMany()) {
//            Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, eReference.getName()).iterator();
//            if (iterator.hasNext()) {
//                Vertex referencedVertex = iterator.next();
//                eventAPI.accessEvent(referencedVertex,graph.resolveInstanceOf(referencedVertex));
//                InternalEObject reifiedObject = reifyVertex(referencedVertex);
//                return reifiedObject;
//            } else {
//                return null;
//            }
//        } else {
//            Integer size = getSize(vertex, eReference);
//            if (index < 0 || index >= size) {
//                throw new IndexOutOfBoundsException();
//            } else {
//                Iterator<Vertex> iterator = vertex.query().labels(eReference.getName()).direction(Direction.OUT).has(POSITION, index).vertices().iterator();
//                if (iterator.hasNext()) {
//                    Vertex referencedVertex = iterator.next();
//                    InternalEObject reifiedObject = reifyVertex(referencedVertex);
//                    eventAPI.accessEvent(referencedVertex,graph.resolveInstanceOf(referencedVertex));
//                    return reifiedObject;
//                } else {
//                    return null;
//                }
//            }
//        }
    }
    
    protected InternalEObject reifyVertex(VertexWrapper vWrapper) {
    	return reifyVertex(vWrapper.getV(),vWrapper.getEClass());		
	}
    
    @Override
    public EList<EObject> getAllInstances(EClass eClass) {
    	Map<EClass, Iterator<Vertex>> indexHits = graph.getAllInstances(eClass);
		EList<EObject> instances = new PrefetchEventBasicEList<EObject>();
		Set<EClass> mapKeys = indexHits.keySet();
		for(EClass metaClass : mapKeys) {
			Iterator<Vertex> instanceVertices = indexHits.get(metaClass);
			while(instanceVertices.hasNext()) {
				Vertex instanceVertex = instanceVertices.next();
				instances.add(reifyVertex(instanceVertex, metaClass));
			}
		}
		return instances;
    }
    
    class PrefetchEventBasicEList<E> extends BasicEList<E> {
    	
    	public PrefetchEventBasicEList() {
    		super();
		}
    	
    	@Override
    	public E get(int index) {
    		E res = super.get(index);
    		if(res instanceof PersistentEObject) {
//    			Vertex vv = graph.getVertex((PersistentEObject)res);
    			pCore.getEventAPI().accessEvent(((PersistentEObject)res).id().toString(), ((PersistentEObject)res).eClass());
    		}
    		return res;
    	}
    	
    }
}
