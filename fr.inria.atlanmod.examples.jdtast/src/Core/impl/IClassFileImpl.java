/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IClassFile;
import Core.IType;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IClass File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.IClassFileImpl#getIsClass <em>Is Class</em>}</li>
 *   <li>{@link Core.impl.IClassFileImpl#getIsInterface <em>Is Interface</em>}</li>
 *   <li>{@link Core.impl.IClassFileImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IClassFileImpl extends ITypeRootImpl implements IClassFile {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ITypeRootImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IClassFileImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ICLASS_FILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsClass() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIClassFile))
			data = new DataIClassFile();
			
		if (((DataIClassFile)data).isClass == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ICLASS_FILE__IS_CLASS, null, null));
		return ((DataIClassFile)data).isClass;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsClass(Boolean newIsClass) {
	
		if (data==null) data =  new DataIClassFile();
		
		else if (!(data instanceof DataIClassFile)) data = new DataIClassFile((DataITypeRoot)data);
	
		Boolean oldIsClass = ((DataIClassFile)data).isClass;
		((DataIClassFile)data).isClass = newIsClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ICLASS_FILE__IS_CLASS, oldIsClass, ((DataIClassFile)data).isClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsInterface() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIClassFile))
			data = new DataIClassFile();
			
		if (((DataIClassFile)data).isInterface == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ICLASS_FILE__IS_INTERFACE, null, null));
		return ((DataIClassFile)data).isInterface;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsInterface(Boolean newIsInterface) {
	
		if (data==null) data =  new DataIClassFile();
		
		else if (!(data instanceof DataIClassFile)) data = new DataIClassFile((DataITypeRoot)data);
	
		Boolean oldIsInterface = ((DataIClassFile)data).isInterface;
		((DataIClassFile)data).isInterface = newIsInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ICLASS_FILE__IS_INTERFACE, oldIsInterface, ((DataIClassFile)data).isInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType getType() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIClassFile))
			data = new DataIClassFile();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ICLASS_FILE__TYPE, null, null));
		return ((DataIClassFile)data).type;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(IType newType, NotificationChain msgs) {
	
		if (data==null) data =  new DataIClassFile();
	
		IType oldType = ((DataIClassFile)data).type;
		((DataIClassFile)data).type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.ICLASS_FILE__TYPE, oldType, newType);
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
	public void setType(IType newType) {
	
		if (data==null) data =  new DataIClassFile();
		
		else if (!(data instanceof DataIClassFile)) data = new DataIClassFile((DataITypeRoot)data);
	
		if (newType != ((DataIClassFile)data).type) {
			NotificationChain msgs = null;
			if (((DataIClassFile)data).type != null)
				msgs = ((InternalEObject) ((DataIClassFile)data).type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.ICLASS_FILE__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.ICLASS_FILE__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ICLASS_FILE__TYPE, newType, newType));
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
			case CorePackage.ICLASS_FILE__TYPE:
				return basicSetType(null, msgs);
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
			case CorePackage.ICLASS_FILE__IS_CLASS:
				return getIsClass();
			case CorePackage.ICLASS_FILE__IS_INTERFACE:
				return getIsInterface();
			case CorePackage.ICLASS_FILE__TYPE:
				return getType();
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
			case CorePackage.ICLASS_FILE__IS_CLASS:
				setIsClass((Boolean)newValue);
				return;
			case CorePackage.ICLASS_FILE__IS_INTERFACE:
				setIsInterface((Boolean)newValue);
				return;
			case CorePackage.ICLASS_FILE__TYPE:
				setType((IType)newValue);
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
			case CorePackage.ICLASS_FILE__IS_CLASS:
				setIsClass(DataIClassFile.IS_CLASS_EDEFAULT);
				return;
			case CorePackage.ICLASS_FILE__IS_INTERFACE:
				setIsInterface(DataIClassFile.IS_INTERFACE_EDEFAULT);
				return;
			case CorePackage.ICLASS_FILE__TYPE:
				setType((IType)null);
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
			case CorePackage.ICLASS_FILE__IS_CLASS:
				return DataIClassFile.IS_CLASS_EDEFAULT == null ? getIsClass() != null : !DataIClassFile.IS_CLASS_EDEFAULT.equals(getIsClass());
			case CorePackage.ICLASS_FILE__IS_INTERFACE:
				return DataIClassFile.IS_INTERFACE_EDEFAULT == null ? getIsInterface() != null : !DataIClassFile.IS_INTERFACE_EDEFAULT.equals(getIsInterface());
			case CorePackage.ICLASS_FILE__TYPE:
				return getType() != null;
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
		if (data != null) result.append(((DataIClassFile)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataIClassFile extends DataITypeRoot {


	/**
	 * The default value of the '{@link #getIsClass() <em>Is Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsClass()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsClass() <em>Is Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsClass()
	 * @generated
	 * @ordered
	 */
	protected Boolean isClass = IS_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsInterface() <em>Is Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsInterface()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsInterface() <em>Is Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsInterface()
	 * @generated
	 * @ordered
	 */
	protected Boolean isInterface = IS_INTERFACE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected IType type;

	/**
	 *Constructor of DataIClassFile
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIClassFile() {
		super();
	}
	
		
	/**
	 *Constructor of DataIClassFile
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ITypeRoot }
	 * @generated
	 */
	public DataIClassFile(DataITypeRoot data) {
		super();		
		
		source = data.source;
				
		sourceRange = data.sourceRange;
				
		path = data.path;
				
		isReadOnly = data.isReadOnly;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (isClass: ");
		result.append(isClass);
		result.append(", isInterface: ");
		result.append(isInterface);
		result.append(')');
		return result.toString();
	}
		
}
} //IClassFileImpl
