/**
 */
package org.eclipse.gmt.modisco.java;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.Model#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Model#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Model#getOrphanTypes <em>Orphan Types</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Model#getUnresolvedItems <em>Unresolved Items</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Model#getCompilationUnits <em>Compilation Units</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Model#getClassFiles <em>Class Files</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Model#getArchives <em>Archives</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModel()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface Model extends PersistentEObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModel_Name()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Model#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Owned Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Package}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.Package#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Elements</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModel_OwnedElements()
	 * @see org.eclipse.gmt.modisco.java.Package#getModel
	 * @model opposite="model" containment="true" ordered="false"
	 * @generated
	 */
	EList<org.eclipse.gmt.modisco.java.Package> getOwnedElements();

	/**
	 * Returns the value of the '<em><b>Orphan Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orphan Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orphan Types</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModel_OrphanTypes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Type> getOrphanTypes();

	/**
	 * Returns the value of the '<em><b>Unresolved Items</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.UnresolvedItem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unresolved Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unresolved Items</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModel_UnresolvedItems()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<UnresolvedItem> getUnresolvedItems();

	/**
	 * Returns the value of the '<em><b>Compilation Units</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.CompilationUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compilation Units</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compilation Units</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModel_CompilationUnits()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<CompilationUnit> getCompilationUnits();

	/**
	 * Returns the value of the '<em><b>Class Files</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ClassFile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Files</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Files</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModel_ClassFiles()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ClassFile> getClassFiles();

	/**
	 * Returns the value of the '<em><b>Archives</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Archive}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Archives</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Archives</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModel_Archives()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Archive> getArchives();

} // Model
