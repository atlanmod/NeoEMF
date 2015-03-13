package fr.inria.atlanmod.neo4emf;

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

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.drivers.ILoader;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;

public interface INeo4emfResource extends Resource, Resource.Internal {

	public String MAX_OPERATIONS_PER_TRANSACTION = ISerializer.MAX_OPERATIONS_PER_TRANSACTION;

	public static final String DUPLICATION_TOLERANT = ILoader.DUPLICATION_TOLERANT;

	public static final String DYNAMIC_LOADING = ILoader.DYNAMIC_LOADING;

	public static final String STATIC_LOADING = ILoader.STATIC_LOADING;

	public static final String FULL_LOADING = ILoader.FULL_LOADING;

	public static final String LOADING_STRATEGY = ILoader.LOADING_STRATEGY;

	/**
	 * Fetches the single-valued attributes lazily on demand
	 * 
	 * @param object
	 *            {@link EObject}
	 */
	public void fetchAttributes(EObject object);

	/**
	 * Gets multi-valued elements of an object by FeatureID
	 * 
	 * @param object
	 *            {@link EObject}
	 * @param featureId
	 *            (Int)
	 */
	public void getOnDemand(EObject object, int featureId);

	/**
	 * Gets element's container
	 * 
	 * @param eObject
	 *            {@link EObject}
	 * @param featureId
	 *            {@link Integer}
	 * @return {@link EObject}
	 * @throws Exception
	 */
	public EObject getContainerOnDemand(EObject eObject, int featureId);

	/**
	 * saves the model changes that have been done
	 */
	public void save();

	/**
	 * Unlock the resource
	 */
	public void shutdown();

	/**
	 * saves the model changes according to the options in the map
	 * 
	 * @param options
	 *            {@link Map}
	 */

	/**
	 * return all the instances of type <b>eClass</b>
	 * 
	 * @param eClass
	 *            {@link EClass}
	 * @return
	 */
	public EList<INeo4emfObject> getAllInstances(EClass eClass);

	/**
	 * return all the instances of type <b>eClass</b>
	 * 
	 * @param eClassID
	 *            {@link Integer}
	 * @return {@link List}
	 */
	public EList<INeo4emfObject> getAllInstances(int eClassID);

	/**
	 * 
	 * @return the change log
	 */
	public IChangeLog<Entry> getChangeLog();

	/**
	 * Allow access to the persistence manager attached to this resource. Needed
	 * for testing proposes.
	 * 
	 * @return The Persistent Manager
	 */
	public IPersistenceManager getPersistenceManager();

}
