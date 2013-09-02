/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.ConditionalExpression;
import DOM.DOMPackage;
import DOM.Expression;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.ConditionalExpressionImpl#getElseExpression <em>Else Expression</em>}</li>
 *   <li>{@link DOM.impl.ConditionalExpressionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link DOM.impl.ConditionalExpressionImpl#getThenExpression <em>Then Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionalExpressionImpl extends ExpressionImpl implements ConditionalExpression {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionalExpressionImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.CONDITIONAL_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getElseExpression() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataConditionalExpression))
			data = new DataConditionalExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION, null, null));
		return ((DataConditionalExpression)data).elseExpression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElseExpression(Expression newElseExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataConditionalExpression();
	
		Expression oldElseExpression = ((DataConditionalExpression)data).elseExpression;
		((DataConditionalExpression)data).elseExpression = newElseExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION, oldElseExpression, newElseExpression);
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
	public void setElseExpression(Expression newElseExpression) {
	
		if (data==null) data =  new DataConditionalExpression();
		
		else if (!(data instanceof DataConditionalExpression)) data = new DataConditionalExpression((DataExpression)data);
	
		if (newElseExpression != ((DataConditionalExpression)data).elseExpression) {
			NotificationChain msgs = null;
			if (((DataConditionalExpression)data).elseExpression != null)
				msgs = ((InternalEObject) ((DataConditionalExpression)data).elseExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION, null, msgs);
			if (newElseExpression != null)
				msgs = ((InternalEObject)newElseExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION, null, msgs);
			msgs = basicSetElseExpression(newElseExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION, newElseExpression, newElseExpression));
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
		if ( data == null || !(data instanceof DataConditionalExpression))
			data = new DataConditionalExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION, null, null));
		return ((DataConditionalExpression)data).expression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataConditionalExpression();
	
		Expression oldExpression = ((DataConditionalExpression)data).expression;
		((DataConditionalExpression)data).expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION, oldExpression, newExpression);
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
	
		if (data==null) data =  new DataConditionalExpression();
		
		else if (!(data instanceof DataConditionalExpression)) data = new DataConditionalExpression((DataExpression)data);
	
		if (newExpression != ((DataConditionalExpression)data).expression) {
			NotificationChain msgs = null;
			if (((DataConditionalExpression)data).expression != null)
				msgs = ((InternalEObject) ((DataConditionalExpression)data).expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getThenExpression() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataConditionalExpression))
			data = new DataConditionalExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION, null, null));
		return ((DataConditionalExpression)data).thenExpression;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThenExpression(Expression newThenExpression, NotificationChain msgs) {
	
		if (data==null) data =  new DataConditionalExpression();
	
		Expression oldThenExpression = ((DataConditionalExpression)data).thenExpression;
		((DataConditionalExpression)data).thenExpression = newThenExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION, oldThenExpression, newThenExpression);
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
	public void setThenExpression(Expression newThenExpression) {
	
		if (data==null) data =  new DataConditionalExpression();
		
		else if (!(data instanceof DataConditionalExpression)) data = new DataConditionalExpression((DataExpression)data);
	
		if (newThenExpression != ((DataConditionalExpression)data).thenExpression) {
			NotificationChain msgs = null;
			if (((DataConditionalExpression)data).thenExpression != null)
				msgs = ((InternalEObject) ((DataConditionalExpression)data).thenExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION, null, msgs);
			if (newThenExpression != null)
				msgs = ((InternalEObject)newThenExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION, null, msgs);
			msgs = basicSetThenExpression(newThenExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION, newThenExpression, newThenExpression));
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
			case DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION:
				return basicSetElseExpression(null, msgs);
			case DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION:
				return basicSetThenExpression(null, msgs);
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
			case DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION:
				return getElseExpression();
			case DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION:
				return getExpression();
			case DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION:
				return getThenExpression();
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
			case DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION:
				setElseExpression((Expression)newValue);
				return;
			case DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION:
				setThenExpression((Expression)newValue);
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
			case DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION:
				setElseExpression((Expression)null);
				return;
			case DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION:
				setThenExpression((Expression)null);
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
			case DOMPackage.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION:
				return getElseExpression() != null;
			case DOMPackage.CONDITIONAL_EXPRESSION__EXPRESSION:
				return getExpression() != null;
			case DOMPackage.CONDITIONAL_EXPRESSION__THEN_EXPRESSION:
				return getThenExpression() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataConditionalExpression extends DataExpression {


	/**
	 * The cached value of the '{@link #getElseExpression() <em>Else Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression elseExpression;

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
	 * The cached value of the '{@link #getThenExpression() <em>Then Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression thenExpression;

	/**
	 *Constructor of DataConditionalExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataConditionalExpression() {
		super();
	}
	
		
	/**
	 *Constructor of DataConditionalExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataConditionalExpression(DataExpression data) {
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
} //ConditionalExpressionImpl
