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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.EnumDeclarationImpl#getEnumConstants <em>Enum Constants</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumDeclarationImpl extends AbstractTypeDeclarationImpl implements EnumDeclaration {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumDeclarationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataEnumDeclaration getData(){
		if ( data == null || !(data instanceof DataEnumDeclaration)){
			data = new DataEnumDeclaration();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataEnumDeclaration) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getEnumDeclaration();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EnumConstantDeclaration> getEnumConstants() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().enumConstants == null){
		getData().enumConstants = new EObjectContainmentEList<EnumConstantDeclaration>(EnumConstantDeclaration.class, this, JavaPackage.ENUM_DECLARATION__ENUM_CONSTANTS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ENUM_DECLARATION__ENUM_CONSTANTS);			}
		return getData().enumConstants;
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
			case JavaPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				return ((InternalEList<?>)getEnumConstants()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				return getEnumConstants();
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
			case JavaPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				getEnumConstants().clear();
				getEnumConstants().addAll((Collection<? extends EnumConstantDeclaration>)newValue);
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
			case JavaPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				getEnumConstants().clear();
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
			case JavaPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				return getEnumConstants() != null && !getEnumConstants().isEmpty();
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
protected static  class DataEnumDeclaration extends DataAbstractTypeDeclaration {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getEnumConstants() <em>Enum Constants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumConstants()
	 * @generated
	 * @ordered
	 */
	protected EList<EnumConstantDeclaration> enumConstants;

	/**
	 *Constructor of DataEnumDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataEnumDeclaration() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataEnumDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link AbstractTypeDeclaration }
	 * @generated
	 */
	//public DataEnumDeclaration(DataAbstractTypeDeclaration data)
	//{
	//	this();		
	//	
	//	usagesInTypeAccess = data.usagesInTypeAccess;
	//	bodyDeclarations = data.bodyDeclarations;
	//	commentsBeforeBody = data.commentsBeforeBody;
	//	commentsAfterBody = data.commentsAfterBody;
	//	package_ = data.package_;
	//	superInterfaces = data.superInterfaces;
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
} //EnumDeclarationImpl
