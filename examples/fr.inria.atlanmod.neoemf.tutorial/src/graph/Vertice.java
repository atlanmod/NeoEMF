/**
 */

package graph;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertice</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link graph.Vertice#getLabel <em>Label</em>}</li>
 * </ul>
 *
 * @model
 * @extends PersistentEObject
 * @generated
 * @see graph.GraphPackage#getVertice()
 */
public interface Vertice extends PersistentEObject {

    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label</em>' attribute.
     *
     * @model
     * @generated
     * @see #setLabel(String)
     * @see graph.GraphPackage#getVertice_Label()
     */
    String getLabel();

    /**
     * Sets the value of the '{@link graph.Vertice#getLabel <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value the new value of the '<em>Label</em>' attribute.
     *
     * @generated
     * @see #getLabel()
     */
    void setLabel(String value);

} // Vertice
