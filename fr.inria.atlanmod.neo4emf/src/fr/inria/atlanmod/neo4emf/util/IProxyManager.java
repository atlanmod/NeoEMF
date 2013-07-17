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

import java.util.WeakHashMap;

import org.arakhne.util.ref.WeakValueTreeMap;
import org.eclipse.emf.ecore.EObject;
import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.Partition;

public interface IProxyManager {
	long getNode(EObject eObj);
	WeakHashMap<EObject, Long> getWeakNodeIds();
	INeo4emfObject getEObject(int id,long id1);
	WeakValueTreeMap<Integer,Partition>getWeakObjectsTree ();
	void updatePartitionsHistory(INeo4emfObject eObject  , int feature, boolean  isReference);
	int newPartitionID();
	boolean isHead(EObject eObject);
	void movePartitionTo(INeo4emfObject obj,int newIndex, int oldIndex);
	void putHeadToProxy(INeo4emfObject obj);
	void addNewHistory(int newId);
	Partition getLeastRecentlyUsedPartition();
	Partition getLIFOPartition();
	Partition getLeastFrequentlyPartition();
	Partition getFIFOPartition();
	
}
