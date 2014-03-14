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
 * A representation of the model object '<em><b>Manifest</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.Manifest#getMainAttributes <em>Main Attributes</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Manifest#getEntryAttributes <em>Entry Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getManifest()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface Manifest extends INeo4emfObject {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Main Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ManifestAttribute}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Main Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Main Attributes</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getManifest_MainAttributes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ManifestAttribute> getMainAttributes(); 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Entry Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ManifestEntry}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Entry Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entry Attributes</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getManifest_EntryAttributes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ManifestEntry> getEntryAttributes(); 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // Manifest
