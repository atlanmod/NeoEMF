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
 * A representation of the model object '<em><b>Vertex</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getContainer <em>Container</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getFrom <em>From</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface Vertex extends INeo4emfObject {

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
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getName <em>Name</em>}' attribute.
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
	 * Returns the value of the '<em><b>Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Container#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' container reference.
	 * @see #setContainer(Container)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_Container()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Container#getNodes
	 * @model opposite="nodes" required="true" transient="false"
	 * @generated
	 */
	Container getContainer();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getContainer <em>Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' container reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(Container value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.neo4emf.testdata.Link}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getOutGoing <em>Out Going</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>From</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_From()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link#getOutGoing
	 * @model opposite="outGoing"
	 * @generated
	 */
	EList<Link> getFrom(); 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>To</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.neo4emf.testdata.Link}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getInComing <em>In Coming</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_To()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link#getInComing
	 * @model opposite="inComing"
	 * @generated
	 */
	EList<Link> getTo(); 





} // Vertex
