/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pack Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent#getParentPack <em>Parent Pack</em>}</li>
 * </ul>
 *
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getPackContent()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface PackContent extends PersistentEObject {
	/**
	 * Returns the value of the '<em><b>Parent Pack</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getOwnedContents <em>Owned Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Pack</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Pack</em>' container reference.
	 * @see #setParentPack(Pack)
	 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getPackContent_ParentPack()
	 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getOwnedContents
	 * @model opposite="ownedContents" transient="false"
	 * @generated
	 */
	Pack getParentPack();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent#getParentPack <em>Parent Pack</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Pack</em>' container reference.
	 * @see #getParentPack()
	 * @generated
	 */
	void setParentPack(Pack value);

} // PackContent
