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

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.InstanceofExpression;
import org.eclipse.gmt.modisco.java.TypeAccess;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instanceof Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.InstanceofExpressionImpl#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.InstanceofExpressionImpl#getLeftOperand <em>Left Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstanceofExpressionImpl extends ExpressionImpl implements InstanceofExpression {

	 
	
	 
	 
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
	protected DataInstanceofExpression getData(){
		if ( data == null || !(data instanceof DataInstanceofExpression)){
			data = new DataInstanceofExpression();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataInstanceofExpression) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getInstanceofExpression();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getRightOperand() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND);
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
	public NotificationChain basicSetRightOperand(TypeAccess newRightOperand, NotificationChain msgs) {
	
		
	
		TypeAccess oldRightOperand = getData().rightOperand;
		getData().rightOperand = newRightOperand;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND, oldRightOperand, newRightOperand);
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
	public void setRightOperand(TypeAccess newRightOperand) {
	
		
	
		if (newRightOperand != getData().rightOperand) {
			NotificationChain msgs = null;
			if (getData().rightOperand != null)
				msgs = ((InternalEObject) getData().rightOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND, null, msgs);
			if (newRightOperand != null)
				msgs = ((InternalEObject)newRightOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND, null, msgs);
			msgs = basicSetRightOperand(newRightOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND, newRightOperand, newRightOperand));
	    addChangelogEntry(newRightOperand, JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND);
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND, oldLeftOperand, newLeftOperand);
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
				msgs = ((InternalEObject) getData().leftOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND, null, msgs);
			if (newLeftOperand != null)
				msgs = ((InternalEObject)newLeftOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND, null, msgs);
			msgs = basicSetLeftOperand(newLeftOperand, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND, newLeftOperand, newLeftOperand));
	    addChangelogEntry(newLeftOperand, JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND);
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
			case JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
				return basicSetRightOperand(null, msgs);
			case JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				return basicSetLeftOperand(null, msgs);
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
			case JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
				return getRightOperand();
			case JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand();
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
			case JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((TypeAccess)newValue);
				return;
			case JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)newValue);
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
			case JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((TypeAccess)null);
				return;
			case JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((Expression)null);
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
			case JavaPackage.INSTANCEOF_EXPRESSION__RIGHT_OPERAND:
				return getRightOperand() != null;
			case JavaPackage.INSTANCEOF_EXPRESSION__LEFT_OPERAND:
				return getLeftOperand() != null;
		}
		return super.eIsSet(featureID);
	}

/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




// data Class generation 
protected static  class DataInstanceofExpression extends DataExpression {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getRightOperand() <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightOperand()
	 * @generated
	 * @ordered
	 */
	protected TypeAccess rightOperand;

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
	//public DataInstanceofExpression(DataExpression data)
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
} //InstanceofExpressionImpl
