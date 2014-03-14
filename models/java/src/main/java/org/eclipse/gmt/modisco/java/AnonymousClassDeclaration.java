/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
**/
package org.eclipse.gmt.modisco.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Anonymous Class Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.AnonymousClassDeclaration#getBodyDeclarations <em>Body Declarations</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.AnonymousClassDeclaration#getClassInstanceCreation <em>Class Instance Creation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getAnonymousClassDeclaration()
 * @model
 * @generated
 */
public interface AnonymousClassDeclaration extends ASTNode {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Body Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.BodyDeclaration}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.BodyDeclaration#getAnonymousClassDeclarationOwner <em>Anonymous Class Declaration Owner</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Body Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Declarations</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getAnonymousClassDeclaration_BodyDeclarations()
	 * @see org.eclipse.gmt.modisco.java.BodyDeclaration#getAnonymousClassDeclarationOwner
	 * @model opposite="anonymousClassDeclarationOwner" containment="true"
	 * @generated
	 */
	EList<BodyDeclaration> getBodyDeclarations(); 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Class Instance Creation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.ClassInstanceCreation#getAnonymousClassDeclaration <em>Anonymous Class Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Class Instance Creation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Instance Creation</em>' container reference.
	 * @see #setClassInstanceCreation(ClassInstanceCreation)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getAnonymousClassDeclaration_ClassInstanceCreation()
	 * @see org.eclipse.gmt.modisco.java.ClassInstanceCreation#getAnonymousClassDeclaration
	 * @model opposite="anonymousClassDeclaration" transient="false" ordered="false"
	 * @generated
	 */
	ClassInstanceCreation getClassInstanceCreation();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.AnonymousClassDeclaration#getClassInstanceCreation <em>Class Instance Creation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Instance Creation</em>' container reference.
	 * @see #getClassInstanceCreation()
	 * @generated
	 */
	void setClassInstanceCreation(ClassInstanceCreation value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // AnonymousClassDeclaration
