/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.Expression;
import DOM.InstanceofExpression;
import DOM.Type;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instanceof Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.InstanceofExpressionImpl#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link DOM.impl.InstanceofExpressionImpl#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstanceofExpressionImpl extends ExpressionImpl implements InstanceofExpression {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanceofExpressionImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.INSTANCEOF_EXPRESSION;
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
		if ( data == null || !(data instanceof DataInstanceofExpression))
			data = new DataInstanceofExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND, null, null));
		return ((DataInstanceofExpression)data).leftOperand;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftOperand(Expression newLeftOperand, NotificationChain msgs) {
	
		if (data==null) data =  new DataInstanceofExpression();
	
		Expression oldLeftOperand = ((DataInstanceofExpression)data).leftOperand;
		((DataInstanceofExpression)data).leftOperand = newLeftOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND, oldLeftOperand, newLeftOperand);
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
	
		if (data==null) data =  new DataInstanceofExpression();
		
		else if (!(data instanceof DataInstanceofExpression)) data = new DataInstanceofExpression((DataExpression)data);
	
		if (newLeftOperand != ((DataInstanceofExpression)data).leftOperand) {
			NotificationChain msgs = null;
			if (((DataInstanceofExpression)data).leftOperand != null)
				msgs = ((InternalEObject) ((DataInstanceofExpression)data).leftOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND, null, msgs);
			if (newLeftOperand != null)
				msgs = ((InternalEObject)newLeftOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND, null, msgs);
			msgs = basicSetLeftOperand(newLeftOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND, newLeftOperand, newLeftOperand));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getRightOperand() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataInstanceofExpression))
			data = new DataInstanceofExpression();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND, null, null));
		return ((DataInstanceofExpression)data).rightOperand;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightOperand(Type newRightOperand, NotificationChain msgs) {
	
		if (data==null) data =  new DataInstanceofExpression();
	
		Type oldRightOperand = ((DataInstanceofExpression)data).rightOperand;
		((DataInstanceofExpression)data).rightOperand = newRightOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND, oldRightOperand, newRightOperand);
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
	public void setRightOperand(Type newRightOperand) {
	
		if (data==null) data =  new DataInstanceofExpression();
		
		else if (!(data instanceof DataInstanceofExpression)) data = new DataInstanceofExpression((DataExpression)data);
	
		if (newRightOperand != ((DataInstanceofExpression)data).rightOperand) {
			NotificationChain msgs = null;
			if (((DataInstanceofExpression)data).rightOperand != null)
				msgs = ((InternalEObject) ((DataInstanceofExpression)data).rightOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND, null, msgs);
			if (newRightOperand != null)
				msgs = ((InternalEObject)newRightOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND, null, msgs);
			msgs = basicSetRightOperand(newRightOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND, newRightOperand, newRightOperand));
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
			case DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				return basicSetLeftOperand(null, msgs);
			case DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
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
			case DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand();
			case DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)newValue);
				return;
			case DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((Type)newValue);
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
			case DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)null);
				return;
			case DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((Type)null);
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
			case DOMPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand() != null;
			case DOMPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
				return getRightOperand() != null;
		}
		return super.eIsSet(featureID);
	}





// data Class generation 
protected static  class DataInstanceofExpression extends DataExpression {


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
	 * The cached value of the '{@link #getRightOperand() <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightOperand()
	 * @generated
	 * @ordered
	 */
	protected Type rightOperand;

	/**
	 *Constructor of DataInstanceofExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataInstanceofExpression() {
		super();
	}
	
		
	/**
	 *Constructor of DataInstanceofExpression
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataInstanceofExpression(DataExpression data) {
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
} //InstanceofExpressionImpl
