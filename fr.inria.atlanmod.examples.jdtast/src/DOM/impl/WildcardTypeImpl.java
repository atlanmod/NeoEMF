/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Type;
import DOM.WildcardType;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wildcard Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.WildcardTypeImpl#getBound <em>Bound</em>}</li>
 *   <li>{@link DOM.impl.WildcardTypeImpl#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WildcardTypeImpl extends TypeImpl implements WildcardType {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//TypeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WildcardTypeImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.WILDCARD_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getBound() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataWildcardType))
			data = new DataWildcardType();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.WILDCARD_TYPE__BOUND, null, null));
		return ((DataWildcardType)data).bound;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBound(Type newBound, NotificationChain msgs) {
	
		if (data==null) data =  new DataWildcardType();
	
		Type oldBound = ((DataWildcardType)data).bound;
		((DataWildcardType)data).bound = newBound;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.WILDCARD_TYPE__BOUND, oldBound, newBound);
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
	public void setBound(Type newBound) {
	
		if (data==null) data =  new DataWildcardType();
		
		else if (!(data instanceof DataWildcardType)) data = new DataWildcardType((DataType)data);
	
		if (newBound != ((DataWildcardType)data).bound) {
			NotificationChain msgs = null;
			if (((DataWildcardType)data).bound != null)
				msgs = ((InternalEObject) ((DataWildcardType)data).bound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.WILDCARD_TYPE__BOUND, null, msgs);
			if (newBound != null)
				msgs = ((InternalEObject)newBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.WILDCARD_TYPE__BOUND, null, msgs);
			msgs = basicSetBound(newBound, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.WILDCARD_TYPE__BOUND, newBound, newBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getUpperBound() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataWildcardType))
			data = new DataWildcardType();
			
		if (((DataWildcardType)data).upperBound == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.WILDCARD_TYPE__UPPER_BOUND, null, null));
		return ((DataWildcardType)data).upperBound;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperBound(Boolean newUpperBound) {
	
		if (data==null) data =  new DataWildcardType();
		
		else if (!(data instanceof DataWildcardType)) data = new DataWildcardType((DataType)data);
	
		Boolean oldUpperBound = ((DataWildcardType)data).upperBound;
		((DataWildcardType)data).upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.WILDCARD_TYPE__UPPER_BOUND, oldUpperBound, ((DataWildcardType)data).upperBound));
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
			case DOMPackage.WILDCARD_TYPE__BOUND:
				return basicSetBound(null, msgs);
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
			case DOMPackage.WILDCARD_TYPE__BOUND:
				return getBound();
			case DOMPackage.WILDCARD_TYPE__UPPER_BOUND:
				return getUpperBound();
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
			case DOMPackage.WILDCARD_TYPE__BOUND:
				setBound((Type)newValue);
				return;
			case DOMPackage.WILDCARD_TYPE__UPPER_BOUND:
				setUpperBound((Boolean)newValue);
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
			case DOMPackage.WILDCARD_TYPE__BOUND:
				setBound((Type)null);
				return;
			case DOMPackage.WILDCARD_TYPE__UPPER_BOUND:
				setUpperBound(DataWildcardType.UPPER_BOUND_EDEFAULT);
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
			case DOMPackage.WILDCARD_TYPE__BOUND:
				return getBound() != null;
			case DOMPackage.WILDCARD_TYPE__UPPER_BOUND:
				return DataWildcardType.UPPER_BOUND_EDEFAULT == null ? getUpperBound() != null : !DataWildcardType.UPPER_BOUND_EDEFAULT.equals(getUpperBound());
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
		if (data != null) result.append(((DataWildcardType)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataWildcardType extends DataType {


	/**
	 * The cached value of the '{@link #getBound() <em>Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBound()
	 * @generated
	 * @ordered
	 */
	protected Type bound;

	/**
	 * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean UPPER_BOUND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected Boolean upperBound = UPPER_BOUND_EDEFAULT;

	/**
	 *Constructor of DataWildcardType
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataWildcardType() {
		super();
	}
	
		
	/**
	 *Constructor of DataWildcardType
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Type }
	 * @generated
	 */
	public DataWildcardType(DataType data) {
		super();		
		
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (upperBound: ");
		result.append(upperBound);
		result.append(')');
		return result.toString();
	}
		
}
} //WildcardTypeImpl
