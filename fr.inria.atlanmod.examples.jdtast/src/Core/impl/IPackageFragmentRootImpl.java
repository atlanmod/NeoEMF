/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IPackageFragment;
import Core.IPackageFragmentRoot;
import Core.PhysicalElement;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IPackage Fragment Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.IPackageFragmentRootImpl#getPath <em>Path</em>}</li>
 *   <li>{@link Core.impl.IPackageFragmentRootImpl#getIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link Core.impl.IPackageFragmentRootImpl#getPackageFragments <em>Package Fragments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IPackageFragmentRootImpl extends IJavaElementImpl implements IPackageFragmentRoot {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IJavaElementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IPackageFragmentRootImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IPACKAGE_FRAGMENT_ROOT;
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
		if ( data == null || !(data instanceof DataIPackageFragmentRoot))
			data = new DataIPackageFragmentRoot();
			
		if (((DataIPackageFragmentRoot)data).path == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH, null, null));
		return ((DataIPackageFragmentRoot)data).path;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
	
		if (data==null) data =  new DataIPackageFragmentRoot();
		
		else if (!(data instanceof DataIPackageFragmentRoot)) data = new DataIPackageFragmentRoot((DataIJavaElement)data);
	
		String oldPath = ((DataIPackageFragmentRoot)data).path;
		((DataIPackageFragmentRoot)data).path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH, oldPath, ((DataIPackageFragmentRoot)data).path));
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
		if ( data == null || !(data instanceof DataIPackageFragmentRoot))
			data = new DataIPackageFragmentRoot();
			
		if (((DataIPackageFragmentRoot)data).isReadOnly == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY, null, null));
		return ((DataIPackageFragmentRoot)data).isReadOnly;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReadOnly(Boolean newIsReadOnly) {
	
		if (data==null) data =  new DataIPackageFragmentRoot();
		
		else if (!(data instanceof DataIPackageFragmentRoot)) data = new DataIPackageFragmentRoot((DataIJavaElement)data);
	
		Boolean oldIsReadOnly = ((DataIPackageFragmentRoot)data).isReadOnly;
		((DataIPackageFragmentRoot)data).isReadOnly = newIsReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY, oldIsReadOnly, ((DataIPackageFragmentRoot)data).isReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IPackageFragment> getPackageFragments() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIPackageFragmentRoot))
			data = new DataIPackageFragmentRoot();
				
	   
		if (((DataIPackageFragmentRoot)data).packageFragments == null) {
			((DataIPackageFragmentRoot)data).packageFragments = new EObjectContainmentWithInverseEList<IPackageFragment>(IPackageFragment.class, this, CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS, CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS);			
		}
		return ((DataIPackageFragmentRoot)data).packageFragments;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY12
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		if (data==null) data = new DataIPackageFragmentRoot();
		switch (featureID) {
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPackageFragments()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				return ((InternalEList<?>)getPackageFragments()).basicRemove(otherEnd, msgs);
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
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH:
				return getPath();
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY:
				return getIsReadOnly();
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				return getPackageFragments();
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
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH:
				setPath((String)newValue);
				return;
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY:
				setIsReadOnly((Boolean)newValue);
				return;
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				getPackageFragments().clear();
				getPackageFragments().addAll((Collection<? extends IPackageFragment>)newValue);
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
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH:
				setPath(DataIPackageFragmentRoot.PATH_EDEFAULT);
				return;
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY:
				setIsReadOnly(DataIPackageFragmentRoot.IS_READ_ONLY_EDEFAULT);
				return;
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				getPackageFragments().clear();
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
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH:
				return DataIPackageFragmentRoot.PATH_EDEFAULT == null ? getPath() != null : !DataIPackageFragmentRoot.PATH_EDEFAULT.equals(getPath());
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY:
				return DataIPackageFragmentRoot.IS_READ_ONLY_EDEFAULT == null ? getIsReadOnly() != null : !DataIPackageFragmentRoot.IS_READ_ONLY_EDEFAULT.equals(getIsReadOnly());
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				return getPackageFragments() != null && !getPackageFragments().isEmpty();
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
		if (baseClass == PhysicalElement.class) {
			switch (derivedFeatureID) {
				case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH: return CorePackage.PHYSICAL_ELEMENT__PATH;
				case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY: return CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY;
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
		if (baseClass == PhysicalElement.class) {
			switch (baseFeatureID) {
				case CorePackage.PHYSICAL_ELEMENT__PATH: return CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH;
				case CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY: return CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY;
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
		if (data != null) result.append(((DataIPackageFragmentRoot)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataIPackageFragmentRoot extends DataIJavaElement {


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
	 * The cached value of the '{@link #getPackageFragments() <em>Package Fragments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageFragments()
	 * @generated
	 * @ordered
	 */
	protected EList<IPackageFragment> packageFragments;

	/**
	 *Constructor of DataIPackageFragmentRoot
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIPackageFragmentRoot() {
		super();
	}
	
		
	/**
	 *Constructor of DataIPackageFragmentRoot
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IJavaElement }
	 * @generated
	 */
	public DataIPackageFragmentRoot(DataIJavaElement data) {
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
		result.append(" (path: ");
		result.append(path);
		result.append(", isReadOnly: ");
		result.append(isReadOnly);
		result.append(')');
		return result.toString();
	}
		
}
} //IPackageFragmentRootImpl
