/**
 *
 * $Id$
 */
package Core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IImport Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Core.IImportDeclaration#getIsOnDemand <em>Is On Demand</em>}</li>
 *   <li>{@link Core.IImportDeclaration#getIsStatic <em>Is Static</em>}</li>
 * </ul>
 * </p>
 *
 * @see Core.CorePackage#getIImportDeclaration()
 * @model
 * @generated
 */
public interface IImportDeclaration extends IJavaElement, ISourceReference {

	/**
	 * Returns the value of the '<em><b>Is On Demand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Is On Demand</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is On Demand</em>' attribute.
	 * @see #setIsOnDemand(Boolean)
	 * @see Core.CorePackage#getIImportDeclaration_IsOnDemand()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsOnDemand();
	/**
	 * Sets the value of the '{@link Core.IImportDeclaration#getIsOnDemand <em>Is On Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is On Demand</em>' attribute.
	 * @see #getIsOnDemand()
	 * @generated
	 */
	void setIsOnDemand(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Is Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Static</em>' attribute.
	 * @see #setIsStatic(Boolean)
	 * @see Core.CorePackage#getIImportDeclaration_IsStatic()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsStatic();
	/**
	 * Sets the value of the '{@link Core.IImportDeclaration#getIsStatic <em>Is Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Static</em>' attribute.
	 * @see #getIsStatic()
	 * @generated
	 */
	void setIsStatic(Boolean value);




// data Class generation 
} // IImportDeclaration
