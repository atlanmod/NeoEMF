package fr.inria.atlanmod.neo4emf.util.impl;

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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.traversal.Evaluator;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.kernel.Traversal;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLog;
import fr.inria.atlanmod.neo4emf.util.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.util.IPersistenceService;


public class PersistenceService extends EmbeddedGraphDatabase implements IPersistenceService {

	String storeDir;
	IPersistenceManager manager;
	@SuppressWarnings("deprecation")
	public PersistenceService(String storeDir,IPersistenceManager mng) {	
		super(storeDir);
		this.storeDir= storeDir;
		manager = mng;
	}

	@Override
	public Index<Node> getMetaIndex(){
		return index().forNodes(META_ELEMENTS);		
	}

	@Override
	public Node createWithIndexIfNotExists(EClass c){		
		if (getMetaIndex().get(ID_META, c.getClassifierID()).getSingle() != null)
			return getMetaIndex().get(ID_META, c.getClassifierID()).getSingle();
		Node n = createNode();
		n.setProperty(ECLASS_NAME,c.getName() );
		n.setProperty(NS_URI, c.getEPackage().getNsURI());
		getMetaIndex().putIfAbsent(n, ID_META, c.getClassifierID());
		return n;					
	}

	public Node createResourceNodeIfAbsent(){
		if (getMetaIndex().get(ID_META, RESOURCE_NODE).getSingle() != null)
			return getMetaIndex().get(ID_META, RESOURCE_NODE).getSingle();
		Node n = createNode();
		getMetaIndex().putIfAbsent(n, ID_META, RESOURCE_NODE);
		return n;					
	}

	@Override 
	public EObject createObjectProxyFromNode(Node n){
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
		for(EReference ref : cls.getEAllReferences())
			if(ref.isContainer()) return false;
		 return true;
	}

	@Override
	public ArrayList<Node> getAllRootNodes() {
		Node resourceNode = getMetaIndex().get(ID_META, RESOURCE_NODE).getSingle();
		return fetchNodesByRT(resourceNode.getId(), IS_ROOT);


	}
	private ArrayList<Node> fetchNodesByRT(long nodeId, RelationshipType relType, Direction direction) {
		Traverser tvr =  setUpTraversal(nodeId,relType,direction);
		return traverseNodes(tvr);
	}
	private ArrayList<Node> fetchNodesByRT(long nodeId, RelationshipType relType) {
		return fetchNodesByRT(nodeId, relType, Direction.OUTGOING);
	}

	private ArrayList<Node> traverseNodes(Traverser tvr) {
		ArrayList<Node > nodeList = new ArrayList<Node>();
		for (Path path : tvr)
			nodeList.add(path.endNode());
		return nodeList;
	}

	protected Traverser setUpTraversal(long nodeId, RelationshipType relType, Direction direction) {
		Node startNode = getNodeById(nodeId);
		TraversalDescription td =  Traversal.description().breadthFirst()
				.relationships(relType, direction)
				.evaluator(Evaluators.excludeStartPosition());
		return td.traverse(startNode);

	}
	
//	private Traverser setUpTraversal(long nodeId, RelationshipType relType) {
//		return setUpTraversal(nodeId, relType, Direction.OUTGOING);
//	}
	
	@Override
	public String getNodeType(Node n) {
		ArrayList<Node> list = fetchNodesByRT(n.getId(), INSTANCE_OF);
		return (String) (list.size() == 1 ? list.get(0).getProperty(ECLASS_NAME) :null);
	}

	@Override
	public String getContainingPackage(Node n) {
		ArrayList<Node> list = fetchNodesByRT(n.getId(), INSTANCE_OF);
		return (String) (list.size() == 1 ? list.get(0).getProperty(NS_URI) :null);
	}

	@Override
	public List<Node> getNodesOnDemand(long nodeid,
			 RelationshipType relationshipType) {
		return fetchNodesByRT(nodeid, relationshipType);
	}

	@Override
	public boolean isRootNode(Node node) {
		List<Node> nodes = fetchNodesByRT(node.getId(), IS_ROOT, Direction.INCOMING);
		if(nodes.size() == 0)
			return false;
		if (nodes.size()==1 && nodes.get(0).equals(node))
			return false ;
		return true;
		}

}
