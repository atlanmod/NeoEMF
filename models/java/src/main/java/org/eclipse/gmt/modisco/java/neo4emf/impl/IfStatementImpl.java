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
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.Statement;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.IfStatementImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.IfStatementImpl#getThenStatement <em>Then Statement</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.IfStatementImpl#getElseStatement <em>Else Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfStatementImpl extends StatementImpl implements IfStatement {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfStatementImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataIfStatement getData(){
		if ( data == null || !(data instanceof DataIfStatement)){
			data = new DataIfStatement();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataIfStatement) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getIfStatement();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.IF_STATEMENT__EXPRESSION);
		}
		return getData().expression;
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
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
	
		
	
		Expression oldExpression = getData().expression;
		getData().expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.IF_STATEMENT__EXPRESSION, oldExpression, newExpression);
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
	
		
	
		if (newExpression != getData().expression) {
			NotificationChain msgs = null;
			if (getData().expression != null)
				msgs = ((InternalEObject) getData().expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.IF_STATEMENT__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.IF_STATEMENT__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.IF_STATEMENT__EXPRESSION, newExpression, newExpression));
	    addChangelogEntry(newExpression, JavaPackage.IF_STATEMENT__EXPRESSION);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getThenStatement() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.IF_STATEMENT__THEN_STATEMENT);
		}
		return getData().thenStatement;
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
	public NotificationChain basicSetThenStatement(Statement newThenStatement, NotificationChain msgs) {
	
		
	
		Statement oldThenStatement = getData().thenStatement;
		getData().thenStatement = newThenStatement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.IF_STATEMENT__THEN_STATEMENT, oldThenStatement, newThenStatement);
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
	public void setThenStatement(Statement newThenStatement) {
	
		
	
		if (newThenStatement != getData().thenStatement) {
			NotificationChain msgs = null;
			if (getData().thenStatement != null)
				msgs = ((InternalEObject) getData().thenStatement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.IF_STATEMENT__THEN_STATEMENT, null, msgs);
			if (newThenStatement != null)
				msgs = ((InternalEObject)newThenStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.IF_STATEMENT__THEN_STATEMENT, null, msgs);
			msgs = basicSetThenStatement(newThenStatement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.IF_STATEMENT__THEN_STATEMENT, newThenStatement, newThenStatement));
	    addChangelogEntry(newThenStatement, JavaPackage.IF_STATEMENT__THEN_STATEMENT);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getElseStatement() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.IF_STATEMENT__ELSE_STATEMENT);
		}
		return getData().elseStatement;
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
	public NotificationChain basicSetElseStatement(Statement newElseStatement, NotificationChain msgs) {
	
		
	
		Statement oldElseStatement = getData().elseStatement;
		getData().elseStatement = newElseStatement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.IF_STATEMENT__ELSE_STATEMENT, oldElseStatement, newElseStatement);
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
	public void setElseStatement(Statement newElseStatement) {
	
		
	
		if (newElseStatement != getData().elseStatement) {
			NotificationChain msgs = null;
			if (getData().elseStatement != null)
				msgs = ((InternalEObject) getData().elseStatement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.IF_STATEMENT__ELSE_STATEMENT, null, msgs);
			if (newElseStatement != null)
				msgs = ((InternalEObject)newElseStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.IF_STATEMENT__ELSE_STATEMENT, null, msgs);
			msgs = basicSetElseStatement(newElseStatement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.IF_STATEMENT__ELSE_STATEMENT, newElseStatement, newElseStatement));
	    addChangelogEntry(newElseStatement, JavaPackage.IF_STATEMENT__ELSE_STATEMENT);
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
			case JavaPackage.IF_STATEMENT__EXPRESSION:
				return basicSetExpression(null, msgs);
			case JavaPackage.IF_STATEMENT__THEN_STATEMENT:
				return basicSetThenStatement(null, msgs);
			case JavaPackage.IF_STATEMENT__ELSE_STATEMENT:
				return basicSetElseStatement(null, msgs);
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
			case JavaPackage.IF_STATEMENT__EXPRESSION:
				return getExpression();
			case JavaPackage.IF_STATEMENT__THEN_STATEMENT:
				return getThenStatement();
			case JavaPackage.IF_STATEMENT__ELSE_STATEMENT:
				return getElseStatement();
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
			case JavaPackage.IF_STATEMENT__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case JavaPackage.IF_STATEMENT__THEN_STATEMENT:
				setThenStatement((Statement)newValue);
				return;
			case JavaPackage.IF_STATEMENT__ELSE_STATEMENT:
				setElseStatement((Statement)newValue);
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
			case JavaPackage.IF_STATEMENT__EXPRESSION:
				setExpression((Expression)null);
				return;
			case JavaPackage.IF_STATEMENT__THEN_STATEMENT:
				setThenStatement((Statement)null);
				return;
			case JavaPackage.IF_STATEMENT__ELSE_STATEMENT:
				setElseStatement((Statement)null);
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
			case JavaPackage.IF_STATEMENT__EXPRESSION:
				return getExpression() != null;
			case JavaPackage.IF_STATEMENT__THEN_STATEMENT:
				return getThenStatement() != null;
			case JavaPackage.IF_STATEMENT__ELSE_STATEMENT:
				return getElseStatement() != null;
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
protected static  class DataIfStatement extends DataStatement {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getThenStatement() <em>Then Statement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenStatement()
	 * @generated
	 * @ordered
	 */
	protected Statement thenStatement;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getElseStatement() <em>Else Statement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseStatement()
	 * @generated
	 * @ordered
	 */
	protected Statement elseStatement;

	/**
	 *Constructor of DataIfStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataIfStatement() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataIfStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	//public DataIfStatement(DataStatement data)
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
} //IfStatementImpl
