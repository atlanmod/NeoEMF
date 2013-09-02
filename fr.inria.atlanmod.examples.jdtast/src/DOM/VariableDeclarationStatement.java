/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.VariableDeclarationStatement#getFragments <em>Fragments</em>}</li>
 *   <li>{@link DOM.VariableDeclarationStatement#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link DOM.VariableDeclarationStatement#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getVariableDeclarationStatement()
 * @model
 * @generated
 */
public interface VariableDeclarationStatement extends Statement {

	/**
	 * Returns the value of the '<em><b>Fragments</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.VariableDeclarationFragment}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Fragments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragments</em>' containment reference list.
	 * @see DOM.DOMPackage#getVariableDeclarationStatement_Fragments()
	 * @model containment="true"
	 * @generated
	 */
	EList<VariableDeclarationFragment> getFragments();
	/**
	 * Returns the value of the '<em><b>Modifiers</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.ExtendedModifier}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Modifiers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modifiers</em>' containment reference list.
	 * @see DOM.DOMPackage#getVariableDeclarationStatement_Modifiers()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExtendedModifier> getModifiers();
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
	 * @see #setType(Type)
	 * @see DOM.DOMPackage#getVariableDeclarationStatement_Type()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Type getType();
	/**
	 * Sets the value of the '{@link DOM.VariableDeclarationStatement#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);




// data Class generation 
} // VariableDeclarationStatement
