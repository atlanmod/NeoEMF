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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.InfixExpression;
import org.eclipse.gmt.modisco.java.InfixExpressionKind;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Infix Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.InfixExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.InfixExpressionImpl#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.InfixExpressionImpl#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.InfixExpressionImpl#getExtendedOperands <em>Extended Operands</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InfixExpressionImpl extends ExpressionImpl implements InfixExpression {

	 
	
	 
	 
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
	protected DataInfixExpression getData(){
		if ( data == null || !(data instanceof DataInfixExpression)){
			data = new DataInfixExpression();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataInfixExpression) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getInfixExpression();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfixExpressionKind getOperator() {
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
	public void setOperator(InfixExpressionKind newOperator) {
	
		
	
		InfixExpressionKind oldOperator = getData().operator;
		getData().operator = newOperator == null ? getData().OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.INFIX_EXPRESSION__OPERATOR,
			oldOperator, getData().operator));
	    addChangelogEntry(newOperator, JavaPackage.INFIX_EXPRESSION__OPERATOR);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightOperand() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND);
		}
		return getData().rightOperand;
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
	public NotificationChain basicSetRightOperand(Expression newRightOperand, NotificationChain msgs) {
	
		
	
		Expression oldRightOperand = getData().rightOperand;
		getData().rightOperand = newRightOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND, oldRightOperand, newRightOperand);
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
	
		
	
		if (newRightOperand != getData().rightOperand) {
			NotificationChain msgs = null;
			if (getData().rightOperand != null)
				msgs = ((InternalEObject) getData().rightOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND, null, msgs);
			if (newRightOperand != null)
				msgs = ((InternalEObject)newRightOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND, null, msgs);
			msgs = basicSetRightOperand(newRightOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND, newRightOperand, newRightOperand));
	    addChangelogEntry(newRightOperand, JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftOperand() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND);
		}
		return getData().leftOperand;
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
	public NotificationChain basicSetLeftOperand(Expression newLeftOperand, NotificationChain msgs) {
	
		
	
		Expression oldLeftOperand = getData().leftOperand;
		getData().leftOperand = newLeftOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND, oldLeftOperand, newLeftOperand);
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
	
		
	
		if (newLeftOperand != getData().leftOperand) {
			NotificationChain msgs = null;
			if (getData().leftOperand != null)
				msgs = ((InternalEObject) getData().leftOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND, null, msgs);
			if (newLeftOperand != null)
				msgs = ((InternalEObject)newLeftOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND, null, msgs);
			msgs = basicSetLeftOperand(newLeftOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND, newLeftOperand, newLeftOperand));
	    addChangelogEntry(newLeftOperand, JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getExtendedOperands() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().extendedOperands == null){
		getData().extendedOperands = new EObjectContainmentEList<Expression>(Expression.class, this, JavaPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS);			}
		return getData().extendedOperands;
	} finally {
	loadingOnDemand = false;
}
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
			case JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				return basicSetRightOperand(null, msgs);
			case JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				return basicSetLeftOperand(null, msgs);
			case JavaPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				return ((InternalEList<?>)getExtendedOperands()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.INFIX_EXPRESSION__OPERATOR:
				return getOperator();
			case JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				return getRightOperand();
			case JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand();
			case JavaPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				return getExtendedOperands();
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
			case JavaPackage.INFIX_EXPRESSION__OPERATOR:
				setOperator((InfixExpressionKind)newValue);
				return;
			case JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((Expression)newValue);
				return;
			case JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)newValue);
				return;
			case JavaPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				getExtendedOperands().clear();
				getExtendedOperands().addAll((Collection<? extends Expression>)newValue);
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
			case JavaPackage.INFIX_EXPRESSION__OPERATOR:
				setOperator(DataInfixExpression.OPERATOR_EDEFAULT);
				return;
			case JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((Expression)null);
				return;
			case JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)null);
				return;
			case JavaPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				getExtendedOperands().clear();
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
			case JavaPackage.INFIX_EXPRESSION__OPERATOR:
				return getOperator() != DataInfixExpression.OPERATOR_EDEFAULT;
			case JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
				return getRightOperand() != null;
			case JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand() != null;
			case JavaPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
				return getExtendedOperands() != null && !getExtendedOperands().isEmpty();
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
protected static  class DataInfixExpression extends DataExpression {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final InfixExpressionKind OPERATOR_EDEFAULT = InfixExpressionKind.TIMES;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected InfixExpressionKind operator = OPERATOR_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getRightOperand() <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightOperand()
	 * @generated
	 * @ordered
	 */
	protected Expression rightOperand;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getLeftOperand() <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftOperand()
	 * @generated
	 * @ordered
	 */
	protected Expression leftOperand;

// The goal of this template is to BLAH, BLAH, BLAH

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
	//public DataInfixExpression(DataExpression data)
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
} //InfixExpressionImpl
