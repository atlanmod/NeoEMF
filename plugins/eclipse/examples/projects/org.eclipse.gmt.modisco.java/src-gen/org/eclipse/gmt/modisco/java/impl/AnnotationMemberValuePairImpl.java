/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotation Member Value Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AnnotationMemberValuePairImpl#getMember <em>Member</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.AnnotationMemberValuePairImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnnotationMemberValuePairImpl extends NamedElementImpl implements AnnotationMemberValuePair {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnnotationMemberValuePairImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAnnotationMemberValuePair();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationTypeMemberDeclaration getMember() {
		return (AnnotationTypeMemberDeclaration)eGet(JavaPackage.eINSTANCE.getAnnotationMemberValuePair_Member(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMember(AnnotationTypeMemberDeclaration newMember) {
		eSet(JavaPackage.eINSTANCE.getAnnotationMemberValuePair_Member(), newMember);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getValue() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getAnnotationMemberValuePair_Value(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(Expression newValue) {
		eSet(JavaPackage.eINSTANCE.getAnnotationMemberValuePair_Value(), newValue);
	}

} //AnnotationMemberValuePairImpl
