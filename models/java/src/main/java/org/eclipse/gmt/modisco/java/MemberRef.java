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
 * A representation of the model object '<em><b>Member Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.MemberRef#getMember <em>Member</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.MemberRef#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getMemberRef()
 * @model
 * @generated
 */
public interface MemberRef extends ASTNode {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Member</b></em>' reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Member</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member</em>' reference.
	 * @see #setMember(NamedElement)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getMemberRef_Member()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	NamedElement getMember();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.MemberRef#getMember <em>Member</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Member</em>' reference.
	 * @see #getMember()
	 * @generated
	 */
	void setMember(NamedElement value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Qualifier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualifier</em>' containment reference.
	 * @see #setQualifier(TypeAccess)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getMemberRef_Qualifier()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	TypeAccess getQualifier();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.MemberRef#getQualifier <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualifier</em>' containment reference.
	 * @see #getQualifier()
	 * @generated
	 */
	void setQualifier(TypeAccess value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // MemberRef
