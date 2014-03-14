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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.NamedElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.NamedElementImpl#isProxy <em>Proxy</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.NamedElementImpl#getUsagesInImports <em>Usages In Imports</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class NamedElementImpl extends ASTNodeImpl implements NamedElement {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedElementImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataNamedElement getData(){
		if ( data == null || !(data instanceof DataNamedElement)){
			data = new DataNamedElement();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataNamedElement) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getNamedElement();
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
			JavaPackage.NAMED_ELEMENT__NAME,
			oldName, getData().name));
	    addChangelogEntry(newName, JavaPackage.NAMED_ELEMENT__NAME);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isProxy() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().proxy;
		
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
	public void setProxy(boolean newProxy) {
	
		
	
		boolean oldProxy = getData().proxy;
		getData().proxy = newProxy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.NAMED_ELEMENT__PROXY,
			oldProxy, getData().proxy));
	    addChangelogEntry(newProxy, JavaPackage.NAMED_ELEMENT__PROXY);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ImportDeclaration> getUsagesInImports() {
		try {
			loadingOnDemand = true;	
	   
		
		if (getData().usagesInImports == null){
		getData().usagesInImports = new EObjectWithInverseResolvingEList<ImportDeclaration>(ImportDeclaration.class, this, JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS, JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT);
			if (isLoaded()) 
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS);			}
		return getData().usagesInImports;
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
			case JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsagesInImports()).basicAdd(otherEnd, msgs);
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
			case JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS:
				return ((InternalEList<?>)getUsagesInImports()).basicRemove(otherEnd, msgs);
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
			case JavaPackage.NAMED_ELEMENT__NAME:
				return getName();
			case JavaPackage.NAMED_ELEMENT__PROXY:
				return isProxy();
			case JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS:
				return getUsagesInImports();
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
			case JavaPackage.NAMED_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case JavaPackage.NAMED_ELEMENT__PROXY:
				setProxy((Boolean)newValue);
				return;
			case JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS:
				getUsagesInImports().clear();
				getUsagesInImports().addAll((Collection<? extends ImportDeclaration>)newValue);
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
			case JavaPackage.NAMED_ELEMENT__NAME:
				setName(DataNamedElement.NAME_EDEFAULT);
				return;
			case JavaPackage.NAMED_ELEMENT__PROXY:
				setProxy(DataNamedElement.PROXY_EDEFAULT);
				return;
			case JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS:
				getUsagesInImports().clear();
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
			case JavaPackage.NAMED_ELEMENT__NAME:
				return DataNamedElement.NAME_EDEFAULT == null ? getName() != null : !DataNamedElement.NAME_EDEFAULT.equals(getName());
			case JavaPackage.NAMED_ELEMENT__PROXY:
				return isProxy() != DataNamedElement.PROXY_EDEFAULT;
			case JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS:
				return getUsagesInImports() != null && !getUsagesInImports().isEmpty();
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
protected static  class DataNamedElement extends DataASTNode {


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
	 * The default value of the '{@link #isProxy() <em>Proxy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProxy()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PROXY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isProxy() <em>Proxy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProxy()
	 * @generated
	 * @ordered
	 */
	protected boolean proxy = PROXY_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getUsagesInImports() <em>Usages In Imports</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsagesInImports()
	 * @generated
	 * @ordered
	 */
	protected EList<ImportDeclaration> usagesInImports;

	/**
	 *Constructor of DataNamedElement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataNamedElement() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataNamedElement
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	//public DataNamedElement(DataASTNode data)
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
		result.append(" (name: ");
		result.append(name);
		result.append(", proxy: ");
		result.append(proxy);
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
} //NamedElementImpl
