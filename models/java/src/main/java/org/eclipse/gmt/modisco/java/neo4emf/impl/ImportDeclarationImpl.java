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

import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ImportDeclarationImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.neo4emf.impl.ImportDeclarationImpl#getImportedElement <em>Imported Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportDeclarationImpl extends ASTNodeImpl implements ImportDeclaration {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportDeclarationImpl() {
		super();
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataImportDeclaration getData(){
		if ( data == null || !(data instanceof DataImportDeclaration)){
			data = new DataImportDeclaration();
			if (isLoaded())
			((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
		return (DataImportDeclaration) data;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getImportDeclaration();
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {
		try {
			loadingOnDemand = true;	
	  		
		return getData().static_;
		
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
	public void setStatic(boolean newStatic) {
	
		
	
		boolean oldStatic = getData().static_;
		getData().static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			JavaPackage.IMPORT_DECLARATION__STATIC,
			oldStatic, getData().static_));
	    addChangelogEntry(newStatic, JavaPackage.IMPORT_DECLARATION__STATIC);
	} 

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement getImportedElement() {
		try {
			loadingOnDemand = true;	
	  
		if (getData().importedElement == null && isLoaded()) {
			((INeo4emfResource) this.eResource()).getOnDemand(this, JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT);
		}		
		return getData().importedElement;
		
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
	public NamedElement basicGetImportedElement() {
		return data != null ? getData().importedElement : null;
	}
	/**
	 * <!-- begin-user-doc -->
	 *XX9-BIS
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImportedElement(NamedElement newImportedElement, NotificationChain msgs) {
	
		
	
		NamedElement oldImportedElement = getData().importedElement;
		getData().importedElement = newImportedElement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT, oldImportedElement, newImportedElement);
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
	public void setImportedElement(NamedElement newImportedElement) {
	
		
	
		if (newImportedElement != getData().importedElement) {
			NotificationChain msgs = null;
			if (getData().importedElement != null)
				msgs = ((InternalEObject) getData().importedElement).eInverseRemove(this, JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS, NamedElement.class, msgs);
			if (newImportedElement != null)
				msgs = ((InternalEObject)newImportedElement).eInverseAdd(this, JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS, NamedElement.class, msgs);
			msgs = basicSetImportedElement(newImportedElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT, newImportedElement, newImportedElement));
	    addChangelogEntry(newImportedElement, JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT);
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
			case JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT:
				if (getData().importedElement != null)
					msgs = ((InternalEObject)getData().importedElement).eInverseRemove(this, JavaPackage.NAMED_ELEMENT__USAGES_IN_IMPORTS, NamedElement.class, msgs);
				return basicSetImportedElement((NamedElement)otherEnd, msgs);
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
			case JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT:
				return basicSetImportedElement(null, msgs);
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
			case JavaPackage.IMPORT_DECLARATION__STATIC:
				return isStatic();
			case JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT:
				if (resolve) return getImportedElement();
				return basicGetImportedElement();
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
			case JavaPackage.IMPORT_DECLARATION__STATIC:
				setStatic((Boolean)newValue);
				return;
			case JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT:
				setImportedElement((NamedElement)newValue);
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
			case JavaPackage.IMPORT_DECLARATION__STATIC:
				setStatic(DataImportDeclaration.STATIC_EDEFAULT);
				return;
			case JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT:
				setImportedElement((NamedElement)null);
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
			case JavaPackage.IMPORT_DECLARATION__STATIC:
				return isStatic() != DataImportDeclaration.STATIC_EDEFAULT;
			case JavaPackage.IMPORT_DECLARATION__IMPORTED_ELEMENT:
				return getImportedElement() != null;
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
protected static  class DataImportDeclaration extends DataASTNode {


// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected boolean static_ = STATIC_EDEFAULT;

// The goal of this template is to BLAH, BLAH, BLAH

	/**
	 * The cached value of the '{@link #getImportedElement() <em>Imported Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedElement()
	 * @generated
	 * @ordered
	 */
	protected NamedElement importedElement;

	/**
	 *Constructor of DataImportDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataImportDeclaration() {
		 super(); 
	}
	
		
	/**
	 *Constructor of DataImportDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link ASTNode }
	 * @generated
	 */
	//public DataImportDeclaration(DataASTNode data)
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
		result.append(" (static: ");
		result.append(static_);
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
} //ImportDeclarationImpl
