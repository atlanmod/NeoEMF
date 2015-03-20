/**
 * *******************************************************************************
 * Copyright (c) 2009 Mia-Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 
 *     Sebastien Minguet (Mia-Software) - initial API and implementation
 *     Frederic Madiot (Mia-Software) - initial API and implementation
 *     Fabien Giquel (Mia-Software) - initial API and implementation
 *     Gabriel Barbier (Mia-Software) - initial API and implementation
 *     Erwan Breton (Sodifrance) - initial API and implementation
 *     Romain Dervaux (Mia-Software) - initial API and implementation
 * *******************************************************************************
 *
 * $Id$
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Single Variable Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.SingleVariableAccess#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.SingleVariableAccess#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.emf.JavaPackage#getSingleVariableAccess()
 * @model
 * @generated
 */
public interface SingleVariableAccess extends Expression {
	/**
	 * Returns the value of the '<em><b>Variable</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.VariableDeclaration#getUsageInVariableAccess <em>Usage In Variable Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' reference.
	 * @see #setVariable(VariableDeclaration)
	 * @see org.eclipse.gmt.modisco.java.emf.JavaPackage#getSingleVariableAccess_Variable()
	 * @see org.eclipse.gmt.modisco.java.VariableDeclaration#getUsageInVariableAccess
	 * @model opposite="usageInVariableAccess" required="true" ordered="false"
	 * @generated
	 */
	VariableDeclaration getVariable();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.SingleVariableAccess#getVariable <em>Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' reference.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(VariableDeclaration value);

	/**
	 * Returns the value of the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualifier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualifier</em>' containment reference.
	 * @see #setQualifier(Expression)
	 * @see org.eclipse.gmt.modisco.java.emf.JavaPackage#getSingleVariableAccess_Qualifier()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	Expression getQualifier();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.SingleVariableAccess#getQualifier <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualifier</em>' containment reference.
	 * @see #getQualifier()
	 * @generated
	 */
	void setQualifier(Expression value);

} // SingleVariableAccess
