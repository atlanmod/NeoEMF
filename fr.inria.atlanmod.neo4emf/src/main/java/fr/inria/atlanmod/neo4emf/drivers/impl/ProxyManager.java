package fr.inria.atlanmod.neo4emf.drivers.impl;

/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
 * */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.IProxyManager;

public class ProxyManager implements IProxyManager {
	
	/**
	 * Maps existent element in the Database
	 */
	private Map<EClass, Cache<Long, INeo4emfObject>> nodes2objects;

	public ProxyManager(){
		nodes2objects = new HashMap<EClass, Cache<Long, INeo4emfObject>>();
	}
	
	@Override
	public void putToProxy(INeo4emfObject obj) {
		EClass eClass = obj.eClass();
		
		if (nodes2objects.containsKey(eClass)) {
			nodes2objects.get(eClass).put(obj.getNodeId(), obj);
		} 
		else {
			Cache<Long,INeo4emfObject> cache = CacheBuilder.newBuilder().softValues().build();
			cache.put(obj.getNodeId(), obj);
			nodes2objects.put(eClass, cache);
		}
		// TODO remove that (debug)
		printMap(nodes2objects);
	}

	@Override
	public INeo4emfObject getObjectFromProxy(EClass eClassifier, long nodeId) {
		if(nodes2objects.containsKey(eClassifier)) {
			return (INeo4emfObject) (nodes2objects.get(eClassifier).getIfPresent(nodeId));
		}
		return null;
	}
	
	@Override
	public Map<EClass, Cache<Long, INeo4emfObject>> getInternalProxy() {
		return nodes2objects;
	}
	
	// TODO remove this function (used for debug the unload strategy)
	private void printMap(Map<EClass,Cache<Long,INeo4emfObject>> map) {
		System.out.println("=== Proxy n2o Content ===");
		Iterator<EClass> eClassIterator = map.keySet().iterator();
		while(eClassIterator.hasNext()) {
			EClass clazz = eClassIterator.next();
			System.out.println("key : " + clazz.getName());
			Cache<Long,INeo4emfObject> objectCache = map.get(clazz);
			Iterator<Long> idIterator = objectCache.asMap().keySet().iterator();
			while(idIterator.hasNext()) {
				Long nodeId = idIterator.next();
				System.out.println("\tkey : " + nodeId);
				System.out.println("\tvalue : " + objectCache.getIfPresent(nodeId));
			}
		}
		System.out.println("=== End Proxy n2o Content ===");
	}
}
