package fr.inria.atlanmod.neo4emf.impl;

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

import java.awt.Point;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.neo4j.graphdb.RelationshipType;


import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.util.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.util.impl.PersistenceManager;


public   class Neo4emfResource extends ResourceImpl implements INeo4emfResource {


/** 
 * The persistence manager holds the communication between the resource and 
 * the different persistence units 
 * @see #IPersistenceManager 
 */

	private IPersistenceManager persistenceManager;
/**
 * storeDirectory represents the location of the store Database
 * @see IPersistenceService	
 */
	@SuppressWarnings("unused")
	private String storeDirectory;
/**
 * Neo4emfResource Constructor  	
 * @param storeDirectory
 */
public Neo4emfResource(String storeDirectory, Map <Point,RelationshipType> map) {
		this.storeDirectory=storeDirectory;
		persistenceManager =  new PersistenceManager(this,storeDirectory,map);
	}
/**
 * @link {@link INeo4emfResource#fetchAttributes(EObject)}
 */
@Override
public void fetchAttributes(EObject obj) {
		persistenceManager.fetchAttributes(obj);
		
	}
/**
 * @link {@link INeo4emfResource#getOnDemand(EObject, int)}
 */
	@Override
public void getOnDemand(EObject obj, int featureId) {
		persistenceManager.getOnDemand(obj, featureId);
		
	}
/**
 * {@link INeo4emfResource#save()}
 */
@Override
public void save() {
	save(null);	
}
/**
 * {@link INeo4emfResource#save(Map)}
 */
@Override
public void save (Map<?, ?> options){
	persistenceManager.save(options);
	}
/**
 * shuting down the backend
 */
@Override
public void shutdown() {
	persistenceManager.shutdown();
	
}
/**
 * load the roots elements of the model 
 * @param options {@link Map}
 */
@Override 
public void load (Map <?,?> options){
	persistenceManager.load(options);
}
/**
 * {@link INeo4emfResource#notifyGet(EObject, EStructuralFeature)}
 */
@Override
public void notifyGet(EObject eObject, EStructuralFeature feature) {
	persistenceManager.updateProxyManager((INeo4emfObject)eObject, feature);
	
}
@Override
public void unload(int PID){
	persistenceManager.unloadPartition(PID);
}

}
