/**
 * Copyright (c) 2009 Mia-Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Fabien Giquel (Mia-Software) - initial API and implementation
 *     Gregoire DUPE (Mia-Software) - initial API and implementation
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Continue Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ContinueStatement#getLabel <em>Label</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage#getContinueStatement()
 * @model
 * @generated
 */
public interface ContinueStatement extends Statement {
    /**
     * Returns the value of the '<em><b>Label</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.LabeledStatement#getUsagesInContinueStatements <em>Usages In Continue Statements</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' reference.
     * @see #setLabel(LabeledStatement)
     * @see org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage#getContinueStatement_Label()
     * @see org.eclipse.gmt.modisco.java.LabeledStatement#getUsagesInContinueStatements
     * @model opposite="usagesInContinueStatements" ordered="false"
     * @generated
     */
    LabeledStatement getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ContinueStatement#getLabel <em>Label</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' reference.
     * @see #getLabel()
     * @generated
     */
    void setLabel(LabeledStatement value);

} // ContinueStatement
