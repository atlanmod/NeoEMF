/**
 */
package org.eclipse.gmt.modisco.java.neoemf.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Statement;

import org.eclipse.gmt.modisco.java.neoemf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enhanced For Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neoemf.impl.EnhancedForStatementImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neoemf.impl.EnhancedForStatementImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neoemf.impl.EnhancedForStatementImpl#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnhancedForStatementImpl extends StatementImpl implements EnhancedForStatement {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EnhancedForStatementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JavaPackage.eINSTANCE.getEnhancedForStatement();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Statement getBody() {
        return (Statement)eGet(JavaPackage.eINSTANCE.getEnhancedForStatement_Body(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBody(Statement newBody) {
        eSet(JavaPackage.eINSTANCE.getEnhancedForStatement_Body(), newBody);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getExpression() {
        return (Expression)eGet(JavaPackage.eINSTANCE.getEnhancedForStatement_Expression(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExpression(Expression newExpression) {
        eSet(JavaPackage.eINSTANCE.getEnhancedForStatement_Expression(), newExpression);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SingleVariableDeclaration getParameter() {
        return (SingleVariableDeclaration)eGet(JavaPackage.eINSTANCE.getEnhancedForStatement_Parameter(), true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParameter(SingleVariableDeclaration newParameter) {
        eSet(JavaPackage.eINSTANCE.getEnhancedForStatement_Parameter(), newParameter);
    }

} //EnhancedForStatementImpl
