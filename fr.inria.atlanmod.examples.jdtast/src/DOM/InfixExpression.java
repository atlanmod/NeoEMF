/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Infix Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DOM.InfixExpression#getExtendedOperands <em>Extended Operands</em>}</li>
 *   <li>{@link DOM.InfixExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link DOM.InfixExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link DOM.InfixExpression#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @see DOM.DOMPackage#getInfixExpression()
 * @model
 * @generated
 */
public interface InfixExpression extends Expression {

	/**
	 * Returns the value of the '<em><b>Extended Operands</b></em>' containment reference list.
	 * The list contents are of type {@link DOM.Expression}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Extended Operands</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Operands</em>' containment reference list.
	 * @see DOM.DOMPackage#getInfixExpression_ExtendedOperands()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getExtendedOperands();
	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' containment reference.
	 * @see #setLeftOperand(Expression)
	 * @see DOM.DOMPackage#getInfixExpression_LeftOperand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getLeftOperand();
	/**
	 * Sets the value of the '{@link DOM.InfixExpression#getLeftOperand <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' containment reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(Expression value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link DOM.InfixExpressionOperatorKind}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see DOM.InfixExpressionOperatorKind
	 * @see #setOperator(InfixExpressionOperatorKind)
	 * @see DOM.DOMPackage#getInfixExpression_Operator()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	InfixExpressionOperatorKind getOperator();
	/**
	 * Sets the value of the '{@link DOM.InfixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see DOM.InfixExpressionOperatorKind
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(InfixExpressionOperatorKind value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Operand</em>' containment reference.
	 * @see #setRightOperand(Expression)
	 * @see DOM.DOMPackage#getInfixExpression_RightOperand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getRightOperand();
	/**
	 * Sets the value of the '{@link DOM.InfixExpression#getRightOperand <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' containment reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(Expression value);




// data Class generation 
} // InfixExpression
