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
 * A representation of the model object '<em><b>Try Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.TryStatement#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.TryStatement#getFinally <em>Finally</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.TryStatement#getCatchClauses <em>Catch Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getTryStatement()
 * @model
 * @generated
 */
public interface TryStatement extends Statement {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(Block)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getTryStatement_Body()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Block getBody();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.TryStatement#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(Block value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Finally</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Finally</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Finally</em>' containment reference.
	 * @see #setFinally(Block)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getTryStatement_Finally()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	Block getFinally();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.TryStatement#getFinally <em>Finally</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finally</em>' containment reference.
	 * @see #getFinally()
	 * @generated
	 */
	void setFinally(Block value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Catch Clauses</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.CatchClause}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Catch Clauses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catch Clauses</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getTryStatement_CatchClauses()
	 * @model containment="true"
	 * @generated
	 */
	EList<CatchClause> getCatchClauses(); 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // TryStatement
