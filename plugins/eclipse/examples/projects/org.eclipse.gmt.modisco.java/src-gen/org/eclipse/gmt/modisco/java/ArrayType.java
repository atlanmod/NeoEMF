/**
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ArrayType#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ArrayType#getElementType <em>Element Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getArrayType()
 * @model
 * @generated
 */
public interface ArrayType extends Type {
	/**
	 * Returns the value of the '<em><b>Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dimensions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dimensions</em>' attribute.
	 * @see #setDimensions(int)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getArrayType_Dimensions()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	int getDimensions();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ArrayType#getDimensions <em>Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimensions</em>' attribute.
	 * @see #getDimensions()
	 * @generated
	 */
	void setDimensions(int value);

	/**
	 * Returns the value of the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Type</em>' containment reference.
	 * @see #setElementType(TypeAccess)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getArrayType_ElementType()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	TypeAccess getElementType();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ArrayType#getElementType <em>Element Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Type</em>' containment reference.
	 * @see #getElementType()
	 * @generated
	 */
	void setElementType(TypeAccess value);

} // ArrayType
