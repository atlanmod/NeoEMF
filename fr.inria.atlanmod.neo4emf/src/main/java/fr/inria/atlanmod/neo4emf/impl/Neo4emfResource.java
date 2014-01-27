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

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.neo4j.graphdb.RelationshipType;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.Point;
import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.change.impl.NewObject;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.impl.PersistenceManager;
import fr.inria.atlanmod.neo4emf.resourceUtil.ChangeAdapterImpl;


public   class Neo4emfResource extends ResourceImpl implements INeo4emfResource {

	private IChangeLog<Entry> changeLog;
	
	private Adapter changeAdapter = new ChangeAdapterImpl();
	
	/** 
	 * The persistence manager holds the communication between the resource and 
	 * the different persistence units 
	 * @see #IPersistenceManager 
	 */

	private IPersistenceManager persistenceManager;
	/**
	 * storeDirectory represents the location of the store Database
	 * @see IPersistenceService	
	 */
	private String storeDirectory;
	/**
	 * Neo4emfResource Constructor  	
	 * @param storeDirectory
	 * @param relationship map
	 */
	public Neo4emfResource(final String storeDirectory, final Map<String,Map<Point,RelationshipType>> map,Map<String, String> params ) {
		super();
		this.storeDirectory = storeDirectory;
		if (params == null) {
			this.persistenceManager = new PersistenceManager(this, storeDirectory, map);
		} else {
			this.persistenceManager = new PersistenceManager(this, storeDirectory, map, params);
		}
		this.changeLog = IChangeLogFactory.eINSTANCE.createChangeLog();
	}
	public Neo4emfResource(final URI uri, Map<String,Map<Point,RelationshipType>> map,  Map<String, String> params){
		this(neo4emfURItoString(uri), map, params);
		setURI(uri);
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
	public void save (Map<?, ?> options){
		this.persistenceManager.save(options);
	}
	/**
	 * shuting down the backend
	 */
	@Override
	public void shutdown() {
		this.persistenceManager.shutdown();

	}
	/**
	 * load the roots elements of the model 
	 * @param options {@link Map}
	 */
	@Override 
	public void load (Map <?,?> options){
		this.persistenceManager.load(options);
	}
	/**
	 * {@link INeo4emfResource#notifyGet(EObject, EStructuralFeature)}
	 */
	@Override
	public void notifyGet(EObject eObject, EStructuralFeature feature) {
		try {
			Assert.isNotNull(eObject, "eObject should not be null ");
		this.persistenceManager.updateProxyManager((INeo4emfObject)eObject, feature);}
		catch (Exception e){
			this.persistenceManager.shutdown();
			e.printStackTrace();
		}

	}
	@Override
	public void unload(int PID){
		this.persistenceManager.unloadPartition(PID);
	}
	@Override
	public EList<INeo4emfObject> getAllInstances(EClass eClass) {
		EList<INeo4emfObject> result =  this.persistenceManager.getAllInstancesOfType(eClass);
		getContents().addAll(result);
		return result;
	}
	@Override
	public EList<INeo4emfObject> getAllInstances(int eClassID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public EObject getContainerOnDemand(EObject eObject, int featureId){
		// TODO Auto-generated method stub
		return this.persistenceManager.getContainerOnDemand(eObject,featureId);

	}
	@Override
	public void setRelationshipsMap(Map<String,Map<Point,RelationshipType>> map) {
		this.persistenceManager.setRelationshipsMap(map);

	}
	private static String neo4emfURItoString (URI uri){ 
		Assert.isTrue(uri.scheme().equals("neo4emf"), "protocol should be neo4emf !!");
		StringBuffer buff = new StringBuffer();
		if (uri.hasDevice())
			buff.append(uri.device()).append("/");
		for (int i = 0; uri.segmentCount() > 0 && i < uri.segmentCount(); i++)
			buff.append(uri.segment(i)).append("/");
		return buff.toString();


	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#attached(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void attached(EObject eObject) {
		super.attached(eObject);
		eObject.eAdapters().add(changeAdapter);
		addChangeLogEntries(eObject);
		Iterator<EObject> it = eObject.eAllContents();
		while (it.hasNext()) {
			EObject itEObject = (EObject) it.next();
			itEObject.eAdapters().add(changeAdapter);
			addChangeLogEntries(itEObject);
		}
	}
	private void addChangeLogEntries(EObject eObject) {
		if (eObject instanceof INeo4emfObject) {
			if (((INeo4emfObject)eObject).getNodeId() == -1) {
				getChangeLog().add(new NewObject(eObject));
			}
		}
	}
	
	@Override
	public void detached(EObject eObject) {
		super.detached(eObject);
		eObject.eAdapters().remove(changeAdapter);
		Iterator<EObject> it = eObject.eAllContents();
		while (it.hasNext()) {
			EObject itEObject = (EObject) it.next();
			itEObject.eAdapters().remove(changeAdapter);
		}
	}

	public IChangeLog<Entry> getChangeLog() {
		return changeLog;
	}
}
