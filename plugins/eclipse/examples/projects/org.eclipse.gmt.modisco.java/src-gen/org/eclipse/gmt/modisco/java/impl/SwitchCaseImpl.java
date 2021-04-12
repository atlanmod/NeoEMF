/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.SwitchCase;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Switch Case</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SwitchCaseImpl#isDefault <em>Default</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.SwitchCaseImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SwitchCaseImpl extends StatementImpl implements SwitchCase {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SwitchCaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getSwitchCase();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefault() {
		return (Boolean)eGet(JavaPackage.eINSTANCE.getSwitchCase_Default(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefault(boolean newDefault) {
		eSet(JavaPackage.eINSTANCE.getSwitchCase_Default(), newDefault);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getSwitchCase_Expression(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		eSet(JavaPackage.eINSTANCE.getSwitchCase_Expression(), newExpression);
	}

} //SwitchCaseImpl
