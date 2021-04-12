/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.Assignment;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AssignmentImpl#getLeftHandSide <em>Left Hand Side</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AssignmentImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AssignmentImpl#getRightHandSide <em>Right Hand Side</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssignmentImpl extends ExpressionImpl implements Assignment {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssignmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAssignment();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftHandSide() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getAssignment_LeftHandSide(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftHandSide(Expression newLeftHandSide) {
		eSet(JavaPackage.eINSTANCE.getAssignment_LeftHandSide(), newLeftHandSide);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentKind getOperator() {
		return (AssignmentKind)eGet(JavaPackage.eINSTANCE.getAssignment_Operator(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(AssignmentKind newOperator) {
		eSet(JavaPackage.eINSTANCE.getAssignment_Operator(), newOperator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightHandSide() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getAssignment_RightHandSide(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightHandSide(Expression newRightHandSide) {
		eSet(JavaPackage.eINSTANCE.getAssignment_RightHandSide(), newRightHandSide);
	}

} //AssignmentImpl
