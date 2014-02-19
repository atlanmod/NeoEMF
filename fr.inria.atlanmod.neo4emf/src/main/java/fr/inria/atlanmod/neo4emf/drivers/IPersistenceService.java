package fr.inria.atlanmod.neo4emf.drivers;

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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.neo4j.cypher.internal.parser.v1_8.ParserPattern.No;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.index.Index;

public interface IPersistenceService extends GraphDatabaseService {
	/**
	 * NS_URI Constant key to save NS_URI {@link String}
	 */
	public final String NS_URI = "ns_uri";
	/**
	 * META_ELEMENTS Constant key to save add EClass types to the index
	 * {@link String}
	 */
	public final String META_ELEMENTS = "meta_elements";
	/**
	 * ROOT_ELEMENTS Constant to meta_elements index name {@link String}
	 */
	public final String ROOT_ELEMENTS = "root_elements";
	/**
	 * ECLASS_NAME Constant to root_elements index name {@link String}
	 */
	public final String ECLASS_NAME = "eClass_name";
	/**
	 * ID_META Constant {@link String}
	 */
	public final String ID_META = "id_meta";
	/**
	 * the Value of the node representing the resource in the backend
	 */
	public final String RESOURCE_NODE = "resource";
	/**
	 * INSTANCE_OF {@link RelationshipType}
	 */
	public final RelationshipType INSTANCE_OF = MetaRelation.INSTANCE_OF;
	/**
	 * IS_ROOT {@link RelationshipType}
	 */
	public final RelationshipType IS_ROOT = MetaRelation.IS_ROOT;

	/**
	 * get the meta_elements' index
	 * 
	 * @return {@link Index}
	 */
	Index<Node> getMetaIndex();

	/**
	 * Create the meta_element node and add it to index if not exists
	 * 
	 * @param c
	 *            {@link EClass}
	 * @return {@link Node}
	 */
	Node createWithIndexIfNotExists(EClass c);

	/**
	 * Create an eObject from node if not exist
	 * 
	 * @param n
	 *            {@link No}
	 * @return {@link EObject}
	 */
	EObject createObjectProxyFromNode(Node n);

	/**
	 * Create Node from eObject
	 * 
	 * @param eObject
	 *            {@link EObject}
	 * @return {@link Node}
	 */
	Node createNodeFromEObject(EObject eObject);

	/**
	 * Return a List of the root nodes
	 * 
	 * @return
	 */

	List<Node> getAllRootNodes();

	/**
	 * Return the nodeType of a Node
	 * 
	 * @param n
	 *            {@link Node}
	 * @return {@link String}
	 */
	String getNodeType(Node n);

	/**
	 * Return the containing package of an node
	 * 
	 * @param n
	 *            {@link Node}
	 * @return {@link String}
	 */
	String getContainingPackage(Node n);

	/**
	 * query the backend with respect to nodeid as start {@link Node} and
	 * relationshipType as the {@link RelationshipType}
	 * 
	 * @param nodeid
	 *            {@link Long}
	 * @param relationshipType
	 *            {@link RelationshipType}
	 * @return {@link List}
	 */
	List<Node> getNodesOnDemand(long nodeid, RelationshipType relationshipType);

	/**
	 * Return true if the node is root Node
	 * 
	 * @param node
	 * @return
	 */
	boolean isRootNode(Node node);

	/**
	 * return a List of nodes of type eClass
	 * 
	 * @param eClass
	 *            {@link EClass}
	 * @return
	 */
	List<Node> getAllNodesOfType(EClass eClass);

	/**
	 * Enum class for the meta_relations
	 * 
	 * @author Amine BENELALLAM
	 * 
	 */

	public enum MetaRelation implements RelationshipType {
		/**
		 * Instance of relationship
		 */
		INSTANCE_OF,
		/**
		 * IS_ROOT relationship
		 */
		IS_ROOT
	}

}
