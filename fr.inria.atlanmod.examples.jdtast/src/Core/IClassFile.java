/**
 *
 * $Id$
 */
package Core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IClass File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Core.IClassFile#getIsClass <em>Is Class</em>}</li>
 *   <li>{@link Core.IClassFile#getIsInterface <em>Is Interface</em>}</li>
 *   <li>{@link Core.IClassFile#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see Core.CorePackage#getIClassFile()
 * @model
 * @generated
 */
public interface IClassFile extends ITypeRoot {

	/**
	 * Returns the value of the '<em><b>Is Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Is Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Class</em>' attribute.
	 * @see #setIsClass(Boolean)
	 * @see Core.CorePackage#getIClassFile_IsClass()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsClass();
	/**
	 * Sets the value of the '{@link Core.IClassFile#getIsClass <em>Is Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Class</em>' attribute.
	 * @see #getIsClass()
	 * @generated
	 */
	void setIsClass(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Is Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Interface</em>' attribute.
	 * @see #setIsInterface(Boolean)
	 * @see Core.CorePackage#getIClassFile_IsInterface()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsInterface();
	/**
	 * Sets the value of the '{@link Core.IClassFile#getIsInterface <em>Is Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Interface</em>' attribute.
	 * @see #getIsInterface()
	 * @generated
	 */
	void setIsInterface(Boolean value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(IType)
	 * @see Core.CorePackage#getIClassFile_Type()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	IType getType();
	/**
	 * Sets the value of the '{@link Core.IClassFile#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(IType value);




// data Class generation 
} // IClassFile
