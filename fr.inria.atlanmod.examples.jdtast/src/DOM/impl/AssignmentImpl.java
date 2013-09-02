/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.Assignment;
import DOM.AssignmentOperatorKind;
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
 * An implementation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link DOM.impl.AssignmentImpl#getLeftHandSide <em>Left Hand Side</em>}</li>
 *   <li>{@link DOM.impl.AssignmentImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link DOM.impl.AssignmentImpl#getRightHandSide <em>Right Hand Side</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignmentImpl extends ExpressionImpl implements Assignment {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//ExpressionImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssignmentImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ASSIGNMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftHandSide() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAssignment))
			data = new DataAssignment();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, null, null));
		return ((DataAssignment)data).leftHandSide;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftHandSide(Expression newLeftHandSide, NotificationChain msgs) {
	
		if (data==null) data =  new DataAssignment();
	
		Expression oldLeftHandSide = ((DataAssignment)data).leftHandSide;
		((DataAssignment)data).leftHandSide = newLeftHandSide;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, oldLeftHandSide, newLeftHandSide);
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
	public void setLeftHandSide(Expression newLeftHandSide) {
	
		if (data==null) data =  new DataAssignment();
		
		else if (!(data instanceof DataAssignment)) data = new DataAssignment((DataExpression)data);
	
		if (newLeftHandSide != ((DataAssignment)data).leftHandSide) {
			NotificationChain msgs = null;
			if (((DataAssignment)data).leftHandSide != null)
				msgs = ((InternalEObject) ((DataAssignment)data).leftHandSide).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, null, msgs);
			if (newLeftHandSide != null)
				msgs = ((InternalEObject)newLeftHandSide).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, null, msgs);
			msgs = basicSetLeftHandSide(newLeftHandSide, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, newLeftHandSide, newLeftHandSide));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentOperatorKind getOperator() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAssignment))
			data = new DataAssignment();
			
		if (((DataAssignment)data).operator == null)	((INeo4emfResource) this.eResource()).fetchAttributes(this);
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ASSIGNMENT__OPERATOR, null, null));
		return ((DataAssignment)data).operator;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(AssignmentOperatorKind newOperator) {
	
		if (data==null) data =  new DataAssignment();
		
		else if (!(data instanceof DataAssignment)) data = new DataAssignment((DataExpression)data);
	
		AssignmentOperatorKind oldOperator = ((DataAssignment)data).operator;
		((DataAssignment)data).operator = newOperator == null ? ((DataAssignment)data).OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__OPERATOR, oldOperator, ((DataAssignment)data).operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightHandSide() {
		if (getNodeId()<0 && data == null ) 
			return null;
		if ( data == null || !(data instanceof DataAssignment))
			data = new DataAssignment();
				
	  		
		if ( getNodeId()>-1 ) 
			eNotify(new ENotificationImpl(this, INeo4emfNotification.GET, DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, null, null));
		return ((DataAssignment)data).rightHandSide;	
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightHandSide(Expression newRightHandSide, NotificationChain msgs) {
	
		if (data==null) data =  new DataAssignment();
	
		Expression oldRightHandSide = ((DataAssignment)data).rightHandSide;
		((DataAssignment)data).rightHandSide = newRightHandSide;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, oldRightHandSide, newRightHandSide);
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
	public void setRightHandSide(Expression newRightHandSide) {
	
		if (data==null) data =  new DataAssignment();
		
		else if (!(data instanceof DataAssignment)) data = new DataAssignment((DataExpression)data);
	
		if (newRightHandSide != ((DataAssignment)data).rightHandSide) {
			NotificationChain msgs = null;
			if (((DataAssignment)data).rightHandSide != null)
				msgs = ((InternalEObject) ((DataAssignment)data).rightHandSide).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, null, msgs);
			if (newRightHandSide != null)
				msgs = ((InternalEObject)newRightHandSide).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, null, msgs);
			msgs = basicSetRightHandSide(newRightHandSide, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, newRightHandSide, newRightHandSide));
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
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				return basicSetLeftHandSide(null, msgs);
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				return basicSetRightHandSide(null, msgs);
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
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				return getLeftHandSide();
			case DOMPackage.ASSIGNMENT__OPERATOR:
				return getOperator();
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				return getRightHandSide();
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
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				setLeftHandSide((Expression)newValue);
				return;
			case DOMPackage.ASSIGNMENT__OPERATOR:
				setOperator((AssignmentOperatorKind)newValue);
				return;
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				setRightHandSide((Expression)newValue);
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
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				setLeftHandSide((Expression)null);
				return;
			case DOMPackage.ASSIGNMENT__OPERATOR:
				setOperator(DataAssignment.OPERATOR_EDEFAULT);
				return;
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				setRightHandSide((Expression)null);
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
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				return getLeftHandSide() != null;
			case DOMPackage.ASSIGNMENT__OPERATOR:
				return getOperator() != DataAssignment.OPERATOR_EDEFAULT;
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				return getRightHandSide() != null;
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
		if (data != null) result.append(((DataAssignment)data).toString());
		
		return result.toString();
		}



// data Class generation 
protected static  class DataAssignment extends DataExpression {


	/**
	 * The cached value of the '{@link #getLeftHandSide() <em>Left Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftHandSide()
	 * @generated
	 * @ordered
	 */
	protected Expression leftHandSide;

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final AssignmentOperatorKind OPERATOR_EDEFAULT = AssignmentOperatorKind.RIGHT_SHIFT_SIGNED_ASSIGN;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected AssignmentOperatorKind operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRightHandSide() <em>Right Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightHandSide()
	 * @generated
	 * @ordered
	 */
	protected Expression rightHandSide;

	/**
	 *Constructor of DataAssignment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAssignment() {
		super();
	}
	
		
	/**
	 *Constructor of DataAssignment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	public DataAssignment(DataExpression data) {
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
} //AssignmentImpl
