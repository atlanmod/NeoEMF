/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sample Model Content Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getSampleModelContentObject()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface SampleModelContentObject extends PersistentEObject {
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
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getSampleModelContentObject_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

				/**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getContentObjects <em>Content Objects</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(SampleModel)
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getSampleModelContentObject_Parent()
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getContentObjects
     * @model opposite="contentObjects" transient="false"
     * @generated
     */
	SampleModel getParent();

				/**
     * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModelContentObject#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
	void setParent(SampleModel value);

} // SampleModelContentObject
