/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.NullLiteral;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Null Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class NullLiteralImpl extends ExpressionImpl implements NullLiteral {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NullLiteralImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.NULL_LITERAL;
	}




// data Class generation 
protected static  class DataNullLiteral extends DataExpression {


	/**
	 *Constructor of DataNullLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataNullLiteral() {
		super();
	}
	
		
	/**
	 *Constructor of DataNullLiteral
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataNullLiteral(DataExpression data) {
		super();		
		
		resolveBoxing = data.resolveBoxing;
				
		resolveUnboxing = data.resolveUnboxing;
				
		typeBinding = data.typeBinding;
				
				
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
} //NullLiteralImpl
