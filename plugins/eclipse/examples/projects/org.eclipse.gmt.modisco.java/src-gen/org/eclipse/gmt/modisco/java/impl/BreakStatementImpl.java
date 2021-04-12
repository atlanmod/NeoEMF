/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.BreakStatement;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.LabeledStatement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Break Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.BreakStatementImpl#getLabel <em>Label</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BreakStatementImpl extends StatementImpl implements BreakStatement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BreakStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getBreakStatement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabeledStatement getLabel() {
		return (LabeledStatement)eGet(JavaPackage.eINSTANCE.getBreakStatement_Label(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(LabeledStatement newLabel) {
		eSet(JavaPackage.eINSTANCE.getBreakStatement_Label(), newLabel);
	}

} //BreakStatementImpl
