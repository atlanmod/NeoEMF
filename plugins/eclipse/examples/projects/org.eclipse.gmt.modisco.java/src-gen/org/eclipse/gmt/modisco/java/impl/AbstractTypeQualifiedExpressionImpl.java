/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractTypeQualifiedExpression;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Type Qualified Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AbstractTypeQualifiedExpressionImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractTypeQualifiedExpressionImpl extends ExpressionImpl implements AbstractTypeQualifiedExpression {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractTypeQualifiedExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAbstractTypeQualifiedExpression();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getQualifier() {
		return (TypeAccess)eGet(JavaPackage.eINSTANCE.getAbstractTypeQualifiedExpression_Qualifier(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualifier(TypeAccess newQualifier) {
		eSet(JavaPackage.eINSTANCE.getAbstractTypeQualifiedExpression_Qualifier(), newQualifier);
	}

} //AbstractTypeQualifiedExpressionImpl
