/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.*;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MapSampleFactoryImpl extends EFactoryImpl implements MapSampleFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static MapSampleFactory init() {
        try {
            MapSampleFactory theMapSampleFactory = (MapSampleFactory)EPackage.Registry.INSTANCE.getEFactory(MapSamplePackage.eNS_URI);
            if (theMapSampleFactory != null) {
                return theMapSampleFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new MapSampleFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MapSampleFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case MapSamplePackage.SAMPLE_MODEL: return (EObject)createSampleModel();
            case MapSamplePackage.STRING_TO_STRING_MAP: return (EObject)createStringToStringMap();
            case MapSamplePackage.K: return (EObject)createK();
            case MapSamplePackage.V: return (EObject)createV();
            case MapSamplePackage.KTO_VMAP: return (EObject)createKToVMap();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SampleModel createSampleModel() {
        SampleModelImpl sampleModel = new SampleModelImpl();
        return sampleModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<String, String> createStringToStringMap() {
        StringToStringMapImpl stringToStringMap = new StringToStringMapImpl();
        return stringToStringMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public K createK() {
        KImpl k = new KImpl();
        return k;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public V createV() {
        VImpl v = new VImpl();
        return v;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<K, V> createKToVMap() {
        KToVMapImpl kToVMap = new KToVMapImpl();
        return kToVMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MapSamplePackage getMapSamplePackage() {
        return (MapSamplePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static MapSamplePackage getPackage() {
        return MapSamplePackage.eINSTANCE;
    }

} //MapSampleFactoryImpl
