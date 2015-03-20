/**
 */
package org.eclipse.gmt.modisco.java.kyanos.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.SuperFieldAccess;

import org.eclipse.gmt.modisco.java.kyanos.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Super Field Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.kyanos.impl.SuperFieldAccessImpl#getField <em>Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperFieldAccessImpl extends AbstractTypeQualifiedExpressionImpl implements SuperFieldAccess {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SuperFieldAccessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getSuperFieldAccess();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleVariableAccess getField() {
		return (SingleVariableAccess)eGet(JavaPackage.eINSTANCE.getSuperFieldAccess_Field(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setField(SingleVariableAccess newField) {
		eSet(JavaPackage.eINSTANCE.getSuperFieldAccess_Field(), newField);
	}

} //SuperFieldAccessImpl
