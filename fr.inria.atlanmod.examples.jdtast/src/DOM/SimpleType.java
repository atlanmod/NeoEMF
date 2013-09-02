/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.SimpleType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getSimpleType()
 * @model
 * @generated
 */
public interface SimpleType extends Type {

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
	 * @see #setName(Name)
	 * @see DOM.DOMPackage#getSimpleType_Name()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Name getName();
	/**
	 * Sets the value of the '{@link DOM.SimpleType#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(Name value);




// data Class generation 
} // SimpleType
