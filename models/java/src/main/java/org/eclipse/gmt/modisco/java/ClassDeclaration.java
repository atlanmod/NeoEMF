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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ClassDeclaration#getSuperClass <em>Super Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getClassDeclaration()
 * @model
 * @generated
 */
public interface ClassDeclaration extends TypeDeclaration {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Super Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Super Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Class</em>' containment reference.
	 * @see #setSuperClass(TypeAccess)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getClassDeclaration_SuperClass()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	TypeAccess getSuperClass();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ClassDeclaration#getSuperClass <em>Super Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Class</em>' containment reference.
	 * @see #getSuperClass()
	 * @generated
	 */
	void setSuperClass(TypeAccess value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // ClassDeclaration
