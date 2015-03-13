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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

public class Serializer implements ISerializer {

	private int maxCount = 50000;
	
	/**
	 * TODO: Comment this
	 */
	PersistenceManager manager;

	/**
	 * TODO: Comment this
	 */
	Map<String, Object> defaultOptions = new HashMap<String, Object>();
	
	private NETransaction currentTx = null;
	private int txCount = 0;

	// INodeBuilder nodeBuilder;
	public Serializer(PersistenceManager manager, int maxTransCount) {
		this.manager = manager;
		this.maxCount = maxTransCount;
		for (int i = 0; i < saveOptions.length; i++)
			defaultOptions.put(saveOptions[i], saveDefaultValues[i]);
	}

	@Override
	/**
	 *  @see {@link INeo4emfResource#save()}
	 */
	public void save(Map<String, Object> options) {
		manager.startNewSession();

		options = mergeOptions(options);
		boolean isTmpSave = (boolean)options.get(TMP_SAVE);
		if(!isTmpSave) {
			flushTmpSave(options);
			System.out.println("flushed");
		}
		List<Entry> changes = manager.getResource().getChangeLog().changes();
		while (!changes.isEmpty()) {
			int times = Math.min(maxCount, changes.size());
			List<Entry> subset = changes.subList(0, times);
			currentTx = manager.createTransaction();
			Entry currentEntry = null;
			boolean error = false;
			try {
				for(Entry each : subset) {
					currentEntry = each;
					each.process(this,isTmpSave);
				}
				currentTx.success();
				subset.clear();
			} catch (Exception e) {
				System.out.println(currentEntry);
				currentTx.abort();
				e.printStackTrace();
				error = true;
			} finally {
				currentTx.commit();
				txCount = 0;
				if(error) return;
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
		List<Relationship> rels = manager.getTmpRelationships();
		while(!rels.isEmpty()) {
			int times = Math.min(maxCount, rels.size());
			List<Relationship> subset = rels.subList(0, times);
			NETransaction tx = manager.createTransaction();
			// TODO give a collection (to handle subset and don't take all the memory by processing the index)
			manager.flushTmpRelationships(subset);
			subset.clear();
			tx.success();
			tx.commit();
		}
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
				txCount += 2;
			}
			else {
				n = manager.getAttributeNodeById(eObject);
			}
		}
		
		if (newValue!= null && !at.isMany()){
		
			if (at.getEType() instanceof EEnum) {
				n.setProperty(at.getName(), newValue.toString());
				txCount++;
			}

			else if (isPrimitive(at.getName())) {
				n.setProperty(at.getName(), newValue);
				txCount++;
			}

			else {
				n.setProperty(at.getName(), newValue.toString());
				txCount++;
			}
		}

		else if (newValue != null && at.isMany()) {
			n.setProperty(at.getName(), ((EList<EObject>) newValue).toArray());
			txCount++;
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
			
			txCount++;
		} else {
			n.setProperty(at.getName(), new Object[1]);
			txCount++;
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

	public void addNewLink(INeo4emfObject eObject, EReference eRef, INeo4emfObject object, boolean isTmp) {
		if(isTmp) {
			this.manager.createAddLinkRelationship(eObject,object,eRef);
		}
		else {
			this.manager.createLink(eObject, (INeo4emfObject)object, eRef);
		}
	}

	public void createNewObject(INeo4emfObject eObject, boolean isTmp) {
		Node n = null;
		if (eObject.getNodeId() == -1) {
			n = this.manager.createNodefromEObject(eObject,isTmp);
			txCount += 2;
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
					txCount++;
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
						int idx = 0;
						Iterator<INeo4emfObject> it = eObjects.iterator();
						while(idx < eObjects.size()) {
							// Loop for all the elements contained in the list
							while(txCount < maxCount && it.hasNext()) {
								INeo4emfObject referencedNeo4emfObject = it.next();
								idx++;
								if (referencedNeo4emfObject.getNodeId() == -1) {// && referencedNeo4emfObject.eResource() != null) {
									Node childNode = this.manager
											.createNodefromEObject(referencedNeo4emfObject,isTmp);
									txCount+=2;
									referencedNeo4emfObject.setNodeId(childNode
											.getId());
								}
//								if(referencedNeo4emfObject.eResource() != null) {
									addNewLink(eObject, ref, referencedNeo4emfObject,isTmp);
									txCount++;
									if(ref.getEOpposite() != null && referencedNeo4emfObject.getSessionId() < eObject.getSessionId()) {
										addNewLink(referencedNeo4emfObject, ref.getEOpposite(), eObject, isTmp);
										txCount++;
									}
//								}
							}
							if(txCount >= maxCount) {
								currentTx.success();
								currentTx.commit();
								currentTx = manager.createTransaction();
								txCount = 0;
							}
						}
					} else {
						INeo4emfObject referencedNeo4emfObject = (INeo4emfObject) eObject
								.eGet(ref);
						if (referencedNeo4emfObject.getNodeId() == -1) { // && referencedNeo4emfObject.eResource() != null) {
							Node childNode = this.manager
									.createNodefromEObject(referencedNeo4emfObject,isTmp);
							txCount += 2;
							referencedNeo4emfObject
									.setNodeId(childNode.getId());
						}
//						if(referencedNeo4emfObject.eResource() != null) {
							addNewLink(eObject, ref, referencedNeo4emfObject,isTmp);
							txCount++;
							if(ref.getEOpposite() != null && referencedNeo4emfObject.getSessionId() < eObject.getSessionId()) {
								addNewLink(referencedNeo4emfObject, ref.getEOpposite(), eObject, isTmp);
								txCount++;
							}
//						}
						if(txCount >= maxCount) {
							currentTx.success();
							currentTx.commit();
							currentTx = manager.createTransaction();
							txCount = 0;
						}
					}
				}
			}
		}
	}
}
