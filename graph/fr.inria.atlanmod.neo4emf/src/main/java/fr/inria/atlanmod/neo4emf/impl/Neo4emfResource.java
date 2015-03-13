package fr.inria.atlanmod.neo4emf.impl;

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

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;
import fr.inria.atlanmod.neo4emf.change.impl.DeleteObject;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.change.impl.NewObject;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;
import fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceManager;

public class Neo4emfResource extends ResourceImpl implements INeo4emfResource {

	private final IChangeLog<Entry> changeLog;

	/**
	 * The persistence manager holds the communication between the resource and
	 * the different persistence units
	 * 
	 * @see #IPersistenceManagerl
	 */
	private IPersistenceManager persistenceManager;

	/**
	 * Neo4emfResource Constructor
	 * 
	 * @param configuration
	 */
	public Neo4emfResource(NEConfiguration configuration) {

		assert configuration != null : "Null configuration";
		this.persistenceManager = new PersistenceManager(this, configuration);
		// this.changeLog = IChangeLogFactory.eINSTANCE.createChangeLog();
		this.changeLog = IChangeLogFactory.eINSTANCE.createChangeLog(this);
	}

	/**
	 * @link {@link INeo4emfResource#fetchAttributes(EObject)}
	 */
	@Override
	public void fetchAttributes(final EObject obj) {
		this.persistenceManager.fetchAttributes(obj);

	}

	/**
	 * @link {@link INeo4emfResource#getOnDemand(EObject, int)}
	 */
	@Override
	public void getOnDemand(final EObject obj, final int featureId) {
		this.persistenceManager.getOnDemand(obj, featureId);
	}

	/**
	 * {@link INeo4emfResource#save()}
	 */
	@Override
	public void save() {
		save(null);
	}

	/**
	 * {@link INeo4emfResource#save(Map)}
	 */
	@Override
	public void save(Map<?, ?> options) {
		this.persistenceManager.save(options);
	}

	/**
	 * shuting down the backend
	 */
	@Override
	public void shutdown() {
		this.persistenceManager.shutdown();
		isLoaded = false;
	}

	/**
	 * load the roots elements of the model
	 * 
	 * @param options
	 *            {@link Map}
	 */
	@Override
	public void load(Map<?, ?> options) {

		if (!isLoaded) {
			try {
				isLoading = true;
				this.persistenceManager.load(options);
				isLoaded = true;
			} catch(Throwable e) {
				e.printStackTrace();
			} finally {
				isLoading = false;
			}
		}
	}

	@Override
	public EList<INeo4emfObject> getAllInstances(EClass eClass) {
		EList<INeo4emfObject> result = this.persistenceManager
				.getAllInstancesOfType(eClass);
		// getContents().addAll(result);
		return result;
	}

	@Override
	public EList<INeo4emfObject> getAllInstances(int eClassID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EObject getContainerOnDemand(EObject eObject, int featureId) {
		// TODO Auto-generated method stub
		return this.persistenceManager.getContainerOnDemand(eObject, featureId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.ecore.resource.impl.ResourceImpl#attached(org.eclipse
	 * .emf.ecore.EObject)
	 */
	@Override
	public void attached(EObject eObject) {
		super.attached(eObject);
		Neo4emfObject neoObject = (Neo4emfObject) eObject;
		if (eObject.eResource() != this || neoObject.getNodeId() == -1) {
			if (!isLoading) {
				addChangeLogCreateEntry(neoObject);
				Iterator<EObject> it = neoObject.eAllResolvedContents();
				while (it.hasNext()) {
					Neo4emfObject itEObject = (Neo4emfObject) it.next();
					addChangeLogCreateEntry(itEObject);
//					if(itEObject.eResource() == null) {
//						itEObject.eSetDirectResource(this);
//					}
				}
			}
		}
	}

	@Override
	public void detached(EObject eObject) {
		super.detached(eObject);
		Neo4emfObject neoObject = (Neo4emfObject) eObject;
		addChangeLogDeleteEntry(neoObject);
		Iterator<EObject> it = neoObject.eAllResolvedContents();
		while (it.hasNext()) {
			Neo4emfObject itEObject = (Neo4emfObject) it.next();
			addChangeLogDeleteEntry(itEObject);
		}
	}

	private void addChangeLogCreateEntry(INeo4emfObject neoObject) {
		// if (neoObject.getNodeId() == -1) {
		getChangeLog().add(new NewObject(neoObject));
		// }
	}

	private void addChangeLogDeleteEntry(INeo4emfObject neoObject) {
		// if (neoObject.getNodeId() == -1) {
		getChangeLog().add(new DeleteObject(neoObject));
		// }
	}

	public IChangeLog<Entry> getChangeLog() {

		return changeLog;
	}

	/**
	 * {@link INeo4emfResource#getPersistenceManager()}
	 */
	@Override
	public IPersistenceManager getPersistenceManager() {
		return persistenceManager;
	}
}
