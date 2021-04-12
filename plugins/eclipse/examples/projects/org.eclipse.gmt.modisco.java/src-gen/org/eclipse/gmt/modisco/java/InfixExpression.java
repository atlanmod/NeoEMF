/**
 */
package org.eclipse.gmt.modisco.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Infix Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.InfixExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.InfixExpression#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.InfixExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.InfixExpression#getExtendedOperands <em>Extended Operands</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getInfixExpression()
 * @model
 * @generated
 */
public interface InfixExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.gmt.modisco.java.InfixExpressionKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.InfixExpressionKind
	 * @see #setOperator(InfixExpressionKind)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getInfixExpression_Operator()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	InfixExpressionKind getOperator();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.InfixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.InfixExpressionKind
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(InfixExpressionKind value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Operand</em>' containment reference.
	 * @see #setRightOperand(Expression)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getInfixExpression_RightOperand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getRightOperand();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.InfixExpression#getRightOperand <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' containment reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(Expression value);

	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' containment reference.
	 * @see #setLeftOperand(Expression)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getInfixExpression_LeftOperand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getLeftOperand();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.InfixExpression#getLeftOperand <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' containment reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(Expression value);

	/**
	 * Returns the value of the '<em><b>Extended Operands</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Operands</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Operands</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getInfixExpression_ExtendedOperands()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getExtendedOperands();

} // InfixExpression
