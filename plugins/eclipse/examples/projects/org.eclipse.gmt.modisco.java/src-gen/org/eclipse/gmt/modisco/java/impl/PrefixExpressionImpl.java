/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.PrefixExpression;
import org.eclipse.gmt.modisco.java.PrefixExpressionKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Prefix Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.PrefixExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.PrefixExpressionImpl#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrefixExpressionImpl extends ExpressionImpl implements PrefixExpression {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrefixExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getPrefixExpression();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrefixExpressionKind getOperator() {
		return (PrefixExpressionKind)eGet(JavaPackage.eINSTANCE.getPrefixExpression_Operator(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(PrefixExpressionKind newOperator) {
		eSet(JavaPackage.eINSTANCE.getPrefixExpression_Operator(), newOperator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getOperand() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getPrefixExpression_Operand(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperand(Expression newOperand) {
		eSet(JavaPackage.eINSTANCE.getPrefixExpression_Operand(), newOperand);
	}

} //PrefixExpressionImpl
