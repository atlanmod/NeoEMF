/**
 */
package org.eclipse.gmt.modisco.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.VariableDeclaration#getExtraArrayDimensions <em>Extra Array Dimensions</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.VariableDeclaration#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.VariableDeclaration#getUsageInVariableAccess <em>Usage In Variable Access</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getVariableDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface VariableDeclaration extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Extra Array Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extra Array Dimensions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extra Array Dimensions</em>' attribute.
	 * @see #setExtraArrayDimensions(int)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getVariableDeclaration_ExtraArrayDimensions()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	int getExtraArrayDimensions();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.VariableDeclaration#getExtraArrayDimensions <em>Extra Array Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extra Array Dimensions</em>' attribute.
	 * @see #getExtraArrayDimensions()
	 * @generated
	 */
	void setExtraArrayDimensions(int value);

	/**
	 * Returns the value of the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initializer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initializer</em>' containment reference.
	 * @see #setInitializer(Expression)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getVariableDeclaration_Initializer()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	Expression getInitializer();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.VariableDeclaration#getInitializer <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initializer</em>' containment reference.
	 * @see #getInitializer()
	 * @generated
	 */
	void setInitializer(Expression value);

	/**
	 * Returns the value of the '<em><b>Usage In Variable Access</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.SingleVariableAccess}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.SingleVariableAccess#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usage In Variable Access</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usage In Variable Access</em>' reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getVariableDeclaration_UsageInVariableAccess()
	 * @see org.eclipse.gmt.modisco.java.SingleVariableAccess#getVariable
	 * @model opposite="variable" ordered="false"
	 * @generated
	 */
	EList<SingleVariableAccess> getUsageInVariableAccess();

} // VariableDeclaration
