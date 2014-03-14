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
 * A representation of the model object '<em><b>Annotation Member Value Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.AnnotationMemberValuePair#getMember <em>Member</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.AnnotationMemberValuePair#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getAnnotationMemberValuePair()
 * @model
 * @generated
 */
public interface AnnotationMemberValuePair extends NamedElement {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Member</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration#getUsages <em>Usages</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Member</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member</em>' reference.
	 * @see #setMember(AnnotationTypeMemberDeclaration)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getAnnotationMemberValuePair_Member()
	 * @see org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration#getUsages
	 * @model opposite="usages" ordered="false"
	 * @generated
	 */
	AnnotationTypeMemberDeclaration getMember();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.AnnotationMemberValuePair#getMember <em>Member</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Member</em>' reference.
	 * @see #getMember()
	 * @generated
	 */
	void setMember(AnnotationTypeMemberDeclaration value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(Expression)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getAnnotationMemberValuePair_Value()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getValue();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.AnnotationMemberValuePair#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Expression value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // AnnotationMemberValuePair
