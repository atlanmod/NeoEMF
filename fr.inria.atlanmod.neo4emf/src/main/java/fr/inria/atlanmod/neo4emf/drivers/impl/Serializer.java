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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;

public class Serializer implements ISerializer {

	/**
	 * TODO: Comment this
	 */
	PersistenceManager manager;

	/**
	 * TODO: Comment this
	 */
	Map<String, Object> defaultOptions = new HashMap<String, Object>();

	// INodeBuilder nodeBuilder;
	public Serializer(PersistenceManager manager) {
		this.manager = manager;
		for (int i = 0; i < saveOptions.length; i++)
			defaultOptions.put(saveOptions[i], saveDefaultValues[i]);
	}

	/**
	 * Saves changes and empties the change lof
	 */
	public void save(Map<String, Object> options) {
		/*
		 * The number of database operations within a transaction is bounded,
		 * that's why changes may be processed in several transactions.
		 */
		if (options != null){
			defaultOptions.putAll(options);
		}
		int max = (int) defaultOptions.get(MAX_OPERATIONS_PER_TRANSACTION);
		List<Entry> changes = manager.getResource().getChangeLog().changes();
		
		while (!changes.isEmpty()) {
			int times = Math.min(max, changes.size());
			List<Entry> subset = changes.subList(0, times);
			NETransaction tx = manager.createTransaction();
			try {
				for(Entry each : subset) {
					each.process(this);
				}
				tx.success();
				subset.clear();
			} catch (Exception e) {
				tx.abort();;
			} finally {
				tx.commit();
			}
		}
	}

	public void deleteExistingObject(EObject geteObject) {

	}

	/**
	 * @deprecated Use {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceManager#setAttributeValue(fr.inria.atlanmod.neo4emf.drivers.impl.Serializer,EObject,EAttribute,Object)} instead
	 */
	public void setAttributeValue(INeo4emfObject eObject, EAttribute at,
			Object newValue) {
				manager.setAttributeValue(eObject, at, newValue);
			}

	/**
	 * @deprecated Use {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceManager#removeExistingLink(EObject,EReference,Object)} instead
	 */
	public void removeExistingLink(EObject eObject, EReference eRef,
			Object object) {
				manager.removeExistingLink(eObject, eRef, object);
			}

	/**
	 * @deprecated Use {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceManager#addNewLink(fr.inria.atlanmod.neo4emf.drivers.impl.Serializer,EObject,EReference,Object)} instead
	 */
	public void addNewLink(EObject eObject, EReference eRef, Object object)
			throws NullPointerException {
				manager.addNewLink(eObject, eRef, object);
			}

	/**
	 * @deprecated Use {@link fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceManager#createNewObject(fr.inria.atlanmod.neo4emf.drivers.impl.Serializer,INeo4emfObject)} instead
	 */
	public void createNewObject(INeo4emfObject eObject) {
		manager.createNewObject(eObject);
	}
}
