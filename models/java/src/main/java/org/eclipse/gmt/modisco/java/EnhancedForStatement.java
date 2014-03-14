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
 * A representation of the model object '<em><b>Enhanced For Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.EnhancedForStatement#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.EnhancedForStatement#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.EnhancedForStatement#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getEnhancedForStatement()
 * @model
 * @generated
 */
public interface EnhancedForStatement extends Statement {

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
	 * @see #setBody(Statement)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getEnhancedForStatement_Body()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Statement getBody();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.EnhancedForStatement#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(Statement value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getEnhancedForStatement_Expression()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getExpression();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.EnhancedForStatement#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.SingleVariableDeclaration#getEnhancedForStatement <em>Enhanced For Statement</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference.
	 * @see #setParameter(SingleVariableDeclaration)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getEnhancedForStatement_Parameter()
	 * @see org.eclipse.gmt.modisco.java.SingleVariableDeclaration#getEnhancedForStatement
	 * @model opposite="enhancedForStatement" containment="true" required="true" ordered="false"
	 * @generated
	 */
	SingleVariableDeclaration getParameter();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.EnhancedForStatement#getParameter <em>Parameter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' containment reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(SingleVariableDeclaration value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // EnhancedForStatement
