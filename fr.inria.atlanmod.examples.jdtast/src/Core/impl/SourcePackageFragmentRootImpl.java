/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.SourcePackageFragmentRoot;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source Package Fragment Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SourcePackageFragmentRootImpl extends IPackageFragmentRootImpl implements SourcePackageFragmentRoot {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IPackageFragmentRootImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SourcePackageFragmentRootImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.SOURCE_PACKAGE_FRAGMENT_ROOT;
	}




// data Class generation 
protected static  class DataSourcePackageFragmentRoot extends DataIPackageFragmentRoot {


	/**
	 *Constructor of DataSourcePackageFragmentRoot
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSourcePackageFragmentRoot() {
		super();
	}
	
		
	/**
	 *Constructor of DataSourcePackageFragmentRoot
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IPackageFragmentRoot }
	 * @generated
	 */
	public DataSourcePackageFragmentRoot(DataIPackageFragmentRoot data) {
		super();		
		
		path = data.path;
				
		isReadOnly = data.isReadOnly;
				
		packageFragments = data.packageFragments;
				
				
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
} //SourcePackageFragmentRootImpl
