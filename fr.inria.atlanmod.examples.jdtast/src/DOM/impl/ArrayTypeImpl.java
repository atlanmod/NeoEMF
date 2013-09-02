/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.ArrayType;
import DOM.DOMPackage;
import DOM.Type;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ArrayTypeImpl#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link DOM.impl.ArrayTypeImpl#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link DOM.impl.ArrayTypeImpl#getElementType <em>Element Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayTypeImpl extends TypeImpl implements ArrayType {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//TypeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayTypeImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ARRAY_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getComponentType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataArrayType))
			data = new DataArrayType();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ARRAY_TYPE__COMPONENT_TYPE, null, null));
		return ((DataArrayType)data).componentType;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentType(Type newComponentType, NotificationChain msgs) {
	
		if (data==null) data =  new DataArrayType();
	
		Type oldComponentType = ((DataArrayType)data).componentType;
		((DataArrayType)data).componentType = newComponentType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ARRAY_TYPE__COMPONENT_TYPE, oldComponentType, newComponentType);
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
	public void setComponentType(Type newComponentType) {
	
		if (data==null) data =  new DataArrayType();
		
		else if (!(data instanceof DataArrayType)) data = new DataArrayType((DataType)data);
	
		if (newComponentType != ((DataArrayType)data).componentType) {
			NotificationChain msgs = null;
			if (((DataArrayType)data).componentType != null)
				msgs = ((InternalEObject) ((DataArrayType)data).componentType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ARRAY_TYPE__COMPONENT_TYPE, null, msgs);
			if (newComponentType != null)
				msgs = ((InternalEObject)newComponentType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ARRAY_TYPE__COMPONENT_TYPE, null, msgs);
			msgs = basicSetComponentType(newComponentType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ARRAY_TYPE__COMPONENT_TYPE, newComponentType, newComponentType));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getDimensions() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataArrayType))
			data = new DataArrayType();
			
		if (((DataArrayType)data).dimensions == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ARRAY_TYPE__DIMENSIONS, null, null));
		return ((DataArrayType)data).dimensions;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDimensions(Integer newDimensions) {
	
		if (data==null) data =  new DataArrayType();
		
		else if (!(data instanceof DataArrayType)) data = new DataArrayType((DataType)data);
	
		Integer oldDimensions = ((DataArrayType)data).dimensions;
		((DataArrayType)data).dimensions = newDimensions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ARRAY_TYPE__DIMENSIONS, oldDimensions, ((DataArrayType)data).dimensions));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getElementType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataArrayType))
			data = new DataArrayType();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ARRAY_TYPE__ELEMENT_TYPE, null, null));
		return ((DataArrayType)data).elementType;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElementType(Type newElementType, NotificationChain msgs) {
	
		if (data==null) data =  new DataArrayType();
	
		Type oldElementType = ((DataArrayType)data).elementType;
		((DataArrayType)data).elementType = newElementType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ARRAY_TYPE__ELEMENT_TYPE, oldElementType, newElementType);
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
	public void setElementType(Type newElementType) {
	
		if (data==null) data =  new DataArrayType();
		
		else if (!(data instanceof DataArrayType)) data = new DataArrayType((DataType)data);
	
		if (newElementType != ((DataArrayType)data).elementType) {
			NotificationChain msgs = null;
			if (((DataArrayType)data).elementType != null)
				msgs = ((InternalEObject) ((DataArrayType)data).elementType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ARRAY_TYPE__ELEMENT_TYPE, null, msgs);
			if (newElementType != null)
				msgs = ((InternalEObject)newElementType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ARRAY_TYPE__ELEMENT_TYPE, null, msgs);
			msgs = basicSetElementType(newElementType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ARRAY_TYPE__ELEMENT_TYPE, newElementType, newElementType));
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
			case DOMPackage.ARRAY_TYPE__COMPONENT_TYPE:
				return basicSetComponentType(null, msgs);
			case DOMPackage.ARRAY_TYPE__ELEMENT_TYPE:
				return basicSetElementType(null, msgs);
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
			case DOMPackage.ARRAY_TYPE__COMPONENT_TYPE:
				return getComponentType();
			case DOMPackage.ARRAY_TYPE__DIMENSIONS:
				return getDimensions();
			case DOMPackage.ARRAY_TYPE__ELEMENT_TYPE:
				return getElementType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.ARRAY_TYPE__COMPONENT_TYPE:
				setComponentType((Type)newValue);
				return;
			case DOMPackage.ARRAY_TYPE__DIMENSIONS:
				setDimensions((Integer)newValue);
				return;
			case DOMPackage.ARRAY_TYPE__ELEMENT_TYPE:
				setElementType((Type)newValue);
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
			case DOMPackage.ARRAY_TYPE__COMPONENT_TYPE:
				setComponentType((Type)null);
				return;
			case DOMPackage.ARRAY_TYPE__DIMENSIONS:
				setDimensions(DataArrayType.DIMENSIONS_EDEFAULT);
				return;
			case DOMPackage.ARRAY_TYPE__ELEMENT_TYPE:
				setElementType((Type)null);
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
			case DOMPackage.ARRAY_TYPE__COMPONENT_TYPE:
				return getComponentType() != null;
			case DOMPackage.ARRAY_TYPE__DIMENSIONS:
				return DataArrayType.DIMENSIONS_EDEFAULT == null ? getDimensions() != null : !DataArrayType.DIMENSIONS_EDEFAULT.equals(getDimensions());
			case DOMPackage.ARRAY_TYPE__ELEMENT_TYPE:
				return getElementType() != null;
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
		if (data != null) result.append(((DataArrayType)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataArrayType extends DataType {


	/**
	 * The cached value of the '{@link #getComponentType() <em>Component Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected Type componentType;

	/**
	 * The default value of the '{@link #getDimensions() <em>Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensions()
	 * @generated
	 * @ordered
	 */
	protected static final Integer DIMENSIONS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDimensions() <em>Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensions()
	 * @generated
	 * @ordered
	 */
	protected Integer dimensions = DIMENSIONS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElementType() <em>Element Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementType()
	 * @generated
	 * @ordered
	 */
	protected Type elementType;

	/**
	 *Constructor of DataArrayType
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataArrayType() {
		super();
	}
	
		
	/**
	 *Constructor of DataArrayType
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Type }
	 * @generated
	 */
	public DataArrayType(DataType data) {
		super();		
		
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (dimensions: ");
		result.append(dimensions);
		result.append(')');
		return result.toString();
	}
		
}
} //ArrayTypeImpl
