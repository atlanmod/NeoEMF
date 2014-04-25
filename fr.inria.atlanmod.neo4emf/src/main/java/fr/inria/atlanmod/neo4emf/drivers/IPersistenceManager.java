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

package fr.inria.atlanmod.neo4emf.drivers;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.drivers.impl.NETransaction;


public interface IPersistenceManager {

	/**
	 * save the resource with respect to the options gave in parameter
	 * 
	 * @param options
	 *            {@link Map}
	 * @see ISerializer#save(Map)
	 */
	public void save(Map<?, ?> options);

	/**
	 * Save the resource using the default options
	 */
	public void save();

	/**
	 * Load the resource using the default options
	 */
	public void load();

	/**
	 * Load the resource with respect to the options gave in parameter
	 * 
	 * @param options
	 *            {@link Map}
	 * @see ILoader#load(Map)
	 */
	public void load(Map<?, ?> options);

	/**
	 * Create a backend's transaction
	 * 
	 * @return {@link Transaction}
	 */
	public NETransaction createTransaction();
	
	public void cleanIndexes();
	/**
	 * Shutdown the backend
	 * 
	 * @see IPersistenceService#shutdown()
	 */
	public void shutdown();

	/**
	 * return the temporary attribute node from an eObject
	 * @param eObj {@link EObject}
	 * @return {@link Node}
	 * @throws NullPointerException
	 * @see {@link IPersistenceService#getNodeById(long)}
	 */
	public Node getAttributeNodeById(EObject eObj);
	
	public Node getAttributeNode(Node n);
	
	/**
	 * Add object to the resource contents
	 * 
	 * @param objects
	 */
	public void addObjectsToContents(List<INeo4emfObject> objects);


	/**
	 * Fetch Object's attributes from the backend
	 * 
	 * @param obj
	 *            {@link EObject}
	 */
	public void fetchAttributes(EObject obj);

	/**
	 * get links in demand
	 * 
	 * @param obj
	 *            {@link EObject}
	 * @param featureId
	 *            {@link int}
	 */
	public void getOnDemand(EObject obj, int featureId);

	/**
	 * return all the instanecs of type eClass
	 * 
	 * @see INeo4emfResource#getAllInstances
	 * @return {@link List}
	 */
	public EList<INeo4emfObject> getAllInstancesOfType(EClass eClass);

	/**
	 * Get the element's container
	 * 
	 * @param eObject
	 * @param featureId
	 * @return {@link EObject}
	 * @throws Exception
	 */
	public EObject getContainerOnDemand(EObject eObject, int featureId);
	
	public INeo4emfObject getObjectFromProxy(EClass eClassifier, Node n);
	
	/**
	 * <p>
	 * Warning : This method is public only for test purpose
	 * </p>
	 * @return the proxy manager
	 */
	public IProxyManager getProxyManager();

}
