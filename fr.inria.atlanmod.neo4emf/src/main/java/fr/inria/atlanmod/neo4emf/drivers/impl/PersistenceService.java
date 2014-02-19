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
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.Direction;
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
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.index.lucene.LuceneKernelExtensionFactory;
import org.neo4j.kernel.Traversal;
import org.neo4j.kernel.extension.KernelExtensionFactory;
import org.neo4j.kernel.impl.cache.CacheProvider;
import org.neo4j.kernel.impl.cache.SoftCacheProvider;

import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;

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
	public Node createWithIndexIfNotExists(EClass c) {
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
		Node node = createNode();
		Node typeNode = createWithIndexIfNotExists(eObject.eClass());
		node.createRelationshipTo(typeNode, INSTANCE_OF);
		if (isRoot(eObject))
			createResourceNodeIfAbsent().createRelationshipTo(node, IS_ROOT);
		return node;
	}



	private boolean isRoot(EObject eObject) {
		EClass cls = eObject.eClass();
		for (EReference ref : cls.getEAllReferences())
			if (ref.isContainer())
				return false;
		return true;
	}

	@Override
	public List<Node> getAllRootNodes() {
		Index<Node> index = getMetaIndex();
		Node resourceNode = index.get(ID_META, RESOURCE_NODE).getSingle();
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
				.evaluator(Evaluators.excludeStartPosition());
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
