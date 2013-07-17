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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.arakhne.util.ref.WeakValueTreeMap;


import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.Partition;
import fr.inria.atlanmod.neo4emf.util.IProxyManager;

public class ProxyManager implements IProxyManager {
	/**
	 * maps the objects to the appropriate nodes while saving the model
	 */
	private WeakHashMap<? extends EObject, Long> objects2nodes;
	/**
	 * maps the partitions ID to the physical partition 
	 * A partitions a @see {@link WeakValueTreeMap} mapping objects and their node ID.
	 */
	private WeakValueTreeMap<Integer,Partition> partitions;
	/**
	 * saves the usage count of a partition 
	 */	
	private List<UsageCount> partitionsUsageCount;
	/**
	 *saves the usage history of the partitions 
	 */
	private List<Integer> partitionsUsageHistory;
	/**
	 * first Available partitionId
	 */
	int availablePartitionId;
	@SuppressWarnings("unchecked")
	@Override
	public WeakHashMap<EObject, Long> getWeakNodeIds(){
		return (WeakHashMap<EObject, Long>) objects2nodes;
	}


	public ProxyManager(){
		objects2nodes = new WeakHashMap<INeo4emfObject, Long>();
		partitions = new WeakValueTreeMap<Integer,Partition>();
		partitionsUsageCount = new ArrayList<UsageCount>();
		partitionsUsageHistory = new ArrayList<Integer>();
		//partitionsWithinObjects = new ArrayList<PartitionsByObject>();
		availablePartitionId = -1;
	}
	@Override
	public long  getNode(EObject eObj) {
		return objects2nodes.containsKey(eObj)? objects2nodes.get(eObj) : -1 ;		
	}

	@Override 
	public INeo4emfObject getEObject(int partitionId, long nodeId) {
		return (partitions.containsKey(partitionId)? partitions.get(partitionId).containsKey(nodeId)? partitions.get(partitionId).get(nodeId):null : null);
	}

	@Override
	public  WeakValueTreeMap<Integer,Partition> getWeakObjectsTree (){
		return partitions;
	}
	@Override
	public Partition getLeastRecentlyUsedPartition(){
		return partitions.get(partitionsUsageHistory.get(partitionsUsageHistory.size())-1);
	}
	@Override
	public Partition getLIFOPartition(){
		for (int i = availablePartitionId; i> -1; i--){
			if (partitions.containsKey(i))
				return partitions.get(i);
		}
		return null;
	}
	@Override
	public Partition getFIFOPartition() {
		for (int i = 0 ; i>= availablePartitionId; i--){
			if (partitions.containsKey(i))
				return partitions.get(i);
		}
		return null;
	}
	
	@Override
	public Partition getLeastFrequentlyPartition(){
		// TODO finish implementation
		UsageCount minUsage= partitionsUsageCount.get(0);
		for (int i=0; i<partitionsUsageCount.size(); i++)
			if (minUsage.compareTo(partitionsUsageCount.get(i)) > 0 && partitions.containsKey(partitionsUsageCount.get(i).PID)){
				minUsage = partitionsUsageCount.get(i);
			}
		return partitions.get(minUsage.PID);
	}

	@Override
	public void updatePartitionsHistory(INeo4emfObject eObject , int feature, boolean  isReference) {
		if (! isReference)
			setEAttirbuteUsage(eObject);
		else 
			setEReferenceUsage(eObject);//TODO constructor is not correct or enough			
	}




	private void setEReferenceUsage(INeo4emfObject eObject) {
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


	private int GetReferencePartitionID(INeo4emfObject eObject) {
		for (Map.Entry<Integer, Partition> entry : partitions.entrySet())
			if (((INeo4emfObject)entry.getValue().geteObjet()).getNodeId() == eObject.getNodeId())
				return entry.getKey();
		return -1;
	}


	private void setEAttirbuteUsage(EObject eObject) {
		int id = ((INeo4emfObject)eObject).getPartitionId();
		setUsageCount(id);
		setHistoryId(id);
		
	}


//	private void setUsageHistoryForReference(int index) {
//		// TODO Auto-generated method stub
//		for (int i=0; i< partitionsUsageHistory.size(); i++){
//			if (partitionsUsageHistory.get(i)==index){
//				partitionsUsageHistory.remove(i);
//				partitionsUsageHistory.add(0, index);
//				return;
//			}
//		}
//	}


	@Override
	public int newPartitionID() {
		availablePartitionId++;
		return availablePartitionId;
		
	}

	@Override
	public boolean isHead(EObject eObject) {
		for (Map.Entry<Integer, Partition> entry : partitions.entrySet())
			if (entry.getValue().geteObjet() == eObject)
				return true;
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
		// first time created elements 
		partitions.put(obj.getPartitionId(), new Partition(obj));
	
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
	public void addNewHistory(int newId) {
		partitionsUsageHistory.add(0, newId);
		partitionsUsageCount.add(new UsageCount(newId));
		
	}



	}
