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
 * A representation of the model object '<em><b>Class File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ClassFile#getOriginalFilePath <em>Original File Path</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ClassFile#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ClassFile#getAttachedSource <em>Attached Source</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ClassFile#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getClassFile()
 * @model
 * @generated
 */
public interface ClassFile extends NamedElement {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Original File Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Original File Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original File Path</em>' attribute.
	 * @see #setOriginalFilePath(String)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getClassFile_OriginalFilePath()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getOriginalFilePath();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ClassFile#getOriginalFilePath <em>Original File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original File Path</em>' attribute.
	 * @see #getOriginalFilePath()
	 * @generated
	 */
	void setOriginalFilePath(String value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(AbstractTypeDeclaration)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getClassFile_Type()
	 * @model ordered="false"
	 * @generated
	 */
	AbstractTypeDeclaration getType();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ClassFile#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(AbstractTypeDeclaration value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Attached Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Attached Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attached Source</em>' reference.
	 * @see #setAttachedSource(CompilationUnit)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getClassFile_AttachedSource()
	 * @model ordered="false"
	 * @generated
	 */
	CompilationUnit getAttachedSource();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ClassFile#getAttachedSource <em>Attached Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attached Source</em>' reference.
	 * @see #getAttachedSource()
	 * @generated
	 */
	void setAttachedSource(CompilationUnit value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' reference.
	 * @see #setPackage(org.eclipse.gmt.modisco.java.Package)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getClassFile_Package()
	 * @model ordered="false"
	 * @generated
	 */
	org.eclipse.gmt.modisco.java.Package getPackage();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ClassFile#getPackage <em>Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(org.eclipse.gmt.modisco.java.Package value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // ClassFile
