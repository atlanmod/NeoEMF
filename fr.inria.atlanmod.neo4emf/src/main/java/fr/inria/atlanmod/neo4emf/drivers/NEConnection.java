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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.PersistentPackage;
import fr.inria.atlanmod.neo4emf.RelationshipMapping;
import fr.inria.atlanmod.neo4emf.drivers.impl.NETransaction;
import fr.inria.atlanmod.neo4emf.drivers.impl.Neo4JTransaction;

/**
 * @author sunye
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
	 * Registered PersistentPackage for this session.
	 */
	private final PersistentPackage epackage;

	/**
	 * Mapping between ERefs and Relationships
	 */
	private final RelationshipMapping mapping;

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
	private Node[] nodeTypes;

	/**
	 * Cache for already loaded nodes.
	 */
	private Map<Long, INeo4emfObject> cache = new TreeMap<Long, INeo4emfObject>();

	public NEConnection(GraphDatabaseService gdb, NEConfiguration configuration) {
		assert gdb != null : "Null Database Service";
		assert configuration != null : "Null Configuration";

		service = gdb;
		epackage = configuration.ePackage();
		mapping = epackage.getRelationshipMapping();
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
		nodeTypes = null;
		metaIndex = null;
		cache.clear();
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
		Node newNode;
		Relationship isRootRel = null;
		Relationship instanceOfRel = null;
		
		try {
			
			newNode = this.basicAddNode(obj);
			instanceOfRel = newNode.createRelationshipTo(nodeTypes[typeID], IPersistenceService.INSTANCE_OF);
			
			if (obj.eContainer() == null && obj.eResource() != null) {
				// ROOT Object
				isRootRel = resourceNode.createRelationshipTo(newNode,
						IPersistenceService.IS_ROOT);
			}
		} catch (Exception e) {
			newNode = null;
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
	
	public Node addObject(INeo4emfObject obj) {
		return this.addObject(obj, false);
	}

	/**
	 * Creates an EObject from an ID. The object type (EClass) is retrieved.
	 * Attribute values are not loaded.
	 * 
	 * @param id
	 * @return the new EObject.
	 */
	public INeo4emfObject retrieveObject(long id) {
		INeo4emfObject result = cache.get(id);
		if (result == null) {
			Node node = service.getNodeById(id);
			Relationship r = node.getSingleRelationship(
					IPersistenceService.INSTANCE_OF, Direction.OUTGOING);
			Node typeNode = r.getEndNode();
			int nodeId = (int) typeNode
					.getProperty(IPersistenceService.ECLASS_ID);
			EClass klass = (EClass) epackage.getEClassifiers().get(nodeId);
			result = (INeo4emfObject) epackage.getEFactoryInstance().create(
					klass);
			result.setNodeId(id);
			cache.put(id, result);
		}

		return result;
	}

	/**
	 * 
	 * @param obj
	 */
	public void saveAllAttributes(INeo4emfObject obj) {
		Node n = service.getNodeById(obj.getNodeId());
		obj.saveAllAttributesTo(n);
	}

	/**
	 * Load all attributes of a EObject.
	 * 
	 * @param obj
	 */
	public void loadAttributes(INeo4emfObject obj) {
		assert obj != null : "Null Persistent Object";
		
		Node n = service.getNodeById(obj.getNodeId());
		obj.loadAllAttributesFrom(n);
	}

	/**
	 * Load all references of a EObject.
	 * 
	 * @param obj
	 */
	public void loadReferences(INeo4emfObject obj) {
		Node n = service.getNodeById(obj.getNodeId());
		// TODO
		// obj.loadFrom(n);
	}

	/**
	 * Removes a link between two persistent objects
	 * 
	 * @param source
	 *            the source persistent object
	 * @param eRef
	 *            the link type
	 * @param target
	 *            the target persistent object
	 */
	public boolean removeLink(INeo4emfObject source, EReference eRef,
			INeo4emfObject target) {
		boolean found = false;
		try {
			Node sourceNode = service.getNodeById(source.getNodeId());
			Node targetNode = service.getNodeById(target.getNodeId());

			RelationshipType rel = mapping.relationshipAt(source.eClass()
					.getClassifierID(), eRef.getFeatureID());

			Iterator<Relationship> it = sourceNode.getRelationships(rel)
					.iterator();
			while (!found && it.hasNext()) {
				Relationship relship = it.next();
				if (relship.getEndNode() == targetNode) {
					relship.delete();
					found = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return found;
	}

	/**
	 * Adds a link between two Nodes.
	 * 
	 * @param source
	 *            the source persistent object.
	 * @param eRef
	 *            the link type.
	 * @param target
	 *            the target persistent object.
	 * @return true if the link was successfully added. False otherwise.
	 */
	public boolean addLink(INeo4emfObject source, EReference eRef,
			INeo4emfObject target) {

		try {
			Node sourceNode = service.getNodeById(source.getNodeId());
			Node targetNode = service.getNodeById(target.getNodeId());
			RelationshipType rel = mapping.relationshipAt(source.eClass()
					.getClassifierID(), eRef.getFeatureID());
			sourceNode.createRelationshipTo(targetNode, rel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void loadReferences(INeo4emfObject source,
			EReference eRef) {
		List<INeo4emfObject> result = new LinkedList<INeo4emfObject>();
		RelationshipType rel = mapping.relationshipAt(source.eClass()
				.getClassifierID(), eRef.getFeatureID());
		Node sourceNode = service.getNodeById(source.getNodeId());

		for (Relationship each : sourceNode.getRelationships(rel,
				Direction.OUTGOING)) {
            long id  = each.getEndNode().getId();
			result.add(this.retrieveObject(id));
		}
	}

	/**
	 * Adds a node representing a persistent object to the database, a 'IS_ROOT'
	 * link between the new node and the resource and a 'INSTANCE-OF' link
	 * between the new node and the node that represents the object's type
	 * (EClass).
	 * 
	 * @param obj
	 * @return
	 */
	public boolean addRootObject(INeo4emfObject obj) {
		assert obj.eContainer() == null : "Container not null (not a root node)";

		try {
			Node newNode = this.basicAddNode(obj);
			resourceNode.createRelationshipTo(newNode,
					IPersistenceService.IS_ROOT);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Adds a node representing a persistent object to the database and an
	 * 'INSTANCE-OF' link between the new node and the node that represents the
	 * object's type (EClass).
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private Node basicAddNode(INeo4emfObject obj) throws Exception {
		assert obj.eClass().getClassifierID() < nodeTypes.length : "Invalid type ID";

		int typeID = obj.eClass().getClassifierID();
		Node newNode = service.createNode();
		obj.setNodeId(newNode.getId());
		newNode.createRelationshipTo(nodeTypes[typeID],
				IPersistenceService.INSTANCE_OF);
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
		Iterator<Relationship> it = fromNode.getRelationships(
				IPersistenceService.ADD_LINK).iterator();
		while (it.hasNext()) {
			Relationship rel = it.next();
			if(rel.getProperty("gen_rel").equals(relType.name())
					&& rel.getOtherNode(fromNode).getId() == toNode.getId()) {
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

    /*
     * Creates indexes for Meta-classes (ECLass), if absent.
     */
	private void initializeIndexes() {
		assert state == ConnectionState.OPEN : "Connection closed";

		IndexManager manager = service.index();
		metaIndex = manager.forNodes(IPersistenceService.META_ELEMENTS);
		// [Unload add]
		relationshipIndex = manager.forRelationships(IPersistenceService.META_RELATIONSHIPS);
		// /[Unload add]
	}

    /*
     * Creates a node corresponding to a resource and adds it to the
     * meta-classes index, if absent.
     */
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

    /*
     * Creates nodes corresponding to all meta-classes of the associated package, if needed
     * and adds these nodes to the nodeTypes array.
     */
	private void initializePackage() {
		assert epackage != null;
		assert metaIndex != null;

		nodeTypes = new Node[epackage.getEClassifiers().size()];
		for (EClassifier each : epackage.getEClassifiers()) {
			String id = each.getEPackage().getName() + "_"
					+ each.getClassifierID();
			Node n = metaIndex.get(IPersistenceService.ID_META, id).getSingle();
			if (n == null) {
				n = this.createTypeNode(id, each);
			}
			nodeTypes[each.getClassifierID()] = n;
		}
	}

    /*
     * creates a node corresponding to a meta-class and adds it to the meta-classes index.
     */
	private Node createTypeNode(String id, EClassifier type) {
		Node n = service.createNode();
		n.setProperty(IPersistenceService.ECLASS_NAME, type.getName());
		n.setProperty(IPersistenceService.NS_URI, type.getEPackage().getNsURI());
		n.setProperty(IPersistenceService.ECLASS_ID, type.getClassifierID());
		metaIndex.putIfAbsent(n, IPersistenceService.ID_META, id);
		return n;
	}

	/**
	 * @deprecated Use {@link #addObject()} instead
	 */
	@Deprecated
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
