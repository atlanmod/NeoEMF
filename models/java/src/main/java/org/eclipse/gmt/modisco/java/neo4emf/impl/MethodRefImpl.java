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

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodRef;
import org.eclipse.gmt.modisco.java.MethodRefParameter;
import org.eclipse.gmt.modisco.java.TypeAccess;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.MethodRefImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.MethodRefImpl#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.MethodRefImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodRefImpl extends ASTNodeImpl implements MethodRef {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodRefImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataMethodRef getData(){
		if ( data == null || !(data instanceof DataMethodRef)){
			data = new DataMethodRef();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataMethodRef) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getMethodRef();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractMethodDeclaration getMethod() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().method == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.METHOD_REF__METHOD);
		}		
		return getData().method;
		
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
	public AbstractMethodDeclaration basicGetMethod() {
		return data != null ? getData().method : null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethod(AbstractMethodDeclaration newMethod, NotificationChain msgs) {
	
		
	
		AbstractMethodDeclaration oldMethod = getData().method;
		getData().method = newMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.METHOD_REF__METHOD, oldMethod, newMethod);
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
	public void setMethod(AbstractMethodDeclaration newMethod) {
	
		
	
		if (newMethod != getData().method) {
			NotificationChain msgs = null;
			if (getData().method != null)
				msgs = ((InternalEObject) getData().method).eInverseRemove(this, JavaPackage.ABSTRACT_METHOD_DECLARATION__USAGES_IN_DOC_COMMENTS, AbstractMethodDeclaration.class, msgs);
			if (newMethod != null)
				msgs = ((InternalEObject)newMethod).eInverseAdd(this, JavaPackage.ABSTRACT_METHOD_DECLARATION__USAGES_IN_DOC_COMMENTS, AbstractMethodDeclaration.class, msgs);
			msgs = basicSetMethod(newMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.METHOD_REF__METHOD, newMethod, newMethod));
	    addChangelogEntry(newMethod, JavaPackage.METHOD_REF__METHOD);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getQualifier() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.METHOD_REF__QUALIFIER);
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
	public NotificationChain basicSetQualifier(TypeAccess newQualifier, NotificationChain msgs) {
	
		
	
		TypeAccess oldQualifier = getData().qualifier;
		getData().qualifier = newQualifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.METHOD_REF__QUALIFIER, oldQualifier, newQualifier);
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
	public void setQualifier(TypeAccess newQualifier) {
	
		
	
		if (newQualifier != getData().qualifier) {
			NotificationChain msgs = null;
			if (getData().qualifier != null)
				msgs = ((InternalEObject) getData().qualifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.METHOD_REF__QUALIFIER, null, msgs);
			if (newQualifier != null)
				msgs = ((InternalEObject)newQualifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.METHOD_REF__QUALIFIER, null, msgs);
			msgs = basicSetQualifier(newQualifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.METHOD_REF__QUALIFIER, newQualifier, newQualifier));
	    addChangelogEntry(newQualifier, JavaPackage.METHOD_REF__QUALIFIER);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MethodRefParameter> getParameters() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().parameters == null){
		getData().parameters = new EObjectContainmentEList<MethodRefParameter>(MethodRefParameter.class, this, JavaPackage.METHOD_REF__PARAMETERS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.METHOD_REF__PARAMETERS);			}
		return getData().parameters;
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
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaPackage.METHOD_REF__METHOD:
				if (getData().method != null)
					msgs = ((InternalEObject)getData().method).eInverseRemove(this, JavaPackage.ABSTRACT_METHOD_DECLARATION__USAGES_IN_DOC_COMMENTS, AbstractMethodDeclaration.class, msgs);
				return basicSetMethod((AbstractMethodDeclaration)otherEnd, msgs);
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
			case JavaPackage.METHOD_REF__METHOD:
				return basicSetMethod(null, msgs);
			case JavaPackage.METHOD_REF__QUALIFIER:
				return basicSetQualifier(null, msgs);
			case JavaPackage.METHOD_REF__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.METHOD_REF__METHOD:
				if (resolve) return getMethod();
				return basicGetMethod();
			case JavaPackage.METHOD_REF__QUALIFIER:
				return getQualifier();
			case JavaPackage.METHOD_REF__PARAMETERS:
				return getParameters();
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
			case JavaPackage.METHOD_REF__METHOD:
				setMethod((AbstractMethodDeclaration)newValue);
				return;
			case JavaPackage.METHOD_REF__QUALIFIER:
				setQualifier((TypeAccess)newValue);
				return;
			case JavaPackage.METHOD_REF__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends MethodRefParameter>)newValue);
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
			case JavaPackage.METHOD_REF__METHOD:
				setMethod((AbstractMethodDeclaration)null);
				return;
			case JavaPackage.METHOD_REF__QUALIFIER:
				setQualifier((TypeAccess)null);
				return;
			case JavaPackage.METHOD_REF__PARAMETERS:
				getParameters().clear();
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
			case JavaPackage.METHOD_REF__METHOD:
				return getMethod() != null;
			case JavaPackage.METHOD_REF__QUALIFIER:
				return getQualifier() != null;
			case JavaPackage.METHOD_REF__PARAMETERS:
				return getParameters() != null && !getParameters().isEmpty();
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
protected static  class DataMethodRef extends DataASTNode {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected AbstractMethodDeclaration method;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifier()
	 * @generated
	 * @ordered
	 */
	protected TypeAccess qualifier;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodRefParameter> parameters;

	/**
	 *Constructor of DataMethodRef
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMethodRef() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataMethodRef
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	//public DataMethodRef(DataASTNode data)
	//{
	//	this();		
	//	
	//	comments = data.comments;
	//	originalCompilationUnit = data.originalCompilationUnit;
	//	originalClassFile = data.originalClassFile;
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
} //MethodRefImpl
