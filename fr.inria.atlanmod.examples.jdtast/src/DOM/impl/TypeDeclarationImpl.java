/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Type;
import DOM.TypeDeclaration;
import DOM.TypeParameter;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.TypeDeclarationImpl#getSuperclassType <em>Superclass Type</em>}</li>
 *   <li>{@link DOM.impl.TypeDeclarationImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link DOM.impl.TypeDeclarationImpl#getSuperInterfaceTypes <em>Super Interface Types</em>}</li>
 *   <li>{@link DOM.impl.TypeDeclarationImpl#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeDeclarationImpl extends AbstractTypeDeclarationImpl implements TypeDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//AbstractTypeDeclarationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.TYPE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getSuperclassType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTypeDeclaration))
			data = new DataTypeDeclaration();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, null, null));
		return ((DataTypeDeclaration)data).superclassType;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuperclassType(Type newSuperclassType, NotificationChain msgs) {
	
		if (data==null) data =  new DataTypeDeclaration();
	
		Type oldSuperclassType = ((DataTypeDeclaration)data).superclassType;
		((DataTypeDeclaration)data).superclassType = newSuperclassType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, oldSuperclassType, newSuperclassType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuperclassType(Type newSuperclassType) {
	
		if (data==null) data =  new DataTypeDeclaration();
		
		else if (!(data instanceof DataTypeDeclaration)) data = new DataTypeDeclaration((DataAbstractTypeDeclaration)data);
	
		if (newSuperclassType != ((DataTypeDeclaration)data).superclassType) {
			NotificationChain msgs = null;
			if (((DataTypeDeclaration)data).superclassType != null)
				msgs = ((InternalEObject) ((DataTypeDeclaration)data).superclassType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, null, msgs);
			if (newSuperclassType != null)
				msgs = ((InternalEObject)newSuperclassType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, null, msgs);
			msgs = basicSetSuperclassType(newSuperclassType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, newSuperclassType, newSuperclassType));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getInterface() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTypeDeclaration))
			data = new DataTypeDeclaration();
			
		if (((DataTypeDeclaration)data).interface_ == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.TYPE_DECLARATION__INTERFACE, null, null));
		return ((DataTypeDeclaration)data).interface_;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(Boolean newInterface) {
	
		if (data==null) data =  new DataTypeDeclaration();
		
		else if (!(data instanceof DataTypeDeclaration)) data = new DataTypeDeclaration((DataAbstractTypeDeclaration)data);
	
		Boolean oldInterface = ((DataTypeDeclaration)data).interface_;
		((DataTypeDeclaration)data).interface_ = newInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_DECLARATION__INTERFACE, oldInterface, ((DataTypeDeclaration)data).interface_));
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
		if ( data == null || !(data instanceof DataTypeDeclaration))
			data = new DataTypeDeclaration();
				
	   
		if (((DataTypeDeclaration)data).superInterfaceTypes == null) {
			((DataTypeDeclaration)data).superInterfaceTypes = new EObjectContainmentEList<Type>(Type.class, this, DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES);			
		}
		return ((DataTypeDeclaration)data).superInterfaceTypes;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeParameter> getTypeParameters() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataTypeDeclaration))
			data = new DataTypeDeclaration();
				
	   
		if (((DataTypeDeclaration)data).typeParameters == null) {
			((DataTypeDeclaration)data).typeParameters = new EObjectContainmentEList<TypeParameter>(TypeParameter.class, this, DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS);			
		}
		return ((DataTypeDeclaration)data).typeParameters;	
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
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				return basicSetSuperclassType(null, msgs);
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				return ((InternalEList<?>)getSuperInterfaceTypes()).basicRemove(otherEnd, msgs);
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
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
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				return getSuperclassType();
			case DOMPackage.TYPE_DECLARATION__INTERFACE:
				return getInterface();
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				return getSuperInterfaceTypes();
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				return getTypeParameters();
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
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				setSuperclassType((Type)newValue);
				return;
			case DOMPackage.TYPE_DECLARATION__INTERFACE:
				setInterface((Boolean)newValue);
				return;
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				getSuperInterfaceTypes().clear();
				getSuperInterfaceTypes().addAll((Collection<? extends Type>)newValue);
				return;
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				getTypeParameters().clear();
				getTypeParameters().addAll((Collection<? extends TypeParameter>)newValue);
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
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				setSuperclassType((Type)null);
				return;
			case DOMPackage.TYPE_DECLARATION__INTERFACE:
				setInterface(DataTypeDeclaration.INTERFACE_EDEFAULT);
				return;
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				getSuperInterfaceTypes().clear();
				return;
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				getTypeParameters().clear();
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
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				return getSuperclassType() != null;
			case DOMPackage.TYPE_DECLARATION__INTERFACE:
				return DataTypeDeclaration.INTERFACE_EDEFAULT == null ? getInterface() != null : !DataTypeDeclaration.INTERFACE_EDEFAULT.equals(getInterface());
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				return getSuperInterfaceTypes() != null && !getSuperInterfaceTypes().isEmpty();
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				return getTypeParameters() != null && !getTypeParameters().isEmpty();
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 *YY27
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		if (data != null) result.append(((DataTypeDeclaration)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataTypeDeclaration extends DataAbstractTypeDeclaration {


	/**
	 * The cached value of the '{@link #getSuperclassType() <em>Superclass Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperclassType()
	 * @generated
	 * @ordered
	 */
	protected Type superclassType;

	/**
	 * The default value of the '{@link #getInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected Boolean interface_ = INTERFACE_EDEFAULT;

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
	 * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeParameter> typeParameters;

	/**
	 *Constructor of DataTypeDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataTypeDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link AbstractTypeDeclaration }
	 * @generated
	 */
	public DataTypeDeclaration(DataAbstractTypeDeclaration data) {
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
		result.append(" (interface: ");
		result.append(interface_);
		result.append(')');
		return result.toString();
	}
		
}
} //TypeDeclarationImpl
