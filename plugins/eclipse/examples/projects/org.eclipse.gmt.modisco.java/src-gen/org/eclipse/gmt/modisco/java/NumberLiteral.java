/**
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Number Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.NumberLiteral#getTokenValue <em>Token Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getNumberLiteral()
 * @model
 * @generated
 */
public interface NumberLiteral extends Expression {
	/**
	 * Returns the value of the '<em><b>Token Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Token Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Token Value</em>' attribute.
	 * @see #setTokenValue(String)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getNumberLiteral_TokenValue()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getTokenValue();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.NumberLiteral#getTokenValue <em>Token Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Token Value</em>' attribute.
	 * @see #getTokenValue()
	 * @generated
	 */
	void setTokenValue(String value);

} // NumberLiteral
