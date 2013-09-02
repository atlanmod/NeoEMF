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
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.drivers.*;
import fr.inria.atlanmod.neo4emf.impl.AbstractPartition;
import fr.inria.atlanmod.neo4emf.impl.FlatPartition;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfResource;
import fr.inria.atlanmod.neo4emf.impl.Partition;


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
	Map<String,Map<Point,RelationshipType>> eRef2relType;	
	/**
	 * Global constructor
	 * @param neo4emfResource {@link Neo4emfResource}
	 * @param storeDirectory {@link String}
	 * @param eRef2relType {@link Map}
	 */
	public PersistenceManager(Neo4emfResource neo4emfResource,
			String storeDirectory, Map<String,Map<Point,RelationshipType>> eRef2relType) {
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
		try{
			serializer.save((Map<String,Object>)options);	}
		catch(Exception e){ 
			shutdown();
			e.printStackTrace();
		}
	}		
	@Override
	public void load() {
		load (null);		
	}	
	@Override
	public void load(Map<?, ?> options) {
		try {
			loader.load(options);	}
		catch(Exception e){ 
			shutdown();
			e.printStackTrace();
		}
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
	public RelationshipType getRelTypefromERef(String key, int clsID, int eRefID) {
		return eRef2relType.get(key).get(new Point (clsID,eRefID));

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
		AbstractPartition partition = proxyManager.getWeakObjectsTree().get(partitionID);
		if (partition == null) {
			System.out.println("partition does not exist "); return ;}
		if (partition instanceof Partition)
		((Partition)partition).put(object.getNodeId(), object, str);
		else 
		System.out.println("TODO");
		// TODO put flatened to proxy
	}
	@Override
	public void putAllToProxy(List<INeo4emfObject> objects) {
		for (INeo4emfObject obj : objects)
			proxyManager.putHeadToProxy(obj);
	}
	@Override
	public void getOnDemand(EObject obj, int featureId) {	
		try{
			List <Node> nodes= persistenceService.getNodesOnDemand(((INeo4emfObject)obj).getNodeId(),
					getRelTypefromERef(obj.eClass().getEPackage().getNsURI(), obj.eClass().getClassifierID(), featureId));
			loader.getObjectsOnDemand(obj, featureId,getNodeById(obj), nodes);
		}catch(Exception e){ 
			shutdown();
			e.printStackTrace();
		}
	}
	
	@Override
	public EObject getContainerOnDemand(EObject eObject, int featureId)  {
		EObject eResult = null;
		try{
			List<Node> singleNode = persistenceService.getNodesOnDemand(((INeo4emfObject)eObject).getNodeId(), 
				getRelTypefromERef(eObject.eClass().getEPackage().getNsURI(), eObject.eClass().getClassifierID(), featureId));		
			eResult = loader.getContainerOnDemand(eObject, featureId, getNodeById(eObject),singleNode.get(0));
			}catch(Exception e){ 
				shutdown();
				e.printStackTrace();
			}
		return  eResult;
	}

	
	@Override
	public int  proxyContainsLongKey(long id){
		for (Entry<Integer, AbstractPartition> entry : proxyManager.getWeakObjectsTree().entrySet())
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
	public void delegate(EObject eObject) {
		boolean isFound = false;
		int newPID=-1;
		INeo4emfObject neoObject = (INeo4emfObject) eObject;
		for (Entry<Integer, AbstractPartition> entry: proxyManager.getWeakObjectsTree().entrySet()){
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
		AbstractPartition partition = proxyManager.getWeakObjectsTree().get(PID);
		unloader.unloadPartition(partition, PID);
		//proxyManager.getWeakObjectsTree().remove(PID);
		Runtime.getRuntime().gc();
	}

	@Override
	public boolean doUnload() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unload(int unloadStrategyId) {
		int partition = -1;
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
		unloader.unloadPartition(proxyManager.getWeakObjectsTree().get(partition), partition);	
	}

	@Override
	public EList<INeo4emfObject> getAllInstancesOfType(EClass eClass) {
		EList<EClass> classesList = ((Loader)loader).subClassesOf(eClass);
		EList<INeo4emfObject> allInstances = new BasicEList<INeo4emfObject>();
		List<Node> nodeList = new ArrayList<Node>();
		try{
			for (EClass eCls : classesList){	
				nodeList = persistenceService.getAllNodesOfType (eClass);
				if(nodeList.isEmpty()) continue;
				allInstances.addAll(loader.getAllInstances(eCls, nodeList));
			}
		}catch(Exception e){ 
			shutdown();
			e.printStackTrace();
		}
		return allInstances;
	}

	@Override
	public void createNewPartitionHistory(int newId) {
		proxyManager.addNewHistory(newId);
	}

	@Override
	public FlatPartition createNewFlatPartition(int id) {
		FlatPartition result = new FlatPartition();
		proxyManager.getWeakObjectsTree().put(id,result );
		return result;
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
	public void moveToPartition(EObject eObj, int fromPID, int toPID, int featureId) {
		proxyManager.moveToPartition(eObj, fromPID, toPID, featureId);
		
	}

	@Override
	public void setUsageTrace(int PID, int partitionId, int featureId, EObject eObject) {
		((ProxyManager)proxyManager).addUsageTrace(PID, partitionId, featureId, eObject);
		
	}

	@Override
	public Map<Integer, ArrayList<INeo4emfObject>> getAffectedElement(
			INeo4emfObject neoObj, int key) {
		return proxyManager.getSideEffectsMap( neoObj, key);
	}

	@Override
	public void setRelationshipsMap(Map<String,Map<Point,RelationshipType>> map) {
		eRef2relType = map;
		
	}


}
