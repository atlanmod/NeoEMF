/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.ASTNode;
import DOM.Comment;
import DOM.DOMPackage;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.CommentImpl#getAlternateRoot <em>Alternate Root</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CommentImpl extends ASTNodeImpl implements Comment {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommentImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.COMMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode getAlternateRoot() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataComment))
			data = new DataComment();
				
	  
		if (((DataComment)data).alternateRoot == null && getNodeId()>-1) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.COMMENT__ALTERNATE_ROOT);
		}		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.COMMENT__ALTERNATE_ROOT, null, null));
		return ((DataComment)data).alternateRoot;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode basicGetAlternateRoot() {
		return data != null ? ((DataComment)data).alternateRoot : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlternateRoot(ASTNode newAlternateRoot) {
	
		if (data==null) data =  new DataComment();
		
		else if (!(data instanceof DataComment)) data = new DataComment((DataASTNode)data);
	
		ASTNode oldAlternateRoot = ((DataComment)data).alternateRoot;
		((DataComment)data).alternateRoot = newAlternateRoot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.COMMENT__ALTERNATE_ROOT, oldAlternateRoot, ((DataComment)data).alternateRoot));
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
			case DOMPackage.COMMENT__ALTERNATE_ROOT:
				if (resolve) return getAlternateRoot();
				return basicGetAlternateRoot();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.COMMENT__ALTERNATE_ROOT:
				setAlternateRoot((ASTNode)newValue);
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
			case DOMPackage.COMMENT__ALTERNATE_ROOT:
				setAlternateRoot((ASTNode)null);
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
			case DOMPackage.COMMENT__ALTERNATE_ROOT:
				return getAlternateRoot() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataComment extends DataASTNode {


	/**
	 * The cached value of the '{@link #getAlternateRoot() <em>Alternate Root</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlternateRoot()
	 * @generated
	 * @ordered
	 */
	protected ASTNode alternateRoot;

	/**
	 *Constructor of DataComment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataComment() {
		super();
	}
	
		
	/**
	 *Constructor of DataComment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataComment(DataASTNode data) {
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
} //CommentImpl
