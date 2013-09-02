/**
 *
 * $Id$
 */
package Core.impl;

import Core.BinaryPackageFragmentRoot;
import Core.CorePackage;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binary Package Fragment Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class BinaryPackageFragmentRootImpl extends IPackageFragmentRootImpl implements BinaryPackageFragmentRoot {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IPackageFragmentRootImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinaryPackageFragmentRootImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.BINARY_PACKAGE_FRAGMENT_ROOT;
	}




// data Class generation 
protected static  class DataBinaryPackageFragmentRoot extends DataIPackageFragmentRoot {


	/**
	 *Constructor of DataBinaryPackageFragmentRoot
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataBinaryPackageFragmentRoot() {
		super();
	}
	
		
	/**
	 *Constructor of DataBinaryPackageFragmentRoot
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IPackageFragmentRoot }
	 * @generated
	 */
	public DataBinaryPackageFragmentRoot(DataIPackageFragmentRoot data) {
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
} //BinaryPackageFragmentRootImpl
