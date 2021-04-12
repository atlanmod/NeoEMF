/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Anonymous Class Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AnonymousClassDeclarationImpl#getBodyDeclarations <em>Body Declarations</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AnonymousClassDeclarationImpl#getClassInstanceCreation <em>Class Instance Creation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnonymousClassDeclarationImpl extends ASTNodeImpl implements AnonymousClassDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnonymousClassDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAnonymousClassDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<BodyDeclaration> getBodyDeclarations() {
		return (EList<BodyDeclaration>)eGet(JavaPackage.eINSTANCE.getAnonymousClassDeclaration_BodyDeclarations(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassInstanceCreation getClassInstanceCreation() {
		return (ClassInstanceCreation)eGet(JavaPackage.eINSTANCE.getAnonymousClassDeclaration_ClassInstanceCreation(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassInstanceCreation(ClassInstanceCreation newClassInstanceCreation) {
		eSet(JavaPackage.eINSTANCE.getAnonymousClassDeclaration_ClassInstanceCreation(), newClassInstanceCreation);
	}

} //AnonymousClassDeclarationImpl
