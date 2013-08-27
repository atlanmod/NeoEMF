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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

import fr.inria.atlanmod.neo4emf.change.impl.*;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.util.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.util.ISerializer;


public class Serializer implements ISerializer {

	IPersistenceManager manager;
	Map<String, Object> defaultOptions ;
	//INodeBuilder nodeBuilder;
	public Serializer (IPersistenceManager manager){
		this.manager = manager;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	/**
	 *  @see {@link INeo4emfResource#save()}
	 */
	public void save(Map<String,Object> options) {
		if (options == null)
			options = new HashMap();
		options= mergeWithDefaultOptions(options);

		int counter=0;

		Transaction tx = manager.beginTx();
		try {
			for (Entry e : ChangeLog.getInstance() ){
				serializeEntrySwitch(e);
				counter++;
				if (counter%((int)options.get(MAX_OPERATIONS_PER_TRANSACTION))==0)
				{	
					tx.success();
					tx.finish();
					tx= manager.beginTx();
				}
			}
			
		} catch(Exception e) {manager.shutdown();
		}finally {
			tx.success();
			tx.finish();
		}	
		ChangeLog.getInstance().clear();	
	}
	/**
	 * init the default options of 
	 * @return  {@link Map} default Options 
	 */
	private void initOptions() {
		defaultOptions =  new HashMap<String, Object>();
		for (int i=0;i<saveOptions.length; i++ )
			defaultOptions.put(saveOptions[i], saveDefaultValues[i]);

	}

	/**
	 * merges the options in the save method's parameters with 
	 * the default options 
	 * @param options
	 * @return {@link Map} merged options
	 */
	private Map<String, Object> mergeWithDefaultOptions(Map<String, Object> options) {
		initOptions();
		for (int i=0; i< saveOptions.length; i++)
			if (options.containsKey(saveOptions[i])){
				defaultOptions.remove(saveOptions[i]);
				defaultOptions.put(saveOptions[i], options.get(saveOptions[i]));	}
		return defaultOptions;
	}

	/**
	 * casts the ChangeLog entry to the appropriate atomic persistence 
	 * action within transactions  
	 * @param e {@link Entry}
	 */
	private void serializeEntrySwitch(Entry e) {



		if ( e instanceof NewObject)
			createNewObject(e.geteObject());
		if ( e instanceof AddLink )	
			addNewLink(e.geteObject(), ((AddLink) e).geteReference(),((AddLink) e).getNewValue());
		if ( e instanceof RemoveLink)
			removeExistingLink(e.geteObject(), ((RemoveLink) e).geteReference(), ((RemoveLink) e).getOldValue());
		if ( e instanceof SetAttribute )
			setAttributeValue(e.geteObject(),((SetAttribute) e).geteAttribute(),((SetAttribute) e).getNewValue());
		if ( e instanceof DeleteObject)
			deleteExistingObject(e.geteObject());

	}

	private void deleteExistingObject(EObject geteObject) {


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
			EAttribute at, Object newValue) {
		Node n = manager.getNodeById(eObject);
		if (newValue!= null && !at.isMany()){
			if (at.getEType() instanceof EEnum)
				n.setProperty(at.getName(), newValue.toString());
			else if (isPrimitive(at.getName()))
				n.setProperty(at.getName(), newValue);
			else 
				n.setProperty(at.getName(), newValue.toString());
		}			
		else if (newValue != null && at.isMany()){
			n.setProperty(at.getName(), ((EList<EObject>) newValue).toArray());}
		else if (!at.isMany()){ 
			if (at.getEType().getName().equals("Boolean"))
				n.setProperty(at.getName(), false );
			else if (at.getEType().getName().equals("String"))
				n.setProperty(at.getName(), "");
			else 
				n.setProperty(at.getName(), 0);
		}
		else {n.setProperty(at.getName(), new Object[1]);}
	}

	private void removeExistingLink(EObject eObject, EReference eRef, Object object) {
		Node n = manager.getNodeById(eObject);
		Node n2 = manager.getNodeById((EObject)object);
		RelationshipType rel = manager.getRelTypefromERef(eObject.eClass().getEPackage().getNsURI(),eObject.eClass().getClassifierID(),eRef.getFeatureID());
		Iterator<Relationship> it = n.getRelationships(rel).iterator();
		while (it.hasNext()){
			Relationship relship = it.next();
			if (relship.getEndNode().getId() == n2.getId())
				relship.delete();
		}
	}

	private void addNewLink(EObject eObject, EReference eRef, Object object) {
		Node n = manager.getNodeById(eObject);
		Node n2 = manager.getNodeById((EObject)object);
		RelationshipType rel = manager.getRelTypefromERef(eObject.eClass().getEPackage().getNsURI(),eObject.eClass().getClassifierID(),eRef.getFeatureID());
		n.createRelationshipTo(n2, rel);

	}

	private void createNewObject(EObject eObject) {
		Node n = manager.createNodefromEObject(eObject);
		((Neo4emfObject)eObject).setNodeId(n.getId());
		EList<EAttribute> atrList= eObject.eClass().getEAllAttributes();
		Iterator<EAttribute> it = atrList.iterator();
		while(it.hasNext()){
			EAttribute at =it.next();
			n.setProperty(at.getName(), "");}
		manager.putNodeId(eObject,n.getId());
		// TODO set the node id in the eObject 

	}
}
