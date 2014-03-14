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

import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.MethodDeclarationImpl#getExtraArrayDimensions <em>Extra Array Dimensions</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.MethodDeclarationImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.MethodDeclarationImpl#getRedefinedMethodDeclaration <em>Redefined Method Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.MethodDeclarationImpl#getRedefinitions <em>Redefinitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodDeclarationImpl extends AbstractMethodDeclarationImpl implements MethodDeclaration {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodDeclarationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataMethodDeclaration getData(){
		if ( data == null || !(data instanceof DataMethodDeclaration)){
			data = new DataMethodDeclaration();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataMethodDeclaration) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getMethodDeclaration();
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
			JavaPackage.METHOD_DECLARATION__EXTRA_ARRAY_DIMENSIONS,
			oldExtraArrayDimensions, getData().extraArrayDimensions));
	    addChangelogEntry(newExtraArrayDimensions, JavaPackage.METHOD_DECLARATION__EXTRA_ARRAY_DIMENSIONS);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getReturnType() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.METHOD_DECLARATION__RETURN_TYPE);
		}
		return getData().returnType;
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
	public NotificationChain basicSetReturnType(TypeAccess newReturnType, NotificationChain msgs) {
	
		
	
		TypeAccess oldReturnType = getData().returnType;
		getData().returnType = newReturnType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.METHOD_DECLARATION__RETURN_TYPE, oldReturnType, newReturnType);
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
	public void setReturnType(TypeAccess newReturnType) {
	
		
	
		if (newReturnType != getData().returnType) {
			NotificationChain msgs = null;
			if (getData().returnType != null)
				msgs = ((InternalEObject) getData().returnType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.METHOD_DECLARATION__RETURN_TYPE, null, msgs);
			if (newReturnType != null)
				msgs = ((InternalEObject)newReturnType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.METHOD_DECLARATION__RETURN_TYPE, null, msgs);
			msgs = basicSetReturnType(newReturnType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.METHOD_DECLARATION__RETURN_TYPE, newReturnType, newReturnType));
	    addChangelogEntry(newReturnType, JavaPackage.METHOD_DECLARATION__RETURN_TYPE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDeclaration getRedefinedMethodDeclaration() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().redefinedMethodDeclaration == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION);
		}		
		return getData().redefinedMethodDeclaration;
		
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
	public MethodDeclaration basicGetRedefinedMethodDeclaration() {
		return data != null ? getData().redefinedMethodDeclaration : null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRedefinedMethodDeclaration(MethodDeclaration newRedefinedMethodDeclaration, NotificationChain msgs) {
	
		
	
		MethodDeclaration oldRedefinedMethodDeclaration = getData().redefinedMethodDeclaration;
		getData().redefinedMethodDeclaration = newRedefinedMethodDeclaration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION, oldRedefinedMethodDeclaration, newRedefinedMethodDeclaration);
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
	public void setRedefinedMethodDeclaration(MethodDeclaration newRedefinedMethodDeclaration) {
	
		
	
		if (newRedefinedMethodDeclaration != getData().redefinedMethodDeclaration) {
			NotificationChain msgs = null;
			if (getData().redefinedMethodDeclaration != null)
				msgs = ((InternalEObject) getData().redefinedMethodDeclaration).eInverseRemove(this, JavaPackage.METHOD_DECLARATION__REDEFINITIONS, MethodDeclaration.class, msgs);
			if (newRedefinedMethodDeclaration != null)
				msgs = ((InternalEObject)newRedefinedMethodDeclaration).eInverseAdd(this, JavaPackage.METHOD_DECLARATION__REDEFINITIONS, MethodDeclaration.class, msgs);
			msgs = basicSetRedefinedMethodDeclaration(newRedefinedMethodDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION, newRedefinedMethodDeclaration, newRedefinedMethodDeclaration));
	    addChangelogEntry(newRedefinedMethodDeclaration, JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MethodDeclaration> getRedefinitions() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().redefinitions == null){
		getData().redefinitions = new EObjectWithInverseResolvingEList<MethodDeclaration>(MethodDeclaration.class, this, JavaPackage.METHOD_DECLARATION__REDEFINITIONS, JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.METHOD_DECLARATION__REDEFINITIONS);			}
		return getData().redefinitions;
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
			case JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION:
				if (getData().redefinedMethodDeclaration != null)
					msgs = ((InternalEObject)getData().redefinedMethodDeclaration).eInverseRemove(this, JavaPackage.METHOD_DECLARATION__REDEFINITIONS, MethodDeclaration.class, msgs);
				return basicSetRedefinedMethodDeclaration((MethodDeclaration)otherEnd, msgs);
			case JavaPackage.METHOD_DECLARATION__REDEFINITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRedefinitions()).basicAdd(otherEnd, msgs);
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
			case JavaPackage.METHOD_DECLARATION__RETURN_TYPE:
				return basicSetReturnType(null, msgs);
			case JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION:
				return basicSetRedefinedMethodDeclaration(null, msgs);
			case JavaPackage.METHOD_DECLARATION__REDEFINITIONS:
				return ((InternalEList<?>)getRedefinitions()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.METHOD_DECLARATION__EXTRA_ARRAY_DIMENSIONS:
				return getExtraArrayDimensions();
			case JavaPackage.METHOD_DECLARATION__RETURN_TYPE:
				return getReturnType();
			case JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION:
				if (resolve) return getRedefinedMethodDeclaration();
				return basicGetRedefinedMethodDeclaration();
			case JavaPackage.METHOD_DECLARATION__REDEFINITIONS:
				return getRedefinitions();
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
			case JavaPackage.METHOD_DECLARATION__EXTRA_ARRAY_DIMENSIONS:
				setExtraArrayDimensions((Integer)newValue);
				return;
			case JavaPackage.METHOD_DECLARATION__RETURN_TYPE:
				setReturnType((TypeAccess)newValue);
				return;
			case JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION:
				setRedefinedMethodDeclaration((MethodDeclaration)newValue);
				return;
			case JavaPackage.METHOD_DECLARATION__REDEFINITIONS:
				getRedefinitions().clear();
				getRedefinitions().addAll((Collection<? extends MethodDeclaration>)newValue);
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
			case JavaPackage.METHOD_DECLARATION__EXTRA_ARRAY_DIMENSIONS:
				setExtraArrayDimensions(DataMethodDeclaration.EXTRA_ARRAY_DIMENSIONS_EDEFAULT);
				return;
			case JavaPackage.METHOD_DECLARATION__RETURN_TYPE:
				setReturnType((TypeAccess)null);
				return;
			case JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION:
				setRedefinedMethodDeclaration((MethodDeclaration)null);
				return;
			case JavaPackage.METHOD_DECLARATION__REDEFINITIONS:
				getRedefinitions().clear();
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
			case JavaPackage.METHOD_DECLARATION__EXTRA_ARRAY_DIMENSIONS:
				return getExtraArrayDimensions() != DataMethodDeclaration.EXTRA_ARRAY_DIMENSIONS_EDEFAULT;
			case JavaPackage.METHOD_DECLARATION__RETURN_TYPE:
				return getReturnType() != null;
			case JavaPackage.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION:
				return getRedefinedMethodDeclaration() != null;
			case JavaPackage.METHOD_DECLARATION__REDEFINITIONS:
				return getRedefinitions() != null && !getRedefinitions().isEmpty();
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
protected static  class DataMethodDeclaration extends DataAbstractMethodDeclaration {


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
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected TypeAccess returnType;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getRedefinedMethodDeclaration() <em>Redefined Method Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinedMethodDeclaration()
	 * @generated
	 * @ordered
	 */
	protected MethodDeclaration redefinedMethodDeclaration;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getRedefinitions() <em>Redefinitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodDeclaration> redefinitions;

	/**
	 *Constructor of DataMethodDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMethodDeclaration() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataMethodDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link AbstractMethodDeclaration }
	 * @generated
	 */
	//public DataMethodDeclaration(DataAbstractMethodDeclaration data)
	//{
	//	this();		
	//	
	//	body = data.body;
	//	parameters = data.parameters;
	//	thrownExceptions = data.thrownExceptions;
	//	typeParameters = data.typeParameters;
	//	usagesInDocComments = data.usagesInDocComments;
	//	usages = data.usages;
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
} //MethodDeclarationImpl
