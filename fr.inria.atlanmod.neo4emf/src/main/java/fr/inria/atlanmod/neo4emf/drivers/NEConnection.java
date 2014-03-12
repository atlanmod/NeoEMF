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
 * @author Sunye
 */
package fr.inria.atlanmod.neo4emf.drivers;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.NETransaction;
import fr.inria.atlanmod.neo4emf.drivers.impl.Neo4JTransaction;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

/**
 * 
 * @author sunye
 * 
 */
public class NEConnection {

	/**
	 * Current state of the session
	 */
	private ConnectionState state = ConnectionState.CLOSED;

	/**
	 * The Neo4j database service.
	 */
	private final GraphDatabaseService service;

	/**
	 * Registered EPackage for this session.
	 */
	private final EPackage epackage;

	/**
	 * Index for EClass nodes.
	 */
	private Index<Node> metaIndex;
	
	private Index<Relationship> relationshipIndex;

	/**
	 * Node representing the resource.
	 */
	private Node resourceNode;

	/**
	 * Nodes representing the EClasses for the EPackage.
	 */
	private Node[] eclasses;

	public NEConnection(GraphDatabaseService gdb, NEConfiguration configuration) {
		assert gdb != null : "Null Database Service";
		assert configuration != null : "Null Configuration";

		service = gdb;
		epackage = configuration.ePackage();
	}

	/**
	 * Opens a connection with the DBMS.
	 */
	public void open() {
		assert state == ConnectionState.CLOSED : "Connection already open";
		
		state = ConnectionState.OPEN;
		Transaction tx = service.beginTx();
		try {
			this.initializeIndexes();
			this.initializeResource();
			this.initializePackage();
			tx.success();
		} catch (Exception e) {
			tx.failure();
		} finally {
			tx.finish();
		}

	}

	/**
	 * Closes the connection with the DBMS
	 */
	public void close() {
		assert state == ConnectionState.OPEN : "Connection already closed";

		resourceNode = null;
		eclasses = null;
		metaIndex = null;
		state = ConnectionState.CLOSED;
		service.shutdown();
	}

	/**
	 * Creates and starts a transaction. All database operations must be
	 * performed within a transaction.
	 * 
	 * @return
	 */
	public NETransaction createTransaction() {
		assert state == ConnectionState.OPEN : "Connection closed";

		return new Neo4JTransaction(service.beginTx());
	}
	
	/**
	 * Creates a database node from a PersistentObject and adds a link
	 * "INSTANCE OF" between the new node and the object's EClass node.
	 * 
	 * @param obj
	 */
	public Node addObject(INeo4emfObject obj, boolean isTmp) {
		int typeID = obj.eClass().getClassifierID(); 
		assert typeID < eclasses.length;
		
		Node newNode = service.createNode();
		obj.setNodeId(newNode.getId());
//		newNode.createRelationshipTo(eclasses[typeID], IPersistenceService.INSTANCE_OF);
		// [Unload add]
		Relationship instanceOfRel = newNode.createRelationshipTo(eclasses[typeID], IPersistenceService.INSTANCE_OF);
		Relationship isRootRel = null;
		// [Unload add]
		if (obj.eContainer() == null && obj.eResource() != null) {
			// ROOT Object
//			resourceNode.createRelationshipTo(newNode, IPersistenceService.IS_ROOT);
			// [Unload add]
			isRootRel = resourceNode.createRelationshipTo(newNode, IPersistenceService.IS_ROOT);
			// /[Unload add]
		}
		
		// [Unload add]
		if (isTmp) {
			metaIndex.add(newNode, IPersistenceService.ID_META,
					IPersistenceService.TMP_NODE);
			relationshipIndex.add(instanceOfRel, IPersistenceService.ID_META,
					IPersistenceService.TMP_RELATIONSHIP);
			if (isRootRel != null) {
				relationshipIndex.add(isRootRel, IPersistenceService.ID_META,
						IPersistenceService.TMP_RELATIONSHIP);
			}
		}
		// /[Unload add]
		
		return newNode;
	}
	
	public Node addAttribute(INeo4emfObject obj) {
		Node base = service.getNodeById(obj.getNodeId());
		Node attributeNode = service.createNode();
		obj.setAttributeNodeId(attributeNode.getId());
		Relationship setAttributeRel = base.createRelationshipTo(attributeNode,
				IPersistenceService.SET_ATTRIBUTE);
		metaIndex.add(attributeNode, IPersistenceService.ID_META,
				IPersistenceService.TMP_NODE);
		relationshipIndex.add(setAttributeRel, IPersistenceService.ID_META,
				IPersistenceService.TMP_RELATIONSHIP);
		return attributeNode;
	}
	
	public void removeObject(INeo4emfObject obj) {
		Node objectNode = service.getNodeById(obj.getNodeId());
		Iterator<Relationship> it = objectNode.getRelationships().iterator();
		while(it.hasNext()) {
			it.next().delete();
		}
		objectNode.delete();
	}
	
	public void createRelationship(INeo4emfObject from, INeo4emfObject to, RelationshipType relType) {
		Node fromNode = service.getNodeById(from.getNodeId());
		Node toNode = service.getNodeById(to.getNodeId());
		fromNode.createRelationshipTo(toNode, relType);
	}
	
	public Relationship addTmpRelationshipBetween(INeo4emfObject from, INeo4emfObject to, RelationshipType relType) {
		Node fromNode = service.getNodeById(from.getNodeId());
		Node toNode = service.getNodeById(to.getNodeId());
		/*
		 * Remove the DELETE relationship that may have been generated. (This
		 * happens when the EObject has been removed from its container).
		 */
		Iterator<Relationship> it = fromNode.getRelationships(
				IPersistenceService.DELETE).iterator();
		while (it.hasNext()) {
			it.next().delete();
		}
		/*
		 * If there is a REMOVE_LINK relationship with the same base
		 * RelationshipType removes it. In that case it is not necessary to
		 * create a ADD_LINK relationship because the base graph contains the
		 * base RelationshipType.
		 */
		it = fromNode.getRelationships(IPersistenceService.REMOVE_LINK)
				.iterator();
		while (it.hasNext()) {
			Relationship rel = it.next();
			if (rel.getProperty("gen_rel").equals(relType.name())
					&& rel.getOtherNode(fromNode).getId() == toNode.getId()) {
				rel.delete();
				return null;
			}
		}
		Relationship addLinkRel = fromNode.createRelationshipTo(toNode,
				IPersistenceService.ADD_LINK);
		addLinkRel.setProperty("gen_rel", relType.name());
		relationshipIndex.add(addLinkRel, IPersistenceService.ID_META,
				IPersistenceService.TMP_RELATIONSHIP);
		return addLinkRel;
	}
	
	public void removeRelationship(INeo4emfObject from, INeo4emfObject to, RelationshipType relType) {
		Node fromNode = service.getNodeById(from.getNodeId());
		Node toNode = service.getNodeById(to.getNodeId());
		Iterator<Relationship> it = fromNode.getRelationships(relType).iterator();
		while(it.hasNext()) {
			Relationship rel = it.next();
			// TODO check if the toNode is needed ?
			if(rel.getEndNode().getId() == toNode.getId()) {
				rel.delete();
				// TODO add a return ?
			}
		}
	}
	
	public Relationship removeTmpRelationshipBetween(INeo4emfObject from,
			INeo4emfObject to, RelationshipType relType) {
		Node fromNode = service.getNodeById(from.getNodeId());
		Node toNode = service.getNodeById(to.getNodeId());
		/*
		 * If there is a ADD_LINK relationship with the same base
		 * RelationshipType removes it. In that case it is not necessary to
		 * create a REMOVE_LINK relationship because the base graph doesn't have
		 * a RelationshipType relationship between from and to.
		 */
		System.out.println("try to remove " + relType.name() + " ADD_LINK");
		System.out.println("\tBetween " + fromNode.getId() + " and " + toNode.getId());
		Iterator<Relationship> it = fromNode.getRelationships(
				IPersistenceService.ADD_LINK).iterator();
		while (it.hasNext()) {
			Relationship rel = it.next();
			System.out.println(rel.getProperty("gen_rel"));
			System.out.println(fromNode.getId() + " -> " + rel.getOtherNode(fromNode).getId());
			if(rel.getProperty("gen_rel").equals(relType.name())
					&& rel.getOtherNode(fromNode).getId() == toNode.getId()) {
				System.out.println("found lol");
				rel.delete();
				return null;
			}
		}
		Relationship removeLinkRel = fromNode.createRelationshipTo(toNode,
				IPersistenceService.REMOVE_LINK);
		removeLinkRel.setProperty("gen_rel", relType.name());
		relationshipIndex.add(removeLinkRel, IPersistenceService.ID_META,
				IPersistenceService.TMP_RELATIONSHIP);
		return removeLinkRel;
	}
	
	public Relationship addDeleteRelationship(INeo4emfObject obj) {
		Node objectNode = service.getNodeById(obj.getNodeId());
		Relationship deleteRel = objectNode.createRelationshipTo(objectNode, IPersistenceService.DELETE);
		relationshipIndex.add(deleteRel, IPersistenceService.ID_META, IPersistenceService.TMP_RELATIONSHIP);
		return deleteRel;
	}
	
	public void cleanIndexes() {
		Iterator<Relationship> relIt = relationshipIndex.get(IPersistenceService.ID_META, IPersistenceService.TMP_RELATIONSHIP).iterator();
		while(relIt.hasNext()) {
			relIt.next().delete();
		}
		Iterator<Node> nodesIt = metaIndex.get(IPersistenceService.ID_META, IPersistenceService.TMP_NODE).iterator();
		while(nodesIt.hasNext()) {
			nodesIt.next().delete();
		}
	}
	
	public void flushTmpRelationships() {
		Iterator<Relationship> it = relationshipIndex.get(IPersistenceService.ID_META, IPersistenceService.TMP_RELATIONSHIP).iterator();
		while(it.hasNext()) {
			Relationship r = it.next();
			if(r.getType().equals(IPersistenceService.SET_ATTRIBUTE)) {
				Node baseNode = r.getStartNode();
				Node attributeNode = r.getEndNode();
				Iterator<String> updatedProperties = attributeNode.getPropertyKeys().iterator();
				while(updatedProperties.hasNext()) {
					String propKey = updatedProperties.next();
					baseNode.setProperty(propKey, attributeNode.getProperty(propKey));
				}
				r.delete();
				attributeNode.delete();
			}
			else if(r.getType().equals(IPersistenceService.ADD_LINK)) {
				String baseRelationName = (String) r.getProperty("gen_rel");
				Node from = r.getStartNode();
				Node to = r.getEndNode();
				// also fix that
				from.createRelationshipTo(to, DynamicRelationshipType.withName(baseRelationName));
				r.delete();
				metaIndex.remove(from, IPersistenceService.ID_META);
				metaIndex.remove(to, IPersistenceService.ID_META);
				// fix that, ugly
				Iterator<Relationship> fromIO = from.getRelationships(IPersistenceService.INSTANCE_OF).iterator();
				Iterator<Relationship> toIO = to.getRelationships(IPersistenceService.INSTANCE_OF).iterator();
				while(fromIO.hasNext()) {
					relationshipIndex.remove(fromIO.next(),IPersistenceService.ID_META);
				}
				while(toIO.hasNext()) {
					relationshipIndex.remove(toIO.next(),IPersistenceService.ID_META);
				}
			}
			else if(r.getType().equals(IPersistenceService.REMOVE_LINK)) {
				/*
				 * Find a more efficient implementation (maybe with RelationShipType directly
				 * as a relationship property
				 */
				String baseRelationName = (String) r.getProperty("gen_rel");
				Node from = r.getStartNode();
				Node to = r.getEndNode();
				Iterator<Relationship> relationships = from.getRelationships().iterator();
				while(relationships.hasNext()) {
					Relationship rel = relationships.next();
					if(rel.getType().toString().equals(baseRelationName) &&
							rel.getEndNode().equals(to)) {
						rel.delete();
						break;
					}
				}
				r.delete();
			}
			else if(r.getType().equals(IPersistenceService.DELETE)) {
				Node n = r.getStartNode();
				r.delete();
				Iterator<Relationship> instanceOfRels = n.getRelationships(IPersistenceService.INSTANCE_OF).iterator();
				while(instanceOfRels.hasNext()) {
					instanceOfRels.next().delete();
				}
				n.delete();
			}
		}
	}

	private void initializeIndexes() {
		assert state == ConnectionState.OPEN : "Connection closed";

		IndexManager manager = service.index();
		metaIndex = manager.forNodes(IPersistenceService.META_ELEMENTS);
		// [Unload add]
		relationshipIndex = manager.forRelationships(IPersistenceService.META_RELATIONSHIPS);
		// /[Unload add]
	}

	private void initializeResource() {
		assert metaIndex != null : "Null meta index";

		resourceNode = metaIndex.get(IPersistenceService.ID_META,
				IPersistenceService.RESOURCE_NODE).getSingle();

		if (resourceNode == null) {
			resourceNode = service.createNode();
			metaIndex.putIfAbsent(resourceNode, IPersistenceService.ID_META,
					IPersistenceService.RESOURCE_NODE);
		}
	}

	private void initializePackage() {
		assert epackage != null;
		assert metaIndex != null;
		
		eclasses = new Node[epackage.getEClassifiers().size()];
		for (EClassifier each : epackage.getEClassifiers()) {
			String id = each.getEPackage().getName() + "_"
					+ each.getClassifierID();
			Node n = metaIndex.get(IPersistenceService.ID_META, id).getSingle();
			if (n == null) {
				n = this.createTypeNode(id, each);
			}
			eclasses[each.getClassifierID()] = n;
		}
	}
	
	private Node createTypeNode(String id, EClassifier type) {
		Node n = service.createNode();
		n.setProperty(IPersistenceService.ECLASS_NAME, type.getName());
		n.setProperty(IPersistenceService.NS_URI, type.getEPackage()
				.getNsURI());
		metaIndex.putIfAbsent(n, IPersistenceService.ID_META, id);
		return n;
	}
	

	public Node createNode() {
		return service.createNode();
	}
	
	public Node getNodeById(long id) {
		return service.getNodeById(id);
	}
	
	public Relationship getRelationshipById(long id) {
		return service.getRelationshipById(id);
	}

	public IndexManager index() {
		return service.index();
	}
	enum ConnectionState {
		OPEN, CLOSED
	}
	
}
