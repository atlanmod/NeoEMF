/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.VariableDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Variable Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SingleVariableAccessImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SingleVariableAccessImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SingleVariableAccessImpl extends ExpressionImpl implements SingleVariableAccess {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleVariableAccessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getSingleVariableAccess();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclaration getVariable() {
		return (VariableDeclaration)eGet(JavaPackage.eINSTANCE.getSingleVariableAccess_Variable(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariable(VariableDeclaration newVariable) {
		eSet(JavaPackage.eINSTANCE.getSingleVariableAccess_Variable(), newVariable);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getQualifier() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getSingleVariableAccess_Qualifier(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualifier(Expression newQualifier) {
		eSet(JavaPackage.eINSTANCE.getSingleVariableAccess_Qualifier(), newQualifier);
	}

} //SingleVariableAccessImpl
