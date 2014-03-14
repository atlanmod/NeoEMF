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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.AbstractVariablesContainer;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Variables Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AbstractVariablesContainerImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.AbstractVariablesContainerImpl#getFragments <em>Fragments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractVariablesContainerImpl extends ASTNodeImpl implements AbstractVariablesContainer {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractVariablesContainerImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataAbstractVariablesContainer getData(){
		if ( data == null || !(data instanceof DataAbstractVariablesContainer)){
			data = new DataAbstractVariablesContainer();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataAbstractVariablesContainer) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getAbstractVariablesContainer();
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE, oldType, newType);
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
				msgs = ((InternalEObject) getData().type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE, newType, newType));
	    addChangelogEntry(newType, JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableDeclarationFragment> getFragments() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().fragments == null){
		getData().fragments = new EObjectContainmentWithInverseEList<VariableDeclarationFragment>(VariableDeclarationFragment.class, this, JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS, JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS);			}
		return getData().fragments;
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
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFragments()).basicAdd(otherEnd, msgs);
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
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE:
				return basicSetType(null, msgs);
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS:
				return ((InternalEList<?>)getFragments()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE:
				return getType();
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS:
				return getFragments();
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
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE:
				setType((TypeAccess)newValue);
				return;
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS:
				getFragments().clear();
				getFragments().addAll((Collection<? extends VariableDeclarationFragment>)newValue);
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
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE:
				setType((TypeAccess)null);
				return;
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS:
				getFragments().clear();
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
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__TYPE:
				return getType() != null;
			case JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS:
				return getFragments() != null && !getFragments().isEmpty();
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
protected static  class DataAbstractVariablesContainer extends DataASTNode {


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

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getFragments() <em>Fragments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragments()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableDeclarationFragment> fragments;

	/**
	 *Constructor of DataAbstractVariablesContainer
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAbstractVariablesContainer() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataAbstractVariablesContainer
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	//public DataAbstractVariablesContainer(DataASTNode data)
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
} //AbstractVariablesContainerImpl
