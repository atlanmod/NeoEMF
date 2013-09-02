/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.EmptyStatement;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Empty Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class EmptyStatementImpl extends StatementImpl implements EmptyStatement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EmptyStatementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.EMPTY_STATEMENT;
	}




// data Class generation 
protected static  class DataEmptyStatement extends DataStatement {


	/**
	 *Constructor of DataEmptyStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataEmptyStatement() {
		super();
	}
	
		
	/**
	 *Constructor of DataEmptyStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataEmptyStatement(DataStatement data) {
		super();		
		
				
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
} //EmptyStatementImpl
