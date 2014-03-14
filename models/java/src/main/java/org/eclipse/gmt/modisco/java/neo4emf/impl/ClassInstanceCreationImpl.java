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
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.TypeAccess;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Instance Creation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassInstanceCreationImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassInstanceCreationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassInstanceCreationImpl#getTypeArguments <em>Type Arguments</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassInstanceCreationImpl#getAnonymousClassDeclaration <em>Anonymous Class Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassInstanceCreationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassInstanceCreationImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassInstanceCreationImpl extends ExpressionImpl implements ClassInstanceCreation {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassInstanceCreationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataClassInstanceCreation getData(){
		if ( data == null || !(data instanceof DataClassInstanceCreation)){
			data = new DataClassInstanceCreation();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataClassInstanceCreation) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getClassInstanceCreation();
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CLASS_INSTANCE_CREATION__METHOD);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.CLASS_INSTANCE_CREATION__METHOD, oldMethod, newMethod);
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.CLASS_INSTANCE_CREATION__METHOD, newMethod, newMethod));
	    addChangelogEntry(newMethod, JavaPackage.CLASS_INSTANCE_CREATION__METHOD);
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
		getData().arguments = new EObjectContainmentEList<Expression>(Expression.class, this, JavaPackage.CLASS_INSTANCE_CREATION__ARGUMENTS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CLASS_INSTANCE_CREATION__ARGUMENTS);			}
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
		getData().typeArguments = new EObjectContainmentEList<TypeAccess>(TypeAccess.class, this, JavaPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS);			}
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
	public AnonymousClassDeclaration getAnonymousClassDeclaration() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION);
		}
		return getData().anonymousClassDeclaration;
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
	public NotificationChain basicSetAnonymousClassDeclaration(AnonymousClassDeclaration newAnonymousClassDeclaration, NotificationChain msgs) {
	
		
	
		AnonymousClassDeclaration oldAnonymousClassDeclaration = getData().anonymousClassDeclaration;
		getData().anonymousClassDeclaration = newAnonymousClassDeclaration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, oldAnonymousClassDeclaration, newAnonymousClassDeclaration);
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
	public void setAnonymousClassDeclaration(AnonymousClassDeclaration newAnonymousClassDeclaration) {
	
		
	
		if (newAnonymousClassDeclaration != getData().anonymousClassDeclaration) {
			NotificationChain msgs = null;
			if (getData().anonymousClassDeclaration != null)
				msgs = ((InternalEObject) getData().anonymousClassDeclaration).eInverseRemove(this, JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION, AnonymousClassDeclaration.class, msgs);
			if (newAnonymousClassDeclaration != null)
				msgs = ((InternalEObject)newAnonymousClassDeclaration).eInverseAdd(this, JavaPackage.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION, AnonymousClassDeclaration.class, msgs);
			msgs = basicSetAnonymousClassDeclaration(newAnonymousClassDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, newAnonymousClassDeclaration, newAnonymousClassDeclaration));
	    addChangelogEntry(newAnonymousClassDeclaration, JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION);
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION, oldExpression, newExpression);
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
				msgs = ((InternalEObject) getData().expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION, newExpression, newExpression));
	    addChangelogEntry(newExpression, JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getType() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CLASS_INSTANCE_CREATION__TYPE);
		}
		return getData().type;
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
	public NotificationChain basicSetType(TypeAccess newType, NotificationChain msgs) {
	
		
	
		TypeAccess oldType = getData().type;
		getData().type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.CLASS_INSTANCE_CREATION__TYPE, oldType, newType);
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
	public void setType(TypeAccess newType) {
	
		
	
		if (newType != getData().type) {
			NotificationChain msgs = null;
			if (getData().type != null)
				msgs = ((InternalEObject) getData().type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.CLASS_INSTANCE_CREATION__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.CLASS_INSTANCE_CREATION__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.CLASS_INSTANCE_CREATION__TYPE, newType, newType));
	    addChangelogEntry(newType, JavaPackage.CLASS_INSTANCE_CREATION__TYPE);
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
			case JavaPackage.CLASS_INSTANCE_CREATION__METHOD:
				if (getData().method != null)
					msgs = ((InternalEObject)getData().method).eInverseRemove(this, JavaPackage.ABSTRACT_METHOD_DECLARATION__USAGES, AbstractMethodDeclaration.class, msgs);
				return basicSetMethod((AbstractMethodDeclaration)otherEnd, msgs);
			case JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				if (getData().anonymousClassDeclaration != null)
					msgs = ((InternalEObject)getData().anonymousClassDeclaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION, null, msgs);
				return basicSetAnonymousClassDeclaration((AnonymousClassDeclaration)otherEnd, msgs);
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
			case JavaPackage.CLASS_INSTANCE_CREATION__METHOD:
				return basicSetMethod(null, msgs);
			case JavaPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
				return ((InternalEList<?>)getTypeArguments()).basicRemove(otherEnd, msgs);
			case JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				return basicSetAnonymousClassDeclaration(null, msgs);
			case JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE:
				return basicSetType(null, msgs);
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
			case JavaPackage.CLASS_INSTANCE_CREATION__METHOD:
				if (resolve) return getMethod();
				return basicGetMethod();
			case JavaPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				return getArguments();
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
				return getTypeArguments();
			case JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				return getAnonymousClassDeclaration();
			case JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				return getExpression();
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE:
				return getType();
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
			case JavaPackage.CLASS_INSTANCE_CREATION__METHOD:
				setMethod((AbstractMethodDeclaration)newValue);
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
				getTypeArguments().clear();
				getTypeArguments().addAll((Collection<? extends TypeAccess>)newValue);
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				setAnonymousClassDeclaration((AnonymousClassDeclaration)newValue);
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE:
				setType((TypeAccess)newValue);
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
			case JavaPackage.CLASS_INSTANCE_CREATION__METHOD:
				setMethod((AbstractMethodDeclaration)null);
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				getArguments().clear();
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
				getTypeArguments().clear();
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				setAnonymousClassDeclaration((AnonymousClassDeclaration)null);
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE:
				setType((TypeAccess)null);
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
			case JavaPackage.CLASS_INSTANCE_CREATION__METHOD:
				return getMethod() != null;
			case JavaPackage.CLASS_INSTANCE_CREATION__ARGUMENTS:
				return getArguments() != null && !getArguments().isEmpty();
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS:
				return getTypeArguments() != null && !getTypeArguments().isEmpty();
			case JavaPackage.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION:
				return getAnonymousClassDeclaration() != null;
			case JavaPackage.CLASS_INSTANCE_CREATION__EXPRESSION:
				return getExpression() != null;
			case JavaPackage.CLASS_INSTANCE_CREATION__TYPE:
				return getType() != null;
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
				case JavaPackage.CLASS_INSTANCE_CREATION__METHOD: return JavaPackage.ABSTRACT_METHOD_INVOCATION__METHOD;
				case JavaPackage.CLASS_INSTANCE_CREATION__ARGUMENTS: return JavaPackage.ABSTRACT_METHOD_INVOCATION__ARGUMENTS;
				case JavaPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS: return JavaPackage.ABSTRACT_METHOD_INVOCATION__TYPE_ARGUMENTS;
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
				case JavaPackage.ABSTRACT_METHOD_INVOCATION__METHOD: return JavaPackage.CLASS_INSTANCE_CREATION__METHOD;
				case JavaPackage.ABSTRACT_METHOD_INVOCATION__ARGUMENTS: return JavaPackage.CLASS_INSTANCE_CREATION__ARGUMENTS;
				case JavaPackage.ABSTRACT_METHOD_INVOCATION__TYPE_ARGUMENTS: return JavaPackage.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS;
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
protected static  class DataClassInstanceCreation extends DataExpression {


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
	 * The cached value of the '{@link #getAnonymousClassDeclaration() <em>Anonymous Class Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnonymousClassDeclaration()
	 * @generated
	 * @ordered
	 */
	protected AnonymousClassDeclaration anonymousClassDeclaration;

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
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected TypeAccess type;

	/**
	 *Constructor of DataClassInstanceCreation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataClassInstanceCreation() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataClassInstanceCreation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	//public DataClassInstanceCreation(DataExpression data)
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
} //ClassInstanceCreationImpl
