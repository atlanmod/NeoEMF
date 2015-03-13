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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.drivers.ILoader;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceService;
import fr.inria.atlanmod.neo4emf.drivers.IPersistenceServiceFactory;
import fr.inria.atlanmod.neo4emf.drivers.IProxyManager;
import fr.inria.atlanmod.neo4emf.drivers.ISerializer;
import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfResource;
import fr.inria.atlanmod.neo4emf.resourceUtil.Neo4emfResourceUtil;

public class PersistenceManager implements IPersistenceManager {

	/**
	 * the persistence Backend {@link PersistenceService}
	 */
	protected IPersistenceService persistenceService;
	/**
	 * The loader and eObjects builder {@link Loader}
	 */
	protected ILoader loader;
	/**
	 * Serializer and nodes converter {@link Serializer}
	 */
	protected ISerializer serializer;
	/**
	 * The resource {@link Neo4emfResource}
	 */
	protected INeo4emfResource resource;
	/**
	 * The loaded elements manager {@link ProxyManager}
	 */
	protected IProxyManager proxyManager;
	
	private int sessionId = 0;

	/**
	 * Global constructor
	 * 
	 * @param neo4emfResource
	 *            {@link Neo4emfResource}
	 * @param storeDirectory
	 *            {@link String}
	 * @param eRef2relType
	 *            {@link Map}
	 */

	
	public PersistenceManager(INeo4emfResource neo4emfResource,
			NEConfiguration configuration) {
		
		this.resource = neo4emfResource;
		this.persistenceService = IPersistenceServiceFactory.eINSTANCE
				.createPersistenceService(configuration);
		this.serializer = new Serializer(this,Integer.parseInt(configuration.options().get("transaction_count")));
		this.proxyManager = new ProxyManager();
		this.loader = new Loader(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(Map<?, ?> options) {
		try {
			this.serializer.save((Map<String, Object>) options);
		} catch (Exception e) {
			shutdown();
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		load(null);
	}

	@Override
	public void load(Map<?, ?> options) {
		try {
			loader.load(options);
		} catch (Exception e) {
			shutdown();
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		serializer.save(null);
	}

	@Override
	public NETransaction createTransaction() {
		return persistenceService.createTransaction();
	}
	
	@Override
	public void cleanIndexes() {
		persistenceService.cleanIndexes();
	}
	
	@Override
	public void shutdown() {
		persistenceService.shutdown();
	}

	//@Override
	public Node getNodeById(EObject eObj) {
		assert ((INeo4emfObject) eObj).getNodeId() >= 0 : "nodeId is > -1";
		
		Node result = persistenceService.getNodeById(((INeo4emfObject) eObj)
				.getNodeId());
		if (result == null) {
			throw new NullPointerException(" Cannot find the node ");
		} else {
			return result;
		}
	}
	
//	@Override
	public Node getAttributeNodeById(EObject eObj) {
		assert ((INeo4emfObject)eObj).getAttributeNodeId() > -1 : "attribute node id is > -1";

		Node result = persistenceService.getNodeById(((INeo4emfObject)eObj).getAttributeNodeId());
		return result;
	}
	
//	@Override
	public Node getAttributeNode(Node n) {
		// Strange to have node here
		Node result = persistenceService.getAttributeNode(n);
		return result;
	}
	
//	@Override
	public List<Relationship> getTmpRelationships() {
		return persistenceService.getTmpRelationships();
	}
	
//	@Override
//	public List<Node> getTmpNodes() {
//		return persistenceService.getTmpNodes();
//	}
//	
//	@Override
	public void flushTmpRelationships(List<Relationship> rels) {
		persistenceService.flushTmpRelationships(rels);	
	}

//	@Override
//	public Node createNodefromEObject(EObject eObject) {
//		return persistenceService.createNodeFromEObject(eObject,false);
//	}
	
//	@Override
//	public Node createNodefromEObject(EObject eObject, boolean isTemporary) {
//		Node n = persistenceService.createNodeFromEObject(eObject,isTemporary);
//		((INeo4emfObject)eObject).setNodeId(n.getId());
//		proxyManager.putToProxy((INeo4emfObject)eObject);
//		return n;
//	}
	
//	@Override
	public void deleteNodeFromEObject(INeo4emfObject eObject) {
		persistenceService.deleteNodeFromEObject(eObject);
	}
	
//	@Override
	public Node createAttributeNodeForEObject(INeo4emfObject eObject) {
		return persistenceService.createAttributeNodeForEObject(eObject);
	}
	
//	@Override
	public Relationship createAddLinkRelationship(INeo4emfObject from, INeo4emfObject to, EReference ref) {
		// FIXME see if it is good (case of an old object that has references to an other one
		// which hasn't been yet created). If this reference is not bidirectionnal there will be
		// a problem
		if(from.getSessionId() >= 0 && to.getSessionId() == -1 && ref.getEOpposite() == null) {
			Relationship r =  persistenceService.createAddLinkRelationship(from, to, ref);
			if(r != null) {
				to.setSessionId(sessionId);
			}
		}
		else if(from.getSessionId() >= 0 && to.getSessionId() >= 0) {
			return persistenceService.createAddLinkRelationship(from, to, ref);
		}
		return null;
	}
	
//	@Override
	public Relationship createRemoveLinkRelationship(INeo4emfObject from, INeo4emfObject to, EReference ref) {
		return persistenceService.createRemoveLinkRelationship(from, to, ref);
	}
	
//	@Override
	public Relationship createDeleteRelationship(INeo4emfObject obj) {
		return persistenceService.createDeleteRelationship(obj);
	}

	//@Override
	public RelationshipType getRelTypefromERef(int classID, int referenceID) {
		return persistenceService.getRelationshipFor(classID, referenceID);
	}
	
	public Node createNodefromEObject(INeo4emfObject eObject, boolean isTemporary) {
		Node n = persistenceService.createNodeFromEObject(eObject,isTemporary);
		eObject.setNodeId(n.getId());
		eObject.setSessionId(sessionId);
		proxyManager.putToProxy((INeo4emfObject)eObject);
		return n;
	}
	
	public void createLink(INeo4emfObject from, INeo4emfObject to, EReference ref) {
		persistenceService.createLink(from,to,ref);
	}
	
	public void removeLink(INeo4emfObject from, INeo4emfObject to, EReference ref) {
		persistenceService.removeLink(from,to,ref);
	}
	
	//@Override
	public List<Node> getAllRootNodes() {
		return persistenceService.getAllRootNodes();
	}

	@Override
	public void addObjectsToContents(List<INeo4emfObject> objects) {
		resource.getContents().addAll(objects);
	}

	
	//@Override
	public String getNodeType(Node n) {
		return persistenceService.getNodeType(n);
	}

	//@Override
	public String getNodeContainingPackage(Node n) {
		return persistenceService.getContainingPackage(n);
	}

	@Override
	public void fetchAttributes(EObject obj) {
		Node node = getNodeById(obj);
		// TODO find a way to avoid that call when appropriate (maybe
		// if there is a non changed changelog)
		Node attributeNode = null;
		if(((Neo4emfObject)obj).getAttributeNodeId() > -1) {
			attributeNode = getAttributeNodeById(obj);
//			((INeo4emfObject)obj).loadAllAttributesFrom(attributeNode);
//			return;
		}
//		((INeo4emfObject)obj).loadAllAttributesFrom(node);
		loader.fetchAttributes(obj,node,attributeNode);	
	}

	@Override
	public void getOnDemand(EObject obj, int featureId) {
		try {
			EClass eClass = obj.eClass();
			EStructuralFeature feature = eClass.getEStructuralFeature(featureId);
			EClass parent = feature.getEContainingClass();
			
//			EPackage ePackage = eClass.getEPackage();
		//	RelationshipType relationship =  getRelTypefromERef(ePackage.getNsURI(),eClass.getClassifierID(), featureId);
			RelationshipType relationship =  null;
			if ( relationship == null ) {
				  //throw new NullPointerException(" Cannot find the relationship ");
				String stri = Neo4emfResourceUtil.formatRelationshipName(parent,feature);
				relationship = DynamicRelationshipType.withName(stri);
			}
			List <Node> nodes= persistenceService.getNodesOnDemand(((INeo4emfObject)obj).getNodeId(),
						relationship);
			List<Node> addLinkNodes = persistenceService.getAddLinkNodesOnDemand(((INeo4emfObject)obj).getNodeId(), relationship);
			List<Node> removeLinkNodes = persistenceService.getRemoveLinkNodesOnDemand(((INeo4emfObject)obj).getNodeId(), relationship);
			nodes.addAll(addLinkNodes);
			nodes.removeAll(removeLinkNodes);
			loader.getObjectsOnDemand(obj, featureId,getNodeById(obj), nodes);
		}catch(Exception e){ 
			shutdown();
			e.printStackTrace();
		}
	}

	@Override
	public EObject getContainerOnDemand(EObject eObject, int featureId) {
		EObject eResult = null;
		try {
			List<Node> singleNode = persistenceService.getNodesOnDemand(
					((INeo4emfObject) eObject).getNodeId(),
					getRelTypefromERef(eObject.eClass().getClassifierID(),
							featureId));
			eResult = loader.getContainerOnDemand(eObject, featureId,
					getNodeById(eObject), singleNode.get(0));
		} catch (Exception e) {
			shutdown();
			e.printStackTrace();
		}
		return eResult;
	}

	public boolean isRootNode(Node node) {
		return persistenceService.isRootNode(node);
	}

	@Override
	public EList<INeo4emfObject> getAllInstancesOfType(EClass eClass) {
		EList<EClass> classesList = ((Loader) loader).subClassesOf(eClass);
		EList<INeo4emfObject> allInstances = new BasicEList<INeo4emfObject>();
		List<Node> nodeList = new ArrayList<Node>();
		try {
			for (EClass eCls : classesList) {
				nodeList = this.persistenceService.getAllNodesOfType(eCls);
				if (nodeList.isEmpty()) {
					continue;
				}
				allInstances
						.addAll(this.loader.getAllInstances(eCls, nodeList));
			}
		} catch (Exception e) {
			shutdown();
			e.printStackTrace();
		}
		return allInstances;
	}

	public INeo4emfResource getResource() {
		return resource;
	}

	@Override
	public INeo4emfObject getObjectFromProxy(EClass eClassifier, Node n) {
		return proxyManager.getObjectFromProxy(eClassifier, n.getId());
	}
	
	/**
	 * {@link IPersistenceManager#getProxyManager()}
	 */
	@Override
	public IProxyManager getProxyManager() {
		return proxyManager;
	}
	
	public void startNewSession() {
		sessionId++;
	}
	
}
