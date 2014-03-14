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
 * A representation of the model object '<em><b>Prefix Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.PrefixExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.PrefixExpression#getOperand <em>Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getPrefixExpression()
 * @model
 * @generated
 */
public interface PrefixExpression extends Expression {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.gmt.modisco.java.PrefixExpressionKind}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.PrefixExpressionKind
	 * @see #setOperator(PrefixExpressionKind)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getPrefixExpression_Operator()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	PrefixExpressionKind getOperator();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.PrefixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.PrefixExpressionKind
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(PrefixExpressionKind value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' containment reference.
	 * @see #setOperand(Expression)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getPrefixExpression_Operand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getOperand();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.PrefixExpression#getOperand <em>Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' containment reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(Expression value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // PrefixExpression
