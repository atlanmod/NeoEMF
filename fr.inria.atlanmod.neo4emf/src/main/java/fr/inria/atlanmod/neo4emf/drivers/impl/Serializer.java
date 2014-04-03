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
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
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
	Map<String, Object> defaultOptions = new HashMap<String, Object>();

	// INodeBuilder nodeBuilder;
	public Serializer(PersistenceManager manager) {
		this.manager = manager;
		for (int i = 0; i < saveOptions.length; i++)
			defaultOptions.put(saveOptions[i], saveDefaultValues[i]);
	}

	@Override
	/**
	 *  @see {@link INeo4emfResource#save()}
	 */
	public void save(Map<String, Object> options) {
		/*
		 * The number of database operations within a transaction is bounded,
		 * that's why changes may be processed in several transactions.
		 */
		options = mergeOptions(options);
		int max = (int) options.get(MAX_OPERATIONS_PER_TRANSACTION);
		boolean isTmpSave = (boolean)options.get(TMP_SAVE);
		if(!isTmpSave) {
			flushTmpSave(options);
		}
		List<Entry> changes = manager.getResource().getChangeLog().changes();
		while (!changes.isEmpty()) {
			int times = Math.min(max, changes.size());
			List<Entry> subset = changes.subList(0, times);
			NETransaction tx = manager.createTransaction();
			try {
				for(Entry each : subset) {
					each.process(this,isTmpSave);
				}
				tx.success();
				subset.clear();
			} catch (Exception e) {
				tx.abort();
				e.printStackTrace();
			} finally {
				tx.commit();
			}
		}
	}
	
	// TODO check if it is a good implementation.
	// The previous version was to put all the given options in the defaultOptions
	// Map. This behavior is not compatible with the TMP_SAVE option, which is one time
	// false and one time true, and should be always false by default for convenience (the user
	// never call save for a temporary save).
	private Map<String,Object> mergeOptions(Map<String,Object> options) {
		if(options == null || options.isEmpty()) {
			return defaultOptions;
		}
		Iterator<String> it = defaultOptions.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			if(!options.containsKey(key)) {
				options.put(key, defaultOptions.get(key));
			}
		}
		return options;
	}
	
	private void flushTmpSave(Map<String,Object> options) {
		NETransaction tx = manager.createTransaction();
		manager.flushTmpRelationships();
		tx.success();
		tx.commit();
	}

	public void deleteExistingObject(INeo4emfObject eObject, boolean isTmp) {
		if(isTmp) {
			this.manager.createDeleteRelationship(eObject);
		}
		else {
			this.manager.deleteNodeFromEObject(eObject);
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
	public void setAttributeValue(INeo4emfObject eObject,
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

	public void removeExistingLink(INeo4emfObject eObject, EReference eRef, Object object, boolean isTmp) {
		if(isTmp) {
			this.manager.createRemoveLinkRelationship(eObject, (INeo4emfObject)object,eRef);
		}
		else {
			this.manager.removeLink(eObject, (INeo4emfObject)object, eRef);
		}
	}

	public void addNewLink(INeo4emfObject eObject, EReference eRef, Object object, boolean isTmp) {
		if(isTmp) {
			this.manager.createAddLinkRelationship(eObject,(INeo4emfObject)object,eRef);
		}
		else {
			this.manager.createLink(eObject, (INeo4emfObject)object, eRef);
		}
	}

	public void createNewObject(INeo4emfObject eObject, boolean isTmp) {
		Node n = null;
		if (eObject.getNodeId() == -1) {
			n = this.manager.createNodefromEObject(eObject,isTmp);
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
				} catch (ClassCastException e) {
				}
				;
				if (isSet) {
					if (ref.getUpperBound() == -1) {
						List<INeo4emfObject> eObjects = (List<INeo4emfObject>) eObject
								.eGet(ref);
						for (INeo4emfObject referencedEObject : eObjects) {
							INeo4emfObject referencedNeo4emfObject = (INeo4emfObject) referencedEObject;
							if (referencedNeo4emfObject.getNodeId() == -1 && referencedNeo4emfObject.eResource() != null) {
								Node childNode = this.manager
										.createNodefromEObject(referencedEObject,isTmp);
								referencedNeo4emfObject.setNodeId(childNode
										.getId());
							}
							if(referencedNeo4emfObject.eResource() != null) {
								addNewLink(eObject, ref, referencedEObject,isTmp);
							}
						}
					} else {
						Neo4emfObject referencedNeo4emfObject = (Neo4emfObject) eObject
								.eGet(ref);
						if (referencedNeo4emfObject.getNodeId() == -1 && referencedNeo4emfObject.eResource() != null) {
							Node childNode = this.manager
									.createNodefromEObject(referencedNeo4emfObject);
							referencedNeo4emfObject
									.setNodeId(childNode.getId());
						}
						if(referencedNeo4emfObject.eResource() != null) {
							addNewLink(eObject, ref, referencedNeo4emfObject,isTmp);
						}
					}
				}
			}
		}
//		manager.putNodeId(eObject, n.getId());
	}
}
