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

	private boolean isPrimitive(String str) {
		if (str.equals("Boolean") || str.equals("Integer")
				|| str.equals("Short") || str.equals("Long")
				|| str.equals("Float") || str.equals("String")
				|| str.equals("Double") || str.equals("Byte"))
			return false;
		return true;
		// TODO debug this instruction
	}

	@SuppressWarnings("unchecked")
	public void setAttributeValue(EObject eObject, EAttribute at,
			Object newValue) {
		Node n = manager.getNodeById(eObject);

		if (newValue != null && !at.isMany()) {

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

	public void removeExistingLink(EObject eObject, EReference eRef,
			Object object) {
		Node n = manager.getNodeById(eObject);
		Node n2 = manager.getNodeById((EObject) object);
		RelationshipType rel = manager.getRelTypefromERef(eObject.eClass()
				.getEPackage().getNsURI(), eObject.eClass().getClassifierID(),
				eRef.getFeatureID());
		Iterator<Relationship> it = n.getRelationships(rel).iterator();
		while (it.hasNext()) {
			Relationship relship = it.next();
			if (relship.getEndNode().getId() == n2.getId())
				relship.delete();
		}
	}

	public void addNewLink(EObject eObject, EReference eRef, Object object)
			throws NullPointerException {
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
			rel = DynamicRelationshipType.withName(Neo4emfResourceUtil
					.formatRelationshipName(eObject.eClass(), eRef));
		}
		n.createRelationshipTo(n2, rel);
	}

	public void createNewObject(INeo4emfObject eObject) {
		Node n = null;
		if (((INeo4emfObject) eObject).getNodeId() == -1) {
			n = this.manager.createNodefromEObject(eObject);
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
					setAttributeValue(eObject, at, eObject.eGet(at));
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
							if (referencedNeo4emfObject.getNodeId() == -1) {
								Node childNode = this.manager
										.createNodefromEObject(referencedEObject);
								referencedNeo4emfObject.setNodeId(childNode
										.getId());
							}
							addNewLink(eObject, ref, referencedEObject);
						}
					} else {
						Neo4emfObject referencedNeo4emfObject = (Neo4emfObject) eObject
								.eGet(ref);
						if (referencedNeo4emfObject.getNodeId() == -1) {
							Node childNode = this.manager
									.createNodefromEObject(referencedNeo4emfObject);
							referencedNeo4emfObject
									.setNodeId(childNode.getId());
						}
						addNewLink(eObject, ref, referencedNeo4emfObject);
					}
				}
			}
		}
		manager.putNodeId(eObject, n.getId());
		// TODO set the node id in the eObject
	}
}
