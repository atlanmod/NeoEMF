/**
 */
package org.eclipse.gmt.modisco.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.AbstractTypeDeclaration#getBodyDeclarations <em>Body Declarations</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.AbstractTypeDeclaration#getCommentsBeforeBody <em>Comments Before Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.AbstractTypeDeclaration#getCommentsAfterBody <em>Comments After Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.AbstractTypeDeclaration#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.AbstractTypeDeclaration#getSuperInterfaces <em>Super Interfaces</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAbstractTypeDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface AbstractTypeDeclaration extends BodyDeclaration, Type {
	/**
	 * Returns the value of the '<em><b>Body Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.BodyDeclaration}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.BodyDeclaration#getAbstractTypeDeclaration <em>Abstract Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Declarations</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAbstractTypeDeclaration_BodyDeclarations()
	 * @see org.eclipse.gmt.modisco.java.BodyDeclaration#getAbstractTypeDeclaration
	 * @model opposite="abstractTypeDeclaration" containment="true"
	 * @generated
	 */
	EList<BodyDeclaration> getBodyDeclarations();

	/**
	 * Returns the value of the '<em><b>Comments Before Body</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Comment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments Before Body</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments Before Body</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAbstractTypeDeclaration_CommentsBeforeBody()
	 * @model containment="true"
	 * @generated
	 */
	EList<Comment> getCommentsBeforeBody();

	/**
	 * Returns the value of the '<em><b>Comments After Body</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Comment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments After Body</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments After Body</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAbstractTypeDeclaration_CommentsAfterBody()
	 * @model containment="true"
	 * @generated
	 */
	EList<Comment> getCommentsAfterBody();

	/**
	 * Returns the value of the '<em><b>Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.Package#getOwnedElements <em>Owned Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' container reference.
	 * @see #setPackage(org.eclipse.gmt.modisco.java.Package)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAbstractTypeDeclaration_Package()
	 * @see org.eclipse.gmt.modisco.java.Package#getOwnedElements
	 * @model opposite="ownedElements" transient="false" ordered="false"
	 * @generated
	 */
	org.eclipse.gmt.modisco.java.Package getPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.AbstractTypeDeclaration#getPackage <em>Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' container reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(org.eclipse.gmt.modisco.java.Package value);

	/**
	 * Returns the value of the '<em><b>Super Interfaces</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.TypeAccess}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Interfaces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Interfaces</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAbstractTypeDeclaration_SuperInterfaces()
	 * @model containment="true"
	 * @generated
	 */
	EList<TypeAccess> getSuperInterfaces();

} // AbstractTypeDeclaration
