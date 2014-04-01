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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Link#getOutGoing <em>Out Going</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Link#getContainer <em>Container</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Link#getInComing <em>In Coming</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getLink()
 * @model
 * @generated
 */
public interface Link extends NamedElement {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Out Going</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Out Going</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Going</em>' reference.
	 * @see #setOutGoing(Vertex)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getLink_OutGoing()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getFrom
	 * @model opposite="from" required="true"
	 * @generated
	 */
	Vertex getOutGoing();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getOutGoing <em>Out Going</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Going</em>' reference.
	 * @see #getOutGoing()
	 * @generated
	 */
	void setOutGoing(Vertex value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.ContainerType#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' container reference.
	 * @see #setContainer(ContainerType)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getLink_Container()
	 * @see fr.inria.atlanmod.neo4emf.testdata.ContainerType#getLinks
	 * @model opposite="links" required="true" transient="false"
	 * @generated
	 */
	ContainerType getContainer();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getContainer <em>Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' container reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(ContainerType value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>In Coming</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>In Coming</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Coming</em>' reference.
	 * @see #setInComing(Vertex)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getLink_InComing()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Vertex#getTo
	 * @model opposite="to" required="true"
	 * @generated
	 */
	Vertex getInComing();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getInComing <em>In Coming</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Coming</em>' reference.
	 * @see #getInComing()
	 * @generated
	 */
	void setInComing(Vertex value);
 

/*
* Neo4EMF inserted code -- begin
*/
// outGoing : Vertex, bi:true, chan:true, list:false, change:true, kind:reference
// container : ContainerType, bi:true, chan:true, list:false, change:true, kind:container reference
// inComing : Vertex, bi:true, chan:true, list:false, change:true, kind:reference
/*
* Neo4EMF inserted code -- end
*/




} // Link
