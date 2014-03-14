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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.Manifest;
import org.eclipse.gmt.modisco.java.ManifestAttribute;
import org.eclipse.gmt.modisco.java.ManifestEntry;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Manifest</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ManifestImpl#getMainAttributes <em>Main Attributes</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ManifestImpl#getEntryAttributes <em>Entry Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ManifestImpl extends Neo4emfObject implements Manifest {

	 
	
	/**
	 * The cached value of the data structure {@link DataManifest <em>data</em> } 
	 * @generated
	 */
	 	protected DataManifest data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ManifestImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataManifest getData(){
		if ( data == null || !(data instanceof DataManifest)){
			data = new DataManifest();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataManifest) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getManifest();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ManifestAttribute> getMainAttributes() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().mainAttributes == null){
		getData().mainAttributes = new EObjectContainmentEList<ManifestAttribute>(ManifestAttribute.class, this, JavaPackage.MANIFEST__MAIN_ATTRIBUTES);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.MANIFEST__MAIN_ATTRIBUTES);			}
		return getData().mainAttributes;
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
	public EList<ManifestEntry> getEntryAttributes() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().entryAttributes == null){
		getData().entryAttributes = new EObjectContainmentEList<ManifestEntry>(ManifestEntry.class, this, JavaPackage.MANIFEST__ENTRY_ATTRIBUTES);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.MANIFEST__ENTRY_ATTRIBUTES);			}
		return getData().entryAttributes;
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
			case JavaPackage.MANIFEST__MAIN_ATTRIBUTES:
				return ((InternalEList<?>)getMainAttributes()).basicRemove(otherEnd, msgs);
			case JavaPackage.MANIFEST__ENTRY_ATTRIBUTES:
				return ((InternalEList<?>)getEntryAttributes()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.MANIFEST__MAIN_ATTRIBUTES:
				return getMainAttributes();
			case JavaPackage.MANIFEST__ENTRY_ATTRIBUTES:
				return getEntryAttributes();
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
			case JavaPackage.MANIFEST__MAIN_ATTRIBUTES:
				getMainAttributes().clear();
				getMainAttributes().addAll((Collection<? extends ManifestAttribute>)newValue);
				return;
			case JavaPackage.MANIFEST__ENTRY_ATTRIBUTES:
				getEntryAttributes().clear();
				getEntryAttributes().addAll((Collection<? extends ManifestEntry>)newValue);
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
			case JavaPackage.MANIFEST__MAIN_ATTRIBUTES:
				getMainAttributes().clear();
				return;
			case JavaPackage.MANIFEST__ENTRY_ATTRIBUTES:
				getEntryAttributes().clear();
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
			case JavaPackage.MANIFEST__MAIN_ATTRIBUTES:
				return getMainAttributes() != null && !getMainAttributes().isEmpty();
			case JavaPackage.MANIFEST__ENTRY_ATTRIBUTES:
				return getEntryAttributes() != null && !getEntryAttributes().isEmpty();
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
protected static  class DataManifest {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getMainAttributes() <em>Main Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMainAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<ManifestAttribute> mainAttributes;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getEntryAttributes() <em>Entry Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntryAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<ManifestEntry> entryAttributes;

	/**
	 *Constructor of DataManifest
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataManifest() {
		
	}
	
		
	
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
} //ManifestImpl
