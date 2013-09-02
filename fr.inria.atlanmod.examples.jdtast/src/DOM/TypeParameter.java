/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.TypeParameter#getName <em>Name</em>}</li>
 *   <li>{@link DOM.TypeParameter#getTypeBounds <em>Type Bounds</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getTypeParameter()
 * @model
 * @generated
 */
public interface TypeParameter extends ASTNode {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(SimpleName)
	 * @see DOM.DOMPackage#getTypeParameter_Name()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	SimpleName getName();
	/**
	 * Sets the value of the '{@link DOM.TypeParameter#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(SimpleName value);

	/**
	 * Returns the value of the '<em><b>Type Bounds</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.Type}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type Bounds</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Bounds</em>' containment reference list.
	 * @see DOM.DOMPackage#getTypeParameter_TypeBounds()
	 * @model containment="true"
	 * @generated
	 */
	EList<Type> getTypeBounds();



// data Class generation 
} // TypeParameter
