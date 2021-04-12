/**
 */
package org.eclipse.gmt.modisco.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.VariableDeclarationExpression#getModifier <em>Modifier</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.VariableDeclarationExpression#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getVariableDeclarationExpression()
 * @model
 * @generated
 */
public interface VariableDeclarationExpression extends Expression, AbstractVariablesContainer {
	/**
	 * Returns the value of the '<em><b>Modifier</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.Modifier#getVariableDeclarationExpression <em>Variable Declaration Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modifier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modifier</em>' containment reference.
	 * @see #setModifier(Modifier)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getVariableDeclarationExpression_Modifier()
	 * @see org.eclipse.gmt.modisco.java.Modifier#getVariableDeclarationExpression
	 * @model opposite="variableDeclarationExpression" containment="true" ordered="false"
	 * @generated
	 */
	Modifier getModifier();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.VariableDeclarationExpression#getModifier <em>Modifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modifier</em>' containment reference.
	 * @see #getModifier()
	 * @generated
	 */
	void setModifier(Modifier value);

	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Annotation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getVariableDeclarationExpression_Annotations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Annotation> getAnnotations();

} // VariableDeclarationExpression
