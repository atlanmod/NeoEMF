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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.change.impl.AddLink;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLog;
import fr.inria.atlanmod.neo4emf.change.impl.DeleteObject;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.change.impl.NewObject;
import fr.inria.atlanmod.neo4emf.change.impl.RemoveLink;
import fr.inria.atlanmod.neo4emf.change.impl.SetAttribute;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.logger.Logger;
import fr.inria.atlanmod.neo4emf.resourceUtil.Neo4emfResourceUtil;


public class Serializer implements ISerializer {

	IPersistenceManager manager;
	Map<String, Object> defaultOptions ;
	//INodeBuilder nodeBuilder;
	public Serializer (IPersistenceManager manager){
		this.manager = manager;
		// TOFIX, juste for testing
		ChangeLog.getInstance().setSaver(this);
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
		boolean tmpSave = (boolean)options.get("tmp_save");
		if(!tmpSave) {
			flushTmpNodes(options);
		}
		int counter=0;
		Transaction tx = manager.beginTx();
		try {
			for (Entry e : ChangeLog.getInstance() ){
				serializeEntrySwitch(e,tmpSave);
				counter++;
				if (counter % ((int)options.get(MAX_OPERATIONS_PER_TRANSACTION)) == 0)
				{	
					tx.success();
					tx.finish();
					tx= manager.beginTx();
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			manager.shutdown();
		}finally {
			tx.success();
			tx.finish();
		}	
		ChangeLog.getInstance().clear();
		// the changelog is cleared after an exception is raised
		// TODO look for a way to manage this 
	}
	
	private void flushTmpNodes(Map<String,Object> options) {
		ArrayList<Node> tmpNodes = this.manager.getAllTmpNodes();
		Transaction tx = manager.beginTx();
		int counter = 0;
		try {
			for(Node n : tmpNodes) {
				Node base = manager.getBaseNodeFromTmp(n);
				counter += manager.deleteBaseNode(n,base);
				if (counter % ((int)options.get(MAX_OPERATIONS_PER_TRANSACTION)) == 0)
				{	
					tx.success();
					tx.finish();
					tx= manager.beginTx();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			manager.shutdown();
		}finally {
			tx.success();
			tx.finish();
		}	
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
	private void serializeEntrySwitch(Entry e, boolean tmp) {
		if ( e instanceof NewObject)
			createNewObject(e.geteObject(),tmp);
		if ( e instanceof AddLink )	
			addNewLink(e.geteObject(), ((AddLink) e).geteReference(),((AddLink) e).getNewValue(),tmp);
		if ( e instanceof RemoveLink)
			removeExistingLink(e.geteObject(), ((RemoveLink) e).geteReference(), ((RemoveLink) e).getOldValue(),tmp);
		if ( e instanceof SetAttribute )
			setAttributeValue(e.geteObject(),((SetAttribute) e).geteAttribute(),((SetAttribute) e).getNewValue(),tmp);
		if ( e instanceof DeleteObject)
			deleteExistingObject(e.geteObject(),tmp);

	}

	private void deleteExistingObject(EObject geteObject, boolean tmp) {


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
			EAttribute at, Object newValue, boolean tmp) {
		Node n = null;
		if(tmp) {
			n = this.manager.getTmpNodeByNodeId(eObject);
			if(n == null) {
				n = this.manager.createTmpNodeFromEObject(eObject);
				((Neo4emfObject) eObject).setTmpNodeId(n.getId());
			}
		}
		else {
			try {
			n = manager.getNodeById(eObject);
			} catch(NotFoundException e) {
				// the node has been deleted, try to see if a related tmp node
				// exists.
				n = manager.getTmpNodeByNodeId(eObject);
				if(n != null) {
					// a tmp node has been found, that means the tmp has been
					// flushed and the ecore model needs to be updated
					((INeo4emfObject)eObject).setNodeId(n.getId());
					((INeo4emfObject)eObject).resetTmpNodeId();
				}
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
		
		else if (newValue != null && at.isMany()){
			n.setProperty(at.getName(), ((EList<EObject>) newValue).toArray());}
		
		else if (!at.isMany()){ 
		
			if (at.getEType().getName().equals("Boolean") || at.getEType().getName().equals("EBoolean"))
				n.setProperty(at.getName(), false );
			
			else if (at.getEType().getName().equals("String") || at.getEType().getName().equals("EString"))
				n.setProperty(at.getName(), "");
			
			else 
				n.setProperty(at.getName(), 0);
		}
		else {n.setProperty(at.getName(), new Object[1]);}
	}

	private void removeExistingLink(EObject eObject, EReference eRef, Object object, boolean tmp) {
		Node n = null;
		Node n2 = null;
		if(tmp) {
			n = this.manager.getTmpNodeByNodeId(eObject);
			if(n == null) {
				n = this.manager.createTmpNodeFromEObject(eObject);
				((Neo4emfObject) eObject).setTmpNodeId(n.getId());
			}
			n2 = manager.getTmpNodeByNodeId((EObject)object);
			if(n2 == null) {
				n2 = this.manager.getNodeById((EObject)object);
				((Neo4emfObject) object).setTmpNodeId(n.getId());
			}
		}
		else {
			try {
				n = manager.getNodeById(eObject);
			} catch(NotFoundException e) {
				// the node has been deleted, try to see if a related tmp node
				// exists.
				n = manager.getTmpNodeByNodeId(eObject);
				if(n != null) {
					// a tmp node has been found, that means the tmp has been
					// flushed and the ecore model needs to be updated
					((INeo4emfObject)eObject).setNodeId(n.getId());
					((INeo4emfObject)eObject).resetTmpNodeId();
				}
			}
			try {
				n2 = manager.getNodeById((EObject)object);
				} catch(NotFoundException e) {
					// the node has been deleted, try to see if a related tmp node
					// exists.
					n2 = manager.getTmpNodeByNodeId((EObject)object);
					if(n != null) {
						// a tmp node has been found, that means the tmp has been
						// flushed and the ecore model needs to be updated
						((INeo4emfObject)object).setNodeId(n2.getId());
						((INeo4emfObject)object).resetTmpNodeId();
					}
				}
		}
		RelationshipType rel = manager.getRelTypefromERef(eObject.eClass().getEPackage().getNsURI(),eObject.eClass().getClassifierID(),eRef.getFeatureID());
		Iterator<Relationship> it = n.getRelationships(rel).iterator();
		while (it.hasNext()){
			Relationship relship = it.next();
			if (relship.getEndNode().getId() == n2.getId())
				relship.delete();
		}
	}

	private void addNewLink(EObject eObject, EReference eRef, Object object, boolean tmp) throws NullPointerException{
		Node n = null;
		Node n2 = null;
		if(tmp) {
			n = this.manager.getTmpNodeByNodeId(eObject);
			if(n == null) {
				n = this.manager.createTmpNodeFromEObject(eObject);
				((Neo4emfObject) eObject).setTmpNodeId(n.getId());
			}
			n2 = this.manager.getTmpNodeByNodeId((EObject)object);
			if(n2 == null) {
				n2 = this.manager.getNodeById((EObject)object);
				((Neo4emfObject) object).setTmpNodeId(n.getId());
			}
		}
		else {
			try {
				n = manager.getNodeById(eObject);
			} catch(NotFoundException e) {
				// the node has been deleted, try to see if a related tmp node
				// exists.
				n = manager.getTmpNodeByNodeId(eObject);
				if(n != null) {
					// a tmp node has been found, that means the tmp has been
					// flushed and the ecore model needs to be updated
					((INeo4emfObject)eObject).setNodeId(n.getId());
					((INeo4emfObject)eObject).resetTmpNodeId();
				}
			}
			try {
				n2 = manager.getNodeById((EObject)object);
			} catch(NotFoundException e) {
				// the node has been deleted, try to see if a related tmp node
				// exists.
				n2 = manager.getTmpNodeByNodeId((EObject)object);
				if(n != null) {
					// a tmp node has been found, that means the tmp has been
					// flushed and the ecore model needs to be updated
					((INeo4emfObject)eObject).setNodeId(n2.getId());
					((INeo4emfObject)eObject).resetTmpNodeId();
				}
			}
		}
		if(n == null || n2 == null) {
			Logger.log(IStatus.WARNING, "Dummy objects");
			return;
		}
		RelationshipType rel = this.manager.getRelTypefromERef(eObject.eClass().getEPackage().getNsURI(),eObject.eClass().getClassifierID(),eRef.getFeatureID());
		if (rel == null) {
			rel = DynamicRelationshipType.withName(Neo4emfResourceUtil.formatRelationshipName(eObject.eClass(), eRef));}
		n.createRelationshipTo(n2, rel);
	}

	private void createNewObject(EObject eObject, boolean tmp) {
		Node n = null;
		if(tmp) {
			n = this.manager.createTmpNodeFromEObject(eObject);
			((Neo4emfObject)eObject).setTmpNodeId(n.getId());
		}
		else {
			n = this.manager.createNodefromEObject(eObject);
		}
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
