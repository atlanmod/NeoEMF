/**
 */
package graph;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link graph.Graph#getVertices <em>Vertices</em>}</li>
 *   <li>{@link graph.Graph#getEdges <em>Edges</em>}</li>
 * </ul>
 *
 * @see graph.GraphPackage#getGraph()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface Graph extends PersistentEObject {
	/**
	 * Returns the value of the '<em><b>Vertices</b></em>' containment reference list.
	 * The list contents are of type {@link graph.Vertice}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertices</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertices</em>' containment reference list.
	 * @see graph.GraphPackage#getGraph_Vertices()
	 * @model containment="true"
	 * @generated
	 */
	EList<Vertice> getVertices();

	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference list.
	 * The list contents are of type {@link graph.Edge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edges</em>' containment reference list.
	 * @see graph.GraphPackage#getGraph_Edges()
	 * @model containment="true"
	 * @generated
	 */
	EList<Edge> getEdges();

} // Graph
