/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl;

import fr.inria.atlanmod.neoemf.core.impl.PersistentEObjectImpl;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.K;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.SampleModel;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.V;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sample Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.SampleModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.SampleModelImpl#getMap <em>Map</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.SampleModelImpl#getKvMap <em>Kv Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SampleModelImpl extends PersistentEObjectImpl implements SampleModel {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SampleModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MapSamplePackage.Literals.SAMPLE_MODEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected int eStaticFeatureCount() {
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return (String)eGet(MapSamplePackage.Literals.SAMPLE_MODEL__NAME, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        eSet(MapSamplePackage.Literals.SAMPLE_MODEL__NAME, newName);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    public EMap<String, String> getMap() {
        return (EMap<String, String>)eGet(MapSamplePackage.Literals.SAMPLE_MODEL__MAP, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    public EMap<K, V> getKvMap() {
        return (EMap<K, V>)eGet(MapSamplePackage.Literals.SAMPLE_MODEL__KV_MAP, true);
    }

} //SampleModelImpl
