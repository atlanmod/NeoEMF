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
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.SuperConstructorInvocation;
import org.eclipse.gmt.modisco.java.TypeAccess;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Super Constructor Invocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.SuperConstructorInvocationImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.SuperConstructorInvocationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.SuperConstructorInvocationImpl#getTypeArguments <em>Type Arguments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.SuperConstructorInvocationImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperConstructorInvocationImpl extends StatementImpl implements SuperConstructorInvocation {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SuperConstructorInvocationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataSuperConstructorInvocation getData(){
		if ( data == null || !(data instanceof DataSuperConstructorInvocation)){
			data = new DataSuperConstructorInvocation();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataSuperConstructorInvocation) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getSuperConstructorInvocation();
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD, oldMethod, newMethod);
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
				msgs = ((InternalEObject) getData().method).eInverseRemove(this, JavaPackage.ABSTRACT_METHOD_DECLARATION__USAGES, AbstractMethodDeclaration.class, msgs);
			if (newMethod != null)
				msgs = ((InternalEObject)newMethod).eInverseAdd(this, JavaPackage.ABSTRACT_METHOD_DECLARATION__USAGES, AbstractMethodDeclaration.class, msgs);
			msgs = basicSetMethod(newMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD, newMethod, newMethod));
	    addChangelogEntry(newMethod, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getArguments() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().arguments == null){
		getData().arguments = new EObjectContainmentEList<Expression>(Expression.class, this, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS);			}
		return getData().arguments;
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
	public EList<TypeAccess> getTypeArguments() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().typeArguments == null){
		getData().typeArguments = new EObjectContainmentEList<TypeAccess>(TypeAccess.class, this, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS);			}
		return getData().typeArguments;
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
	public Expression getExpression() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION, oldExpression, newExpression);
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
				msgs = ((InternalEObject) getData().expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION, newExpression, newExpression));
	    addChangelogEntry(newExpression, JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION);
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
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD:
				if (getData().method != null)
					msgs = ((InternalEObject)getData().method).eInverseRemove(this, JavaPackage.ABSTRACT_METHOD_DECLARATION__USAGES, AbstractMethodDeclaration.class, msgs);
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
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD:
				return basicSetMethod(null, msgs);
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
				return ((InternalEList<?>)getTypeArguments()).basicRemove(otherEnd, msgs);
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				return basicSetExpression(null, msgs);
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
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD:
				if (resolve) return getMethod();
				return basicGetMethod();
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				return getArguments();
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
				return getTypeArguments();
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				return getExpression();
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
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD:
				setMethod((AbstractMethodDeclaration)newValue);
				return;
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
				getTypeArguments().clear();
				getTypeArguments().addAll((Collection<? extends TypeAccess>)newValue);
				return;
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				setExpression((Expression)newValue);
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
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD:
				setMethod((AbstractMethodDeclaration)null);
				return;
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				getArguments().clear();
				return;
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
				getTypeArguments().clear();
				return;
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				setExpression((Expression)null);
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
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD:
				return getMethod() != null;
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS:
				return getArguments() != null && !getArguments().isEmpty();
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS:
				return getTypeArguments() != null && !getTypeArguments().isEmpty();
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION:
				return getExpression() != null;
		}
		return super.eIsSet(featureID);
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY19
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractMethodInvocation.class) {
			switch (derivedFeatureID) {
				case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD: return JavaPackage.ABSTRACT_METHOD_INVOCATION__METHOD;
				case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS: return JavaPackage.ABSTRACT_METHOD_INVOCATION__ARGUMENTS;
				case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS: return JavaPackage.ABSTRACT_METHOD_INVOCATION__TYPE_ARGUMENTS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY20
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractMethodInvocation.class) {
			switch (baseFeatureID) {
				case JavaPackage.ABSTRACT_METHOD_INVOCATION__METHOD: return JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__METHOD;
				case JavaPackage.ABSTRACT_METHOD_INVOCATION__ARGUMENTS: return JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS;
				case JavaPackage.ABSTRACT_METHOD_INVOCATION__TYPE_ARGUMENTS: return JavaPackage.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}


/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




// data Class generation 
protected static  class DataSuperConstructorInvocation extends DataStatement {


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
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> arguments;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getTypeArguments() <em>Type Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeAccess> typeArguments;

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

	/**
	 *Constructor of DataSuperConstructorInvocation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSuperConstructorInvocation() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataSuperConstructorInvocation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Statement }
	 * @generated
	 */
	//public DataSuperConstructorInvocation(DataStatement data)
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
} //SuperConstructorInvocationImpl
