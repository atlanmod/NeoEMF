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

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AST Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ASTNode#getComments <em>Comments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ASTNode#getOriginalCompilationUnit <em>Original Compilation Unit</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ASTNode#getOriginalClassFile <em>Original Class File</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getASTNode()
 * @model abstract="true"
 * @extends INeo4emfObject
 * @generated
 */
public interface ASTNode extends INeo4emfObject {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Comments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Comment}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Comments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getASTNode_Comments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Comment> getComments(); 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Original Compilation Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Original Compilation Unit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Compilation Unit</em>' reference.
	 * @see #setOriginalCompilationUnit(CompilationUnit)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getASTNode_OriginalCompilationUnit()
	 * @model ordered="false"
	 * @generated
	 */
	CompilationUnit getOriginalCompilationUnit();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ASTNode#getOriginalCompilationUnit <em>Original Compilation Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Compilation Unit</em>' reference.
	 * @see #getOriginalCompilationUnit()
	 * @generated
	 */
	void setOriginalCompilationUnit(CompilationUnit value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Original Class File</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Original Class File</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Class File</em>' reference.
	 * @see #setOriginalClassFile(ClassFile)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getASTNode_OriginalClassFile()
	 * @model ordered="false"
	 * @generated
	 */
	ClassFile getOriginalClassFile();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ASTNode#getOriginalClassFile <em>Original Class File</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Class File</em>' reference.
	 * @see #getOriginalClassFile()
	 * @generated
	 */
	void setOriginalClassFile(ClassFile value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // ASTNode
