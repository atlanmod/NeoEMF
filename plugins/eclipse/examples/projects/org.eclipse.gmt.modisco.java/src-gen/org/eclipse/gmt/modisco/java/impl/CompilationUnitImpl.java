/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.CompilationUnitImpl#getOriginalFilePath <em>Original File Path</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.CompilationUnitImpl#getCommentList <em>Comment List</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.CompilationUnitImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.CompilationUnitImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.CompilationUnitImpl#getTypes <em>Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompilationUnitImpl extends NamedElementImpl implements CompilationUnit {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompilationUnitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getCompilationUnit();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOriginalFilePath() {
		return (String)eGet(JavaPackage.eINSTANCE.getCompilationUnit_OriginalFilePath(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalFilePath(String newOriginalFilePath) {
		eSet(JavaPackage.eINSTANCE.getCompilationUnit_OriginalFilePath(), newOriginalFilePath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Comment> getCommentList() {
		return (EList<Comment>)eGet(JavaPackage.eINSTANCE.getCompilationUnit_CommentList(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<ImportDeclaration> getImports() {
		return (EList<ImportDeclaration>)eGet(JavaPackage.eINSTANCE.getCompilationUnit_Imports(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getPackage() {
		return (org.eclipse.gmt.modisco.java.Package)eGet(JavaPackage.eINSTANCE.getCompilationUnit_Package(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(org.eclipse.gmt.modisco.java.Package newPackage) {
		eSet(JavaPackage.eINSTANCE.getCompilationUnit_Package(), newPackage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<AbstractTypeDeclaration> getTypes() {
		return (EList<AbstractTypeDeclaration>)eGet(JavaPackage.eINSTANCE.getCompilationUnit_Types(), true);
	}

} //CompilationUnitImpl
