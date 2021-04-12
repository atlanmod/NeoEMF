/**
 */
package org.eclipse.gmt.modisco.java.impl;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.Archive;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.UnresolvedItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ModelImpl#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ModelImpl#getOrphanTypes <em>Orphan Types</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ModelImpl#getUnresolvedItems <em>Unresolved Items</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ModelImpl#getCompilationUnits <em>Compilation Units</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ModelImpl#getClassFiles <em>Class Files</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ModelImpl#getArchives <em>Archives</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelImpl extends DefaultPersistentEObject implements Model {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getModel();
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
		return (String)eGet(JavaPackage.eINSTANCE.getModel_Name(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(JavaPackage.eINSTANCE.getModel_Name(), newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<org.eclipse.gmt.modisco.java.Package> getOwnedElements() {
		return (EList<org.eclipse.gmt.modisco.java.Package>)eGet(JavaPackage.eINSTANCE.getModel_OwnedElements(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Type> getOrphanTypes() {
		return (EList<Type>)eGet(JavaPackage.eINSTANCE.getModel_OrphanTypes(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<UnresolvedItem> getUnresolvedItems() {
		return (EList<UnresolvedItem>)eGet(JavaPackage.eINSTANCE.getModel_UnresolvedItems(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<CompilationUnit> getCompilationUnits() {
		return (EList<CompilationUnit>)eGet(JavaPackage.eINSTANCE.getModel_CompilationUnits(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<ClassFile> getClassFiles() {
		return (EList<ClassFile>)eGet(JavaPackage.eINSTANCE.getModel_ClassFiles(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Archive> getArchives() {
		return (EList<Archive>)eGet(JavaPackage.eINSTANCE.getModel_Archives(), true);
	}

} //ModelImpl
