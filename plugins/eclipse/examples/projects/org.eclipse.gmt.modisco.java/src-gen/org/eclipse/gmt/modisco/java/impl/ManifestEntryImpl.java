/**
 */
package org.eclipse.gmt.modisco.java.impl;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.ManifestAttribute;
import org.eclipse.gmt.modisco.java.ManifestEntry;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Manifest Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ManifestEntryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ManifestEntryImpl#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ManifestEntryImpl extends DefaultPersistentEObject implements ManifestEntry {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ManifestEntryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getManifestEntry();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)eGet(JavaPackage.eINSTANCE.getManifestEntry_Name(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(JavaPackage.eINSTANCE.getManifestEntry_Name(), newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<ManifestAttribute> getAttributes() {
		return (EList<ManifestAttribute>)eGet(JavaPackage.eINSTANCE.getManifestEntry_Attributes(), true);
	}

} //ManifestEntryImpl
