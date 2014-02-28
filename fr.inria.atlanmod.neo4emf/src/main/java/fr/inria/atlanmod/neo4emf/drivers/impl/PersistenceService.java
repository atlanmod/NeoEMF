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
import org.eclipse.emf.ecore.EObject;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.event.KernelEventHandler;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.traversal.Evaluator;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.index.lucene.LuceneKernelExtensionFactory;
import org.neo4j.kernel.Traversal;
import org.neo4j.kernel.extension.KernelExtensionFactory;
import org.neo4j.kernel.impl.cache.CacheProvider;
import org.neo4j.kernel.impl.cache.SoftCacheProvider;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

public class PersistenceService implements IPersistenceService {
	
	/**
	 * The Neo4j database service.
	 */
	GraphDatabaseService db;

	public PersistenceService(String storeDir) {

		// the cache providers
		ArrayList<CacheProvider> cacheList = new ArrayList<CacheProvider>();
		cacheList.add(new SoftCacheProvider());

		// the kernel extensions
		LuceneKernelExtensionFactory lucene = new LuceneKernelExtensionFactory();
		List<KernelExtensionFactory<?>> extensions = new ArrayList<KernelExtensionFactory<?>>();
		extensions.add(lucene);

		// the database setup
		GraphDatabaseFactory gdbf = new GraphDatabaseFactory();
		gdbf.setKernelExtensions(extensions);
		gdbf.setCacheProviders(cacheList);
		db = gdbf.newEmbeddedDatabase(storeDir);
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

	@Override
	public Node createWithIndexIfNotExists(EClass c) {
		assert c != null : "Null ECLass";
		assert c.getName() != null : "Null EClass name";
		assert c.getEPackage() != null : "Null EPackage for EClass";
		assert c.getEPackage().getNsURI() != null : "Null EPackage NsURI";
		
		
		String value = getIdMetaValueFromClass(c);
		if (getMetaIndex().get(ID_META, value).getSingle() != null)
			return getMetaIndex().get(ID_META, value).getSingle();
		Node n = createNode();
		n.setProperty(ECLASS_NAME, c.getName());
		n.setProperty(NS_URI, c.getEPackage().getNsURI());
		getMetaIndex().putIfAbsent(n, ID_META, value);
		return n;
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
	public EObject createObjectProxyFromNode(Node n) {
		// TODO finish declaration
		return null;
	}
	
	@Override
	public Node createNodeFromEObject(EObject eObject) {
		return createNodeFromEObject(eObject, false);
	}

	@Override
	public Node createNodeFromEObject(EObject eObject, boolean isTemporary) {
		Node node = createNode();
		Node typeNode = createWithIndexIfNotExists(eObject.eClass());
		Relationship instanceOfRel = node.createRelationshipTo(typeNode, INSTANCE_OF);
		Relationship isRootRel = null;
		if (isRoot(eObject))
			isRootRel = createResourceNodeIfAbsent().createRelationshipTo(node, IS_ROOT);		
		if(isTemporary) {
			getMetaIndex().add(node, ID_META, TMP_NODE);
			getRelationshipIndex().add(instanceOfRel, ID_META, TMP_RELATIONSHIP);
			if(isRootRel != null) {
				getRelationshipIndex().add(isRootRel, ID_META, TMP_RELATIONSHIP);
			}
		}
		return node;
	}
	
	@Override
	public Node createAttributeNodeForEObject(EObject eObject) {
		Node base = getNodeById(((INeo4emfObject)eObject).getNodeId());
		Node node = createNode();
		((Neo4emfObject)eObject).setAttributeNodeId(node.getId());
		Relationship r = base.createRelationshipTo(node, SET_ATTRIBUTE);
		getMetaIndex().add(node, ID_META, TMP_NODE);
		getRelationshipIndex().add(r, ID_META, TMP_RELATIONSHIP);
		return node;
	}
	
	@Override
	public void deleteNodeFromEObject(EObject eObject) {
		Node n = getNodeById(((INeo4emfObject)eObject).getNodeId());
		Iterator<Relationship> it = n.getRelationships().iterator();
		while(it.hasNext()) {
			Relationship rel = it.next();
			rel.delete();
		}
		n.delete();
	}
	
	@Override
	public Relationship createAddLinkRelationship(Node from, Node to, RelationshipType relType) {
		/*
		 * If there already is a REMOVE_LINK relation ship with the same
		 * property just removes it
		 */
		boolean changeThatAmazingBoolean= false;
		Iterator<Relationship> it = from.getRelationships(REMOVE_LINK).iterator();
		while(it.hasNext()) {
			Relationship r = it.next();
			if(r.getProperty("gen_rel").equals(relType.toString()) && r.getOtherNode(from).equals(to)) {
				r.delete();
				changeThatAmazingBoolean = true;
			}
		}
		/*
		 * Also remove the "delete" relations generated
		 */
		it = from.getRelationships(DELETE).iterator();
		while(it.hasNext()) {
			it.next().delete();
		}
		if(changeThatAmazingBoolean) {
			return null;
		}
		Relationship rel = from.createRelationshipTo(to, ADD_LINK);
		rel.setProperty("gen_rel", relType.toString());
		getRelationshipIndex().add(rel, ID_META, TMP_RELATIONSHIP);
		return rel;
	}
	
	@Override
	public Relationship createRemoveLinkRelationship(Node from, Node to, RelationshipType relType) {
		/*
		 * If there already is an ADD_LINK relation ship with the same
		 * property just removes it
		 */
		boolean changeThatAmazingBoolean= false;
		Iterator<Relationship> it = from.getRelationships(ADD_LINK).iterator();
		while(it.hasNext()) {
			Relationship r = it.next();
			if(r.getProperty("gen_rel").equals(relType.toString()) && r.getOtherNode(from).equals(to)) {
				r.delete();
				changeThatAmazingBoolean = true;
			}
		}
		if(changeThatAmazingBoolean) {
			return null;
		}
		Relationship rel = from.createRelationshipTo(to, REMOVE_LINK);
		rel.setProperty("gen_rel", relType.toString());
		getRelationshipIndex().add(rel, ID_META, TMP_RELATIONSHIP);
		return rel;
	}
	
	@Override
	public Relationship createDeleteRelationship(Node node) {
		Relationship rel = node.createRelationshipTo(node, DELETE);
		getRelationshipIndex().add(rel, ID_META, TMP_RELATIONSHIP);
		return rel;
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
	public void processTemporaryRelationship(Relationship r) {
		if(r.getType().equals(SET_ATTRIBUTE)) {
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
		else if(r.getType().equals(ADD_LINK)) {
			String baseRelationName = (String) r.getProperty("gen_rel");
			Node from = r.getStartNode();
			Node to = r.getEndNode();
			// also fix that
			from.createRelationshipTo(to, DynamicRelationshipType.withName(baseRelationName));
			r.delete();
			getMetaIndex().remove(from, ID_META);
			getMetaIndex().remove(to, ID_META);
			// fix that, ugly
			Iterator<Relationship> fromIO = from.getRelationships(INSTANCE_OF).iterator();
			Iterator<Relationship> toIO = to.getRelationships(INSTANCE_OF).iterator();
			while(fromIO.hasNext()) {
				getRelationshipIndex().remove(fromIO.next(),ID_META);
			}
			while(toIO.hasNext()) {
				getRelationshipIndex().remove(toIO.next(),ID_META);
			}
		}
		else if(r.getType().equals(REMOVE_LINK)) {
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
		else if(r.getType().equals(DELETE)) {
			Node n = r.getStartNode();
			r.delete();
			Iterator<Relationship> instanceOfRels = n.getRelationships(INSTANCE_OF).iterator();
			while(instanceOfRels.hasNext()) {
				instanceOfRels.next().delete();
			}
			n.delete();
		}
	}

	private boolean isRoot(EObject eObject) {		
		if (eObject.eContainer() == null && eObject.eResource() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Node> getAllRootNodes() {
		Index<Node> index = getMetaIndex();
		Node resourceNode = index.get(ID_META, RESOURCE_NODE).getSingle();
		
		assert resourceNode != null : "Null resource node";
		return fetchNodesByRT(resourceNode.getId(), IS_ROOT);
	}

	
	private List<Node> fetchNodesByRT(long nodeId, RelationshipType relType, Direction direction) {
		Traverser tvr =  setUpTraversal(nodeId,relType,direction);
		return traverseNodes(tvr);
	}
	
	private ArrayList<Node> fetchNodesWithAddLink(long nodeId, RelationshipType baseRelType, Direction direction) {
//		Traverser tvr = setUpTraversalAddLink(nodeId,  direction);
//		return traverseNodesAddLink(tvr, baseRelType.name());*/
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node startNode = getNodeById(nodeId);
		Iterator<Relationship> it = startNode.getRelationships(ADD_LINK,Direction.OUTGOING).iterator();
		while(it.hasNext()) {
			Relationship r = it.next();
			if(r.hasProperty("gen_rel") && r.getProperty("gen_rel").equals(baseRelType.name())) {
				nodes.add(r.getEndNode());
				System.out.println(r.getEndNode());
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
	
	private ArrayList<Node> traverseNodesAddLink(Traverser tvr, String baseRelTypeName) {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for(Path path : tvr) {
			Relationship lastRelationship = path.lastRelationship();
			System.out.println(baseRelTypeName);
			System.out.println(lastRelationship);
			if(lastRelationship.hasProperty("gen_rel")) {
				System.out.println(lastRelationship.getProperty("gen_rel"));
			}
			else {
				System.out.println("no gen rel");
			}
			if(lastRelationship.hasProperty("gen_rel") && lastRelationship.getProperty("gen_rel").equals(baseRelTypeName)) {
				nodeList.add(path.endNode());
			}
		}
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
		Iterator<Relationship> it = startNode.getRelationships(ADD_LINK).iterator();
		while(it.hasNext()) {
			Relationship r = it.next();
			if(r.hasProperty("gen_rel")) {
				System.out.println(r.getProperty("gen_rel"));
			}
		}
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

	@Override
	public Transaction beginTx() {
		return db.beginTx();
	}

	@Override
	public Node createNode() {
		return db.createNode();
	}

	@Override
	@Deprecated
	public Iterable<Node> getAllNodes() {
		return db.getAllNodes();
	}

	@Override
	public Node getNodeById(long arg0) {
		return db.getNodeById(arg0);
	}

	@Override
	@Deprecated
	public Node getReferenceNode() {
		return db.getReferenceNode();
	}

	@Override
	public Relationship getRelationshipById(long arg0) {
		return db.getRelationshipById(arg0);
	}

	@Override
	@Deprecated
	public Iterable<RelationshipType> getRelationshipTypes() {
		return db.getRelationshipTypes();
	}

	@Override
	public IndexManager index() {
		return db.index();
	}

	@Override
	public KernelEventHandler registerKernelEventHandler(KernelEventHandler arg0) {
		return db.registerKernelEventHandler(arg0);
	}

	@Override
	public <T> TransactionEventHandler<T> registerTransactionEventHandler(
			TransactionEventHandler<T> arg0) {
		return db.registerTransactionEventHandler(arg0);
	}

	@Override
	public void shutdown() {
		db.shutdown();
	}

	@Override
	public KernelEventHandler unregisterKernelEventHandler(
			KernelEventHandler arg0) {
		return db.unregisterKernelEventHandler(arg0);
	}

	@Override
	public <T> TransactionEventHandler<T> unregisterTransactionEventHandler(
			TransactionEventHandler<T> arg0) {
		return db.unregisterTransactionEventHandler(arg0);
	}
}
