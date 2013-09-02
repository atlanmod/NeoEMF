/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IClassFile;
import Core.ICompilationUnit;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IPackage Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.IPackageFragmentImpl#getPath <em>Path</em>}</li>
 *   <li>{@link Core.impl.IPackageFragmentImpl#getIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link Core.impl.IPackageFragmentImpl#getIsDefaultPackage <em>Is Default Package</em>}</li>
 *   <li>{@link Core.impl.IPackageFragmentImpl#getPackageFragmentRoot <em>Package Fragment Root</em>}</li>
 *   <li>{@link Core.impl.IPackageFragmentImpl#getClassFiles <em>Class Files</em>}</li>
 *   <li>{@link Core.impl.IPackageFragmentImpl#getCompilationUnits <em>Compilation Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IPackageFragmentImpl extends IJavaElementImpl implements IPackageFragment {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IJavaElementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IPackageFragmentImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IPACKAGE_FRAGMENT;
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
		if ( data == null || !(data instanceof DataIPackageFragment))
			data = new DataIPackageFragment();
			
		if (((DataIPackageFragment)data).path == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IPACKAGE_FRAGMENT__PATH, null, null));
		return ((DataIPackageFragment)data).path;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
	
		if (data==null) data =  new DataIPackageFragment();
		
		else if (!(data instanceof DataIPackageFragment)) data = new DataIPackageFragment((DataIJavaElement)data);
	
		String oldPath = ((DataIPackageFragment)data).path;
		((DataIPackageFragment)data).path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IPACKAGE_FRAGMENT__PATH, oldPath, ((DataIPackageFragment)data).path));
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
		if ( data == null || !(data instanceof DataIPackageFragment))
			data = new DataIPackageFragment();
			
		if (((DataIPackageFragment)data).isReadOnly == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IPACKAGE_FRAGMENT__IS_READ_ONLY, null, null));
		return ((DataIPackageFragment)data).isReadOnly;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReadOnly(Boolean newIsReadOnly) {
	
		if (data==null) data =  new DataIPackageFragment();
		
		else if (!(data instanceof DataIPackageFragment)) data = new DataIPackageFragment((DataIJavaElement)data);
	
		Boolean oldIsReadOnly = ((DataIPackageFragment)data).isReadOnly;
		((DataIPackageFragment)data).isReadOnly = newIsReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IPACKAGE_FRAGMENT__IS_READ_ONLY, oldIsReadOnly, ((DataIPackageFragment)data).isReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsDefaultPackage() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIPackageFragment))
			data = new DataIPackageFragment();
			
		if (((DataIPackageFragment)data).isDefaultPackage == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IPACKAGE_FRAGMENT__IS_DEFAULT_PACKAGE, null, null));
		return ((DataIPackageFragment)data).isDefaultPackage;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsDefaultPackage(Boolean newIsDefaultPackage) {
	
		if (data==null) data =  new DataIPackageFragment();
		
		else if (!(data instanceof DataIPackageFragment)) data = new DataIPackageFragment((DataIJavaElement)data);
	
		Boolean oldIsDefaultPackage = ((DataIPackageFragment)data).isDefaultPackage;
		((DataIPackageFragment)data).isDefaultPackage = newIsDefaultPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IPACKAGE_FRAGMENT__IS_DEFAULT_PACKAGE, oldIsDefaultPackage, ((DataIPackageFragment)data).isDefaultPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackageFragmentRoot getPackageFragmentRoot() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIPackageFragment))
			data = new DataIPackageFragment();
				
	  
		if (eContainerFeatureID() != CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT) return null;
		return (IPackageFragmentRoot)eContainer();	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackageFragmentRoot(IPackageFragmentRoot newPackageFragmentRoot, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newPackageFragmentRoot, CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageFragmentRoot(IPackageFragmentRoot newPackageFragmentRoot) {
	
		if (data==null) data =  new DataIPackageFragment();
		
		else if (!(data instanceof DataIPackageFragment)) data = new DataIPackageFragment((DataIJavaElement)data);
	
		if (newPackageFragmentRoot != eInternalContainer() || (eContainerFeatureID() != CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT && newPackageFragmentRoot != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newPackageFragmentRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPackageFragmentRoot != null)
				msgs = ((InternalEObject)newPackageFragmentRoot).eInverseAdd(this, CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS, IPackageFragmentRoot.class, msgs);
			msgs = basicSetPackageFragmentRoot(newPackageFragmentRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT, newPackageFragmentRoot, newPackageFragmentRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IClassFile> getClassFiles() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIPackageFragment))
			data = new DataIPackageFragment();
				
	   
		if (((DataIPackageFragment)data).classFiles == null) {
			((DataIPackageFragment)data).classFiles = new EObjectContainmentEList<IClassFile>(IClassFile.class, this, CorePackage.IPACKAGE_FRAGMENT__CLASS_FILES);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IPACKAGE_FRAGMENT__CLASS_FILES);			
		}
		return ((DataIPackageFragment)data).classFiles;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ICompilationUnit> getCompilationUnits() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIPackageFragment))
			data = new DataIPackageFragment();
				
	   
		if (((DataIPackageFragment)data).compilationUnits == null) {
			((DataIPackageFragment)data).compilationUnits = new EObjectContainmentEList<ICompilationUnit>(ICompilationUnit.class, this, CorePackage.IPACKAGE_FRAGMENT__COMPILATION_UNITS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IPACKAGE_FRAGMENT__COMPILATION_UNITS);			
		}
		return ((DataIPackageFragment)data).compilationUnits;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY12
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		if (data==null) data = new DataIPackageFragment();
		switch (featureID) {
			case CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPackageFragmentRoot((IPackageFragmentRoot)otherEnd, msgs);
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
			case CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT:
				return basicSetPackageFragmentRoot(null, msgs);
			case CorePackage.IPACKAGE_FRAGMENT__CLASS_FILES:
				return ((InternalEList<?>)getClassFiles()).basicRemove(otherEnd, msgs);
			case CorePackage.IPACKAGE_FRAGMENT__COMPILATION_UNITS:
				return ((InternalEList<?>)getCompilationUnits()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY14
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT:
				return eInternalContainer().eInverseRemove(this, CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS, IPackageFragmentRoot.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case CorePackage.IPACKAGE_FRAGMENT__PATH:
				return getPath();
			case CorePackage.IPACKAGE_FRAGMENT__IS_READ_ONLY:
				return getIsReadOnly();
			case CorePackage.IPACKAGE_FRAGMENT__IS_DEFAULT_PACKAGE:
				return getIsDefaultPackage();
			case CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT:
				return getPackageFragmentRoot();
			case CorePackage.IPACKAGE_FRAGMENT__CLASS_FILES:
				return getClassFiles();
			case CorePackage.IPACKAGE_FRAGMENT__COMPILATION_UNITS:
				return getCompilationUnits();
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
			case CorePackage.IPACKAGE_FRAGMENT__PATH:
				setPath((String)newValue);
				return;
			case CorePackage.IPACKAGE_FRAGMENT__IS_READ_ONLY:
				setIsReadOnly((Boolean)newValue);
				return;
			case CorePackage.IPACKAGE_FRAGMENT__IS_DEFAULT_PACKAGE:
				setIsDefaultPackage((Boolean)newValue);
				return;
			case CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT:
				setPackageFragmentRoot((IPackageFragmentRoot)newValue);
				return;
			case CorePackage.IPACKAGE_FRAGMENT__CLASS_FILES:
				getClassFiles().clear();
				getClassFiles().addAll((Collection<? extends IClassFile>)newValue);
				return;
			case CorePackage.IPACKAGE_FRAGMENT__COMPILATION_UNITS:
				getCompilationUnits().clear();
				getCompilationUnits().addAll((Collection<? extends ICompilationUnit>)newValue);
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
			case CorePackage.IPACKAGE_FRAGMENT__PATH:
				setPath(DataIPackageFragment.PATH_EDEFAULT);
				return;
			case CorePackage.IPACKAGE_FRAGMENT__IS_READ_ONLY:
				setIsReadOnly(DataIPackageFragment.IS_READ_ONLY_EDEFAULT);
				return;
			case CorePackage.IPACKAGE_FRAGMENT__IS_DEFAULT_PACKAGE:
				setIsDefaultPackage(DataIPackageFragment.IS_DEFAULT_PACKAGE_EDEFAULT);
				return;
			case CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT:
				setPackageFragmentRoot((IPackageFragmentRoot)null);
				return;
			case CorePackage.IPACKAGE_FRAGMENT__CLASS_FILES:
				getClassFiles().clear();
				return;
			case CorePackage.IPACKAGE_FRAGMENT__COMPILATION_UNITS:
				getCompilationUnits().clear();
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
			case CorePackage.IPACKAGE_FRAGMENT__PATH:
				return DataIPackageFragment.PATH_EDEFAULT == null ? getPath() != null : !DataIPackageFragment.PATH_EDEFAULT.equals(getPath());
			case CorePackage.IPACKAGE_FRAGMENT__IS_READ_ONLY:
				return DataIPackageFragment.IS_READ_ONLY_EDEFAULT == null ? getIsReadOnly() != null : !DataIPackageFragment.IS_READ_ONLY_EDEFAULT.equals(getIsReadOnly());
			case CorePackage.IPACKAGE_FRAGMENT__IS_DEFAULT_PACKAGE:
				return DataIPackageFragment.IS_DEFAULT_PACKAGE_EDEFAULT == null ? getIsDefaultPackage() != null : !DataIPackageFragment.IS_DEFAULT_PACKAGE_EDEFAULT.equals(getIsDefaultPackage());
			case CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT:
				return getPackageFragmentRoot() != null;
			case CorePackage.IPACKAGE_FRAGMENT__CLASS_FILES:
				return getClassFiles() != null && !getClassFiles().isEmpty();
			case CorePackage.IPACKAGE_FRAGMENT__COMPILATION_UNITS:
				return getCompilationUnits() != null && !getCompilationUnits().isEmpty();
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
				case CorePackage.IPACKAGE_FRAGMENT__PATH: return CorePackage.PHYSICAL_ELEMENT__PATH;
				case CorePackage.IPACKAGE_FRAGMENT__IS_READ_ONLY: return CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY;
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
				case CorePackage.PHYSICAL_ELEMENT__PATH: return CorePackage.IPACKAGE_FRAGMENT__PATH;
				case CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY: return CorePackage.IPACKAGE_FRAGMENT__IS_READ_ONLY;
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
		if (data != null) result.append(((DataIPackageFragment)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataIPackageFragment extends DataIJavaElement {


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
	 * The default value of the '{@link #getIsDefaultPackage() <em>Is Default Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDefaultPackage()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_DEFAULT_PACKAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsDefaultPackage() <em>Is Default Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDefaultPackage()
	 * @generated
	 * @ordered
	 */
	protected Boolean isDefaultPackage = IS_DEFAULT_PACKAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getClassFiles() <em>Class Files</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassFiles()
	 * @generated
	 * @ordered
	 */
	protected EList<IClassFile> classFiles;

	/**
	 * The cached value of the '{@link #getCompilationUnits() <em>Compilation Units</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompilationUnits()
	 * @generated
	 * @ordered
	 */
	protected EList<ICompilationUnit> compilationUnits;

	/**
	 *Constructor of DataIPackageFragment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIPackageFragment() {
		super();
	}
	
		
	/**
	 *Constructor of DataIPackageFragment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IJavaElement }
	 * @generated
	 */
	public DataIPackageFragment(DataIJavaElement data) {
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
		result.append(", isDefaultPackage: ");
		result.append(isDefaultPackage);
		result.append(')');
		return result.toString();
	}
		
}
} //IPackageFragmentImpl
