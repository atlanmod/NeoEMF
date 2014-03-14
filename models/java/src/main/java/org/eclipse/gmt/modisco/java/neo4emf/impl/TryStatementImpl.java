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

import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.TryStatement;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Try Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.TryStatementImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.TryStatementImpl#getFinally <em>Finally</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.TryStatementImpl#getCatchClauses <em>Catch Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TryStatementImpl extends StatementImpl implements TryStatement {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TryStatementImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataTryStatement getData(){
		if ( data == null || !(data instanceof DataTryStatement)){
			data = new DataTryStatement();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataTryStatement) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getTryStatement();
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.TRY_STATEMENT__BODY);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.TRY_STATEMENT__BODY, oldBody, newBody);
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
				msgs = ((InternalEObject) getData().body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.TRY_STATEMENT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.TRY_STATEMENT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.TRY_STATEMENT__BODY, newBody, newBody));
	    addChangelogEntry(newBody, JavaPackage.TRY_STATEMENT__BODY);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getFinally() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.TRY_STATEMENT__FINALLY);
		}
		return getData().finally_;
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
	public NotificationChain basicSetFinally(Block newFinally, NotificationChain msgs) {
	
		
	
		Block oldFinally = getData().finally_;
		getData().finally_ = newFinally;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.TRY_STATEMENT__FINALLY, oldFinally, newFinally);
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
	public void setFinally(Block newFinally) {
	
		
	
		if (newFinally != getData().finally_) {
			NotificationChain msgs = null;
			if (getData().finally_ != null)
				msgs = ((InternalEObject) getData().finally_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.TRY_STATEMENT__FINALLY, null, msgs);
			if (newFinally != null)
				msgs = ((InternalEObject)newFinally).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.TRY_STATEMENT__FINALLY, null, msgs);
			msgs = basicSetFinally(newFinally, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.TRY_STATEMENT__FINALLY, newFinally, newFinally));
	    addChangelogEntry(newFinally, JavaPackage.TRY_STATEMENT__FINALLY);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CatchClause> getCatchClauses() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().catchClauses == null){
		getData().catchClauses = new EObjectContainmentEList<CatchClause>(CatchClause.class, this, JavaPackage.TRY_STATEMENT__CATCH_CLAUSES);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.TRY_STATEMENT__CATCH_CLAUSES);			}
		return getData().catchClauses;
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
			case JavaPackage.TRY_STATEMENT__BODY:
				return basicSetBody(null, msgs);
			case JavaPackage.TRY_STATEMENT__FINALLY:
				return basicSetFinally(null, msgs);
			case JavaPackage.TRY_STATEMENT__CATCH_CLAUSES:
				return ((InternalEList<?>)getCatchClauses()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.TRY_STATEMENT__BODY:
				return getBody();
			case JavaPackage.TRY_STATEMENT__FINALLY:
				return getFinally();
			case JavaPackage.TRY_STATEMENT__CATCH_CLAUSES:
				return getCatchClauses();
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
			case JavaPackage.TRY_STATEMENT__BODY:
				setBody((Block)newValue);
				return;
			case JavaPackage.TRY_STATEMENT__FINALLY:
				setFinally((Block)newValue);
				return;
			case JavaPackage.TRY_STATEMENT__CATCH_CLAUSES:
				getCatchClauses().clear();
				getCatchClauses().addAll((Collection<? extends CatchClause>)newValue);
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
			case JavaPackage.TRY_STATEMENT__BODY:
				setBody((Block)null);
				return;
			case JavaPackage.TRY_STATEMENT__FINALLY:
				setFinally((Block)null);
				return;
			case JavaPackage.TRY_STATEMENT__CATCH_CLAUSES:
				getCatchClauses().clear();
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
			case JavaPackage.TRY_STATEMENT__BODY:
				return getBody() != null;
			case JavaPackage.TRY_STATEMENT__FINALLY:
				return getFinally() != null;
			case JavaPackage.TRY_STATEMENT__CATCH_CLAUSES:
				return getCatchClauses() != null && !getCatchClauses().isEmpty();
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
protected static  class DataTryStatement extends DataStatement {


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

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getFinally() <em>Finally</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinally()
	 * @generated
	 * @ordered
	 */
	protected Block finally_;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getCatchClauses() <em>Catch Clauses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCatchClauses()
	 * @generated
	 * @ordered
	 */
	protected EList<CatchClause> catchClauses;

	/**
	 *Constructor of DataTryStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTryStatement() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataTryStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	//public DataTryStatement(DataStatement data)
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
} //TryStatementImpl
