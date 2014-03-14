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
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.PackageAccess;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Access</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.PackageAccessImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.PackageAccessImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageAccessImpl extends NamespaceAccessImpl implements PackageAccess {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageAccessImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataPackageAccess getData(){
		if ( data == null || !(data instanceof DataPackageAccess)){
			data = new DataPackageAccess();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataPackageAccess) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getPackageAccess();
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
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.PACKAGE_ACCESS__PACKAGE);
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
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackage(org.eclipse.gmt.modisco.java.Package newPackage, NotificationChain msgs) {
	
		
	
		org.eclipse.gmt.modisco.java.Package oldPackage = getData().package_;
		getData().package_ = newPackage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.PACKAGE_ACCESS__PACKAGE, oldPackage, newPackage);
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
	public void setPackage(org.eclipse.gmt.modisco.java.Package newPackage) {
	
		
	
		if (newPackage != getData().package_) {
			NotificationChain msgs = null;
			if (getData().package_ != null)
				msgs = ((InternalEObject) getData().package_).eInverseRemove(this, JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS, org.eclipse.gmt.modisco.java.Package.class, msgs);
			if (newPackage != null)
				msgs = ((InternalEObject)newPackage).eInverseAdd(this, JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS, org.eclipse.gmt.modisco.java.Package.class, msgs);
			msgs = basicSetPackage(newPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.PACKAGE_ACCESS__PACKAGE, newPackage, newPackage));
	    addChangelogEntry(newPackage, JavaPackage.PACKAGE_ACCESS__PACKAGE);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageAccess getQualifier() {
		try {
			loadingOnDemand = true;	
	  		
		if (isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.PACKAGE_ACCESS__QUALIFIER);
		}
		return getData().qualifier;
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
	public NotificationChain basicSetQualifier(PackageAccess newQualifier, NotificationChain msgs) {
	
		
	
		PackageAccess oldQualifier = getData().qualifier;
		getData().qualifier = newQualifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.PACKAGE_ACCESS__QUALIFIER, oldQualifier, newQualifier);
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
	public void setQualifier(PackageAccess newQualifier) {
	
		
	
		if (newQualifier != getData().qualifier) {
			NotificationChain msgs = null;
			if (getData().qualifier != null)
				msgs = ((InternalEObject) getData().qualifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.PACKAGE_ACCESS__QUALIFIER, null, msgs);
			if (newQualifier != null)
				msgs = ((InternalEObject)newQualifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaPackage.PACKAGE_ACCESS__QUALIFIER, null, msgs);
			msgs = basicSetQualifier(newQualifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.PACKAGE_ACCESS__QUALIFIER, newQualifier, newQualifier));
	    addChangelogEntry(newQualifier, JavaPackage.PACKAGE_ACCESS__QUALIFIER);
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
			case JavaPackage.PACKAGE_ACCESS__PACKAGE:
				if (getData().package_ != null)
					msgs = ((InternalEObject)getData().package_).eInverseRemove(this, JavaPackage.PACKAGE__USAGES_IN_PACKAGE_ACCESS, org.eclipse.gmt.modisco.java.Package.class, msgs);
				return basicSetPackage((org.eclipse.gmt.modisco.java.Package)otherEnd, msgs);
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
			case JavaPackage.PACKAGE_ACCESS__PACKAGE:
				return basicSetPackage(null, msgs);
			case JavaPackage.PACKAGE_ACCESS__QUALIFIER:
				return basicSetQualifier(null, msgs);
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
			case JavaPackage.PACKAGE_ACCESS__PACKAGE:
				if (resolve) return getPackage();
				return basicGetPackage();
			case JavaPackage.PACKAGE_ACCESS__QUALIFIER:
				return getQualifier();
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
			case JavaPackage.PACKAGE_ACCESS__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)newValue);
				return;
			case JavaPackage.PACKAGE_ACCESS__QUALIFIER:
				setQualifier((PackageAccess)newValue);
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
			case JavaPackage.PACKAGE_ACCESS__PACKAGE:
				setPackage((org.eclipse.gmt.modisco.java.Package)null);
				return;
			case JavaPackage.PACKAGE_ACCESS__QUALIFIER:
				setQualifier((PackageAccess)null);
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
			case JavaPackage.PACKAGE_ACCESS__PACKAGE:
				return getPackage() != null;
			case JavaPackage.PACKAGE_ACCESS__QUALIFIER:
				return getQualifier() != null;
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
protected static  class DataPackageAccess extends DataNamespaceAccess {


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
	 * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifier()
	 * @generated
	 * @ordered
	 */
	protected PackageAccess qualifier;

	/**
	 *Constructor of DataPackageAccess
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataPackageAccess() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataPackageAccess
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link NamespaceAccess }
	 * @generated
	 */
	//public DataPackageAccess(DataNamespaceAccess data)
	//{
	//	this();		
	//	
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
} //PackageAccessImpl
