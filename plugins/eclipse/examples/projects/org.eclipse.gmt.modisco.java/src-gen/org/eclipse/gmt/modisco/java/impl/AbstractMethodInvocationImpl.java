/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Method Invocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AbstractMethodInvocationImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AbstractMethodInvocationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AbstractMethodInvocationImpl#getTypeArguments <em>Type Arguments</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractMethodInvocationImpl extends ASTNodeImpl implements AbstractMethodInvocation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractMethodInvocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAbstractMethodInvocation();
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

} //AbstractMethodInvocationImpl
