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
 * A representation of the model object '<em><b>Manifest Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ManifestEntry#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ManifestEntry#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getManifestEntry()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface ManifestEntry extends INeo4emfObject {

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
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getManifestEntry_Name()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getName();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ManifestEntry#getName <em>Name</em>}' attribute.
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
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ManifestAttribute}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getManifestEntry_Attributes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ManifestAttribute> getAttributes(); 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // ManifestEntry
