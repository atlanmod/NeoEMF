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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.gmt.modisco.java.AbstractVariablesContainer;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.VariableDeclarationFragmentImpl#getVariablesContainer <em>Variables Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableDeclarationFragmentImpl extends VariableDeclarationImpl implements VariableDeclarationFragment {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationFragmentImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataVariableDeclarationFragment getData(){
		if ( data == null || !(data instanceof DataVariableDeclarationFragment)){
			data = new DataVariableDeclarationFragment();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataVariableDeclarationFragment) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getVariableDeclarationFragment();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractVariablesContainer getVariablesContainer() {
		try {
			loadingOnDemand = true;	
	  
		if (isLoaded() && eContainer() == null) {
			AbstractVariablesContainer variablesContainer = (AbstractVariablesContainer) ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER);
			basicSetVariablesContainer(variablesContainer,null);}
		return (AbstractVariablesContainer)eContainer();
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
	public NotificationChain basicSetVariablesContainer(AbstractVariablesContainer newVariablesContainer, NotificationChain msgs) {
	
		msgs = eBasicSetContainer((InternalEObject)newVariablesContainer, JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER, msgs);
		return msgs;
	}

 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setVariablesContainer(AbstractVariablesContainer newVariablesContainer) {
	
		
	
		if (newVariablesContainer != eInternalContainer() || (eContainerFeatureID() != JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER && newVariablesContainer != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newVariablesContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newVariablesContainer != null)
				msgs = ((InternalEObject)newVariablesContainer).eInverseAdd(this, JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS, AbstractVariablesContainer.class, msgs);
			msgs = basicSetVariablesContainer(newVariablesContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER, newVariablesContainer, newVariablesContainer));
	    addChangelogEntry(newVariablesContainer, JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER);
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
			case JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetVariablesContainer((AbstractVariablesContainer)otherEnd, msgs);
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
			case JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER:
				return basicSetVariablesContainer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY14
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER:
				return eInternalContainer().eInverseRemove(this, JavaPackage.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS, AbstractVariablesContainer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER:
				return getVariablesContainer();
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
			case JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER:
				setVariablesContainer((AbstractVariablesContainer)newValue);
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
			case JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER:
				setVariablesContainer((AbstractVariablesContainer)null);
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
			case JavaPackage.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER:
				return getVariablesContainer() != null;
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
protected static  class DataVariableDeclarationFragment extends DataVariableDeclaration {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 *Constructor of DataVariableDeclarationFragment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataVariableDeclarationFragment() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataVariableDeclarationFragment
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link VariableDeclaration }
	 * @generated
	 */
	//public DataVariableDeclarationFragment(DataVariableDeclaration data)
	//{
	//	this();		
	//	
	//	extraArrayDimensions = data.extraArrayDimensions;
	//	initializer = data.initializer;
	//	usageInVariableAccess = data.usageInVariableAccess;
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
} //VariableDeclarationFragmentImpl
