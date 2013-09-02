/**
 *
 * $Id$
 */
package DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Prefix Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.PrefixExpression#getOperand <em>Operand</em>}</li>
 *   <li>{@link DOM.PrefixExpression#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getPrefixExpression()
 * @model
 * @generated
 */
public interface PrefixExpression extends Expression {

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
	 * @see DOM.DOMPackage#getPrefixExpression_Operand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getOperand();
	/**
	 * Sets the value of the '{@link DOM.PrefixExpression#getOperand <em>Operand</em>}' containment reference.
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
	 * The literals are from the enumeration {@link DOM.PrefixExpressionOperatorKind}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see DOM.PrefixExpressionOperatorKind
	 * @see #setOperator(PrefixExpressionOperatorKind)
	 * @see DOM.DOMPackage#getPrefixExpression_Operator()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	PrefixExpressionOperatorKind getOperator();
	/**
	 * Sets the value of the '{@link DOM.PrefixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see DOM.PrefixExpressionOperatorKind
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(PrefixExpressionOperatorKind value);




// data Class generation 
} // PrefixExpression
