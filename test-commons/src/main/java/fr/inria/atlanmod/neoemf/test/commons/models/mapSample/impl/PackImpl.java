/**
 */
package fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl;

import fr.inria.atlanmod.neoemf.core.impl.PersistentEObjectImpl;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.AbstractPackContent;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pack</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.PackImpl#getPacks <em>Packs</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.PackImpl#getParentPack <em>Parent Pack</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.PackImpl#getOwnedContents <em>Owned Contents</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neoemf.test.commons.models.mapSample.impl.PackImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackImpl extends PersistentEObjectImpl implements Pack {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected PackImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return MapSamplePackage.Literals.PACK;
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
	@SuppressWarnings("unchecked")
	public EList<Pack> getPacks() {
        return (EList<Pack>)eGet(MapSamplePackage.Literals.PACK__PACKS, true);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Pack getParentPack() {
        return (Pack)eGet(MapSamplePackage.Literals.PACK__PARENT_PACK, true);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setParentPack(Pack newParentPack) {
        eSet(MapSamplePackage.Literals.PACK__PARENT_PACK, newParentPack);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	public EList<AbstractPackContent> getOwnedContents() {
        return (EList<AbstractPackContent>)eGet(MapSamplePackage.Literals.PACK__OWNED_CONTENTS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return (String)eGet(MapSamplePackage.Literals.PACK__NAME, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        eSet(MapSamplePackage.Literals.PACK__NAME, newName);
    }

} //PackImpl
