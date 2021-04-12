/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.Archive;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Manifest;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Archive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ArchiveImpl#getOriginalFilePath <em>Original File Path</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ArchiveImpl#getClassFiles <em>Class Files</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ArchiveImpl#getManifest <em>Manifest</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArchiveImpl extends NamedElementImpl implements Archive {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArchiveImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getArchive();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOriginalFilePath() {
		return (String)eGet(JavaPackage.eINSTANCE.getArchive_OriginalFilePath(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalFilePath(String newOriginalFilePath) {
		eSet(JavaPackage.eINSTANCE.getArchive_OriginalFilePath(), newOriginalFilePath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<ClassFile> getClassFiles() {
		return (EList<ClassFile>)eGet(JavaPackage.eINSTANCE.getArchive_ClassFiles(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Manifest getManifest() {
		return (Manifest)eGet(JavaPackage.eINSTANCE.getArchive_Manifest(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManifest(Manifest newManifest) {
		eSet(JavaPackage.eINSTANCE.getArchive_Manifest(), newManifest);
	}

} //ArchiveImpl
