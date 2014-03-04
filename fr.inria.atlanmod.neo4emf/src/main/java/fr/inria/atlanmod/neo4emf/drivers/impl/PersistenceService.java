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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.kernel.Traversal;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;
import fr.inria.atlanmod.neo4emf.drivers.NEConnection;

public class PersistenceService implements IPersistenceService {
	
	/**
	 * The database service connection.
	 */
	private final NEConnection connection;

	protected PersistenceService(GraphDatabaseService service, NEConfiguration configuration) {
		this.connection = new NEConnection(service, configuration);
		connection.open();
	}

	@Override
	public Index<Node> getMetaIndex() {
		return index().forNodes(META_ELEMENTS);
	}

	private String getIdMetaValueFromClass(EClass c) {
		return c.getEPackage().getName() + "_" + c.getClassifierID();
	}

	public Node createResourceNodeIfAbsent() {
		if (getMetaIndex().get(ID_META, RESOURCE_NODE).getSingle() != null)
			return getMetaIndex().get(ID_META, RESOURCE_NODE).getSingle();
		Node n = createNode();
		getMetaIndex().putIfAbsent(n, ID_META, RESOURCE_NODE);
		return n;
	}

	@Override
	public Node createNodeFromEObject(INeo4emfObject eObject) {
		return connection.addObject(eObject);
	}

	@Override
	public List<Node> getAllRootNodes() {
		Index<Node> index = getMetaIndex();
		Node resourceNode = this.createResourceNodeIfAbsent();
		assert resourceNode != null : "Null resource node";
		return fetchNodesByRT(resourceNode.getId(), IS_ROOT);
	}

	
	private List<Node> fetchNodesByRT(long nodeId, RelationshipType relType, Direction direction) {
		Traverser tvr =  setUpTraversal(nodeId,relType,direction);
		return traverseNodes(tvr);
	}

	private List<Node> fetchNodesByRT(long nodeId, RelationshipType relType) {
		return fetchNodesByRT(nodeId, relType, Direction.OUTGOING);
	}

	private List<Node> traverseNodes(Traverser tvr) {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for (Path path : tvr)
			nodeList.add(path.endNode());
		return nodeList;
	}

	protected Traverser setUpTraversal(long nodeId, RelationshipType relType,
			Direction direction) {
		Node startNode = getNodeById(nodeId);
		TraversalDescription td = Traversal.description().breadthFirst()
				.relationships(relType, direction)
				.evaluator(Evaluators.excludeStartPosition())
				.evaluator(Evaluators.toDepth(1));
		return td.traverse(startNode);

	}

	// private Traverser setUpTraversal(long nodeId, RelationshipType relType) {
	// return setUpTraversal(nodeId, relType, Direction.OUTGOING);
	// }

	@Override
	public String getNodeType(Node n) {
		List<Node> list = fetchNodesByRT(n.getId(), INSTANCE_OF);
		return (String) (list.size() == 1 ? list.get(0)
				.getProperty(ECLASS_NAME) : null);
	}

	@Override
	public String getContainingPackage(Node n) {
		List<Node> list = fetchNodesByRT(n.getId(), INSTANCE_OF);
		return (String) (list.size() == 1 ? list.get(0).getProperty(NS_URI)
				: null);
	}

	@Override
	public List<Node> getNodesOnDemand(long nodeid,
			RelationshipType relationshipType) {
		return fetchNodesByRT(nodeid, relationshipType);
	}

	@Override
	public boolean isRootNode(Node node) {
		List<Node> nodes = fetchNodesByRT(node.getId(), IS_ROOT,
				Direction.INCOMING);
		if (nodes.size() == 0)
			return false;
		if (nodes.size() == 1 && nodes.get(0).equals(node))
			return false;
		return true;
	}

	@Override
	public List<Node> getAllNodesOfType(EClass eClass) {
		Node eClassNode = getMetaIndex().get(ID_META,
				getIdMetaValueFromClass(eClass)).getSingle();
		if (eClassNode == null)
			return Collections.emptyList();
		return fetchNodesByRT(eClassNode.getId(), INSTANCE_OF,
				Direction.INCOMING);
	}


	public NETransaction createTransaction() {
		return connection.createTransaction();
	}


	public Node createNode() {
		return connection.createNode();
	}

	public Node getNodeById(long id) {
		return connection.getNodeById(id);
	}


	public Relationship getRelationshipById(long id) {
		return connection.getRelationshipById(id);
	}


	public IndexManager index() {
		return connection.index();
	}

	@Override
	public void shutdown() {
		connection.close();
	}

}
