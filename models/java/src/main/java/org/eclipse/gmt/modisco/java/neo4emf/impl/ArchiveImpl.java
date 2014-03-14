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

import org.eclipse.gmt.modisco.java.Archive;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.Manifest;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Archive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ArchiveImpl#getOriginalFilePath <em>Original File Path</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ArchiveImpl#getClassFiles <em>Class Files</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ArchiveImpl#getManifest <em>Manifest</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArchiveImpl extends NamedElementImpl implements Archive {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArchiveImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataArchive getData(){
		if ( data == null || !(data instanceof DataArchive)){
			data = new DataArchive();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataArchive) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getArchive();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOriginalFilePath() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().originalFilePath;
		
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
	public void setOriginalFilePath(String newOriginalFilePath) {
	
		
	
		String oldOriginalFilePath = getData().originalFilePath;
		getData().originalFilePath = newOriginalFilePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.ARCHIVE__ORIGINAL_FILE_PATH,
			oldOriginalFilePath, getData().originalFilePath));
	    addChangelogEntry(newOriginalFilePath, JavaPackage.ARCHIVE__ORIGINAL_FILE_PATH);
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
		getData().classFiles = new EObjectContainmentEList<ClassFile>(ClassFile.class, this, JavaPackage.ARCHIVE__CLASS_FILES);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ARCHIVE__CLASS_FILES);			}
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
	public Manifest getManifest() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.ARCHIVE__MANIFEST);
		}
		return getData().manifest;
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
	public NotificationChain basicSetManifest(Manifest newManifest, NotificationChain msgs) {
	
		
	
		Manifest oldManifest = getData().manifest;
		getData().manifest = newManifest;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.ARCHIVE__MANIFEST, oldManifest, newManifest);
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
	public void setManifest(Manifest newManifest) {
	
		
	
		if (newManifest != getData().manifest) {
			NotificationChain msgs = null;
			if (getData().manifest != null)
				msgs = ((InternalEObject) getData().manifest).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ARCHIVE__MANIFEST, null, msgs);
			if (newManifest != null)
				msgs = ((InternalEObject)newManifest).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.ARCHIVE__MANIFEST, null, msgs);
			msgs = basicSetManifest(newManifest, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.ARCHIVE__MANIFEST, newManifest, newManifest));
	    addChangelogEntry(newManifest, JavaPackage.ARCHIVE__MANIFEST);
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
			case JavaPackage.ARCHIVE__CLASS_FILES:
				return ((InternalEList<?>)getClassFiles()).basicRemove(otherEnd, msgs);
			case JavaPackage.ARCHIVE__MANIFEST:
				return basicSetManifest(null, msgs);
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
			case JavaPackage.ARCHIVE__ORIGINAL_FILE_PATH:
				return getOriginalFilePath();
			case JavaPackage.ARCHIVE__CLASS_FILES:
				return getClassFiles();
			case JavaPackage.ARCHIVE__MANIFEST:
				return getManifest();
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
			case JavaPackage.ARCHIVE__ORIGINAL_FILE_PATH:
				setOriginalFilePath((String)newValue);
				return;
			case JavaPackage.ARCHIVE__CLASS_FILES:
				getClassFiles().clear();
				getClassFiles().addAll((Collection<? extends ClassFile>)newValue);
				return;
			case JavaPackage.ARCHIVE__MANIFEST:
				setManifest((Manifest)newValue);
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
			case JavaPackage.ARCHIVE__ORIGINAL_FILE_PATH:
				setOriginalFilePath(DataArchive.ORIGINAL_FILE_PATH_EDEFAULT);
				return;
			case JavaPackage.ARCHIVE__CLASS_FILES:
				getClassFiles().clear();
				return;
			case JavaPackage.ARCHIVE__MANIFEST:
				setManifest((Manifest)null);
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
			case JavaPackage.ARCHIVE__ORIGINAL_FILE_PATH:
				return DataArchive.ORIGINAL_FILE_PATH_EDEFAULT == null ? getOriginalFilePath() != null : !DataArchive.ORIGINAL_FILE_PATH_EDEFAULT.equals(getOriginalFilePath());
			case JavaPackage.ARCHIVE__CLASS_FILES:
				return getClassFiles() != null && !getClassFiles().isEmpty();
			case JavaPackage.ARCHIVE__MANIFEST:
				return getManifest() != null;
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
protected static  class DataArchive extends DataNamedElement {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #getOriginalFilePath() <em>Original File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalFilePath()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGINAL_FILE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginalFilePath() <em>Original File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalFilePath()
	 * @generated
	 * @ordered
	 */
	protected String originalFilePath = ORIGINAL_FILE_PATH_EDEFAULT;

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
	 * The cached value of the '{@link #getManifest() <em>Manifest</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManifest()
	 * @generated
	 * @ordered
	 */
	protected Manifest manifest;

	/**
	 *Constructor of DataArchive
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataArchive() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataArchive
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link NamedElement }
	 * @generated
	 */
	//public DataArchive(DataNamedElement data)
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
		result.append(" (originalFilePath: ");
		result.append(originalFilePath);
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
} //ArchiveImpl
