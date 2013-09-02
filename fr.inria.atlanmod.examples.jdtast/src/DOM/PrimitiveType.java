/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Primitive Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.PrimitiveType#getCode <em>Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getPrimitiveType()
 * @model
 * @generated
 */
public interface PrimitiveType extends Type {

	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see DOM.DOMPackage#getPrimitiveType_Code()
	 * @model unique="false" dataType="PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getCode();
	/**
	 * Sets the value of the '{@link DOM.PrimitiveType#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(String value);




// data Class generation 
} // PrimitiveType
