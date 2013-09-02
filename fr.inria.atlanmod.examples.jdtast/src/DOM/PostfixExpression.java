/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Postfix Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.PostfixExpression#getOperand <em>Operand</em>}</li>
 *   <li>{@link DOM.PostfixExpression#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getPostfixExpression()
 * @model
 * @generated
 */
public interface PostfixExpression extends Expression {

	/**
	 * Returns the value of the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' containment reference.
	 * @see #setOperand(Expression)
	 * @see DOM.DOMPackage#getPostfixExpression_Operand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getOperand();
	/**
	 * Sets the value of the '{@link DOM.PostfixExpression#getOperand <em>Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' containment reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(Expression value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link DOM.PostfixExpressionOperatorKind}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see DOM.PostfixExpressionOperatorKind
	 * @see #setOperator(PostfixExpressionOperatorKind)
	 * @see DOM.DOMPackage#getPostfixExpression_Operator()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	PostfixExpressionOperatorKind getOperator();
	/**
	 * Sets the value of the '{@link DOM.PostfixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see DOM.PostfixExpressionOperatorKind
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(PostfixExpressionOperatorKind value);




// data Class generation 
} // PostfixExpression
