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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.impl.AddLink;
import fr.inria.atlanmod.neo4emf.change.impl.DeleteObject;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.change.impl.NewObject;
import fr.inria.atlanmod.neo4emf.change.impl.RemoveLink;
import fr.inria.atlanmod.neo4emf.change.impl.SetAttribute;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.logger.Logger;
import fr.inria.atlanmod.neo4emf.resourceUtil.Neo4emfResourceUtil;

public class Serializer implements ISerializer {
	
	/**
	 * TODO: Comment this
	 */
	PersistenceManager manager;
	
	/**
	 * TODO: Comment this
	 */
	Map<String, Object> defaultOptions;


	//INodeBuilder nodeBuilder;
	public Serializer (PersistenceManager manager){
		this.manager = manager;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	/**
	 *  @see {@link INeo4emfResource#save()}
	 */
	public void save(Map<String, Object> options) {
		if (options == null)
			options = new HashMap();
		options= mergeWithDefaultOptions(options);
		boolean isTmpSave = (boolean)options.get("tmp_save");
		if(!isTmpSave) {
			flushTmpSave(options);
		}
		int counter=0;
		Transaction tx = manager.beginTx();
		IChangeLog<Entry> changeLog = manager.getResource().getChangeLog();
		try {
			// TODO: Fix with a more efficient implementation
			// First execute only for create objects
			Iterator<Entry> it = changeLog.iterator();
			while (it.hasNext()) {
				Entry e = it.next();
				if (e instanceof NewObject) {
					serializeEntrySwitch(e,isTmpSave);
					counter++;
					if (counter % ((int)options.get(MAX_OPERATIONS_PER_TRANSACTION)) == 0)
					{	
						tx.success();
						tx.finish();
						tx= manager.beginTx();
					}
				}
			}
			// other updates...
			it = changeLog.iterator();
			while (it.hasNext()) {
				Entry e = it.next();
				if (!(e instanceof NewObject)) {
					serializeEntrySwitch(e,isTmpSave);
					counter++;
					if (counter % ((int)options.get(MAX_OPERATIONS_PER_TRANSACTION)) == 0)
					{	
						tx.success();
						tx.finish();
						tx= manager.beginTx();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			manager.shutdown();
		} finally {
			tx.success();
			tx.finish();
		}	
		changeLog.clear();
		// the changelog is cleared after an exception is raised
		// TODO look for a way to manage this
	}
	
	private void flushTmpSave(Map<String,Object> options) {
		List<Relationship> tmpRelationships = manager.getTmpRelationships();
		Iterator<Relationship> it = tmpRelationships.iterator();
		int counter = 0;
		Transaction tx = manager.beginTx();
		while(it.hasNext()) {
			Relationship rel = it.next();
			manager.processTemporaryRelationship(rel);
			counter++;
			if (counter % ((int)options.get(MAX_OPERATIONS_PER_TRANSACTION)) == 0)
			{	
				tx.success();
				tx.finish();
				tx= manager.beginTx();
			}
		}
		tx.success();
		tx.finish();
	}
	
	/**
	 * init the default options of
	 * 
	 * @return {@link Map} default Options
	 */
	private void initOptions() {
		defaultOptions = new HashMap<String, Object>();
		for (int i = 0; i < saveOptions.length; i++)
			defaultOptions.put(saveOptions[i], saveDefaultValues[i]);

	}

	/**
	 * merges the options in the save method's parameters with the default
	 * options
	 * 
	 * @param options
	 * @return {@link Map} merged options
	 */
	private Map<String, Object> mergeWithDefaultOptions(
			Map<String, Object> options) {
		initOptions();
		for (int i = 0; i < saveOptions.length; i++)
			if (options.containsKey(saveOptions[i])) {
				defaultOptions.remove(saveOptions[i]);
				defaultOptions.put(saveOptions[i], options.get(saveOptions[i]));
			}
		return defaultOptions;
	}

	/**
	 * casts the ChangeLog entry to the appropriate atomic persistence action
	 * within transactions
	 * 
	 * @param e
	 *            {@link Entry}
	 */
	private void serializeEntrySwitch(Entry e, boolean isTmp) {
		if ( e instanceof NewObject)
			createNewObject(e.geteObject(), isTmp);
		else if ( e instanceof AddLink )	
			addNewLink(e.geteObject(), ((AddLink) e).geteReference(),((AddLink) e).getNewValue(), isTmp);
		else if ( e instanceof RemoveLink)
			removeExistingLink(e.geteObject(), ((RemoveLink) e).geteReference(), ((RemoveLink) e).getOldValue(), isTmp);
		else if ( e instanceof SetAttribute )
			setAttributeValue(e.geteObject(),((SetAttribute) e).geteAttribute(),((SetAttribute) e).getNewValue(), isTmp);
		else if ( e instanceof DeleteObject)
			deleteExistingObject(e.geteObject(),isTmp);

	}

	private void deleteExistingObject(EObject geteObject, boolean isTmp) {
		if(isTmp) {
			Node n = this.manager.getNodeById(geteObject);
			this.manager.createDeleteRelationship(n);
		}
		else {
			this.manager.deleteNodeFromEObject(geteObject);
		}
	}
	
	private boolean isPrimitive(String str){
		if (str.equals("Boolean") 
				|| str.equals("Integer") 
				|| str.equals("Short") 
				|| str.equals("Long") 
				|| str.equals("Float")
				|| str.equals("String") 
				|| str.equals("Double")
				|| str.equals("Byte")
				)
			return false;
		return true;
		// TODO debug this instruction
	}

	@SuppressWarnings("unchecked")
	private void setAttributeValue(EObject eObject,
			EAttribute at, Object newValue, boolean isTmp) {
		Node n = manager.getNodeById(eObject);
		if(isTmp) {
			if(((Neo4emfObject)eObject).getAttributeNodeId() < 0) {
				n = manager.createAttributeNodeForEObject(eObject);
			}
			else {
				n = manager.getAttributeNodeById(eObject);
			}
		}
		
		if (newValue!= null && !at.isMany()){
		
			if (at.getEType() instanceof EEnum)
				n.setProperty(at.getName(), newValue.toString());

			else if (isPrimitive(at.getName()))
				n.setProperty(at.getName(), newValue);

			else
				n.setProperty(at.getName(), newValue.toString());
		}

		else if (newValue != null && at.isMany()) {
			n.setProperty(at.getName(), ((EList<EObject>) newValue).toArray());
		}

		else if (!at.isMany()) {

			if (at.getEType().getName().equals("Boolean")
					|| at.getEType().getName().equals("EBoolean"))
				n.setProperty(at.getName(), false);

			else if (at.getEType().getName().equals("String")
					|| at.getEType().getName().equals("EString"))
				n.setProperty(at.getName(), "");

			else
				n.setProperty(at.getName(), 0);
		} else {
			n.setProperty(at.getName(), new Object[1]);
		}
	}

	private void removeExistingLink(EObject eObject, EReference eRef, Object object, boolean isTmp) {
		Node n = manager.getNodeById(eObject);
		Node n2 = manager.getNodeById((EObject)object);
		RelationshipType rel = manager.getRelTypefromERef(eObject.eClass().getEPackage().getNsURI(),eObject.eClass().getClassifierID(),eRef.getFeatureID());
		if(isTmp) {
			this.manager.createRemoveLinkRelationship(n,n2,rel);
		}
		else {
			Iterator<Relationship> it = n.getRelationships(rel).iterator();
			while (it.hasNext()){
				Relationship relship = it.next();
				if (relship.getEndNode().getId() == n2.getId())
					relship.delete();
			}
		}
	}

	private void addNewLink(EObject eObject, EReference eRef, Object object, boolean isTmp) throws NullPointerException{
		Node n = this.manager.getNodeById(eObject);
		Node n2 = this.manager.getNodeById((EObject) object);
		if (n == null || n2 == null) {
			Logger.log(IStatus.WARNING, "Dummy objects");
			return;
		}
		RelationshipType rel = this.manager.getRelTypefromERef(eObject.eClass()
				.getEPackage().getNsURI(), eObject.eClass().getClassifierID(),
				eRef.getFeatureID());
		if (rel == null) {
			rel = DynamicRelationshipType.withName(Neo4emfResourceUtil.formatRelationshipName(eObject.eClass(), eRef));}
		if(isTmp) {
			this.manager.createAddLinkRelationship(n,n2,rel);
		}
		else {
			n.createRelationshipTo(n2, rel);
		}
	}

	private void createNewObject(EObject eObject,boolean isTmp) {
		Node n = null;
		if (((INeo4emfObject)eObject).getNodeId() == -1) {
			n = this.manager.createNodefromEObject(eObject,isTmp);
			((Neo4emfObject) eObject).setNodeId(n.getId());
		} else {
			n = this.manager.getNodeById(eObject);
		}
		{
			EList<EAttribute> atrList = eObject.eClass().getEAllAttributes();
			Iterator<EAttribute> itAtt = atrList.iterator();
			while (itAtt.hasNext()) {
				EAttribute at = itAtt.next();
				if (eObject.eIsSet(at)) {
					setAttributeValue(eObject, at, eObject.eGet(at),false); // false ?
				} else {
					n.setProperty(at.getName(), "");
				}
			}
		}
		{
			EList<EReference> refList = eObject.eClass().getEAllReferences();
			Iterator<EReference> itRef = refList.iterator();
			while (itRef.hasNext()) {
				EReference ref = itRef.next();
				boolean isSet = false;
				try {
					isSet = eObject.eIsSet(ref);
				} catch (ClassCastException e) {};
				if (isSet) {
					if (ref.getUpperBound() == -1) {
						List<EObject> eObjects = (List<EObject>) eObject.eGet(ref);
						for (EObject referencedEObject : eObjects) {
							Neo4emfObject referencedNeo4emfObject = (Neo4emfObject) referencedEObject;
							if (referencedNeo4emfObject.getNodeId() == -1) {
								Node childNode = this.manager.createNodefromEObject(referencedEObject);
								referencedNeo4emfObject.setNodeId(childNode.getId());
							}
							addNewLink(eObject, ref, referencedEObject,isTmp);
						}
					} else {
						Neo4emfObject referencedNeo4emfObject = (Neo4emfObject) eObject.eGet(ref);
						if (referencedNeo4emfObject.getNodeId() == -1) {
							Node childNode = this.manager.createNodefromEObject(referencedNeo4emfObject);
							referencedNeo4emfObject.setNodeId(childNode.getId());
						}
						addNewLink(eObject, ref, referencedNeo4emfObject,isTmp);
					}
				}
			}
		}
		manager.putNodeId(eObject, n.getId());
		// TODO set the node id in the eObject
	}
}
