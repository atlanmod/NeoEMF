/**
 */
package graph.impl;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;

import graph.Edge;
import graph.Graph;
import graph.GraphPackage;
import graph.Vertice;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link graph.impl.GraphImpl#getVertices <em>Vertices</em>}</li>
 *   <li>{@link graph.impl.GraphImpl#getEdges <em>Edges</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GraphImpl extends DefaultPersistentEObject implements Graph {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphPackage.Literals.GRAPH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Vertice> getVertices() {
		return (EList<Vertice>)eGet(GraphPackage.Literals.GRAPH__VERTICES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Edge> getEdges() {
		return (EList<Edge>)eGet(GraphPackage.Literals.GRAPH__EDGES, true);
	}

} //GraphImpl
