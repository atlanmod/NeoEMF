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
import org.eclipse.gmt.modisco.java.ForStatement;
import org.eclipse.gmt.modisco.java.Statement;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>For Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ForStatementImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ForStatementImpl#getUpdaters <em>Updaters</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ForStatementImpl#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ForStatementImpl#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForStatementImpl extends StatementImpl implements ForStatement {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForStatementImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataForStatement getData(){
		if ( data == null || !(data instanceof DataForStatement)){
			data = new DataForStatement();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataForStatement) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getForStatement();
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.FOR_STATEMENT__EXPRESSION);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.FOR_STATEMENT__EXPRESSION, oldExpression, newExpression);
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
				msgs = ((InternalEObject) getData().expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.FOR_STATEMENT__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.FOR_STATEMENT__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.FOR_STATEMENT__EXPRESSION, newExpression, newExpression));
	    addChangelogEntry(newExpression, JavaPackage.FOR_STATEMENT__EXPRESSION);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getUpdaters() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().updaters == null){
		getData().updaters = new EObjectContainmentEList<Expression>(Expression.class, this, JavaPackage.FOR_STATEMENT__UPDATERS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.FOR_STATEMENT__UPDATERS);			}
		return getData().updaters;
	} finally {
	loadingOnDemand = false;
}
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getInitializers() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().initializers == null){
		getData().initializers = new EObjectContainmentEList<Expression>(Expression.class, this, JavaPackage.FOR_STATEMENT__INITIALIZERS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.FOR_STATEMENT__INITIALIZERS);			}
		return getData().initializers;
	} finally {
	loadingOnDemand = false;
}
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getBody() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.FOR_STATEMENT__BODY);
		}
		return getData().body;
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
	public NotificationChain basicSetBody(Statement newBody, NotificationChain msgs) {
	
		
	
		Statement oldBody = getData().body;
		getData().body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.FOR_STATEMENT__BODY, oldBody, newBody);
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
	public void setBody(Statement newBody) {
	
		
	
		if (newBody != getData().body) {
			NotificationChain msgs = null;
			if (getData().body != null)
				msgs = ((InternalEObject) getData().body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.FOR_STATEMENT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.FOR_STATEMENT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.FOR_STATEMENT__BODY, newBody, newBody));
	    addChangelogEntry(newBody, JavaPackage.FOR_STATEMENT__BODY);
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
			case JavaPackage.FOR_STATEMENT__EXPRESSION:
				return basicSetExpression(null, msgs);
			case JavaPackage.FOR_STATEMENT__UPDATERS:
				return ((InternalEList<?>)getUpdaters()).basicRemove(otherEnd, msgs);
			case JavaPackage.FOR_STATEMENT__INITIALIZERS:
				return ((InternalEList<?>)getInitializers()).basicRemove(otherEnd, msgs);
			case JavaPackage.FOR_STATEMENT__BODY:
				return basicSetBody(null, msgs);
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
			case JavaPackage.FOR_STATEMENT__EXPRESSION:
				return getExpression();
			case JavaPackage.FOR_STATEMENT__UPDATERS:
				return getUpdaters();
			case JavaPackage.FOR_STATEMENT__INITIALIZERS:
				return getInitializers();
			case JavaPackage.FOR_STATEMENT__BODY:
				return getBody();
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
			case JavaPackage.FOR_STATEMENT__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case JavaPackage.FOR_STATEMENT__UPDATERS:
				getUpdaters().clear();
				getUpdaters().addAll((Collection<? extends Expression>)newValue);
				return;
			case JavaPackage.FOR_STATEMENT__INITIALIZERS:
				getInitializers().clear();
				getInitializers().addAll((Collection<? extends Expression>)newValue);
				return;
			case JavaPackage.FOR_STATEMENT__BODY:
				setBody((Statement)newValue);
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
			case JavaPackage.FOR_STATEMENT__EXPRESSION:
				setExpression((Expression)null);
				return;
			case JavaPackage.FOR_STATEMENT__UPDATERS:
				getUpdaters().clear();
				return;
			case JavaPackage.FOR_STATEMENT__INITIALIZERS:
				getInitializers().clear();
				return;
			case JavaPackage.FOR_STATEMENT__BODY:
				setBody((Statement)null);
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
			case JavaPackage.FOR_STATEMENT__EXPRESSION:
				return getExpression() != null;
			case JavaPackage.FOR_STATEMENT__UPDATERS:
				return getUpdaters() != null && !getUpdaters().isEmpty();
			case JavaPackage.FOR_STATEMENT__INITIALIZERS:
				return getInitializers() != null && !getInitializers().isEmpty();
			case JavaPackage.FOR_STATEMENT__BODY:
				return getBody() != null;
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
protected static  class DataForStatement extends DataStatement {


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
	 * The cached value of the '{@link #getUpdaters() <em>Updaters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpdaters()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> updaters;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getInitializers() <em>Initializers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializers()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> initializers;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected Statement body;

	/**
	 *Constructor of DataForStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataForStatement() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataForStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	//public DataForStatement(DataStatement data)
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
} //ForStatementImpl
