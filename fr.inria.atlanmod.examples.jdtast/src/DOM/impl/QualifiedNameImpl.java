/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Name;
import DOM.QualifiedName;
import DOM.SimpleName;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Qualified Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.QualifiedNameImpl#getName <em>Name</em>}</li>
 *   <li>{@link DOM.impl.QualifiedNameImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QualifiedNameImpl extends NameImpl implements QualifiedName {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//NameImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QualifiedNameImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.QUALIFIED_NAME;
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
		if ( data == null || !(data instanceof DataQualifiedName))
			data = new DataQualifiedName();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.QUALIFIED_NAME__NAME, null, null));
		return ((DataQualifiedName)data).name;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
	
		if (data==null) data =  new DataQualifiedName();
	
		SimpleName oldName = ((DataQualifiedName)data).name;
		((DataQualifiedName)data).name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.QUALIFIED_NAME__NAME, oldName, newName);
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
	
		if (data==null) data =  new DataQualifiedName();
		
		else if (!(data instanceof DataQualifiedName)) data = new DataQualifiedName((DataName)data);
	
		if (newName != ((DataQualifiedName)data).name) {
			NotificationChain msgs = null;
			if (((DataQualifiedName)data).name != null)
				msgs = ((InternalEObject) ((DataQualifiedName)data).name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.QUALIFIED_NAME__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.QUALIFIED_NAME__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.QUALIFIED_NAME__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Name getQualifier() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataQualifiedName))
			data = new DataQualifiedName();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.QUALIFIED_NAME__QUALIFIER, null, null));
		return ((DataQualifiedName)data).qualifier;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualifier(Name newQualifier, NotificationChain msgs) {
	
		if (data==null) data =  new DataQualifiedName();
	
		Name oldQualifier = ((DataQualifiedName)data).qualifier;
		((DataQualifiedName)data).qualifier = newQualifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.QUALIFIED_NAME__QUALIFIER, oldQualifier, newQualifier);
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
	public void setQualifier(Name newQualifier) {
	
		if (data==null) data =  new DataQualifiedName();
		
		else if (!(data instanceof DataQualifiedName)) data = new DataQualifiedName((DataName)data);
	
		if (newQualifier != ((DataQualifiedName)data).qualifier) {
			NotificationChain msgs = null;
			if (((DataQualifiedName)data).qualifier != null)
				msgs = ((InternalEObject) ((DataQualifiedName)data).qualifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.QUALIFIED_NAME__QUALIFIER, null, msgs);
			if (newQualifier != null)
				msgs = ((InternalEObject)newQualifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.QUALIFIED_NAME__QUALIFIER, null, msgs);
			msgs = basicSetQualifier(newQualifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.QUALIFIED_NAME__QUALIFIER, newQualifier, newQualifier));
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
			case DOMPackage.QUALIFIED_NAME__NAME:
				return basicSetName(null, msgs);
			case DOMPackage.QUALIFIED_NAME__QUALIFIER:
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
			case DOMPackage.QUALIFIED_NAME__NAME:
				return getName();
			case DOMPackage.QUALIFIED_NAME__QUALIFIER:
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
			case DOMPackage.QUALIFIED_NAME__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.QUALIFIED_NAME__QUALIFIER:
				setQualifier((Name)newValue);
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
			case DOMPackage.QUALIFIED_NAME__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.QUALIFIED_NAME__QUALIFIER:
				setQualifier((Name)null);
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
			case DOMPackage.QUALIFIED_NAME__NAME:
				return getName() != null;
			case DOMPackage.QUALIFIED_NAME__QUALIFIER:
				return getQualifier() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataQualifiedName extends DataName {


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
	protected Name qualifier;

	/**
	 *Constructor of DataQualifiedName
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataQualifiedName() {
		super();
	}
	
		
	/**
	 *Constructor of DataQualifiedName
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Name }
	 * @generated
	 */
	public DataQualifiedName(DataName data) {
		super();		
		
		fullyQualifiedName = data.fullyQualifiedName;
				
				
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
} //QualifiedNameImpl
