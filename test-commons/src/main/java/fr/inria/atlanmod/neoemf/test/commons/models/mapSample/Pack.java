/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pack</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getPacks <em>Packs</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getParentPack <em>Parent Pack</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getOwnedContents <em>Owned Contents</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getPack()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface Pack extends PersistentEObject {
	/**
     * Returns the value of the '<em><b>Packs</b></em>' containment reference list.
     * The list contents are of type {@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack}.
     * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getParentPack <em>Parent Pack</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Packs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Packs</em>' containment reference list.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getPack_Packs()
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getParentPack
     * @model opposite="parentPack" containment="true"
     * @generated
     */
	EList<Pack> getPacks();

	/**
     * Returns the value of the '<em><b>Parent Pack</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getPacks <em>Packs</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Pack</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parent Pack</em>' container reference.
     * @see #setParentPack(Pack)
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getPack_ParentPack()
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getPacks
     * @model opposite="packs" transient="false"
     * @generated
     */
	Pack getParentPack();

	/**
     * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getParentPack <em>Parent Pack</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent Pack</em>' container reference.
     * @see #getParentPack()
     * @generated
     */
	void setParentPack(Pack value);

	/**
     * Returns the value of the '<em><b>Owned Contents</b></em>' containment reference list.
     * The list contents are of type {@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent}.
     * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent#getParentPack <em>Parent Pack</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Owned Contents</em>' containment reference list.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getPack_OwnedContents()
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent#getParentPack
     * @model opposite="parentPack" containment="true"
     * @generated
     */
	EList<PackContent> getOwnedContents();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getPack_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // Pack
