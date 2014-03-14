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

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.CompilationUnit;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassFileImpl#getOriginalFilePath <em>Original File Path</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassFileImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassFileImpl#getAttachedSource <em>Attached Source</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ClassFileImpl#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassFileImpl extends NamedElementImpl implements ClassFile {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassFileImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataClassFile getData(){
		if ( data == null || !(data instanceof DataClassFile)){
			data = new DataClassFile();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataClassFile) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getClassFile();
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
			JavaPackage.CLASS_FILE__ORIGINAL_FILE_PATH,
			oldOriginalFilePath, getData().originalFilePath));
	    addChangelogEntry(newOriginalFilePath, JavaPackage.CLASS_FILE__ORIGINAL_FILE_PATH);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractTypeDeclaration getType() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().type == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CLASS_FILE__TYPE);
		}		
		return getData().type;
		
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
	public AbstractTypeDeclaration basicGetType() {
		return data != null ? getData().type : null;
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setType(AbstractTypeDeclaration newType) {
	
		
	
		AbstractTypeDeclaration oldType = getData().type;
		getData().type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.CLASS_FILE__TYPE,
			oldType, getData().type));
	    addChangelogEntry(newType, JavaPackage.CLASS_FILE__TYPE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit getAttachedSource() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().attachedSource == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CLASS_FILE__ATTACHED_SOURCE);
		}		
		return getData().attachedSource;
		
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
	public CompilationUnit basicGetAttachedSource() {
		return data != null ? getData().attachedSource : null;
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setAttachedSource(CompilationUnit newAttachedSource) {
	
		
	
		CompilationUnit oldAttachedSource = getData().attachedSource;
		getData().attachedSource = newAttachedSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.CLASS_FILE__ATTACHED_SOURCE,
			oldAttachedSource, getData().attachedSource));
	    addChangelogEntry(newAttachedSource, JavaPackage.CLASS_FILE__ATTACHED_SOURCE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package getPackage() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().package_ == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.CLASS_FILE__PACKAGE);
		}		
		return getData().package_;
		
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
	public org.eclipse.gmt.modisco.java.Package basicGetPackage() {
		return data != null ? getData().package_ : null;
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setPackage(org.eclipse.gmt.modisco.java.Package newPackage) {
	
		
	
		org.eclipse.gmt.modisco.java.Package oldPackage = getData().package_;
		getData().package_ = newPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.CLASS_FILE__PACKAGE,
			oldPackage, getData().package_));
	    addChangelogEntry(newPackage, JavaPackage.CLASS_FILE__PACKAGE);
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
			case JavaPackage.CLASS_FILE__ORIGINAL_FILE_PATH:
				return getOriginalFilePath();
			case JavaPackage.CLASS_FILE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case JavaPackage.CLASS_FILE__ATTACHED_SOURCE:
				if (resolve) return getAttachedSource();
				return basicGetAttachedSource();
			case JavaPackage.CLASS_FILE__PACKAGE:
				if (resolve) return getPackage();
				return basicGetPackage();
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
			case JavaPackage.CLASS_FILE__ORIGINAL_FILE_PATH:
				setOriginalFilePath((String)newValue);
				return;
			case JavaPackage.CLASS_FILE__TYPE:
				setType((AbstractTypeDeclaration)newValue);
				return;
			case JavaPackage.CLASS_FILE__ATTACHED_SOURCE:
				setAttachedSource((CompilationUnit)newValue);
				return;
			case JavaPackage.CLASS_FILE__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)newValue);
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
			case JavaPackage.CLASS_FILE__ORIGINAL_FILE_PATH:
				setOriginalFilePath(DataClassFile.ORIGINAL_FILE_PATH_EDEFAULT);
				return;
			case JavaPackage.CLASS_FILE__TYPE:
				setType((AbstractTypeDeclaration)null);
				return;
			case JavaPackage.CLASS_FILE__ATTACHED_SOURCE:
				setAttachedSource((CompilationUnit)null);
				return;
			case JavaPackage.CLASS_FILE__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)null);
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
			case JavaPackage.CLASS_FILE__ORIGINAL_FILE_PATH:
				return DataClassFile.ORIGINAL_FILE_PATH_EDEFAULT == null ? getOriginalFilePath() != null : !DataClassFile.ORIGINAL_FILE_PATH_EDEFAULT.equals(getOriginalFilePath());
			case JavaPackage.CLASS_FILE__TYPE:
				return getType() != null;
			case JavaPackage.CLASS_FILE__ATTACHED_SOURCE:
				return getAttachedSource() != null;
			case JavaPackage.CLASS_FILE__PACKAGE:
				return getPackage() != null;
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
protected static  class DataClassFile extends DataNamedElement {


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
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected AbstractTypeDeclaration type;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getAttachedSource() <em>Attached Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttachedSource()
	 * @generated
	 * @ordered
	 */
	protected CompilationUnit attachedSource;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getPackage() <em>Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackage()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.gmt.modisco.java.Package package_;

	/**
	 *Constructor of DataClassFile
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataClassFile() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataClassFile
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link NamedElement }
	 * @generated
	 */
	//public DataClassFile(DataNamedElement data)
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
} //ClassFileImpl
