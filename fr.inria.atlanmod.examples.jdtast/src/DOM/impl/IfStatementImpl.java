/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.IfStatement;
import DOM.Statement;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.IfStatementImpl#getElseStatement <em>Else Statement</em>}</li>
 *   <li>{@link DOM.impl.IfStatementImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link DOM.impl.IfStatementImpl#getThenStatement <em>Then Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfStatementImpl extends StatementImpl implements IfStatement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfStatementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.IF_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getElseStatement() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIfStatement))
			data = new DataIfStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.IF_STATEMENT__ELSE_STATEMENT, null, null));
		return ((DataIfStatement)data).elseStatement;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElseStatement(Statement newElseStatement, NotificationChain msgs) {
	
		if (data==null) data =  new DataIfStatement();
	
		Statement oldElseStatement = ((DataIfStatement)data).elseStatement;
		((DataIfStatement)data).elseStatement = newElseStatement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.IF_STATEMENT__ELSE_STATEMENT, oldElseStatement, newElseStatement);
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
	public void setElseStatement(Statement newElseStatement) {
	
		if (data==null) data =  new DataIfStatement();
		
		else if (!(data instanceof DataIfStatement)) data = new DataIfStatement((DataStatement)data);
	
		if (newElseStatement != ((DataIfStatement)data).elseStatement) {
			NotificationChain msgs = null;
			if (((DataIfStatement)data).elseStatement != null)
				msgs = ((InternalEObject) ((DataIfStatement)data).elseStatement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.IF_STATEMENT__ELSE_STATEMENT, null, msgs);
			if (newElseStatement != null)
				msgs = ((InternalEObject)newElseStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.IF_STATEMENT__ELSE_STATEMENT, null, msgs);
			msgs = basicSetElseStatement(newElseStatement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.IF_STATEMENT__ELSE_STATEMENT, newElseStatement, newElseStatement));
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
		if ( data == null || !(data instanceof DataIfStatement))
			data = new DataIfStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.IF_STATEMENT__EXPRESSION, null, null));
		return ((DataIfStatement)data).expression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataIfStatement();
	
		Expression oldExpression = ((DataIfStatement)data).expression;
		((DataIfStatement)data).expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.IF_STATEMENT__EXPRESSION, oldExpression, newExpression);
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
	
		if (data==null) data =  new DataIfStatement();
		
		else if (!(data instanceof DataIfStatement)) data = new DataIfStatement((DataStatement)data);
	
		if (newExpression != ((DataIfStatement)data).expression) {
			NotificationChain msgs = null;
			if (((DataIfStatement)data).expression != null)
				msgs = ((InternalEObject) ((DataIfStatement)data).expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.IF_STATEMENT__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.IF_STATEMENT__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.IF_STATEMENT__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getThenStatement() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataIfStatement))
			data = new DataIfStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.IF_STATEMENT__THEN_STATEMENT, null, null));
		return ((DataIfStatement)data).thenStatement;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThenStatement(Statement newThenStatement, NotificationChain msgs) {
	
		if (data==null) data =  new DataIfStatement();
	
		Statement oldThenStatement = ((DataIfStatement)data).thenStatement;
		((DataIfStatement)data).thenStatement = newThenStatement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.IF_STATEMENT__THEN_STATEMENT, oldThenStatement, newThenStatement);
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
	public void setThenStatement(Statement newThenStatement) {
	
		if (data==null) data =  new DataIfStatement();
		
		else if (!(data instanceof DataIfStatement)) data = new DataIfStatement((DataStatement)data);
	
		if (newThenStatement != ((DataIfStatement)data).thenStatement) {
			NotificationChain msgs = null;
			if (((DataIfStatement)data).thenStatement != null)
				msgs = ((InternalEObject) ((DataIfStatement)data).thenStatement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.IF_STATEMENT__THEN_STATEMENT, null, msgs);
			if (newThenStatement != null)
				msgs = ((InternalEObject)newThenStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.IF_STATEMENT__THEN_STATEMENT, null, msgs);
			msgs = basicSetThenStatement(newThenStatement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.IF_STATEMENT__THEN_STATEMENT, newThenStatement, newThenStatement));
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
			case DOMPackage.IF_STATEMENT__ELSE_STATEMENT:
				return basicSetElseStatement(null, msgs);
			case DOMPackage.IF_STATEMENT__EXPRESSION:
				return basicSetExpression(null, msgs);
			case DOMPackage.IF_STATEMENT__THEN_STATEMENT:
				return basicSetThenStatement(null, msgs);
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
			case DOMPackage.IF_STATEMENT__ELSE_STATEMENT:
				return getElseStatement();
			case DOMPackage.IF_STATEMENT__EXPRESSION:
				return getExpression();
			case DOMPackage.IF_STATEMENT__THEN_STATEMENT:
				return getThenStatement();
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
			case DOMPackage.IF_STATEMENT__ELSE_STATEMENT:
				setElseStatement((Statement)newValue);
				return;
			case DOMPackage.IF_STATEMENT__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case DOMPackage.IF_STATEMENT__THEN_STATEMENT:
				setThenStatement((Statement)newValue);
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
			case DOMPackage.IF_STATEMENT__ELSE_STATEMENT:
				setElseStatement((Statement)null);
				return;
			case DOMPackage.IF_STATEMENT__EXPRESSION:
				setExpression((Expression)null);
				return;
			case DOMPackage.IF_STATEMENT__THEN_STATEMENT:
				setThenStatement((Statement)null);
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
			case DOMPackage.IF_STATEMENT__ELSE_STATEMENT:
				return getElseStatement() != null;
			case DOMPackage.IF_STATEMENT__EXPRESSION:
				return getExpression() != null;
			case DOMPackage.IF_STATEMENT__THEN_STATEMENT:
				return getThenStatement() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataIfStatement extends DataStatement {


	/**
	 * The cached value of the '{@link #getElseStatement() <em>Else Statement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseStatement()
	 * @generated
	 * @ordered
	 */
	protected Statement elseStatement;

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
	 * The cached value of the '{@link #getThenStatement() <em>Then Statement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenStatement()
	 * @generated
	 * @ordered
	 */
	protected Statement thenStatement;

	/**
	 *Constructor of DataIfStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIfStatement() {
		super();
	}
	
		
	/**
	 *Constructor of DataIfStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataIfStatement(DataStatement data) {
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
} //IfStatementImpl
