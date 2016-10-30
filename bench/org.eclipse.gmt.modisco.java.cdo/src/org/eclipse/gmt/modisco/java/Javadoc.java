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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Javadoc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.Javadoc#getTags <em>Tags</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage#getJavadoc()
 * @model
 * @generated
 */
public interface Javadoc extends Comment {
    /**
     * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.gmt.modisco.java.TagElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tags</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tags</em>' containment reference list.
     * @see org.eclipse.gmt.modisco.java.cdo.meta.JavaPackage#getJavadoc_Tags()
     * @model containment="true"
     * @generated
     */
    EList<TagElement> getTags();

} // Javadoc
