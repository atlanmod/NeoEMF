/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Qualified Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.QualifiedType#getName <em>Name</em>}</li>
 *   <li>{@link DOM.QualifiedType#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getQualifiedType()
 * @model
 * @generated
 */
public interface QualifiedType extends Type {

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
	 * @see DOM.DOMPackage#getQualifiedType_Name()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	SimpleName getName();
	/**
	 * Sets the value of the '{@link DOM.QualifiedType#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(SimpleName value);

	/**
	 * Returns the value of the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Qualifier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualifier</em>' containment reference.
	 * @see #setQualifier(Type)
	 * @see DOM.DOMPackage#getQualifiedType_Qualifier()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Type getQualifier();
	/**
	 * Sets the value of the '{@link DOM.QualifiedType#getQualifier <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualifier</em>' containment reference.
	 * @see #getQualifier()
	 * @generated
	 */
	void setQualifier(Type value);




// data Class generation 
} // QualifiedType
