/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>K</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K#getKName <em>KName</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K#getKInt <em>KInt</em>}</li>
 * </ul>
 *
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getK()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface K extends PersistentEObject {
    /**
	 * Returns the value of the '<em><b>KName</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>KName</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>KName</em>' attribute.
	 * @see #setKName(String)
	 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getK_KName()
	 * @model
	 * @generated
	 */
    String getKName();

    /**
	 * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K#getKName <em>KName</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KName</em>' attribute.
	 * @see #getKName()
	 * @generated
	 */
    void setKName(String value);

    /**
	 * Returns the value of the '<em><b>KInt</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>KInt</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>KInt</em>' attribute.
	 * @see #setKInt(int)
	 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage#getK_KInt()
	 * @model
	 * @generated
	 */
    int getKInt();

    /**
	 * Sets the value of the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K#getKInt <em>KInt</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KInt</em>' attribute.
	 * @see #getKInt()
	 * @generated
	 */
    void setKInt(int value);

} // K
