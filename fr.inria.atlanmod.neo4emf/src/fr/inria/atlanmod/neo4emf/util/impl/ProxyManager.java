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
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.jboss.util.collection.SoftValueTreeMap;
//import org.jboss.util.collection.TreeMap;


import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.AbstractPartition;
import fr.inria.atlanmod.neo4emf.impl.FlatPartition;
import fr.inria.atlanmod.neo4emf.impl.Partition;
import fr.inria.atlanmod.neo4emf.util.IProxyManager;

public class ProxyManager implements IProxyManager {
	/**
	 * maps the objects to the appropriate nodes while saving the model
	 */
	private WeakHashMap<? extends EObject, Long> objects2nodes;
	/**
	 * maps the partitions ID to the physical partition 
	 * A partitions a @see {@link TreeMap} mapping objects and their node ID.
	 */
	private SoftValueTreeMap<Integer,AbstractPartition> partitions;
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
		partitions = new SoftValueTreeMap<Integer,AbstractPartition>();
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
	public  SoftValueTreeMap<Integer,AbstractPartition> getWeakObjectsTree (){
		return partitions;
	}
	@Override
	public AbstractPartition getLeastRecentlyUsedPartition(){
		return partitions.get(partitionsUsageHistory.get(partitionsUsageHistory.size())-1);
	}
	@Override
	public AbstractPartition getLIFOPartition(){
		for (int i = availablePartitionId; i> -1; i--){
			if (partitions.containsKey(i))
				return partitions.get(i);
		}
		return null;
	}
	@Override
	public AbstractPartition getFIFOPartition() {
		for (int i = 0 ; i>= availablePartitionId; i--){
			if (partitions.containsKey(i))
				return partitions.get(i);
		}
		return null;
	}
	
	@Override
	public AbstractPartition getLeastFrequentlyPartition(){
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
		//TODO to check if its true for FlatPartition 
		for (Entry<Integer, AbstractPartition> entry : partitions.entrySet()){
			int bol = entry.getValue() instanceof Partition? 1:0 ;
			switch (bol) {
			case 1:
				if (((INeo4emfObject)((Partition)entry.getValue()).geteObjet()).getNodeId() == eObject.getNodeId())
					return entry.getKey();
				break;

			case 0:
				System.out.println("FlatPartition");
				return -1;
				// TODO do not forget the break statement
			}
		}
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
		for (Entry<Integer, AbstractPartition> entry : partitions.entrySet()){
			if (entry.getValue() instanceof Partition){
			if (((Partition)entry.getValue()).geteObjet() == eObject)
				return true;}
			else if (((FlatPartition)entry.getValue()).containsKey(((INeo4emfObject)eObject).getNodeId()))
				return true;}
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
