package fr.inria.atlanmod.neo4emf.drivers;


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
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.jboss.util.collection.SoftValueTreeMap;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.AbstractPartition;

public interface IProxyManager {
	long getNode(EObject eObj);
	WeakHashMap<EObject, Long> getWeakNodeIds();
	INeo4emfObject getEObject(int id,long id1);
	SoftValueTreeMap<Integer,AbstractPartition>getWeakObjectsTree ();
	void updatePartitionsHistory(INeo4emfObject eObject  , int feature, boolean  isReference);
	int newPartitionID();
	boolean isHead(EObject eObject);
	void movePartitionTo(INeo4emfObject obj,int newIndex, int oldIndex);
	void putHeadToProxy(INeo4emfObject obj);
	void addNewHistory(int newId);
	int getLeastRecentlyUsedPartition();
	int getLIFOPartition();
	int getLeastFrequentlyPartition();
	int getFIFOPartition();
	void moveToPartition(EObject eObj,int fromPID, int toPID, int featureId);
	Map<Integer, ArrayList<INeo4emfObject>> getSideEffectsMap(
			INeo4emfObject neoObj, int key);	
}
