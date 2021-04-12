/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotation Type Member Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AnnotationTypeMemberDeclarationImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AnnotationTypeMemberDeclarationImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AnnotationTypeMemberDeclarationImpl#getUsages <em>Usages</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnnotationTypeMemberDeclarationImpl extends BodyDeclarationImpl implements AnnotationTypeMemberDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnnotationTypeMemberDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getDefault() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefault(Expression newDefault) {
		eSet(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(), newDefault);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getType() {
		return (TypeAccess)eGet(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Type(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeAccess newType) {
		eSet(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Type(), newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<AnnotationMemberValuePair> getUsages() {
		return (EList<AnnotationMemberValuePair>)eGet(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Usages(), true);
	}

} //AnnotationTypeMemberDeclarationImpl
