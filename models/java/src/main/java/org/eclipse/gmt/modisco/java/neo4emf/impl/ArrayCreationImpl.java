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

import org.eclipse.gmt.modisco.java.ArrayCreation;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.TypeAccess;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Creation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ArrayCreationImpl#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ArrayCreationImpl#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ArrayCreationImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayCreationImpl extends ExpressionImpl implements ArrayCreation {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayCreationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataArrayCreation getData(){
		if ( data == null || !(data instanceof DataArrayCreation)){
			data = new DataArrayCreation();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataArrayCreation) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getArrayCreation();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getDimensions() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().dimensions == null){
		getData().dimensions = new EObjectContainmentEList<Expression>(Expression.class, this, JavaPackage.ARRAY_CREATION__DIMENSIONS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ARRAY_CREATION__DIMENSIONS);			}
		return getData().dimensions;
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
	public ArrayInitializer getInitializer() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ARRAY_CREATION__INITIALIZER);
		}
		return getData().initializer;
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
	public NotificationChain basicSetInitializer(ArrayInitializer newInitializer, NotificationChain msgs) {
	
		
	
		ArrayInitializer oldInitializer = getData().initializer;
		getData().initializer = newInitializer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.ARRAY_CREATION__INITIALIZER, oldInitializer, newInitializer);
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
	public void setInitializer(ArrayInitializer newInitializer) {
	
		
	
		if (newInitializer != getData().initializer) {
			NotificationChain msgs = null;
			if (getData().initializer != null)
				msgs = ((InternalEObject) getData().initializer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ARRAY_CREATION__INITIALIZER, null, msgs);
			if (newInitializer != null)
				msgs = ((InternalEObject)newInitializer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ARRAY_CREATION__INITIALIZER, null, msgs);
			msgs = basicSetInitializer(newInitializer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ARRAY_CREATION__INITIALIZER, newInitializer, newInitializer));
	    addChangelogEntry(newInitializer, JavaPackage.ARRAY_CREATION__INITIALIZER);
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ARRAY_CREATION__TYPE);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.ARRAY_CREATION__TYPE, oldType, newType);
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
				msgs = ((InternalEObject) getData().type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ARRAY_CREATION__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ARRAY_CREATION__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ARRAY_CREATION__TYPE, newType, newType));
	    addChangelogEntry(newType, JavaPackage.ARRAY_CREATION__TYPE);
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
			case JavaPackage.ARRAY_CREATION__DIMENSIONS:
				return ((InternalEList<?>)getDimensions()).basicRemove(otherEnd, msgs);
			case JavaPackage.ARRAY_CREATION__INITIALIZER:
				return basicSetInitializer(null, msgs);
			case JavaPackage.ARRAY_CREATION__TYPE:
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
			case JavaPackage.ARRAY_CREATION__DIMENSIONS:
				return getDimensions();
			case JavaPackage.ARRAY_CREATION__INITIALIZER:
				return getInitializer();
			case JavaPackage.ARRAY_CREATION__TYPE:
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
			case JavaPackage.ARRAY_CREATION__DIMENSIONS:
				getDimensions().clear();
				getDimensions().addAll((Collection<? extends Expression>)newValue);
				return;
			case JavaPackage.ARRAY_CREATION__INITIALIZER:
				setInitializer((ArrayInitializer)newValue);
				return;
			case JavaPackage.ARRAY_CREATION__TYPE:
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
			case JavaPackage.ARRAY_CREATION__DIMENSIONS:
				getDimensions().clear();
				return;
			case JavaPackage.ARRAY_CREATION__INITIALIZER:
				setInitializer((ArrayInitializer)null);
				return;
			case JavaPackage.ARRAY_CREATION__TYPE:
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
			case JavaPackage.ARRAY_CREATION__DIMENSIONS:
				return getDimensions() != null && !getDimensions().isEmpty();
			case JavaPackage.ARRAY_CREATION__INITIALIZER:
				return getInitializer() != null;
			case JavaPackage.ARRAY_CREATION__TYPE:
				return getType() != null;
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
protected static  class DataArrayCreation extends DataExpression {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getDimensions() <em>Dimensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensions()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> dimensions;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getInitializer() <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializer()
	 * @generated
	 * @ordered
	 */
	protected ArrayInitializer initializer;

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
	 *Constructor of DataArrayCreation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataArrayCreation() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataArrayCreation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Expression }
	 * @generated
	 */
	//public DataArrayCreation(DataExpression data)
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
} //ArrayCreationImpl
