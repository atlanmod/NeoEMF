/**
 *
 * $Id$
 */
package Core.impl;

import Core.CorePackage;
import Core.IInitializer;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IInitializer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class IInitializerImpl extends IMemberImpl implements IInitializer {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//IMemberImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IInitializerImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IINITIALIZER;
	}




// data Class generation 
protected static  class DataIInitializer extends DataIMember {


	/**
	 *Constructor of DataIInitializer
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIInitializer() {
		super();
	}
	
		
	/**
	 *Constructor of DataIInitializer
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link IMember }
	 * @generated
	 */
	public DataIInitializer(DataIMember data) {
		super();		
		
		source = data.source;
				
		sourceRange = data.sourceRange;
				
		javadocRange = data.javadocRange;
				
		nameRange = data.nameRange;
				
				
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
} //IInitializerImpl
