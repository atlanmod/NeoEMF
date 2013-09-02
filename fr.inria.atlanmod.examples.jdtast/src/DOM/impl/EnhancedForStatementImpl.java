/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.EnhancedForStatement;
import DOM.Expression;
import DOM.SingleVariableDeclaration;
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
 * An implementation of the model object '<em><b>Enhanced For Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.EnhancedForStatementImpl#getBody <em>Body</em>}</li>
 *   <li>{@link DOM.impl.EnhancedForStatementImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link DOM.impl.EnhancedForStatementImpl#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnhancedForStatementImpl extends StatementImpl implements EnhancedForStatement {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//StatementImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnhancedForStatementImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ENHANCED_FOR_STATEMENT;
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
		if ( data == null || !(data instanceof DataEnhancedForStatement))
			data = new DataEnhancedForStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ENHANCED_FOR_STATEMENT__BODY, null, null));
		return ((DataEnhancedForStatement)data).body;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(Statement newBody, NotificationChain msgs) {
	
		if (data==null) data =  new DataEnhancedForStatement();
	
		Statement oldBody = ((DataEnhancedForStatement)data).body;
		((DataEnhancedForStatement)data).body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ENHANCED_FOR_STATEMENT__BODY, oldBody, newBody);
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
	
		if (data==null) data =  new DataEnhancedForStatement();
		
		else if (!(data instanceof DataEnhancedForStatement)) data = new DataEnhancedForStatement((DataStatement)data);
	
		if (newBody != ((DataEnhancedForStatement)data).body) {
			NotificationChain msgs = null;
			if (((DataEnhancedForStatement)data).body != null)
				msgs = ((InternalEObject) ((DataEnhancedForStatement)data).body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENHANCED_FOR_STATEMENT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENHANCED_FOR_STATEMENT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ENHANCED_FOR_STATEMENT__BODY, newBody, newBody));
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
		if ( data == null || !(data instanceof DataEnhancedForStatement))
			data = new DataEnhancedForStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION, null, null));
		return ((DataEnhancedForStatement)data).expression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataEnhancedForStatement();
	
		Expression oldExpression = ((DataEnhancedForStatement)data).expression;
		((DataEnhancedForStatement)data).expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION, oldExpression, newExpression);
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
	
		if (data==null) data =  new DataEnhancedForStatement();
		
		else if (!(data instanceof DataEnhancedForStatement)) data = new DataEnhancedForStatement((DataStatement)data);
	
		if (newExpression != ((DataEnhancedForStatement)data).expression) {
			NotificationChain msgs = null;
			if (((DataEnhancedForStatement)data).expression != null)
				msgs = ((InternalEObject) ((DataEnhancedForStatement)data).expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleVariableDeclaration getParameter() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataEnhancedForStatement))
			data = new DataEnhancedForStatement();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER, null, null));
		return ((DataEnhancedForStatement)data).parameter;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParameter(SingleVariableDeclaration newParameter, NotificationChain msgs) {
	
		if (data==null) data =  new DataEnhancedForStatement();
	
		SingleVariableDeclaration oldParameter = ((DataEnhancedForStatement)data).parameter;
		((DataEnhancedForStatement)data).parameter = newParameter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER, oldParameter, newParameter);
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
	public void setParameter(SingleVariableDeclaration newParameter) {
	
		if (data==null) data =  new DataEnhancedForStatement();
		
		else if (!(data instanceof DataEnhancedForStatement)) data = new DataEnhancedForStatement((DataStatement)data);
	
		if (newParameter != ((DataEnhancedForStatement)data).parameter) {
			NotificationChain msgs = null;
			if (((DataEnhancedForStatement)data).parameter != null)
				msgs = ((InternalEObject) ((DataEnhancedForStatement)data).parameter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER, null, msgs);
			if (newParameter != null)
				msgs = ((InternalEObject)newParameter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER, null, msgs);
			msgs = basicSetParameter(newParameter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER, newParameter, newParameter));
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
			case DOMPackage.ENHANCED_FOR_STATEMENT__BODY:
				return basicSetBody(null, msgs);
			case DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION:
				return basicSetExpression(null, msgs);
			case DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER:
				return basicSetParameter(null, msgs);
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
			case DOMPackage.ENHANCED_FOR_STATEMENT__BODY:
				return getBody();
			case DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION:
				return getExpression();
			case DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER:
				return getParameter();
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
			case DOMPackage.ENHANCED_FOR_STATEMENT__BODY:
				setBody((Statement)newValue);
				return;
			case DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER:
				setParameter((SingleVariableDeclaration)newValue);
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
			case DOMPackage.ENHANCED_FOR_STATEMENT__BODY:
				setBody((Statement)null);
				return;
			case DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION:
				setExpression((Expression)null);
				return;
			case DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER:
				setParameter((SingleVariableDeclaration)null);
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
			case DOMPackage.ENHANCED_FOR_STATEMENT__BODY:
				return getBody() != null;
			case DOMPackage.ENHANCED_FOR_STATEMENT__EXPRESSION:
				return getExpression() != null;
			case DOMPackage.ENHANCED_FOR_STATEMENT__PARAMETER:
				return getParameter() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataEnhancedForStatement extends DataStatement {


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
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected SingleVariableDeclaration parameter;

	/**
	 *Constructor of DataEnhancedForStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataEnhancedForStatement() {
		super();
	}
	
		
	/**
	 *Constructor of DataEnhancedForStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	public DataEnhancedForStatement(DataStatement data) {
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
} //EnhancedForStatementImpl
