/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.ForStatement;
import DOM.Statement;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>For Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ForStatementImpl#getBody <em>Body</em>}</li>
 *   <li>{@link DOM.impl.ForStatementImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link DOM.impl.ForStatementImpl#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link DOM.impl.ForStatementImpl#getUpdaters <em>Updaters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForStatementImpl extends StatementImpl implements ForStatement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForStatementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.FOR_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getBody() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataForStatement))
			data = new DataForStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.FOR_STATEMENT__BODY, null, null));
		return ((DataForStatement)data).body;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(Statement newBody, NotificationChain msgs) {
	
		if (data==null) data =  new DataForStatement();
	
		Statement oldBody = ((DataForStatement)data).body;
		((DataForStatement)data).body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.FOR_STATEMENT__BODY, oldBody, newBody);
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
	public void setBody(Statement newBody) {
	
		if (data==null) data =  new DataForStatement();
		
		else if (!(data instanceof DataForStatement)) data = new DataForStatement((DataStatement)data);
	
		if (newBody != ((DataForStatement)data).body) {
			NotificationChain msgs = null;
			if (((DataForStatement)data).body != null)
				msgs = ((InternalEObject) ((DataForStatement)data).body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.FOR_STATEMENT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.FOR_STATEMENT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.FOR_STATEMENT__BODY, newBody, newBody));
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
		if ( data == null || !(data instanceof DataForStatement))
			data = new DataForStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.FOR_STATEMENT__EXPRESSION, null, null));
		return ((DataForStatement)data).expression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataForStatement();
	
		Expression oldExpression = ((DataForStatement)data).expression;
		((DataForStatement)data).expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.FOR_STATEMENT__EXPRESSION, oldExpression, newExpression);
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
	
		if (data==null) data =  new DataForStatement();
		
		else if (!(data instanceof DataForStatement)) data = new DataForStatement((DataStatement)data);
	
		if (newExpression != ((DataForStatement)data).expression) {
			NotificationChain msgs = null;
			if (((DataForStatement)data).expression != null)
				msgs = ((InternalEObject) ((DataForStatement)data).expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.FOR_STATEMENT__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.FOR_STATEMENT__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.FOR_STATEMENT__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getInitializers() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataForStatement))
			data = new DataForStatement();
				
	   
		if (((DataForStatement)data).initializers == null) {
			((DataForStatement)data).initializers = new EObjectContainmentEList<Expression>(Expression.class, this, DOMPackage.FOR_STATEMENT__INITIALIZERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.FOR_STATEMENT__INITIALIZERS);			
		}
		return ((DataForStatement)data).initializers;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getUpdaters() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataForStatement))
			data = new DataForStatement();
				
	   
		if (((DataForStatement)data).updaters == null) {
			((DataForStatement)data).updaters = new EObjectContainmentEList<Expression>(Expression.class, this, DOMPackage.FOR_STATEMENT__UPDATERS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.FOR_STATEMENT__UPDATERS);			
		}
		return ((DataForStatement)data).updaters;	
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
			case DOMPackage.FOR_STATEMENT__BODY:
				return basicSetBody(null, msgs);
			case DOMPackage.FOR_STATEMENT__EXPRESSION:
				return basicSetExpression(null, msgs);
			case DOMPackage.FOR_STATEMENT__INITIALIZERS:
				return ((InternalEList<?>)getInitializers()).basicRemove(otherEnd, msgs);
			case DOMPackage.FOR_STATEMENT__UPDATERS:
				return ((InternalEList<?>)getUpdaters()).basicRemove(otherEnd, msgs);
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
			case DOMPackage.FOR_STATEMENT__BODY:
				return getBody();
			case DOMPackage.FOR_STATEMENT__EXPRESSION:
				return getExpression();
			case DOMPackage.FOR_STATEMENT__INITIALIZERS:
				return getInitializers();
			case DOMPackage.FOR_STATEMENT__UPDATERS:
				return getUpdaters();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.FOR_STATEMENT__BODY:
				setBody((Statement)newValue);
				return;
			case DOMPackage.FOR_STATEMENT__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case DOMPackage.FOR_STATEMENT__INITIALIZERS:
				getInitializers().clear();
				getInitializers().addAll((Collection<? extends Expression>)newValue);
				return;
			case DOMPackage.FOR_STATEMENT__UPDATERS:
				getUpdaters().clear();
				getUpdaters().addAll((Collection<? extends Expression>)newValue);
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
			case DOMPackage.FOR_STATEMENT__BODY:
				setBody((Statement)null);
				return;
			case DOMPackage.FOR_STATEMENT__EXPRESSION:
				setExpression((Expression)null);
				return;
			case DOMPackage.FOR_STATEMENT__INITIALIZERS:
				getInitializers().clear();
				return;
			case DOMPackage.FOR_STATEMENT__UPDATERS:
				getUpdaters().clear();
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
			case DOMPackage.FOR_STATEMENT__BODY:
				return getBody() != null;
			case DOMPackage.FOR_STATEMENT__EXPRESSION:
				return getExpression() != null;
			case DOMPackage.FOR_STATEMENT__INITIALIZERS:
				return getInitializers() != null && !getInitializers().isEmpty();
			case DOMPackage.FOR_STATEMENT__UPDATERS:
				return getUpdaters() != null && !getUpdaters().isEmpty();
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataForStatement extends DataStatement {


	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected Statement body;

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
	 * The cached value of the '{@link #getInitializers() <em>Initializers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializers()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> initializers;

	/**
	 * The cached value of the '{@link #getUpdaters() <em>Updaters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpdaters()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> updaters;

	/**
	 *Constructor of DataForStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataForStatement() {
		super();
	}
	
		
	/**
	 *Constructor of DataForStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataForStatement(DataStatement data) {
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
} //ForStatementImpl
