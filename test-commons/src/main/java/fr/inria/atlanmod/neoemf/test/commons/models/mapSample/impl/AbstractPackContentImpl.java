/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl;

import fr.inria.atlanmod.neoemf.core.impl.PersistentEObjectImpl;

import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContent;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Pack Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.AbstractPackContentImpl#getParentPack <em>Parent Pack</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.AbstractPackContentImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractPackContentImpl extends PersistentEObjectImpl implements AbstractPackContent {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractPackContentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MapSamplePackage.Literals.ABSTRACT_PACK_CONTENT;
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
    public Pack getParentPack() {
        return (Pack)eGet(MapSamplePackage.Literals.ABSTRACT_PACK_CONTENT__PARENT_PACK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParentPack(Pack newParentPack) {
        eSet(MapSamplePackage.Literals.ABSTRACT_PACK_CONTENT__PARENT_PACK, newParentPack);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return (String)eGet(MapSamplePackage.Literals.ABSTRACT_PACK_CONTENT__NAME, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        eSet(MapSamplePackage.Literals.ABSTRACT_PACK_CONTENT__NAME, newName);
    }

} //AbstractPackContentImpl
