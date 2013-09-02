/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.TypeLiteral#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getTypeLiteral()
 * @model
 * @generated
 */
public interface TypeLiteral extends Expression {

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(Type)
	 * @see DOM.DOMPackage#getTypeLiteral_Type()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Type getType();
	/**
	 * Sets the value of the '{@link DOM.TypeLiteral#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);




// data Class generation 
} // TypeLiteral
