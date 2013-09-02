/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.ISourceRange;
import Core.ISourceReference;
import Core.ITypeRoot;
import Core.PhysicalElement;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IType Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.ITypeRootImpl#getSource <em>Source</em>}</li>
 *   <li>{@link Core.impl.ITypeRootImpl#getSourceRange <em>Source Range</em>}</li>
 *   <li>{@link Core.impl.ITypeRootImpl#getPath <em>Path</em>}</li>
 *   <li>{@link Core.impl.ITypeRootImpl#getIsReadOnly <em>Is Read Only</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ITypeRootImpl extends IJavaElementImpl implements ITypeRoot {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IJavaElementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ITypeRootImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ITYPE_ROOT;
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
		if ( data == null || !(data instanceof DataITypeRoot))
			data = new DataITypeRoot();
			
		if (((DataITypeRoot)data).source == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ITYPE_ROOT__SOURCE, null, null));
		return ((DataITypeRoot)data).source;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(String newSource) {
	
		if (data==null) data =  new DataITypeRoot();
		
		else if (!(data instanceof DataITypeRoot)) data = new DataITypeRoot((DataIJavaElement)data);
	
		String oldSource = ((DataITypeRoot)data).source;
		((DataITypeRoot)data).source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ITYPE_ROOT__SOURCE, oldSource, ((DataITypeRoot)data).source));
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
		if ( data == null || !(data instanceof DataITypeRoot))
			data = new DataITypeRoot();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ITYPE_ROOT__SOURCE_RANGE, null, null));
		return ((DataITypeRoot)data).sourceRange;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceRange(ISourceRange newSourceRange, NotificationChain msgs) {
	
		if (data==null) data =  new DataITypeRoot();
	
		ISourceRange oldSourceRange = ((DataITypeRoot)data).sourceRange;
		((DataITypeRoot)data).sourceRange = newSourceRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.ITYPE_ROOT__SOURCE_RANGE, oldSourceRange, newSourceRange);
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
	
		if (data==null) data =  new DataITypeRoot();
		
		else if (!(data instanceof DataITypeRoot)) data = new DataITypeRoot((DataIJavaElement)data);
	
		if (newSourceRange != ((DataITypeRoot)data).sourceRange) {
			NotificationChain msgs = null;
			if (((DataITypeRoot)data).sourceRange != null)
				msgs = ((InternalEObject) ((DataITypeRoot)data).sourceRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.ITYPE_ROOT__SOURCE_RANGE, null, msgs);
			if (newSourceRange != null)
				msgs = ((InternalEObject)newSourceRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.ITYPE_ROOT__SOURCE_RANGE, null, msgs);
			msgs = basicSetSourceRange(newSourceRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ITYPE_ROOT__SOURCE_RANGE, newSourceRange, newSourceRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataITypeRoot))
			data = new DataITypeRoot();
			
		if (((DataITypeRoot)data).path == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ITYPE_ROOT__PATH, null, null));
		return ((DataITypeRoot)data).path;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
	
		if (data==null) data =  new DataITypeRoot();
		
		else if (!(data instanceof DataITypeRoot)) data = new DataITypeRoot((DataIJavaElement)data);
	
		String oldPath = ((DataITypeRoot)data).path;
		((DataITypeRoot)data).path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ITYPE_ROOT__PATH, oldPath, ((DataITypeRoot)data).path));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsReadOnly() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataITypeRoot))
			data = new DataITypeRoot();
			
		if (((DataITypeRoot)data).isReadOnly == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.ITYPE_ROOT__IS_READ_ONLY, null, null));
		return ((DataITypeRoot)data).isReadOnly;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReadOnly(Boolean newIsReadOnly) {
	
		if (data==null) data =  new DataITypeRoot();
		
		else if (!(data instanceof DataITypeRoot)) data = new DataITypeRoot((DataIJavaElement)data);
	
		Boolean oldIsReadOnly = ((DataITypeRoot)data).isReadOnly;
		((DataITypeRoot)data).isReadOnly = newIsReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ITYPE_ROOT__IS_READ_ONLY, oldIsReadOnly, ((DataITypeRoot)data).isReadOnly));
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
			case CorePackage.ITYPE_ROOT__SOURCE_RANGE:
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
			case CorePackage.ITYPE_ROOT__SOURCE:
				return getSource();
			case CorePackage.ITYPE_ROOT__SOURCE_RANGE:
				return getSourceRange();
			case CorePackage.ITYPE_ROOT__PATH:
				return getPath();
			case CorePackage.ITYPE_ROOT__IS_READ_ONLY:
				return getIsReadOnly();
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
			case CorePackage.ITYPE_ROOT__SOURCE:
				setSource((String)newValue);
				return;
			case CorePackage.ITYPE_ROOT__SOURCE_RANGE:
				setSourceRange((ISourceRange)newValue);
				return;
			case CorePackage.ITYPE_ROOT__PATH:
				setPath((String)newValue);
				return;
			case CorePackage.ITYPE_ROOT__IS_READ_ONLY:
				setIsReadOnly((Boolean)newValue);
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
			case CorePackage.ITYPE_ROOT__SOURCE:
				setSource(DataITypeRoot.SOURCE_EDEFAULT);
				return;
			case CorePackage.ITYPE_ROOT__SOURCE_RANGE:
				setSourceRange((ISourceRange)null);
				return;
			case CorePackage.ITYPE_ROOT__PATH:
				setPath(DataITypeRoot.PATH_EDEFAULT);
				return;
			case CorePackage.ITYPE_ROOT__IS_READ_ONLY:
				setIsReadOnly(DataITypeRoot.IS_READ_ONLY_EDEFAULT);
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
			case CorePackage.ITYPE_ROOT__SOURCE:
				return DataITypeRoot.SOURCE_EDEFAULT == null ? getSource() != null : !DataITypeRoot.SOURCE_EDEFAULT.equals(getSource());
			case CorePackage.ITYPE_ROOT__SOURCE_RANGE:
				return getSourceRange() != null;
			case CorePackage.ITYPE_ROOT__PATH:
				return DataITypeRoot.PATH_EDEFAULT == null ? getPath() != null : !DataITypeRoot.PATH_EDEFAULT.equals(getPath());
			case CorePackage.ITYPE_ROOT__IS_READ_ONLY:
				return DataITypeRoot.IS_READ_ONLY_EDEFAULT == null ? getIsReadOnly() != null : !DataITypeRoot.IS_READ_ONLY_EDEFAULT.equals(getIsReadOnly());
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 *YY19
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ISourceReference.class) {
			switch (derivedFeatureID) {
				case CorePackage.ITYPE_ROOT__SOURCE: return CorePackage.ISOURCE_REFERENCE__SOURCE;
				case CorePackage.ITYPE_ROOT__SOURCE_RANGE: return CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE;
				default: return -1;
			}
		}
		if (baseClass == PhysicalElement.class) {
			switch (derivedFeatureID) {
				case CorePackage.ITYPE_ROOT__PATH: return CorePackage.PHYSICAL_ELEMENT__PATH;
				case CorePackage.ITYPE_ROOT__IS_READ_ONLY: return CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY20
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ISourceReference.class) {
			switch (baseFeatureID) {
				case CorePackage.ISOURCE_REFERENCE__SOURCE: return CorePackage.ITYPE_ROOT__SOURCE;
				case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE: return CorePackage.ITYPE_ROOT__SOURCE_RANGE;
				default: return -1;
			}
		}
		if (baseClass == PhysicalElement.class) {
			switch (baseFeatureID) {
				case CorePackage.PHYSICAL_ELEMENT__PATH: return CorePackage.ITYPE_ROOT__PATH;
				case CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY: return CorePackage.ITYPE_ROOT__IS_READ_ONLY;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		if (data != null) result.append(((DataITypeRoot)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataITypeRoot extends DataIJavaElement {


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
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_READ_ONLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected Boolean isReadOnly = IS_READ_ONLY_EDEFAULT;

	/**
	 *Constructor of DataITypeRoot
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataITypeRoot() {
		super();
	}
	
		
	/**
	 *Constructor of DataITypeRoot
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IJavaElement }
	 * @generated
	 */
	public DataITypeRoot(DataIJavaElement data) {
		super();		
		
		elementName = data.elementName;
				
				
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
		result.append(", path: ");
		result.append(path);
		result.append(", isReadOnly: ");
		result.append(isReadOnly);
		result.append(')');
		return result.toString();
	}
		
}
} //ITypeRootImpl
