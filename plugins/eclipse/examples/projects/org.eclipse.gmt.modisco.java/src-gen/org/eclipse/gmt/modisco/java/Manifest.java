/**
 */
package org.eclipse.gmt.modisco.java;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Manifest</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.Manifest#getMainAttributes <em>Main Attributes</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Manifest#getEntryAttributes <em>Entry Attributes</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getManifest()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface Manifest extends PersistentEObject {
	/**
	 * Returns the value of the '<em><b>Main Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ManifestAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Main Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Main Attributes</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getManifest_MainAttributes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ManifestAttribute> getMainAttributes();

	/**
	 * Returns the value of the '<em><b>Entry Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.ManifestEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entry Attributes</em>' containment reference list.
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getManifest_EntryAttributes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ManifestEntry> getEntryAttributes();

} // Manifest
