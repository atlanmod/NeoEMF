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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.traversal.Evaluator;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.kernel.Traversal;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.Point;
import fr.inria.atlanmod.neo4emf.RelationshipMapping;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;
import fr.inria.atlanmod.neo4emf.drivers.NEConnection;

public class PersistenceService implements IPersistenceService {
	
	/**
	 * The database service connection.
	 */
	private final NEConnection connection;
	
	private final RelationshipMapping mapping;

	protected PersistenceService(GraphDatabaseService service, NEConfiguration configuration) {
		this.connection = new NEConnection(service, configuration);
		this.mapping = configuration.ePackage().getRelationshipMapping();
		connection.open();
	}

	@Override
	public Index<Node> getMetaIndex() {
		return index().forNodes(META_ELEMENTS);
	}
	
	@Override
	public Index<Relationship> getRelationshipIndex() {
		return index().forRelationships(META_RELATIONSHIPS);
	}
	
	@Override
	public Node getAttributeNode(Node n) {
		Iterator<Relationship> setAttributeRels = n.getRelationships(SET_ATTRIBUTE).iterator();
		while(setAttributeRels.hasNext()) {
			return setAttributeRels.next().getEndNode();
		}
		return null;
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
		return createNodeFromEObject(eObject, false);
	}

	@Override
	public Node createNodeFromEObject(INeo4emfObject eObject, boolean isTemporary) {
		return connection.addObject(eObject,isTemporary);
	}
	
	@Override
	public void createLink(INeo4emfObject from, INeo4emfObject to, EReference ref) {
		connection.createRelationship(from,to,getRelationshipFor(from.eClass().getClassifierID(), ref.getFeatureID()));
	}
	
	@Override
	public void removeLink(INeo4emfObject from, INeo4emfObject to, EReference ref) {
		connection.removeRelationship(from,to,getRelationshipFor(from.eClass().getClassifierID(), ref.getFeatureID()));
	}
	
	@Override
	public Node createAttributeNodeForEObject(INeo4emfObject eObject) {
		return connection.addAttribute(eObject);
	}
	
	@Override
	public void deleteNodeFromEObject(INeo4emfObject eObject) {
		connection.removeObject(eObject);
	}
	
	@Override
	public Relationship createAddLinkRelationship(INeo4emfObject from, INeo4emfObject to, EReference ref) {
		return connection.addTmpRelationshipBetween(from, to, getRelationshipFor(from.eClass().getClassifierID(), ref.getFeatureID()));
	}
	
	@Override
	public Relationship createRemoveLinkRelationship(INeo4emfObject from, INeo4emfObject to, EReference ref) {
		return connection.removeTmpRelationshipBetween(from, to, getRelationshipFor(from.eClass().getClassifierID(), ref.getFeatureID()));
	}
	
	@Override
	public Relationship createDeleteRelationship(INeo4emfObject obj) {
		return connection.addDeleteRelationship(obj);
	}
	
	@Override
	public List<Relationship> getTmpRelationships() {
		Iterator<Relationship> it = this.getRelationshipIndex().get(ID_META, TMP_RELATIONSHIP).iterator();
		ArrayList<Relationship> rels = new ArrayList<Relationship>();
		while(it.hasNext()) {
			rels.add(it.next());
		}
		return rels;
	}
	
	@Override
	public List<Node> getTmpNodes() {
		Iterator<Node> it = this.getMetaIndex().get(ID_META, TMP_NODE).iterator();
		ArrayList<Node> nodes = new ArrayList<Node>();
		while(it.hasNext()) {
			nodes.add(it.next());
		}
		return nodes;
	}
	
	@Override
	public void flushTmpRelationships() {
		connection.flushTmpRelationships();
	}

	// TODO, check if it is still needed
	private boolean isRoot(Neo4emfObject eObject) {		
		if (eObject.eContainer() == null && eObject.eResource() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Node> getAllRootNodes() {
		//Index<Node> index = getMetaIndex();
		Node resourceNode = this.createResourceNodeIfAbsent();
		assert resourceNode != null : "Null resource node";
		return fetchNodesByRT(resourceNode.getId(), IS_ROOT);
	}

	
	private List<Node> fetchNodesByRT(long nodeId, RelationshipType relType, Direction direction) {
		Traverser tvr =  setUpTraversal(nodeId,relType,direction);
		return traverseNodes(tvr);
	}
	
	private ArrayList<Node> fetchNodesWithAddLink(long nodeId, RelationshipType baseRelType, Direction direction) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node startNode = getNodeById(nodeId);
		Iterator<Relationship> it = startNode.getRelationships(ADD_LINK,Direction.OUTGOING).iterator();
		while(it.hasNext()) {
			Relationship r = it.next();
			if(r.hasProperty("gen_rel") && r.getProperty("gen_rel").equals(baseRelType.name())) {
				nodes.add(r.getEndNode());
			}
		}
		return nodes;
	}
	
	private ArrayList<Node> fetchNodesWithRemoveLink(long nodeId, RelationshipType baseRelType, Direction direction) {
		Traverser tvr = setUpTraversalRemoveLink(nodeId, direction);
		return traverseNodesRemoveLink(tvr, baseRelType.name());
	}
	
	private List<Node> fetchNodesByRT(long nodeId, RelationshipType relType) {
		return fetchNodesByRT(nodeId, relType, Direction.OUTGOING);
	}
	
	private ArrayList<Node> fetchNodesWithAddLink(long nodeId, RelationshipType baseRelType) {
		return fetchNodesWithAddLink(nodeId, baseRelType, Direction.OUTGOING);
	}
	
	private ArrayList<Node> fetchNodesWithRemoveLink(long nodeId, RelationshipType baseRelType) {
		return fetchNodesWithRemoveLink(nodeId, baseRelType, Direction.OUTGOING);
	}

	private List<Node> traverseNodes(Traverser tvr) {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for (Path path : tvr)
			nodeList.add(path.endNode());
		return nodeList;
	}
	
	private ArrayList<Node> traverseNodesRemoveLink(Traverser tvr, String baseRelTypeName) {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for(Path path : tvr) {
			Relationship lastRelationship = path.lastRelationship();
			if(lastRelationship.hasProperty("gen_rel") && lastRelationship.getProperty("gen_rel").equals(baseRelTypeName)) {
				nodeList.add(path.endNode());
			}
		}
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
	
	protected Traverser setUpTraversalAddLink(long nodeId, Direction direction) {
		Node startNode = getNodeById(nodeId);
		TraversalDescription td = Traversal.description().breadthFirst()
				.relationships(ADD_LINK,direction)
				.evaluator(Evaluators.excludeStartPosition())
				.evaluator(Evaluators.toDepth(1));
		return td.traverse(startNode);
	}
	
	protected Traverser setUpTraversalRemoveLink(long nodeId, Direction direction) {
		Node startNode = getNodeById(nodeId);
		TraversalDescription td = Traversal.description().breadthFirst()
				.relationships(REMOVE_LINK,direction)
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
	public List<Node> getAddLinkNodesOnDemand(long nodeid, 
			RelationshipType baseRelationshipType) {
		return fetchNodesWithAddLink(nodeid, baseRelationshipType);
	}
	
	@Override
	public List<Node> getRemoveLinkNodesOnDemand(long nodeid,
			RelationshipType baseRelationshipType) {
		return fetchNodesWithRemoveLink(nodeid,baseRelationshipType);
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
	
	public void cleanIndexes() {
		connection.cleanIndexes();
	}
	
	public RelationshipType getRelationshipFor(int classID, int referenceID) {
		return mapping.relationshipAt(classID, referenceID);
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
