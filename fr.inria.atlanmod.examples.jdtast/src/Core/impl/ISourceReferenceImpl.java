/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.ISourceRange;
import Core.ISourceReference;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ISource Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.ISourceReferenceImpl#getSource <em>Source</em>}</li>
 *   <li>{@link Core.impl.ISourceReferenceImpl#getSourceRange <em>Source Range</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ISourceReferenceImpl extends Neo4emfObject implements ISourceReference {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//Neo4emfObject
	
	/**
	 * The cached value of the data structure {@link DataISourceReference <em>data</em> } 
	 * @generated
	 */
	 	protected DataISourceReference data;
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ISourceReferenceImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ISOURCE_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSource() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataISourceReference))
			data = new DataISourceReference();
			
		if (((DataISourceReference)data).source == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ISOURCE_REFERENCE__SOURCE, null, null));
		return ((DataISourceReference)data).source;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(String newSource) {
	
		if (data==null) data =  new DataISourceReference();
		
		String oldSource = ((DataISourceReference)data).source;
		((DataISourceReference)data).source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ISOURCE_REFERENCE__SOURCE, oldSource, ((DataISourceReference)data).source));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange getSourceRange() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataISourceReference))
			data = new DataISourceReference();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE, null, null));
		return ((DataISourceReference)data).sourceRange;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceRange(ISourceRange newSourceRange, NotificationChain msgs) {
	
		if (data==null) data =  new DataISourceReference();
	
		ISourceRange oldSourceRange = ((DataISourceReference)data).sourceRange;
		((DataISourceReference)data).sourceRange = newSourceRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE, oldSourceRange, newSourceRange);
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
	public void setSourceRange(ISourceRange newSourceRange) {
	
		if (data==null) data =  new DataISourceReference();
		
		if (newSourceRange != ((DataISourceReference)data).sourceRange) {
			NotificationChain msgs = null;
			if (((DataISourceReference)data).sourceRange != null)
				msgs = ((InternalEObject) ((DataISourceReference)data).sourceRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE, null, msgs);
			if (newSourceRange != null)
				msgs = ((InternalEObject)newSourceRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE, null, msgs);
			msgs = basicSetSourceRange(newSourceRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE, newSourceRange, newSourceRange));
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
			case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE:
				return basicSetSourceRange(null, msgs);
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
			case CorePackage.ISOURCE_REFERENCE__SOURCE:
				return getSource();
			case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE:
				return getSourceRange();
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
			case CorePackage.ISOURCE_REFERENCE__SOURCE:
				setSource((String)newValue);
				return;
			case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE:
				setSourceRange((ISourceRange)newValue);
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
			case CorePackage.ISOURCE_REFERENCE__SOURCE:
				setSource(DataISourceReference.SOURCE_EDEFAULT);
				return;
			case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE:
				setSourceRange((ISourceRange)null);
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
			case CorePackage.ISOURCE_REFERENCE__SOURCE:
				return DataISourceReference.SOURCE_EDEFAULT == null ? getSource() != null : !DataISourceReference.SOURCE_EDEFAULT.equals(getSource());
			case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE:
				return getSourceRange() != null;
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
		if (data != null) result.append(((DataISourceReference)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataISourceReference {


	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected String source = SOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSourceRange() <em>Source Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRange()
	 * @generated
	 * @ordered
	 */
	protected ISourceRange sourceRange;

	/**
	 *Constructor of DataISourceReference
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataISourceReference() {
		super();
	}
	
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (source: ");
		result.append(source);
		result.append(')');
		return result.toString();
	}
		
}
} //ISourceReferenceImpl
