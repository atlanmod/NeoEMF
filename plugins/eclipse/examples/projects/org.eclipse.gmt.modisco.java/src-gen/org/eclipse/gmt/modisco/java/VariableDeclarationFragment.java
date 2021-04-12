/**
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.VariableDeclarationFragment#getVariablesContainer <em>Variables Container</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getVariableDeclarationFragment()
 * @model
 * @generated
 */
public interface VariableDeclarationFragment extends VariableDeclaration {
	/**
	 * Returns the value of the '<em><b>Variables Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.AbstractVariablesContainer#getFragments <em>Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables Container</em>' container reference.
	 * @see #setVariablesContainer(AbstractVariablesContainer)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getVariableDeclarationFragment_VariablesContainer()
	 * @see org.eclipse.gmt.modisco.java.AbstractVariablesContainer#getFragments
	 * @model opposite="fragments" transient="false" ordered="false"
	 * @generated
	 */
	AbstractVariablesContainer getVariablesContainer();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.VariableDeclarationFragment#getVariablesContainer <em>Variables Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variables Container</em>' container reference.
	 * @see #getVariablesContainer()
	 * @generated
	 */
	void setVariablesContainer(AbstractVariablesContainer value);

} // VariableDeclarationFragment
