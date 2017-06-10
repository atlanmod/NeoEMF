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
 * <li>{@link graph.Graph#getVertices <em>Vertices</em>}</li>
 * <li>{@link graph.Graph#getEdges <em>Edges</em>}</li>
 * </ul>
 *
 * @model
 * @extends PersistentEObject
 * @generated
 * @see graph.GraphPackage#getGraph()
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
     *
     * @return the value of the '<em>Vertices</em>' containment reference list.
     *
     * @model containment="true"
     * @generated
     * @see graph.GraphPackage#getGraph_Vertices()
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
     *
     * @return the value of the '<em>Edges</em>' containment reference list.
     *
     * @model containment="true"
     * @generated
     * @see graph.GraphPackage#getGraph_Edges()
     */
    EList<Edge> getEdges();

} // Graph
