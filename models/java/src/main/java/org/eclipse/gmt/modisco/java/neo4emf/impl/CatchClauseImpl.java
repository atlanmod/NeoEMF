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

import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Catch Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CatchClauseImpl#getException <em>Exception</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CatchClauseImpl#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CatchClauseImpl extends StatementImpl implements CatchClause {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CatchClauseImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataCatchClause getData(){
		if ( data == null || !(data instanceof DataCatchClause)){
			data = new DataCatchClause();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataCatchClause) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getCatchClause();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleVariableDeclaration getException() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CATCH_CLAUSE__EXCEPTION);
		}
		return getData().exception;
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
	public NotificationChain basicSetException(SingleVariableDeclaration newException, NotificationChain msgs) {
	
		
	
		SingleVariableDeclaration oldException = getData().exception;
		getData().exception = newException;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.CATCH_CLAUSE__EXCEPTION, oldException, newException);
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
	public void setException(SingleVariableDeclaration newException) {
	
		
	
		if (newException != getData().exception) {
			NotificationChain msgs = null;
			if (getData().exception != null)
				msgs = ((InternalEObject) getData().exception).eInverseRemove(this, JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE, SingleVariableDeclaration.class, msgs);
			if (newException != null)
				msgs = ((InternalEObject)newException).eInverseAdd(this, JavaPackage.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE, SingleVariableDeclaration.class, msgs);
			msgs = basicSetException(newException, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.CATCH_CLAUSE__EXCEPTION, newException, newException));
	    addChangelogEntry(newException, JavaPackage.CATCH_CLAUSE__EXCEPTION);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBody() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CATCH_CLAUSE__BODY);
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
	public NotificationChain basicSetBody(Block newBody, NotificationChain msgs) {
	
		
	
		Block oldBody = getData().body;
		getData().body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.CATCH_CLAUSE__BODY, oldBody, newBody);
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
	public void setBody(Block newBody) {
	
		
	
		if (newBody != getData().body) {
			NotificationChain msgs = null;
			if (getData().body != null)
				msgs = ((InternalEObject) getData().body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.CATCH_CLAUSE__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.CATCH_CLAUSE__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.CATCH_CLAUSE__BODY, newBody, newBody));
	    addChangelogEntry(newBody, JavaPackage.CATCH_CLAUSE__BODY);
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
			case JavaPackage.CATCH_CLAUSE__EXCEPTION:
				if (getData().exception != null)
					msgs = ((InternalEObject)getData().exception).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.CATCH_CLAUSE__EXCEPTION, null, msgs);
				return basicSetException((SingleVariableDeclaration)otherEnd, msgs);
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
			case JavaPackage.CATCH_CLAUSE__EXCEPTION:
				return basicSetException(null, msgs);
			case JavaPackage.CATCH_CLAUSE__BODY:
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
			case JavaPackage.CATCH_CLAUSE__EXCEPTION:
				return getException();
			case JavaPackage.CATCH_CLAUSE__BODY:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaPackage.CATCH_CLAUSE__EXCEPTION:
				setException((SingleVariableDeclaration)newValue);
				return;
			case JavaPackage.CATCH_CLAUSE__BODY:
				setBody((Block)newValue);
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
			case JavaPackage.CATCH_CLAUSE__EXCEPTION:
				setException((SingleVariableDeclaration)null);
				return;
			case JavaPackage.CATCH_CLAUSE__BODY:
				setBody((Block)null);
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
			case JavaPackage.CATCH_CLAUSE__EXCEPTION:
				return getException() != null;
			case JavaPackage.CATCH_CLAUSE__BODY:
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
protected static  class DataCatchClause extends DataStatement {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getException() <em>Exception</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getException()
	 * @generated
	 * @ordered
	 */
	protected SingleVariableDeclaration exception;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected Block body;

	/**
	 *Constructor of DataCatchClause
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataCatchClause() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataCatchClause
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	//public DataCatchClause(DataStatement data)
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
} //CatchClauseImpl
