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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.kernel.Traversal;

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
	public Node addObject(INeo4emfObject obj) {
		Node newNode;
		try {
			newNode = this.basicAddNode(obj);
			if (obj.eContainer() == null && obj.eResource() != null) {
				// ROOT Object
				resourceNode.createRelationshipTo(newNode,
						IPersistenceService.IS_ROOT);
			}
		} catch (Exception e) {
			newNode = null;
		}

		return newNode;
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

	public List<Long> somethingVeryComplicate(INeo4emfObject source,
			EReference eRef) {
		List<Long> result = new LinkedList<Long>();
		RelationshipType rel = mapping.relationshipAt(source.eClass()
				.getClassifierID(), eRef.getFeatureID());
		Node sourceNode = service.getNodeById(source.getNodeId());

		for (Relationship each : sourceNode.getRelationships(rel,
				Direction.OUTGOING)) {
			result.add(each.getEndNode().getId());
		}
		return result;
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

	private void initializeIndexes() {
		assert state == ConnectionState.OPEN : "Connection closed";

		IndexManager manager = service.index();
		metaIndex = manager.forNodes(IPersistenceService.META_ELEMENTS);
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

	public void invariant() {
		assert epackage.getEFactoryInstance() != null : "Null factory";
	}
}
