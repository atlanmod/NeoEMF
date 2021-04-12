/**
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.TypeAccess#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.TypeAccess#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getTypeAccess()
 * @model
 * @generated
 */
public interface TypeAccess extends Expression, NamespaceAccess {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.Type#getUsagesInTypeAccess <em>Usages In Type Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Type)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getTypeAccess_Type()
	 * @see org.eclipse.gmt.modisco.java.Type#getUsagesInTypeAccess
	 * @model opposite="usagesInTypeAccess" required="true" ordered="false"
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.TypeAccess#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);

	/**
	 * Returns the value of the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualifier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualifier</em>' containment reference.
	 * @see #setQualifier(NamespaceAccess)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getTypeAccess_Qualifier()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	NamespaceAccess getQualifier();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.TypeAccess#getQualifier <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualifier</em>' containment reference.
	 * @see #getQualifier()
	 * @generated
	 */
	void setQualifier(NamespaceAccess value);

} // TypeAccess
