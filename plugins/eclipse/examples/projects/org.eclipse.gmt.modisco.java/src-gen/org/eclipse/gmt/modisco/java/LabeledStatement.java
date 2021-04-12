/**
 */
package org.eclipse.gmt.modisco.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Labeled Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.LabeledStatement#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.LabeledStatement#getUsagesInBreakStatements <em>Usages In Break Statements</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.LabeledStatement#getUsagesInContinueStatements <em>Usages In Continue Statements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getLabeledStatement()
 * @model
 * @generated
 */
public interface LabeledStatement extends NamedElement, Statement {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(Statement)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getLabeledStatement_Body()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Statement getBody();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.LabeledStatement#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(Statement value);

	/**
	 * Returns the value of the '<em><b>Usages In Break Statements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.BreakStatement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.BreakStatement#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usages In Break Statements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usages In Break Statements</em>' reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getLabeledStatement_UsagesInBreakStatements()
	 * @see org.eclipse.gmt.modisco.java.BreakStatement#getLabel
	 * @model opposite="label" ordered="false"
	 * @generated
	 */
	EList<BreakStatement> getUsagesInBreakStatements();

	/**
	 * Returns the value of the '<em><b>Usages In Continue Statements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ContinueStatement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.ContinueStatement#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usages In Continue Statements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usages In Continue Statements</em>' reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getLabeledStatement_UsagesInContinueStatements()
	 * @see org.eclipse.gmt.modisco.java.ContinueStatement#getLabel
	 * @model opposite="label" ordered="false"
	 * @generated
	 */
	EList<ContinueStatement> getUsagesInContinueStatements();

} // LabeledStatement
