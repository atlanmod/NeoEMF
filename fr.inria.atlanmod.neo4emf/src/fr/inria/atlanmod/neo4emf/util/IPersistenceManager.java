package fr.inria.atlanmod.neo4emf.util;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

import scala.collection.generic.BitOperations.Int;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.Partition;

public interface IPersistenceManager {
	
	/**
	 * save the resource with respect to the options gave in parameter
	 * @param options {@link Map}
	 * @see ISerializer#save(Map)
	 */
	public void save(Map<?, ?> options);
	/**
	 * Save the resource using  the default options 
	 */
	public void save();
	/**
	 * Load the resource using the default options 
	 */
	public void load();
	/**
	 * Load the resource with respect to the options gave in parameter
	 * @param options {@link Map}
	 * @see ILoader#load(Map)
	 */
	public void load(Map<?, ?> options);
	/**
	 * Create a backend's transaction 
	 * @return {@link Transaction}
	 */
	public Transaction beginTx();
	/**
	 * Shutdown the backend
	 * @see IPersistenceService#shutdown()
	 */
	public void shutdown();
	/**
	 * return the equivalent node from an eObject
	 * @param eObj {@link EObject}
	 * @return {@link Node}
	 * @throws NullPointerException
	 * @see {@link IPersistenceService#getNodeById(long)}
	 */
	public Node getNodeById(EObject eObj) throws NullPointerException ;
	/**
	 * return the equivalent relationshipType from eObject
	 * @param clsID {@link Int}
	 * @param eRefID {@link Int}
	 * @return {@link RelationshipType}
	 */
	public RelationshipType getRelTypefromERef(int clsID, int eRefID);
	/**
	 * Create a node from an EObject
	 * @param eObject {@link EObject}
	 * @return {@link Node}
	 * @see IPersistenceService#createNodeFromEObject(EObject)
	 */
	public Node createNodefromEObject(EObject eObject);
	/**
	 * map an {@link EObject} that is loaded to its node Id
	 * @param eObject
	 * @param id
	 */
	public void putNodeId(EObject eObject, long id);
	/**
	 * get the rootNodes first time we load a resource
	 * @return {@link List}
	 */
	public ArrayList<Node> getAllRootNodes();
	/**
	 * Add object to the resource contents
	 * @param objects
	 */
	public void addObjectsToContents(List<INeo4emfObject> objects);
	/**
	 * return node's type as String
	 * @param n {@link Node}
	 * @return {@link String}
	 */
	public String getNodeType(Node n);
	/**
	 * Return the Ns_Uri of the the node's containing package 
	 * @param n {@link Node}
	 * @return {@link String}
	 */
	public String getNodeContainingPackage(Node n);
	/**
	 * Fetch Object's attributes from the backend 
	 * @param obj {@link EObject}
	 */
	public void fetchAttributes(EObject obj);
	/**
	 * Create the correct maps of the root nodes first time we load the resource 
	 * @param objects {@link List}
	 */
	public void putAllToProxy(List<INeo4emfObject> objects);
	/**
	 * get links in demand
	 * @param obj {@link EObject}
	 * @param featureId {@link Int}
	 */
	public void getOnDemand(EObject obj, int featureId);
	/**
	 * create the correct map in the proxy manager
	 * @param object {@link EObject}
	 * @param str {@link EStructuralFeature}
	 * @param partitionID {@link Int}
	 */
	void putToProxy(INeo4emfObject object, EStructuralFeature str, int partitionID);
	
	/**
	 * return the partition ID containing the nodeId <b>id</b>
	 * @param id {@link Long}
	 * @return {@link Int}
	 */
	int proxyContainsLongKey(long id);
	/**
	 * return true if the proxy contains the element
	 * @param obj {@link INeo4emfObject}
	 * @return
	 */
	boolean proxyContainsObjectKey(INeo4emfObject obj);
	
	/**
	 * get an eObject from the proxy using the nodeId
	 * @param id {@link Long}
	 * @return {@link EObject}
	 */
	public EObject getObjectFromProxy(long id);
	
	/**
	 * update the proxy manager while a {@code GET} or {@code SET} notification is captured
	 * @param eObject {@link EObject}
	 * @param feature {@link EStructuralFeature}
	 */
	public void updateProxyManager(INeo4emfObject  eObject, EStructuralFeature feature);
	/**
	 * return true if <b>node</b> is a root node
	 * @param node
	 * @return {@link Boolean}
	 */
	public boolean isRootNode(Node node);
	/**
	 * return available index to create a new {@link Partition}
	 * @return
	 */
	public int getNewPartitionID();
	/**
	 * return true if the eObject is a head of a partition
	 * @param eObject {@link EObject}
	 * @return {@link Boolean}
	 */
	public boolean isHead(EObject eObject);
	/**
	 * Create a new {@link Partition} and set the partition ID and the Head
	 * @param obj
	 * @param partitionID
	 * @return
	 */
	public int createNewPartition(EObject obj, int partitionID);
	/**
	 * Set up a history field to maintain the partition usage 
	 * @param newId
	 */
	public void createNewPartitionHistory(int newId);
	/**
	 * delegate to another eObject proxy to be the eObject delagator
	 * @param eObject {@link EObject}
	 */
	public void delegate(EObject eObject);
	/**
	 * Unload a partition by its ID
	 * @param PID {@link Int}
	 */
	void unloadPartition(int PID);
	public boolean doUnload();
	/**
	 * unload a partition regarding the 
	 * @param unloadStrategyId
	 */
	public void unload(int unloadStrategyId);


	
}
