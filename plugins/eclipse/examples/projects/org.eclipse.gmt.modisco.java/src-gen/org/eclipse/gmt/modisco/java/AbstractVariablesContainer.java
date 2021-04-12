/**
 */
package org.eclipse.gmt.modisco.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Variables Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.AbstractVariablesContainer#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.AbstractVariablesContainer#getFragments <em>Fragments</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAbstractVariablesContainer()
 * @model abstract="true"
 * @generated
 */
public interface AbstractVariablesContainer extends ASTNode {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(TypeAccess)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAbstractVariablesContainer_Type()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	TypeAccess getType();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.AbstractVariablesContainer#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeAccess value);

	/**
	 * Returns the value of the '<em><b>Fragments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.VariableDeclarationFragment}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.VariableDeclarationFragment#getVariablesContainer <em>Variables Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragments</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAbstractVariablesContainer_Fragments()
	 * @see org.eclipse.gmt.modisco.java.VariableDeclarationFragment#getVariablesContainer
	 * @model opposite="variablesContainer" containment="true"
	 * @generated
	 */
	EList<VariableDeclarationFragment> getFragments();

} // AbstractVariablesContainer
