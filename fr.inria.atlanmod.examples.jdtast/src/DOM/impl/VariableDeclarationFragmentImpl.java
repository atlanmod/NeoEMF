/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.VariableDeclarationFragment;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class VariableDeclarationFragmentImpl extends VariableDeclarationImpl implements VariableDeclarationFragment {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//VariableDeclarationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationFragmentImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.VARIABLE_DECLARATION_FRAGMENT;
	}




// data Class generation 
protected static  class DataVariableDeclarationFragment extends DataVariableDeclaration {


	/**
	 *Constructor of DataVariableDeclarationFragment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableDeclarationFragment() {
		super();
	}
	
		
	/**
	 *Constructor of DataVariableDeclarationFragment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link VariableDeclaration }
	 * @generated
	 */
	public DataVariableDeclarationFragment(DataVariableDeclaration data) {
		super();		
		
		extraDimensions = data.extraDimensions;
				
		initializer = data.initializer;
				
		name = data.name;
				
				
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
} //VariableDeclarationFragmentImpl
