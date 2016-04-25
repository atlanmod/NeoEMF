/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>V</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V#getVName <em>VName</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V#getVInt <em>VInt</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getV()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface V extends PersistentEObject {
    /**
     * Returns the value of the '<em><b>VName</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>VName</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>VName</em>' attribute.
     * @see #setVName(String)
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getV_VName()
     * @model
     * @generated
     */
    String getVName();

    /**
     * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V#getVName <em>VName</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>VName</em>' attribute.
     * @see #getVName()
     * @generated
     */
    void setVName(String value);

    /**
     * Returns the value of the '<em><b>VInt</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>VInt</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>VInt</em>' attribute.
     * @see #setVInt(int)
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getV_VInt()
     * @model
     * @generated
     */
    int getVInt();

    /**
     * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V#getVInt <em>VInt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>VInt</em>' attribute.
     * @see #getVInt()
     * @generated
     */
    void setVInt(int value);

} // V
