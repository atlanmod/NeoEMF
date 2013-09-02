/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.Block;
import DOM.CatchClause;
import DOM.DOMPackage;
import DOM.SingleVariableDeclaration;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Catch Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.CatchClauseImpl#getBody <em>Body</em>}</li>
 *   <li>{@link DOM.impl.CatchClauseImpl#getException <em>Exception</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CatchClauseImpl extends ASTNodeImpl implements CatchClause {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ASTNodeImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CatchClauseImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.CATCH_CLAUSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBody() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataCatchClause))
			data = new DataCatchClause();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CATCH_CLAUSE__BODY, null, null));
		return ((DataCatchClause)data).body;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(Block newBody, NotificationChain msgs) {
	
		if (data==null) data =  new DataCatchClause();
	
		Block oldBody = ((DataCatchClause)data).body;
		((DataCatchClause)data).body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.CATCH_CLAUSE__BODY, oldBody, newBody);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(Block newBody) {
	
		if (data==null) data =  new DataCatchClause();
		
		else if (!(data instanceof DataCatchClause)) data = new DataCatchClause((DataASTNode)data);
	
		if (newBody != ((DataCatchClause)data).body) {
			NotificationChain msgs = null;
			if (((DataCatchClause)data).body != null)
				msgs = ((InternalEObject) ((DataCatchClause)data).body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CATCH_CLAUSE__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CATCH_CLAUSE__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CATCH_CLAUSE__BODY, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleVariableDeclaration getException() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataCatchClause))
			data = new DataCatchClause();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CATCH_CLAUSE__EXCEPTION, null, null));
		return ((DataCatchClause)data).exception;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetException(SingleVariableDeclaration newException, NotificationChain msgs) {
	
		if (data==null) data =  new DataCatchClause();
	
		SingleVariableDeclaration oldException = ((DataCatchClause)data).exception;
		((DataCatchClause)data).exception = newException;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.CATCH_CLAUSE__EXCEPTION, oldException, newException);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setException(SingleVariableDeclaration newException) {
	
		if (data==null) data =  new DataCatchClause();
		
		else if (!(data instanceof DataCatchClause)) data = new DataCatchClause((DataASTNode)data);
	
		if (newException != ((DataCatchClause)data).exception) {
			NotificationChain msgs = null;
			if (((DataCatchClause)data).exception != null)
				msgs = ((InternalEObject) ((DataCatchClause)data).exception).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CATCH_CLAUSE__EXCEPTION, null, msgs);
			if (newException != null)
				msgs = ((InternalEObject)newException).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CATCH_CLAUSE__EXCEPTION, null, msgs);
			msgs = basicSetException(newException, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CATCH_CLAUSE__EXCEPTION, newException, newException));
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
			case DOMPackage.CATCH_CLAUSE__BODY:
				return basicSetBody(null, msgs);
			case DOMPackage.CATCH_CLAUSE__EXCEPTION:
				return basicSetException(null, msgs);
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
			case DOMPackage.CATCH_CLAUSE__BODY:
				return getBody();
			case DOMPackage.CATCH_CLAUSE__EXCEPTION:
				return getException();
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
			case DOMPackage.CATCH_CLAUSE__BODY:
				setBody((Block)newValue);
				return;
			case DOMPackage.CATCH_CLAUSE__EXCEPTION:
				setException((SingleVariableDeclaration)newValue);
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
			case DOMPackage.CATCH_CLAUSE__BODY:
				setBody((Block)null);
				return;
			case DOMPackage.CATCH_CLAUSE__EXCEPTION:
				setException((SingleVariableDeclaration)null);
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
			case DOMPackage.CATCH_CLAUSE__BODY:
				return getBody() != null;
			case DOMPackage.CATCH_CLAUSE__EXCEPTION:
				return getException() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataCatchClause extends DataASTNode {


	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected Block body;

	/**
	 * The cached value of the '{@link #getException() <em>Exception</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getException()
	 * @generated
	 * @ordered
	 */
	protected SingleVariableDeclaration exception;

	/**
	 *Constructor of DataCatchClause
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataCatchClause() {
		super();
	}
	
		
	/**
	 *Constructor of DataCatchClause
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	public DataCatchClause(DataASTNode data) {
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
} //CatchClauseImpl
