/**
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Super Field Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.SuperFieldAccess#getField <em>Field</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getSuperFieldAccess()
 * @model
 * @generated
 */
public interface SuperFieldAccess extends AbstractTypeQualifiedExpression {
	/**
	 * Returns the value of the '<em><b>Field</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field</em>' containment reference.
	 * @see #setField(SingleVariableAccess)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getSuperFieldAccess_Field()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	SingleVariableAccess getField();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.SuperFieldAccess#getField <em>Field</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field</em>' containment reference.
	 * @see #getField()
	 * @generated
	 */
	void setField(SingleVariableAccess value);

} // SuperFieldAccess
