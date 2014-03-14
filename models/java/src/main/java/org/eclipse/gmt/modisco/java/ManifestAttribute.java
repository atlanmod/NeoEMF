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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Manifest Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ManifestAttribute#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ManifestAttribute#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getManifestAttribute()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface ManifestAttribute extends INeo4emfObject {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getManifestAttribute_Key()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getKey();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ManifestAttribute#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getManifestAttribute_Value()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getValue();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ManifestAttribute#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // ManifestAttribute
