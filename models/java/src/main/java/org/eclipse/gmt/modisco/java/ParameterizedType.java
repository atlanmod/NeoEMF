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
 * A representation of the model object '<em><b>Parameterized Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ParameterizedType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ParameterizedType#getTypeArguments <em>Type Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getParameterizedType()
 * @model
 * @generated
 */
public interface ParameterizedType extends Type {

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
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getParameterizedType_Type()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	TypeAccess getType();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ParameterizedType#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeAccess value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Type Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.TypeAccess}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Type Arguments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Arguments</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getParameterizedType_TypeArguments()
	 * @model containment="true"
	 * @generated
	 */
	EList<TypeAccess> getTypeArguments(); 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // ParameterizedType
