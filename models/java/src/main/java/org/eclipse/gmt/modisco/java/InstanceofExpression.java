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
 * A representation of the model object '<em><b>Instanceof Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.InstanceofExpression#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.InstanceofExpression#getLeftOperand <em>Left Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getInstanceofExpression()
 * @model
 * @generated
 */
public interface InstanceofExpression extends Expression {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Operand</em>' containment reference.
	 * @see #setRightOperand(TypeAccess)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getInstanceofExpression_RightOperand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	TypeAccess getRightOperand();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.InstanceofExpression#getRightOperand <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' containment reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(TypeAccess value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' containment reference.
	 * @see #setLeftOperand(Expression)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getInstanceofExpression_LeftOperand()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getLeftOperand();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.InstanceofExpression#getLeftOperand <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' containment reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(Expression value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // InstanceofExpression
