/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory
 * @model kind="package"
 * @generated
 */
public interface MapSamplePackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "mapSample";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "com.mapSample";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "mapSample";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    MapSamplePackage eINSTANCE = fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl.init();

    /**
     * The meta object id for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.SampleModelImpl <em>Sample Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.SampleModelImpl
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getSampleModel()
     * @generated
     */
    int SAMPLE_MODEL = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SAMPLE_MODEL__NAME = 0;

    /**
     * The feature id for the '<em><b>Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SAMPLE_MODEL__MAP = 1;

    /**
     * The feature id for the '<em><b>Kv Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SAMPLE_MODEL__KV_MAP = 2;

    /**
     * The number of structural features of the '<em>Sample Model</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SAMPLE_MODEL_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Sample Model</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SAMPLE_MODEL_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.StringToStringMapImpl
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getStringToStringMap()
     * @generated
     */
    int STRING_TO_STRING_MAP = 1;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_TO_STRING_MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_TO_STRING_MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>String To String Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_TO_STRING_MAP_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>String To String Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_TO_STRING_MAP_OPERATION_COUNT = 0;


    /**
     * The meta object id for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.KImpl <em>K</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.KImpl
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getK()
     * @generated
     */
    int K = 2;

    /**
     * The feature id for the '<em><b>KName</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int K__KNAME = 0;

    /**
     * The feature id for the '<em><b>KInt</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int K__KINT = 1;

    /**
     * The number of structural features of the '<em>K</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int K_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>K</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int K_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.VImpl <em>V</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.VImpl
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getV()
     * @generated
     */
    int V = 3;

    /**
     * The feature id for the '<em><b>VName</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int V__VNAME = 0;

    /**
     * The feature id for the '<em><b>VInt</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int V__VINT = 1;

    /**
     * The number of structural features of the '<em>V</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int V_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>V</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int V_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.KToVMapImpl <em>KTo VMap</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.KToVMapImpl
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getKToVMap()
     * @generated
     */
    int KTO_VMAP = 4;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTO_VMAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTO_VMAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>KTo VMap</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTO_VMAP_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>KTo VMap</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTO_VMAP_OPERATION_COUNT = 0;


    /**
     * Returns the meta object for class '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel <em>Sample Model</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Sample Model</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel
     * @generated
     */
    EClass getSampleModel();

    /**
     * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getName()
     * @see #getSampleModel()
     * @generated
     */
    EAttribute getSampleModel_Name();

    /**
     * Returns the meta object for the map '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getMap <em>Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Map</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getMap()
     * @see #getSampleModel()
     * @generated
     */
    EReference getSampleModel_Map();

    /**
     * Returns the meta object for the map '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getKvMap <em>Kv Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Kv Map</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel#getKvMap()
     * @see #getSampleModel()
     * @generated
     */
    EReference getSampleModel_KvMap();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>String To String Map</em>'.
     * @see java.util.Map.Entry
     * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
     *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
     * @generated
     */
    EClass getStringToStringMap();

    /**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getStringToStringMap()
     * @generated
     */
    EAttribute getStringToStringMap_Key();

    /**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getStringToStringMap()
     * @generated
     */
    EAttribute getStringToStringMap_Value();

    /**
     * Returns the meta object for class '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K <em>K</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>K</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K
     * @generated
     */
    EClass getK();

    /**
     * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K#getKName <em>KName</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>KName</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K#getKName()
     * @see #getK()
     * @generated
     */
    EAttribute getK_KName();

    /**
     * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K#getKInt <em>KInt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>KInt</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K#getKInt()
     * @see #getK()
     * @generated
     */
    EAttribute getK_KInt();

    /**
     * Returns the meta object for class '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V <em>V</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>V</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V
     * @generated
     */
    EClass getV();

    /**
     * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V#getVName <em>VName</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>VName</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V#getVName()
     * @see #getV()
     * @generated
     */
    EAttribute getV_VName();

    /**
     * Returns the meta object for the attribute '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V#getVInt <em>VInt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>VInt</em>'.
     * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V#getVInt()
     * @see #getV()
     * @generated
     */
    EAttribute getV_VInt();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>KTo VMap</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KTo VMap</em>'.
     * @see java.util.Map.Entry
     * @model keyType="fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K" keyRequired="true"
     *        valueType="fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V" valueRequired="true"
     * @generated
     */
    EClass getKToVMap();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getKToVMap()
     * @generated
     */
    EReference getKToVMap_Key();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getKToVMap()
     * @generated
     */
    EReference getKToVMap_Value();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    MapSampleFactory getMapSampleFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.SampleModelImpl <em>Sample Model</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.SampleModelImpl
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getSampleModel()
         * @generated
         */
        EClass SAMPLE_MODEL = eINSTANCE.getSampleModel();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SAMPLE_MODEL__NAME = eINSTANCE.getSampleModel_Name();

        /**
         * The meta object literal for the '<em><b>Map</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SAMPLE_MODEL__MAP = eINSTANCE.getSampleModel_Map();

        /**
         * The meta object literal for the '<em><b>Kv Map</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SAMPLE_MODEL__KV_MAP = eINSTANCE.getSampleModel_KvMap();

        /**
         * The meta object literal for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.StringToStringMapImpl
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getStringToStringMap()
         * @generated
         */
        EClass STRING_TO_STRING_MAP = eINSTANCE.getStringToStringMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STRING_TO_STRING_MAP__KEY = eINSTANCE.getStringToStringMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STRING_TO_STRING_MAP__VALUE = eINSTANCE.getStringToStringMap_Value();

        /**
         * The meta object literal for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.KImpl <em>K</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.KImpl
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getK()
         * @generated
         */
        EClass K = eINSTANCE.getK();

        /**
         * The meta object literal for the '<em><b>KName</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute K__KNAME = eINSTANCE.getK_KName();

        /**
         * The meta object literal for the '<em><b>KInt</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute K__KINT = eINSTANCE.getK_KInt();

        /**
         * The meta object literal for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.VImpl <em>V</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.VImpl
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getV()
         * @generated
         */
        EClass V = eINSTANCE.getV();

        /**
         * The meta object literal for the '<em><b>VName</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute V__VNAME = eINSTANCE.getV_VName();

        /**
         * The meta object literal for the '<em><b>VInt</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute V__VINT = eINSTANCE.getV_VInt();

        /**
         * The meta object literal for the '{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.KToVMapImpl <em>KTo VMap</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.KToVMapImpl
         * @see fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.MapSamplePackageImpl#getKToVMap()
         * @generated
         */
        EClass KTO_VMAP = eINSTANCE.getKToVMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KTO_VMAP__KEY = eINSTANCE.getKToVMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KTO_VMAP__VALUE = eINSTANCE.getKToVMap_Value();

    }

} //MapSamplePackage
