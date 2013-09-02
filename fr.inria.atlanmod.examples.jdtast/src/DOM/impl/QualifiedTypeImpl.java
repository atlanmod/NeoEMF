/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.QualifiedType;
import DOM.SimpleName;
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
 * An implementation of the model object '<em><b>Qualified Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.QualifiedTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.QualifiedTypeImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QualifiedTypeImpl extends TypeImpl implements QualifiedType {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//TypeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QualifiedTypeImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.QUALIFIED_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName getName() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataQualifiedType))
			data = new DataQualifiedType();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.QUALIFIED_TYPE__NAME, null, null));
		return ((DataQualifiedType)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataQualifiedType();
	
		SimpleName oldName = ((DataQualifiedType)data).name;
		((DataQualifiedType)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.QUALIFIED_TYPE__NAME, oldName, newName);
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
	public void setName(SimpleName newName) {
	
		if (data==null) data =  new DataQualifiedType();
		
		else if (!(data instanceof DataQualifiedType)) data = new DataQualifiedType((DataType)data);
	
		if (newName != ((DataQualifiedType)data).name) {
			NotificationChain msgs = null;
			if (((DataQualifiedType)data).name != null)
				msgs = ((InternalEObject) ((DataQualifiedType)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.QUALIFIED_TYPE__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.QUALIFIED_TYPE__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.QUALIFIED_TYPE__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getQualifier() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataQualifiedType))
			data = new DataQualifiedType();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.QUALIFIED_TYPE__QUALIFIER, null, null));
		return ((DataQualifiedType)data).qualifier;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualifier(Type newQualifier, NotificationChain msgs) {
	
		if (data==null) data =  new DataQualifiedType();
	
		Type oldQualifier = ((DataQualifiedType)data).qualifier;
		((DataQualifiedType)data).qualifier = newQualifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.QUALIFIED_TYPE__QUALIFIER, oldQualifier, newQualifier);
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
	public void setQualifier(Type newQualifier) {
	
		if (data==null) data =  new DataQualifiedType();
		
		else if (!(data instanceof DataQualifiedType)) data = new DataQualifiedType((DataType)data);
	
		if (newQualifier != ((DataQualifiedType)data).qualifier) {
			NotificationChain msgs = null;
			if (((DataQualifiedType)data).qualifier != null)
				msgs = ((InternalEObject) ((DataQualifiedType)data).qualifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.QUALIFIED_TYPE__QUALIFIER, null, msgs);
			if (newQualifier != null)
				msgs = ((InternalEObject)newQualifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.QUALIFIED_TYPE__QUALIFIER, null, msgs);
			msgs = basicSetQualifier(newQualifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.QUALIFIED_TYPE__QUALIFIER, newQualifier, newQualifier));
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
			case DOMPackage.QUALIFIED_TYPE__NAME:
				return basicSetName(null, msgs);
			case DOMPackage.QUALIFIED_TYPE__QUALIFIER:
				return basicSetQualifier(null, msgs);
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
			case DOMPackage.QUALIFIED_TYPE__NAME:
				return getName();
			case DOMPackage.QUALIFIED_TYPE__QUALIFIER:
				return getQualifier();
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
			case DOMPackage.QUALIFIED_TYPE__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.QUALIFIED_TYPE__QUALIFIER:
				setQualifier((Type)newValue);
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
			case DOMPackage.QUALIFIED_TYPE__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.QUALIFIED_TYPE__QUALIFIER:
				setQualifier((Type)null);
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
			case DOMPackage.QUALIFIED_TYPE__NAME:
				return getName() != null;
			case DOMPackage.QUALIFIED_TYPE__QUALIFIER:
				return getQualifier() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataQualifiedType extends DataType {


	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected SimpleName name;

	/**
	 * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifier()
	 * @generated
	 * @ordered
	 */
	protected Type qualifier;

	/**
	 *Constructor of DataQualifiedType
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataQualifiedType() {
		super();
	}
	
		
	/**
	 *Constructor of DataQualifiedType
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Type }
	 * @generated
	 */
	public DataQualifiedType(DataType data) {
		super();		
		
				
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
} //QualifiedTypeImpl
