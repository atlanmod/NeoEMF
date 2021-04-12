/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.WildCardType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wild Card Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.WildCardTypeImpl#isUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.WildCardTypeImpl#getBound <em>Bound</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WildCardTypeImpl extends TypeImpl implements WildCardType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WildCardTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getWildCardType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUpperBound() {
		return (Boolean)eGet(JavaPackage.eINSTANCE.getWildCardType_UpperBound(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperBound(boolean newUpperBound) {
		eSet(JavaPackage.eINSTANCE.getWildCardType_UpperBound(), newUpperBound);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getBound() {
		return (TypeAccess)eGet(JavaPackage.eINSTANCE.getWildCardType_Bound(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBound(TypeAccess newBound) {
		eSet(JavaPackage.eINSTANCE.getWildCardType_Bound(), newBound);
	}

} //WildCardTypeImpl
