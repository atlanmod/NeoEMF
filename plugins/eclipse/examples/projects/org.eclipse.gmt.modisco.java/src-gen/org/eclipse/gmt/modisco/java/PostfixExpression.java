/**
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Postfix Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.PostfixExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.PostfixExpression#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getPostfixExpression()
 * @model
 * @generated
 */
public interface PostfixExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.gmt.modisco.java.PostfixExpressionKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.PostfixExpressionKind
	 * @see #setOperator(PostfixExpressionKind)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getPostfixExpression_Operator()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	PostfixExpressionKind getOperator();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.PostfixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.PostfixExpressionKind
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(PostfixExpressionKind value);

	/**
	 * Returns the value of the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' containment reference.
	 * @see #setOperand(Expression)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getPostfixExpression_Operand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getOperand();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.PostfixExpression#getOperand <em>Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' containment reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(Expression value);

} // PostfixExpression
