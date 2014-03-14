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
**/
package mgraph;

import fr.inria.atlanmod.neo4emf.PersistentPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * NEO4EMF WAS HERE
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see mgraph.MgraphFactory
 * @model kind="package"
 * @generated
 */
public interface MgraphPackage extends PersistentPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "mgraph";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://mgraph/1.1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mgraphs";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MgraphPackage eINSTANCE = mgraph.impl.MgraphPackageImpl.init();

	/**
	 * The meta object id for the '{@link mgraph.impl.MGraphImpl <em>MGraph</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mgraph.impl.MGraphImpl
	 * @see mgraph.impl.MgraphPackageImpl#getMGraph()
	 * @generated
	 */
	int MGRAPH = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MGRAPH__NAME = 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MGRAPH__NODES = 1;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MGRAPH__EDGES = 2;

	/**
	 * The number of structural features of the '<em>MGraph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MGRAPH_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>MGraph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MGRAPH_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link mgraph.impl.MEdgeImpl <em>MEdge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mgraph.impl.MEdgeImpl
	 * @see mgraph.impl.MgraphPackageImpl#getMEdge()
	 * @generated
	 */
	int MEDGE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDGE__NAME = 0;

	/**
	 * The feature id for the '<em><b>In Coming</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDGE__IN_COMING = 1;

	/**
	 * The feature id for the '<em><b>Out Going</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDGE__OUT_GOING = 2;

	/**
	 * The feature id for the '<em><b>Graph</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDGE__GRAPH = 3;

	/**
	 * The number of structural features of the '<em>MEdge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDGE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>MEdge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link mgraph.impl.MNodeImpl <em>MNode</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mgraph.impl.MNodeImpl
	 * @see mgraph.impl.MgraphPackageImpl#getMNode()
	 * @generated
	 */
	int MNODE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MNODE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Graph</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MNODE__GRAPH = 1;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MNODE__FROM = 2;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MNODE__TO = 3;

	/**
	 * The number of structural features of the '<em>MNode</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MNODE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>MNode</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MNODE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link mgraph.MGraph <em>MGraph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MGraph</em>'.
	 * @see mgraph.MGraph
	 * @generated
	 */
	EClass getMGraph();

	/**
	 * Returns the meta object for the attribute '{@link mgraph.MGraph#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see mgraph.MGraph#getName()
	 * @see #getMGraph()
	 * @generated
	 */
	EAttribute getMGraph_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link mgraph.MGraph#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see mgraph.MGraph#getNodes()
	 * @see #getMGraph()
	 * @generated
	 */
	EReference getMGraph_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link mgraph.MGraph#getEdges <em>Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Edges</em>'.
	 * @see mgraph.MGraph#getEdges()
	 * @see #getMGraph()
	 * @generated
	 */
	EReference getMGraph_Edges();

	/**
	 * Returns the meta object for class '{@link mgraph.MEdge <em>MEdge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MEdge</em>'.
	 * @see mgraph.MEdge
	 * @generated
	 */
	EClass getMEdge();

	/**
	 * Returns the meta object for the attribute '{@link mgraph.MEdge#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see mgraph.MEdge#getName()
	 * @see #getMEdge()
	 * @generated
	 */
	EAttribute getMEdge_Name();

	/**
	 * Returns the meta object for the reference '{@link mgraph.MEdge#getInComing <em>In Coming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>In Coming</em>'.
	 * @see mgraph.MEdge#getInComing()
	 * @see #getMEdge()
	 * @generated
	 */
	EReference getMEdge_InComing();

	/**
	 * Returns the meta object for the reference '{@link mgraph.MEdge#getOutGoing <em>Out Going</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Out Going</em>'.
	 * @see mgraph.MEdge#getOutGoing()
	 * @see #getMEdge()
	 * @generated
	 */
	EReference getMEdge_OutGoing();

	/**
	 * Returns the meta object for the container reference '{@link mgraph.MEdge#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Graph</em>'.
	 * @see mgraph.MEdge#getGraph()
	 * @see #getMEdge()
	 * @generated
	 */
	EReference getMEdge_Graph();

	/**
	 * Returns the meta object for class '{@link mgraph.MNode <em>MNode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MNode</em>'.
	 * @see mgraph.MNode
	 * @generated
	 */
	EClass getMNode();

	/**
	 * Returns the meta object for the attribute '{@link mgraph.MNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see mgraph.MNode#getName()
	 * @see #getMNode()
	 * @generated
	 */
	EAttribute getMNode_Name();

	/**
	 * Returns the meta object for the container reference '{@link mgraph.MNode#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Graph</em>'.
	 * @see mgraph.MNode#getGraph()
	 * @see #getMNode()
	 * @generated
	 */
	EReference getMNode_Graph();

	/**
	 * Returns the meta object for the reference list '{@link mgraph.MNode#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>From</em>'.
	 * @see mgraph.MNode#getFrom()
	 * @see #getMNode()
	 * @generated
	 */
	EReference getMNode_From();

	/**
	 * Returns the meta object for the reference list '{@link mgraph.MNode#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>To</em>'.
	 * @see mgraph.MNode#getTo()
	 * @see #getMNode()
	 * @generated
	 */
	EReference getMNode_To();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MgraphFactory getMgraphFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link mgraph.impl.MGraphImpl <em>MGraph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mgraph.impl.MGraphImpl
		 * @see mgraph.impl.MgraphPackageImpl#getMGraph()
		 * @generated
		 */
		EClass MGRAPH = eINSTANCE.getMGraph();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MGRAPH__NAME = eINSTANCE.getMGraph_Name();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MGRAPH__NODES = eINSTANCE.getMGraph_Nodes();

		/**
		 * The meta object literal for the '<em><b>Edges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MGRAPH__EDGES = eINSTANCE.getMGraph_Edges();

		/**
		 * The meta object literal for the '{@link mgraph.impl.MEdgeImpl <em>MEdge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mgraph.impl.MEdgeImpl
		 * @see mgraph.impl.MgraphPackageImpl#getMEdge()
		 * @generated
		 */
		EClass MEDGE = eINSTANCE.getMEdge();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEDGE__NAME = eINSTANCE.getMEdge_Name();

		/**
		 * The meta object literal for the '<em><b>In Coming</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEDGE__IN_COMING = eINSTANCE.getMEdge_InComing();

		/**
		 * The meta object literal for the '<em><b>Out Going</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEDGE__OUT_GOING = eINSTANCE.getMEdge_OutGoing();

		/**
		 * The meta object literal for the '<em><b>Graph</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEDGE__GRAPH = eINSTANCE.getMEdge_Graph();

		/**
		 * The meta object literal for the '{@link mgraph.impl.MNodeImpl <em>MNode</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mgraph.impl.MNodeImpl
		 * @see mgraph.impl.MgraphPackageImpl#getMNode()
		 * @generated
		 */
		EClass MNODE = eINSTANCE.getMNode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MNODE__NAME = eINSTANCE.getMNode_Name();

		/**
		 * The meta object literal for the '<em><b>Graph</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MNODE__GRAPH = eINSTANCE.getMNode_Graph();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MNODE__FROM = eINSTANCE.getMNode_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MNODE__TO = eINSTANCE.getMNode_To();

	}

} //MgraphPackage
