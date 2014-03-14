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
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.VariableDeclaration;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Variable Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.SingleVariableAccessImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.SingleVariableAccessImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SingleVariableAccessImpl extends ExpressionImpl implements SingleVariableAccess {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleVariableAccessImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataSingleVariableAccess getData(){
		if ( data == null || !(data instanceof DataSingleVariableAccess)){
			data = new DataSingleVariableAccess();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataSingleVariableAccess) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getSingleVariableAccess();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclaration getVariable() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().variable == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE);
		}		
		return getData().variable;
		
	} finally {
	loadingOnDemand = false;
}
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX8-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclaration basicGetVariable() {
		return data != null ? getData().variable : null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariable(VariableDeclaration newVariable, NotificationChain msgs) {
	
		
	
		VariableDeclaration oldVariable = getData().variable;
		getData().variable = newVariable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE, oldVariable, newVariable);
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
	public void setVariable(VariableDeclaration newVariable) {
	
		
	
		if (newVariable != getData().variable) {
			NotificationChain msgs = null;
			if (getData().variable != null)
				msgs = ((InternalEObject) getData().variable).eInverseRemove(this, JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS, VariableDeclaration.class, msgs);
			if (newVariable != null)
				msgs = ((InternalEObject)newVariable).eInverseAdd(this, JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS, VariableDeclaration.class, msgs);
			msgs = basicSetVariable(newVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE, newVariable, newVariable));
	    addChangelogEntry(newVariable, JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getQualifier() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER);
		}
		return getData().qualifier;
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
	public NotificationChain basicSetQualifier(Expression newQualifier, NotificationChain msgs) {
	
		
	
		Expression oldQualifier = getData().qualifier;
		getData().qualifier = newQualifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER, oldQualifier, newQualifier);
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
	public void setQualifier(Expression newQualifier) {
	
		
	
		if (newQualifier != getData().qualifier) {
			NotificationChain msgs = null;
			if (getData().qualifier != null)
				msgs = ((InternalEObject) getData().qualifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER, null, msgs);
			if (newQualifier != null)
				msgs = ((InternalEObject)newQualifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER, null, msgs);
			msgs = basicSetQualifier(newQualifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER, newQualifier, newQualifier));
	    addChangelogEntry(newQualifier, JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER);
	} 

/**
	 * <!-- begin-user-doc -->
	 *YY12
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE:
				if (getData().variable != null)
					msgs = ((InternalEObject)getData().variable).eInverseRemove(this, JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS, VariableDeclaration.class, msgs);
				return basicSetVariable((VariableDeclaration)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE:
				return basicSetVariable(null, msgs);
			case JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER:
				return basicSetQualifier(null, msgs);
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
			case JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE:
				if (resolve) return getVariable();
				return basicGetVariable();
			case JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER:
				return getQualifier();
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
			case JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE:
				setVariable((VariableDeclaration)newValue);
				return;
			case JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER:
				setQualifier((Expression)newValue);
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
			case JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE:
				setVariable((VariableDeclaration)null);
				return;
			case JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER:
				setQualifier((Expression)null);
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
			case JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE:
				return getVariable() != null;
			case JavaPackage.SINGLE_VARIABLE_ACCESS__QUALIFIER:
				return getQualifier() != null;
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
protected static  class DataSingleVariableAccess extends DataExpression {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getVariable() <em>Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected VariableDeclaration variable;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifier()
	 * @generated
	 * @ordered
	 */
	protected Expression qualifier;

	/**
	 *Constructor of DataSingleVariableAccess
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSingleVariableAccess() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataSingleVariableAccess
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	//public DataSingleVariableAccess(DataExpression data)
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
} //SingleVariableAccessImpl
