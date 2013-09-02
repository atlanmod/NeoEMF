/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.BlockComment;
import DOM.DOMPackage;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class BlockCommentImpl extends CommentImpl implements BlockComment {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//CommentImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlockCommentImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.BLOCK_COMMENT;
	}




// data Class generation 
protected static  class DataBlockComment extends DataComment {


	/**
	 *Constructor of DataBlockComment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataBlockComment() {
		super();
	}
	
		
	/**
	 *Constructor of DataBlockComment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Comment }
	 * @generated
	 */
	public DataBlockComment(DataComment data) {
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
} //BlockCommentImpl
