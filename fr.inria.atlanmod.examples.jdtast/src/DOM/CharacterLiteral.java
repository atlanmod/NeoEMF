/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Character Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.CharacterLiteral#getCharValue <em>Char Value</em>}</li>
 *   <li>{@link DOM.CharacterLiteral#getEscapedValue <em>Escaped Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getCharacterLiteral()
 * @model
 * @generated
 */
public interface CharacterLiteral extends Expression {

	/**
	 * Returns the value of the '<em><b>Char Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Char Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Char Value</em>' attribute.
	 * @see #setCharValue(String)
	 * @see DOM.DOMPackage#getCharacterLiteral_CharValue()
	 * @model unique="false" dataType="PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getCharValue();
	/**
	 * Sets the value of the '{@link DOM.CharacterLiteral#getCharValue <em>Char Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Char Value</em>' attribute.
	 * @see #getCharValue()
	 * @generated
	 */
	void setCharValue(String value);

	/**
	 * Returns the value of the '<em><b>Escaped Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Escaped Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Escaped Value</em>' attribute.
	 * @see #setEscapedValue(String)
	 * @see DOM.DOMPackage#getCharacterLiteral_EscapedValue()
	 * @model unique="false" dataType="PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getEscapedValue();
	/**
	 * Sets the value of the '{@link DOM.CharacterLiteral#getEscapedValue <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Escaped Value</em>' attribute.
	 * @see #getEscapedValue()
	 * @generated
	 */
	void setEscapedValue(String value);




// data Class generation 
} // CharacterLiteral
