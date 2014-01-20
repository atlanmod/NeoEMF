package fr.inria.atlanmod.neo4emf;
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


import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.neo4j.graphdb.RelationshipType;

import fr.inria.atlanmod.neo4emf.drivers.ILoader;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;




public interface INeo4emfResource extends Resource, Resource.Internal {
	
	public String MAX_OPERATIONS_PER_TRANSACTION = ISerializer.MAX_OPERATIONS_PER_TRANSACTION;
	
	public static final String DUPLICATION_TOLERANT = ILoader.DUPLICATION_TOLERANT;
	
	public static final String DYNAMIC_LOADING = ILoader.DYNAMIC_LOADING;
	
	public static final String STATIC_LOADING = ILoader.STATIC_LOADING;
	
	public static final String FULL_LOADING = ILoader.FULL_LOADING;
	
	public static final String LOADING_STRATEGY = ILoader.LOADING_STRATEGY;
	
	
	
	
	
	/**
	 * Fetches the single-valued attributes lazily on demand  
	 * @param object {@link EObject}
	 */
	public void fetchAttributes(EObject object);
	/**
	 * Gets multi-valued elements of an object by FeatureID
	 * @param object {@link EObject}	
	 * @param featureId (Int)
	 */
	public void getOnDemand(EObject object,int featureId);
	/**
	 * Gets element's container 
	 * @param eObject {@link EObject}
	 * @param featureId {@link Integer}
	 * @return {@link EObject}
	 * @throws Exception 
	 */
	public EObject getContainerOnDemand(EObject eObject, int  featureId);
	
	/**
	 * saves the model changes that have been done 
	 */
	public void save ();
	/**
	 * Unlock the resource
	 */
	public void shutdown();
	
	/**
	 * saves the model changes according to the options in the map 
	 * @param options {@link Map} 
	 */

//	/**
//	 * load the model roots and save them in the resource contents
//	 */
//	public void load ();
//	/**
//	 * load the model roots and save them in the resource contents
//	 * @param options  {@link Map}
//	 */
//	public void load (Map options);
	/**
	 * Notify the proxy manager when a element is accessed
	 * @param eObject {@link EObject}
	 * @param feature {@link EStructuralFeature}
	 */
	public void notifyGet(EObject eObject, EStructuralFeature feature);
	/**
	 * unload the partition holding the <b>ID</b> {@codePID}
	 * @param PID {@link Integer}
	 */
	void unload(int PID);
	/**
	 * return all the instances of type <b>eClass</b>
	 * @param eClass {@link EClass}
	 * @return
	 */
	public EList<INeo4emfObject>  getAllInstances (EClass eClass);
	/**
	 * return all the instances of type <b>eClass</b>
	 * @param eClassID {@link Integer}
	 * @return {@link List}
	 */
	public EList<INeo4emfObject>  getAllInstances (int eClassID);
	/**
	 * Sets the relationships Map in the persistenceManager
	 * @param map
	 */
	void setRelationshipsMap(Map<String,Map<Point,RelationshipType>> map);
	
	
}
