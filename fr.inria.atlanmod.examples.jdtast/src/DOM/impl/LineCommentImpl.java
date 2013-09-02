/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.LineComment;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Line Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class LineCommentImpl extends CommentImpl implements LineComment {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//CommentImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LineCommentImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.LINE_COMMENT;
	}




// data Class generation 
protected static  class DataLineComment extends DataComment {


	/**
	 *Constructor of DataLineComment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLineComment() {
		super();
	}
	
		
	/**
	 *Constructor of DataLineComment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Comment }
	 * @generated
	 */
	public DataLineComment(DataComment data) {
		super();		
		
		alternateRoot = data.alternateRoot;
				
				
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
} //LineCommentImpl
