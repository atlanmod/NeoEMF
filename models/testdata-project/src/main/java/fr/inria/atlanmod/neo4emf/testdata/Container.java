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
package fr.inria.atlanmod.neo4emf.testdata;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Container#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Container#getNodes <em>Nodes</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Container#getLinks <em>Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getContainer()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface Container extends INeo4emfObject {

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
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getContainer_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Container#getName <em>Name</em>}' attribute.
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
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.neo4emf.testdata.Vertex}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getVcontainer <em>Vcontainer</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getContainer_Nodes()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getVcontainer
	 * @model opposite="vcontainer" containment="true"
	 * @generated
	 */
	EList<Vertex> getNodes(); 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Links</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.neo4emf.testdata.Link}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Links</em>' containment reference list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getContainer_Links()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link#getContainer
	 * @model opposite="container" containment="true"
	 * @generated
	 */
	EList<Link> getLinks(); 


/*
* Neo4EMF inserted code -- begin
*/



/*
* Neo4EMF inserted code -- end
*/




} // Container
