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
 * A representation of the model object '<em><b>Import Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ImportDeclaration#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ImportDeclaration#getImportedElement <em>Imported Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getImportDeclaration()
 * @model
 * @generated
 */
public interface ImportDeclaration extends ASTNode {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(boolean)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getImportDeclaration_Static()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	boolean isStatic();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ImportDeclaration#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Imported Element</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.NamedElement#getUsagesInImports <em>Usages In Imports</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Imported Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Element</em>' reference.
	 * @see #setImportedElement(NamedElement)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getImportDeclaration_ImportedElement()
	 * @see org.eclipse.gmt.modisco.java.NamedElement#getUsagesInImports
	 * @model opposite="usagesInImports" required="true" ordered="false"
	 * @generated
	 */
	NamedElement getImportedElement();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ImportDeclaration#getImportedElement <em>Imported Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imported Element</em>' reference.
	 * @see #getImportedElement()
	 * @generated
	 */
	void setImportedElement(NamedElement value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // ImportDeclaration
