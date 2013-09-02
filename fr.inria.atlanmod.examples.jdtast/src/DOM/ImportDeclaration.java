/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.ImportDeclaration#getOnDemand <em>On Demand</em>}</li>
 *   <li>{@link DOM.ImportDeclaration#getStatic <em>Static</em>}</li>
 *   <li>{@link DOM.ImportDeclaration#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getImportDeclaration()
 * @model
 * @generated
 */
public interface ImportDeclaration extends ASTNode {

	/**
	 * Returns the value of the '<em><b>On Demand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>On Demand</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Demand</em>' attribute.
	 * @see #setOnDemand(Boolean)
	 * @see DOM.DOMPackage#getImportDeclaration_OnDemand()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getOnDemand();
	/**
	 * Sets the value of the '{@link DOM.ImportDeclaration#getOnDemand <em>On Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Demand</em>' attribute.
	 * @see #getOnDemand()
	 * @generated
	 */
	void setOnDemand(Boolean value);

	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(Boolean)
	 * @see DOM.DOMPackage#getImportDeclaration_Static()
	 * @model unique="false" dataType="PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getStatic();
	/**
	 * Sets the value of the '{@link DOM.ImportDeclaration#getStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #getStatic()
	 * @generated
	 */
	void setStatic(Boolean value);

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
	 * @see DOM.DOMPackage#getImportDeclaration_Name()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Name getName();
	/**
	 * Sets the value of the '{@link DOM.ImportDeclaration#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(Name value);




// data Class generation 
} // ImportDeclaration
