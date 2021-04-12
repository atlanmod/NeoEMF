/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.MethodDeclarationImpl#getExtraArrayDimensions <em>Extra Array Dimensions</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.MethodDeclarationImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.MethodDeclarationImpl#getRedefinedMethodDeclaration <em>Redefined Method Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.MethodDeclarationImpl#getRedefinitions <em>Redefinitions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MethodDeclarationImpl extends AbstractMethodDeclarationImpl implements MethodDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getMethodDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getExtraArrayDimensions() {
		return (Integer)eGet(JavaPackage.eINSTANCE.getMethodDeclaration_ExtraArrayDimensions(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtraArrayDimensions(int newExtraArrayDimensions) {
		eSet(JavaPackage.eINSTANCE.getMethodDeclaration_ExtraArrayDimensions(), newExtraArrayDimensions);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getReturnType() {
		return (TypeAccess)eGet(JavaPackage.eINSTANCE.getMethodDeclaration_ReturnType(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(TypeAccess newReturnType) {
		eSet(JavaPackage.eINSTANCE.getMethodDeclaration_ReturnType(), newReturnType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDeclaration getRedefinedMethodDeclaration() {
		return (MethodDeclaration)eGet(JavaPackage.eINSTANCE.getMethodDeclaration_RedefinedMethodDeclaration(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRedefinedMethodDeclaration(MethodDeclaration newRedefinedMethodDeclaration) {
		eSet(JavaPackage.eINSTANCE.getMethodDeclaration_RedefinedMethodDeclaration(), newRedefinedMethodDeclaration);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<MethodDeclaration> getRedefinitions() {
		return (EList<MethodDeclaration>)eGet(JavaPackage.eINSTANCE.getMethodDeclaration_Redefinitions(), true);
	}

} //MethodDeclarationImpl
