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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.NETransaction;
import fr.inria.atlanmod.neo4emf.drivers.impl.Neo4JTransaction;

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
	public Node addObject(INeo4emfObject obj) {
		int typeID = obj.eClass().getClassifierID(); 
		assert typeID < eclasses.length;
		
		Node newNode = service.createNode();
		obj.setNodeId(newNode.getId());
		newNode.createRelationshipTo(eclasses[typeID], IPersistenceService.INSTANCE_OF);
		
		if (obj.eContainer() == null && obj.eResource() != null) {
			// ROOT Object
			resourceNode.createRelationshipTo(newNode, IPersistenceService.IS_ROOT);
		}
		
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
