/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.PostfixExpression;
import DOM.PostfixExpressionOperatorKind;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Postfix Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.PostfixExpressionImpl#getOperand <em>Operand</em>}</li>
 *   <li>{@link DOM.impl.PostfixExpressionImpl#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PostfixExpressionImpl extends ExpressionImpl implements PostfixExpression {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PostfixExpressionImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.POSTFIX_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getOperand() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataPostfixExpression))
			data = new DataPostfixExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.POSTFIX_EXPRESSION__OPERAND, null, null));
		return ((DataPostfixExpression)data).operand;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperand(Expression newOperand, NotificationChain msgs) {
	
		if (data==null) data =  new DataPostfixExpression();
	
		Expression oldOperand = ((DataPostfixExpression)data).operand;
		((DataPostfixExpression)data).operand = newOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.POSTFIX_EXPRESSION__OPERAND, oldOperand, newOperand);
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
	public void setOperand(Expression newOperand) {
	
		if (data==null) data =  new DataPostfixExpression();
		
		else if (!(data instanceof DataPostfixExpression)) data = new DataPostfixExpression((DataExpression)data);
	
		if (newOperand != ((DataPostfixExpression)data).operand) {
			NotificationChain msgs = null;
			if (((DataPostfixExpression)data).operand != null)
				msgs = ((InternalEObject) ((DataPostfixExpression)data).operand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.POSTFIX_EXPRESSION__OPERAND, null, msgs);
			if (newOperand != null)
				msgs = ((InternalEObject)newOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.POSTFIX_EXPRESSION__OPERAND, null, msgs);
			msgs = basicSetOperand(newOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.POSTFIX_EXPRESSION__OPERAND, newOperand, newOperand));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PostfixExpressionOperatorKind getOperator() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataPostfixExpression))
			data = new DataPostfixExpression();
			
		if (((DataPostfixExpression)data).operator == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.POSTFIX_EXPRESSION__OPERATOR, null, null));
		return ((DataPostfixExpression)data).operator;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(PostfixExpressionOperatorKind newOperator) {
	
		if (data==null) data =  new DataPostfixExpression();
		
		else if (!(data instanceof DataPostfixExpression)) data = new DataPostfixExpression((DataExpression)data);
	
		PostfixExpressionOperatorKind oldOperator = ((DataPostfixExpression)data).operator;
		((DataPostfixExpression)data).operator = newOperator == null ? ((DataPostfixExpression)data).OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.POSTFIX_EXPRESSION__OPERATOR, oldOperator, ((DataPostfixExpression)data).operator));
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
			case DOMPackage.POSTFIX_EXPRESSION__OPERAND:
				return basicSetOperand(null, msgs);
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
			case DOMPackage.POSTFIX_EXPRESSION__OPERAND:
				return getOperand();
			case DOMPackage.POSTFIX_EXPRESSION__OPERATOR:
				return getOperator();
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
			case DOMPackage.POSTFIX_EXPRESSION__OPERAND:
				setOperand((Expression)newValue);
				return;
			case DOMPackage.POSTFIX_EXPRESSION__OPERATOR:
				setOperator((PostfixExpressionOperatorKind)newValue);
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
			case DOMPackage.POSTFIX_EXPRESSION__OPERAND:
				setOperand((Expression)null);
				return;
			case DOMPackage.POSTFIX_EXPRESSION__OPERATOR:
				setOperator(DataPostfixExpression.OPERATOR_EDEFAULT);
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
			case DOMPackage.POSTFIX_EXPRESSION__OPERAND:
				return getOperand() != null;
			case DOMPackage.POSTFIX_EXPRESSION__OPERATOR:
				return getOperator() != DataPostfixExpression.OPERATOR_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 *YY27
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		if (data != null) result.append(((DataPostfixExpression)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataPostfixExpression extends DataExpression {


	/**
	 * The cached value of the '{@link #getOperand() <em>Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperand()
	 * @generated
	 * @ordered
	 */
	protected Expression operand;

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final PostfixExpressionOperatorKind OPERATOR_EDEFAULT = PostfixExpressionOperatorKind.INCREMENT;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected PostfixExpressionOperatorKind operator = OPERATOR_EDEFAULT;

	/**
	 *Constructor of DataPostfixExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataPostfixExpression() {
		super();
	}
	
		
	/**
	 *Constructor of DataPostfixExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataPostfixExpression(DataExpression data) {
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
		result.append(" (operator: ");
		result.append(operator);
		result.append(')');
		return result.toString();
	}
		
}
} //PostfixExpressionImpl
