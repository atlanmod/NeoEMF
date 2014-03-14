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
 * A representation of the model object '<em><b>Enum Constant Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.EnumConstantDeclaration#getAnonymousClassDeclaration <em>Anonymous Class Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.EnumConstantDeclaration#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getEnumConstantDeclaration()
 * @model
 * @generated
 */
public interface EnumConstantDeclaration extends BodyDeclaration, VariableDeclaration {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Anonymous Class Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Anonymous Class Declaration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anonymous Class Declaration</em>' containment reference.
	 * @see #setAnonymousClassDeclaration(AnonymousClassDeclaration)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getEnumConstantDeclaration_AnonymousClassDeclaration()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	AnonymousClassDeclaration getAnonymousClassDeclaration();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.EnumConstantDeclaration#getAnonymousClassDeclaration <em>Anonymous Class Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anonymous Class Declaration</em>' containment reference.
	 * @see #getAnonymousClassDeclaration()
	 * @generated
	 */
	void setAnonymousClassDeclaration(AnonymousClassDeclaration value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Expression}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getEnumConstantDeclaration_Arguments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getArguments(); 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // EnumConstantDeclaration
