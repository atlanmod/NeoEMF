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
 * A representation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.NamedElement#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.NamedElement#isProxy <em>Proxy</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.NamedElement#getUsagesInImports <em>Usages In Imports</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getNamedElement()
 * @model abstract="true"
 * @generated
 */
public interface NamedElement extends ASTNode {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getNamedElement_Name()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getName();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.NamedElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Proxy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Proxy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proxy</em>' attribute.
	 * @see #setProxy(boolean)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getNamedElement_Proxy()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isProxy();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.NamedElement#isProxy <em>Proxy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proxy</em>' attribute.
	 * @see #isProxy()
	 * @generated
	 */
	void setProxy(boolean value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Usages In Imports</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ImportDeclaration}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.ImportDeclaration#getImportedElement <em>Imported Element</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Usages In Imports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usages In Imports</em>' reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getNamedElement_UsagesInImports()
	 * @see org.eclipse.gmt.modisco.java.ImportDeclaration#getImportedElement
	 * @model opposite="importedElement" ordered="false"
	 * @generated
	 */
	EList<ImportDeclaration> getUsagesInImports(); 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // NamedElement
