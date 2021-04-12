/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.InstanceofExpression;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instanceof Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.InstanceofExpressionImpl#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.InstanceofExpressionImpl#getLeftOperand <em>Left Operand</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InstanceofExpressionImpl extends ExpressionImpl implements InstanceofExpression {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanceofExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getInstanceofExpression();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getRightOperand() {
		return (TypeAccess)eGet(JavaPackage.eINSTANCE.getInstanceofExpression_RightOperand(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightOperand(TypeAccess newRightOperand) {
		eSet(JavaPackage.eINSTANCE.getInstanceofExpression_RightOperand(), newRightOperand);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftOperand() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getInstanceofExpression_LeftOperand(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftOperand(Expression newLeftOperand) {
		eSet(JavaPackage.eINSTANCE.getInstanceofExpression_LeftOperand(), newLeftOperand);
	}

} //InstanceofExpressionImpl
