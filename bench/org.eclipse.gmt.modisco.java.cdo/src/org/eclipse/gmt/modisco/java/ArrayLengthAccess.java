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
 * A representation of the model object '<em><b>Array Length Access</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ArrayLengthAccess#getArray <em>Array</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage#getArrayLengthAccess()
 * @model
 * @generated
 */
public interface ArrayLengthAccess extends Expression {
    /**
     * Returns the value of the '<em><b>Array</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Array</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Array</em>' containment reference.
     * @see #setArray(Expression)
     * @see org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage#getArrayLengthAccess_Array()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    Expression getArray();

    /**
     * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ArrayLengthAccess#getArray <em>Array</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Array</em>' containment reference.
     * @see #getArray()
     * @generated
     */
    void setArray(Expression value);

} // ArrayLengthAccess
