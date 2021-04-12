/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Invocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.MethodInvocationImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.MethodInvocationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.MethodInvocationImpl#getTypeArguments <em>Type Arguments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.MethodInvocationImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MethodInvocationImpl extends ExpressionImpl implements MethodInvocation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodInvocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getMethodInvocation();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractMethodDeclaration getMethod() {
		return (AbstractMethodDeclaration)eGet(JavaPackage.eINSTANCE.getAbstractMethodInvocation_Method(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethod(AbstractMethodDeclaration newMethod) {
		eSet(JavaPackage.eINSTANCE.getAbstractMethodInvocation_Method(), newMethod);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Expression> getArguments() {
		return (EList<Expression>)eGet(JavaPackage.eINSTANCE.getAbstractMethodInvocation_Arguments(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TypeAccess> getTypeArguments() {
		return (EList<TypeAccess>)eGet(JavaPackage.eINSTANCE.getAbstractMethodInvocation_TypeArguments(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getMethodInvocation_Expression(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		eSet(JavaPackage.eINSTANCE.getMethodInvocation_Expression(), newExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractMethodInvocation.class) {
			switch (derivedFeatureID) {
				case JavaPackage.METHOD_INVOCATION__METHOD: return JavaPackage.ABSTRACT_METHOD_INVOCATION__METHOD;
				case JavaPackage.METHOD_INVOCATION__ARGUMENTS: return JavaPackage.ABSTRACT_METHOD_INVOCATION__ARGUMENTS;
				case JavaPackage.METHOD_INVOCATION__TYPE_ARGUMENTS: return JavaPackage.ABSTRACT_METHOD_INVOCATION__TYPE_ARGUMENTS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractMethodInvocation.class) {
			switch (baseFeatureID) {
				case JavaPackage.ABSTRACT_METHOD_INVOCATION__METHOD: return JavaPackage.METHOD_INVOCATION__METHOD;
				case JavaPackage.ABSTRACT_METHOD_INVOCATION__ARGUMENTS: return JavaPackage.METHOD_INVOCATION__ARGUMENTS;
				case JavaPackage.ABSTRACT_METHOD_INVOCATION__TYPE_ARGUMENTS: return JavaPackage.METHOD_INVOCATION__TYPE_ARGUMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //MethodInvocationImpl
