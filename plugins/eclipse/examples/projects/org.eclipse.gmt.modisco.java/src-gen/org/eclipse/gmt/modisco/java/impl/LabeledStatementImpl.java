/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.BreakStatement;
import org.eclipse.gmt.modisco.java.ContinueStatement;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.LabeledStatement;
import org.eclipse.gmt.modisco.java.Statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Labeled Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.LabeledStatementImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.LabeledStatementImpl#getUsagesInBreakStatements <em>Usages In Break Statements</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.LabeledStatementImpl#getUsagesInContinueStatements <em>Usages In Continue Statements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LabeledStatementImpl extends NamedElementImpl implements LabeledStatement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LabeledStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getLabeledStatement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getBody() {
		return (Statement)eGet(JavaPackage.eINSTANCE.getLabeledStatement_Body(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(Statement newBody) {
		eSet(JavaPackage.eINSTANCE.getLabeledStatement_Body(), newBody);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<BreakStatement> getUsagesInBreakStatements() {
		return (EList<BreakStatement>)eGet(JavaPackage.eINSTANCE.getLabeledStatement_UsagesInBreakStatements(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<ContinueStatement> getUsagesInContinueStatements() {
		return (EList<ContinueStatement>)eGet(JavaPackage.eINSTANCE.getLabeledStatement_UsagesInContinueStatements(), true);
	}

} //LabeledStatementImpl
