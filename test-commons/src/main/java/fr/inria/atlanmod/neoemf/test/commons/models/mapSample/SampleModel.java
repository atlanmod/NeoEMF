/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sample Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getMap <em>Map</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getKvMap <em>Kv Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getSampleModel()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface SampleModel extends PersistentEObject {
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
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getSampleModel_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Map</em>' map.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getSampleModel_Map()
     * @model mapType="fr.inria.atlanmod.neoemf.test.commons.models.mapSample.StringToStringMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
     * @generated
     */
    EMap<String, String> getMap();

    /**
     * Returns the value of the '<em><b>Kv Map</b></em>' map.
     * The key is of type {@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K},
     * and the value is of type {@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Kv Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kv Map</em>' map.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getSampleModel_KvMap()
     * @model mapType="fr.inria.atlanmod.neoemf.test.commons.models.mapSample.KToVMap<fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K, fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V>"
     * @generated
     */
    EMap<K, V> getKvMap();

} // SampleModel
