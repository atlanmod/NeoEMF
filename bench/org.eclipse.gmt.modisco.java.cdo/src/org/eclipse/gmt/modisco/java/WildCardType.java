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
 * A representation of the model object '<em><b>Wild Card Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.WildCardType#isUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.WildCardType#getBound <em>Bound</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage#getWildCardType()
 * @model
 * @generated
 */
public interface WildCardType extends Type {
    /**
     * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Upper Bound</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Upper Bound</em>' attribute.
     * @see #setUpperBound(boolean)
     * @see org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage#getWildCardType_UpperBound()
     * @model unique="false" required="true" ordered="false"
     * @generated
     */
    boolean isUpperBound();

    /**
     * Sets the value of the '{@link org.eclipse.gmt.modisco.java.WildCardType#isUpperBound <em>Upper Bound</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Upper Bound</em>' attribute.
     * @see #isUpperBound()
     * @generated
     */
    void setUpperBound(boolean value);

    /**
     * Returns the value of the '<em><b>Bound</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bound</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bound</em>' containment reference.
     * @see #setBound(TypeAccess)
     * @see org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage#getWildCardType_Bound()
     * @model containment="true" ordered="false"
     * @generated
     */
    TypeAccess getBound();

    /**
     * Sets the value of the '{@link org.eclipse.gmt.modisco.java.WildCardType#getBound <em>Bound</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bound</em>' containment reference.
     * @see #getBound()
     * @generated
     */
    void setBound(TypeAccess value);

} // WildCardType
