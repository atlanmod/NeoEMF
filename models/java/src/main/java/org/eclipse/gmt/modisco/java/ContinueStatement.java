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
 * A representation of the model object '<em><b>Continue Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ContinueStatement#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getContinueStatement()
 * @model
 * @generated
 */
public interface ContinueStatement extends Statement {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Label</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.LabeledStatement#getUsagesInContinueStatements <em>Usages In Continue Statements</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Label</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' reference.
	 * @see #setLabel(LabeledStatement)
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#getContinueStatement_Label()
	 * @see org.eclipse.gmt.modisco.java.LabeledStatement#getUsagesInContinueStatements
	 * @model opposite="usagesInContinueStatements" ordered="false"
	 * @generated
	 */
	LabeledStatement getLabel();
	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ContinueStatement#getLabel <em>Label</em>}' reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' reference.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(LabeledStatement value);
 


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




} // ContinueStatement
