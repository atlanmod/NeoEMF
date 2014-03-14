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

import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.ManifestAttribute;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Manifest Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ManifestAttributeImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ManifestAttributeImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ManifestAttributeImpl extends Neo4emfObject implements ManifestAttribute {

	 
	
	/**
	 * The cached value of the data structure {@link DataManifestAttribute <em>data</em> } 
	 * @generated
	 */
	 	protected DataManifestAttribute data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ManifestAttributeImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataManifestAttribute getData(){
		if ( data == null || !(data instanceof DataManifestAttribute)){
			data = new DataManifestAttribute();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataManifestAttribute) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getManifestAttribute();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKey() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().key;
		
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
	public void setKey(String newKey) {
	
		
		String oldKey = getData().key;
		getData().key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MANIFEST_ATTRIBUTE__KEY,
			oldKey, getData().key));
	    addChangelogEntry(newKey, JavaPackage.MANIFEST_ATTRIBUTE__KEY);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().value;
		
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
	public void setValue(String newValue) {
	
		
		String oldValue = getData().value;
		getData().value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MANIFEST_ATTRIBUTE__VALUE,
			oldValue, getData().value));
	    addChangelogEntry(newValue, JavaPackage.MANIFEST_ATTRIBUTE__VALUE);
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
			case JavaPackage.MANIFEST_ATTRIBUTE__KEY:
				return getKey();
			case JavaPackage.MANIFEST_ATTRIBUTE__VALUE:
				return getValue();
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
			case JavaPackage.MANIFEST_ATTRIBUTE__KEY:
				setKey((String)newValue);
				return;
			case JavaPackage.MANIFEST_ATTRIBUTE__VALUE:
				setValue((String)newValue);
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
			case JavaPackage.MANIFEST_ATTRIBUTE__KEY:
				setKey(DataManifestAttribute.KEY_EDEFAULT);
				return;
			case JavaPackage.MANIFEST_ATTRIBUTE__VALUE:
				setValue(DataManifestAttribute.VALUE_EDEFAULT);
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
			case JavaPackage.MANIFEST_ATTRIBUTE__KEY:
				return DataManifestAttribute.KEY_EDEFAULT == null ? getKey() != null : !DataManifestAttribute.KEY_EDEFAULT.equals(getKey());
			case JavaPackage.MANIFEST_ATTRIBUTE__VALUE:
				return DataManifestAttribute.VALUE_EDEFAULT == null ? getValue() != null : !DataManifestAttribute.VALUE_EDEFAULT.equals(getValue());
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
protected static  class DataManifestAttribute {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected String key = KEY_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 *Constructor of DataManifestAttribute
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataManifestAttribute() {
		
	}
	
		
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (key: ");
		result.append(key);
		result.append(", value: ");
		result.append(value);
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
} //ManifestAttributeImpl
