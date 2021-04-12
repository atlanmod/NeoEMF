/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.WhileStatement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>While Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.WhileStatementImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.WhileStatementImpl#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WhileStatementImpl extends StatementImpl implements WhileStatement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WhileStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getWhileStatement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getWhileStatement_Expression(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		eSet(JavaPackage.eINSTANCE.getWhileStatement_Expression(), newExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getBody() {
		return (Statement)eGet(JavaPackage.eINSTANCE.getWhileStatement_Body(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(Statement newBody) {
		eSet(JavaPackage.eINSTANCE.getWhileStatement_Body(), newBody);
	}

} //WhileStatementImpl
