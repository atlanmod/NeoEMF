/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.EnumConstantDeclaration;
import DOM.EnumDeclaration;
import DOM.Type;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.EnumDeclarationImpl#getSuperInterfaceTypes <em>Super Interface Types</em>}</li>
 *   <li>{@link DOM.impl.EnumDeclarationImpl#getEnumConstants <em>Enum Constants</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumDeclarationImpl extends AbstractTypeDeclarationImpl implements EnumDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//AbstractTypeDeclarationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ENUM_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getSuperInterfaceTypes() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataEnumDeclaration))
			data = new DataEnumDeclaration();
				
	   
		if (((DataEnumDeclaration)data).superInterfaceTypes == null) {
			((DataEnumDeclaration)data).superInterfaceTypes = new EObjectContainmentEList<Type>(Type.class, this, DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES);			
		}
		return ((DataEnumDeclaration)data).superInterfaceTypes;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EnumConstantDeclaration> getEnumConstants() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataEnumDeclaration))
			data = new DataEnumDeclaration();
				
	   
		if (((DataEnumDeclaration)data).enumConstants == null) {
			((DataEnumDeclaration)data).enumConstants = new EObjectContainmentEList<EnumConstantDeclaration>(EnumConstantDeclaration.class, this, DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS);			
		}
		return ((DataEnumDeclaration)data).enumConstants;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY13
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				return ((InternalEList<?>)getSuperInterfaceTypes()).basicRemove(otherEnd, msgs);
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				return ((InternalEList<?>)getEnumConstants()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY15
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				return getSuperInterfaceTypes();
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				return getEnumConstants();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				getSuperInterfaceTypes().clear();
				getSuperInterfaceTypes().addAll((Collection<? extends Type>)newValue);
				return;
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				getEnumConstants().clear();
				getEnumConstants().addAll((Collection<? extends EnumConstantDeclaration>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY17
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				getSuperInterfaceTypes().clear();
				return;
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				getEnumConstants().clear();
				return;
		}
		super.eUnset(featureID);
	}

/**
	 * <!-- begin-user-doc -->
	 *YY18
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				return getSuperInterfaceTypes() != null && !getSuperInterfaceTypes().isEmpty();
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				return getEnumConstants() != null && !getEnumConstants().isEmpty();
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataEnumDeclaration extends DataAbstractTypeDeclaration {


	/**
	 * The cached value of the '{@link #getSuperInterfaceTypes() <em>Super Interface Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperInterfaceTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> superInterfaceTypes;

	/**
	 * The cached value of the '{@link #getEnumConstants() <em>Enum Constants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumConstants()
	 * @generated
	 * @ordered
	 */
	protected EList<EnumConstantDeclaration> enumConstants;

	/**
	 *Constructor of DataEnumDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataEnumDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataEnumDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link AbstractTypeDeclaration }
	 * @generated
	 */
	public DataEnumDeclaration(DataAbstractTypeDeclaration data) {
		super();		
		
		bodyDeclarations = data.bodyDeclarations;
				
		name = data.name;
				
		localTypeDeclaration = data.localTypeDeclaration;
				
		memberTypeDeclaration = data.memberTypeDeclaration;
				
		packageMemberTypeDeclaration = data.packageMemberTypeDeclaration;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(')');
		return result.toString();
	}
		
}
} //EnumDeclarationImpl
