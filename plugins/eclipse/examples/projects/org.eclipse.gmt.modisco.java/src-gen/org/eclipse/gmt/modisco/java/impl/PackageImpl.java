/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.PackageAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.PackageImpl#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.PackageImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.PackageImpl#getOwnedPackages <em>Owned Packages</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.PackageImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.PackageImpl#getUsagesInPackageAccess <em>Usages In Package Access</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PackageImpl extends NamedElementImpl implements org.eclipse.gmt.modisco.java.Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<AbstractTypeDeclaration> getOwnedElements() {
		return (EList<AbstractTypeDeclaration>)eGet(JavaPackage.eINSTANCE.getPackage_OwnedElements(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getModel() {
		return (Model)eGet(JavaPackage.eINSTANCE.getPackage_Model(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(Model newModel) {
		eSet(JavaPackage.eINSTANCE.getPackage_Model(), newModel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<org.eclipse.gmt.modisco.java.Package> getOwnedPackages() {
		return (EList<org.eclipse.gmt.modisco.java.Package>)eGet(JavaPackage.eINSTANCE.getPackage_OwnedPackages(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getPackage() {
		return (org.eclipse.gmt.modisco.java.Package)eGet(JavaPackage.eINSTANCE.getPackage_Package(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(org.eclipse.gmt.modisco.java.Package newPackage) {
		eSet(JavaPackage.eINSTANCE.getPackage_Package(), newPackage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<PackageAccess> getUsagesInPackageAccess() {
		return (EList<PackageAccess>)eGet(JavaPackage.eINSTANCE.getPackage_UsagesInPackageAccess(), true);
	}

} //PackageImpl
