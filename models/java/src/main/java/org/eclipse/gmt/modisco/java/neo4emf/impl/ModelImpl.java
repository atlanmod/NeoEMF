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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.Archive;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.UnresolvedItem;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModelImpl#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModelImpl#getOrphanTypes <em>Orphan Types</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModelImpl#getUnresolvedItems <em>Unresolved Items</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModelImpl#getCompilationUnits <em>Compilation Units</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModelImpl#getClassFiles <em>Class Files</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ModelImpl#getArchives <em>Archives</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends Neo4emfObject implements Model {

	 
	
	/**
	 * The cached value of the data structure {@link DataModel <em>data</em> } 
	 * @generated
	 */
	 	protected DataModel data;
	 
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 
	protected DataModel getData(){
		if ( data == null || !(data instanceof DataModel)){
			data = new DataModel();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataModel) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getModel();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().name;
		
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
	public void setName(String newName) {
	
		
		String oldName = getData().name;
		getData().name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.MODEL__NAME,
			oldName, getData().name));
	    addChangelogEntry(newName, JavaPackage.MODEL__NAME);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<org.eclipse.gmt.modisco.java.Package> getOwnedElements() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().ownedElements == null){
		getData().ownedElements = new EObjectContainmentWithInverseEList<org.eclipse.gmt.modisco.java.Package>(org.eclipse.gmt.modisco.java.Package.class, this, JavaPackage.MODEL__OWNED_ELEMENTS, JavaPackage.PACKAGE__MODEL);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.MODEL__OWNED_ELEMENTS);			}
		return getData().ownedElements;
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
	public EList<Type> getOrphanTypes() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().orphanTypes == null){
		getData().orphanTypes = new EObjectContainmentEList<Type>(Type.class, this, JavaPackage.MODEL__ORPHAN_TYPES);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.MODEL__ORPHAN_TYPES);			}
		return getData().orphanTypes;
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
	public EList<UnresolvedItem> getUnresolvedItems() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().unresolvedItems == null){
		getData().unresolvedItems = new EObjectContainmentEList<UnresolvedItem>(UnresolvedItem.class, this, JavaPackage.MODEL__UNRESOLVED_ITEMS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.MODEL__UNRESOLVED_ITEMS);			}
		return getData().unresolvedItems;
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
	public EList<CompilationUnit> getCompilationUnits() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().compilationUnits == null){
		getData().compilationUnits = new EObjectContainmentEList<CompilationUnit>(CompilationUnit.class, this, JavaPackage.MODEL__COMPILATION_UNITS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.MODEL__COMPILATION_UNITS);			}
		return getData().compilationUnits;
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
	public EList<ClassFile> getClassFiles() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().classFiles == null){
		getData().classFiles = new EObjectContainmentEList<ClassFile>(ClassFile.class, this, JavaPackage.MODEL__CLASS_FILES);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.MODEL__CLASS_FILES);			}
		return getData().classFiles;
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
	public EList<Archive> getArchives() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().archives == null){
		getData().archives = new EObjectContainmentEList<Archive>(Archive.class, this, JavaPackage.MODEL__ARCHIVES);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.MODEL__ARCHIVES);			}
		return getData().archives;
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
			case JavaPackage.MODEL__OWNED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedElements()).basicAdd(otherEnd, msgs);
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
			case JavaPackage.MODEL__OWNED_ELEMENTS:
				return ((InternalEList<?>)getOwnedElements()).basicRemove(otherEnd, msgs);
			case JavaPackage.MODEL__ORPHAN_TYPES:
				return ((InternalEList<?>)getOrphanTypes()).basicRemove(otherEnd, msgs);
			case JavaPackage.MODEL__UNRESOLVED_ITEMS:
				return ((InternalEList<?>)getUnresolvedItems()).basicRemove(otherEnd, msgs);
			case JavaPackage.MODEL__COMPILATION_UNITS:
				return ((InternalEList<?>)getCompilationUnits()).basicRemove(otherEnd, msgs);
			case JavaPackage.MODEL__CLASS_FILES:
				return ((InternalEList<?>)getClassFiles()).basicRemove(otherEnd, msgs);
			case JavaPackage.MODEL__ARCHIVES:
				return ((InternalEList<?>)getArchives()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.MODEL__NAME:
				return getName();
			case JavaPackage.MODEL__OWNED_ELEMENTS:
				return getOwnedElements();
			case JavaPackage.MODEL__ORPHAN_TYPES:
				return getOrphanTypes();
			case JavaPackage.MODEL__UNRESOLVED_ITEMS:
				return getUnresolvedItems();
			case JavaPackage.MODEL__COMPILATION_UNITS:
				return getCompilationUnits();
			case JavaPackage.MODEL__CLASS_FILES:
				return getClassFiles();
			case JavaPackage.MODEL__ARCHIVES:
				return getArchives();
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
			case JavaPackage.MODEL__NAME:
				setName((String)newValue);
				return;
			case JavaPackage.MODEL__OWNED_ELEMENTS:
				getOwnedElements().clear();
				getOwnedElements().addAll((Collection<? extends org.eclipse.gmt.modisco.java.Package>)newValue);
				return;
			case JavaPackage.MODEL__ORPHAN_TYPES:
				getOrphanTypes().clear();
				getOrphanTypes().addAll((Collection<? extends Type>)newValue);
				return;
			case JavaPackage.MODEL__UNRESOLVED_ITEMS:
				getUnresolvedItems().clear();
				getUnresolvedItems().addAll((Collection<? extends UnresolvedItem>)newValue);
				return;
			case JavaPackage.MODEL__COMPILATION_UNITS:
				getCompilationUnits().clear();
				getCompilationUnits().addAll((Collection<? extends CompilationUnit>)newValue);
				return;
			case JavaPackage.MODEL__CLASS_FILES:
				getClassFiles().clear();
				getClassFiles().addAll((Collection<? extends ClassFile>)newValue);
				return;
			case JavaPackage.MODEL__ARCHIVES:
				getArchives().clear();
				getArchives().addAll((Collection<? extends Archive>)newValue);
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
			case JavaPackage.MODEL__NAME:
				setName(DataModel.NAME_EDEFAULT);
				return;
			case JavaPackage.MODEL__OWNED_ELEMENTS:
				getOwnedElements().clear();
				return;
			case JavaPackage.MODEL__ORPHAN_TYPES:
				getOrphanTypes().clear();
				return;
			case JavaPackage.MODEL__UNRESOLVED_ITEMS:
				getUnresolvedItems().clear();
				return;
			case JavaPackage.MODEL__COMPILATION_UNITS:
				getCompilationUnits().clear();
				return;
			case JavaPackage.MODEL__CLASS_FILES:
				getClassFiles().clear();
				return;
			case JavaPackage.MODEL__ARCHIVES:
				getArchives().clear();
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
			case JavaPackage.MODEL__NAME:
				return DataModel.NAME_EDEFAULT == null ? getName() != null : !DataModel.NAME_EDEFAULT.equals(getName());
			case JavaPackage.MODEL__OWNED_ELEMENTS:
				return getOwnedElements() != null && !getOwnedElements().isEmpty();
			case JavaPackage.MODEL__ORPHAN_TYPES:
				return getOrphanTypes() != null && !getOrphanTypes().isEmpty();
			case JavaPackage.MODEL__UNRESOLVED_ITEMS:
				return getUnresolvedItems() != null && !getUnresolvedItems().isEmpty();
			case JavaPackage.MODEL__COMPILATION_UNITS:
				return getCompilationUnits() != null && !getCompilationUnits().isEmpty();
			case JavaPackage.MODEL__CLASS_FILES:
				return getClassFiles() != null && !getClassFiles().isEmpty();
			case JavaPackage.MODEL__ARCHIVES:
				return getArchives() != null && !getArchives().isEmpty();
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
protected static  class DataModel {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getOwnedElements() <em>Owned Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<org.eclipse.gmt.modisco.java.Package> ownedElements;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getOrphanTypes() <em>Orphan Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrphanTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> orphanTypes;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getUnresolvedItems() <em>Unresolved Items</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnresolvedItems()
	 * @generated
	 * @ordered
	 */
	protected EList<UnresolvedItem> unresolvedItems;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getCompilationUnits() <em>Compilation Units</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompilationUnits()
	 * @generated
	 * @ordered
	 */
	protected EList<CompilationUnit> compilationUnits;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getClassFiles() <em>Class Files</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassFiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassFile> classFiles;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getArchives() <em>Archives</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArchives()
	 * @generated
	 * @ordered
	 */
	protected EList<Archive> archives;

	/**
	 *Constructor of DataModel
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataModel() {
		
	}
	
		
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (name: ");
		result.append(name);
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
} //ModelImpl
