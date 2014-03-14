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
 * A representation of the model object '<em><b>Variable Declaration Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.VariableDeclarationFragment#getVariablesContainer <em>Variables Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getVariableDeclarationFragment()
 * @model
 * @generated
 */
public interface VariableDeclarationFragment extends VariableDeclaration {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Variables Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.AbstractVariablesContainer#getFragments <em>Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Variables Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables Container</em>' container reference.
	 * @see #setVariablesContainer(AbstractVariablesContainer)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getVariableDeclarationFragment_VariablesContainer()
	 * @see org.eclipse.gmt.modisco.java.AbstractVariablesContainer#getFragments
	 * @model opposite="fragments" transient="false" ordered="false"
	 * @generated
	 */
	AbstractVariablesContainer getVariablesContainer();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.VariableDeclarationFragment#getVariablesContainer <em>Variables Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variables Container</em>' container reference.
	 * @see #getVariablesContainer()
	 * @generated
	 */
	void setVariablesContainer(AbstractVariablesContainer value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // VariableDeclarationFragment
