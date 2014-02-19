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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.jboss.util.NullArgumentException;
import org.jboss.util.collection.SoftValueTreeMap;
import org.neo4j.graphdb.Node;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.IProxyManager;
import fr.inria.atlanmod.neo4emf.impl.AbstractPartition;
import fr.inria.atlanmod.neo4emf.impl.FlatPartition;
import fr.inria.atlanmod.neo4emf.impl.Partition;
import fr.inria.atlanmod.neo4emf.impl.Partition.Tail;
import fr.inria.atlanmod.neo4emf.logger.Logger;

public class ProxyManager implements IProxyManager {
	/**
	 * maps the objects to the appropriate nodes while saving the model
	 */
	private HashMap<? extends EObject, Long> objects2nodes;
	/**
	 * maps the partitions ID to the physical partition 
	 * A partitions a @see {@link TreeMap} mapping objects and their node ID.
	 */
	private TreeMap<Integer,AbstractPartition> partitions;
	/**
	 * saves the usage count of a partition 
	 */	
	private List<UsageCount> partitionsUsageCount;
	/**
	 *saves the usage history of the partitions 
	 */
	private List<Integer> partitionsUsageHistory;
	/**
	 * 
	 */
	private HashMap<INeo4emfObject, INeo4emfObject> object2proxy;
	/**
	 * Maps each partition to the partitions using it  
	 */
	private HashMap<Integer, List<UsageTrace>> usageTraces;
	
	/**
	 * Maps existant element in the Database
	 */
	private HashMap<EClass, TreeMap<Long, EObject>> nodes2objects;
	/**
	 * first Available partitionId
	 */
	int availablePartitionId;
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<EObject, Long> getWeakNodeIds(){
		return (HashMap<EObject, Long>) objects2nodes;
	}


	public ProxyManager(){
		objects2nodes = new HashMap<INeo4emfObject, Long>();
		partitions = new TreeMap<Integer,AbstractPartition>();
		partitionsUsageCount = new ArrayList<UsageCount>();
		partitionsUsageHistory = new ArrayList<Integer>();
		//partitionsWithinObjects = new ArrayList<PartitionsByObject>();
		availablePartitionId = -1;
		usageTraces = new HashMap<Integer,List<UsageTrace>>();
		object2proxy =  new HashMap<INeo4emfObject,INeo4emfObject>();
		nodes2objects = new HashMap<EClass, TreeMap<Long,EObject>>();
	}
	public boolean matchProxy(INeo4emfObject eObject, INeo4emfObject proxyEObject){
		boolean result = object2proxy.containsKey(eObject);
		if (result) object2proxy.put(eObject, proxyEObject);
		return result;
	}
	@Override
	public long  getNode(EObject eObj) {
		return objects2nodes.containsKey(eObj)? objects2nodes.get(eObj) : -1 ;		
	}

	@Override 
	public INeo4emfObject getEObject(int partitionId, long nodeId) {
		return (partitions.containsKey(partitionId)? partitions.get(partitionId).containsKey(nodeId)? partitions.get(partitionId).get(nodeId):null : null);
	}
	
	public void addUsageTrace(int usedPID, int userPID, int featureId, EObject eObj ){
		if (! usageTraces.containsKey(usedPID)) usageTraces.put(usedPID, new ArrayList<UsageTrace>());
		usageTraces.get(usedPID).add(new UsageTrace(userPID, featureId, eObj));
	}
	@Override
	public  TreeMap<Integer,AbstractPartition> getWeakObjectsTree (){
		return partitions;
	}
	@Override
	public int getLeastRecentlyUsedPartition(){
		return partitionsUsageHistory.get(partitionsUsageHistory.size())-1;
	}
	@Override
	public int getLIFOPartition(){
		for (int i = availablePartitionId; i> -1; i--){
			if (partitions.containsKey(i))
				return i;
		}
		return -1;
	}
	@Override
	public int getFIFOPartition() {
		for (int i = 0 ; i>= availablePartitionId; i--){
			if (partitions.containsKey(i))
				return i;
		}
		return -1;
	}
	
	@Override
	public int getLeastFrequentlyPartition(){
		// TODO finish implementation
		UsageCount minUsage= partitionsUsageCount.get(0);
		for (int i=0; i<partitionsUsageCount.size(); i++)
			if (minUsage.compareTo(partitionsUsageCount.get(i)) > 0 && partitions.containsKey(partitionsUsageCount.get(i).PID)){
				minUsage = partitionsUsageCount.get(i);
			}
		return minUsage.PID;
	}

	@Override
	public void updatePartitionsHistory(INeo4emfObject eObject , int feature, boolean  isReference) {
		if (! isReference)
			setEAttirbuteUsage(eObject);
		else 
			setEReferenceUsage(eObject);
		//TODO constructor is not correct or enough			
	}




	private void setEReferenceUsage(INeo4emfObject eObject) {
		if (eObject == null ) {
			Logger.log(IStatus.ERROR, "EObject is Null");
			throw new NullPointerException();	
		}
		int id = GetReferencePartitionID(eObject);
		if (id != -1) {
			setUsageCount(id);
			setHistoryId(id);
		}
		
	}


	private void setHistoryId(int id) {
		int index = -1;
		boolean exist = false;
		for (int i : partitionsUsageHistory ){
			index++;
			if (i==id){
				exist = true;
				partitionsUsageHistory.remove(index);
				partitionsUsageHistory.add(0, id);
				break;
			}
		}
		if (exist ==  false) {
			// TODO
		}
		
	}


	private void setUsageCount(int id) {
		boolean exist = false;
		for (UsageCount element : partitionsUsageCount)
			if (element.PID == id ){
				element.increment();
				exist =  true;
			}
					
		if (exist == false){
			// TODO
		}
	}

	private int GetReferencePartitionID(INeo4emfObject eObject) throws NullArgumentException {
		//TODO to check if its true for FlatPartition 
		Assert.isNotNull(eObject, "Null object");
		
		for (Entry<Integer, AbstractPartition> entry : partitions.entrySet()){
			Object  partition = entry .getValue();
			EObject eObj = ((Partition)partition).geteObjet();
			Assert.isNotNull(partition,"Null Partition");
			if (eObj == null) {
				Logger.log(IStatus.ERROR, new NullArgumentException());
				throw new NullArgumentException();}
			int bol = entry.getValue() instanceof Partition? 1:0 ;
			switch (bol) {
			case 1:
				if (((INeo4emfObject)((Partition)entry.getValue()).geteObjet()).getNodeId() == eObject.getNodeId())
					return entry.getKey();
				break;

			case 0:
				Logger.log(IStatus.WARNING, "FLatPartition does not have an ID : " + entry.getValue() );
				return -1;
				
			}
		}
		return -1;
	}


	private void setEAttirbuteUsage(EObject eObject) {
		int id = ((INeo4emfObject)eObject).getPartitionId();
		setUsageCount(id);
		setHistoryId(id);		
	}


	@Override
	public int newPartitionID() {
		availablePartitionId++;
		return availablePartitionId;
	}

	@Override
	public boolean isHead(EObject eObject) {
		Assert.isNotNull(eObject, "Null Object");
		for (Entry<Integer, AbstractPartition> entry : partitions.entrySet()){
			if (entry.getValue() instanceof Partition)
				if (((Partition)entry.getValue()).geteObjet() == eObject)
					return true;
			}
		return false;
	}


	@Override
	public void movePartitionTo(INeo4emfObject obj,int newIndex, int oldIndex) {
//		if (partitions.containsKey(oldIndex) && partitions.containsKey(newIndex)){
//		partitions.get(newIndex).put(obj.getNodeId(), obj);
//		partitions.get(oldIndex).remove(obj.getNodeId());
//		}
		
	}


	@Override
	public void putHeadToProxy(INeo4emfObject obj ) {
		partitions.put(obj.getPartitionId(), new Partition(obj));
	}

	@Override
	public void addNewHistory(int newId) {
		partitionsUsageHistory.add(0, newId);
		partitionsUsageCount.add(new UsageCount(newId));
		
	}

	protected static class UsageTrace {
		
		public UsageTrace(int userPID, int featureId, EObject eObj ) {
			PID = userPID;
			featureID = featureId;
			eObject = eObj;
		}
		
		EObject eObject;
		int PID;
		int featureID;
		
	}
	
	@Override
	public void moveToPartition(EObject eObj, int fromPID, int toPID, int featureId) {
		if (fromPID == toPID) {
			return;
		}
		if (partitions.containsKey(fromPID)) {
			partitions.get(fromPID).remove(((INeo4emfObject)eObj).getNodeId());
		}
		Tail tail = ((Partition) partitions.get(toPID)).getTail(featureId);
		tail.put(((INeo4emfObject)eObj).getNodeId(), eObj);
		
	}
	protected static class UsageCount implements Comparable<UsageCount>{
		
		/**
		 * int PID
		 */
		int PID;
		/**
		 * int count
		 */
		int count ;
		
		public UsageCount(int newId) {
			PID = newId;
		}
		void increment (){
			count++;
		}
		void setToZero(){
			count = 0;
		}
		@Override
		public int compareTo(UsageCount o) {
			return ((Integer)count).compareTo((Integer)o.count);
			}
		}

	@Override
	public Map<Integer, List<INeo4emfObject>> getSideEffectsMap(
			INeo4emfObject neoObj, int key) {
		List<UsageTrace> usedTraces = new ArrayList<UsageTrace>();
		Map<Integer, List<INeo4emfObject>> map= new HashMap<Integer,List<INeo4emfObject>>();
		if (usageTraces.containsKey(key))
			for (UsageTrace trace : usageTraces.get(key)){
				if (trace.eObject.equals(neoObj)){
					if (!map.containsKey(trace.PID))
						map.put(trace.PID, new ArrayList<INeo4emfObject>());
					AbstractPartition partition = partitions.get(trace.PID); 	
					List<? extends INeo4emfObject> list = (partitions.get(trace.PID) instanceof Partition)? ((Partition)partition).getTailByFeatureID(trace.featureID):
						((FlatPartition)partition).flattened();
					map.get(key).addAll(list);
					usedTraces.add(trace);
				}
			}
		if (!usageTraces.isEmpty())
			usageTraces.get(key).removeAll(usedTraces);
		return map;
		}


	@Override
	public void putToProxy(INeo4emfObject obj) {
		EClass eClass = obj.eClass();
		
		if (nodes2objects.containsKey(eClass)) {
			nodes2objects.get(eClass).put(obj.getNodeId(), obj);
		} else {
				TreeMap<Long, EObject> treeMap  = new TreeMap<Long, EObject>();
				treeMap.put (obj.getNodeId(), obj);
				nodes2objects.put(eClass, treeMap);
			}	
		}


	@Override
	public INeo4emfObject getObjectFromProxy(EClass eClassifier, Node n) {
		if (nodes2objects.containsKey(eClassifier)) {
			return (INeo4emfObject) (nodes2objects.get(eClassifier).containsKey(n.getId()) ? nodes2objects.get(eClassifier).get(n.getId()) : null) ;
		}
		return null;
	}
	}
