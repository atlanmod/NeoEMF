/**
 */

package graph;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link graph.Edge#getFrom <em>From</em>}</li>
 * <li>{@link graph.Edge#getTo <em>To</em>}</li>
 * </ul>
 *
 * @model
 * @extends PersistentEObject
 * @generated
 * @see graph.GraphPackage#getEdge()
 */
public interface Edge extends PersistentEObject {

    /**
     * Returns the value of the '<em><b>From</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>From</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>From</em>' reference.
     *
     * @model
     * @generated
     * @see #setFrom(Vertice)
     * @see graph.GraphPackage#getEdge_From()
     */
    Vertice getFrom();

    /**
     * Sets the value of the '{@link graph.Edge#getFrom <em>From</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value the new value of the '<em>From</em>' reference.
     *
     * @generated
     * @see #getFrom()
     */
    void setFrom(Vertice value);

    /**
     * Returns the value of the '<em><b>To</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>To</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>To</em>' reference.
     *
     * @model
     * @generated
     * @see #setTo(Vertice)
     * @see graph.GraphPackage#getEdge_To()
     */
    Vertice getTo();

    /**
     * Sets the value of the '{@link graph.Edge#getTo <em>To</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value the new value of the '<em>To</em>' reference.
     *
     * @generated
     * @see #getTo()
     */
    void setTo(Vertice value);

} // Edge
