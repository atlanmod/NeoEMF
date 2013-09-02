/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IJavaProject;
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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IJava Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.IJavaProjectImpl#getPath <em>Path</em>}</li>
 *   <li>{@link Core.impl.IJavaProjectImpl#getIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link Core.impl.IJavaProjectImpl#getPackageFragmentRoots <em>Package Fragment Roots</em>}</li>
 *   <li>{@link Core.impl.IJavaProjectImpl#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}</li>
 *   <li>{@link Core.impl.IJavaProjectImpl#getRequiredProjects <em>Required Projects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IJavaProjectImpl extends IJavaElementImpl implements IJavaProject {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IJavaElementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IJavaProjectImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IJAVA_PROJECT;
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
		if ( data == null || !(data instanceof DataIJavaProject))
			data = new DataIJavaProject();
			
		if (((DataIJavaProject)data).path == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IJAVA_PROJECT__PATH, null, null));
		return ((DataIJavaProject)data).path;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
	
		if (data==null) data =  new DataIJavaProject();
		
		else if (!(data instanceof DataIJavaProject)) data = new DataIJavaProject((DataIJavaElement)data);
	
		String oldPath = ((DataIJavaProject)data).path;
		((DataIJavaProject)data).path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IJAVA_PROJECT__PATH, oldPath, ((DataIJavaProject)data).path));
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
		if ( data == null || !(data instanceof DataIJavaProject))
			data = new DataIJavaProject();
			
		if (((DataIJavaProject)data).isReadOnly == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, CorePackage.IJAVA_PROJECT__IS_READ_ONLY, null, null));
		return ((DataIJavaProject)data).isReadOnly;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReadOnly(Boolean newIsReadOnly) {
	
		if (data==null) data =  new DataIJavaProject();
		
		else if (!(data instanceof DataIJavaProject)) data = new DataIJavaProject((DataIJavaElement)data);
	
		Boolean oldIsReadOnly = ((DataIJavaProject)data).isReadOnly;
		((DataIJavaProject)data).isReadOnly = newIsReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IJAVA_PROJECT__IS_READ_ONLY, oldIsReadOnly, ((DataIJavaProject)data).isReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IPackageFragmentRoot> getPackageFragmentRoots() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIJavaProject))
			data = new DataIJavaProject();
				
	   
		if (((DataIJavaProject)data).packageFragmentRoots == null) {
			((DataIJavaProject)data).packageFragmentRoots = new EObjectContainmentEList<IPackageFragmentRoot>(IPackageFragmentRoot.class, this, CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS);			
		}
		return ((DataIJavaProject)data).packageFragmentRoots;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IPackageFragmentRoot> getExternalPackageFragmentRoots() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIJavaProject))
			data = new DataIJavaProject();
				
	   
		if (((DataIJavaProject)data).externalPackageFragmentRoots == null) {
			((DataIJavaProject)data).externalPackageFragmentRoots = new EObjectResolvingEList<IPackageFragmentRoot>(IPackageFragmentRoot.class, this, CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS);			
		}
		return ((DataIJavaProject)data).externalPackageFragmentRoots;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IJavaProject> getRequiredProjects() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIJavaProject))
			data = new DataIJavaProject();
				
	   
		if (((DataIJavaProject)data).requiredProjects == null) {
			((DataIJavaProject)data).requiredProjects = new EObjectResolvingEList<IJavaProject>(IJavaProject.class, this, CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS);			
		}
		return ((DataIJavaProject)data).requiredProjects;	
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
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				return ((InternalEList<?>)getPackageFragmentRoots()).basicRemove(otherEnd, msgs);
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
			case CorePackage.IJAVA_PROJECT__PATH:
				return getPath();
			case CorePackage.IJAVA_PROJECT__IS_READ_ONLY:
				return getIsReadOnly();
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				return getPackageFragmentRoots();
			case CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return getExternalPackageFragmentRoots();
			case CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS:
				return getRequiredProjects();
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
			case CorePackage.IJAVA_PROJECT__PATH:
				setPath((String)newValue);
				return;
			case CorePackage.IJAVA_PROJECT__IS_READ_ONLY:
				setIsReadOnly((Boolean)newValue);
				return;
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				getPackageFragmentRoots().clear();
				getPackageFragmentRoots().addAll((Collection<? extends IPackageFragmentRoot>)newValue);
				return;
			case CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				getExternalPackageFragmentRoots().clear();
				getExternalPackageFragmentRoots().addAll((Collection<? extends IPackageFragmentRoot>)newValue);
				return;
			case CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS:
				getRequiredProjects().clear();
				getRequiredProjects().addAll((Collection<? extends IJavaProject>)newValue);
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
			case CorePackage.IJAVA_PROJECT__PATH:
				setPath(DataIJavaProject.PATH_EDEFAULT);
				return;
			case CorePackage.IJAVA_PROJECT__IS_READ_ONLY:
				setIsReadOnly(DataIJavaProject.IS_READ_ONLY_EDEFAULT);
				return;
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				getPackageFragmentRoots().clear();
				return;
			case CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				getExternalPackageFragmentRoots().clear();
				return;
			case CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS:
				getRequiredProjects().clear();
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
			case CorePackage.IJAVA_PROJECT__PATH:
				return DataIJavaProject.PATH_EDEFAULT == null ? getPath() != null : !DataIJavaProject.PATH_EDEFAULT.equals(getPath());
			case CorePackage.IJAVA_PROJECT__IS_READ_ONLY:
				return DataIJavaProject.IS_READ_ONLY_EDEFAULT == null ? getIsReadOnly() != null : !DataIJavaProject.IS_READ_ONLY_EDEFAULT.equals(getIsReadOnly());
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				return getPackageFragmentRoots() != null && !getPackageFragmentRoots().isEmpty();
			case CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return getExternalPackageFragmentRoots() != null && !getExternalPackageFragmentRoots().isEmpty();
			case CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS:
				return getRequiredProjects() != null && !getRequiredProjects().isEmpty();
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
				case CorePackage.IJAVA_PROJECT__PATH: return CorePackage.PHYSICAL_ELEMENT__PATH;
				case CorePackage.IJAVA_PROJECT__IS_READ_ONLY: return CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY;
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
				case CorePackage.PHYSICAL_ELEMENT__PATH: return CorePackage.IJAVA_PROJECT__PATH;
				case CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY: return CorePackage.IJAVA_PROJECT__IS_READ_ONLY;
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
		if (data != null) result.append(((DataIJavaProject)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataIJavaProject extends DataIJavaElement {


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
	 * The cached value of the '{@link #getPackageFragmentRoots() <em>Package Fragment Roots</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageFragmentRoots()
	 * @generated
	 * @ordered
	 */
	protected EList<IPackageFragmentRoot> packageFragmentRoots;

	/**
	 * The cached value of the '{@link #getExternalPackageFragmentRoots() <em>External Package Fragment Roots</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPackageFragmentRoots()
	 * @generated
	 * @ordered
	 */
	protected EList<IPackageFragmentRoot> externalPackageFragmentRoots;

	/**
	 * The cached value of the '{@link #getRequiredProjects() <em>Required Projects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredProjects()
	 * @generated
	 * @ordered
	 */
	protected EList<IJavaProject> requiredProjects;

	/**
	 *Constructor of DataIJavaProject
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIJavaProject() {
		super();
	}
	
		
	/**
	 *Constructor of DataIJavaProject
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IJavaElement }
	 * @generated
	 */
	public DataIJavaProject(DataIJavaElement data) {
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
} //IJavaProjectImpl
