package fr.inria.atlanmod.neo4emf.util.impl;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfResource;
import fr.inria.atlanmod.neo4emf.impl.Partition;
import fr.inria.atlanmod.neo4emf.util.*;


public class PersistenceManager implements IPersistenceManager {
	
	/**
	 * the persistence Backend {@link PersistenceService}
	 */
	IPersistenceService persistenceService;
	/**
	 * The loader and eObjects builder {@link Loader}
	 */
	ILoader loader;
	/**
	 * Serializer and nodes converter {@link Serializer}
	 */
	ISerializer serializer;
	/**
	 * Unloader {@link Unloader}
	 */
	IUnloader unloader;
	/**
	 * The resource {@link Neo4emfResource}
	 */
	INeo4emfResource resource;
	/**
	 * The loaded elements manager {@link ProxyManager}
	 */
	IProxyManager proxyManager;
	/**
	 * {@link EReference} to {@link RelationshipType} mapping of the package
	 */
	Map<Point,RelationshipType> eRef2relType;	
	/**
	 * Global constructor
	 * @param neo4emfResource {@link Neo4emfResource}
	 * @param storeDirectory {@link String}
	 * @param eRef2relType {@link Map}
	 */
	public PersistenceManager(Neo4emfResource neo4emfResource,
			String storeDirectory, Map<Point,RelationshipType> eRef2relType) {
		resource = neo4emfResource;
		persistenceService = IPersistenceServiceFactory.eINSTANCE.createPersistenceService(storeDirectory,this);
		serializer = new Serializer(this);
		this.eRef2relType = eRef2relType;
		proxyManager = new ProxyManager();
		loader = new Loader(this);
		unloader = new Unloader(this, null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void save(Map<?,?> options) {
		serializer.save((Map<String,Object>)options);		
	}		
	@Override
	public void load() {
		load (null);		
	}	
	@Override
	public void load(Map<?, ?> options) {
		loader.load(options);	
	}
	@Override
	public void save() {
		serializer.save(null);	
	}
	@Override
	public Transaction beginTx() {
		return persistenceService.beginTx();	 
	}
	@Override
	public void shutdown() {
		persistenceService.shutdown();	
	}
	@Override
	public Node getNodeById (EObject eObj) throws NullPointerException {
		return persistenceService.getNodeById(((INeo4emfObject)eObj).getNodeId());
	}
	@Override
	public RelationshipType getRelTypefromERef(int clsID, int eRefID) {
		Point point = new Point (clsID,eRefID);
		return eRef2relType.get(point);
	}
	@Override
	public Node createNodefromEObject(EObject eObject) {
		return persistenceService.createNodeFromEObject(eObject);
	}
	@Override
	public void putNodeId(EObject eObject, long id) {
		if (! proxyManager.getWeakNodeIds().containsKey(eObject))
			proxyManager.getWeakNodeIds().put(eObject, id);		
	}
	@Override
	public ArrayList<Node> getAllRootNodes() {
		return persistenceService.getAllRootNodes();	
	}
	@Override
	public void addObjectsToContents(List<INeo4emfObject> objects) {
			resource.getContents().addAll(objects);		
	}
	@Override
	public String getNodeType(Node n) {
		return persistenceService.getNodeType(n);	
	}
	@Override
	public String getNodeContainingPackage(Node n) {
		return persistenceService.getContainingPackage(n);	
	}
	@Override
	public void fetchAttributes(EObject obj) {
		Node node = getNodeById(obj);
		loader.fetchAttributes(obj,node);	
	}
	@Override
	public void putToProxy(INeo4emfObject object, EStructuralFeature str, int partitionID){
		Partition partition = proxyManager.getWeakObjectsTree().get(partitionID);
		partition.put(object.getNodeId(), object, str);
	}
	@Override
	public void putAllToProxy(List<INeo4emfObject> objects) {
		for (INeo4emfObject obj : objects)
				proxyManager.putHeadToProxy(obj);
	}
	@Override
	public void getOnDemand(EObject obj, int featureId) {	
		List <Node> nodes= persistenceService.getNodesOnDemand(((INeo4emfObject)obj).getNodeId(),eRef2relType.get(new Point(obj.eClass().getClassifierID(),featureId)));
		loader.getObjectsOnDemand(obj, featureId,getNodeById(obj), nodes);
	}
	@Override
	public int  proxyContainsLongKey(long id){
		for (Map.Entry<Integer,Partition> entry : proxyManager.getWeakObjectsTree().entrySet())
			if (entry.getValue()!= null && entry.getValue().containsKey(id))
				return entry.getKey();
		return -1;	
	}
	@Override
	public boolean proxyContainsObjectKey(INeo4emfObject obj){
		return proxyManager.getWeakNodeIds().containsKey(obj);
	}
	@Override
	public EObject getObjectFromProxy(long id) {
		int i = proxyContainsLongKey(id);
		return i == -1 ? null :proxyManager.getEObject(i, id);
	}
	@Override
	public void updateProxyManager(INeo4emfObject eObject, EStructuralFeature feature) {
		 proxyManager.updatePartitionsHistory(eObject, feature.getFeatureID(), feature instanceof EReference);
			
	}
	@Override
	public boolean isRootNode(Node node) {
		return persistenceService.isRootNode(node);
	}
	@Override
	public int getNewPartitionID() {
		return proxyManager.newPartitionID();
	}
	@Override
	public boolean isHead(EObject eObject) {
		return proxyManager.isHead(eObject);
	}
	@Override
	public int  createNewPartition(EObject obj, int partitionID) {
		
		int newIndex = getNewPartitionID();
		((INeo4emfObject)obj).setPartitionId(partitionID);
		((INeo4emfObject)obj).setProxy(true);
		proxyManager.getWeakObjectsTree().put(newIndex, new Partition(obj));
		//proxyManager.movePartitionTo(((INeo4emfObject) obj),newIndex, oldIndex);
		return newIndex;
	}
	@Override
	public void createNewPartitionHistory(int newId) {
		proxyManager.addNewHistory(newId);
	}
	@Override
	public void delegate(EObject eObject) {
		boolean isFound = false;
		int newPID=-1;
		INeo4emfObject neoObject = (INeo4emfObject) eObject;
		for (Map.Entry<Integer, Partition> entry: proxyManager.getWeakObjectsTree().entrySet()){
			if (entry.getKey() == neoObject.getPartitionId() || !entry.getValue().containsKey(neoObject.getNodeId()))
				continue;
			if (!isFound){
			INeo4emfObject object = entry.getValue().get(neoObject.getNodeId());
			object.setProxy(false);
			newPID = entry.getKey();
			object.setPartitionId(newPID);
			}else {
				entry.getValue().get(neoObject.getNodeId()).setPartitionId(newPID);
			}
		}
	}
	 @Override
	public void unloadPartition(int PID){
		 Partition partition = proxyManager.getWeakObjectsTree().get(PID);
		 unloader.unloadPartition(partition);
		 proxyManager.getWeakObjectsTree().remove(PID);
		 Runtime.getRuntime().gc();
	 }

	@Override
	public boolean doUnload() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void unload(int unloadStrategyId) {
		Partition partition = null;
		switch (unloadStrategyId) {
		case IUnloader.LIFO:
			partition = proxyManager.getLIFOPartition();
			break;
		case IUnloader.FIFO : 
			partition = proxyManager.getFIFOPartition();
			break;
		case IUnloader.LEAST_RECENTLY_USED : 
			partition= proxyManager.getLeastRecentlyUsedPartition();
			break;
		case IUnloader.LEAST_FREQUENTLY_USED : 
			partition = proxyManager.getLeastFrequentlyPartition();
			break;
		}
		unloader.unloadPartition(partition);	
	}

	
}
