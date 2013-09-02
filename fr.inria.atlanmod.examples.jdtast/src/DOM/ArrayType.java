/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.ArrayType#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link DOM.ArrayType#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link DOM.ArrayType#getElementType <em>Element Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getArrayType()
 * @model
 * @generated
 */
public interface ArrayType extends Type {

	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Component Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Type</em>' containment reference.
	 * @see #setComponentType(Type)
	 * @see DOM.DOMPackage#getArrayType_ComponentType()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Type getComponentType();
	/**
	 * Sets the value of the '{@link DOM.ArrayType#getComponentType <em>Component Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Type</em>' containment reference.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(Type value);

	/**
	 * Returns the value of the '<em><b>Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Dimensions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dimensions</em>' attribute.
	 * @see #setDimensions(Integer)
	 * @see DOM.DOMPackage#getArrayType_Dimensions()
	 * @model unique="false" dataType="PrimitiveTypes.Integer" required="true" ordered="false"
	 * @generated
	 */
	Integer getDimensions();
	/**
	 * Sets the value of the '{@link DOM.ArrayType#getDimensions <em>Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimensions</em>' attribute.
	 * @see #getDimensions()
	 * @generated
	 */
	void setDimensions(Integer value);

	/**
	 * Returns the value of the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Element Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Type</em>' containment reference.
	 * @see #setElementType(Type)
	 * @see DOM.DOMPackage#getArrayType_ElementType()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Type getElementType();
	/**
	 * Sets the value of the '{@link DOM.ArrayType#getElementType <em>Element Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Type</em>' containment reference.
	 * @see #getElementType()
	 * @generated
	 */
	void setElementType(Type value);




// data Class generation 
} // ArrayType
