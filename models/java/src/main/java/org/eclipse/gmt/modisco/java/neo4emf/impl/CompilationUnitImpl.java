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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CompilationUnitImpl#getOriginalFilePath <em>Original File Path</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CompilationUnitImpl#getCommentList <em>Comment List</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CompilationUnitImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CompilationUnitImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.CompilationUnitImpl#getTypes <em>Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompilationUnitImpl extends NamedElementImpl implements CompilationUnit {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompilationUnitImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataCompilationUnit getData(){
		if ( data == null || !(data instanceof DataCompilationUnit)){
			data = new DataCompilationUnit();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataCompilationUnit) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getCompilationUnit();
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
			JavaPackage.COMPILATION_UNIT__ORIGINAL_FILE_PATH,
			oldOriginalFilePath, getData().originalFilePath));
	    addChangelogEntry(newOriginalFilePath, JavaPackage.COMPILATION_UNIT__ORIGINAL_FILE_PATH);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Comment> getCommentList() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().commentList == null){
		getData().commentList = new EObjectResolvingEList<Comment>(Comment.class, this, JavaPackage.COMPILATION_UNIT__COMMENT_LIST);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.COMPILATION_UNIT__COMMENT_LIST);			}
		return getData().commentList;
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
	public EList<ImportDeclaration> getImports() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().imports == null){
		getData().imports = new EObjectContainmentEList<ImportDeclaration>(ImportDeclaration.class, this, JavaPackage.COMPILATION_UNIT__IMPORTS);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.COMPILATION_UNIT__IMPORTS);			}
		return getData().imports;
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
	public org.eclipse.gmt.modisco.java.Package getPackage() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().package_ == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.COMPILATION_UNIT__PACKAGE);
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
			JavaPackage.COMPILATION_UNIT__PACKAGE,
			oldPackage, getData().package_));
	    addChangelogEntry(newPackage, JavaPackage.COMPILATION_UNIT__PACKAGE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractTypeDeclaration> getTypes() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().types == null){
		getData().types = new EObjectResolvingEList<AbstractTypeDeclaration>(AbstractTypeDeclaration.class, this, JavaPackage.COMPILATION_UNIT__TYPES);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.COMPILATION_UNIT__TYPES);			}
		return getData().types;
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
			case JavaPackage.COMPILATION_UNIT__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.COMPILATION_UNIT__ORIGINAL_FILE_PATH:
				return getOriginalFilePath();
			case JavaPackage.COMPILATION_UNIT__COMMENT_LIST:
				return getCommentList();
			case JavaPackage.COMPILATION_UNIT__IMPORTS:
				return getImports();
			case JavaPackage.COMPILATION_UNIT__PACKAGE:
				if (resolve) return getPackage();
				return basicGetPackage();
			case JavaPackage.COMPILATION_UNIT__TYPES:
				return getTypes();
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
			case JavaPackage.COMPILATION_UNIT__ORIGINAL_FILE_PATH:
				setOriginalFilePath((String)newValue);
				return;
			case JavaPackage.COMPILATION_UNIT__COMMENT_LIST:
				getCommentList().clear();
				getCommentList().addAll((Collection<? extends Comment>)newValue);
				return;
			case JavaPackage.COMPILATION_UNIT__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends ImportDeclaration>)newValue);
				return;
			case JavaPackage.COMPILATION_UNIT__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)newValue);
				return;
			case JavaPackage.COMPILATION_UNIT__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection<? extends AbstractTypeDeclaration>)newValue);
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
			case JavaPackage.COMPILATION_UNIT__ORIGINAL_FILE_PATH:
				setOriginalFilePath(DataCompilationUnit.ORIGINAL_FILE_PATH_EDEFAULT);
				return;
			case JavaPackage.COMPILATION_UNIT__COMMENT_LIST:
				getCommentList().clear();
				return;
			case JavaPackage.COMPILATION_UNIT__IMPORTS:
				getImports().clear();
				return;
			case JavaPackage.COMPILATION_UNIT__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)null);
				return;
			case JavaPackage.COMPILATION_UNIT__TYPES:
				getTypes().clear();
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
			case JavaPackage.COMPILATION_UNIT__ORIGINAL_FILE_PATH:
				return DataCompilationUnit.ORIGINAL_FILE_PATH_EDEFAULT == null ? getOriginalFilePath() != null : !DataCompilationUnit.ORIGINAL_FILE_PATH_EDEFAULT.equals(getOriginalFilePath());
			case JavaPackage.COMPILATION_UNIT__COMMENT_LIST:
				return getCommentList() != null && !getCommentList().isEmpty();
			case JavaPackage.COMPILATION_UNIT__IMPORTS:
				return getImports() != null && !getImports().isEmpty();
			case JavaPackage.COMPILATION_UNIT__PACKAGE:
				return getPackage() != null;
			case JavaPackage.COMPILATION_UNIT__TYPES:
				return getTypes() != null && !getTypes().isEmpty();
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
protected static  class DataCompilationUnit extends DataNamedElement {


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
	 * The cached value of the '{@link #getCommentList() <em>Comment List</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommentList()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> commentList;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<ImportDeclaration> imports;

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

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractTypeDeclaration> types;

	/**
	 *Constructor of DataCompilationUnit
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataCompilationUnit() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataCompilationUnit
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link NamedElement }
	 * @generated
	 */
	//public DataCompilationUnit(DataNamedElement data)
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
} //CompilationUnitImpl
