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
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.Type#getUsagesInTypeAccess <em>Usages In Type Access</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getType()
 * @model abstract="true"
 * @generated
 */
public interface Type extends NamedElement {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Usages In Type Access</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.TypeAccess}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.TypeAccess#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Usages In Type Access</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usages In Type Access</em>' reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getType_UsagesInTypeAccess()
	 * @see org.eclipse.gmt.modisco.java.TypeAccess#getType
	 * @model opposite="type" ordered="false"
	 * @generated
	 */
	EList<TypeAccess> getUsagesInTypeAccess(); 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // Type
