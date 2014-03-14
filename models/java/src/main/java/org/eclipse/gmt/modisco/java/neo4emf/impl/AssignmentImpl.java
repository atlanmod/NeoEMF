/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
**/
package org.eclipse.gmt.modisco.java.neo4emf.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.Assignment;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Expression;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AssignmentImpl#getLeftHandSide <em>Left Hand Side</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AssignmentImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AssignmentImpl#getRightHandSide <em>Right Hand Side</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignmentImpl extends ExpressionImpl implements Assignment {

	 
	
	 
	 
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
	protected DataAssignment getData(){
		if ( data == null || !(data instanceof DataAssignment)){
			data = new DataAssignment();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataAssignment) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAssignment();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftHandSide() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE);
		}
		return getData().leftHandSide;
	} finally {
	loadingOnDemand = false;
}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftHandSide(Expression newLeftHandSide, NotificationChain msgs) {
	
		
	
		Expression oldLeftHandSide = getData().leftHandSide;
		getData().leftHandSide = newLeftHandSide;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE, oldLeftHandSide, newLeftHandSide);
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
	
		
	
		if (newLeftHandSide != getData().leftHandSide) {
			NotificationChain msgs = null;
			if (getData().leftHandSide != null)
				msgs = ((InternalEObject) getData().leftHandSide).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE, null, msgs);
			if (newLeftHandSide != null)
				msgs = ((InternalEObject)newLeftHandSide).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE, null, msgs);
			msgs = basicSetLeftHandSide(newLeftHandSide, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE, newLeftHandSide, newLeftHandSide));
	    addChangelogEntry(newLeftHandSide, JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentKind getOperator() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().operator;
		
	} finally {
	loadingOnDemand = false;
}
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setOperator(AssignmentKind newOperator) {
	
		
	
		AssignmentKind oldOperator = getData().operator;
		getData().operator = newOperator == null ? getData().OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.ASSIGNMENT__OPERATOR,
			oldOperator, getData().operator));
	    addChangelogEntry(newOperator, JavaPackage.ASSIGNMENT__OPERATOR);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightHandSide() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE);
		}
		return getData().rightHandSide;
	} finally {
	loadingOnDemand = false;
}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightHandSide(Expression newRightHandSide, NotificationChain msgs) {
	
		
	
		Expression oldRightHandSide = getData().rightHandSide;
		getData().rightHandSide = newRightHandSide;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE, oldRightHandSide, newRightHandSide);
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
	
		
	
		if (newRightHandSide != getData().rightHandSide) {
			NotificationChain msgs = null;
			if (getData().rightHandSide != null)
				msgs = ((InternalEObject) getData().rightHandSide).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE, null, msgs);
			if (newRightHandSide != null)
				msgs = ((InternalEObject)newRightHandSide).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE, null, msgs);
			msgs = basicSetRightHandSide(newRightHandSide, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE, newRightHandSide, newRightHandSide));
	    addChangelogEntry(newRightHandSide, JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE);
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
			case JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				return basicSetLeftHandSide(null, msgs);
			case JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
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
			case JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				return getLeftHandSide();
			case JavaPackage.ASSIGNMENT__OPERATOR:
				return getOperator();
			case JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
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
			case JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				setLeftHandSide((Expression)newValue);
				return;
			case JavaPackage.ASSIGNMENT__OPERATOR:
				setOperator((AssignmentKind)newValue);
				return;
			case JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				setRightHandSide((Expression)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY17-Bis
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				setLeftHandSide((Expression)null);
				return;
			case JavaPackage.ASSIGNMENT__OPERATOR:
				setOperator(DataAssignment.OPERATOR_EDEFAULT);
				return;
			case JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
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
			case JavaPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				return getLeftHandSide() != null;
			case JavaPackage.ASSIGNMENT__OPERATOR:
				return getOperator() != DataAssignment.OPERATOR_EDEFAULT;
			case JavaPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
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
		if (data != null) result.append(data.toString());
		
		return result.toString();
		}

/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




// data Class generation 
protected static  class DataAssignment extends DataExpression {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getLeftHandSide() <em>Left Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftHandSide()
	 * @generated
	 * @ordered
	 */
	protected Expression leftHandSide;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final AssignmentKind OPERATOR_EDEFAULT = AssignmentKind.ASSIGN;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected AssignmentKind operator = OPERATOR_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

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
	//public DataAssignment(DataExpression data)
	//{
	//	this();		
	//	
	//	}
	
	
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
		

/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/
}//end data class
} //AssignmentImpl
