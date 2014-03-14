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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.BreakStatement;
import org.eclipse.gmt.modisco.java.ContinueStatement;
import org.eclipse.gmt.modisco.java.LabeledStatement;
import org.eclipse.gmt.modisco.java.Statement;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Labeled Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.LabeledStatementImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.LabeledStatementImpl#getUsagesInBreakStatements <em>Usages In Break Statements</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.LabeledStatementImpl#getUsagesInContinueStatements <em>Usages In Continue Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabeledStatementImpl extends NamedElementImpl implements LabeledStatement {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LabeledStatementImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataLabeledStatement getData(){
		if ( data == null || !(data instanceof DataLabeledStatement)){
			data = new DataLabeledStatement();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataLabeledStatement) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getLabeledStatement();
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.LABELED_STATEMENT__BODY);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.LABELED_STATEMENT__BODY, oldBody, newBody);
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
				msgs = ((InternalEObject) getData().body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.LABELED_STATEMENT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.LABELED_STATEMENT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.LABELED_STATEMENT__BODY, newBody, newBody));
	    addChangelogEntry(newBody, JavaPackage.LABELED_STATEMENT__BODY);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BreakStatement> getUsagesInBreakStatements() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().usagesInBreakStatements == null){
		getData().usagesInBreakStatements = new EObjectWithInverseResolvingEList<BreakStatement>(BreakStatement.class, this, JavaPackage.LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS, JavaPackage.BREAK_STATEMENT__LABEL);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS);			}
		return getData().usagesInBreakStatements;
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
	public EList<ContinueStatement> getUsagesInContinueStatements() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().usagesInContinueStatements == null){
		getData().usagesInContinueStatements = new EObjectWithInverseResolvingEList<ContinueStatement>(ContinueStatement.class, this, JavaPackage.LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS, JavaPackage.CONTINUE_STATEMENT__LABEL);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS);			}
		return getData().usagesInContinueStatements;
	} finally {
	loadingOnDemand = false;
}
	} 

/**
	 * <!-- begin-user-doc -->
	 *YY12
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsagesInBreakStatements()).basicAdd(otherEnd, msgs);
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsagesInContinueStatements()).basicAdd(otherEnd, msgs);
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
			case JavaPackage.LABELED_STATEMENT__BODY:
				return basicSetBody(null, msgs);
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS:
				return ((InternalEList<?>)getUsagesInBreakStatements()).basicRemove(otherEnd, msgs);
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS:
				return ((InternalEList<?>)getUsagesInContinueStatements()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.LABELED_STATEMENT__BODY:
				return getBody();
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS:
				return getUsagesInBreakStatements();
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS:
				return getUsagesInContinueStatements();
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
			case JavaPackage.LABELED_STATEMENT__BODY:
				setBody((Statement)newValue);
				return;
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS:
				getUsagesInBreakStatements().clear();
				getUsagesInBreakStatements().addAll((Collection<? extends BreakStatement>)newValue);
				return;
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS:
				getUsagesInContinueStatements().clear();
				getUsagesInContinueStatements().addAll((Collection<? extends ContinueStatement>)newValue);
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
			case JavaPackage.LABELED_STATEMENT__BODY:
				setBody((Statement)null);
				return;
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS:
				getUsagesInBreakStatements().clear();
				return;
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS:
				getUsagesInContinueStatements().clear();
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
			case JavaPackage.LABELED_STATEMENT__BODY:
				return getBody() != null;
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS:
				return getUsagesInBreakStatements() != null && !getUsagesInBreakStatements().isEmpty();
			case JavaPackage.LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS:
				return getUsagesInContinueStatements() != null && !getUsagesInContinueStatements().isEmpty();
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
protected static  class DataLabeledStatement extends DataNamedElement {


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

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getUsagesInBreakStatements() <em>Usages In Break Statements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsagesInBreakStatements()
	 * @generated
	 * @ordered
	 */
	protected EList<BreakStatement> usagesInBreakStatements;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getUsagesInContinueStatements() <em>Usages In Continue Statements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsagesInContinueStatements()
	 * @generated
	 * @ordered
	 */
	protected EList<ContinueStatement> usagesInContinueStatements;

	/**
	 *Constructor of DataLabeledStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLabeledStatement() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataLabeledStatement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link NamedElement }
	 * @generated
	 */
	//public DataLabeledStatement(DataNamedElement data)
	//{
	//	this();		
	//	
	//	name = data.name;
	//	proxy = data.proxy;
	//	usagesInImports = data.usagesInImports;
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
} //LabeledStatementImpl
