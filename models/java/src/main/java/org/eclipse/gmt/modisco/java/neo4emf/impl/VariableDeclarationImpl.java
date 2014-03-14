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

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.VariableDeclaration;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.VariableDeclarationImpl#getExtraArrayDimensions <em>Extra Array Dimensions</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.VariableDeclarationImpl#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.VariableDeclarationImpl#getUsageInVariableAccess <em>Usage In Variable Access</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class VariableDeclarationImpl extends NamedElementImpl implements VariableDeclaration {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataVariableDeclaration getData(){
		if ( data == null || !(data instanceof DataVariableDeclaration)){
			data = new DataVariableDeclaration();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataVariableDeclaration) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getVariableDeclaration();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getExtraArrayDimensions() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().extraArrayDimensions;
		
	} finally {
	loadingOnDemand = false;
}
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setExtraArrayDimensions(int newExtraArrayDimensions) {
	
		
	
		int oldExtraArrayDimensions = getData().extraArrayDimensions;
		getData().extraArrayDimensions = newExtraArrayDimensions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.VARIABLE_DECLARATION__EXTRA_ARRAY_DIMENSIONS,
			oldExtraArrayDimensions, getData().extraArrayDimensions));
	    addChangelogEntry(newExtraArrayDimensions, JavaPackage.VARIABLE_DECLARATION__EXTRA_ARRAY_DIMENSIONS);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getInitializer() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.VARIABLE_DECLARATION__INITIALIZER);
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
	public NotificationChain basicSetInitializer(Expression newInitializer, NotificationChain msgs) {
	
		
	
		Expression oldInitializer = getData().initializer;
		getData().initializer = newInitializer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.VARIABLE_DECLARATION__INITIALIZER, oldInitializer, newInitializer);
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
	public void setInitializer(Expression newInitializer) {
	
		
	
		if (newInitializer != getData().initializer) {
			NotificationChain msgs = null;
			if (getData().initializer != null)
				msgs = ((InternalEObject) getData().initializer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.VARIABLE_DECLARATION__INITIALIZER, null, msgs);
			if (newInitializer != null)
				msgs = ((InternalEObject)newInitializer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.VARIABLE_DECLARATION__INITIALIZER, null, msgs);
			msgs = basicSetInitializer(newInitializer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.VARIABLE_DECLARATION__INITIALIZER, newInitializer, newInitializer));
	    addChangelogEntry(newInitializer, JavaPackage.VARIABLE_DECLARATION__INITIALIZER);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SingleVariableAccess> getUsageInVariableAccess() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().usageInVariableAccess == null){
		getData().usageInVariableAccess = new EObjectWithInverseResolvingEList<SingleVariableAccess>(SingleVariableAccess.class, this, JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS, JavaPackage.SINGLE_VARIABLE_ACCESS__VARIABLE);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS);			}
		return getData().usageInVariableAccess;
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
			case JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsageInVariableAccess()).basicAdd(otherEnd, msgs);
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
			case JavaPackage.VARIABLE_DECLARATION__INITIALIZER:
				return basicSetInitializer(null, msgs);
			case JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS:
				return ((InternalEList<?>)getUsageInVariableAccess()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.VARIABLE_DECLARATION__EXTRA_ARRAY_DIMENSIONS:
				return getExtraArrayDimensions();
			case JavaPackage.VARIABLE_DECLARATION__INITIALIZER:
				return getInitializer();
			case JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS:
				return getUsageInVariableAccess();
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
			case JavaPackage.VARIABLE_DECLARATION__EXTRA_ARRAY_DIMENSIONS:
				setExtraArrayDimensions((Integer)newValue);
				return;
			case JavaPackage.VARIABLE_DECLARATION__INITIALIZER:
				setInitializer((Expression)newValue);
				return;
			case JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS:
				getUsageInVariableAccess().clear();
				getUsageInVariableAccess().addAll((Collection<? extends SingleVariableAccess>)newValue);
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
			case JavaPackage.VARIABLE_DECLARATION__EXTRA_ARRAY_DIMENSIONS:
				setExtraArrayDimensions(DataVariableDeclaration.EXTRA_ARRAY_DIMENSIONS_EDEFAULT);
				return;
			case JavaPackage.VARIABLE_DECLARATION__INITIALIZER:
				setInitializer((Expression)null);
				return;
			case JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS:
				getUsageInVariableAccess().clear();
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
			case JavaPackage.VARIABLE_DECLARATION__EXTRA_ARRAY_DIMENSIONS:
				return getExtraArrayDimensions() != DataVariableDeclaration.EXTRA_ARRAY_DIMENSIONS_EDEFAULT;
			case JavaPackage.VARIABLE_DECLARATION__INITIALIZER:
				return getInitializer() != null;
			case JavaPackage.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS:
				return getUsageInVariableAccess() != null && !getUsageInVariableAccess().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY27
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		if (data != null) result.append(data.toString());
		
		return result.toString();
		}

/*
* Neo4EMF inserted code -- begin
*/

/*
* Neo4EMF inserted code -- end
*/




// data Class generation 
protected static  class DataVariableDeclaration extends DataNamedElement {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getExtraArrayDimensions() <em>Extra Array Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraArrayDimensions()
	 * @generated
	 * @ordered
	 */
	protected static final int EXTRA_ARRAY_DIMENSIONS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getExtraArrayDimensions() <em>Extra Array Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraArrayDimensions()
	 * @generated
	 * @ordered
	 */
	protected int extraArrayDimensions = EXTRA_ARRAY_DIMENSIONS_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getInitializer() <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializer()
	 * @generated
	 * @ordered
	 */
	protected Expression initializer;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getUsageInVariableAccess() <em>Usage In Variable Access</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsageInVariableAccess()
	 * @generated
	 * @ordered
	 */
	protected EList<SingleVariableAccess> usageInVariableAccess;

	/**
	 *Constructor of DataVariableDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableDeclaration() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataVariableDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link NamedElement }
	 * @generated
	 */
	//public DataVariableDeclaration(DataNamedElement data)
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
		result.append(" (extraArrayDimensions: ");
		result.append(extraArrayDimensions);
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
} //VariableDeclarationImpl
