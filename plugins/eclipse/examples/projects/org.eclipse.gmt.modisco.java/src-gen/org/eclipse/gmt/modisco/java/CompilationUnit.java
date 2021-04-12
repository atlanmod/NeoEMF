/**
 */
package org.eclipse.gmt.modisco.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.CompilationUnit#getOriginalFilePath <em>Original File Path</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.CompilationUnit#getCommentList <em>Comment List</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.CompilationUnit#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.CompilationUnit#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.CompilationUnit#getTypes <em>Types</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getCompilationUnit()
 * @model
 * @generated
 */
public interface CompilationUnit extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Original File Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Original File Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original File Path</em>' attribute.
	 * @see #setOriginalFilePath(String)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getCompilationUnit_OriginalFilePath()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getOriginalFilePath();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.CompilationUnit#getOriginalFilePath <em>Original File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original File Path</em>' attribute.
	 * @see #getOriginalFilePath()
	 * @generated
	 */
	void setOriginalFilePath(String value);

	/**
	 * Returns the value of the '<em><b>Comment List</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Comment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment List</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment List</em>' reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getCompilationUnit_CommentList()
	 * @model
	 * @generated
	 */
	EList<Comment> getCommentList();

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ImportDeclaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getCompilationUnit_Imports()
	 * @model containment="true"
	 * @generated
	 */
	EList<ImportDeclaration> getImports();

	/**
	 * Returns the value of the '<em><b>Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' reference.
	 * @see #setPackage(org.eclipse.gmt.modisco.java.Package)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getCompilationUnit_Package()
	 * @model ordered="false"
	 * @generated
	 */
	org.eclipse.gmt.modisco.java.Package getPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.CompilationUnit#getPackage <em>Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(org.eclipse.gmt.modisco.java.Package value);

	/**
	 * Returns the value of the '<em><b>Types</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.AbstractTypeDeclaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getCompilationUnit_Types()
	 * @model
	 * @generated
	 */
	EList<AbstractTypeDeclaration> getTypes();

} // CompilationUnit
