/**
 *
 * $Id$
 */
package Core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IField</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Core.IField#getConstant <em>Constant</em>}</li>
 *   <li>{@link Core.IField#getIsEnumConstant <em>Is Enum Constant</em>}</li>
 *   <li>{@link Core.IField#getTypeSignature <em>Type Signature</em>}</li>
 *   <li>{@link Core.IField#getIsVolatile <em>Is Volatile</em>}</li>
 *   <li>{@link Core.IField#getIsTransient <em>Is Transient</em>}</li>
 * </ul>
 * </p>
 *
 * @see Core.CorePackage#getIField()
 * @model
 * @generated
 */
public interface IField extends IMember {

	/**
	 * Returns the value of the '<em><b>Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Constant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constant</em>' attribute.
	 * @see #setConstant(String)
	 * @see Core.CorePackage#getIField_Constant()
	 * @model unique="false" dataType="PrimitiveTypes.String" ordered="false"
	 * @generated
	 */
	String getConstant();
	/**
	 * Sets the value of the '{@link Core.IField#getConstant <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constant</em>' attribute.
	 * @see #getConstant()
	 * @generated
	 */
	void setConstant(String value);

	/**
	 * Returns the value of the '<em><b>Is Enum Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Is Enum Constant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Enum Constant</em>' attribute.
	 * @see #setIsEnumConstant(Boolean)
	 * @see Core.CorePackage#getIField_IsEnumConstant()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsEnumConstant();
	/**
	 * Sets the value of the '{@link Core.IField#getIsEnumConstant <em>Is Enum Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Enum Constant</em>' attribute.
	 * @see #getIsEnumConstant()
	 * @generated
	 */
	void setIsEnumConstant(Boolean value);

	/**
	 * Returns the value of the '<em><b>Type Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type Signature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Signature</em>' attribute.
	 * @see #setTypeSignature(String)
	 * @see Core.CorePackage#getIField_TypeSignature()
	 * @model unique="false" dataType="PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getTypeSignature();
	/**
	 * Sets the value of the '{@link Core.IField#getTypeSignature <em>Type Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Signature</em>' attribute.
	 * @see #getTypeSignature()
	 * @generated
	 */
	void setTypeSignature(String value);

	/**
	 * Returns the value of the '<em><b>Is Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Is Volatile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Volatile</em>' attribute.
	 * @see #setIsVolatile(Boolean)
	 * @see Core.CorePackage#getIField_IsVolatile()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsVolatile();
	/**
	 * Sets the value of the '{@link Core.IField#getIsVolatile <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Volatile</em>' attribute.
	 * @see #getIsVolatile()
	 * @generated
	 */
	void setIsVolatile(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Is Transient</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Transient</em>' attribute.
	 * @see #setIsTransient(Boolean)
	 * @see Core.CorePackage#getIField_IsTransient()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsTransient();
	/**
	 * Sets the value of the '{@link Core.IField#getIsTransient <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Transient</em>' attribute.
	 * @see #getIsTransient()
	 * @generated
	 */
	void setIsTransient(Boolean value);




// data Class generation 
} // IField
