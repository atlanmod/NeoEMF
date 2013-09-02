/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.Block;
import DOM.DOMPackage;
import DOM.Expression;
import DOM.SynchronizedStatement;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Synchronized Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.SynchronizedStatementImpl#getBody <em>Body</em>}</li>
 *   <li>{@link DOM.impl.SynchronizedStatementImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SynchronizedStatementImpl extends StatementImpl implements SynchronizedStatement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SynchronizedStatementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.SYNCHRONIZED_STATEMENT;
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
		if ( data == null || !(data instanceof DataSynchronizedStatement))
			data = new DataSynchronizedStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SYNCHRONIZED_STATEMENT__BODY, null, null));
		return ((DataSynchronizedStatement)data).body;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(Block newBody, NotificationChain msgs) {
	
		if (data==null) data =  new DataSynchronizedStatement();
	
		Block oldBody = ((DataSynchronizedStatement)data).body;
		((DataSynchronizedStatement)data).body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.SYNCHRONIZED_STATEMENT__BODY, oldBody, newBody);
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
	
		if (data==null) data =  new DataSynchronizedStatement();
		
		else if (!(data instanceof DataSynchronizedStatement)) data = new DataSynchronizedStatement((DataStatement)data);
	
		if (newBody != ((DataSynchronizedStatement)data).body) {
			NotificationChain msgs = null;
			if (((DataSynchronizedStatement)data).body != null)
				msgs = ((InternalEObject) ((DataSynchronizedStatement)data).body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SYNCHRONIZED_STATEMENT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SYNCHRONIZED_STATEMENT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SYNCHRONIZED_STATEMENT__BODY, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataSynchronizedStatement))
			data = new DataSynchronizedStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION, null, null));
		return ((DataSynchronizedStatement)data).expression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataSynchronizedStatement();
	
		Expression oldExpression = ((DataSynchronizedStatement)data).expression;
		((DataSynchronizedStatement)data).expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION, oldExpression, newExpression);
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
	public void setExpression(Expression newExpression) {
	
		if (data==null) data =  new DataSynchronizedStatement();
		
		else if (!(data instanceof DataSynchronizedStatement)) data = new DataSynchronizedStatement((DataStatement)data);
	
		if (newExpression != ((DataSynchronizedStatement)data).expression) {
			NotificationChain msgs = null;
			if (((DataSynchronizedStatement)data).expression != null)
				msgs = ((InternalEObject) ((DataSynchronizedStatement)data).expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION, newExpression, newExpression));
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
			case DOMPackage.SYNCHRONIZED_STATEMENT__BODY:
				return basicSetBody(null, msgs);
			case DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION:
				return basicSetExpression(null, msgs);
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
			case DOMPackage.SYNCHRONIZED_STATEMENT__BODY:
				return getBody();
			case DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION:
				return getExpression();
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
			case DOMPackage.SYNCHRONIZED_STATEMENT__BODY:
				setBody((Block)newValue);
				return;
			case DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION:
				setExpression((Expression)newValue);
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
			case DOMPackage.SYNCHRONIZED_STATEMENT__BODY:
				setBody((Block)null);
				return;
			case DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION:
				setExpression((Expression)null);
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
			case DOMPackage.SYNCHRONIZED_STATEMENT__BODY:
				return getBody() != null;
			case DOMPackage.SYNCHRONIZED_STATEMENT__EXPRESSION:
				return getExpression() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataSynchronizedStatement extends DataStatement {


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
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 *Constructor of DataSynchronizedStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSynchronizedStatement() {
		super();
	}
	
		
	/**
	 *Constructor of DataSynchronizedStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataSynchronizedStatement(DataStatement data) {
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
} //SynchronizedStatementImpl
