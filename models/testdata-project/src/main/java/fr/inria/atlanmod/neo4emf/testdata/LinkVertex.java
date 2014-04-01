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
 * A representation of the model object '<em><b>Link Vertex</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getFirstName <em>First Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getLink <em>Link</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getVertex <em>Vertex</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getLinkVertex()
 * @model
 * @generated
 */
public interface LinkVertex extends Link, Vertex {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>First Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Name</em>' attribute.
	 * @see #setFirstName(String)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getLinkVertex_FirstName()
	 * @model required="true"
	 * @generated
	 */
	String getFirstName();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getFirstName <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Name</em>' attribute.
	 * @see #getFirstName()
	 * @generated
	 */
	void setFirstName(String value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link</em>' reference.
	 * @see #setLink(Link)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getLinkVertex_Link()
	 * @model required="true"
	 * @generated
	 */
	Link getLink();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getLink <em>Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link</em>' reference.
	 * @see #getLink()
	 * @generated
	 */
	void setLink(Link value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Vertex</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Vertex</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertex</em>' reference.
	 * @see #setVertex(Vertex)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getLinkVertex_Vertex()
	 * @model required="true"
	 * @generated
	 */
	Vertex getVertex();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.LinkVertex#getVertex <em>Vertex</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vertex</em>' reference.
	 * @see #getVertex()
	 * @generated
	 */
	void setVertex(Vertex value);
 

/*
* Neo4EMF inserted code -- begin
*/
// vcontainer : ContainerType, bi:true, chan:true, list:false, change:true, kind:container reference
// from : EList<Link>, bi:true, chan:true, list:true, change:true, kind:reference list
// to : EList<Link>, bi:true, chan:true, list:true, change:true, kind:reference list
// link : Link, bi:false, chan:true, list:false, change:true, kind:reference
// vertex : Vertex, bi:false, chan:true, list:false, change:true, kind:reference
/*
* Neo4EMF inserted code -- end
*/




} // LinkVertex
