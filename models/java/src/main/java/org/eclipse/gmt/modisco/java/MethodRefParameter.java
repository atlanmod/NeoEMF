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
 * A representation of the model object '<em><b>Method Ref Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.MethodRefParameter#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.MethodRefParameter#isVarargs <em>Varargs</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.MethodRefParameter#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getMethodRefParameter()
 * @model
 * @generated
 */
public interface MethodRefParameter extends ASTNode {

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
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getMethodRefParameter_Name()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getName();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.MethodRefParameter#getName <em>Name</em>}' attribute.
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
	 * Returns the value of the '<em><b>Varargs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Varargs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Varargs</em>' attribute.
	 * @see #setVarargs(boolean)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getMethodRefParameter_Varargs()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isVarargs();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.MethodRefParameter#isVarargs <em>Varargs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Varargs</em>' attribute.
	 * @see #isVarargs()
	 * @generated
	 */
	void setVarargs(boolean value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(TypeAccess)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getMethodRefParameter_Type()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	TypeAccess getType();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.MethodRefParameter#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeAccess value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // MethodRefParameter
