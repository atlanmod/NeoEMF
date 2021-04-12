/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.PackageAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.PackageAccessImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.PackageAccessImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PackageAccessImpl extends NamespaceAccessImpl implements PackageAccess {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageAccessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getPackageAccess();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getPackage() {
		return (org.eclipse.gmt.modisco.java.Package)eGet(JavaPackage.eINSTANCE.getPackageAccess_Package(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(org.eclipse.gmt.modisco.java.Package newPackage) {
		eSet(JavaPackage.eINSTANCE.getPackageAccess_Package(), newPackage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageAccess getQualifier() {
		return (PackageAccess)eGet(JavaPackage.eINSTANCE.getPackageAccess_Qualifier(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualifier(PackageAccess newQualifier) {
		eSet(JavaPackage.eINSTANCE.getPackageAccess_Qualifier(), newQualifier);
	}

} //PackageAccessImpl
