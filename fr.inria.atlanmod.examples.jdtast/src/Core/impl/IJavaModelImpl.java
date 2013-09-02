/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IJavaModel;
import Core.IJavaProject;
import Core.IPackageFragmentRoot;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IJava Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Core.impl.IJavaModelImpl#getJavaProjects <em>Java Projects</em>}</li>
 *   <li>{@link Core.impl.IJavaModelImpl#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IJavaModelImpl extends PhysicalElementImpl implements IJavaModel {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//PhysicalElementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IJavaModelImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IJAVA_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IJavaProject> getJavaProjects() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIJavaModel))
			data = new DataIJavaModel();
				
	   
		if (((DataIJavaModel)data).javaProjects == null) {
			((DataIJavaModel)data).javaProjects = new EObjectContainmentEList<IJavaProject>(IJavaProject.class, this, CorePackage.IJAVA_MODEL__JAVA_PROJECTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IJAVA_MODEL__JAVA_PROJECTS);			
		}
		return ((DataIJavaModel)data).javaProjects;	
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
		if ( data == null || !(data instanceof DataIJavaModel))
			data = new DataIJavaModel();
				
	   
		if (((DataIJavaModel)data).externalPackageFragmentRoots == null) {
			((DataIJavaModel)data).externalPackageFragmentRoots = new EObjectContainmentEList<IPackageFragmentRoot>(IPackageFragmentRoot.class, this, CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS);			
		}
		return ((DataIJavaModel)data).externalPackageFragmentRoots;	
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
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				return ((InternalEList<?>)getJavaProjects()).basicRemove(otherEnd, msgs);
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return ((InternalEList<?>)getExternalPackageFragmentRoots()).basicRemove(otherEnd, msgs);
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
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				return getJavaProjects();
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return getExternalPackageFragmentRoots();
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
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				getJavaProjects().clear();
				getJavaProjects().addAll((Collection<? extends IJavaProject>)newValue);
				return;
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				getExternalPackageFragmentRoots().clear();
				getExternalPackageFragmentRoots().addAll((Collection<? extends IPackageFragmentRoot>)newValue);
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
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				getJavaProjects().clear();
				return;
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				getExternalPackageFragmentRoots().clear();
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
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				return getJavaProjects() != null && !getJavaProjects().isEmpty();
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return getExternalPackageFragmentRoots() != null && !getExternalPackageFragmentRoots().isEmpty();
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataIJavaModel extends DataPhysicalElement {


	/**
	 * The cached value of the '{@link #getJavaProjects() <em>Java Projects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaProjects()
	 * @generated
	 * @ordered
	 */
	protected EList<IJavaProject> javaProjects;

	/**
	 * The cached value of the '{@link #getExternalPackageFragmentRoots() <em>External Package Fragment Roots</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPackageFragmentRoots()
	 * @generated
	 * @ordered
	 */
	protected EList<IPackageFragmentRoot> externalPackageFragmentRoots;

	/**
	 *Constructor of DataIJavaModel
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIJavaModel() {
		super();
	}
	
		
	/**
	 *Constructor of DataIJavaModel
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link PhysicalElement }
	 * @generated
	 */
	public DataIJavaModel(DataPhysicalElement data) {
		super();		
		
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
		result.append(')');
		return result.toString();
	}
		
}
} //IJavaModelImpl
