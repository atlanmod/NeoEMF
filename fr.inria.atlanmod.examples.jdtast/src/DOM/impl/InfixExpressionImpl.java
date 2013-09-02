/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.InfixExpression;
import DOM.InfixExpressionOperatorKind;

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
 * An implementation of the model object '<em><b>Infix Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.InfixExpressionImpl#getExtendedOperands <em>Extended Operands</em>}</li>
 *   <li>{@link DOM.impl.InfixExpressionImpl#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link DOM.impl.InfixExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link DOM.impl.InfixExpressionImpl#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InfixExpressionImpl extends ExpressionImpl implements InfixExpression {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InfixExpressionImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.INFIX_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getExtendedOperands() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataInfixExpression))
			data = new DataInfixExpression();
				
	   
		if (((DataInfixExpression)data).extendedOperands == null) {
			((DataInfixExpression)data).extendedOperands = new EObjectContainmentEList<Expression>(Expression.class, this, DOMPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS);
			if (getNodeId()>-1) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, DOMPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS);			
		}
		return ((DataInfixExpression)data).extendedOperands;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftOperand() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataInfixExpression))
			data = new DataInfixExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND, null, null));
		return ((DataInfixExpression)data).leftOperand;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftOperand(Expression newLeftOperand, NotificationChain msgs) {
	
		if (data==null) data =  new DataInfixExpression();
	
		Expression oldLeftOperand = ((DataInfixExpression)data).leftOperand;
		((DataInfixExpression)data).leftOperand = newLeftOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND, oldLeftOperand, newLeftOperand);
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
	public void setLeftOperand(Expression newLeftOperand) {
	
		if (data==null) data =  new DataInfixExpression();
		
		else if (!(data instanceof DataInfixExpression)) data = new DataInfixExpression((DataExpression)data);
	
		if (newLeftOperand != ((DataInfixExpression)data).leftOperand) {
			NotificationChain msgs = null;
			if (((DataInfixExpression)data).leftOperand != null)
				msgs = ((InternalEObject) ((DataInfixExpression)data).leftOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND, null, msgs);
			if (newLeftOperand != null)
				msgs = ((InternalEObject)newLeftOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND, null, msgs);
			msgs = basicSetLeftOperand(newLeftOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND, newLeftOperand, newLeftOperand));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfixExpressionOperatorKind getOperator() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataInfixExpression))
			data = new DataInfixExpression();
			
		if (((DataInfixExpression)data).operator == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.INFIX_EXPRESSION__OPERATOR, null, null));
		return ((DataInfixExpression)data).operator;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(InfixExpressionOperatorKind newOperator) {
	
		if (data==null) data =  new DataInfixExpression();
		
		else if (!(data instanceof DataInfixExpression)) data = new DataInfixExpression((DataExpression)data);
	
		InfixExpressionOperatorKind oldOperator = ((DataInfixExpression)data).operator;
		((DataInfixExpression)data).operator = newOperator == null ? ((DataInfixExpression)data).OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.INFIX_EXPRESSION__OPERATOR, oldOperator, ((DataInfixExpression)data).operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightOperand() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataInfixExpression))
			data = new DataInfixExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND, null, null));
		return ((DataInfixExpression)data).rightOperand;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightOperand(Expression newRightOperand, NotificationChain msgs) {
	
		if (data==null) data =  new DataInfixExpression();
	
		Expression oldRightOperand = ((DataInfixExpression)data).rightOperand;
		((DataInfixExpression)data).rightOperand = newRightOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND, oldRightOperand, newRightOperand);
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
	public void setRightOperand(Expression newRightOperand) {
	
		if (data==null) data =  new DataInfixExpression();
		
		else if (!(data instanceof DataInfixExpression)) data = new DataInfixExpression((DataExpression)data);
	
		if (newRightOperand != ((DataInfixExpression)data).rightOperand) {
			NotificationChain msgs = null;
			if (((DataInfixExpression)data).rightOperand != null)
				msgs = ((InternalEObject) ((DataInfixExpression)data).rightOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND, null, msgs);
			if (newRightOperand != null)
				msgs = ((InternalEObject)newRightOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND, null, msgs);
			msgs = basicSetRightOperand(newRightOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND, newRightOperand, newRightOperand));
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
			case DOMPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				return ((InternalEList<?>)getExtendedOperands()).basicRemove(otherEnd, msgs);
			case DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				return basicSetLeftOperand(null, msgs);
			case DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				return basicSetRightOperand(null, msgs);
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
			case DOMPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				return getExtendedOperands();
			case DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand();
			case DOMPackage.INFIX_EXPRESSION__OPERATOR:
				return getOperator();
			case DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				return getRightOperand();
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
			case DOMPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				getExtendedOperands().clear();
				getExtendedOperands().addAll((Collection<? extends Expression>)newValue);
				return;
			case DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)newValue);
				return;
			case DOMPackage.INFIX_EXPRESSION__OPERATOR:
				setOperator((InfixExpressionOperatorKind)newValue);
				return;
			case DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((Expression)newValue);
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
			case DOMPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				getExtendedOperands().clear();
				return;
			case DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)null);
				return;
			case DOMPackage.INFIX_EXPRESSION__OPERATOR:
				setOperator(DataInfixExpression.OPERATOR_EDEFAULT);
				return;
			case DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((Expression)null);
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
			case DOMPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				return getExtendedOperands() != null && !getExtendedOperands().isEmpty();
			case DOMPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand() != null;
			case DOMPackage.INFIX_EXPRESSION__OPERATOR:
				return getOperator() != DataInfixExpression.OPERATOR_EDEFAULT;
			case DOMPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				return getRightOperand() != null;
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
		if (data != null) result.append(((DataInfixExpression)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataInfixExpression extends DataExpression {


	/**
	 * The cached value of the '{@link #getExtendedOperands() <em>Extended Operands</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedOperands()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> extendedOperands;

	/**
	 * The cached value of the '{@link #getLeftOperand() <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftOperand()
	 * @generated
	 * @ordered
	 */
	protected Expression leftOperand;

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final InfixExpressionOperatorKind OPERATOR_EDEFAULT = InfixExpressionOperatorKind.GREATER_EQUALS;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected InfixExpressionOperatorKind operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRightOperand() <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightOperand()
	 * @generated
	 * @ordered
	 */
	protected Expression rightOperand;

	/**
	 *Constructor of DataInfixExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataInfixExpression() {
		super();
	}
	
		
	/**
	 *Constructor of DataInfixExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataInfixExpression(DataExpression data) {
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
} //InfixExpressionImpl
